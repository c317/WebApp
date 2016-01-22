package com.gasinfo.server.webselect;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SelectContent {

	private String content = "";
	private Elements elements;
	//針對中海油三層正文
    private Element element;
    

	public String GetContentA(File file, String strClassName) {

		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		elements = doc.getElementsByClass(strClassName);
		content = elements.text().trim();
		return content;
	}

	public String GetContentB(File file, String strTagName) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
//		System.out.println(doc);
//	   
		elements = doc.getElementsByTag(strTagName);
		content = elements.text().trim();
		
		return content;
	}
	
	public String GetContentC(File file, String strVar,String strValue){
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		elements = doc.getElementsByAttributeValue(strVar, strValue);
		content = elements.text().trim();
		
		return content;
	}
	
	//国家能源网Var="id" Value="PrintTable" 
	public String GetContentD(File file, String strVar,String strValue){
		String HTML = new GetHtmlCharset().GetHtml(file);
		
		Document doc = Jsoup.parse(HTML);
		elements = doc.getElementsByAttributeValue(strVar, strValue);	
		elements=elements.get(0).getAllElements().get(1).getElementsByIndexGreaterThan(2);
		elements=elements.get(0).getAllElements().get(0).getElementsByIndexGreaterThan(2);
		elements=elements.get(0).getAllElements().get(1).getElementsByIndexGreaterThan(0);;
	
		content = elements.text().trim();
		return content;
	}
	//中国海油网
	public String GetContentE(File file, String strClassName) {

		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		//elements = doc.select("meta");
		 element = doc.getElementsByClass(strClassName).first();
		//elements=elements.select("ContentStart");

		content = element.text().trim();
		
		return content;
	}
	//英文网站--沙美石油网
	public String GetContentF(File file, String strClassName) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		elements = doc.getElementsByClass(strClassName);
		content = elements.text().trim();
		int beginIndex;
		int endIndex;
		beginIndex = content.indexOf("Print Share");
		endIndex = content.length();
		content = content.substring(beginIndex, endIndex);
		return content;
	}
	//針對中海油三層正文的處理方法
	public String GetHtml2(){
		String htmlcontent = element.html();
		
		int i=htmlcontent.indexOf("附件");
		if(i>0){
			htmlcontent=htmlcontent.substring(0,i);
		}
		
		i=htmlcontent.indexOf("中国非常规油气网www.cuog.cn-页岩气专业技术网站");
		if(i>0){
			htmlcontent=htmlcontent.substring(0,i);
		}
		return htmlcontent;
	}
	public String GetHtml(){
		String htmlcontent = elements.html();
		
		int i=htmlcontent.indexOf("附件");
		if(i>0){
			htmlcontent=htmlcontent.substring(0,i);
		}
		
		i=htmlcontent.indexOf("中国非常规油气网www.cuog.cn-页岩气专业技术网站");
		if(i>0){
			htmlcontent=htmlcontent.substring(0,i);
		}
		return htmlcontent;
	}
	public String GetHtml7(){
		String htmlcontent = elements.html();
		int o = htmlcontent.length();
		int i=htmlcontent.indexOf("parsys primary");
		
		htmlcontent=htmlcontent.substring(i+18,o);
		return htmlcontent;
	}
	public String GetHtml000(){
		String htmlcontent = elements.html();
		int o = htmlcontent.length();
		int i=htmlcontent.indexOf("<font");
		
		htmlcontent=htmlcontent.substring(i,o);
		return htmlcontent;
	}
	
}
