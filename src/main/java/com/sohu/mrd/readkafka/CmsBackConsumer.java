package com.sohu.mrd.readkafka;

import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;






import cn.pezy.lightning.kafka.Consumer;

/**
 * Back CMS news consumer
 * 
 * @author Ma Di
 * 
 */
//public class CmsBackConsumer extends Consumer {
public class CmsBackConsumer {

	private static final Log LOG = LogFactory.getLog(CmsBackConsumer.class);

	private static final String SID = "sid";
	private static final String ST = "st";
	private static final String OP = "op";
	private static final String DOCID = "docId";
	private static final String MD5 = "md5";
	private static final String PLATFORM = "p";
	private static final String TS = "ts";
	public static final int KUAIXUN = 6;
	public static final int WEATHER = 11;
	public static final int SUB = 12;
	public static final int PROMOTE = 14;
	public static final int STOCKPUSH = 15;

	private static final String CONTENT = "content";
	private static final String TITLE = "title";
	private static final String MEDIA = "source"; // 改为media
	private static final String IMAGE = "image";
	private static final String DATA_SOURCE = "url";
	private static final String CREATE_TEIME = "st";
	private static final String BREAD = "navigator";
	private static final String SEND_TIME = "sendTs";
	private static final String FROM = "from";
	private static final String SUBTYPE = "subType";
	private static final String SOURCE_FROM = "source_from";
	//private static final String THEMEIMGURL = "themeImgUrl";    //lxj,defined,2015-04-27
//	 private static HotMediaProcess bp = new HotMediaProcess();

	//private static  KafkahotNewsViaMediaProcess km = new KafkahotNewsViaMediaProcess();
	
	public static long last_received_time = 0L;
	
	public static long first_alert_time = 0L;
    // Backend receive field
    private static final String PAYLOAD = "data"; // news json-format data
//    private static final String FROM = "from"; // news from platform

   

    public static Double last_backend_delay = 0.0;

    public static boolean is_backend_task_timeout = false;

	private static HashSet<Integer> forceRecTypeSet = new HashSet<Integer>();
	
	public static Double last_cms_delay = 0.0;
	
	public static long last_cms_news_ts = 0;
	
    private static LinkedBlockingQueue<ConsumeTask> consumeQueue = new LinkedBlockingQueue<ConsumeTask>(10);

    private static ExecutorService mulitTaskConsumer = Executors.newFixedThreadPool(3);
    
    static {
        // start 10 thread to consume
        for (int i = 0; i < 2; ++i) {
            mulitTaskConsumer.execute(new ConsumeLocalQueueForever());
            LOG.info("Start consumer task id:" + i);
        }
    }

    public static int getLocalQueueSize() {
        return consumeQueue.size();
    }

