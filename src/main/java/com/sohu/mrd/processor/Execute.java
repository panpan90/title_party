package com.sohu.mrd.processor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.mrd.generateSVMFile.GenerateSVMFile;
import com.sohu.mrd.getTitle.GetTitle;
import com.sohu.mrd.model.TermInfo;
import com.sohu.mrd.splitword.SplitWord;
import com.sohu.mrd.svm.SVMHandle;
import com.sohu.mrd.util.IOUtils;
import com.sohu.mrd.util.S2InputStream;
public class Execute {
	public static final Log LOG=LogFactory.getLog(Execute.class);
	public static void main(String[] args) {
       String title1="震惊惊呆!!!!震惊!";
//       ArrayList<String> titles=SplitWord.sigleTitleAnalysis(title);
//       System.out.println(titles.toString());
       
//       String modelPath="svm_files/train_model";
//       String titlePartyPath="svm_files/titles";
    // executePredict(title,modelPath,titlePartyPath);
		TermInfo  partyTermInfo=SplitWord.getTitleParyWords("svm_files/titles");
//		GenerateSVMFile.converSvmFileByFre("svm_file_top/patrySVMFile", partyTermInfo, 0);
//		TermInfo  normalTermInfo=SplitWord.getTitleParyWords("svm_files/normalTiles");
//		GenerateSVMFile.converSvmFileByFre("svm_file_top/normalSVMFile", normalTermInfo, 1);
		//SVMHandle.gernerateMode("svm_file_top/svmFile", "svm_file_top/model");
//	ArrayList<String> partyTitles=IOUtils.readFile2List("svm_files/titles");
//	ArrayList<String> normalTitles=IOUtils.readFile2List("svm_files/normalTiles");
//	for(int i=0;i<normalTitles.size();i++)
//	{
//		String title=partyTitles.get(i);
//		executePredictByFre(title,"svm_file_top/model",partyTermInfo);
//	}
	String title22="震惊惊呆!!!!震惊!";
	executePredictByFre(title22,"svm_file_top/model",partyTermInfo);
		//SVMHandle.crossValidation("svm_file_top/svmFile", 10);
	}
	/**
	 * 按照词出现的比例作为预测的特征
	 * @param title
	 * @param modelPath
	 * @param titlePartyPath
	 */
	public  static  void  executePredictByRatio(String title,String modelPath,String titlePartyPath)
	{
		TermInfo  termInfo=SplitWord.getTitleParyWords(titlePartyPath);
		HashMap<String,Integer>  titleMap=termInfo.getTitle();
		int allCount=termInfo.getAllSplitTermCount();
		ArrayList<String> titles=SplitWord.sigleTitleAnalysis(title);
		StringBuilder sb=new StringBuilder();
		sb.append("1");
		sb.append(" ");
		for(int i=0;i<titles.size();i++)
		{
			sb.append(i+"");
			String termName=titles.get(i);
			double fre=0.0;
			if(titleMap.containsKey(termName))
			{
				int time=titleMap.get(termName);
				System.out.println(termName+":"+time);
				fre=(double)(time+1)/(allCount+1);
				System.out.println(allCount);
			}else{
				fre=(double)1/(allCount+1);
			}
			sb.append(":");
			sb.append(fre);
			sb.append(" ");
		}
	    String predictTitle=sb.toString();
	    System.out.println("predictTitle  "+predictTitle);
		InputStream  is=S2InputStream.Str2Inputstr(predictTitle);
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		try {
			String predictResult=SVMHandle.customPredict(br, modelPath);
			System.out.println(predictResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 按照词出现的次数作为预测的特征
	 * @param title
	 * @param modelPath
	 * @param termInfo
	 */
	public  static  void  executePredictByFre(String title,String modelPath,TermInfo termInfo)
	{
		HashMap<String,Integer>  titleMap=termInfo.getTitle();
		ArrayList<String> splitWord=SplitWord.sigleTitleAnalysis(title);
	    StringBuilder predictSb=new StringBuilder();
	    int autoIncr=1;
	    predictSb.append("0");
	    for(int i=0;i<splitWord.size();i++)
	    {
	    	String word=splitWord.get(i);
	    	int fre=0;
	    	if(titleMap.containsKey(word))
	    	{
	    		 fre=titleMap.get(word);
	    	}
	    	predictSb.append(" ");
	    	predictSb.append(autoIncr++);
	    	predictSb.append(":");
	    	predictSb.append(fre+1);
	    }
	    String predictTitle=predictSb.toString();
	    LOG.info("predictTitle "+predictTitle.toString());
		InputStream  is=S2InputStream.Str2Inputstr(predictTitle);
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		try {
			String predictResult=SVMHandle.customPredict(br, modelPath);
			System.out.println(predictResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
