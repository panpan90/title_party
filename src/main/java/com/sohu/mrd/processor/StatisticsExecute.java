package com.sohu.mrd.processor;
import java.util.ArrayList;
import java.util.Map;

import com.sohu.mrd.model.JudgeInfo;
import com.sohu.mrd.model.TermInfo;
import com.sohu.mrd.splitword.SplitWord;
import com.sohu.mrd.statistics.Statistic;
import com.sohu.mrd.util.IOUtils;
import com.sohu.mrd.util.Maputils;
import com.sohu.mrd.util.Test;
public class StatisticsExecute {
	public static void main(String[] args) {
		isTitleParty("震惊竟然是!");
	}
	public static void isTitlePartyTest() {
		double thresholdWeight=0.040284979124027544;
		double thresholdAppearRadio=0.11588372550692243;
		TermInfo  partyTermInfo=SplitWord.getTitleParyWords("svm_files/titles");
 		Map<String, Double> map=Statistic.getWeightMap(partyTermInfo.getTitle());
 		ArrayList<String> partyTitles=IOUtils.readFile2List("svm_files/titles");
 		ArrayList<String> normalTitles=IOUtils.readFile2List("svm_files/normalTiles");
 		ArrayList<String> preidictTitles=IOUtils.readFile2List("topN/top_10000_5_7");
 		int m=0;
 		int n=0;
 		for(int i=0;i<preidictTitles.size();i++)
 		{
 			String title=preidictTitles.get(i);
 			JudgeInfo judgeInfo=Statistic.Judge(title, map);
 			if(judgeInfo.getWeightScore()>=thresholdWeight&&judgeInfo.getAppearRadio()>=thresholdAppearRadio)
 			{
 				System.out.println("是标题党");
 			}else{
 				System.out.println("不是标题党");
 			}
 		}
 		System.out.println("标题党 "+m);
 		System.out.println("非标题党 "+n);
	}
	
	public static boolean  isTitleParty(String title)
	{
		boolean flag=false;
		double thresholdWeight=0.040284979124027544;
		double thresholdAppearRadio=0.11588372550692243;
		String classPath=Test.class.getClassLoader().getResource("/").getPath();
		System.out.println("classPath "+classPath);
		String titlePartyPath=StatisticsExecute.class.getClassLoader().getResource("svm_files/titles").getPath();
		System.out.println(titlePartyPath+" titlePartyPath");
		TermInfo  partyTermInfo=SplitWord.getTitleParyWords(titlePartyPath);
 		Map<String, Double> map=Statistic.getWeightMap(partyTermInfo.getTitle());
		JudgeInfo judgeInfo=Statistic.Judge(title, map);
			if(judgeInfo.getWeightScore()>=thresholdWeight&&judgeInfo.getAppearRadio()>=thresholdAppearRadio)
			{
				flag=true;
			}else{
				flag=false;
			}
			
			return flag;
	}
	public static void test() {
		TermInfo  partyTermInfo=SplitWord.getTitleParyWords("svm_files/titles");
 		Map<String, Double> map=Statistic.getWeightMap(partyTermInfo.getTitle());
 		ArrayList<String> partyTitles=IOUtils.readFile2List("svm_files/titles");
 		ArrayList<String> normalTitles=IOUtils.readFile2List("svm_files/normalTiles");
 		double partyWeighCount=0;
 		double partyWordRadioCount=0;
 		for(int i=0;i<partyTitles.size();i++)
 		{
 			String title=partyTitles.get(i);
 			JudgeInfo judgeInfo=Statistic.Judge(title, map);
 			partyWeighCount+=judgeInfo.getWeightScore();
 			partyWordRadioCount+=judgeInfo.getAppearRadio();
 			//System.out.println(judgeInfo.getWeightScore()+"\t"+judgeInfo.getWordTimes()+"\t"+judgeInfo.getAppearRadio());
 		}
 		double avgPartyWeigh=partyWeighCount/partyTitles.size();
 		double avgPartyRadio=partyWordRadioCount/partyTitles.size();
 		System.out.println("标题党"+partyWeighCount/partyTitles.size()+"\t"+partyWordRadioCount/partyTitles.size());
 		
 		System.out.println("-----------------------以下是非标题党-------------------------");
 		double normalWeighCount=0;
 		double normalWordRadioCount=0;
 		for(int i=0;i<normalTitles.size();i++)
 		{
 			String title=normalTitles.get(i);
 			JudgeInfo judgeInfo=Statistic.Judge(title, map);
 			normalWeighCount+=judgeInfo.getWeightScore();
 			normalWordRadioCount+=judgeInfo.getAppearRadio();
 		}
 		System.out.println("非标题党"+normalWeighCount/partyTitles.size()+"\t"+normalWordRadioCount/partyTitles.size());
 		double avgNormalWeigh=normalWeighCount/partyTitles.size();
 		double avgNormalRadio=normalWordRadioCount/partyTitles.size();
 		System.out.println("阈值 "+Math.sqrt(avgPartyWeigh*avgNormalWeigh)+"\t"+Math.sqrt(avgPartyRadio*avgNormalRadio));
	}
}
