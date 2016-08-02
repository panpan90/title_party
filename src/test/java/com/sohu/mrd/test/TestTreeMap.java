package com.sohu.mrd.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class TestTreeMap {
	public static void main(String[] args){
		TreeMap<String,Integer>  treeMap=new TreeMap<String,Integer>();
		treeMap.put("我的", 1);
		treeMap.put("你的", 6);
		treeMap.put("他的", 2);
		Map<String,Integer> linkeHashMap=sortMapByValue(treeMap);
		printMap(linkeHashMap);
	}
	
	public static  void printMap(Map<String,Integer> map)
	{
		Set<String> keySet=map.keySet();
		Iterator<String> it=keySet.iterator();
		while(it.hasNext())
		{
			String key=it.next();
			System.out.println("key "+key);
			System.out.println("value "+map.get(key));
		}
	}
	
	public static Map<String,Integer> sortMapByValue(Map<String,Integer> map)
	{
		//HashMap是无序的，LinkedHashMap是有序的
		Map<String, Integer>  linkedHashMap=new LinkedHashMap<String,Integer>();
		List<Entry<String,Integer>> list=new ArrayList<Entry<String,Integer>>(map.entrySet());
		Collections.sort(list, new CustomerComparator());
		for(int i=0;i<list.size();i++)
		{
			linkedHashMap.put(list.get(i).getKey(), list.get(i).getValue());
		}
		return linkedHashMap;
	}
	
	static  class CustomerComparator  implements Comparator<Map.Entry<String, Integer>>{

		public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
			return entry1.getValue().compareTo(entry2.getValue());
		}
		
		
	}

}
   

