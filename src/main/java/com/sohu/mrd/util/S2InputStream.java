package com.sohu.mrd.util;
import java.io.InputStream;
import java.io.StringBufferInputStream;
public class S2InputStream{
	 /** 
	    * 利用ByteArrayInputStream：String------------------>InputStream <功能详细描述> 
	    *  
	    * @param inStr 
	    * @return 
	    * @see [类、类#方法、类#成员] 
	    */  
	   public static InputStream Str2Inputstr(String inStr)  
	   {  
	       try  
	       {  
	           // return new ByteArrayInputStream(inStr.getBytes());  
	           // return new ByteArrayInputStream(inStr.getBytes("UTF-8"));  
	           return new StringBufferInputStream(inStr);  
	       }  
	       catch (Exception e)  
	       {  
	           e.printStackTrace();  
	       }  
	       return null;  
	   } 
}

