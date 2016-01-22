package com.gasinfo.server.webselect;

import com.gasinfo.config.Configuration;
import com.gasinfo.model.News;

import java.io.*;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.jaxen.dom4j.Dom4jXPath;
import org.jsoup.Jsoup;

public class WebSelect {

	private Document doc;
	private News news = new News();
	private Element SelectElemtent;

	public News getNews() {
		return news;
	}

	public static void main(String args[]) throws Exception {
		 new WebSelect().WebSelect("19",new File("E:\\workspacesql\\exjobs\\中国能源网\\16\\mirror\\www.china5e.com\\news\\news-930119-1.html"));
//		new WebSelect().WebSelect("5",new File("E:\\workspacesql\\exjobs\\中国国家统计局\\5\\mirror\\www.stats.gov.cn\\1.html"));
	}

	public String WebSelect(String WebId, File file) {
		try {
			this.OpenXml("WebSelectXml.xml");
			Element RootElemtent = doc.getRootElement();
			Element WebElemtent = RootElemtent.element("Web")
					.elementByID(WebId);
			SelectElemtent = WebElemtent.element("Select");
			Element ModuleElemtent = WebElemtent.element("Module");
			String webWeight = WebElemtent.element("webWeight").getTextTrim();
			// 设置网站权重
			news.setWebWeight(Integer.parseInt(webWeight));
			// 设置网站来源
			String SiteSource = WebElemtent.attributeValue("name");
			news.setSiteSource(SiteSource);
			// 设置区域
			if (SiteSource.equals("中国石油新闻网")) {
				news.setOilField("中石油");
			}
			if (SiteSource.equals("中国石化新闻网")) {
				news.setOilField("中石化");
			}
			if (SiteSource.equals("中国海油新闻网")) {
				news.setOilField("中海油");
			}

			// 得到MK
			String ModuleType = ModuleElemtent.attributeValue("ID");
			Element ModElemtent = ModuleElemtent.elementByID(ModuleType);
			if (ModuleType.equals("00")) {
				news.setMK(ModElemtent.attributeValue("MK"));
			} else if (ModuleType.equals("01")) {
				String ClassName = ModElemtent.attributeValue("ClassName");
				List<Element> nodes = ModElemtent.elements();
				for (int i = 0; i < nodes.size(); i++) {
					String Postion = nodes.get(i).attributeValue("Postion");
					String MK = nodes.get(i).attributeValue("MK");
					Boolean bool = new SelectPostion().GetPostionA(file,
							ClassName, Postion);
					if (bool) {
						news.setMK(MK);
					}
				}
			} else if (ModuleType.equals("000")) {
				
				List<Element> nodes = ModElemtent.elements();
				for (int i = 0; i < nodes.size(); i++) {
					String Postion = nodes.get(i).attributeValue("Postion");
					String MK = nodes.get(i).attributeValue("MK");
					Boolean bool = new SelectPostion().GetPostionNEW(file,
						Postion);
					if (bool) {
						news.setMK(MK);
					}
				}
			}
			else {
				String Var = ModElemtent.attributeValue("Var");
				String Value = ModElemtent.attributeValue("Value");
				List<Element> nodes = ModElemtent.elements();
				for (int i = 0; i < nodes.size(); i++) {
					String Postion = nodes.get(i).attributeValue("Postion");
					String MK = nodes.get(i).attributeValue("MK");
					Boolean bool = new SelectPostion().GetPostionB(file, Var,
							Value, Postion);
					if (bool) {
						news.setMK(MK);
					}
				}
			}
			if (news.getMK() != "") {
				if (WebId.equals("13")) {
					this.setShiYouJingJi(file);
				}
				else{
					this.getNewsMessage(file);
				}
				return "success";
			}
//			if (SiteSource.equals("中国石油新闻网") || SiteSource.equals("中国石化新闻网")
//					|| SiteSource.equals("中国海油新闻网")) {
//				news.setMK("Gzdt");
//				this.getNewsMessage(file);
//				return "success";
//			}
			// this.print();
			return "error";
		} catch (Exception e) {
			return "error";
		}
	}

