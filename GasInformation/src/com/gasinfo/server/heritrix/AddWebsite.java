package com.gasinfo.server.heritrix;  
import com.gasinfo.config.Configuration;
import com.gasinfo.config.xmlOperate;

import java.io.File;  
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.management.InvalidAttributeValueException;  

import org.archive.crawler.event.CrawlStatusListener;  
import org.archive.crawler.framework.CrawlController;  
import org.archive.crawler.framework.exceptions.InitializationException;  
import org.archive.crawler.settings.XMLSettingsHandler;  

//import IndexDatebase.IndexDatebase;
import com.gasinfo.server.indexdatebase.*;
import com.gasinfo.server.webselect.*;
      
public class AddWebsite {  
		
		static Configuration rc = new Configuration("daoconfig.properties");
		static String RootURL_model = rc.getValue("RootURL_model");
		static String RootURL_exjobs = rc.getValue("RootURL_exjobs");
		
		static xmlOperate xmloperator = new xmlOperate("HeritrixXml.xml");
		static CopyFile copyfile = new CopyFile();
		
		//传入字符串moduleIds
		public void addWeb(String webName , String seed , String[] moduleIds) throws IOException{
//			Date datenow = new Date();   
//			SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
//		    String strDateNow = dateformat.format(datenow);
		    //把时间加到webName后面 ，形成唯一标识,(废弃)
//			String strTargetFilepath = RootURL_exjobs + File.separator + webName + strDateNow + File.separator + "1";
			String strTargetFilepath = RootURL_exjobs + File.separator + webName + File.separator + "1";
			
			copyfile.copyFileForHeritrix(RootURL_model, strTargetFilepath);
			copyfile.modifySeedsTXT(strTargetFilepath, seed);
			//增加网站节点
			xmloperator.addWebsite(webName , seed);
			
			//将新增的网站节点添加到对应的模块后，取出模块对应的现有网站id值
			String maxWebId = xmloperator.getMaxWebId();
			for(int i = 0 ; i < moduleIds.length ; i++){
				//将新增的网站id加到现有的模块id后，以“，”号隔开
				String newWebId = xmloperator.getWebIdByModuleId(moduleIds[i].trim()) + "," + maxWebId;
				//将该模块对应的新的id保存
				xmloperator.setWebIdByModuleId(moduleIds[i].trim(), newWebId);
			}
			
			
		}
		
		//传入字符moduleIds  重载addWeb函数
		public void addWeb(String webName , String seed , String moduleIds) throws IOException{

			String strTargetFilepath = RootURL_exjobs + File.separator + webName + File.separator + "1";
			
			copyfile.copyFileForHeritrix(RootURL_model, strTargetFilepath);
			copyfile.modifySeedsTXT(strTargetFilepath, seed);
			//增加网站节点
			xmloperator.addWebsite(webName , seed);
			
			//将新增的网站节点添加到对应的模块后，取出模块对应的现有网站id值
			String maxWebId = xmloperator.getMaxWebId();
			
			//将新增的网站id加到现有的模块id后，以“，”号隔开
			String newWebId = xmloperator.getWebIdByModuleId(moduleIds.trim()) + "," + maxWebId;
			//将该模块对应的新的id保存
			xmloperator.setWebIdByModuleId(moduleIds.trim(), newWebId);
			
		}
		
		public static void main(String args[]) throws IOException{
			AddWebsite aw = new AddWebsite();
			String[] strtest = {"1","  2","  3","4  ","  5  "}; 
			aw.addWeb("中文测试2", "www.iike.com",strtest);
		}
}
		