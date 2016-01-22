package com.gasinfo.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.gasinfo.util.*;

public class indexAction implements ServletRequestAware, Action {

	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}

	public String index() throws Exception {
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<News> listGzdt = newsdao.getIndexNews("gzdt");
//		ArrayList<News> listYzrd = newsdao.getIndexNews("yzrd");
		ArrayList<News> listYzrd = newsdao.getIndexNews("gzdtrd");
		ArrayList<News> listTpxw = newsdao.getIndexNews("tpxw");
		ArrayList<HashMap<String, Integer>> listHotWord = newsdao.getHotword(1);
		request.setAttribute("listGzdt", listGzdt);
		request.setAttribute("listYzrd", listYzrd);
		request.setAttribute("listTpxw", listTpxw);
		request.setAttribute("listHotWord", listHotWord);
		return SUCCESS;
	}
}
