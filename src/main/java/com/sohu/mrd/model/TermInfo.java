package com.sohu.mrd.model;

import java.util.HashMap;

public class TermInfo {
	public HashMap<String, Integer> title; //所有标题
	public int allSplitTermCount; //所有分词个数
	public int category; //该类标题的类别  0表示标题党  1 表示 正常标题

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public HashMap<String, Integer> getTitle() {
		return title;
	}

	public void setTitle(HashMap<String, Integer> title) {
		this.title = title;
	}

	public int getAllSplitTermCount() {
		return allSplitTermCount;
	}

	public void setAllSplitTermCount(int allSplitTermCount) {
		this.allSplitTermCount = allSplitTermCount;
	}
}
