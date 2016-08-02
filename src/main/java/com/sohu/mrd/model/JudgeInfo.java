package com.sohu.mrd.model;
/**
 * 标题，权重，出现次数 的模型
 * @author Administrator
 *
 */
public class JudgeInfo {

	public  double weightScore;
	public  int  wordTimes;
	public  double appearRadio;
	public double getAppearRadio() {
		return appearRadio;
	}
	public void setAppearRadio(double appearRadio) {
		this.appearRadio = appearRadio;
	}
	public double getWeightScore() {
		return weightScore;
	}
	public void setWeightScore(double weightScore) {
		this.weightScore = weightScore;
	}
	public int getWordTimes() {
		return wordTimes;
	}
	public void setWordTimes(int wordTimes) {
		this.wordTimes = wordTimes;
	}
    
	
}
