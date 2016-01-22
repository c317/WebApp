package com.gasinfo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.server.indexdatebase.Words;

public class dealWordsAction implements ServletRequestAware, Action {

	private HttpServletRequest request;
	private String WordsType;
	private String WordsName;


	public String getWordsName() {
		return WordsName;
	}

	public void setWordsName(String wordsName) {
		WordsName = wordsName;
	}

	public String getWordsType() {
		return WordsType;
	}

	public void setWordsType(String wordsType) {
		WordsType = wordsType;
	}

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}

	public String getWords() throws Exception {
		
		

		ArrayList<String> listWords = new ArrayList<String>();
		listWords = new Words().getWords(WordsType);
		
		request.setAttribute("WordsType", WordsType);
		request.setAttribute("listWords", listWords);
		return SUCCESS;
	}
	//获得关键词词表
  public String getWebKeyWords() throws Exception {
		ArrayList<String> listWords = new ArrayList<String>();
		WordsType=new String(request.getParameter("WordsType").getBytes("iso-8859-1"),"utf-8");
		listWords = new Words().getWebKeyWords(WordsType);
		request.setAttribute("WordsType", WordsType);
		request.setAttribute("listWords", listWords);
		return SUCCESS;
	}

	public String getAllWords() throws Exception {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> listAllWords = new ArrayList<String>();
		listAllWords = new Words().getAllWords();
		request.setAttribute("listAllWords", listAllWords);
		return SUCCESS;
	}
	
	
	public String RemoveWords() throws Exception{
		ArrayList<String> listWords = new ArrayList<String>();
		
		WordsName=new String(request.getParameter("WordsName").getBytes("iso-8859-1"),"utf-8");
		new Words().RemoveWords(WordsType, WordsName);
		listWords= new Words().getWords(WordsType);
		request.setAttribute("listWords", listWords);
		return SUCCESS;
	}
	public String RemoveWebKeyWords() throws Exception{
		ArrayList<String> listWords = new ArrayList<String>();
		WordsType=new String(request.getParameter("WordsType").getBytes("iso-8859-1"),"utf-8");
		WordsName=new String(request.getParameter("WordsName").getBytes("iso-8859-1"),"utf-8");
		new Words().RemoveWebKeyWords(WordsType, WordsName);
		listWords= new Words().getWebKeyWords(WordsType);
		request.setAttribute("listWords", listWords);
		return SUCCESS;
	}
	
	public String AddWords() throws Exception{
		ArrayList<String> listWords = new ArrayList<String>();
		WordsName=new String(request.getParameter("WordsName").getBytes("iso-8859-1"),"utf-8");
		new Words().AddWords(WordsType, WordsName);
		listWords= new Words().getWords(WordsType);
		request.setAttribute("listWords", listWords);
//		System.out.println(WordsType+WordsName);
		return SUCCESS;
	}
	public String AddWebKeyWords() throws Exception{
		ArrayList<String> listWords = new ArrayList<String>();
		WordsType=new String(request.getParameter("WordsType").getBytes("iso-8859-1"),"utf-8");
		WordsName=new String(request.getParameter("WordsName").getBytes("iso-8859-1"),"utf-8");
		new Words().AddWebKeyWords(WordsType, WordsName);
		listWords= new Words().getWebKeyWords(WordsType);
		request.setAttribute("listWords", listWords);
//		System.out.println(WordsType+WordsName);
		return SUCCESS;
	}

}
