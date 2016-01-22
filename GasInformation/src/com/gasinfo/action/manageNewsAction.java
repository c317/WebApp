package com.gasinfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.News;
import com.gasinfo.util.Newsdao;

public class manageNewsAction implements ServletRequestAware, Action {
	private HttpServletRequest request;
	private String newsId;
	private String moduleId;


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

	public String manageNews() throws Exception {
		ArrayList<HashMap<String, String>> listCurrpage = new ArrayList<HashMap<String, String>>();
		News listMK = null;
		int intNewsId = Integer.valueOf(newsId);
		int intModuleId = Integer.valueOf(moduleId);
		
//		ActionContext actioncontext = ActionContext.getContext();
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
			
		switch (intModuleId){
			case 1: request.setAttribute("Location", "工作动态"); break;
			case 2: request.setAttribute("Location", "矿产管理"); break;
			case 3: request.setAttribute("Location", "政策法规"); break;
			case 4: request.setAttribute("Location", "一周热点"); break;
			case 5: request.setAttribute("Location", "国际合作"); break;
			case 6: request.setAttribute("Location", "科技进展"); break;
			case 7: request.setAttribute("Location", "图片新闻"); break;
			case 8: request.setAttribute("Location", "领导动态"); break;
			case 9: request.setAttribute("Location", "统计数据"); break;
			case 10: request.setAttribute("Location", "勘探开发"); break;
			case 11: request.setAttribute("Location", "油价信息"); break;
			case 12: request.setAttribute("Location", "动态热点"); break;
			case 13: request.setAttribute("Location", "动态参考"); break;
		}
		
		listMK = newsdao.findById(intNewsId, intModuleId);
			
		request.setAttribute("title", listMK.getTitle());
		request.setAttribute("source", listMK.getOriginSource());
		request.setAttribute("siteSource", listMK.getSiteSource());
		request.setAttribute("time", listMK.getPubTime());
		request.setAttribute("html", listMK.getContent());
		return SUCCESS;
	}
}
