package com.gasinfo.model;

public class News {
	private String title="";
	private String source="";
	private String time="";
	private String content="";
	private String MK="";
	private String charset="";
	private String oilField = "";
	private String siteSource="";
	private String Output="";
	private String oil="";
	private String company="";
	private String basin="";
	
	public String getOil() {
		return oil;
	}
	public void setOil(String oil) {
		this.oil = oil;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBasin() {
		return basin;
	}
	public void setBasin(String basin) {
		this.basin = basin;
	}
	public String getOilField() {
		return oilField;
	}
	public void setOilField(String oilField) {
		this.oilField = oilField;
	}
	public String getSiteSource() {
		return siteSource;
	}
	public void setSiteSource(String siteSource) {
		this.siteSource = siteSource;
	}
	public Integer getWebWeight() {
		return webWeight;
	}
	public void setWebWeight(int webWeight) {
		this.webWeight = webWeight;
	}
	private String html="";
	private int webWeight;
	public String getMK() {
		return MK;
	}
	public void setMK(String mK) {
		MK = mK;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getOutput() {
		return Output;
	}
	public void setOutput(String output) {
		Output = output;
	}
}
