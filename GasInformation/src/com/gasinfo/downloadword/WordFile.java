package com.gasinfo.downloadword;

import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;



import com.gasinfo.htmlToDoc.htmlToDoc;
import com.gasinfo.htmlToDoc.htmlToDoc2;
import com.gasinfo.util.DateConvert;
import com.gasinfo.util.News;

public abstract class WordFile {
	/**
	 * word对象
	 */
	protected XWPFDocument doc;
	/**
	 * word目录（内容列表）
	 */
	protected CustomTOC toc;
	/**
	 * word格式设置对象
	 */
	protected WordSetting wordSetting;
	/**
	 * 红头标题说明，如"油气工作动态与参考"
	 */
	protected String redTitleString;
	/**
	 * 单位信息，如"国土资源部油气资源战略研究中心主办"
	 */
	protected String company;
	/**
	 * 期号信息，如"2015年第05期（总第145期）"
	 */
	protected String intervalTime;
	/**
	 * 副标说明，如"——一周热点扫描"
	 */
	protected String subTitleString;
	/**
	 * 红线下面的文字说明，如"本　周　热　点"
	 */
	protected String subRedLineString;
	/**
	 * 时间和来源的正则表达式处理器
	 */
	protected htmlToDoc change1;
	/**
	 * 正文的正则表达式处理器
	 */
	protected htmlToDoc2 change;
	
	public WordFile(XWPFDocument doc) {
		super();
		this.doc = doc;
		this.change1 = new htmlToDoc();
		this.change = new htmlToDoc2();
		this.wordSetting = WordSetting.getInstance();
	}
	
	/**
	 * 写红头标题
	 */
	protected void writeReadTitle(){
		XWPFParagraph headp = doc.createParagraph();
		wordSetting.setParagraphAlignInfo(headp, ParagraphAlignment.CENTER, TextAlignment.CENTER);
		wordSetting.setTextFontInfo(headp, false, false, this.redTitleString, "宋体", "ff0000", "98",true,null, false, false, null, 0,null);
	}
	
	/**
	 * 写副标题
	 */
	protected abstract void writeSubTitle();
	/**
	 * 写期别
	 */
	protected abstract void writeIntervalTime();
	
	/**
	 * 写单位和时间
	 */
	protected void writeCompanyAndTime(){
		XWPFParagraph headp = doc.createParagraph();
		wordSetting.setParagraphAlignInfo(headp, ParagraphAlignment.CENTER, TextAlignment.CENTER);
		wordSetting.setParagraphSpacingInfo(headp, null, null, "100", null, null, STLineSpacingRule.EXACT);
		wordSetting.setTextFontInfo(headp, false, false, this.company+"\t"+DateConvert.getDate(), "华文中宋", "000000", "32",false,null, false, false, null, 0,null);
       
	}
	/**
	 * 画红线
	 * @throws Exception 
	 * @throws InvalidFormatException 
	 */
	protected void drawRedLine() throws InvalidFormatException, Exception{
		XWPFParagraph headp = doc.createParagraph();
        wordSetting.setParagraphSpacingInfo(headp, null, null, "100", "200", "400", STLineSpacingRule.EXACT);
        wordSetting.setParagraphAlignInfo(headp, ParagraphAlignment.CENTER, null);
        String imagePath =  this.getClass().getClassLoader().getResource("RedLine.gif").getPath();
        wordSetting.createPicture(imagePath,doc.getNextPicNameNumber(Document.PICTURE_TYPE_PNG), headp);
	}
	/**
	 * 写红线下面的字
	 */
	protected abstract void writeSubRedLineString();
	/**
	 * 写文件红头
	 * @throws Exception 
	 * @throws InvalidFormatException 
	 */
	public abstract void writeFileHead() throws InvalidFormatException, Exception;
	/**
	 * 写文章分栏(一级标题)
	 */
	protected abstract void writeColumn(String column);
	/**
	 * 另起一页，写文章分栏(一级标题)
	 */
	protected abstract void writeColumnPageBreak(String column);
	/**
	 * 写文章标题(二级标题),文章标题如要插入目录，需要在标题处插入书签
	 */
	protected abstract void writeTitle(String Title,int bookmarkID);
	
	/**
	 * 写文章正文
	 */
	protected abstract void writeContent(String str[]);
	
	/**
	 * 写来源或时间
	 */
	protected abstract void writeSourceOrTime(String sourceOrTime);
	/**
	 * 写所有的新闻消息
	 */
	public abstract void writeAllNews(ArrayList<News> loadData);
	
	/**
	 * 创建目录
	 */
	public abstract void createCustomTOC();
	
	/**
	 * 向目录中添加条目
	 */
	public abstract void addItem2TOC();
	
}
