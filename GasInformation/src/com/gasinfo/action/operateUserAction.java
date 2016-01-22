package com.gasinfo.action;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.UsersDao;
public class operateUserAction implements ServletRequestAware, Action {

	private HttpServletRequest request;
	private String username;
	private String password;


	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}


	public String addUser() throws Exception {
		//添加用户的代码
		
		UsersDao usersdao=DaoFactory.getInstance().getUsersDao();
		username=new String(request.getParameter("username").
				getBytes("iso-8859-1"),"utf-8");
		password=new String(request.getParameter("password").
				getBytes("iso-8859-1"),"utf-8");
		String results=usersdao.addUsers(username, password);
		return results;
	}
	
	public String reUser() throws Exception {
		UsersDao usersdao=DaoFactory.getInstance().getUsersDao();
		String results=usersdao.updateUsers(username, password);
		return results;
	}	
	public String delUser() throws Exception {
		//删除用户的代码
		UsersDao usersdao=DaoFactory.getInstance().getUsersDao();
		username=new String(request.getParameter("username")
				.getBytes("iso-8859-1"),"utf-8");
		String results=usersdao.deleteUsers(username);
		return results;
	}
	public String rePd() throws Exception {
		UsersDao userdao=DaoFactory.getInstance().getUsersDao();
		username=new String(request.getParameter("username").
				getBytes("iso-8859-1"),"utf-8");
		password=new String(request.getParameter("password").
				getBytes("iso-8859-1"),"utf-8");
		String resualt=userdao.update(username, password);
		return resualt;
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

}
