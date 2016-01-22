package com.gasinfo.server.heritrix;

import java.io.File;  

import javax.management.InvalidAttributeValueException;  

import org.archive.crawler.event.CrawlStatusListener;  
import org.archive.crawler.framework.CrawlController;  
import org.archive.crawler.framework.exceptions.InitializationException;  
import org.archive.crawler.settings.XMLSettingsHandler;  

//import SelectIndexDatabase.SelectIndexDatabase;
 
public class StartOnlyTask {  
 
   public static void main(String[] args) throws InterruptedException {  

       String orderFile = "E:\\workspacesql\\exjobs\\中国国土资源部\\34\\order.xml"; 
       File file = null;   
       
       CrawlStatusListener listener = null;//
       XMLSettingsHandler handler = null;  //
       CrawlController controller = null;  //
       try {  
           file=new File(orderFile);  
           handler = new XMLSettingsHandler(file);  
           handler.initialize();//
 
           controller = new CrawlController();//  
           controller.initialize(handler);//
 
           if (listener != null) {  
               controller.addCrawlStatusListener(listener);//
           }  
           controller.requestCrawlStart();//
             
           /*
            * 
            */  
           while (true) {  
               if (controller.isRunning() == false) {  
                   break;  
               }  
               Thread.sleep(1000);  
           }  
             
           //
           controller.requestCrawlStop();  
 
       } catch (Exception e) {   
    	   e.printStackTrace();  
       } 
//		try {
//			new SelectIndexDatabase().SelectIndexDatabase(1,1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
       
   }  
 

} 
