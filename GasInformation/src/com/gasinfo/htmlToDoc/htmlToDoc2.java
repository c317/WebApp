package com.gasinfo.htmlToDoc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class htmlToDoc2 {
	public static String abbreviate(String original, int width, String ellipsis) {
		if (original == null || "".equals(original)) {
			return "";
		}
		int byteIndex = 0;
		int charIndex = 0;
		for (; charIndex < original.length(); charIndex++) {
			byteIndex = (int) original.charAt(byteIndex) > 256 ? byteIndex + 2
					: byteIndex + 1;
			if (byteIndex > width) {
				break;
			}
		}
		if (byteIndex > width) {
			charIndex = charIndex - ellipsis.length()/2*2;
			return original.substring(0, charIndex > 0 ? charIndex : 0)
					+ ellipsis;
		}
		return original.substring(0, charIndex);
	}

	public  String[] htmlToText(String inputString) {
		//String inputString1 = inputString.replaceAll("&acute;", "\'");
	 
		String[] str = null; 		
		String htmlStr = inputString;
		
		String replaceComment="<!--.+-->";
		String scriptRegEx = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		String styleRegEx = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
		String htmlRegEx1 = "<[^>]*>";
		String htmlRegEx2 = "<[^>]*";
		try {
			
			Pattern commentPattern = Pattern.compile(replaceComment,
					Pattern.CASE_INSENSITIVE);
			Matcher commentMatcher = commentPattern.matcher(htmlStr);
			htmlStr = commentMatcher.replaceAll("");
			System.out.println(htmlStr);
			
			Pattern scriptPattern = Pattern.compile(scriptRegEx,
					Pattern.CASE_INSENSITIVE);
			Matcher scriptMatcher = scriptPattern.matcher(htmlStr);
			htmlStr = scriptMatcher.replaceAll("");
			
		
			Pattern stylePattern = Pattern.compile(styleRegEx,
					Pattern.CASE_INSENSITIVE);
			Matcher styleMatcher = stylePattern.matcher(htmlStr);
			htmlStr = styleMatcher.replaceAll("");
			//从这开始分
		
			str=htmlStr.split("<p>");
			
			
			for(int n = 0;n<str.length;n++)
			{
			//在这里保留<p>标签
				if(str[n].length()>1)
				{
				
			Pattern htmlPattern1 = Pattern.compile(htmlRegEx1,
					Pattern.CASE_INSENSITIVE);
			Matcher htmlMatcher1 = htmlPattern1.matcher(str[n]);
			str[n] = htmlMatcher1.replaceAll("");
			//System.out.println(htmlStr);
			//
			Pattern htmlPattern2 = Pattern.compile(htmlRegEx2,
					Pattern.CASE_INSENSITIVE);
			Matcher htmlMatcher2 = htmlPattern2.matcher(str[n]);
			str[n] = htmlMatcher2.replaceAll("");
			//textStrnow[n] = str[n];
				}
			//System.out.println(str[n]);
			}
			
			for(int n=0;n<str.length;n++)
			{
			//System.out.println(textStr);
				str[n] = str[n].replaceAll("&acute;", "\'");
				str[n] = str[n].replaceAll("&quot;", "\"");
				str[n] = str[n].replaceAll("&lt;", "<");
				str[n] = str[n].replaceAll("&gt;", ">");
				str[n] = str[n].replaceAll("&nbsp;", "");
				str[n] = str[n].replaceAll("&amp;", "&");
				str[n] = str[n].replaceAll("\n", "");
				str[n] = str[n].replaceAll("&divide;", "/");//HTML中的除号为"&divide;"，写入word前要转换
				str[n] = str[n].replaceAll("&times;", "x");//HTML中的“x”（乘号）为"&times;"，写入word前要转换
				str[n] = str[n].replaceAll("&middot;", "·");//HTML中的“·”（如姓名中间的点号）为"&middot;"，写入word前要转换
			}
					
		} catch (Exception e) {
			System.err.println("->htmlToText(String inputString):"
					+ e.getMessage());
		}
		

		return str;
		
	}

}
