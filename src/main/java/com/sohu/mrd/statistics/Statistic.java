package com.sohu.mrd.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.mrd.model.JudgeInfo;
import com.sohu.mrd.splitword.SplitWord;

public class Statistic {
	public static final Log LOG=LogFactory.getLog(Statistic.class);
	public static Map<String, Double>  getWeightMap(Map<String,Integer> map)
	{
		int sum=0;
		Set<String> key=map.keySet();
		Map<String,Double> statiscMap=new HashMap<String,Double>();
		Iterator<String> it=key.iterator();
		while(it.hasNext())
		{
			String word=it.next();
			Integer fre=map.get(word);
			if(fre>=4)
			{
				sum+=fre;
			}
		}
		Iterator<String> it1=key.iterator();
		while(it1.hasNext())
		{
			String word=it1.next();
			Integer fre=map.get(word);
			if(fre>=4)
			{
				statiscMap.put(word, (double)fre/sum);
			}
			
		}
		return statiscMap;
	}
	
	public static JudgeInfo  Judge(String title,Map<String,Double> statiscMap)
	{
		ArrayList<String> words=SplitWord.sigleTitleAnalysis(title);
		int wordTimes=0;
		double weightScore=0;;
		for(int i=0;i<words.size();i++)
		{
			if(statiscMap.containsKey(words.get(i)))
			{
				wordTimes++;
				weightScore+=statiscMap.get(words.get(i));
			}
		}
		JudgeInfo judgeInfo=new JudgeInfo();
		judgeInfo.setWeightScore(weightScore);
		judgeInfo.setWordTimes(wordTimes);
		judgeInfo.setAppearRadio((double)wordTimes/words.size());
		return judgeInfo;
	}
	
}
