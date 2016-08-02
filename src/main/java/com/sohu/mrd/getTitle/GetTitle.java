package com.sohu.mrd.getTitle;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.HanLP;
import com.sohu.mrd.util.HttpClientUtil;
import com.sohu.mrd.util.IOUtils;
import com.sohu.mrd.util.KillTag;
public class GetTitle {
	public static final String  DOCITER="http://service1.mrd.sohuno.com/recomoperator/detail?type=idtrans&from=k.sohu.com&cmsid=";
	public static final String  NEWSINTER="http://10.10.93.179:8080/news/newsprofile?act=getdata&docid=";
	public static final Log LOG=LogFactory.getLog(GetTitle.class);
	/**
	 * 得到标题党标题
	 * @return
	 */
	public static ArrayList<String> getTitleParty(){
	    ArrayList<String>  titles=new ArrayList<String>();
	    titles.add("0");//初始化标题类型 0 表示标题党
	    String path=GetTitle.class.getClassLoader().getResource("titleIds").getPath();
		ArrayList<String> ids=IOUtils.readFile2List(path);
		for(int i=0;i<ids.size();i++)
		{
			String content=HttpClientUtil.executeGet(DOCITER+ids.get(i));
			JSONObject  json=JSON.parseObject(content);
			String data=json.getString("data");
			if(!"[\"\"]".equals(data.trim()))
			{
				//JSON 中  带[] 表示json数组，要用 JSONArray 来接受 ，get(0),接着转化为 JSONobject
				//因为[] 是正则表达式中的特殊字符，所以需要用转义字符，又因为\是转义字符，所以用\\
				//()表示正则中的一组
				String docId=data.replaceAll("(\\[\")|(\"\\])", "");
				String news=HttpClientUtil.executeGet(NEWSINTER+docId);
				JSONObject  newsJson=JSON.parseObject(news);
				LOG.info(news);
				JSONArray jsonArray=newsJson.getJSONArray("data");
				JSONObject myJSONObject = JSON.parseObject(jsonArray.getString(0));
			    String articleContent=KillTag.killTags(myJSONObject.getString("co"));
			    String title=myJSONObject.getString("t");
				titles.add(title);
			}
		}
		return titles;
	}
	//获得正常标题
	public static ArrayList<String> getNormalTitles(String path)
	{
		ArrayList<String>  normalTitles=IOUtils.readFile2List("svm_files/normalTiles");
		normalTitles.add(0, "1"); //初始化正常标题类别号
		return normalTitles;
	}
	/**
	 * 标题党标题和内容写入文件中
	 * @return
	 */
	public static void  getTitleContents(String  filePath){
		FileOutputStream   fos=null;
	    String   titleIdPath=GetTitle.class.getClassLoader().getResource("titleIds").getPath();
		ArrayList<String> ids=IOUtils.readFile2List(titleIdPath);
	    try {
	    	fos=new FileOutputStream(filePath);
			for(int i=0;i<ids.size();i++)
			{
				String content=HttpClientUtil.executeGet(DOCITER+ids.get(i));
				JSONObject  json=JSON.parseObject(content);
				String data=json.getString("data");
				StringBuilder sb=new StringBuilder();
				if(!"[\"\"]".equals(data.trim()))
				{
					//JSON 中  带[] 表示json数组，要用 JSONArray 来接受 ，get(0),接着转化为 JSONobject
					//因为[] 是正则表达式中的特殊字符，所以需要用转义字符，又因为\是转义字符，所以用\\
					//()表示正则中的一组
					String docId=data.replaceAll("(\\[\")|(\"\\])", "");
					String news=HttpClientUtil.executeGet(NEWSINTER+docId);
					JSONObject  newsJson=JSON.parseObject(news);
					JSONArray jsonArray=newsJson.getJSONArray("data");
					JSONObject myJSONObject = JSON.parseObject(jsonArray.getString(0));
				    String articleContent=KillTag.killTags(myJSONObject.getString("co"));
				    String title=myJSONObject.getString("t");
				    sb.append(title);
				    sb.append("\n");
				    sb.append(articleContent);
				    sb.append("\n");
				   // LOG.info("title "+title);
				   // LOG.info("articleContent "+articleContent);
				    fos.write(sb.toString().getBytes());
				    sb.delete(0, sb.length());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * 标题党标题和内容写入文件中
	 * @return
	 */
	public static void  getTitleskeyword(String  filePath){
		FileOutputStream   fos=null;
	    String   titleIdPath=GetTitle.class.getClassLoader().getResource("titleIds").getPath();
		ArrayList<String> ids=IOUtils.readFile2List(titleIdPath);
	    try {
	    	fos=new FileOutputStream(filePath);
			for(int i=0;i<ids.size();i++)
			{
				String content=HttpClientUtil.executeGet(DOCITER+ids.get(i));
				JSONObject  json=JSON.parseObject(content);
				String data=json.getString("data");
				StringBuilder sb=new StringBuilder();
				if(!"[\"\"]".equals(data.trim()))
				{
					//JSON 中  带[] 表示json数组，要用 JSONArray 来接受 ，get(0),接着转化为 JSONobject
					//因为[] 是正则表达式中的特殊字符，所以需要用转义字符，又因为\是转义字符，所以用\\
					//()表示正则中的一组
					String docId=data.replaceAll("(\\[\")|(\"\\])", "");
					String news=HttpClientUtil.executeGet(NEWSINTER+docId);
					JSONObject  newsJson=JSON.parseObject(news);
					JSONArray jsonArray=newsJson.getJSONArray("data");
					JSONObject myJSONObject = JSON.parseObject(jsonArray.getString(0));
				    String articleContent=KillTag.killTags(myJSONObject.getString("co"));
				    String title=myJSONObject.getString("t");
				    sb.append(title);
				    sb.append("\n");
				    List<String> keywordList = HanLP.extractKeyword(articleContent, 20);
				    sb.append(keywordList.toString());
				    sb.append("\n");
				   // LOG.info("title "+title);
				   // LOG.info("articleContent "+articleContent);
				    fos.write(sb.toString().getBytes());
				    sb.delete(0, sb.length());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * topN
	 * @param filePath
	 */
	public static void  getTopNTitleskeyword(String  filePath){
		FileOutputStream   fos=null;
		ArrayList<String> ids=IOUtils.readTopNFile2List("topN/top_10000_5_7");
	    LOG.info("ids "+ids.toString());
	    try {
	    	fos=new FileOutputStream(filePath);
			for(int i=0;i<ids.size();i++)
			{
				String content=HttpClientUtil.executeGet(DOCITER+ids.get(i).trim());
				LOG.debug("ids.get(i).trim() "+ids.get(i).trim());
				LOG.debug("DOCITER+ids.get(i).trim() "+DOCITER+ids.get(i).trim());
				//LOG.info("content "+content);
				if(content!=null&&!"".equals(content)&&content.contains("data"))
				{
					JSONObject  json=JSON.parseObject(content);
					String data=json.getString("data");
					//LOG.info("cms_data"+data);
					StringBuilder sb=new StringBuilder();
					if(!"[\"\"]".equals(data.trim()))
					{
						//JSON 中  带[] 表示json数组，要用 JSONArray 来接受 ，get(0),接着转化为 JSONobject
						//因为[] 是正则表达式中的特殊字符，所以需要用转义字符，又因为\是转义字符，所以用\\
						//()表示正则中的一组
						String docId=data.replaceAll("(\\[\")|(\"\\])", "");
						LOG.info("docId  "+docId);
						String news=HttpClientUtil.executeGet(NEWSINTER+docId);
						if(news!=null&&!"".equals(news.trim())&&news.contains("data"))
						{
							
					    	net.sf.json.JSONObject  newsJson=net.sf.json.JSONObject.fromObject(news);
					    	//LOG.info("newsJson "+newsJson);
					    	//LOG.info("newsJson.getString "+newsJson.getString("data"));
					    	if(!"".equals(newsJson.getString("data")))
					    	{
					    		net.sf.json.JSONArray jsonArray=newsJson.getJSONArray("data");
						    	net.sf.json.JSONObject myJSONObject = net.sf.json.JSONObject.fromObject(jsonArray.getString(0));
						    	String title=myJSONObject.getString("t");
						    	sb.append(title);
							    sb.append("\n");
							    fos.write(sb.toString().getBytes());
							    sb.delete(0, sb.length());
					    	}
					    	
						}
					}
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public static void main(String[] args) {
		//getTopNTitleskeyword("topN/titles_5_7");
		getTitleParty();
	}
	
}
