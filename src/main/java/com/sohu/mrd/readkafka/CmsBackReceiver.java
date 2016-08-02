package com.sohu.mrd.readkafka;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Function;
import com.sohu.mrd.processor.StatisticsExecute;
import com.sohu.mrd.statistics.Statistic;
import com.sohu.mrd.util.IOUtils;
import com.sohu.smc.common.kafka.Kafka;
/**
 * 接收并处理CMS后台推送到消息队列的新闻
 * @author chencheng
 */
public class CmsBackReceiver {
    private static final Log LOG = LogFactory.getLog(CmsBackReceiver.class);
    private static CmsBackConsumer consumer = null;
    private static Properties properties = PropertyUtil.getPropertiesFromClassResFile("kafka8.properties");
    private static MetricRegistry registry = new MetricRegistry();
    private static final Kafka kafka = new Kafka(properties, registry);
    public static class ConsumeTask extends Thread {
        @Override
        public void run() {
        		 consumer = new CmsBackConsumer();  
                // final String path=CmsBackReceiver.class.getClassLoader().getResource("").getPath();
                 final String path=System.getProperty("user.dir")+"//svm_files";
                 System.out.println("Start to consume mrd_receive_topic");
                 LOG.info("Start to consume mrd_receive_topic");
                 kafka.consumeForever("mrd.allnews.all_realtime_news", "mrd_hotNewsVia_group", 3, new Function<byte[], Boolean>()  {
                     public Boolean apply(byte[] input) {
                         LOG.info(" receive  all news message:" ); //lxj,2015-04-02,input.toString()
                         String news=new String(input);
                         JSONObject newJson=JSON.parseObject(news);
                         String data=newJson.getString("data");
                         JSONObject  json=JSON.parseObject(data);
                         String title=json.getString("t");
                         LOG.info("title "+title);
                        boolean isTitleParty=StatisticsExecute.isTitleParty(title);
                        if(isTitleParty)
                        {
                        	IOUtils.writeFile(path+"//kafkaTitlePary", title+"\n");
                        }else
                        {
                        	IOUtils.writeFile(path+"//kafkanormalPary", title+"\n");
                        }
                         StringBuilder sb=new StringBuilder();
                         sb.append(title);
                         sb.append("\n");
                         IOUtils.writeFile(path+"//normalTiles", sb.toString());
                         return true;
                     }
                 });
        }
    }
    public static void start() {
        ConsumeTask consumeTask = new ConsumeTask();
        consumeTask.start();
    }
    public static void main(String[] args) {
        CmsBackReceiver.start();
    }
}
