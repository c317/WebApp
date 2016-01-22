package com.gasinfo.server.indexdatebase;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream; 
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.dom.DOMAttribute; 
import org.dom4j.QName;

import com.gasinfo.config.Configuration;

public class Words {

	private Document doc;

	public static void main(String[] args) throws Exception {

		// new Words().AddWords(5, "中原油田");
		// new Words().RemoveWords(5, "中原油田");

	}

	public void UpData() {

	}

	// 得到节点下的所有词
	public ArrayList<String> getAllWords() throws Exception {
		this.OpenXml("Wordsxml.xml");
		ArrayList<String> listAllWords = new ArrayList<String>();
		ArrayList<String> listWords = new ArrayList<String>();
		listWords = this.getWords("Oil");
		for (int i = 0; i < listWords.size(); i++) {
			listAllWords.add(listWords.get(i));
		}
		listWords = this.getWords("Company");
		for (int i = 0; i < listWords.size(); i++) {
			listAllWords.add(listWords.get(i));
		}
		listWords = this.getWords("Basin");
		for (int i = 0; i < listWords.size(); i++) {
			listAllWords.add(listWords.get(i));
		}
		listWords = this.getWords("KeyWords");
		for (int i = 0; i < listWords.size(); i++) {
			listAllWords.add(listWords.get(i));
		}
		//listwords里不含有Blacklist
		return listAllWords;
	}

	// 得到指定节点下的词
	public ArrayList<String> getWords(String Name) throws Exception {
		this.OpenXml("Wordsxml.xml");
		ArrayList<String> listWords = new ArrayList<String>();
		Element RootElemtent = doc.getRootElement();
		Element elemtent = RootElemtent.element(Name);

		List<Element> StringElemtents = elemtent.elements();
		for (int i = 0; i < StringElemtents.size(); i++) {
			listWords.add(StringElemtents.get(i).getTextTrim());
		}
		return listWords;

	}
	//关键词筛选功能用：得到指定节点下的关键词
		public ArrayList<String> getWebKeyWords(String WebName) throws Exception {
			this.OpenXml("KeyWords.xml");
			ArrayList<String> listWebKeyWords = new ArrayList<String>();
			Element RootElemtent = doc.getRootElement();
			
			Element elemtent = RootElemtent.element(WebName);
	        List<Element> StringElemtents = elemtent.elements();
			for (int i = 0; i < StringElemtents.size(); i++) {
				listWebKeyWords.add(StringElemtents.get(i).getTextTrim());
			}
			return listWebKeyWords;

		}
	// 新增词
	// ID=1 Oil ID=2 Company ID=3 Basin ID=4 KeyWords ID=5 Blacklist
	public void AddWords1(String WordsType, String WordsName) throws Exception {
		this.OpenXml("Wordsxml.xml");
		Element RootElemtent = doc.getRootElement();
		Element elemtent = RootElemtent.element(WordsType);
		List<Element> StringElemtents = elemtent.elements();
		// 判断是否已经存在该词
		boolean bool = true;
		for (int i = 0; i < StringElemtents.size(); i++) {
			if (WordsName.trim().equals(StringElemtents.get(i).getTextTrim())) {
				bool = false;
			}
		}
		if (bool) {
			// 新增节点
			Element AddElemtent = elemtent.addElement("String");
			AddElemtent.setText(WordsName);
		}
		this.saveXMLFile("Wordsxml.xml");
	}
	
