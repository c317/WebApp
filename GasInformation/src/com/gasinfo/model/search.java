package com.gasinfo.model;

public class search {

	//搜索关键词
	private String strSearchKey;
	
	//搜索起始时间
	private String strBeginTime;
	
	//搜索终止时间
	private String strEndTime;
	
	//搜索区域
	private String strArea;
	
	//搜索标题或正文      0：按标题搜索     1：按正文搜索
	private int intTorCNo;
	
	//搜索索引位置
	//0：所有索引      1：工作动态      2：矿产管理       3：政策法规        4：一周热点         5：国际合作
	//6：科技进展      7：图片新闻      8：领导动态       9：统计数据      10：勘探开发      11：油价信息
	private int intWebNo;

	public String getStrSearchKey() {
		return strSearchKey;
	}

	public void setStrSearchKey(String strSearchKey) {
		this.strSearchKey = strSearchKey;
	}

	public String getStrBeginTime() {
		return strBeginTime;
	}

	public void setStrBeginTime(String strBeginTime) {
		this.strBeginTime = strBeginTime;
	}

	public String getStrEndTime() {
		return strEndTime;
	}

	public void setStrEndTime(String strEndTime) {
		this.strEndTime = strEndTime;
	}

	public String getStrArea() {
		return strArea;
	}

	public void setStrArea(String strArea) {
		this.strArea = strArea;
	}

	public int getIntTorCNo() {
		return intTorCNo;
	}

	public void setIntTorCNo(int intTorCNo) {
		this.intTorCNo = intTorCNo;
	}

	public int getIntWebNo() {
		return intWebNo;
	}

	public void setIntWebNo(int intWebNo) {
		this.intWebNo = intWebNo;
	}

	

}
