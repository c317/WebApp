package com.gasinfo.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.*;

public class isVisibleAction implements ServletRequestAware, Action{
	private HttpServletRequest request;
	private String newsId;
	private String moduleId;
	private String visible;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
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
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	
	public String isVisible() {
	    String currpage = request.getParameter("currPage");
			PageRoll pageRoll = new PageRoll();
			 if (currpage != null) {
			 pageRoll.setCurrPage(Integer.parseInt(currpage));
			 }
			 
		int intNewsId = Integer.valueOf(newsId);
		int intModule = Integer.valueOf(moduleId);
		int intVisible = Integer.valueOf(visible);
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		newsdao.isVisble(intNewsId, intModule,intVisible);
		ArrayList<News> listData = newsdao.pageList(pageRoll, intModule);
		request.setAttribute("pageRoll", pageRoll);
			if (intModule == 1) {
				request.setAttribute("list", listData);
				return "1";
			} else if (intModule == 2) {
				request.setAttribute("list", listData);
				return "2";
			} else if (intModule == 3) {
				request.setAttribute("list", listData);
				return "3";
			}else if (intModule == 4) {
				request.setAttribute("list", listData);
				return "4";
			}else if (intModule == 5) {
				request.setAttribute("list", listData);
				return "5";
			}else if (intModule == 6) {
				request.setAttribute("list", listData);
				return "6";
			}else if (intModule == 7) {
				request.setAttribute("list", listData);
				return "7";
			}else if (intModule == 8) {
				request.setAttribute("list", listData);
				return "8";
			}else if (intModule == 9) {
				request.setAttribute("list", listData);
				return "9";
			}else if (intModule == 10) {
				request.setAttribute("list", listData);
				return "10";
			}else if (intModule == 11) {
				request.setAttribute("list", listData);
				return "11";
			}else if (intModule == 12) {
				request.setAttribute("list", listData);
				return "12";
			} else if (intModule == 13) {
				request.setAttribute("list", listData);
				return "13";
			} else if (intModule == 14) {
				request.setAttribute("list", listData);
				return "14";
			}
				return ERROR;
		}
		
	
	}

