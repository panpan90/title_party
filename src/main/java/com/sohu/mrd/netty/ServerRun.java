package com.sohu.mrd.netty;
import java.net.InetSocketAddress;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class ServerRun{
   public static final Log  LOG=LogFactory.getLog(ServerRun.class);
   public static void main(String[] args) {
	 int port=8011;
	 HttpServer server=new HttpServer(new InetSocketAddress(port), new GetnewsHandler());
	 try {
		 server.start();
		 LOG.info("netty 服务 启动");
	} catch (Exception e) {
		LOG.info("netty 服务 启动异常 ");
		e.printStackTrace();
	}
  }
}
