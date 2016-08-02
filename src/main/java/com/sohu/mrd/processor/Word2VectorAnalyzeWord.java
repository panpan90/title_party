package com.sohu.mrd.processor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.mrd.util.IOUtils;
public class Word2VectorAnalyzeWord {
	public static final  Log  LOG = LogFactory.getLog(Word2VectorAnalyzeWord.class);
	public static void main(String[] args) {
		FileOutputStream fos = null;
		FileInputStream fis =null;
		try {
		    fis=new FileInputStream("word2vec_file/content旅游");
			fos=new FileOutputStream("word2vec_file/splitWord",true);
			BufferedReader br=new BufferedReader(new InputStreamReader(fis));
			String line="";
			StringBuilder sb=new StringBuilder();
			ArrayList<String> stopWords=IOUtils.readFile2List("word2vec_file/stopword.txt");
			while((line=br.readLine())!=null)
			{
				if(!"".equals(line.trim())&&line.length()>20)
				{
					List<Term>  terms=ToAnalysis.parse(line);
					for(int i=0;i<terms.size();i++)
					{
						if(!stopWords.contains(terms.get(i).getName()))
						{
							sb.append(terms.get(i).getName());
							sb.append("\t");	
						}
					}
					sb.append("\n");
				}
				fos.write(sb.toString().getBytes());
				LOG.info(sb.toString());
				sb.delete(0, sb.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				 fos.close();
			      fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
