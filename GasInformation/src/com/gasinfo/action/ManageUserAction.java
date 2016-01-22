package com.gasinfo.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Newsdao;
import com.gasinfo.util.PageRoll;
import com.gasinfo.util.PhoneUser;

public class ManageUserAction implements ServletRequestAware, Action{
	
	private HttpServletRequest request;
	private ArrayList<PhoneUser> phoneUsers;
	private int curpage=0;
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	public String getAllUser() {
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll = new PageRoll();
		if (currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		Newsdao newsDao=DaoFactory.getInstance().getNewsDao();
		phoneUsers=newsDao.getAllUser(pageRoll);
		request.setAttribute("pageRoll", pageRoll);
		request.setAttribute("users", phoneUsers);
		return SUCCESS;
	}
	public String getDepartMentUser() throws UnsupportedEncodingException {
		PageRoll pageRoll = new PageRoll();
		curpage=Integer.parseInt(request.getParameter("curPage"));
		String department=new String(request.getParameter("department").
				getBytes("iso-8859-1"),"utf-8");
		 if (curpage!=0) {
			 pageRoll.setCurrPage(curpage);
			 } 
		 Newsdao newsDao=DaoFactory.getInstance().getNewsDao();
		 ArrayList<PhoneUser> phoneUsers=newsDao.getDepartmentUser(pageRoll, department);
		 request.setAttribute("users", phoneUsers);
		return SUCCESS;
	}
	//修改用户角色
	public String changeRole() throws UnsupportedEncodingException {
		

//		String department=request.getParameter("department");
//		String role=request.getParameter("role");
		int userID=Integer.parseInt(request.getParameter("userID"));
		String department=new String(request.getParameter("department").
				getBytes("iso-8859-1"),"utf-8");
		String role=new String(request.getParameter("role").
				getBytes("iso-8859-1"),"utf-8");
		Newsdao newsDao=DaoFactory.getInstance().getNewsDao();
		newsDao.changeRole(userID, role,department);
		if(request.getParameter("curPage")!=null) {
			curpage=Integer.parseInt(request.getParameter("curPage"));
		}
		PageRoll pageRoll = new PageRoll();
		 if (curpage!=0) {
			 pageRoll.setCurrPage(curpage);
			 } 
		request.setAttribute("pageRoll", pageRoll);
		phoneUsers=newsDao.getAllUser(pageRoll);
		request.setAttribute("users", phoneUsers);
		return SUCCESS;
	}
}
