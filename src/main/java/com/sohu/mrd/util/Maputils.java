package com.sohu.mrd.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * map根据value进行自定义排序
 * @author jinguopan
 *
 */
public class Maputils {
	public static final Log LOG=LogFactory.getLog(Maputils.class);
	public static Map<String,Integer>  sortMapByValue(Map<String,Integer> map)
	{
		if(null==map)
		{
			LOG.info("map 为 空");
			System.exit(-1);
		}
		List<Map.Entry<String,Integer>> list=new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
		Collections.sort(list, new CustomerComparator());
		
		Map<String, Integer>  linkedHashMap=new LinkedHashMap<String,Integer>(); 
		
		for(int i=0;i<list.size();i++)
		{
			linkedHashMap.put(list.get(i).getKey(), list.get(i).getValue());
		}
		
		return linkedHashMap;
	}
	
	static class CustomerComparator implements Comparator<Map.Entry<String,Integer>>{
		@Override
		public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
			// TODO Auto-generated method stub
			return -1*entry1.getValue().compareTo(entry2.getValue());
		}
		
	}
	
	public static <K, V> void printMap2Console(Map<String,Integer> map)
	{
		Set<String> keySet=map.keySet();
		Iterator<String>  it=keySet.iterator();
		while(it.hasNext())
		{
			String key=it.next();
			Integer value=map.get(key);
			System.out.println(key+":"+value);
		}
	}
	
	public static <K, V> void printMap2ConsoleDouble(Map<String,Double> map)
	{
		Set<String> keySet=map.keySet();
		Iterator<String>  it=keySet.iterator();
		while(it.hasNext())
		{
			String key=it.next();
			Double value=map.get(key);
			System.out.println(key+":"+value);
		}
	}
	
	public static void writeMap2File(Map<String,Integer> map,String filePath)
	{
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(filePath);
			Set<String> keySet=map.keySet();
			Iterator<String>  it=keySet.iterator();
			StringBuilder sb=new StringBuilder();
			while(it.hasNext())
			{
				String key=it.next();
				Integer value=map.get(key);
				sb.append(key);
				sb.append(":");
				sb.append(value);
				sb.append("\n");
				fos.write(sb.toString().getBytes());
				sb.delete(0, sb.length());
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}finally{
			  try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
