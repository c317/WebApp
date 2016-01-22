package com.gasinfo.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.model.search;

import com.gasinfo.server.search.WebSearch;
import com.gasinfo.util.PageRoll;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class searchAction implements ServletRequestAware, Action {

	private HttpServletRequest request;
	private  static int countSearch=0;

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}

	public String search() throws Exception {
		HttpSession session=request.getSession();
		ArrayList<HashMap<String, String>> searchlist = new ArrayList<HashMap<String, String>>();
		PageRoll pageRoll = new PageRoll();
		String searchkey = request.getParameter("textfield");
		String B_time = request.getParameter("startTime");
		String E_time = request.getParameter("endTime");
		String area = request.getParameter("area");
		String NoString = request.getParameter("module");

		// sel值 1表示全文检索 0表示标题检索
		String sel = request.getParameter("sel");

		int No = -1;
		if (NoString != null) {
			No = Integer.parseInt(NoString);
		}

		int No_sel = -1;
		if (sel != null) {
			No_sel = Integer.parseInt(sel);
		}
		WebSearch websearch = new WebSearch();

		searchlist = websearch.WebSearch(searchkey, area, B_time, E_time, No,No_sel);

		if (searchlist.size() == 0) {
			return ERROR;
		}
		countSearch++;
		session.setAttribute("s"+String.valueOf(countSearch), searchlist);
		pageRoll.setTotalCount(searchlist.size());
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
		request.setAttribute("searchIdCount", "s"+String.valueOf(countSearch));
		request.setAttribute("listCurrpage", listCurrpage);
		request.setAttribute("pageRoll", pageRoll);
		request.setAttribute("searchCurrentPage", pageRoll.getCurrPage());
		return SUCCESS;
	}
}
