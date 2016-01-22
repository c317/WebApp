package com.gasinfo.downloadword;

import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import com.gasinfo.htmlToDoc.htmlToDoc;
import com.gasinfo.htmlToDoc.htmlToDoc2;
import com.gasinfo.util.News;

public class FileHandle {
	private  static final FileHandle FILE_HANDLE = new FileHandle();
	
	private FileHandle(){
		
	}
	
	public static FileHandle getInstance(){
		return FILE_HANDLE;
	}
	
	public void write2File(ArrayList<News> loadData,OutputStream outputsream,int moduleId,int fileType) throws Exception{
		if (fileType == Constants.FILETYPE_HTML) {
			write2HTML(loadData, outputsream, moduleId);
		}else if(fileType == Constants.FILETYPE_DOCX){
			write2Docx(loadData, outputsream, moduleId);
		}else if(fileType == Constants.FILETYPE_EXCEL){
			write2Excel(loadData, outputsream, moduleId);
		}
	}
	
	private void write2HTML(ArrayList<News> loadData,OutputStream outputsream,int moduleId) throws Exception{
		StringBuffer sb=new StringBuffer();
		sb.append("<html>"); 
    	sb.append("<head>"); 
    	sb.append("<title>HTML格式新闻</title>"); 
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"); 
    	sb.append("<style type=\"text/css\">.indent{text-indent: 2em;}</style>"); 
    	sb.append("</head>"); 
    	sb.append("<body>");
        for (int i = 0; i < loadData.size(); i++) {
        	sb.append("<h4 align=\"center\">"+loadData.get(i).getTitle()+"</h4>");
        	String allText = loadData.get(i).getContent();
        	sb.append(allText);
        	sb.append("<p align=\"right\">"+loadData.get(i).getOriginSource()+"</p>");
        	sb.append("<p align=\"right\">"+loadData.get(i).getPubTime()+"</p>");
        }
        sb.append("</body></html>");
        outputsream.write(sb.toString().getBytes("UTF-8"));//注意需要转换对应的字符集
		outputsream.flush();
		outputsream.close();  
		outputsream=null;
	}
	
	private void write2Docx(ArrayList<News> loadData,OutputStream outputsream,int moduleId) throws Exception{
				
		XWPFDocument doc = new XWPFDocument();
		WordSetting.getInstance().setDocumentMargin(doc, "1531", "1440", "1531", "1440");//设置页边距
		WordSetting.getInstance().setDocumentGrid(doc);
		//写红头
		WordFile wordFile = null;
		
		if (moduleId == 12) {// 如果是一周热点参考则加入红头
			wordFile = new FileRedWeekHots(doc);
		} else if (moduleId == 13) {// 如果是工作动态参考则加入红头
			wordFile = new FileRedWordDynamic(doc);
		}else{
			wordFile = new FileNoRedTiltle(doc);    
		}
		wordFile.writeFileHead();
		wordFile.createCustomTOC();
		wordFile.writeAllNews(loadData);
	    wordFile.addItem2TOC();
		doc.write(outputsream);
		outputsream.flush();
		outputsream.close();  
		outputsream=null;
	}
	
	private void write2Excel(ArrayList<News> loadData,OutputStream outputsream,int moduleId) throws Exception{
		
		HSSFWorkbook hwb = new HSSFWorkbook();
		HSSFSheet hsheet = hwb.createSheet();
		int rowNum = hsheet.getLastRowNum(); 
		HSSFRow hrow = hsheet.getRow(rowNum);
		
		if(rowNum==0){
			hrow = hsheet.createRow(rowNum);
			hrow.createCell(0);
	        hrow.getCell(0).setCellValue("title");
	        hrow.createCell(1);
	        hrow.getCell(1).setCellValue("siteSource");
	        hrow.createCell(2);
	        hrow.getCell(2).setCellValue("originSource");
	        hrow.createCell(3);
	        hrow.getCell(3).setCellValue("pubTime");
	        hrow.createCell(4);
	        hrow.getCell(4).setCellValue("content");
		}
                
		for (int i = 0; i < loadData.size(); i++) {
			hsheet.createRow(i+1);
			hrow = hsheet.getRow(i+1);
			hrow.createCell(0);
			hrow.getCell(0).setCellValue(loadData.get(i).getTitle());
			hrow.createCell(1);
			hrow.getCell(1).setCellValue(loadData.get(i).getSiteSource());
			hrow.createCell(2);
			hrow.getCell(2).setCellValue(loadData.get(i).getOriginSource());
			hrow.createCell(3);
			hrow.getCell(3).setCellValue(loadData.get(i).getPubTime());
			hrow.createCell(4);
			hrow.getCell(4).setCellValue(loadData.get(i).getContent());
		}
	    
	    hwb.write(outputsream);
		outputsream.flush();
		outputsream.close();  
		outputsream=null;
	}
}
