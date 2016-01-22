package com.gasinfo.server.webselect;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SelectPostion {
private String postion="";
	
	public boolean GetPostionA(File file,String strClassName,String strPostion){
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByClass(strClassName);
		postion = elements.text();
		if(postion.indexOf(strPostion)!=-1){
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean GetPostionB(File file, String strVar,String strValue,String strPostion){
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByAttributeValue(strVar, strValue);
		postion = elements.toString();
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]{3,10}");
		Matcher match = pattern.matcher(postion);
		if (match.find())     
		{     
			postion=match.group(0);
		}
		if(postion.indexOf(strPostion)!=-1){
			return true;
		}else{
			return false;
		}
		
	}
	public boolean GetPostionNEW(File file,String strPostion){
		String HTML = new GetHtmlCharset().GetHtml(file);
		String postion = file.getName();
		if(postion.indexOf(strPostion)!=-1){
			return true;
		}else{
			return false;
		}
		
	}

}
