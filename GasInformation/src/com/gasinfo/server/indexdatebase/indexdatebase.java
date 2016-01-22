package com.gasinfo.server.indexdatebase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import com.gasinfo.config.Configuration;
import com.gasinfo.model.News;
import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Newsdao;

public class indexdatebase {

	private News news = new News();
	private IndexWriter Allindexwrite;
	private Checker checker = new Checker();
	private ArrayList<String> listAllWords = new ArrayList<String>();
	private ArrayList<String> listOilWords = new ArrayList<String>();
	private ArrayList<String> listCompanyWords = new ArrayList<String>();
	private ArrayList<String> listBasinWords = new ArrayList<String>();
	private ArrayList<String> listBlackWords = new ArrayList<String>();
	//关键词词集
	private ArrayList<String> listWebKeyWords = new ArrayList<String>();
	public indexdatebase() {
		try {
			Words words = new Words();
			listAllWords = words.getAllWords();
			listOilWords = words.getWords("Oil");
			listCompanyWords = words.getWords("Company");
			listBasinWords = words.getWords("Basin");
			listBlackWords = words.getWords("Blacklist");
			//获取相应节点下的关键词
//			listWebKeyWords = words.getWebKeyWords(news.getSiteSource().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		try {
			new indexdatebase().indexAnddatebase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 石油模块建索引和入库
	public void indexAnddatebase() throws Exception {
		this.CreatIndexFile();
		this.DeleteLock();
		//integratedSecurity=TRUE;
		this.EnterIndexDateBase("Gzdt");
		this.EnterIndexDateBase("Kcgl");
		this.EnterIndexDateBase("Zcfg");
		this.EnterIndexDateBase("Yzrd");
		this.EnterIndexDateBase("Gjhz");
		this.EnterIndexDateBase("Kjjz");
		// this.EnterIndexDateBase("Tpxw");
		this.EnterIndexDateBase("Lddt");
		this.EnterIndexDateBase("Tjsj");
		this.EnterIndexDateBase("Ktkf");
		this.EnterIndexDateBase("Yjxx");
	}
	
	// 各个模块建索引和入库
	public void EnterIndexDateBase(String MK) throws Exception {

		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_exIndex = rc.getValue("RootURL_exIndex");
		String NextURL = "";
		if (MK.equals("Gzdt")) {
			NextURL = RootURL_exIndex + File.separator + "GongZuoDongTaiIndex";
		}
		if (MK.equals("Gjhz")) {
			NextURL = RootURL_exIndex + File.separator + "GuoJiHeZuoIndex";
		}
		if (MK.equals("Ktkf")) {
			NextURL = RootURL_exIndex + File.separator + "KanTanKaiFaIndex";
		}
		if (MK.equals("Kjjz")) {
			NextURL = RootURL_exIndex + File.separator + "KeJiJinZhanIndex";
		}
		if (MK.equals("Kcgl")) {
			NextURL = RootURL_exIndex + File.separator + "KuangChanGuanLiIndex";
		}
		if (MK.equals("Lddt")) {
			NextURL = RootURL_exIndex + File.separator + "LingDaoDongTaiIndex";
		}
		if (MK.equals("Tjsj")) {
			NextURL = RootURL_exIndex + File.separator + "TongJiShuJuIndex";
		}
		if (MK.equals("Tpxw")) {
			NextURL = RootURL_exIndex + File.separator + "TuPianXinWenIndex";
		}
		if (MK.equals("Yzrd")) {
			NextURL = RootURL_exIndex + File.separator + "YiZouReDianIndex";
		}
		if (MK.equals("Yjxx")) {
			NextURL = RootURL_exIndex + File.separator + "YouJiaXinXiIndex";
		}
		if (MK.equals("Zcfg")) {
			NextURL = RootURL_exIndex + File.separator + "ZhengCeFaGuiIndex";
		}

		String NextAllURL = RootURL_exIndex + File.separator + "IndexAll";
		String PreURL = this.GetURL();
		PreURL = PreURL + File.separator + MK;
		IndexWriter indexwrite;
		File file = new File(PreURL);
		if (new File(NextURL).listFiles().length > 0) {
			indexwrite = new IndexWriter(NextURL, new MMAnalyzer(), false);
		} else {
			indexwrite = new IndexWriter(NextURL, new MMAnalyzer(), true);
		}
		if (new File(NextAllURL).listFiles().length > 0) {
			Allindexwrite = new IndexWriter(NextAllURL, new MMAnalyzer(), false);
		} else {
			Allindexwrite = new IndexWriter(NextAllURL, new MMAnalyzer(), true);
		}
		//新加入的代码
		
		MyFilenameFilter myFilter = new MyFilenameFilter("html");
        String[] htmlFilesName = file.list(myFilter);
        myFilter = new MyFilenameFilter("content");
        String[] contentFilesName = file.list(myFilter);
        myFilter = new MyFilenameFilter("message");
        String[] messageFilesName = file.list(myFilter);
        for(int i=0;i<htmlFilesName.length;i++){
			String contentpath = PreURL + File.separator + contentFilesName[i];
			String messagepath = PreURL + File.separator + messageFilesName[i];
			String htmlpath = PreURL + File.separator + htmlFilesName[i];
        	try {
				this.SetValue(contentpath,messagepath , htmlpath);
			} catch (Exception e) {
				continue;
			}
        	//得到关键词集
        	Words WebKeywords = new Words();
    		listWebKeyWords = WebKeywords.getWebKeyWords(news.getSiteSource().trim());

			if (checker.check(news)&& this.NotContain(news.getTitle(), listBlackWords)) {
				if (this.Contain(news.getTitle(), listAllWords)||this.KeyWordsCheck(news.getTitle(), listWebKeyWords)) {
					news.setOutput("1");
				} else {
					news.setOutput("0");
				}
				this.SetOilCompanyBasin(news.getTitle(), listOilWords, "Oil");
				this.SetOilCompanyBasin(news.getTitle(), listCompanyWords,
						"Company");
				this.SetOilCompanyBasin(news.getTitle(), listBasinWords,
						"Basin");

				Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
				com.gasinfo.util.News jdbcNews = new com.gasinfo.util.News();

				jdbcNews.setTitle(news.getTitle());
				jdbcNews.setSiteSource(news.getSiteSource());
				jdbcNews.setOriginSource(news.getSource());
				jdbcNews.setPubTime(news.getTime());
				jdbcNews.setContent(news.getHtml());
				jdbcNews.setOilField(news.getOilField());
				jdbcNews.setWebWeight(news.getWebWeight());
				jdbcNews.setCharset(news.getCharset());
				jdbcNews.setBasin(news.getBasin());
				jdbcNews.setOil(news.getOil());
				jdbcNews.setCompany(news.getCompany());
				jdbcNews.setOutput(Integer.valueOf(news.getOutput()));
				
				if (MK.equals("Gzdt")) {
					newsdao.addNews(jdbcNews, 1);
				} else if (MK.equals("Kcgl")) {
					newsdao.addNews(jdbcNews, 2);
				} else if (MK.equals("Zcfg")) {
					newsdao.addNews(jdbcNews, 3);
				} else if (MK.equals("Yzrd")) {
					newsdao.addNews(jdbcNews, 4);
				} else if (MK.equals("Gjhz")) {
					newsdao.addNews(jdbcNews, 5);
				} else if (MK.equals("Kjjz")) {
					newsdao.addNews(jdbcNews, 6);
				} else if (MK.equals("Lddt")) {
					newsdao.addNews(jdbcNews, 8);
				} else if (MK.equals("Tjsj")) {
					newsdao.addNews(jdbcNews, 9);
				} else if (MK.equals("Ktkf")) {
					newsdao.addNews(jdbcNews, 10);
				} else if (MK.equals("Yjxx")) {
					newsdao.addNews(jdbcNews, 11);
				}

				this.AddDocuments(indexwrite);
				this.AddDocuments(Allindexwrite);
			}
        }

		indexwrite.close();
		Allindexwrite.close();
	}

	// 如果没有索引文件夹，就创建索引文件夹
	public void CreatIndexFile() {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_exIndex = rc.getValue("RootURL_exIndex");
		if (!new File(RootURL_exIndex).exists()) {
			new File(RootURL_exIndex + File.separator + "AllIndex").mkdirs();
			new File(RootURL_exIndex + File.separator + "GongZuoDongTaiIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "KuangChanGuanLiIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "ZhengCeFaGuiIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "YiZouReDianIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "GuoJiHeZuoIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "KeJiJinZhanIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "TuPianXinWenIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "LingDaoDongTaiIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "TongJiShuJuIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "KanTanKaiFaIndex")
					.mkdirs();
			new File(RootURL_exIndex + File.separator + "YouJiaXinXiIndex")
					.mkdirs();
		}
	}

	// 得到exSelectWeb最新文件夹位置
	public String GetURL() {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_exSelectedWeb = rc.getValue("RootURL_exSelectedWeb");
		File Rootfile = new File(RootURL_exSelectedWeb);
		File[] files = Rootfile.listFiles();
		ArrayList<Long> FileNameList = new ArrayList<Long>();
		for (int i = 0; i < files.length; i++) {
			FileNameList.add(Long.valueOf(files[i].getName()));
		}
		Long MaxName = FileNameList.get(0);
		for (int i = 1; i < FileNameList.size(); i++) {
			if (MaxName < FileNameList.get(i)) {
				MaxName = FileNameList.get(i);
			}
		}
		return RootURL_exSelectedWeb + File.separator + MaxName;

	}

	// 将news值建索引
	public void AddDocuments(IndexWriter contentwriter) throws Exception {

		// BufferedReader bis = new BufferedReader(new InputStreamReader(
		// new FileInputStream(new File(RootURL_exSelectedWeb
		// + "\\words.txt"))));
		// MMAnalyzer.addDictionary(bis);

		Document doc = new Document();
		Field title_field = new Field("title", news.getTitle(),
				Field.Store.YES, Field.Index.TOKENIZED);
		Field content_field = new Field("content", news.getContent(),
				Field.Store.NO, Field.Index.TOKENIZED);
		Field html_field = new Field("html", news.getHtml(), Field.Store.YES,
				Field.Index.UN_TOKENIZED);
		Field source_field = new Field("source", news.getSource(),
				Field.Store.YES, Field.Index.UN_TOKENIZED);
		Field time_field = new Field("time", news.getTime(), Field.Store.YES,
				Field.Index.UN_TOKENIZED);
		doc.add(title_field);
		doc.add(content_field);
		doc.add(html_field);
		doc.add(source_field);
		doc.add(time_field);

		contentwriter.addDocument(doc);
	}

	// 给news赋值
	public void SetValue(String contentpath, String messagepath, String htmlpath) {
		news = new News();
		int i;
		String message;
		File contentfile = new File(contentpath);
		File messagefile = new File(messagepath);
		File htmlfile = new File(htmlpath);
		news.setContent(ReadFile(contentfile));
		news.setHtml(ReadFile(htmlfile));
		message = ReadFile(messagefile);
		i = message.indexOf('|');
		news.setTitle(message.substring(0, i));

		message = message.substring(i + 1);
		i = message.indexOf('|');
		news.setTime(message.substring(0, i));

		message = message.substring(i + 1);
		i = message.indexOf('|');
		news.setSource(message.substring(0, i));

		message = message.substring(i + 1);
		i = message.indexOf('|');
		news.setCharset(message.substring(0, i));

		message = message.substring(i + 1);
		i = message.indexOf('|');
		news.setWebWeight(Integer.parseInt(message.substring(0, i)));

		message = message.substring(i + 1);
		i = message.indexOf('|');
		news.setSiteSource(message.substring(0, i));

		message = message.substring(i + 1).trim();
		news.setOilField(message);
		this.print();
	}

	// 读取文件数据
	String ReadFile(File file) {
		try {
//			BufferedReader bis = new BufferedReader(new InputStreamReader(
//					new FileInputStream(file)));
//			BufferedReader bis = new BufferedReader(
//					new FileReader(file));
			BufferedReader bis = new BufferedReader(new InputStreamReader(
					new FileInputStream(file),"UTF-8"));

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

	// 删除Lock文件
	public void DeleteLock() {
		Configuration rc = new Configuration("daoconfig.properties");
		String rootURL = rc.getValue("RootURL_exIndex");

		File file_IndexALL = new File(rootURL + "\\IndexAll\\write.lock");
		if (file_IndexALL.exists()) {
			file_IndexALL.delete();
		}

		File file_GongZuoDongTaiIndex = new File(rootURL
				+ "\\GongZuoDongTaiIndex\\write.lock");
		if (file_GongZuoDongTaiIndex.exists()) {
			file_GongZuoDongTaiIndex.delete();
		}

		File file_GuoJiHeZuoIndex = new File(rootURL
				+ "\\GuoJiHeZuoIndex\\write.lock");
		if (file_GuoJiHeZuoIndex.exists()) {
			file_GuoJiHeZuoIndex.delete();
		}

		File file_KanTanKaiFaIndex = new File(rootURL
				+ "\\KanTanKaiFaIndex\\write.lock");
		if (file_KanTanKaiFaIndex.exists()) {
			file_KanTanKaiFaIndex.delete();
		}

		File file_KeJiJinZhanIndex = new File(rootURL
				+ "\\KeJiJinZhanIndex\\write.lock");
		if (file_KeJiJinZhanIndex.exists()) {
			file_KeJiJinZhanIndex.delete();
		}

		File file_KuangChanGuanLiIndex = new File(rootURL
				+ "\\KuangChanGuanLiIndex\\write.lock");
		if (file_KuangChanGuanLiIndex.exists()) {
			file_KuangChanGuanLiIndex.delete();
		}

		File file_LingDaoDongTaiIndex = new File(rootURL
				+ "\\LingDaoDongTaiIndex\\write.lock");
		if (file_LingDaoDongTaiIndex.exists()) {
			file_LingDaoDongTaiIndex.delete();
		}

		File file_TongJiShuJuIndex = new File(rootURL
				+ "\\TongJiShuJuIndex\\write.lock");
		if (file_TongJiShuJuIndex.exists()) {
			file_TongJiShuJuIndex.delete();
		}

		File file_TuPianXinWenIndex = new File(rootURL
				+ "\\TuPianXinWenIndex\\write.lock");
		if (file_TuPianXinWenIndex.exists()) {
			file_TuPianXinWenIndex.delete();
		}

		File file_YiZouReDianIndex = new File(rootURL
				+ "\\YiZouReDianIndex\\write.lock");
		if (file_YiZouReDianIndex.exists()) {
			file_YiZouReDianIndex.delete();
		}

		File file_YouJiaXinXiIndex = new File(rootURL
				+ "\\YouJiaXinXiIndex\\write.lock");
		if (file_YouJiaXinXiIndex.exists()) {
			file_YouJiaXinXiIndex.delete();
		}

		File file_ZhengCeFaGuiIndex = new File(rootURL
				+ "\\ZhengCeFaGuiIndex\\write.lock");
		if (file_ZhengCeFaGuiIndex.exists()) {
			file_ZhengCeFaGuiIndex.delete();
		}

	}

	// 给 oil;company;basin;赋值
	public void SetOilCompanyBasin(String Content, ArrayList<String> list,
			String Area) {

		for (int i = 0; i < list.size(); i++) {
			if (Content.indexOf(list.get(i).toString()) != -1) {
				if (Area.equals("Oil")) {
					news.setOil(list.get(i).toString());
				}
				if (Area.equals("Company")) {
					news.setCompany(list.get(i).toString());
				}
				if (Area.equals("Basin")) {
					news.setBasin(list.get(i).toString());
				}
				break;
			}
		}
	}

	// 判断是否符合词属性
	public boolean Contain(String Content, ArrayList<String> list) {
		boolean bool = false;
		for (int i = 0; i < list.size(); i++) {
			if (Content.indexOf(list.get(i).toString()) != -1) {
				bool = true;
				break;
			}
		}
		return bool;
	}
	//以网站为单位关键词筛选功能：关键词筛选检测方法
		public boolean KeyWordsCheck(String title , ArrayList<String> listWebKeyWords) {
			boolean bool = false;
			for (int i = 0; i < listWebKeyWords.size(); i++) {
				if (title.indexOf(listWebKeyWords.get(i).toString()) != -1) {
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

	// 打印数据
	public void print() {
		System.out.println(news.getTitle());
		System.out.println("标题↑*****************************************");
		System.out.println(news.getSource());
		System.out.println("来源↑*****************************************");
		System.out.println(news.getTime());
		System.out.println("时间↑*****************************************");
		System.out.println(news.getContent());
		System.out.println("正文↑*****************************************");
		System.out.println(news.getMK());
		System.out.println("模块↑*****************************************");
		System.out.println(news.getCharset());
		System.out.println("编码↑*****************************************");
		System.out.println(news.getHtml());
		System.out.println("HTML↑*****************************************");
		System.out.println(news.getWebWeight());
		System.out.println("网站权重↑*****************************************");
		System.out.println(news.getSiteSource());
		System.out.println("来源↑*****************************************");
		System.out.println(news.getOilField());
		System.out.println("油田↑*****************************************");
	}
}
