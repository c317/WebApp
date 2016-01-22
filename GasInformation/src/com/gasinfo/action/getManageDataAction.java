package com.gasinfo.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.News;
import com.gasinfo.util.Newsdao;
import com.gasinfo.util.PageRoll;

public class getManageDataAction implements ServletRequestAware, Action {

	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}

	private String moduleId;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getManageData() throws Exception {
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll = new PageRoll();
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		String columnsName[] = newsdao.getColumnsName();
		request.setAttribute("columnsName", columnsName);
		if (currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		request.setAttribute("pageRoll", pageRoll);
		
		if (moduleId == null) {
			moduleId = "1";
		} 
		
		ArrayList<News> listData = null;
		if (Integer.valueOf(moduleId) ==13) {//工作动态参考的分页机制与其他模块不同
			listData = newsdao.pageListOfDtck(pageRoll);
		}else{
			listData = newsdao.pageList(pageRoll, Integer.valueOf(moduleId));
		}
		request.setAttribute("list", listData);
		return String.valueOf(moduleId);
	}
}
