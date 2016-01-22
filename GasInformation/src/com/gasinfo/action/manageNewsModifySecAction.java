package com.gasinfo.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

public class manageNewsModifySecAction implements ServletRequestAware , Action   {
	private HttpServletRequest request;
	private String newsId;
	private String moduleId;
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
	
	public String manageNewsModifySec() {
		int intModuleId = Integer.valueOf(moduleId);
		int  intNewsId= Integer.valueOf(newsId);
		request.setAttribute("moduleId",intModuleId );
		request.setAttribute("newsId", intNewsId);
		return  SUCCESS;
	}
	
}
