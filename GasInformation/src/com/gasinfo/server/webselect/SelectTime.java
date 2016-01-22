package com.gasinfo.server.webselect;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SelectTime {

	private String time = "";

	public String GetTimeA(File file, String strClassName) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByClass(strClassName);
		time = elements.text();
		Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Pattern pattern2 = Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日");
		Matcher match1 = pattern1.matcher(time);
		Matcher match2 = pattern2.matcher(time);
		if (match1.find()) {
			time = match1.group(0);
		}
		if (match2.find()) {
			time = match2.group(0);
			time = time.replaceAll("[年|月]", "-");
			time = time.replaceAll("[日]", "");

		}

		if (time.length() != 10) {
			if (time.charAt(6) == '-') {
				time = time.substring(0, 5) + '0' + time.substring(5);
			}
			if (time.length() != 10) {
				time = time.substring(0, 8) + '0' + time.substring(8);
			}
		}

		return time;
	}
	public String GetTimeCpcia(File file, String strClassName) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByClass(strClassName);
		time = elements.text();
		Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Pattern pattern2 = Pattern.compile("\\d{4}-\\d{1}-\\d{1}");
		Pattern pattern3 = Pattern.compile("\\d{4}-\\d{1}-\\d{2}");
		Pattern pattern4 = Pattern.compile("\\d{4}-\\d{2}-\\d{1}");
	
		Matcher match1 = pattern1.matcher(time);
		Matcher match2 = pattern2.matcher(time);
		Matcher match3 = pattern3.matcher(time);
		Matcher match4 = pattern4.matcher(time);
		if (match1.find()) {
			time = match1.group(0);
		}
		if (match2.find()) {
			time = match2.group(0);
			time = time.replaceAll("-", "-0");
		}
		if (match3.find()) {
			time = match3.group(0);
			time = time.replaceFirst("-", "-0");
		}
		if (match4.find()) {
			time = match4.group(0);
			int all = time.length();
			int a = time.indexOf("-");
			String firstpart = time.substring(0,a+4);
			String Secondpart = time.substring(a+4);
			time = (firstpart+"0"+Secondpart).trim();
		}
		if (time.length() != 10) {
			if (time.charAt(6) == '-') {
				time = time.substring(0, 5) + '0' + time.substring(5);
			}
			if (time.length() != 10) {
				time = time.substring(0, 8) + '0' + time.substring(8);
			}
		}

		return time;
	}

	public String GetTimeB(File file, String strTagName) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByTag(strTagName);
		time = elements.toString();
		Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Pattern pattern2 = Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日");
		Matcher match1 = pattern1.matcher(time);
		Matcher match2 = pattern2.matcher(time);
		if (match1.find()) {
			time = match1.group(0);
		}
		if (match2.find()) {
			time = match2.group(0);
			time = time.replaceAll("[年|月]", "-");
			time = time.replaceAll("[日]", "");

		}

		if (time.length() != 10) {
			if (time.charAt(6) == '-') {
				time = time.substring(0, 5) + '0' + time.substring(5);
			}
			if (time.length() != 10) {
				time = time.substring(0, 8) + '0' + time.substring(8);
			}
		}

		return time;

	}

	public String GetTimeC(File file, String strVar, String strValue) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByAttributeValue(strVar, strValue);
		time = elements.toString();
		Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Pattern pattern2 = Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日");
		Matcher match1 = pattern1.matcher(time);
		Matcher match2 = pattern2.matcher(time);
		if (match1.find()) {
			time = match1.group(0);
		}
		if (match2.find()) {
			time = match2.group(0);
			time = time.replaceAll("[年|月]", "-");
			time = time.replaceAll("[日]", "");

		}

		if (time.length() != 10) {
			if (time.charAt(6) == '-') {
				time = time.substring(0, 5) + '0' + time.substring(5);
			}
			if (time.length() != 10) {
				time = time.substring(0, 8) + '0' + time.substring(8);
			}
		}
		return time;
	}

	public String GetTimeD(File file) {
		String rr = file.getName();
		String time = rr.substring(1, 5) + "-" + rr.substring(5, 7) + "-"
				+ rr.substring(7, 9);
		return time;
	}

	public String GetTimeE(File file, String strVar, String strValue) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByAttributeValue(strVar, strValue);
		time = elements.toString();
		Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

		Matcher match1 = pattern1.matcher(time);

		if (match1.find()) {
			time = match1.group(0);
		}

		if (time.length() != 10) {
			if (time.charAt(6) == '-') {
				time = time.substring(0, 5) + '0' + time.substring(5);
			}
			if (time.length() != 10) {
				time = time.substring(0, 8) + '0' + time.substring(8);
			}
		}
		return time;

	}

	// lbh沙美石油网站标题清洗
	public String GetTimeF(File file, String strClassName) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByClass(strClassName);
		time = elements.text();
		int timeindex1;
		int timeindex2;

		timeindex1 = time.length();
		timeindex2 = time.indexOf(",");
		time = time.substring(timeindex2 + 1, timeindex1).trim();
		int timeindexnew = time.length();
		String timestr[] = new String[] { "2015", "10", "26" };
		int time1 = time.indexOf(" ");
		String time1str = time.substring(0, time1);
		switch (time1str) {
		case "January":
			timestr[1] = "01";
			break;
		case "February":
			timestr[1] = "02";
			break;
		case "March":
			timestr[1] = "03";
			break;
		case "April":
			timestr[1] = "04";
			break;
		case "May":
			timestr[1] = "05";
			break;
		case "June":
			timestr[1] = "06";
			break;
		case "July":
			timestr[1] = "07";
			break;
		case "August":
			timestr[1] = "08";
			break;
		case "September":
			timestr[1] = "09";
			break;
		case "October":
			timestr[1] = "10";
			break;
		case "November":
			timestr[1] = "11";
			break;
		case "December":
			timestr[1] = "12";
			break;
		default:
			break;
		}
		//获得年
		int time0 = time.indexOf(",");
		String time0str = time.substring(time0+1, timeindexnew);
		timestr[0] = time0str.trim();
		//获得日
		String time2str = time.substring(time1, time0);
		timestr[2] = time2str.trim();
		time = timestr[0]+"-"+timestr[1]+"-"+timestr[2];
		return time;

	}
	public String GetTimeNEW(File file, String key,String value) {
		String HTML = new GetHtmlCharset().GetHtml(file);
		Document doc = Jsoup.parse(HTML);
		Elements elements = doc.getElementsByAttributeValue(key, value);
		time = elements.text();
		Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Pattern pattern2 = Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日");
		Pattern pattern3 = Pattern.compile("\\d{4}.\\d{2}.\\d{2}");
		Matcher match1 = pattern1.matcher(time);
		Matcher match2 = pattern2.matcher(time);
		Matcher match3 = pattern3.matcher(time);
		if (match1.find()) {
			time = match1.group(0);
		}
		if (match2.find()) {
			time = match2.group(0);
			time = time.replaceAll("[年|月]", "-");
			time = time.replaceAll("[日]", "");

		}
		if (match3.find()) {
			time = match3.group(0);
			time = time.replaceAll("[.]", "-");

		}

		if (time.length() != 10) {
			if (time.charAt(6) == '-') {
				time = time.substring(0, 5) + '0' + time.substring(5);
			}
			if (time.length() != 10) {
				time = time.substring(0, 8) + '0' + time.substring(8);
			}
		}

		return time;

	}
}
