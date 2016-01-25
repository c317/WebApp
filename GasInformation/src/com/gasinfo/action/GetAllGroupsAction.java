package com.gasinfo.action;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Group;
import com.gasinfo.util.Newsdao;

public class GetAllGroupsAction implements ServletRequestAware, Action {
	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}

	public String getAllGroup() {
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<Group> groups = newsdao.getAllGroup();
		request.setAttribute("groups", groups);
		return SUCCESS;
	}

	public String getPushNews() throws UnsupportedEncodingException {
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		String groupGet = request.getParameter("groups");
		String[] group = groupGet.split(",");
		ArrayList<Integer> groupsGet = new ArrayList<Integer>();
		for (int i = 0; i < group.length; i++) {
			groupsGet.add(Integer.parseInt(group[i]));
		}
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		String publisher = new String(request.getParameter("publisher")
				.getBytes("iso-8859-1"), "utf-8");
		String pushNewsContent = new String(request.getParameter(
				"pushNewsContent").getBytes("iso-8859-1"), "utf-8");
		String department = new String(request.getParameter("department")
				.getBytes("iso-8859-1"), "utf-8");
		String title=new String(request.getParameter("title")
				.getBytes("iso-8859-1"), "utf-8");
		newsdao.setNotification(time, groupsGet, pushNewsContent, publisher,department,title);
		ArrayList<Group> groups = newsdao.getAllGroup();
		request.setAttribute("groups", groups);
		return SUCCESS;
	}
}
