//package com.gasinfo.server.heritrix;
//
//import com.gasinfo.config.Configuration;
//import com.gasinfo.config.xmlOperate;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.archive.crawler.event.CrawlStatusListener;
//import org.archive.crawler.framework.CrawlController;
//import org.archive.crawler.framework.exceptions.InitializationException;
//import org.archive.crawler.settings.XMLSettingsHandler;
//
//public class StartHeritrix {
//
//	public StartHeritrix() {
//	}
//
//	static Configuration rc = new Configuration("daoconfig.properties");
//	static String RootURL_exjobs = rc.getValue("RootURL_exjobs");
//
//	static xmlOperate xmloperator = new xmlOperate("HeritrixXml.xml");
//	static XmlMod xmlmod = new XmlMod();
//	static CopyFile copyfile = new CopyFile();
//
//	public void startTaskByModAndWeb(int ModuleId, int[] num_wangzhan)
//			throws Exception {
//		// 通过模块的id找到模块对应的网站ID
//		String strWebIds = xmloperator.getWebIdByModuleId(String
//				.valueOf(ModuleId));
//		// webId存储方式为1,2,3 故要用“,”将他们分开 ，然后用字符数字存放各个webId
//		String[] strWebId = strWebIds.split(",");
//
//		for (int i = 0; i < strWebId.length; i++) {
//			// System.out.println(strWebId[i]);
//			// 如果传入的要爬取的网站ID不包括某些ID时，就不爬取该网站
//			if (prestrIsInlastints(strWebId[i], num_wangzhan)) {
//
//				String webName = xmloperator.getWebNameByWebId(strWebId[i]);
//				String taskVersion = xmloperator
//						.getTaskVersionByWebId(strWebId[i]);
//				String strSourceFilepath = RootURL_exjobs + File.separator
//						+ webName + File.separator + taskVersion;
//				// 设置最大爬取时间
//				xmlmod.setMaxTime(strSourceFilepath);
//				// 开始爬取任务
//				startTask(strSourceFilepath);
//
//				/*
//				 * 当前任务爬取完成后，马上基于当前的文件夹复制出下一个文件夹 要复制出来的文件夹就是上一个文件夹名加1
//				 */
//				String newTaskVersion = String.valueOf(Integer
//						.valueOf(taskVersion) + 1);
//				String strTargetFilepath = RootURL_exjobs + File.separator
//						+ webName + File.separator + newTaskVersion;
//				// 基于此次任务，复制出新的文件夹为下一次爬取工作做准备
//				copyfile.copyFileForHeritrix(strSourceFilepath,
//						strTargetFilepath);
//				// 更新Hetrix.xml配置文件里的对应网站的taskVersion值
//				xmloperator.setTaskVersionByWebId(strWebId[i], newTaskVersion);
//
//			}
//		}
//
//	}
//
//	public boolean startTaskByWebId(String num_wangzhan) throws Exception {
//		// 判断该ID是否存在
//		if ((xmloperator.getWebNameByWebId(num_wangzhan)) != (null)) {
//			String webName = xmloperator.getWebNameByWebId(num_wangzhan);
//			String taskVersion = xmloperator
//					.getTaskVersionByWebId(num_wangzhan);
//			String strSourceFilepath = RootURL_exjobs + File.separator
//					+ webName + File.separator + taskVersion;
//			// 设置最大爬取时间
//			xmlmod.setMaxTime(strSourceFilepath);
//			// 开始爬取任务
//			startTask(strSourceFilepath);
//
//			/*
//			 * 当前任务爬取完成后，马上基于当前的文件夹复制出下一个文件夹 要复制出来的文件夹就是上一个文件夹名加1
//			 */
//			String newTaskVersion = String
//					.valueOf(Integer.valueOf(taskVersion) + 1);
//			String strTargetFilepath = RootURL_exjobs + File.separator
//					+ webName + File.separator + newTaskVersion;
//			// 基于此次任务，复制出新的文件夹为下一次爬取工作做准备
//			copyfile.copyFileForHeritrix(strSourceFilepath, strTargetFilepath);
//			// 更新Hetrix.xml配置文件里的对应网站的taskVersion值
//			xmloperator.setTaskVersionByWebId(num_wangzhan, newTaskVersion);
//			return true;
//		} else{
//			return false;
//		}
//			
//
//	}
//
//	private boolean prestrIsInlastints(String strWebId, int[] num_wangzhan) {
//		boolean result = false;
//		int intWebId = Integer.valueOf(strWebId);
//		for (int i = 0; i < num_wangzhan.length; i++) {
//			if (intWebId == num_wangzhan[i]) {
//				result = true;
//			}
//		}
//		return result;
//	}
//
//	public void startTask(String taskpath) {
//		String orderPath = taskpath + File.separator + "order.xml";
//		File file = null; //
//
//		CrawlStatusListener listener = null;//
//		XMLSettingsHandler handler = null; //
//		CrawlController controller = null; //
//		try {
//			file = new File(orderPath);
//			handler = new XMLSettingsHandler(file);
//			handler.initialize();//
//
//			controller = new CrawlController();//
//			controller.initialize(handler);//
//
//			if (listener != null) {
//				controller.addCrawlStatusListener(listener);//
//			}
//			controller.requestCrawlStart();//
//
//			/*
//	         * 
//	         */
//			while (true) {
//				if (controller.isRunning() == false) {
//					break;
//				}
//				Thread.sleep(1000);
//			}
//			controller.requestCrawlStop();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//
//
//	public static void main(String args[]) {
//	
//	}
//}
package com.gasinfo.server.heritrix;

