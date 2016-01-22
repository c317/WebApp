package com.gasinfo.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.*;



public class manageNewsDeleteAction implements ServletRequestAware, Action  {
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
	
	
	public String DeleteBatch() {
		String[] news = request.getParameterValues("newsIds");
		int intModuleId = Integer.valueOf(moduleId);
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		newsdao.deleteMore(news,intModuleId);
		return SUCCESS;
	}
	
	
	public String DeleteSingle() throws IOException {
		int intNewsId =Integer.valueOf(newsId);
		int intModuleId =Integer.valueOf(moduleId);
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		newsdao.delete(intNewsId,intModuleId);
		
		return SUCCESS;
	}
	
	public String DeleteAll() {
		int intmoduleId =Integer.valueOf(moduleId);
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		newsdao.deleteData(intmoduleId);
		try {
			switch(intmoduleId){
/*			case 12:response.sendRedirect(request.getContextPath()+"/FriendServlet?method=gotoDtrd");break;
			case 13:response.sendRedirect(request.getContextPath()+"/FriendServlet?method=gotoDtck");break;*/
			case 12:return SUCCESS;
			case 13:return SUCCESS;
			default:break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}
	
	public String getJspId(){
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll = new PageRoll();
		if (currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		request.setAttribute("pageRoll", pageRoll);
		
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		String columnsName[] = newsdao.getColumnsName();
		request.setAttribute("columnsName", columnsName);
		
		ArrayList<News> listData;
		if(Integer.valueOf(moduleId) == 13){//工作动态参考的分页机制与其他模块不同
			listData = newsdao.pageListOfDtck(pageRoll);
		}else{
			listData = newsdao.pageList(pageRoll, Integer.valueOf(moduleId));
		}
		request.setAttribute("list", listData);
		return String.valueOf(moduleId);
	}
}