	public void getNewsMessage(File file) {

		// 得到编码方式
		news.setCharset(new SelectCharset().GetCharset(file));
		// 得到标题
		String TitleType = SelectElemtent.element("SelectTitle")
				.attributeValue("ID");
		String TileTag = SelectElemtent.element("SelectTitle")
				.attributeValue("ClassName");
		if (TitleType.equals("00")) {
			news.setTitle(new SelectTitle().GetTitleA(file, 0));
		} else if (TitleType.equals("01")) {
			news.setTitle(new SelectTitle().GetTitleA(file, 1));
		} else if (TitleType.equals("03")) {
			news.setTitle(new SelectTitle().GetTitleB(file, TileTag));
		} 
		else {
			news.setTitle(new SelectTitle().GetTitleA(file, 2));
		}
		// 得到时间
		Element SelectTimeElemtent = SelectElemtent.element("SelectTime");
		String TimeType = SelectTimeElemtent.attributeValue("ID");
		Element TimeStringElemtent = SelectTimeElemtent.elementByID(TimeType);
		if (TimeType.equals("00")) {
			String ClassName = TimeStringElemtent.attributeValue("ClassName");
			news.setTime(new SelectTime().GetTimeA(file, ClassName));
		} else if (TimeType.equals("01")) {
			String TagName = TimeStringElemtent.attributeValue("TagName");
			news.setTime(new SelectTime().GetTimeB(file, TagName));
		}else if (TimeType.equals("000")) {
			String key = TimeStringElemtent.attributeValue("key");
			String value = TimeStringElemtent.attributeValue("value");
			news.setTime(new SelectTime().GetTimeNEW(file,key,value));
		}else if (TimeType.equals("77")){//国家石油和化工网的提取时间方法
			String ClassName = TimeStringElemtent.attributeValue("ClassName");
			news.setTime(new SelectTime().GetTimeCpcia(file, ClassName));
		}
		else if (TimeType.equals("99")){//国家发改委提供的新闻页面没有发布时间，以网页名字包含时间信息为标准
			
			news.setTime(new SelectTime().GetTimeD(file));
		}else if (TimeType.equals("88")){//国家发改委提供的新闻页面没有发布时间，以网页名字包含时间信息为标准
			String Var = TimeStringElemtent.attributeValue("Var");
			String Value = TimeStringElemtent.attributeValue("Value");
			news.setTime(new SelectTime().GetTimeE(file, Var, Value));
		}else if (TimeType.equals("70")){//外文网站
			String Var = TimeStringElemtent.attributeValue("Var");
			String Value = TimeStringElemtent.attributeValue("Value");
			news.setTime(new SelectTime().GetTimeF(file, Value));
		}
		else{
			String Var = TimeStringElemtent.attributeValue("Var");
			String Value = TimeStringElemtent.attributeValue("Value");
			news.setTime(new SelectTime().GetTimeC(file, Var, Value));
		}
		// 得到来源
		Element SelectSourceElemtent = SelectElemtent.element("SelectSource");

		String SourceType = SelectSourceElemtent.attributeValue("ID");
		Element SourceStringElemtent = SelectSourceElemtent
				.elementByID(SourceType);
		if (SourceType.equals("00")) {
			String ClassName = SourceStringElemtent.attributeValue("ClassName");
			news.setSource(new SelectSource().GetSourceA(file, ClassName));
		} else if (SourceType.equals("01")) {
			String TagName = SourceStringElemtent.attributeValue("TagName");
			String Var = SourceStringElemtent.attributeValue("Var");
			news.setSource(new SelectSource().GetSourceB(file, TagName, Var));
		} else if (SourceType.equals("02")) {
			String Var = SourceStringElemtent.attributeValue("Var");
			String Value = SourceStringElemtent.attributeValue("Value");
			news.setSource(new SelectSource().GetSourceC(file, Var, Value));
		}else if (SourceType.equals("000")) {
			String key = TimeStringElemtent.attributeValue("key");
			String value = TimeStringElemtent.attributeValue("value");
			news.setSource(new SelectSource().GetSourceNEW(file, key, value));
		}
		else {
			String Source = SourceStringElemtent.attributeValue("Source");
			news.setSource(new SelectSource().GetSourceD(file, Source));
		}
//		System.out.println(news.getSiteSource());
		// 得到正文
		Element SelectContentElemtent = SelectElemtent.element("SelectContent");
		String ContentType = SelectContentElemtent.attributeValue("ID");
		Element ContentStringElemtent = SelectContentElemtent
				.elementByID(ContentType);
		if (ContentType.equals("00")) {
			String ClassName = ContentStringElemtent
					.attributeValue("ClassName");
			SelectContent selectcontent = new SelectContent();
			if (news.getSiteSource().equals("中国海油新闻网"))
			{
				news.setContent(selectcontent.GetContentE(file, ClassName));
				news.setHtml(selectcontent.GetHtml2());
			}else if (news.getSiteSource().equals("沙美石油公司")){
				//沙美石油公司正文清洗入口
				news.setContent(selectcontent.GetContentF(file, ClassName));
				news.setHtml(selectcontent.GetHtml7());
				
				}else if (news.getSiteSource().equals("中国石油海外勘探开发公司")){
					//沙美石油公司正文清洗入口
					news.setContent(selectcontent.GetContentA(file, ClassName));
					news.setHtml(selectcontent.GetHtml000());
					
					}
			else{
	
			news.setContent(selectcontent.GetContentA(file, ClassName));
			news.setHtml(selectcontent.GetHtml());
			
			}
			
		} else if (ContentType.equals("01")) {
			String TagName = ContentStringElemtent.attributeValue("TagName");
			SelectContent selectcontent = new SelectContent();
			news.setContent(selectcontent.GetContentB(file, TagName));
			news.setHtml(selectcontent.GetHtml());
		} else {
			String Var = ContentStringElemtent.attributeValue("Var");
			String Value = ContentStringElemtent.attributeValue("Value");
			SelectContent selectcontent = new SelectContent();
			if(news.getSiteSource().equals("国家能源网")){
				news.setContent(selectcontent.GetContentD(file, Var, Value));	
			}else
			{
				news.setContent(selectcontent.GetContentC(file, Var, Value));
			}
		
			news.setHtml(selectcontent.GetHtml());
		}

	}

//	public void setShiYouJingJi(File file) {
//		// 得到编码方式
//		news.setCharset(new SelectCharset().GetCharset(file));
//		// 得到标题
//		String TitleType = SelectElemtent.element("SelectTitle")
//				.attributeValue("ID");
//		if (TitleType.equals("00")) {
//			news.setTitle(new SelectTitle().GetTitleA(file, 0));
//		} else if (TitleType.equals("01")) {
//			news.setTitle(new SelectTitle().GetTitleA(file, 1));
//		} else {
//			news.setTitle(new SelectTitle().GetTitleA(file, 2));
//		}
//		
//		String HTML = new GetHtmlCharset().GetHtml(file);
//		org.jsoup.nodes.Document doc;
//		doc = Jsoup.parse(HTML);
//		org.jsoup.nodes.Element elements;
//		int i, j , k = -1;
//		String title = doc.title().toString();
//		k = title.indexOf("--");
//		if (k > 0) {
//			title = title.substring(0, k);
//		}
//		String source = doc.title().toString();
//		i = source.indexOf("--");
//		source = source.substring(i + 2);
//		news.setSource(source);
//		String time = doc.title().toString();
//		for(;;){
//			i = time.indexOf("》");
//			if(i!=-1){
//				time = time.substring(i + 1);
//			}else{
//				break;
//			}
//		}
//		j = time.indexOf("年");
//		k = time.indexOf("期");
//		time = time.substring(0, j) + "-" + time.substring(j + 1 , k) + "-01";
//		news.setTime(time);
//		title = title + "——" +time;
//		news.setTitle(title);
//		elements = doc.getElementById("ty_pdf");
//		String content = elements.text().trim();
//		i = content.indexOf("中国非常规油气网www.cuog.cn-页岩气专业技术网站");
//		if(i!=-1){
//			content = content.substring(0 , i);
//		}
//		news.setContent(content);
//		String htmlcontent = elements.html();
//		i=htmlcontent.indexOf("<br />");
//		if(i!=-1){
//			htmlcontent=htmlcontent.substring(i+6);
//		}
//		j=htmlcontent.indexOf("<p align=\"center\" style=\"color:#999999;\">");
//		if(j!=-1){
//			htmlcontent=htmlcontent.substring(0,j);	
//		}
//		news.setHtml(htmlcontent);
//	}
	
	
	public void setShiYouJingJi(File file) {
		// 得到编码方式
		news.setCharset(new SelectCharset().GetCharset(file));
		// 得到标题
		if (file.getName().startsWith("CJFDTotal-GJJJ")
				&& file.getName().endsWith(".htm")){
			//先处理标题和时间和来源
			String HTML = new GetHtmlCharset().GetHtml(file);
		
			org.jsoup.nodes.Document doc;
			doc = Jsoup.parse(HTML);
			org.jsoup.nodes.Element elements;
			int i, j , k = -1;
			String title = doc.title().toString();
			
			//处理标题
			k = title.indexOf("--");
			if (k > 0) {
				title = title.substring(0, k);
			}
			
			//处理来源
			String source = doc.title().toString();
			i = source.indexOf("--");
			source = source.substring(i + 2);
			news.setSource(source);
			
			//处理时间
			String time = doc.title().toString();
			for(;;){
				i = time.indexOf("》");
				if(i!=-1){
					time = time.substring(i + 1);
				}else{
					break;
				}
			}
			j = time.indexOf("年");
			k = time.indexOf("期");
			time = time.substring(0, j) + "-" + time.substring(j + 1 , k) + "-01";
			
			news.setTime(time);
			
			//处理后的title  为了避免不同期但是同名的标题出现
			title = title + "——" +time;
			
			news.setTitle(title);
			
			//再处理正文和html正文，到这里title已经变了，在原来基础上加上了时间
			if(title.contains("价格")||title.contains("指数")||title.contains("零售限价")){
				//把标题作为正文内容给索引搜索
				news.setContent(title);
				
				String htmlcontent = "<a href="
						+ "http://www.cnki.com.cn/Article/"
						+ file.getName() + ">" + "The original download page </a>";
				
				try {
					htmlcontent = new String(htmlcontent.getBytes(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				news.setHtml(htmlcontent);
			}
			
		}

	}

	
	
	
	public void OpenXml(String xml) throws Exception {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_WebSelectXml = rc.getValue("RootURL_Xml")
				+ File.separator + xml;
		SAXReader sr = new SAXReader();
		sr.setEncoding("UTF-8");
		doc = sr.read(RootURL_WebSelectXml);
	}

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
	}

}
