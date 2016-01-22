package com.gasinfo.action;



import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.chart.JFreeChart;
import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Newsdao;

public class UpdateWord implements ServletRequestAware,Action{
	
	private HttpServletRequest request;
	private JFreeChart chart;
	private String output;

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public void setServletRequest(HttpServletRequest hsr) {
		this.request = hsr;
	}
	
	public String updateWord(){
		Newsdao newsdao=DaoFactory.getInstance().getNewsDao();
		newsdao.updateHotword();
		return SUCCESS;
	}
	
	public String updateblackOrWhite(){
		String i=output;
		Newsdao newsdao=DaoFactory.getInstance().getNewsDao();
		newsdao.updateblacklist(Integer.valueOf(output));
		return SUCCESS;
	}

}
