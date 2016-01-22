package com.gasinfo.server.webselect;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SelectSource {

	private String source = "";

	// public String GetSourceA(File file, String strClassName) {
	//
	// String HTML = new GetHtmlCharset().GetHtml(file);
	// Document doc = Jsoup.parse(HTML);
	// Elements elements = doc.getElementsByClass(strClassName);
	// source = elements.text();
	//
	// if (source.indexOf("来源：") != -1) {
	// source = source.substring(source.indexOf("来源：") + 3);
	//
	// int i = source.indexOf("│");
	// if (i != -1) {
	// source = source.substring(1, i);
	// }
	// i=source.indexOf(" ");
	// while(i==0) {
	// source = source.substring(1);
	// i=source.indexOf(" ");
	// }
	//
	// try {
	// source = source.substring(0, i);
	// System.out.println(i);
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// // TODO: handle exception
	// }
	//
	// // if(source.startsWith(" ")){
	// // source = source.substring(1);
	// // i=source.indexOf(" ");
	// //
	// // if(i!=-1){
	// // source = source.substring(0, i);
	// // }
	// // }
	// }
	//
	// if (source.indexOf("发文机关") != -1) {
	// source = source.substring(source.indexOf("发文机关") + 4);
	// int i = source.indexOf(" ");
	// if (i != -1) {
	// source = source.substring(0, i);
	// }
	// }
	//
	// if (source.indexOf("发布机关") != -1) {
	// source = source.substring(source.indexOf("发布机关") + 4);
	// int i = source.indexOf(" ");
	// if (i != -1) {
	// source = source.substring(0, i);
	// }
	// }
	//
	// if (source.indexOf("发布时间") != -1) {
	// source = source.substring(0, source.indexOf("发布时间"));
	// }
	//
	// // /////////中文的正则表达式
	// Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]{3,14}");
	// Matcher match = pattern.matcher(source);
	// if (match.find()) {
	// source = match.group(0);
	// }
	//
	// return source;
	// }
	public String GetSourceA(File file, String strClassName) {

		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByClass(strClassName);
		source = elements.text();

		if (source.indexOf("来源：") != -1) {
			source = source.substring(source.indexOf("来源：") + 3);
			int i = source.indexOf("│");
			if (i != -1) {
				source = source.substring(1, i);
			}

		}
//		if (source.indexOf("") != -1) {
//
//			int i = source.indexOf(" ");
//			if (i != -1) {
//			source = source.substring(0, i);
//			}
//
//		}

		if (source.indexOf("发文机关") != -1) {
			source = source.substring(source.indexOf("发文机关") + 4);
			int i = source.indexOf(" ");
			if (i != -1) {
				source = source.substring(0, i);
			}
		}

		if (source.indexOf("发布机关") != -1) {
			source = source.substring(source.indexOf("发布机关") + 4);
			int i = source.indexOf(" ");
			if (i != -1) {
				source = source.substring(0, i);
			}
		}

		if (source.indexOf("发布时间") != -1) {
			source = source.substring(0, source.indexOf("发布时间"));
		}
		if (source.indexOf("發佈時間") != -1) {
			source = source.substring(0, source.indexOf("發佈時間"));
		}
		if (source.indexOf("FX168") != -1) {
			source ="中国能源网";
		}
		// /////////中文的正则表达式
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]{3,14}");
		Matcher match = pattern.matcher(source);
		if (match.find()) {
			source = match.group(0);
		}

		return source;
	}

	public String GetSourceB(File file, String strTagName, String strVar) {

		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByTag(strTagName);
		source = elements.toString();
		if (source.indexOf(strVar) != -1) {
			source = source.substring(source.indexOf(strVar) + strVar.length());
		}
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]{3,14}");
		Matcher match = pattern.matcher(source);
		if (match.find()) {
			source = match.group(0);
		}
		return source;

	}

	public String GetSourceC(File file, String strVar, String strValue) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByAttributeValue(strVar, strValue);
		source = elements.toString();
		if (source.indexOf(strValue) != -1) {
			source = source.substring(source.indexOf(strValue)
					+ strValue.length());
		}
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]{3,14}");
		Matcher match = pattern.matcher(source);
		if (match.find()) {
			source = match.group(0);
		}
		return source;
	}

	public String GetSourceD(File file, String source) {
		return source;
	}
	public String GetSourceNEW(File file, String key, String value) {

		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByAttributeValue(key, value);
		source = elements.toString();
		if (source.indexOf("来源：") != -1) {
			source = source.substring(source.indexOf("来源：") + 3);
			int i = source.indexOf("│");
			if (i != -1) {
				source = source.substring(1, i);
			}

		}
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]{2,14}");
		Matcher match = pattern.matcher(source);
		if (match.find()) {
			source = match.group(0);
		}
		else if(source.indexOf("FX168")!=0){
			source = "FX168";
			
		}
		return source;

	}
	public static void main(String[] args) {

	}
}
