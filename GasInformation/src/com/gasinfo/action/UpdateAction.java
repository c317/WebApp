package com.gasinfo.action;

import java.util.ArrayList;

import javax.faces.application.Application;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.mortbay.jetty.servlet.SessionManager.Session;

import com.gasinfo.config.xmlOperate;
import com.gasinfo.model.SelectModel;
import com.gasinfo.model.UpdateModel;
import com.gasinfo.server.heritrix.StartHeritrix;
import com.gasinfo.server.indexdatebase.indexdatebase;
import com.gasinfo.server.webselect.SelectAll;

public class UpdateAction extends UpdateModel implements ServletRequestAware,
		Action {

	public String Update() {

		String strWebId = this.getWebIDs();
		// “，”后面带了一个空格
		String[] strsWebId = strWebId.split(",");
		int[] intsWebId = new int[strsWebId.length];
		for (int i = 0; i < strsWebId.length; i++) {
			// trim()截去前后空格
			intsWebId[i] = Integer.valueOf(strsWebId[i].trim());
		}

		// 读取xml文档
		xmlOperate xmloperator = new xmlOperate("HeritrixXml.xml");

		try {
			new StartHeritrix().startTaskByModAndWeb(
					Integer.valueOf(this.getModuleID()), intsWebId);
//			for (int j = 0; j < intsWebId.length; j++) {
//				String taskVersion = xmloperator.getTaskVersionByWebId(String
//						.valueOf(intsWebId[j]));
//				new SelectAll().SelectAll(String.valueOf(intsWebId[j]),
//						Integer.valueOf(taskVersion) - 1);
//				new indexdatebase().indexAnddatebase();
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String UpdateAll() {
		// 读取xml文档
		xmlOperate xmloperator = new xmlOperate("HeritrixXml.xml");
		//得到最大的webID号
		String strMaxWebId = xmloperator.getMaxWebId();
		int intMaxWebId = Integer.valueOf(strMaxWebId);
		StartHeritrix startHeritrix = new StartHeritrix();

		try {
			for (int j = 1; j <= intMaxWebId; j++) {
				//如果爬取操作执行成功才会进行下一步处理
				if(startHeritrix.startTaskByWebId(String.valueOf(j))){
					String taskVersion = xmloperator.getTaskVersionByWebId(String
							.valueOf(j));
					new SelectAll().SelectAll(String.valueOf(j),
							Integer.valueOf(taskVersion) - 1);
					new indexdatebase().indexAnddatebase();
				}else{
					continue;
				}	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public static void main(String args[]) throws Exception {

		String strWebId = "1, 2,3";
		// “，”后面带了一个空格
		String[] strsWebId = strWebId.split(",");
		int[] intsWebId = new int[strsWebId.length];
		for (int i = 0; i < strsWebId.length; i++) {
			// trim()截去前后空格
			intsWebId[i] = Integer.valueOf(strsWebId[i].trim());
			System.out.println(strsWebId[i]);
			System.out.println("int" + intsWebId[i]);
		}

		// int[] intsWebId = new int[] { 1 };
		// String[] strsWebId = this.getWebIDs();
		//
		// for(int i =0 ; i<this.getWebIDs().length ; i++){
		// intsWebId[i] = Integer.valueOf(strsWebId[i]);
		// }
		//

		// xmlOperate xmloperator = new xmlOperate("HeritrixXml.xml");

		// new StartHeritrix().startTaskByModAndWeb(1, intsWebId);
		// for(int j=0 ; j<this.getWebIDs().length;j++ ){
		// String taskVersion = xmloperator.getTaskVersionByWebId(strsWebId[j]);
		// new SelectAll().SelectAll(strsWebId[j] ,
		// Integer.valueOf(taskVersion)-1);
		// new indexdatebase().indexdatebase();
		// new SelectAll().SelectAll("1", 3);
		// new indexdatebase().indexdatebase();
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

}
