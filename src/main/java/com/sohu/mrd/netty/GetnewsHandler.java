package com.sohu.mrd.netty;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sohu.mrd.processor.StatisticsExecute;
import com.sohu.mrd.util.IOUtils;
/**
 * {"data":{"type":"1"},"msg":"成功"}
 * @author Administrator
 */
public class GetnewsHandler implements Handler{
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public String doGet(Map<String, String> map){
		System.out.println("get-----------------------");
		String encodeTitle=map.get("title");
		String msg="成功";
		String decodeTitle="";
		try {
	    encodeTitle=java.net.URLEncoder.encode(encodeTitle ,"UTF-8");
		encodeTitle = encodeTitle.replaceAll("%(?![0-9a-fA-F]{2})", "%25"); 
		 decodeTitle = URLDecoder.decode(encodeTitle, "UTF-8"); 
		 System.out.println("decodeTitle "+decodeTitle);
		} catch (Exception e) {
			System.out.println("111111111111");
			e.printStackTrace();
			msg="失败";
		}
		if("".equals(decodeTitle.trim()))
		{
			msg="标题为空";
		}
		boolean isTitleParty=StatisticsExecute.isTitleParty(decodeTitle);
		String isTitlePartyFlag="";
		if(isTitleParty)
		{
			isTitlePartyFlag="1";
		}else{
			isTitlePartyFlag="0";
		}
		System.out.println("======="+isTitlePartyFlag);
		JSONObject resultJSON=new JSONObject();
		JSONArray dataArray=new JSONArray();
		JSONObject datajson=new  JSONObject(); 
		datajson.put("type", isTitlePartyFlag);
		dataArray.add(datajson);
		resultJSON.put("data", datajson);
		resultJSON.put("msg", msg);
		IOUtils.writeFile("logs/titlePatry", sdf.format(new Date())+decodeTitle);
		IOUtils.writeFile("logs/normalPatry", sdf.format(new Date())+decodeTitle);
		return resultJSON.toJSONString();
	}
	public String doPost(Map<String, String> map, String content) {
		// TODO Auto-generated method stub
		return "";
	}
}