import com.gasinfo.config.Configuration;
import com.gasinfo.config.xmlOperate;
import com.gasinfo.server.indexdatebase.indexdatebase;
import com.gasinfo.server.webselect.SelectAll;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

import org.archive.crawler.event.CrawlStatusListener;
import org.archive.crawler.framework.CrawlController;
import org.archive.crawler.framework.exceptions.InitializationException;
import org.archive.crawler.settings.XMLSettingsHandler;

public class StartHeritrix {

	public StartHeritrix() {
	}

	static Configuration rc = new Configuration("daoconfig.properties");
	static String RootURL_exjobs = rc.getValue("RootURL_exjobs");

	static xmlOperate xmloperator = new xmlOperate("HeritrixXml.xml");
	static XmlMod xmlmod = new XmlMod();
	static CopyFile copyfile = new CopyFile();

	public void startTaskByModAndWeb(int ModuleId, int[] num_wangzhan)
			throws Exception {
		// 通过模块的id找到模块对应的网站ID
		String strWebIds = xmloperator.getWebIdByModuleId(String
				.valueOf(ModuleId));
		// webId存储方式为1,2,3 故要用“,”将他们分开 ，然后用字符数字存放各个webId
		String[] strWebId = strWebIds.split(",");

		for (int i = 0; i < strWebId.length; i++) {
			// System.out.println(strWebId[i]);
			// 如果传入的要爬取的网站ID不包括某些ID时，就不爬取该网站
			if (prestrIsInlastints(strWebId[i], num_wangzhan)) {

				String webName = xmloperator.getWebNameByWebId(strWebId[i]);
				// 获取对应网站文件夹下的最大的文件夹编号
				String taskVersion = GetMaxVersion(RootURL_exjobs
						+ File.separator + webName);
				System.out.println(taskVersion + "aaaaaaaaaaaaaaaaa");
				// strSourceFilepath = E:\workspacesql\exjobs\中国石油新闻网\39
				String strSourceFilepath = RootURL_exjobs + File.separator
						+ webName + File.separator + taskVersion;
				/*
				 * 文件夹检测功能：在此处判断文件夹中是否只含有两个文件，如果是继续进行下面，否则自动添加两个文件，然后最大文件编号加一。
				 */

				File[] file = (new File(strSourceFilepath)).listFiles();
				if (file.length != 2) {
					String newTaskVersion = String.valueOf(Integer
							.valueOf(taskVersion) + 1);
					String strTargetFilepath = RootURL_exjobs + File.separator
							+ webName + File.separator + newTaskVersion;
					copyfile.copyFileForHeritrix(strSourceFilepath,
							strTargetFilepath);
					strSourceFilepath = strTargetFilepath;
					taskVersion = newTaskVersion;
					System.out.println("更新网站" + webName + "时配置文件有缺损，已经修复完成；");

				}
				// 设置最大爬取时间
				xmlmod.setMaxTime(strSourceFilepath);
				// 开始爬取任务
				startTask(strSourceFilepath);

				/*
				 * 当前任务爬取完成后，马上基于当前的文件夹复制出下一个文件夹 要复制出来的文件夹就是上一个文件夹名加1
				 */
				String newTaskVersion = String.valueOf(Integer
						.valueOf(taskVersion) + 1);
				String strTargetFilepath = RootURL_exjobs + File.separator
						+ webName + File.separator + newTaskVersion;
				// 基于此次任务，复制出新的文件夹为下一次爬取工作做准备
				copyfile.copyFileForHeritrix(strSourceFilepath,
						strTargetFilepath);
				// 更新Hetrix.xml配置文件里的对应网站的taskVersion值
				xmloperator.setTaskVersionByWebId(strWebId[i], newTaskVersion);
				System.out.println(webName+"第一次抓取结束。");
				/*
				 *二次抓取功能：一次爬取结束后对文件夹进行检测，查看是否存在mirror文件夹，如果有则停止抓取，否则则再次进行抓取。
				 */
				if (checkMirrorExits(strSourceFilepath) == false) {
					System.out.println(webName+"第一次抓取未抓取到mirror文件，现在进行二次抓取。");
					xmlmod.setMaxTime(strTargetFilepath);
					//二次抓取启动
					startTask(strTargetFilepath);
					String newTaskVersion2 = String.valueOf(Integer
							.valueOf(taskVersion) + 2);
					String strTargetFilepath2 = RootURL_exjobs + File.separator
							+ webName + File.separator + newTaskVersion2;
					copyfile.copyFileForHeritrix(strTargetFilepath,
							strTargetFilepath2);
					xmloperator.setTaskVersionByWebId(strWebId[i],
							newTaskVersion2);
					System.out.println(webName+"第二次抓取结束。");

				}
				String taskVersionEnd = xmloperator.getTaskVersionByWebId(String
						.valueOf(strWebId[i]));
				new SelectAll().SelectAll(String.valueOf(strWebId[i]),
						Integer.valueOf(taskVersionEnd)-1);
				System.out.println(webName+"清洗完成！");
				new indexdatebase().indexAnddatebase();
				System.out.println(webName+"更新完成！");

			}
		}
	}
	public boolean checkMirrorExits(String strSourceFilepath) {

		File[] FileMirrorExits = (new File(strSourceFilepath)).listFiles();
		for (int m = 0; m < FileMirrorExits.length; m++) {
			if (FileMirrorExits[m].equals("mirror")) {
				return true;
			}

		}
		return false;
	}
	public boolean startTaskByWebId(String num_wangzhan) throws Exception {
		// 判断该ID是否存在
		if ((xmloperator.getWebNameByWebId(num_wangzhan)) != (null)) {
			String webName = xmloperator.getWebNameByWebId(num_wangzhan);
//			String taskVersion = xmloperator
//					.getTaskVersionByWebId(num_wangzhan);
			String taskVersion = GetMaxVersion(RootURL_exjobs + File.separator
					+ webName);
			String strSourceFilepath = RootURL_exjobs + File.separator
					+ webName + File.separator + taskVersion;
			// 设置最大爬取时间
			xmlmod.setMaxTime(strSourceFilepath);
			// 开始爬取任务
			startTask(strSourceFilepath);

			/*
			 * 当前任务爬取完成后，马上基于当前的文件夹复制出下一个文件夹 要复制出来的文件夹就是上一个文件夹名加1
			 */
			String newTaskVersion = String
					.valueOf(Integer.valueOf(taskVersion) + 1);
			String strTargetFilepath = RootURL_exjobs + File.separator
					+ webName + File.separator + newTaskVersion;
			// 基于此次任务，复制出新的文件夹为下一次爬取工作做准备
			copyfile.copyFileForHeritrix(strSourceFilepath, strTargetFilepath);
			// 更新Hetrix.xml配置文件里的对应网站的taskVersion值
			xmloperator.setTaskVersionByWebId(num_wangzhan, newTaskVersion);
			return true;
		} else{
			return false;
		}
			

	}
	// 得到exSelectWeb最新文件夹位置
		public String GetMaxVersion(String sFilepath) {
			File Rootfile = new File(sFilepath);
			File[] files = Rootfile.listFiles();
			ArrayList<Long> FileNameList = new ArrayList<Long>();
			int iFilesLen = files.length;
			for (int i = 0; i < iFilesLen; i++) {
				FileNameList.add(Long.valueOf(files[i].getName()));
			}
			Long MaxName = FileNameList.get(0);
			int iFileNameListSize = FileNameList.size();
			for (int i = 1; i < iFileNameListSize; i++) {
				if (MaxName < FileNameList.get(i)) {
					MaxName = FileNameList.get(i);
				}
			}
		
			return String.valueOf(MaxName);

		}

