package com.gasinfo.util;

import java.util.ArrayList;

public interface UsersDao {
	//获取所有系统用户
	public ArrayList<Users> accountUsers();
	public Users userLogin(String username,String password); 
	public String addUsers(String username,String password);
	public String updateUsers(String username,String password);
	public String deleteUsers(String username);
	public String update(String username,String password);
}
