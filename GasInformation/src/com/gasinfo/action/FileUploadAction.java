package com.gasinfo.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.gasinfo.config.Configuration;
import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Newsdao;

public class FileUploadAction implements ServletRequestAware, Action {

	// 上传用
	private File pic;
	private String picFileName;
	private String picContentType;
	private InputStream inputStream;	
	private String fileName;

	private HttpServletRequest request;
	
	Configuration rc = new Configuration("daoconfig.properties");
	String realpath = rc.getValue("RootURL_uploadImageFile");

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 上传并存入数据库
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	public String putInStorage() throws UnsupportedEncodingException,
			ParseException {
		String title = request.getParameter("title");
		String department = request.getParameter("department");
		int module = Integer.valueOf(request.getParameter("module"));
		String fileName = request.getParameter("fileName");
		String picName = new String(fileName.getBytes("iso-8859-1"), "utf-8");
		String message = "";

		// 不同的浏览器，picName获取的值不一样，统一截取需要的文件名+后缀  + 
		int last = picName.lastIndexOf("\\");
		int end = picName.lastIndexOf(".");
		picName = picName.substring((last + 1), picName.length());
		String extensionName = picName.substring((end), picName.length());
		//上传到服务器中的文件名
		String picURL = System.currentTimeMillis() + extensionName;
	
		if (upload(module,picURL)) {
			// 写入数据库
			Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
			newsdao.uploadFileInputStorage(title, department, module, picURL,picName);
			message = "上传成功！";
		} else {
			message = "上传失败！";
		}

		// 查询上传文件记录
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<HashMap<String, Object>> fileList = newsdao
				.getFileByType(module);
		request.setAttribute("fileList", fileList);
		request.setAttribute("tipMessage", message);
		return String.valueOf(module);
	}

	/**
	 * 文件上传
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public boolean upload(int i,String picURL) throws ParseException {
		try {
			/**
			 * RootURL_uploadImageFile 	上传图片文件根目录 
			 * RootURl_uploadHotFile 	上传热点文件根目录
			 * RootURL_uploadThemeFile 	上传专题文件根目录 
			 * 可在daoconfig.properties 	配置文件中修改
			 */
			switch (i) {
			case 1:
				realpath = rc.getValue("RootURl_uploadHotFile");
				break;
			case 2:
				realpath = rc.getValue("RootURL_uploadThemeFile");
				break;
			default:
				realpath = rc.getValue("RootURL_uploadImageFile");
				break;
			}

			picFileName = picURL;

			if (pic != null) {
				File savefile = new File(new File(realpath), picFileName);
				if (!savefile.getParentFile().exists())
					savefile.getParentFile().mkdirs();
				FileUtils.copyFile(pic, savefile);
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 上传成功后显示的列表
	 * 
	 * @return
	 */
	public String showUploadFile() {
		int module = Integer.valueOf(request.getParameter("module"));
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<HashMap<String, Object>> fileList = newsdao
				.getFileByType(module);
		request.setAttribute("fileList", fileList);
		return String.valueOf(module);
	}

	/**
	 * 图片上传成功，显示缩略图
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String viewImages() throws UnsupportedEncodingException {

		realpath = rc.getValue("RootURL_uploadImageFile");
		// pic为读取到图片的存储路径（数据库中存储的字段值）
		HttpServletRequest request = ServletActionContext.getRequest();
		String pic = request.getParameter("picFileName");
		pic = new String(pic.getBytes("iso-8859-1"), "UTF-8");

		
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletOutputStream out = null;
		FileInputStream ips = null;
		try {
			//imagePath为图片的真实路径
			String imagePath = realpath + "/" + pic;
			ips = new FileInputStream(new File(imagePath));
			response.setContentType("multipart/form-data");
			out = response.getOutputStream();
			// 读取文件流
			int i = 0;
			byte[] buffer = new byte[4096];
			while ((i = ips.read(buffer)) != -1) {
				// 写文件流
				out.write(buffer, 0, i);
			}
			out.flush();
			ips.close();
		} catch (Exception e) {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
}