	public void AddWords(String WordsType, String WordsName) throws Exception {
		SAXReader reader = new SAXReader();  
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")+ File.separator + "Wordsxml.xml";
		Document document = reader.read(new File(RootURL_WebSelectXml));  
		Element node = document.getRootElement();  
		Element element = node.element(WordsType);
		List<Element> StringElemtents = node.elements();
		// 判断是否已经存在该词
		boolean bool = true;
		for (int i = 0; i < StringElemtents.size(); i++) {
			if (WordsName.trim().equals(StringElemtents.get(i).getTextTrim())) {
				bool = false;
			}
		}
		if (bool) {
			// 新增节点
			Element AddElemtent = element.addElement(WordsName);
			AddElemtent.setText(WordsName);
		}
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8"); 
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File(RootURL_WebSelectXml)), "UTF-8"), format); 
		writer.write(document);
		writer.flush();
		 writer.close();  
	}
	public void AddWebKeyWords(String WordsType, String WordsName) throws Exception {
		SAXReader reader = new SAXReader();  
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")+ File.separator + "KeyWords.xml";
		Document document = reader.read(new File(RootURL_WebSelectXml));  
		Element node = document.getRootElement();  
		Element element = node.element(WordsType);
		List<Element> StringElemtents = node.elements();
		// 判断是否已经存在该词
		boolean bool = true;
		for (int i = 0; i < StringElemtents.size(); i++) {
			if (WordsName.trim().equals(StringElemtents.get(i).getTextTrim())) {
				bool = false;
			}
		}
		if (bool) {
			// 新增节点
			Element AddElemtent = element.addElement(WordsName);
			AddElemtent.setText(WordsName);
		}
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8"); 
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File(RootURL_WebSelectXml)), "UTF-8"), format); 
		writer.write(document);
		writer.flush();
		 writer.close();  
	}

	// 删除词
	// ID=1 Oil ID=2 Company ID=3 Basin ID=4 KeyWords ID=5 Blacklist
	
	public void RemoveWords(String WordsType, String WordsName)
			throws Exception {
		SAXReader reader = new SAXReader();  
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")+ File.separator + "Wordsxml.xml";
		Document document = reader.read(new File(RootURL_WebSelectXml));  
		Element node = document.getRootElement();  
		Element element = node.element(WordsType);
		Element author = element.element(WordsName); 
		boolean flag = element.remove(author);
		System.out.println(flag);  
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8"); 
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File(RootURL_WebSelectXml)), "UTF-8"), format); 
		writer.write(document);
		writer.flush();
		 writer.close();  
		
	}
	public void RemoveWebKeyWords(String WordsType, String WordsName)
			throws Exception {
		SAXReader reader = new SAXReader();  
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")+ File.separator + "KeyWords.xml";
		Document document = reader.read(new File(RootURL_WebSelectXml));  
		Element node = document.getRootElement();  
		Element element = node.element(WordsType);
		Element author = element.element(WordsName); 
		boolean flag = element.remove(author);
		System.out.println(flag);  
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8"); 
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File(RootURL_WebSelectXml)), "UTF-8"), format); 
		writer.write(document);
		writer.flush();
		 writer.close();  
		
	}

	// 判断是否符合词属性
	public boolean Contain(String Content, ArrayList<String> list) {
		boolean bool = false;
		if(Content==null){
			return false;
		}
		for (int i = 0; i < list.size(); i++) {
			if (Content.indexOf(list.get(i).toString()) != -1) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	// 判断是否不符合词属性
	public boolean NotContain(String Content, ArrayList<String> list) {
		boolean bool = true;
		for (int i = 0; i < list.size(); i++) {
			if (Content.indexOf(list.get(i).toString()) != -1) {
				bool = false;
				break;
			}
		}
		return bool;
	}

	// 读取XML
	public void OpenXml(String xml) throws Exception {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")
				+ File.separator + xml;
		SAXReader sr = new SAXReader();
		sr.setEncoding("UTF-8");

		doc = sr.read(RootURL_WebSelectXml);
		// doc = sr.read(new
		// ByteArrayInputStream(RootURL_WebSelectXml.getBytes("UTF-8")));
	}

	// 存储XML
	private void saveXMLFile(String xml) {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")
				+ File.separator + xml;
		
		System.out.println(RootURL_WebSelectXml);
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileWriter(new File(
					RootURL_WebSelectXml)), format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			// String loginfo = StackTraceToString.getExceptionTrace(e);
			// writelog.writeLogToEnd("LocalServerManager", loginfo);
		}
	}
}
