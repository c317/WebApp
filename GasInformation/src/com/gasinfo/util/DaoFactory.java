package com.gasinfo.util;

import com.gasinfo.config.Configuration;


public class DaoFactory {
	private static Newsdao newsdao =null;
	private static UsersDao usersDao =null;
	private static DaoFactory instance =new DaoFactory();
	
	private DaoFactory(){
		
		try {
			Configuration rc = new Configuration("daoconfig.properties");
			String newsDaoClass = rc.getValue("newsDaoClass");
			Class<?> clazz = Class.forName(newsDaoClass);
			newsdao =(Newsdao) clazz.newInstance();
			usersDao =(UsersDao) clazz.newInstance();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public  Newsdao getNewsDao(){
		return newsdao;
	}

	public UsersDao getUsersDao() {
		// TODO Auto-generated method stub
		return usersDao;
	}
}