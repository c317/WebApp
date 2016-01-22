package com.gasinfo.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.News;
import com.gasinfo.util.Newsdao;


public class manageNewsModifyAction implements ServletRequestAware , Action  {
	
	
	private HttpServletRequest request;
	private String newsId;
	private String moduleId;
	private String title;
	private String tomodule;
	private News news;
	
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getTomodule() {
		return tomodule;
	}
	public void setTomodule(String tomodule) {
		this.tomodule = tomodule;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	
	public String manageNewsModify() {
		int intModuleId = Integer.valueOf(moduleId);
		int  intNewsId= Integer.valueOf(newsId);
		Newsdao newsdao=DaoFactory.getInstance().getNewsDao();
		News news =newsdao.findById(intNewsId, intModuleId);
		request.setAttribute("news", news);
//		request.setAttribute("newsId", (Object)Integer.valueOf(intNewsId));
		if(intModuleId<12){
			return SUCCESS;
		}else{
			return "qtsuccess";
		}
		
	}
	
	public String newsModify() throws UnsupportedEncodingException{
		title = new String(title.getBytes("iso-8859-1"), "utf-8");
		Newsdao newsdao =DaoFactory.getInstance().getNewsDao();
	 	News news=newsdao.findByTitle(title, Integer.valueOf(tomodule));
	 	request.setAttribute("updateNews", news);
		return SUCCESS;
	}

	

}
