package com.gasinfo.wordsanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class OpenFile {

	public static String openFile(File file) {
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String szContent = "";
			String szTemp;

			while ((szTemp = bis.readLine()) != null) {
				szContent += szTemp + "，" + "\n";
			}
			bis.close();
			szContent = szContent.replaceAll("[\\pZ|\n\\pN\\p{P}&&[^，。！？、；：]]",
					"");
			return szContent;
		} catch (Exception e) {
			return "";
		}
	}

	// 去除HTML标签，获取正文
	public String deleteHTMLTag(String html) {
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByTag("p");
		elements.select("strong").remove();
		String content = elements.text().toString();
		return content;
	}

	// 计算字符串在文章中出现的次数
	public static int strCount(String content, String target) {
		int count = 0;
		int index = 0;
		while (true) {
			index = content.indexOf(target, index);
			if (index >= 0) {
				count++;
				index++;
			} else {
				break;
			}
		}
		return count;
	}

	// 将提取出来的关键词加入到文本中
	public static void AddtoTxt(String key) throws IOException {

		File filePath = new File("E:\\words.txt");
		if (!filePath.exists()) {
			filePath.createNewFile();
		}

		OutputStream os = new FileOutputStream(filePath, true);
		OutputStreamWriter osr = new OutputStreamWriter(os, "utf-8");
		osr.write(key);
		osr.write("\t");
//		osr.write("\r\n");
		if (osr != null) {
			osr.close();
		}
		if (os != null) {
			os.close();
		}
	}
}
