package com.gasinfo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.*;

public class manageNewsDtPageAction implements ServletRequestAware, Action {
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}


	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	
	public String gotoDtck() {
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll =new PageRoll();
		if(currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		Newsdao Newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<News> manageDtck =Newsdao.pageList(pageRoll,13);
		request.setAttribute("manageDtck", manageDtck);
		request.setAttribute("pageRoll", pageRoll);
		return SUCCESS;
	}
	
	
	public String gotoDtrd() {
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll =new PageRoll();
		if(currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		Newsdao Newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<News> manageDtrd =Newsdao.pageList(pageRoll,12);
		request.setAttribute("manageDtrd", manageDtrd);
		request.setAttribute("pageRoll", pageRoll);
		return SUCCESS;
	}
}
