package com.sohu.mrd.test;

import java.util.ArrayList;

import com.sohu.mrd.util.IOUtils;

public class Test_StopWord {

	public static void main(String[] args) {
		  ArrayList<String>  splitWords=IOUtils.readFile2List("svm_files/stopword.txt");
		  System.out.println(splitWords.toString());

	}
}
