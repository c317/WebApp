package com.gasinfo.server.webselect;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SelectTitle {

	private String title;

	//清洗标题时提供按照class进行提取（oilchina）
	public String GetTitleB(File file, String TagName) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements titleIn;
		titleIn = doc.getElementsByClass(TagName);	
		title = titleIn.text();
		return title.trim();
	}
	public String GetTitleA(File file, int intType) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		title = doc.title().toString();
		if (intType == 1) {
			if(title.lastIndexOf('-')!=-1){
				title=title.substring(title.lastIndexOf('-')+1).trim();
				int i=title.indexOf("_");
				if(i!=-1) {
					
				}
			}
			if(title.lastIndexOf('_')!=-1){
				return title.substring(title.lastIndexOf('_')+1).trim();
			}

			return title.trim();
			
		} else if (intType == 2) {
			if(title.indexOf('-')!=-1){
				title=title.substring(0,title.indexOf('-')).trim();
				int m= title.indexOf('-');
				if(m !=-1){
					if(m==0){
					int n =title.lastIndexOf('-');
					title=title.substring(n);
				    }else{
					title= title.substring(0,m);
				    }
			     }
			}
			if(title.indexOf('_')!=-1){
				title=title.substring(0,title.indexOf('_')).trim();
				int m= title.indexOf('-');
				if(m !=-1){
					if(m==0){
						int n =title.lastIndexOf('-');
					title=title.substring(n);
					}else{
						title= title.substring(0,m);
					}
				}
			}
			//////加去掉下划线或者中划线代码
			int m= title.indexOf('-');
			if(m !=-1){
				if(m==0){
					int n =title.lastIndexOf('-');
					title=title.substring(n);
				}else{
					title= title.substring(0,m);
				}
			}
			return title.trim();
		} 
		
		else {
			return title.trim();
		}
	}

	public static void main(String[] args) {
		String title = "外媒：中国援助尼泊尔石油或打破印度垄断-国际石油网";
		int m= title.indexOf('-');
		if(m !=-1){
			if(m==0){
				int n =title.lastIndexOf('-');
				title=title.substring(n);
			}else{
				title= title.substring(0,m);
			}
		}
		System.out.println(title);
	}
}
