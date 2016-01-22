package com.gasinfo.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.util.Users;
import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.JdbcUtils;
import com.gasinfo.util.UsersDao;

public class AccountAction implements ServletRequestAware, Action {
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	public String accountAction(){
		UsersDao usersdao=DaoFactory.getInstance().getUsersDao();
//		
//		ArrayList<Users> users = new ArrayList<Users>();
//		users=usersdao.accountUsers();
//		Connection con = null;
//		Statement sql = null;
//		ResultSet rs = null;
//		try{ con = JdbcUtils.getConnection();
//			sql = con.createStatement();
//			rs = sql.executeQuery("select * from Yhdl");
//			for(int i =0;rs.next();i++)
//			{
//				Users us = new Users();
//				us.setUsername(rs.getString("name"));
//				us.setPassword(rs.getString("passwords"));
//				if (rs.getString("type").equals("1")) {
//					us.setType(true);
//				} else {
//					us.setType(false);
//				}
//				users.add(us);
//			}
//			request.setAttribute("users", users);
//
//			return SUCCESS;
//
//		}catch (Exception e) {
//			System.out.println("accoutAction isError");
//		}finally {
//			JdbcUtils.releaseConnection(con);
//		}
//		return SUCCESS;
		ArrayList<Users> users = new ArrayList<Users>();
		users=usersdao.accountUsers();
		request.setAttribute("users", users);
		return SUCCESS;
	}
}
