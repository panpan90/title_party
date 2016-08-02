package com.sohu.mrd.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sohu.mrd.model.TermInfo;
import com.sohu.mrd.splitword.SplitWord;
import com.sohu.mrd.util.IOUtils;
import com.sohu.mrd.util.Maputils;

public class HotTermBySort {

	public static void main(String[] args) {
		TermInfo  partyTermInfo=SplitWord.getTitleParyWords("svm_files/titles");
		TermInfo  normalTermInfo=SplitWord.getTitleParyWords("svm_files/normalTiles");
		Map<String,Integer> partyTitlesMap=partyTermInfo.getTitle();
		Map<String,Integer> normalTitleMap=normalTermInfo.getTitle();
		Map<String,Integer> sortPartyMap=Maputils.sortMapByValue(partyTitlesMap);
		Map<String,Integer> sortNormalMap=Maputils.sortMapByValue(normalTitleMap);
		Maputils.writeMap2File(sortPartyMap, "svm_file_top/sortPartyTitles");
		Maputils.writeMap2File(sortNormalMap, "svm_file_top/sortNormalTitles");
		Maputils.printMap2Console(sortPartyMap);
		Maputils.printMap2Console(sortNormalMap);
		/**
		 * 获取从集群上的topN 并写到本地
		 */
//		ArrayList<String> titles=IOUtils.readFile2List("topN/titles_5_7");
//		TermInfo termInfo=SplitWord.getSplitWrodFre(titles);
//		HashMap<String, Integer> titleMap=termInfo.getTitle();
//		Map<String, Integer> sortMap=Maputils.sortMapByValue(titleMap);
//		Maputils.writeMap2File(sortMap, "svm_file_top/hot_top_n");
		//Maputils.printMap2Console(sortMap);
	}
	
}