    static class ConsumeLocalQueueForever extends Thread {
        @Override
        public void run() {
            ConsumeTask ct = null;
            while (true) {
                try {
                    // will be blocked when queue is empty
                    ct = consumeQueue.take();
                    LOG.info("[B-01] Get message from mrd queue: size = "+ consumeQueue.size());
                } catch (InterruptedException e1) {
                    LOG.error("[B-01] Error in getting msg from local queue", e1);
                }

                if (ct == null) {
                    // skip failed message
                    continue;
                }

                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<Boolean> future = executor.submit(ct);

                try {
                    // run consume_task and limit it to 600 sec life time
                    if (!future.get(600, TimeUnit.SECONDS)) {
                        LOG.error("[B-01] Consuming error: " + new String(ct.getBytes()));
                    }
                } catch (TimeoutException e) {
                    is_backend_task_timeout = true;
                    LOG.error("[B-02] Consuming timeout: " + new String(ct.getBytes()).replaceAll("\r|\n|\t", "")+e.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                executor.shutdownNow();
            }
        }
    }

	static {
		forceRecTypeSet.add(KUAIXUN); 	// kuaixun
		forceRecTypeSet.add(WEATHER);   // weather
		forceRecTypeSet.add(PROMOTE);   // promote
		forceRecTypeSet.add(STOCKPUSH);//gupiao push
	}
	  class ConsumeTask implements Callable<Boolean> {
	        private byte[] consume_byte = null;

	        public void setBytes(byte[] bytes) {
	            consume_byte = bytes;
	        }

	        public byte[] getBytes() {
	            return consume_byte;
	        }

	        public Boolean call() throws Exception {
	            consumeQueue(consume_byte); // Just to demo a long running task of 4
	                                        // seconds.
	            return true;
	        }

	        private void consumeQueue(byte[] bytes) {
	        
	    		String message = new String(bytes);
	  		  long current_ts = System.currentTimeMillis();
	  		if (message == null || "".equals(message)) {
	  			LOG.error("[F-MRD-01] Message is null or empty!");
	  			return ;
	  		}
//	  		System.out.println("message == "+message);
	  		  try {
	  			   JSONObject json = JSONObject.fromObject(message);
	                long ts = json.getLong(TS);
	                
	                String from = json.getString(FROM);
	                Double kafka_delay = (current_ts - ts) / 60000.0;
	                
	                JSONObject payload = JSONObject.fromObject(json.get(PAYLOAD));
	              
	                MrdNewsObject_Kafka news = (MrdNewsObject_Kafka) JSONObject.toBean(payload, MrdNewsObject_Kafka.class);

	                LOG.info(String.format("[B-02] Get message with kafka t_delay: %.2f min nid=%s", kafka_delay, news.getNid()));

	                if ((current_ts - ts) > 86400 * 1000L) {
	                    LOG.warn(String.format("[B-02] This message is old than one day, delay: %.2f h nid=%s", kafka_delay / 60.0, news.getNid()));
	                    return ;
	                }

	                last_backend_delay = kafka_delay;
	  			
	                if ((current_ts - ts) > 86400 * 1000L) {
	                    LOG.warn(String.format("[B-02] This message is old than one day, delay: %.2f h nid=%s", kafka_delay / 60.0, news.getNid()));
	                    return ;
	                }
//	  			
//	                last_backend_delay = kafka_delay;
	                if(news.getFrom().equals("m.sohu.com")){
	              	  LOG.info("shou sou news not consume nid = "+news.getNid()+" ds = "+news.getDs());
	              	  return ;
	                }
	                // monitor add count
	                // do rest process in front end
	                long t4 =System.currentTimeMillis();
	                long t = t4-current_ts;
//	                LOG.info("backend receive news time nid="+news.getNid()+"=time="+t);
	               // km.hotMediaProcess(news);
	            	long t5 =System.currentTimeMillis();
	            	long t6 = t5 -t4;
	            	LOG.info(" processed news time nid="+news.getNid()+",time="+t6);
	            } catch (Exception e) {
	                LOG.error("[B-03] Message parsing error:" + e.getMessage(), e);
	                e.printStackTrace();
	                System.out.println("Message parsing err");
	            }
	            is_backend_task_timeout = false;
	  		

	  		return ;
	        }
	    }
	  
	    public boolean consume(byte[] bytes) {
	        if (bytes == null)
	            return true;

	        ConsumeTask ct = new ConsumeTask();
	        ct.setBytes(bytes); // set input data

	        try {
	            // read from kafka queue and enqueue to local queue
	            consumeQueue.put(ct);
	        } catch (InterruptedException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	        return true;
	    }
	public JSONObject getJsonData(byte[] bytes){
		JSONObject json = null;
		if (bytes != null) {
			String data = new String(bytes);
			try {
				json = JSONObject.fromObject(data);
				if (json == null || !json.containsKey(SID)
						|| !json.containsKey(ST) || !json.containsKey(OP)
						|| !json.containsKey(DOCID)
						//|| !json.containsKey(MD5)
						|| !json.containsKey(PLATFORM)) {
					json = null;
					LOG.warn("[F-CB] Error format: " + data);
				}
			} catch (Exception e) {
				LOG.warn("数据转换异常:" + data);
				json = null;
			}
		}
		return json;
	}
}
