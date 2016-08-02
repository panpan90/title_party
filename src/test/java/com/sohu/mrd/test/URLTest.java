package com.sohu.mrd.test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class URLTest {
	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8011/?title=%E6%88%91%E6%98%AF%E6%A0%87%E9%A2%98%E5%85%9A";
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		InputStream is = conn.getInputStream();
		byte[] b = new byte[is.available()];
		IOUtils.read(is, b);
		System.out.println(new String(b, "utf-8"));
		
	}
}
