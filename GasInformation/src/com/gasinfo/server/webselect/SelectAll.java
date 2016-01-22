package com.gasinfo.server.webselect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.gasinfo.config.Configuration;
import com.gasinfo.model.News;
import com.gasinfo.server.indexdatebase.Checker;

public class SelectAll {

	private Document doc;
	private String strNextURL;
	private ArrayList<String> filesList = new ArrayList<String>();
	private News news = new News();
	private Checker selectCheker = new Checker();
	public static void main(String args[]) throws Exception {
		//前一个参数是WebID，后一个参数是taskVersion
		new SelectAll().SelectAll("21",16);
	}
	
	
	public void SelectAll(String WebId, int taskVersion) throws Exception {
		this.CreatFile();
		this.OpenXml("WebSelectXml.xml");
		Element RootElemtent = doc.getRootElement();
		Element WebElemtent = RootElemtent.element("Web").elementByID(WebId);
		Configuration rc = new Configuration("daoconfig.properties");
		String PrePostion = rc.getValue("RootURL_exjobs");
		PrePostion = PrePostion + File.separator
				+ WebElemtent.attributeValue("name").trim();
		PrePostion = PrePostion + File.separator+taskVersion+File.separator
				+ WebElemtent.element("PrePostion").getTextTrim();
		this.FileList(PrePostion);
		int No[] = new int[12];
		for (int i = 0; i < filesList.size(); i++) {
			File file = new File(filesList.get(i));
			if (file.exists()
					&& (file.getName().endsWith(".shtml")
							|| file.getName().endsWith(".html") || file
							.getName().endsWith(".htm") ||file.getName().endsWith(".jsp"))) {
				WebSelect webselect = new WebSelect();
				if (webselect.WebSelect(WebId, file).equals("success")) {
					news = webselect.getNews();
					String MK = news.getMK();
					if (MK.equals("Gzdt")) {
						No[0]++;
						this.OutFile(No[0]);
					} else if (MK.equals("Gzdt")) {
						No[1]++;
						this.OutFile(No[1]);
					} else if (MK.equals("Kcgl")) {
						No[2]++;
						this.OutFile(No[2]);
					} else if (MK.equals("Zcfg")) {
						No[3]++;
						this.OutFile(No[3]);
					} else if (MK.equals("Yzrd")) {
						No[4]++;
						this.OutFile(No[4]);
					} else if (MK.equals("Gjhz")) {
						No[5]++;
						this.OutFile(No[5]);
					} else if (MK.equals("Kjjz")) {
						No[6]++;
						this.OutFile(No[6]);
					} else if (MK.equals("Tpxw")) {
						No[7]++;
						this.OutFile(No[7]);
					} else if (MK.equals("Lddt")) {
						No[8]++;
						this.OutFile(No[8]);
					} else if (MK.equals("Tjsj")) {
						No[9]++;
						this.OutFile(No[9]);
					} else if (MK.equals("Ktkf")) {
						No[10]++;
						this.OutFile(No[10]);
					} else if (MK.equals("Yjxx")) {
						No[11]++;
						this.OutFile(No[11]);
					}
//					this.print();
				}
			}
		}
	}

	public void OutFile(int No) {
		//删选规则
		if(selectCheker.check(news.getTitle(),news.getTime(),news.getSource(),news.getHtml())){
			this.Writer(strNextURL + File.separator + news.getMK() + File.separator
					+ No + "html.html", news.getHtml());
			this.Writer(strNextURL + File.separator + news.getMK() + File.separator
					+ No + "content.txt", news.getContent());
			this.Writer(strNextURL + File.separator + news.getMK() + File.separator
					+ No + "message.txt",
					news.getTitle() + "|" + news.getTime() + "|" + news.getSource()
							+ "|" + news.getCharset() + "|" + news.getWebWeight()
							+ "|" + news.getSiteSource() + "|" + news.getOilField());
			this.print();
		}
		
	}

	// 得到文件夹下的所有的文档路径
	public void FileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();
		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				FileList(files[i].getAbsolutePath());
			} else {
				String strFileName = files[i].getAbsolutePath().toLowerCase();
				filesList.add(files[i].getAbsolutePath());
			}
		}

	}

	// 创建exSelectedWeb文件夹
	public void CreatFile() {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_exSelectedWeb = rc.getValue("RootURL_exSelectedWeb");
		Date datenow = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDateNow = dateformat.format(datenow);
		strNextURL = RootURL_exSelectedWeb + File.separator + strDateNow;
		File file = new File(strNextURL);
		file.mkdirs();
		new File(strNextURL + File.separator + "Gzdt").mkdir();
		new File(strNextURL + File.separator + "Kcgl").mkdir();
		new File(strNextURL + File.separator + "Zcfg").mkdir();
		new File(strNextURL + File.separator + "Yzrd").mkdir();
		new File(strNextURL + File.separator + "Gjhz").mkdir();
		new File(strNextURL + File.separator + "Kjjz").mkdir();
		new File(strNextURL + File.separator + "Tpxw").mkdir();
		new File(strNextURL + File.separator + "Lddt").mkdir();
		new File(strNextURL + File.separator + "Tjsj").mkdir();
		new File(strNextURL + File.separator + "Ktkf").mkdir();
		new File(strNextURL + File.separator + "Yjxx").mkdir();
	}

	// 读取XML
	public void OpenXml(String xml) throws Exception {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")
				+ File.separator + xml;
		SAXReader sr = new SAXReader();
		doc = sr.read(RootURL_WebSelectXml);
	}

	// 输出文档
	void Writer(String path, String string) {
		try {
			BufferedWriter bwr;
			try {
				bwr = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(new File(path)), "UTF-8"));
				try {
					bwr.write(string);
					bwr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void print() {
		System.out.println(news.getTitle());
		System.out.println("Title*****************************************");
		System.out.println(news.getSource());
		System.out.println("Source*****************************************");
		System.out.println(news.getTime());
		System.out.println("Time*****************************************");
		System.out.println(news.getContent());
		System.out.println("Content*****************************************");
		System.out.println(news.getMK());
		System.out.println("MK*****************************************");
		System.out.println(news.getCharset());
		System.out.println("Charset*****************************************");
		System.out.println(news.getHtml());
		System.out.println("Html*****************************************");
		System.out.println(news.getWebWeight());
		System.out.println("WebWeight*****************************************");
		System.out.println(news.getOilField());
		System.out.println("OilField*****************************************");
		System.out.println(news.getSiteSource());
		System.out.println("SiteSource*****************************************");
	}
}
