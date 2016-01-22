package com.gasinfo.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.server.indexdatebase.indexdatebase;

public class RukuAction implements ServletRequestAware, Action {
	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest arg0) {
		
	} 
	public String ruku() {
		try {
//			new indexdatebase().indexAnddatebase();
			System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

}
