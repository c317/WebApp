package com.gasinfo.server.webselect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class GetHtmlCharset {

	String html = null;
	String charset = null;


	String openFile1(File file) {
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String szContent = "";
			String szTemp;

			while ((szTemp = bis.readLine()) != null) {
				szContent += szTemp + "\n";
			}
			bis.close();
			return szContent;
		} catch (Exception e) {
			return "";
		}
	}

	
	String openFile2(File file, String charset) {
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), charset));
			String szContent = "";
			String szTemp;

			while ((szTemp = bis.readLine()) != null) {
				szContent += szTemp + "\n";
			}
			bis.close();
			return szContent;
		} catch (Exception e) {
			return "";
		}
	}

	
	String GetCharset(File file) {

		Parser parser;
		String string = null;
		String companyName = null;
		String regEx = "charset=.+?\"";
		String abc = "=.+?\"";
		NodeList node;
		String szContent = openFile1(file);
		parser = Parser.createParser(szContent, "UTF-8");
		try {
			NodeFilter filter = new TagNameFilter("meta");
			node = parser.extractAllNodesThatMatch(filter);
			string = node.toHtml().toString();
		} catch (ParserException e) {
			e.printStackTrace();
		}
		try {
			Matcher mat = Pattern.compile(regEx).matcher(string);
			while (mat.find()) {
				companyName = mat.group();
			}
			Matcher ma = Pattern.compile(abc).matcher(companyName);
			while (ma.find()) {
				charset = ma.group().replaceAll("=|\"", "");
			}
			return charset;
		} catch (Exception e) {
			return null;
		}
	}

	
	String GetHtml(File file) {
		charset = GetCharset(file);
		html = openFile2(file, charset);
		return html;
	}

}
