package com.gasinfo.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.*;


public class manageNewsAddAction implements ServletRequestAware, Action {
	private HttpServletRequest request;
	private String moduleId;
	public void setRequest(HttpServletRequest request) {
		this.request = request;
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
	/**
	 * 向工作动态参考表添加新闻
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String newsAddDtck()  {
		String[] newsId = request.getParameterValues("newsIds");
		int moduleId = Integer.valueOf(request.getParameter("moduleId"));
		String columnName = null;
		try {
			columnName = new String(request.getParameter("columnName").getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		newsdao.addTobookdb(newsId, moduleId, 13);	 
		newsdao.addTobook(newsId, moduleId, 13, columnName);
		return SUCCESS;
	}
	/**
	 * 向一周参考表添加新闻
	 * @return
	 */
	public String newsAddDtrd() {
		String[] newsId = request.getParameterValues("newsIds");
		int moduleId = Integer.valueOf(request.getParameter("moduleId"));
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		newsdao.addTobookdb(newsId, moduleId, 12);
		//一周热点参考没有分栏信息
		newsdao.addTobook(newsId, moduleId, 12, null);
		return SUCCESS;
	}
	/**
	 * 获取工作动态参考的所有分栏
	 * @return
	 */
	public String getNewsColumn(){
		String[] columnsName = null;
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		newsdao.getColumnsName();
		request.setAttribute("columnsName", columnsName);
		return SUCCESS;
	}
	/**
	 * 修改消息的分栏属性
	 * @return
	 */
	public String modifyColumnName(){
		int newsID = Integer.valueOf(request.getParameter("id"));
		String toColumnName = null;
		try {
			toColumnName = new String(request.getParameter("toColumnName").getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		boolean result = newsdao.modifyColumnName(newsID, toColumnName);
		if(result){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	 
	public String getJspId(){
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll = new PageRoll();
		if (currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		request.setAttribute("pageRoll", pageRoll);
		
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		String columnsName[] = newsdao.getColumnsName();
		request.setAttribute("columnsName", columnsName);
		
		ArrayList<News> listData;
		if(Integer.valueOf(moduleId) == 13){//工作动态参考的分页机制与其他模块不同
			listData = newsdao.pageListOfDtck(pageRoll);
		}else{
			listData = newsdao.pageList(pageRoll, Integer.valueOf(moduleId));
		}
		request.setAttribute("list", listData);
		return String.valueOf(moduleId);
	}
}
