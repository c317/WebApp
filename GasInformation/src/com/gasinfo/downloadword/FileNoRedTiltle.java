package com.gasinfo.downloadword;

import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

import com.gasinfo.util.News;

public class FileNoRedTiltle extends WordFile{
	
	public FileNoRedTiltle(XWPFDocument doc) {
		super(doc);
	}

	@Override
	protected void writeSubTitle() {
		// 空实现即可
	}
	
	@Override
	protected void writeIntervalTime() {
		// 空实现即可
	}

	@Override
	protected void writeSubRedLineString() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void writeFileHead() throws InvalidFormatException, Exception {
		// 空实现即可
	}

	@Override
	protected void writeContent(String[] str) {
		for(int j=0;j<str.length;j++){//将正文中的每段依次填写在p2中
			if(str[j].trim().length()>1){
				if(str[j].trim().codePointAt(0)==12288){//识别已有的缩进，缩进为非java表示的空格或制表符，只能通过Ascll识别
					str[j] = str[j].substring(2);//取消已有的缩进符
				}
				XWPFParagraph p = doc.createParagraph();//正文段落
				wordSetting.setTextFontInfo(p, false, false, str[j].trim(), "宋体", "000000", "21",false, null, false, false, null, 0,null);
				wordSetting.setParagraphIndInfo(p, "440", "200", null, null, null, null, null, null);
				wordSetting.setParagraphAlignInfo(p, ParagraphAlignment.BOTH, TextAlignment.BASELINE);//段落两端对齐，文本基线对齐
				wordSetting.setParagraphSpacingInfo(p, null, null, null, null, "400", STLineSpacingRule.EXACT);
			}
		}
	}

	@Override
	protected void writeSourceOrTime(String sourceOrTime) {
		XWPFParagraph p = doc.createParagraph();//正文段落
		wordSetting.setTextFontInfo(p, false, false, sourceOrTime, "宋体", "000000", "21",false, null, false, false, null, 0,null);
		wordSetting.setParagraphAlignInfo(p, ParagraphAlignment.RIGHT, TextAlignment.BASELINE);//段落两端对齐，文本基线对齐
	}

	@Override
	protected void writeTitle(String Title,int bookmarkID) {
		XWPFParagraph p1 = doc.createParagraph();//标题段落
		wordSetting.setParagraphSpacingInfo(p1, null, null, "100", null, null, null);
		wordSetting.setTextFontInfo(p1, false, false, Title, "宋体", "000000", "24",true,null, false, false, null, 0,null);
		wordSetting.setParagraphAlignInfo(p1, ParagraphAlignment.CENTER, TextAlignment.BASELINE);//段落居中，文本基线对齐
	}

	@Override
	public void createCustomTOC() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addItem2TOC() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeColumn(String column) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeAllNews(ArrayList<News> loadData) {
		String[] allTextnow;
		String allText;
		for (int i = 0; i < loadData.size(); i++) {
						
			allText = loadData.get(i).getContent();
			
			allTextnow = change.htmlToText(allText);
			
			//写标题
			writeTitle(loadData.get(i).getTitle(),i);
			
			//写正文
			writeContent(allTextnow);
			
			//写来源
			writeSourceOrTime(change1.htmlToText(loadData.get(i).getOriginSource()));
			
			//时间
			writeSourceOrTime(change1.htmlToText(loadData.get(i).getPubTime()));
		}
	}

	@Override
	protected void writeColumnPageBreak(String column) {
		
	}

}
