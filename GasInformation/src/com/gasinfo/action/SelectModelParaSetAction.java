package com.gasinfo.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.model.SelectModel;
import com.gasinfo.server.heritrix.AddWebsite;
import com.gasinfo.server.webselect.SelectWebNodes;
import com.opensymphony.xwork2.ActionContext;

public class SelectModelParaSetAction extends SelectModel implements
		ServletRequestAware, Action {

	private String ModuleB_Postion;
	private String ModuleB_MK;
	private String ModuleC_Postion;
	private String ModuleC_MK;

	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	
	public String getModuleB_Postion() {
		return ModuleB_Postion;
	}

	public void setModuleB_Postion(String moduleB_Postion) {
		ModuleB_Postion = moduleB_Postion;
	}

	public String getModuleB_MK() {
		return ModuleB_MK;
	}

	public void setModuleB_MK(String moduleB_MK) {
		ModuleB_MK = moduleB_MK;
	}

	public String getModuleC_Postion() {
		return ModuleC_Postion;
	}

	public void setModuleC_Postion(String moduleC_Postion) {
		ModuleC_Postion = moduleC_Postion;
	}

	public String getModuleC_MK() {
		return ModuleC_MK;
	}

	public void setModuleC_MK(String moduleC_MK) {
		ModuleC_MK = moduleC_MK;
	}

	public String SelectModelParaSet() throws Exception {
		SelectModel selectModel;
		selectModel = (SelectModel) this;
		AddWebsite aw = new AddWebsite();
		ActionContext actioncontext = ActionContext.getContext();
		if(selectModel.getWebName()==null||selectModel.getWebName()==""){
			String Webname = (String)actioncontext.getSession().get("Webname");
			selectModel.setWebName(Webname);
		}
		SelectWebNodes selectwebnodes = new SelectWebNodes();
		ArrayList<HashMap<String, String>> listAllWebs = selectwebnodes.GetAllWebs();
		request.setAttribute("listAllWebs", listAllWebs);
		if(selectModel.getWebID().equals("-1")){
			for(int i=0;i<listAllWebs.size();i++){
				if(selectModel.getWebName().trim().equals(listAllWebs.get(i).get("name").trim())){
					return ERROR;
				}
			}
		}
		
//		SelectWebNodes selectwebnodes = new SelectWebNodes();
//		ArrayList<HashMap<String, String>> listAllWebs =  new ArrayList<HashMap<String, String>>();
//		listAllWebs = selectwebnodes.GetAllWebs();
//		request.setAttribute("listAllWebs", listAllWebs);
		String strModuleID ;
		String[] strsModuleID;
		String strModulePostion;
		String[] strsModulePostion;
		ArrayList<HashMap<String, String>> Module_String = new ArrayList<HashMap<String, String>>();
		if(this.getModuleID().equals("00")){
			strModuleID = this.getModuleA_MK();
			strsModuleID = strModuleID.split(",");
			aw.addWeb(selectModel.getWebName(), selectModel.getSeed(), strsModuleID);
		}
			
	    if(this.getModuleID().equals("01")){
	    	strModuleID = this.getModuleB_MK();
	    	strsModuleID = strModuleID.split(",");
	    	strModulePostion = this.getModuleB_Postion();
	    	strsModulePostion = strModulePostion.split(",");
	    	selectModel.setModuleB_String(this.GetModule_String(strsModuleID, strsModulePostion));
	    	aw.addWeb(selectModel.getWebName(), selectModel.getSeed(), strsModuleID);
		}
	    if(this.getModuleID().equals("02")){
	    	strModuleID = this.getModuleC_MK();
	    	strsModuleID = strModuleID.split(",");
	    	strModulePostion = this.getModuleC_Postion();
	    	strsModulePostion = strModulePostion.split(",");
	    	selectModel.setModuleC_String(this.GetModule_String(strsModuleID, strsModulePostion));
	    	aw.addWeb(selectModel.getWebName(), selectModel.getSeed(), strsModuleID);
		}

		new SelectWebNodes().AddOrChangeWebNodes(selectModel);
		return SUCCESS;
	}

	public ArrayList<HashMap<String, String>> GetModule_String(String[] strsModuleID,String[] strsModulePostion){
		ArrayList<HashMap<String, String>> Module_String = new ArrayList<HashMap<String, String>>();
		for(int i = 0;i<strsModuleID.length;i++){
			HashMap<String, String> hashmap = new HashMap<String, String>();
			hashmap.put("Postion", strsModulePostion[i].trim());
			hashmap.put("MK", this.getMK(strsModuleID[i].trim()));
			Module_String.add(hashmap);
		}
		return Module_String;
	}
	
	public String getMK(String WebID){
		if(WebID.equals("1")){
			return "Gzdt";
		}
		if(WebID.equals("2")){
			return "Kcgl";
		}
		if(WebID.equals("3")){
			return "Zcfg";
		}
		if(WebID.equals("4")){
			return "Yzrd";
		}
		if(WebID.equals("5")){
			return "Gjhz";
		}
		if(WebID.equals("6")){
			return "Kjjz";
		}
		if(WebID.equals("7")){
			return "Tpxw";
		}
		if(WebID.equals("8")){
			return "Lddt";
		}
		if(WebID.equals("9")){
			return "Tjsj";
		}
		
		if(WebID.equals("10")){
			
			return "Ktkf";
		}
		if(WebID.equals("11")){
			return "Yjxx";
		}
		return "sucess";
	}

}
