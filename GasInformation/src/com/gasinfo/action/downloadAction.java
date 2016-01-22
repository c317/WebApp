package com.gasinfo.action;

import com.gasinfo.downloadword.Constants;
import com.gasinfo.downloadword.FileHandle;
import com.gasinfo.downloadword.FileNoRedTiltle;
import com.gasinfo.downloadword.FileRedWeekHots;
import com.gasinfo.downloadword.FileRedWordDynamic;
import com.gasinfo.downloadword.SearchNewsConvert;
import com.gasinfo.downloadword.WordFile;
import com.gasinfo.downloadword.WordSetting;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import com.gasinfo.htmlToDoc.htmlToDoc;
import com.gasinfo.htmlToDoc.htmlToDoc2;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.News;
import com.gasinfo.util.Newsdao;
import com.gasinfo.util.PageRoll;
import com.opensymphony.xwork2.ActionContext;
/**
 * @author 刘挺  
 * 
 * 操作docx文档需要的jar包有6个：dom4j.jar,poi-ooxml-3.10-FINA.jar,
 * poi-ooxml-schemas-3.10-FINA.jar,xmlbean-2.3.0.jar,commons-lang.jar以及ooxml-schemas-1.1.jar
 * 操作doc文档需要的jar包有poi-3.10-FINAL.jar,poi-scratchpad-3.10-FINA.jar
 * 其中操作doc文档对模板的依赖很大，功能相对操作docx的接口很弱
 * @author Administrator
 *
 */

public class downloadAction implements ServletRequestAware, Action{
	private HttpServletRequest request;
	
