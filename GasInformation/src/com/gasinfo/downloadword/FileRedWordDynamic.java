package com.gasinfo.downloadword;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

import com.gasinfo.util.News;

public class FileRedWordDynamic extends WordFile{
	
	public FileRedWordDynamic(XWPFDocument doc) {
		super(doc);
		this.redTitleString = "油气工作动态与参考";
		this.intervalTime = "2016年第01期（总第145期）";
		this.company = "国土资源部油气资源战略研究中心主办";
	}

	@Override
	protected void writeSubTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeIntervalTime(){
		XWPFParagraph headp = doc.createParagraph();
		wordSetting.setParagraphAlignInfo(headp, ParagraphAlignment.CENTER, TextAlignment.CENTER);
		wordSetting.setParagraphSpacingInfo(headp, null, null, "100", null, null, STLineSpacingRule.EXACT);
		wordSetting.setTextFontInfo(headp, false, false, this.intervalTime, "楷体_GB2312", "000000", "36",true,null, false, false, null, 0,null);
	}

	@Override
	protected void writeSubRedLineString() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void writeFileHead() throws InvalidFormatException, Exception {
		writeReadTitle();
		writeIntervalTime();
		writeCompanyAndTime();
		drawRedLine();
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
	protected void writeColumn(String column) {
		XWPFParagraph p1 = doc.createParagraph();//标题段落
		wordSetting.setParagraphSpacingInfo(p1, null, null, "150", null, null, null);
		wordSetting.setTextFontInfo(p1, false, false, column, "华文行楷", "000000", "36",true,null, false, false, null, 0,null);
		wordSetting.setParagraphAlignInfo(p1, ParagraphAlignment.LEFT, TextAlignment.BASELINE);//段落居中，文本基线对齐
		p1.setStyle("Heading1");
	}
	@Override
	protected void writeColumnPageBreak(String column) {
		XWPFParagraph p1 = doc.createParagraph();//标题段落
		p1.setPageBreak(true);
		wordSetting.setParagraphSpacingInfo(p1, null, null, "150", null, null, null);
		wordSetting.setTextFontInfo(p1, false, false, column, "华文行楷", "000000", "36",true,null, false, false, null, 0,null);
		wordSetting.setParagraphAlignInfo(p1, ParagraphAlignment.LEFT, TextAlignment.BASELINE);//段落居中，文本基线对齐
		p1.setStyle("Heading1");
	}
	@Override
	protected void writeTitle(String Title,int bookmarkID) {
		XWPFParagraph p1 = doc.createParagraph();//标题段落
		wordSetting.setParagraphSpacingInfo(p1, null, null, "100", null, null, null);
		wordSetting.setTextFontInfo(p1, false, false, Title, "华文行楷", "000000", "30",true,null, false, false, null, 0,null);
		wordSetting.setParagraphAlignInfo(p1, ParagraphAlignment.CENTER, TextAlignment.BASELINE);//段落居中，文本基线对齐
		//插入书签
		CTBookmark bookStart = p1.getCTP().addNewBookmarkStart();
		bookStart.setName("bookmark"+bookmarkID);
		bookStart.setId(BigInteger.valueOf(bookmarkID));
		CTMarkupRange bookEnd = p1.getCTP().addNewBookmarkEnd();  
        bookEnd.setId(BigInteger.valueOf(bookmarkID));
		p1.setStyle("Heading2");
	}

	@Override
	public void createCustomTOC() {
		CTSdtBlock block = doc.getDocument().getBody().addNewSdt();
		this.toc = new CustomTOC(block);
        wordSetting.setCustomHeadingStyle(doc, "Heading1", 1);
        wordSetting.setCustomHeadingStyle(doc, "Heading2", 2);
	}
	
	@Override
	public void writeAllNews(ArrayList<News> loadData) {
		String[] allTextnow;
		String allText;
		String Column = null;
		String tempColumn;
		if(loadData.size()>0){
			Column = loadData.get(0).getColumnName();
			writeColumnPageBreak(Column);
		}
		for (int i = 0; i < loadData.size(); i++) {
			tempColumn = loadData.get(i).getColumnName();
			if(!Column.endsWith(tempColumn)){
				writeColumn(tempColumn);
				Column = tempColumn;
			}
			
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
	public void addItem2TOC() {
		List<XWPFParagraph> paragraphs = doc.getParagraphs();
		for (XWPFParagraph par : paragraphs) {
            String parStyle = par.getStyle();
            if (parStyle != null && parStyle.startsWith("Heading")) {
            	List<CTBookmark> bookmarkList=par.getCTP().getBookmarkStartList();
                try {
                    int level = Integer.parseInt(parStyle.substring("Heading".length()));
                    if(level==1){
                    	toc.addRowOnlyTitle(level, par.getText());
                    }else{
                    	toc.addRow(level, par.getText(), 1, bookmarkList.get(0).getName());
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	
	
}
