package com.gasinfo.server.heritrix;

import com.gasinfo.config.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlMod {
	public XmlMod() {
	}

	Configuration rc = new Configuration("daoconfig.properties");
	String strTime = rc.getValue("Time_Heritrix");


	public void setMaxTime(String strSourceFilepath) throws Exception {
		File inputXml = new File(strSourceFilepath + File.separator + "order.xml");
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("UTF-8");
		Document document = saxReader.read(inputXml);
		// 找到设置时间的xml位置
		List listModTime = document.selectNodes("//controller/long/@name");

		Iterator iterModTime = listModTime.iterator();
		while (iterModTime.hasNext()) {
			Attribute attribute = (Attribute) iterModTime.next();
			if (attribute.getValue().equals("max-time-sec")) {
				Element element = (Element) attribute.getParent();
				element.setText(strTime);
				System.out.println("modifi order.xml time is ok");
			}
		} 
		OutputFormat outFmt = OutputFormat.createPrettyPrint();  
	    outFmt.setEncoding("UTF-8"); 
		XMLWriter output = new XMLWriter(new FileWriter(inputXml),outFmt);
		output.write(document);
		output.close();
	}

	//输入要修改的order.xml的位置，并用File打包
	public void modifyRecoverPath(String strTargetFilepath , String preURL) {
		try {
			/*
			 * 如果前一个文件夹中没有mirror文件夹，则说明前一次任务失败，
			 * 就再向前找一个文件夹，直到找到有mirror文件夹的文件夹为止
			 * preURL例如E:\Workplace\exjobs\ZhongShiYou\1
			 */
			File orderXmlPath = new File(strTargetFilepath + File.separator +"order.xml");
			
			int taskVersion = Integer.valueOf(preURL.substring(preURL.length()-1));
			String webNamePath = preURL.substring(0,preURL.length()-1);
			String havaMirrorFilePath = null;
			for (;; taskVersion--) {
				havaMirrorFilePath = webNamePath + "" + taskVersion;
				if ((taskVersion == 1) ||(new File(havaMirrorFilePath + File.separator + "mirror").exists())) {
					break;
				}
			}
			
			
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding("UTF-8");
			Document document = saxReader.read(orderXmlPath);
			// 找到增量爬取的基础文件
			List list = document.selectNodes("//controller/string/@name");

			Iterator iter = list.iterator();
			
			while (iter.hasNext()) {
				Attribute attribute = (Attribute) iter.next();
				if (attribute.getValue().equals("recover-path")) {
					Element element = (Element) attribute.getParent();
					element.setText(havaMirrorFilePath + "\\logs\\recover.gz");
					System.out.println("modifi order.xml is ok");
				}
			}

			
			OutputFormat outFmt = OutputFormat.createPrettyPrint(); 
		    outFmt.setEncoding("UTF-8"); 
			XMLWriter output = new XMLWriter(new FileWriter(orderXmlPath),outFmt);
			output.write(document);
			output.close();
		}

		catch (DocumentException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
