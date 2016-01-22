package com.gasinfo.util;


import com.gasinfo.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyDataSource {
	static Configuration rc = new Configuration("daoconfig.properties");
	static String SQL_IP = rc.getValue("SQL_IP");
	static String SQL_account = rc.getValue("SQL_account");
	static String SQL_key = rc.getValue("SQL_key");
	private static String url = SQL_IP;
	private static int initCount =5;
	private static int maxCount =10;
	int currentCount =0;
	LinkedList<Connection> connectionsTool =new LinkedList<Connection>();
	
	public MyDataSource(){
		try{
		for(int i=0;i<initCount;i++){
			this.connectionsTool.addLast(this.createConnection());
		    this.currentCount++;
		}
		}catch (SQLException e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public Connection getConnection() throws SQLException{
		synchronized (connectionsTool) {
			if(this.connectionsTool.size()>0)
				return this.connectionsTool.removeFirst();
			
			if(this.currentCount <maxCount){
			    this.currentCount++;
				return this.createConnection();
			}
			
			throw new SQLException("SQLerror");
		}
	}
	
	public void free(Connection conn){
		this.connectionsTool.addLast(conn);
	}
	
	private Connection createConnection() throws SQLException{
		if(SQL_account == "" || SQL_account == null || SQL_account.isEmpty()){
			Connection realConn =DriverManager.getConnection(url);
			MyConnection myConnection =new MyConnection(realConn,this);
			return myConnection;
		}else{
			Connection realConn =DriverManager.getConnection(url,SQL_account,SQL_key);
			MyConnection myConnection =new MyConnection(realConn,this);
			return myConnection;
		}
		/*Connection realConn =DriverManager.getConnection(url);
		MyConnection myConnection =new MyConnection(realConn,this);
		return myConnection;*/
		
		//加到配置文件中;integratedSecurity=TRUE;
		
	}

}
