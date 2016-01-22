package com.gasinfo.action;

import java.sql.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Users;
import com.gasinfo.util.JdbcUtils;
import com.gasinfo.util.UsersDao;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 5912638975542232044L;

	private String username;
	private String password;
	private boolean type;
	public String execute() throws Exception {
		UsersDao usersdao=DaoFactory.getInstance().getUsersDao();
		Users us=usersdao.userLogin(username, password);
		if(us.getUsername()!=null && us.getPassword()!=null && us.getUsername().trim().equals(username) && us.getPassword().trim().equals(password)) {
			ServletRequest request = ServletActionContext.getRequest();
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.setAttribute("us", us);
			return "success";
		}
		return "fail";
	 }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}
                                                   
}
