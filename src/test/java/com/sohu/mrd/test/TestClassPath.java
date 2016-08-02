package com.sohu.mrd.test;
public class TestClassPath {
	public static void main(String[] args) {
		System.out.println(TestClassPath.class.getClassLoader().getResource("titleIds").getPath());
		//System.out.println(TestClassPath.class.getClassLoader().getResource("/").getPath());
		//System.out.println(TestClassPath.class.getResource("/").getPath());
		String relativelyPath=System.getProperty("user.dir");
		System.out.println("relativelyPath "+relativelyPath);
	}
}
