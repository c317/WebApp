package com.gasinfo.action;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;



import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.config.Configuration;
import com.gasinfo.server.heritrix.AddWebsite;
import com.gasinfo.server.webselect.SelectWebNodes;
import com.opensymphony.xwork2.ActionContext;

public class UpLoadAction implements ServletRequestAware, Action {
	private HttpServletRequest request;
	private String title;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}

	public String execute() throws Exception {
		
		AddWebsite aw = new AddWebsite();
		ActionContext actioncontext = ActionContext.getContext();
		
		SelectWebNodes selectwebnodes = new SelectWebNodes();
		ArrayList<HashMap<String, String>> listAllWebs = selectwebnodes.GetAllWebs();
		request.setAttribute("listAllWebs", listAllWebs);
		
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_XML = rc.getValue("RootURL_XML");
		String tempPath = RootURL_XML+File.separator+"temp.xml";
		request.setCharacterEncoding("UTF-8");
		File file = new File(tempPath);
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(
				file), "UTF-8");
		BufferedWriter writer = new BufferedWriter(write);
		File fileRequest = (File) request.getAttribute("upload");
		FileInputStream fileInputStream = new FileInputStream(fileRequest);
		FileReader fileReader = new FileReader(fileRequest);
		int tempbyte;
		while ((tempbyte = fileReader.read()) != -1) {
			writer.write(tempbyte);
		}
		writer.close();
		fileInputStream.close();
		if(selectwebnodes.EnterXML(tempPath,listAllWebs).equals("sucess")){
			file.delete();
			return SUCCESS;
		}
		if(selectwebnodes.EnterXML(tempPath,listAllWebs).equals("error")){
			file.delete();
			return ERROR;
		}
		return ERROR;
	}
}
