package com.gasinfo.action;

import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.config.Configuration;
import com.gasinfo.filesZip.testZip;
import com.gasinfo.server.indexdatebase.IncreaseData;

public class updateIncreaseDataAction implements ServletRequestAware,Action {
	
	private HttpServletRequest request;

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	
	public String increaseData() {
		JFileChooser chooser = new JFileChooser();
		JPanel parent = new JPanel();
		int returnVal = chooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("选择的文件地址为："
					+ chooser.getSelectedFile().getPath());
		}else{
			return SUCCESS;
		}
		File zipfile=new File(chooser.getSelectedFile().getPath());
		String Url = this.GetURL();
		try {
			
			new testZip().unZipFiles(zipfile, Url);
			new IncreaseData().indexAnddatebase();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "导入失败！", "System Message! ", 1);
			e.printStackTrace();			
		}
		JOptionPane.showMessageDialog(null, "导入成功！", "System Message! ", 1);
		return SUCCESS;
	}
	
	public String exportData() {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if (startTime == null || startTime.equals("") && endTime == null
				|| endTime.equals("")) {
			startTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		Configuration rc = new Configuration("daoconfig.properties");
		String exportURL = rc.getValue("RootURL_exSelectedWeb");
		ArrayList<String> fitDoc = new testZip().copyFiles(startTime, endTime,
				exportURL);
		if(fitDoc.size()==0){
			JOptionPane.showMessageDialog(null, "对不起，该时间段内无更新数据 ！", "System Message! ", 0);
			return SUCCESS;
		}
		File[] srcfile = new File[fitDoc.size()];
		for (int i = 0; i < fitDoc.size(); i++) {
			srcfile[i] = new File(fitDoc.get(i));
		}
		JFileChooser chooser = new JFileChooser();
		JPanel parent = new JPanel();
		chooser.setSelectedFile(new File("增量包.zip"));
		int returnVal = chooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("选择的文件地址为："
					+ chooser.getSelectedFile().getPath());
		}else{
			return SUCCESS;
		}
		File zipfile = new File(chooser.getSelectedFile().getPath());
		try {
			new testZip().ZipFiles(zipfile, "", srcfile);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "导出失败！", "System Message! ", 0);
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "导出成功！", "System Message! ", 1);
		return SUCCESS;
	}
	
	// 得到exSelectWeb最新文件夹位置
	public String GetURL() {
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_IncreaseData = rc.getValue("RootURL_IncreaseData");
		File Rootfile = new File(RootURL_IncreaseData);
		File[] files = Rootfile.listFiles();
		ArrayList<Long> FileNameList = new ArrayList<Long>();
		for (int i = 0; i < files.length; i++) {
			FileNameList.add(Long.valueOf(files[i].getName()));
		}
		Long MaxName = FileNameList.get(0);
		for (int i = 1; i < FileNameList.size(); i++) {
			if (MaxName < FileNameList.get(i)) {
				MaxName = FileNameList.get(i);
			}
		}
		System.out.print(RootURL_IncreaseData + File.separator + MaxName);
		return RootURL_IncreaseData + File.separator + MaxName;
	}

	
}
