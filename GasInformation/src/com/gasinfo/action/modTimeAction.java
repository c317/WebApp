package com.gasinfo.action;

import com.gasinfo.config.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;

public class modTimeAction implements ServletRequestAware, Action {
	private String strHour;
	private String strMinute;

	public String getStrHour() {
		return strHour;
	}

	public void setStrHour(String strHour) {
		this.strHour = strHour;
	}

	public String getStrMinute() {
		return strMinute;
	}

	public void setStrMinute(String strMinute) {
		this.strMinute = strMinute;
	}

	public String modTime() {
		int intHour = Integer.valueOf(strHour);
		int intMinute = Integer.valueOf(strMinute);

		int intTime = (intHour * 3600) + (intMinute * 60);
		String strTime = String.valueOf(intTime);
		Configuration rc = new Configuration("daoconfig.properties");
		rc.setValue("Time_Heritrix", strTime);
		// System.out.println(rc.getValue("Time_Heritrix"));
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
}