	//将Struts2的response的调出来
	ActionContext ac = ActionContext.getContext();
	HttpServletResponse response = (HttpServletResponse) ac.get(StrutsStatics.HTTP_RESPONSE);
	
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	
	/**
	 * 采用HWPF的接口编写，套用模板
	 * 因接口功能很少，c此法已经弃用，转用XWPF接口，即downloadSingle方法
	 */
	public String downloadSingleHWPF() throws IOException {
		
		int moduleId  = Integer.valueOf(request.getParameter("moduleId"));
		int newsId = Integer.valueOf(request.getParameter("newsId"));

		//从数据库里取出数据
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		News down =newsdao.findById(newsId, moduleId);
		htmlToDoc change = new htmlToDoc();
		String allText = down.getContent();
		String befText = null;
		String[] str = null;
		//对获取的正文内容进行处理
		befText = change.htmlToText(allText).substring(0, change.htmlToText(allText).indexOf(")")+1);
 		allText = change.htmlToText(allText).substring(change.htmlToText(allText).indexOf(")")+1);
		str = allText.split(" ");
		int j=0;
		String allText1="";
		for(j=0;j<str.length;j++){
			if(str[j].length()>1){
				allText1 +=str[j]+"\r";
			}
		}
		allText1 =befText + allText1;
		
		String templatePath = this.getClass().getClassLoader().getResource("template.doc").getPath();
        InputStream is = new FileInputStream(templatePath);  
        HWPFDocument doc = new HWPFDocument(is);  
        Range range = doc.getRange();  
        range.replaceText("${title}", down.getTitle());  
        range.replaceText("${content}", allText1);  
        range.replaceText("${source}", change.htmlToText(down.getOriginSource()));  
        range.replaceText("${time}", change.htmlToText(down.getPubTime())); 
        
		response.setContentType("application/x-download");
		
		//strDefaultFileName为默认的下载文件名
		String strDefaultFileName = down.getTitle() +".doc";
		try {
			strDefaultFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(strDefaultFileName.getBytes("UTF-8")))) + "?=";
		} catch (UnsupportedEncodingException e1) {
			
		}
		response.addHeader("Content-Disposition", "attachment;filename=" + strDefaultFileName);
		java.io.OutputStream outputsream = response.getOutputStream();
        doc.write(outputsream);
        outputsream.flush();
		outputsream.close();  
		outputsream=null;  
		response.flushBuffer(); 
		is.close();

		return getJspId(moduleId);
		
	}
	
	/**
	 * 采用XWPF的接口编写，单个文档下载
	 */
	public String downloadSingle() throws IOException {
		
		int moduleId  = Integer.valueOf(request.getParameter("moduleId"));
		int newsId = Integer.valueOf(request.getParameter("newsId"));

		//从数据库里取出数据
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		News down =newsdao.findById(newsId, moduleId);
		htmlToDoc change1 = new htmlToDoc();
		htmlToDoc2 change = new htmlToDoc2();
		String allText = down.getContent();
		String befText = null;
		String[] str = null;
		String[] allTextnow = null;
		//对获取的正文内容进行处理
		//befText = change.htmlToText(allText).substring(0, change.htmlToText(allText).indexOf(")")+1);
 		//allText = change.htmlToText(allText).substring(change.htmlToText(allText).indexOf(")")+1);
		allTextnow = change.htmlToText(allText);
		//str = allText.split(" ");
		
		response.setContentType("application/x-download");
		//strDefaultFileName为默认的下载文件名
		String strDefaultFileName = down.getTitle() +".doc";
		try {
			strDefaultFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(strDefaultFileName.getBytes("UTF-8")))) + "?=";
		} catch (UnsupportedEncodingException e1) {
			
		}
		response.addHeader("Content-Disposition", "attachment;filename=" + strDefaultFileName);
		java.io.OutputStream outputsream = response.getOutputStream();
		
		XWPFDocument doc = new XWPFDocument();
	    XWPFParagraph p1,p2,p3,p4 ;//分别为对应标题，正文，来源，时间四个段落
	    XWPFRun r1,r2,r3,r4;//分别用于填充四个段落内容的工具，包括文本和样式
		//标题
		p1 = doc.createParagraph();
		p1.setAlignment(ParagraphAlignment.CENTER);
		p1.setSpacingAfter(50);
		r1 = p1.createRun();
		r1.setBold(true);//设置标题字体粗体
		r1.setText(down.getTitle());
		//正文
		for(int j=0;j<allTextnow.length;j++){//将正文中的每段依次填写在p2中
			if(allTextnow[j].length()>1){
				p2 = doc.createParagraph();
				p2.setAlignment(ParagraphAlignment.BOTH);
				WordSetting.getInstance().setParagraphIndInfo(p2);//首行缩进
				r2 = p2.createRun();
				if(allTextnow[j].trim().codePointAt(0)==12288){//识别已有的缩进，缩进为非java表示的空格或制表符，只能通过Ascll
					allTextnow[j] = allTextnow[j].substring(2);//取消已有的缩进符
				}
				r2.setText(allTextnow[j].trim());
			}
		}
		//来源
		p3 = doc.createParagraph();
		p3.setAlignment(ParagraphAlignment.RIGHT);
		r3 = p3.createRun();
		r3.setText(change1.htmlToText(down.getOriginSource()));
		//时间
		p4 = doc.createParagraph();
		p4.setAlignment(ParagraphAlignment.RIGHT);
		r4 = p4.createRun();
		r4.setText(change1.htmlToText(down.getPubTime()));
		
        doc.write(outputsream);
        outputsream.flush();
		outputsream.close();  
		outputsream=null;  
		response.flushBuffer(); 

		return getJspId(moduleId);
		
	}
	
	/**
	 * 批量下载，后台管理部分
	 */
	public String downloadBatch() throws IOException {
		ArrayList<News> downList = null;
		
		int moduleId  = Integer.valueOf(request.getParameter("moduleId"));
		int fileType =  Integer.valueOf(request.getParameter("fileType"));
		downList = getNewsData(moduleId);
		  
		response.setContentType("application/x-download");
		
		String strDefaultFileName = getFileName(moduleId,fileType);
		try {
			strDefaultFileName = "=?UTF-8?B?"+ (new String(Base64.encodeBase64(strDefaultFileName.getBytes("UTF-8")))) + "?=";
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.addHeader("Content-Disposition", "attachment;filename=" + strDefaultFileName);
		java.io.OutputStream outputsream = response.getOutputStream();
		try {
			FileHandle.getInstance().write2File(downList, outputsream, moduleId, fileType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.flushBuffer(); 
		return getJspId(moduleId);
	}
	
	private ArrayList<News> getNewsData(int moduleId){
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<News> downList = null;
		if (moduleId == 12||moduleId == 13) {// 如果是一周热点参考或工作动态参考
			downList = newsdao.downloadAll(moduleId);
		}else if(moduleId==55){//搜索模块信息下载
			String listpage=request.getParameter("searchIdCount");
			String[] selectedID = request.getParameterValues("What_select");
			Object list = ((SessionMap) ActionContext.getContext().getSession()).get(listpage);
			downList = new SearchNewsConvert(list, selectedID).getNewsList();
		}else{//后台其他信息模块下载
			String[] newsIds = request.getParameterValues("newsIds");
			downList = newsdao.getMoreNews(newsIds, moduleId);
		}
		return downList;
	}
	
	private String getFileName(int moduleId,int fileType){
		
		String suffix = "" ;
		String strDefaultFileName = "" ;
		if (fileType == Constants.FILETYPE_HTML) {
			suffix = ".html";
		}else if(fileType == Constants.FILETYPE_DOCX){
			suffix = ".doc";
		}else if(fileType == Constants.FILETYPE_EXCEL){
			suffix = ".xls";
		}
		switch (moduleId) {
		case 1:
			strDefaultFileName = "工作动态" + suffix;
			break;
		case 2:
			strDefaultFileName = "油气管理" + suffix;
			break;
		case 3:
			strDefaultFileName = "政策法规" + suffix;
			break;
		case 4:
			strDefaultFileName = "一周热点" + suffix;
			break;
		case 5:
			strDefaultFileName = "对外合作" + suffix;
			break;
		case 6:
			strDefaultFileName = "科技进展" + suffix;
			break;
		case 8:
			strDefaultFileName = "领导动态" + suffix;
			break;
		case 9:
			strDefaultFileName = "统计数据" + suffix;
			break;
		case 10:
			strDefaultFileName = "勘探开发" + suffix;
			break;
		case 12:
			strDefaultFileName = "一周热点参考" + suffix;
			break;
		case 13:
			strDefaultFileName = "工作动态参考" + suffix;
			break;
		case 55:
			strDefaultFileName = "搜索结果下载" + suffix;
			break;
		default:
			break;
		}
		
		return strDefaultFileName;
	}
		
	public String getJspId(int moduleId){
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll = new PageRoll();
		if (currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		// 从数据库里取出数据
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<News> listData = newsdao.pageList(pageRoll, moduleId);

		request.setAttribute("pageRoll", pageRoll);

		if (moduleId == 1) {
			request.setAttribute("listGzdt", listData);
			return "1";
		} else if (moduleId == 2) {
			request.setAttribute("listKcgl", listData);
			return "2";
		} else if (moduleId == 3) {
			request.setAttribute("listZcfg", listData);
			return "3";
		} else if (moduleId == 4) {
			request.setAttribute("listYzrd", listData);
			return "4";
		} else if (moduleId == 5) {
			request.setAttribute("listGjhz", listData);
			return "5";
		} else if (moduleId == 6) {
			request.setAttribute("listKjjz", listData);
			return "6";
		} else if (moduleId == 7) {
			request.setAttribute("listTpxw", listData);
			return "7";
		} else if (moduleId == 8) {
			request.setAttribute("listLddt", listData);
			return "8";
		} else if (moduleId == 9) {
			request.setAttribute("listTjsj", listData);
			return "9";
		} else if (moduleId == 10) {
			request.setAttribute("listKtkf", listData);
			return "10";
		} else if (moduleId == 11) {
			request.setAttribute("listYjxx", listData);
			return "11";
		} else if (moduleId == 12) {
			request.setAttribute("listDtrd", listData);
			return "12";
		} else if (moduleId == 13) {
			request.setAttribute("listDtck", listData);
			return "13";
		} else if (moduleId == 14) {
			request.setAttribute("listYqbg", listData);
			return "14";
		}else if (moduleId == 55) {
			request.setAttribute("list", listData);
			return "55";
		}
		return ERROR;
	}
	
	
}
