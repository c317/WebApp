package com.gasinfo.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;  
import java.io.InputStream;  
import java.io.UnsupportedEncodingException;
import java.text.ParseException;  

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  
import org.apache.commons.io.FileUtils;  
import org.apache.struts2.ServletActionContext;  
import org.apache.struts2.interceptor.ServletRequestAware;
  
import com.gasinfo.config.Configuration;
import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Newsdao;
import com.opensymphony.xwork2.ActionContext;  
  
public class FileUploadAction implements ServletRequestAware, Action  {  
      
        //上传用  
        private File pic;     
        private String picFileName;  
        private String picContentType;  
        private InputStream inputStream;  
        
        Configuration rc = new Configuration("daoconfig.properties");
		String realpath = rc.getValue("RootURL_uploadImageFile");
		
		private HttpServletRequest request;

		public void setServletRequest(HttpServletRequest hsr) {
			request = hsr;
		}

		
    /** 
     * 功能：文件上传 
     * 
     * @throws IOException 
     * @throws ParseException 
     */  
    public void upload(int i) throws ParseException{  
        try {    
        	
        	if(i == 1){
        		realpath = rc.getValue("RootURl_uploadHotFile");
        	}else if(i == 2){
        		realpath = rc.getValue("RootURL_uploadThemeFile");
        	}else {
        		realpath = rc.getValue("RootURL_uploadImageFile");
        	}  		
        	
            System.out.println("上传到服务器的地址realpath: "+realpath);   

            String lj = realpath+"\\"+picFileName;  
            System.out.println("具体的地址路径"+lj);  
            
            if (pic != null) {              
                File savefile = new File(new File(realpath), picFileName);              
                if (!savefile.getParentFile().exists())   
                    savefile.getParentFile().mkdirs();    
                FileUtils.copyFile(pic, savefile);        		
                ActionContext.getContext().put("message", "文件上传成功");       
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }              
    }  
    
    /**
     * 把上传的文件存入数据库
     * @return
     * @throws UnsupportedEncodingException
     * @throws ParseException
     */
    public String putInStorage() throws UnsupportedEncodingException, ParseException{								
		    String title = new String(request.getParameter("title")
					.getBytes("iso-8859-1"), "utf-8");
		    //System.out.println(title);
		    String department = request.getParameter("department");
		    //System.out.println(department);
		    
		    int module = Integer.valueOf(request.getParameter("module"));
		    //System.out.println(module);
		    String fileName = new String(request.getParameter("fileName")
		    		.getBytes("iso-8859-1"), "utf-8"); 
		    int last = fileName.lastIndexOf("\\");
		    String picURL = fileName.substring((last + 1),fileName.length());
		    //System.out.println(picURL);

		    upload(module);
		    
		    Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
			newsdao.uploadFileInputStorage(title, department, module, picURL);
			return String.valueOf(module);  
    }
    
    /**
     * 上传成功的图片在后台显示
     * 
     * @return
     */
    public String viewImages() { 
    	
    	realpath = rc.getValue("RootURL_uploadImageFile");
    	//pic为读取到图片的存储路径（数据库中存储的字段值）
    	HttpServletRequest request=ServletActionContext. getRequest(); 
    	String pic = request. getParameter("picFileName");

    	HttpServletResponse response = ServletActionContext. getResponse(); 
    	ServletOutputStream out = null; 
    	FileInputStream ips = null; 
    	try { 
	    		
	    	// picFilename为本地图片存放路径， imagePath为图片的真实路径 
	    	String imagePath = realpath +"/"+ pic; 
	    	ips = new FileInputStream(new File(imagePath));
	    	response.setContentType("multipart/form-data");
	    	out = response.getOutputStream();
	    	//读取文件流
	    	int i= 0;
	    	byte[]  buffer  = new byte [4096];
	    	while((i = ips.read(buffer))!=-1){
	    		//写文件流
	    		out.write(buffer,0,i);
	    	}
	    	out.flush();
	    	ips.close();
    	}catch (Exception e){
    		if(out != null){
	    		try{
	    			out.close();
		    	}catch(Exception e1){
		    		e1.printStackTrace();
		    	}
    		}
    	}
    	return null;
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

}  
