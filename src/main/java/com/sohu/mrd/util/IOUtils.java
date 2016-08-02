package com.sohu.mrd.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IOUtils {
	public static final Log LOG=LogFactory.getLog(IOUtils.class);
	public static  void readFile(String path,String filePath)
	{
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try {
			 fis=new FileInputStream(path);
			 fos=new FileOutputStream(filePath);
			BufferedReader  br=new BufferedReader(new InputStreamReader(fis));
			String temp="";
			while((temp=br.readLine())!=null)
			{
			     fos.write(temp.getBytes());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static  ArrayList<String>  readFile2List(String path)
	{
		FileInputStream fis=null;
		ArrayList<String> ids=new ArrayList<String>();
		try {
			 fis=new FileInputStream(path);
			BufferedReader  br=new BufferedReader(new InputStreamReader(fis));
			String temp="";
			while((temp=br.readLine())!=null)
			{
				if(!"".equals(temp.trim()))
				{
					ids.add(temp.trim());  
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ids;
	}
	
	public static  ArrayList<String>  readTopNFile2List(String path)
	{
		FileInputStream fis=null;
		ArrayList<String> ids=new ArrayList<String>();
		try {
			 fis=new FileInputStream(path);
			BufferedReader  br=new BufferedReader(new InputStreamReader(fis));
			String temp="";
			while((temp=br.readLine())!=null)
			{
				String[] tempArray=temp.split("\t", -1);
				if(tempArray.length>=2)
				{
					ids.add(tempArray[0].trim());  
					//System.out.println("tempArray[0].trim() "+tempArray[0].trim());
					//System.out.println("tempArray[1].trim() "+tempArray[1].trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ids;
	}
	
	public static void writeFile(String path,String content)
	{
		
		FileOutputStream fos=null;
		File file=new File(path);
		try {
			if(!file.exists())
			{
				LOG.debug("文件不存在，创建新文件");
				file.createNewFile();
			}
				fos=new FileOutputStream(file,true);
				fos.write(content.getBytes());
				LOG.debug("已经写入的东西 "+content);
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
	
	public static void main(String[] args) {
//		String path=IOUtils.class.getClassLoader().getResource("").getPath();
//		writeFile("test22","test.test.test.test");
		readTopNFile2List("topN/top_10000_5_7");
	}
}
