package com.gasinfo.htmlToDoc;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
		/**
		 * 按长度截取字符串，超过长度的添加省略符，显示缩写
		 * 
		 * @author YPJ
		 * @param original
		 * @param width
		 * @param ellipsis
		 * @return
		 */
	public class htmlToDoc {
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

		public  String htmlToText(String inputString) {
			String htmlStr = inputString;
			String textStr = "";
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
				
				Pattern scriptPattern = Pattern.compile(scriptRegEx,
						Pattern.CASE_INSENSITIVE);
				Matcher scriptMatcher = scriptPattern.matcher(htmlStr);
				htmlStr = scriptMatcher.replaceAll("");
				
				Pattern stylePattern = Pattern.compile(styleRegEx,
						Pattern.CASE_INSENSITIVE);
				Matcher styleMatcher = stylePattern.matcher(htmlStr);
				htmlStr = styleMatcher.replaceAll("");
				
				Pattern htmlPattern1 = Pattern.compile(htmlRegEx1,
						Pattern.CASE_INSENSITIVE);
				Matcher htmlMatcher1 = htmlPattern1.matcher(htmlStr);
				htmlStr = htmlMatcher1.replaceAll("");
				
				Pattern htmlPattern2 = Pattern.compile(htmlRegEx2,
						Pattern.CASE_INSENSITIVE);
				Matcher htmlMatcher2 = htmlPattern2.matcher(htmlStr);
				htmlStr = htmlMatcher2.replaceAll("");
				textStr = htmlStr;
			} catch (Exception e) {
				System.err.println("->htmlToText(String inputString):"
						+ e.getMessage());
			}
			textStr = textStr.replaceAll("&acute;", "\'");
			textStr = textStr.replaceAll("&quot;", "\"");
			textStr = textStr.replaceAll("&lt;", "<");
			textStr = textStr.replaceAll("&gt;", ">");
			textStr = textStr.replaceAll("&nbsp;", "");
			textStr = textStr.replaceAll("&amp;", "&");
			textStr = textStr.replaceAll("\n", "");
			textStr = textStr.replaceAll("&divide;", "/");//HTML中的除号为"&divide;"，写入word前要转换
			textStr = textStr.replaceAll("&times;", "x");//HTML中的“x”（乘号）为"&times;"，写入word前要转换
			textStr = textStr.replaceAll("&middot;", "·");//HTML中的“·”（如姓名中间的点号）为"&middot;"，写入word前要转换
			return textStr;
		}
}
