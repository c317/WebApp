package com.gasinfo.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.model.SelectModel;
import com.gasinfo.server.webselect.SelectWebNodes;
import com.opensymphony.xwork2.ActionContext;

public class manageXywzMaintainAction implements ServletRequestAware, Action{

	private HttpServletRequest request;
	private String WebID;
	public String getWebID() {
		return WebID;
	}
	public void setWebID(String webID) {
		WebID = webID;
	}
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	//获取所有网站信息
	public String manageXywzMaintain() throws Exception{
		SelectWebNodes selectwebnodes = new SelectWebNodes();
		ArrayList<HashMap<String, String>> listAllWebs =  new ArrayList<HashMap<String, String>>();
		listAllWebs = selectwebnodes.GetAllWebs();
		request.setAttribute("listAllWebs", listAllWebs);
		return SUCCESS;
		
	}
	
	//获取model信息
	public String manageXywzMaintainModule() throws Exception{
		ActionContext actioncontext = ActionContext.getContext();
		ArrayList<HashMap<String, String>> listAllWebs = (ArrayList<HashMap<String, String>>) actioncontext
				.getSession().get("listAllWebs");
		SelectWebNodes selectwebnodes = new SelectWebNodes();
		SelectModel selectmodel =  new SelectModel();
		selectmodel = selectwebnodes.GetWebNodes(Integer.parseInt(WebID));
		request.setAttribute("SelectModel", selectmodel);
		request.setAttribute("listAllWebs", listAllWebs);
		return SUCCESS;
		
	}

}
