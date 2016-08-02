package com.sohu.mrd.util;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		String path=Test.class.getClassLoader().getResource("").getPath();
		System.out.println(path);
//		ArrayList<String> list=IOUtils.readFile2List(path);
//		System.out.println(list.toString());
//		ArrayList<String>  al=IOUtils.readFile2List("titles");
//		System.out.println(al.toString());
	}
}
