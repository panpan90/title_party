package com.sohu.mrd.splitword;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.mrd.getTitle.GetTitle;
import com.sohu.mrd.model.TermInfo;
import com.sohu.mrd.util.IOUtils;
import com.sohu.mrd.util.MathUtils;
public class SplitWord{
	public static  final Log LOG=LogFactory.getLog(SplitWord.class);
	/**
	 * 对集合list中的所有标题进行分词 并进行停留词过滤
	 * @param titles
	 * @return
	 */
	public static TermInfo  getSplitWrodFre(ArrayList<String> titles){
		  String stopWordPath=SplitWord.class.getClassLoader().getResource("svm_files/mystopWord").getPath();
		  System.out.println("stopWordPath"+stopWordPath);
		  ArrayList<String>  splitWords=IOUtils.readFile2List(stopWordPath);
		  System.out.println("splitWords"+splitWords.toString());
		  boolean  isCategory=true;
		  int allTermTimes=0;
		  TermInfo termInfo=new TermInfo();
		  HashMap<String,Integer> titleMap=new HashMap<String,Integer>();
		  String category=titles.get(0);//titles第一个字段存储标题类型
		  int i=0;
		  if(MathUtils.isNumber(category))
		  {
			  i=1;
		  }else{
			  isCategory=false;
		  }
		  for(;i<titles.size();i++)
		  {
			  String title=titles.get(i);
			  if(!"".equals(title.trim())&&title!=null)
			  {
				  List<Term>  terms=ToAnalysis.parse(title);
				  for(int k=0;k<terms.size();k++)
				  {
					 Term  term=terms.get(k);
					 String termName=term.getName().trim();
					 //判断分词之后是否为空,防止:430  情况出现
					 if(!"".equals(termName))
					 {
						 //过滤停用词
							if(!splitWords.contains(termName))
							{
								 //LOG.info("进行停用词过滤");
								 allTermTimes++;
								 int count=1;
								 if(titleMap.containsKey(termName))
								 {
									 count=titleMap.get(termName)+1;
									 titleMap.put(termName, new Integer(count));
								 }
								 titleMap.put(termName, count);
							}else{
								//LOG.info("过滤的停用词 "+termName);
							} 
					 }
					 
				  }
			  }
		  } 
		  if(isCategory)
		  {
			  termInfo.setCategory(Integer.valueOf(category));  
		  }
		  termInfo.setTitle(titleMap);
		  termInfo.setAllSplitTermCount(allTermTimes);
		 return termInfo;
	}
	
	/**
	 * 根据文件路径返回标题党的分词信息
	 * @param TitlePartyPath
	 * @return
	 */
	public static  TermInfo getTitleParyWords(String titlePartyPath)
	{
		ArrayList<String> titles =IOUtils.readFile2List(titlePartyPath);
		System.out.println("titles"+titles.toString());
		titles.add(0, "-1");
		TermInfo termInfo=getSplitWrodFre(titles);
		return  termInfo;
	}
	public static ArrayList<String>   sigleTitleAnalysis(String title)
	{
		ArrayList<String> titleNames=new ArrayList<String>();
		if(""!=title.trim()&&null!=title)
		{
			List<Term>  terms=ToAnalysis.parse(title);
			for(int i=0;i<terms.size();i++)
			{
				Term term=terms.get(i);
				String name=term.getName();
				titleNames.add(name);
			}
		}
		return titleNames;
	}
}
