package com.gasinfo.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.gasinfo.util.*;

public class getDataAction implements ServletRequestAware, Action {

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


	public String getData() throws Exception {
	    String currpage = request.getParameter("currPage");
		PageRoll pageRoll = new PageRoll();
		 if (currpage != null) {
		 pageRoll.setCurrPage(Integer.parseInt(currpage));
		 } 
		int intModule = Integer.valueOf(moduleId) ; 
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		
		ArrayList<News> listData = newsdao.visiblePage(pageRoll, intModule);
		request.setAttribute("pageRoll", pageRoll);
		
		if (intModule == 3) {
			ArrayList<HashMap<String, Integer>> listHotWord = newsdao.getHotword(3);
//			ArrayList<News> listRdzc = newsdao.getIndexNews("yzrd");
			ArrayList<News> listRdzc = newsdao.getIndexNews("zcfgrd");
			request.setAttribute("listHotWord", listHotWord);
			request.setAttribute("listRdzc", listRdzc);
			request.setAttribute("listZcfg", listData);
			return "zcfg";	
		} else if (intModule == 1) {
			request.setAttribute("listGzdt", listData);
			ArrayList<HashMap<String, Integer>> listHotWord = newsdao.getHotword(1);
//			ArrayList<News> listYzrd = newsdao.getIndexNews("yzrd");
			ArrayList<News> listYzrd = newsdao.getIndexNews("gzdtrd");
			request.setAttribute("listHotWord", listHotWord);
			request.setAttribute("listYzrd", listYzrd);
			return "gzdt";
		}else if (intModule == 2) {
			request.setAttribute("listKcgl", listData);
			ArrayList<HashMap<String, Integer>> listHotWord = newsdao.getHotword(2);
//			ArrayList<News> listKcrd = newsdao.getIndexNews("yzrd");
			ArrayList<News> listKcrd = newsdao.getIndexNews("kcglrd");
			request.setAttribute("listHotWord", listHotWord);
			request.setAttribute("listKcrd", listKcrd);
			
			return "kcgl";
		} else if (intModule == 10) {
			request.setAttribute("listKtkf", listData);
			ArrayList<HashMap<String, Integer>> listHotWord = newsdao.getHotword(10);
//			ArrayList<News> listYwhg= newsdao.getIndexNews("yzrd");
			ArrayList<News> listYwhg= newsdao.getIndexNews("ktkfrd");
			request.setAttribute("listHotWord", listHotWord);
			request.setAttribute("listYwhg", listYwhg);
			
			return "ktkf";
		} else if (intModule == 14) {
			request.setAttribute("listYqbg", listData);
			ArrayList<News> listQyyq= newsdao.getIndexNews("yzrd");
			request.setAttribute("listQyyq", listQyyq);
	
			return "yqbg";
		} else if (intModule == 6) {
			request.setAttribute("listKjjz", listData);
			ArrayList<HashMap<String, Integer>> listHotWord = newsdao.getHotword(6);
//			ArrayList<News> listKjrd= newsdao.getIndexNews("yzrd");
			ArrayList<News> listKjrd= newsdao.getIndexNews("kjjzrd");
			request.setAttribute("listHotWord", listHotWord);
			request.setAttribute("listKjrd", listKjrd);
		
			return "kjjz";
		} else if (intModule == 5) {
			request.setAttribute("listGjhz", listData);
			ArrayList<HashMap<String, Integer>> listHotWord = newsdao.getHotword(5);
			request.setAttribute("listHotWord", listHotWord);
			
			return "gjhz";
		} else if ((intModule == 15) || (intModule == 4)) {
//			ArrayList<News> listYjxx= newsdao.getIndexNews("yjxx");
			ArrayList<News> listYjxx= newsdao.getIndexNews("yzrd");
			ArrayList<News> listLddt= newsdao.getIndexNews("lddt");
			ArrayList<News> listTjsj= newsdao.getIndexNews("tjsj");
			request.setAttribute("listYjxx", listYjxx);
			request.setAttribute("listLddt", listLddt);
			request.setAttribute("listTjsj", listTjsj);
	
			return "qt";
		} else
			return ERROR;
	}

}
