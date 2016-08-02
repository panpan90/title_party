package com.sohu.mrd.util;
public class MathUtils {
	/**
	 * 判断一个字符串是否是数字
	 */
	public static boolean isNumber(String str)
	{
		boolean flag=true;
		for(int i=0;i<str.length();i++)
		{
			char c=str.charAt(i);
			if(!Character.isDigit(c))
			{
				flag=false;
			}
		}
		return flag;
	}
	
	public static void main(String[] args) {
		System.out.println(isNumber("1&23"));
		
	}
}
