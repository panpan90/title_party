package com.sohu.mrd.test;

public class TestNumber {

	public static void main(String[] args) {
		String s="0.001027221366204417";
		String ss="5.136106831022085E-4";
		System.out.println(atof(ss));
		
	}
	
	private static double atof(String s)
	{
		double d = Double.valueOf(s).doubleValue();
		if (Double.isNaN(d) || Double.isInfinite(d))
		{
			System.err.print("NaN or Infinity in input\n");
			System.exit(1);
		}
		return(d);
	}
}
