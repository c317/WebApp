package com.gasinfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.News;
import com.gasinfo.util.Newsdao;
import com.gasinfo.util.PageRoll;

public class newsAction implements ServletRequestAware, Action {
	private HttpServletRequest request;

	private String titleNO;
	private String str_MK;
	private String searchCurrentPage;

	public String getSearchCurrentPage() {
		return searchCurrentPage;
	}

	public void setSearchCurrentPage(String searchCurrentPage) {
		this.searchCurrentPage = searchCurrentPage;
	}
	private String newsId;
	private String moduleId;

	public String getStr_MK() {
		return str_MK;
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

	public void setStr_MK(String str_MK) {
		this.str_MK = str_MK;
	}

	public String getTitleNO() {
		return titleNO;
	}

	public void setTitleNO(String titleNO) {
		this.titleNO = titleNO;
	}

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}

	public String news() throws Exception {
		News listMK = null;
		int intNewsId = Integer.valueOf(newsId);
		int intModuleId = Integer.valueOf(moduleId);

		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();	
		
		switch (intModuleId){
			case 1: request.setAttribute("Location", "工作动态"); break;
			case 2: request.setAttribute("Location", "油气管理"); break;
			case 3: request.setAttribute("Location", "政策法规"); break;
			case 4: request.setAttribute("Location", "一周热点"); break;
			case 5: request.setAttribute("Location", "对外合作"); break;
			case 6: request.setAttribute("Location", "科技进展"); break;
			case 7: request.setAttribute("Location", "图片新闻"); break;
			case 8: request.setAttribute("Location", "领导动态"); break;
			case 9: request.setAttribute("Location", "统计数据"); break;
			case 10: request.setAttribute("Location", "勘探开发"); break;
			case 11: request.setAttribute("Location", "油价信息"); break;
			case 12: request.setAttribute("Location", "动态热点"); break;
			case 13: request.setAttribute("Location", "动态参考"); break;
			//zm
			case 100:request.setAttribute("Location", "动态热点"); break;
			//zm
		}
		newsdao.countVisit(intNewsId, intModuleId);
		listMK = newsdao.findById(intNewsId, intModuleId);
		request.setAttribute("newsData", listMK);
		return SUCCESS;
	}
	public String rearchNews(){
		ArrayList<HashMap<String, String>> listCurrpage = new ArrayList<HashMap<String, String>>();
		HttpSession session=request.getSession();
		int i = Integer.parseInt(titleNO);
		listCurrpage = (ArrayList<HashMap<String, String>>) session.getAttribute(str_MK);
		int CurrentPage=(Integer.parseInt(searchCurrentPage)-1)*(new PageRoll().getPageSize());
		request.setAttribute("title", listCurrpage.get(i+CurrentPage).get("title"));
		request.setAttribute("source", listCurrpage.get(i+CurrentPage).get("source"));
		request.setAttribute("time", listCurrpage.get(i+CurrentPage).get("time"));
		request.setAttribute("html", listCurrpage.get(i+CurrentPage).get("html"));
		request.setAttribute("Location", "搜索");
		return SUCCESS;	

	}
}
