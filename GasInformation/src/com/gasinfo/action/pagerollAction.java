package com.gasinfo.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;


import com.gasinfo.util.PageRoll;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class pagerollAction implements ServletRequestAware, Action {
	private HttpServletRequest request;
	private String searchIdCount;

	public String getSearchIdCount() {
		return searchIdCount;
	}

	public void setSearchIdCount(String searchIdCount) {
		this.searchIdCount = searchIdCount;
	}

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}

	public String pageroll() throws Exception {
		HttpSession session=request.getSession();
		ArrayList<HashMap<String, String>> searchlist = new ArrayList<HashMap<String, String>>();
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll = new PageRoll();
		searchlist = (ArrayList<HashMap<String, String>>) session.getAttribute(searchIdCount);
		pageRoll.setTotalCount(searchlist.size());
		if (currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		ArrayList<HashMap<String, String>> listCurrpage = new ArrayList<HashMap<String, String>>();

		if (pageRoll.getCurrPage() != pageRoll.getPageCount()) {
			for (int i = (pageRoll.getCurrPage() - 1) * pageRoll.getPageSize(); i < pageRoll
					.getCurrPage() * pageRoll.getPageSize(); i++) {
				listCurrpage.add(searchlist.get(i));
			}
		} else {
			for (int i = (pageRoll.getCurrPage() - 1) * pageRoll.getPageSize(); i < pageRoll
					.getTotalCount(); i++) {
				listCurrpage.add(searchlist.get(i));
			}
		}
		request.setAttribute("listCurrpage", listCurrpage);
		request.setAttribute("pageRoll", pageRoll);
		request.setAttribute("searchIdCount", searchIdCount);
		request.setAttribute("searchCurrentPage", pageRoll.getCurrPage());
		return SUCCESS;
	}
}
