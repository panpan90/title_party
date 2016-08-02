package com.sohu.mrd.test;

import net.sf.json.JSONArray;

import com.sohu.mrd.util.HttpClientUtil;

public class TestHttp {
	public static final String  NEWSINTER="http://10.10.93.179:8080/news/newsprofile?act=getdata&docid=7f59953d4aa462ca-676f857a2444d6f0";
    public static void main(String[] args) {
    	String news=HttpClientUtil.executeGet(NEWSINTER);
    	net.sf.json.JSONObject  newsJson=net.sf.json.JSONObject.fromObject(news);
    	JSONArray jsonArray=newsJson.getJSONArray("data");
    	net.sf.json.JSONObject myJSONObject = net.sf.json.JSONObject.fromObject(jsonArray.getString(0));
    	String title=myJSONObject.getString("t");
    	System.out.println(title);

    	
	}
	
}
