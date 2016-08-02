package com.sohu.mrd.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SortMap {  
    public static void main(String[] args) throws Exception {  
        // TODO code application logic here  
        Map<String, Integer> myMap = new LinkedHashMap();  
        myMap.put("1", 1);  
        myMap.put("2", 4);  
        myMap.put("3", 3);  
        myMap.put("4", 9);  
        myMap.put("5", 6);  
        myMap.put("6", 2);  
          
        printMap(myMap);  
          
        myMap = sortMap(myMap);  
          
        printMap(myMap);  
    }  
      
    private static void printMap(Map map){  
        System.out.println("===================mapStart==================");  
        Iterator it = map.entrySet().iterator();  
        while(it.hasNext()){  
            Map.Entry entry = (Map.Entry) it.next();  
            System.out.println(entry.getKey() + ":" + entry.getValue());  
        }  
        System.out.println("===================mapEnd==================");  
    }   
    public static Map sortMap(Map oldMap) {  
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
        Collections.sort(list, new CustomerComparator());
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
//            public int compare(Entry<java.lang.String, Integer> arg0,  
//                    Entry<java.lang.String, Integer> arg1) {  
//                return arg0.getValue() - arg1.getValue();  
//            }  
//        });  
        Map<String,Integer> newMap = new LinkedHashMap<String,Integer>();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    }  
    static class CustomerComparator implements Comparator<Map.Entry<String, Integer>>
    {

		@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			// TODO Auto-generated method stub
			return 0;
		}

//		@Override
//		public int compare(Object o1, Object o2) {
//			// TODO Auto-generated method stub
//			return 0;
//		}

//		@Override
//		public int compare(Entry<String, Integer> entry0, Entry<String, Integer> entry1) {
//			 
//			
//			return entry0.getValue().compareTo(entry1.getValue());
//		}
    	
    }
}  