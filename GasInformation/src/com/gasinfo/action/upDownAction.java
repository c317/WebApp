package com.gasinfo.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.News;
import com.gasinfo.util.Newsdao;
import com.gasinfo.util.PageRoll;

public class upDownAction implements ServletRequestAware,Action{

	private HttpServletRequest request;
	private String moduleId;
	private String updown;
	private String updownId;
	private String newsId;
	
	public String getUpdownId() {
		return updownId;
	}

	public void setUpdownId(String updownId) {
		this.updownId = updownId;
	}
	
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getUpdown() {
		return updown;
	}

	public void setUpdown(String updown) {
		this.updown = updown;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	
	public String updownstick(){
		PageRoll pageRoll = new PageRoll();
		int NewsId=Integer.valueOf(newsId);
		int UpdownId=Integer.valueOf(updownId);
		
		Newsdao newsdao=DaoFactory.getInstance().getNewsDao();
		if(Integer.valueOf(moduleId) == 13){
			String columnOrder = request.getParameter("columnOrder");
			newsdao.upDownStickOfDtck(updown, NewsId, Integer.valueOf(columnOrder), UpdownId);
		}else{
			newsdao.upDownStick(Integer.valueOf(moduleId),updown,NewsId,UpdownId);
		}
		
		ArrayList<News> listData;
		if(Integer.valueOf(moduleId) == 13){//工作动态参考的分页机制与其他模块不同
			listData = newsdao.pageListOfDtck(pageRoll);
		}else{//上移、下移以及置顶只在一周热点参考和工作动态参考中
			listData = newsdao.pageList(pageRoll, Integer.valueOf(moduleId));
		}
		request.setAttribute("pageRoll", pageRoll);
		request.setAttribute("list", listData);
		
		newsdao = DaoFactory.getInstance().getNewsDao();
		String columnsName[] = newsdao.getColumnsName();
		request.setAttribute("columnsName", columnsName);
		
		return moduleId;
	}

}