	private boolean prestrIsInlastints(String strWebId, int[] num_wangzhan) {
		boolean result = false;
		int intWebId = Integer.valueOf(strWebId);
		for (int i = 0; i < num_wangzhan.length; i++) {
			if (intWebId == num_wangzhan[i]) {
				result = true;
			}
		}
		return result;
	}

	public void startTask(String taskpath) {
		//File order=new File("order.xml"); //这个我放在了根目录下的还有另外的一个文件seeds.txt
		String orderPath = taskpath + File.separator + "order.xml";
		File order = null;  
		XMLSettingsHandler orderHandler=null;//声明读取order.xml
		  CrawlController controller=null; //控制
		  CrawlStatusListener listener=null;//监听  
		        try {
		          //封装order.xml
		        	
		        	order =new File(orderPath);
		          if(!order.exists()){
		           System.out.println("配置文件找不到结束");
		           System.exit(0);
		          }
		    //libohan构造一个XMLSettingsHandler对象，将order.xml属性信息装入。
		          orderHandler=new XMLSettingsHandler(order);		    
		          orderHandler.initialize();
		    
		    //控制器加载
		    controller=new CrawlController();
		    controller.initialize(orderHandler);
		    if(listener!=null){
		     controller.addCrawlStatusListener(listener);
		    }   
		    //调用requestCrawlStart();启动线程池和frontier
		    controller.requestCrawlStart();    
		    while(true){
		     if(controller.isRunning()==false){
		      break;
		     }
		     Thread.sleep(1000);
		    }  
		    controller.requestCrawlStop(); 
		   } catch (InvalidAttributeValueException e) {
		    e.printStackTrace();
		    System.out.println("加载配置文件失败");
		   } catch (InitializationException e) {
		    e.printStackTrace();
		    System.out.println("控制器加载配置失败");
		   } catch (InterruptedException e) {
		    e.printStackTrace();
		    System.out.println("睡眠抛出异常");
		   } 
//		String orderPath = taskpath + File.separator + "order.xml";
//		File file = null; //
//
//		CrawlStatusListener listener = null;//
//		XMLSettingsHandler handler = null; //
//		CrawlController controller = null; //
//		try {
//			file = new File(orderPath);
//			handler = new XMLSettingsHandler(file);
//			handler.initialize();//
//
//			controller = new CrawlController();//
//			controller.initialize(handler);//
//
//			if (listener != null) {
//				controller.addCrawlStatusListener(listener);//
//			}
//			System.out.println("<<<<<<<<爬虫开始>>>>>>>>>");
//			controller.requestCrawlStart();//
//
//			/*
//	         *如果爬虫一直在运行则等待 
//	         */
//			while (true) {
//				if (controller.isRunning() == false) {
//					break;
//				}
//				Thread.sleep(1000);
//			}
//			System.out.println("<<<<<<<<爬虫结束>>>>>>>>>");
//			controller.requestCrawlStop();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}




	public static void main(String args[]) {
	 
		StartHeritrix sta = new StartHeritrix();
		String temp =  sta.GetMaxVersion("E:\\workspace\\exjobs\\非常规油气网");
		System.out.println(temp);
	}
}