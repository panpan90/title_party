package com.sohu.mrd.generateSVMFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sohu.mrd.model.TermInfo;
import com.sohu.mrd.splitword.SplitWord;
import com.sohu.mrd.util.IOUtils;
import com.sohu.mrd.util.Maputils;

public class GenerateSVMFile {
	/**
	 * 把标题转化为libsvm能识别的样式 按照比例的方式
	 * @param path
	 * @param termInfo
	 */
	public static void converSvmFile(String path,TermInfo termInfo)
	{
		FileOutputStream fos=null;
		int autoIncr=1;
		try {
			fos=new FileOutputStream(path,true);
			HashMap<String, Integer>  termMap=termInfo.getTitle();
			int countTimes=termInfo.getAllSplitTermCount();
			int  category=termInfo.getCategory();
			Set<String> keySet=termMap.keySet();
			Iterator<String> it=keySet.iterator();
			StringBuilder categorySb=new StringBuilder();
			categorySb.append(category);
			categorySb.append(" ");
			fos.write(categorySb.toString().getBytes());
			int count=0;
			while(it.hasNext())
			{  
				count++;
				StringBuilder sb=new StringBuilder();
				String word=it.next();
				int wordFre=termMap.get(word);
				//LOG.info(" 过滤停用词之后的结果 word "+word+":"+wordFre+"\n");
				double  ratioWordFre=(double)wordFre/(double)countTimes;
				if(count==30)
				 {
					sb.append("\n");
					sb.append(category+" ");
				 } 
				sb.append(autoIncr++);
				sb.append(":");
				sb.append(ratioWordFre);
				sb.append(" ");
				fos.write(sb.toString().getBytes());
				sb.delete(0, sb.length());
				if(count==30)
				{
					count=0;
				}
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
	/**
	 * 把标题转化为libsvm 可以输入的格式 ，按照每个词出现的频率,其中category为样本的类型
	 * @param svmFile
	 * @param termInfo
	 * @param category
	 * libsvm 识别的类型 0 1:6.464124111182935E-4 2:6.464124111182935E-4
	 */
	public static void converSvmFileByFre(String svmFile, TermInfo termInfo,int category)
	{
		FileOutputStream fos=null;
		int autoIncr=1;
		try {
			fos=new FileOutputStream(svmFile,true);
			HashMap<String, Integer> hashMap=termInfo.getTitle();
			Map<String, Integer>  sortMap=Maputils.sortMapByValue(hashMap);
			StringBuilder svmSb=new StringBuilder();
			Set<String> keySet=sortMap.keySet();
			Iterator<String> it=keySet.iterator();
			svmSb.append(category);
			svmSb.append(" ");
			int count=0;
			while(it.hasNext())
			{
				String word=it.next();
				int fre=sortMap.get(word);
				svmSb.append(autoIncr++);
				svmSb.append(":");
				svmSb.append(fre);
				svmSb.append(" ");
				if(++count%20==0)
				{
					svmSb.append("\n");
					svmSb.append(category+" ");
				}
				fos.write(svmSb.toString().getBytes());
				svmSb.delete(0, svmSb.length());
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
