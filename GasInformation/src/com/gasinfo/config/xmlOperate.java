package com.gasinfo.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.io.FileWriter;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class xmlOperate {

	private Document document;
	private String filePath; // 文件所在的实际物理路径


	public xmlOperate(String filepath) {	
		Configuration rc = new Configuration("daoconfig.properties");
		String RootURL_HeritrixXml = rc.getValue("RootURL_HeritrixXml");
		this.document = null;
		this.filePath = RootURL_HeritrixXml + File.separator + filepath;
		// this.filePath = filepath;
//		this.filePath = this.getClass().getClassLoader()
//				.getResource(filepath).getPath();
		getXmlFile();
		// writelog = new WriteLog();
	}
	
	/**
	 * 创建XML文件
	 * 
	 * @param rootName
	 *            :根节点名称
	 */
	public void createXMLFile(String rootName) {
		if (!fileExist()) {
			this.document = DocumentHelper.createDocument();
			this.document.addElement(rootName);
			saveXMLFile(this.document);
		}
	}

	/**
	 * 获取已存在的XML文档
	 * 
	 * @return
	 */
	public Document getXmlFile() {
		if (fileExist()) {
			SAXReader saxreader = new SAXReader();
			saxreader.setEncoding("UTF-8");
			try {
				this.document = saxreader.read(new File(filePath));
			} catch (DocumentException e) {
				// String loginfo = StackTraceToString.getExceptionTrace(e);
				// writelog.writeLogToEnd("LocalServerManager", loginfo);
			} finally {
				saxreader = null;
			}
		} else {
			// 写日志
			// String loginfo = "XML file does not exist,read error!";
			// writelog.writeLogToEnd("LocalServerManager",loginfo);
			System.exit(0);
		}
		return this.document;
	}

	/**
	 * 添加元素
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要添加的节点名称
	 * @param childValue
	 *            ：要添加的节点值
	 */
	public void addChild(String fatherNode, String fatherAttr,
			String childName, String childValue, Map mapAttr) {
		ChildOperator(fatherNode, fatherAttr, childName, childValue, "add",
				mapAttr, 0);
	}

	/**
	 * 修改元素
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要修改的节点名称
	 * @param childValue
	 *            ：要修改成的节点值
	 */
	public void updateChild(String fatherNode, String fatherAttr,
			String childName, String childValue, int updatId) {
		ChildOperator(fatherNode, fatherAttr, childName, childValue, "update",
				null, updatId);
	}

	/**
	 * 删除元素
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要删除的节点名称
	 */
	public void deleteChild(String fatherNode, String fatherAttr,
			String childName) {
		ChildOperator(fatherNode, fatherAttr, childName, "", "delete", null, 0);
	}

	/**
	 * 删除元素
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要删除的节点名称
	 */
	public void deleteChildAll(String fatherNode, String fatherAttr,
			String childName) {
		ChildOperator(fatherNode, fatherAttr, childName, "", "deleteAll", null,
				0);
	}

	/**
	 * 删除某个元素
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要删除的节点名称
	 */
	public void deleteChildOne(String fatherNode, String fatherAttr,
			String childName, String childValue) {
		ChildOperator(fatherNode, fatherAttr, childName, childValue,
				"deleteOne", null, 0);
	}

	/**
	 * 获取某个元素数值
	 * 
	 * @param fatherPath
	 *            :父节点名称
	 * @param fatherattr
	 *            :父节点属性
	 * @param childName
	 *            ：要获取的节点名称
	 */
	public String getChild(String fatherNode, String fatherAttr,
			String childName) {
		String result = "";
		result = ChildOperator(fatherNode, fatherAttr, childName, "", "get",
				null, 0);
		return result;
	}
	
	

	/**
	 * 子节点操作
	 * 
	 * @param fatherNode
	 *            :父节点名称
	 * @param fatherAttr
	 *            :父节点属性
	 * @param childName
	 *            ：要修改的节点
	 * @param childValue
	 *            ：修改后的节点值
	 * @param operator
	 *            : 要执行的操作名称
	 */
	private synchronized String ChildOperator(String fatherNode,
			String fatherAttr, String childName, String childValue,
			String operator, Map mapAttr, int updateId) {
		String result = "";
		if (this.document == null) {

			return "null";
		}
		Element root = this.document.getRootElement();// 获取根节点名称

		if (!root.getName().equals(fatherNode)) { // 如果不是在根节点下添加
			result = XmlElementOperator(root, fatherNode, fatherAttr,
					childName, childValue, operator, mapAttr);
		} else {
			if (operator.equals("add")) {
				Element childelement = root.addElement(childName);// 根节点不存在元素属性值
				childelement.setAttributeValue("id", childValue);

				saveXMLFile(this.document);
			} else if (operator.equals("update")) {
				List childelements = root.elements(childName);
				// for(Iterator
				// childs=childelements.iterator();childs.hasNext();){
				// Element everyone = (Element)childs.next();
				// everyone.setText(childValue); //修改该元素值
				// everyone.setAttributeValue("id",childValue);
				Element everyone = (Element) childelements.get(updateId);
				everyone.setAttributeValue("id", childValue);
				// }
				saveXMLFile(this.document);
			} else if (operator.equals("delete")) {
				List childelements = root.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改
				for (Iterator childs = childelements.iterator(); childs
						.hasNext();) {
					Element everyone = (Element) childs.next();
					List childelements1 = everyone.elements("module");
					for (Iterator childs1 = childelements1.iterator(); childs1
							.hasNext();) {
						Element everyone1 = (Element) childs1.next();
						everyone.remove(everyone1);
					}

				}

				saveXMLFile(this.document);
			} else if (operator.equals("get")) {
				List childelements = root.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改

				for (Iterator childs = childelements.iterator(); childs
						.hasNext();) {
					Element everyone = (Element) childs.next();

					result = everyone.getText();
				}
				saveXMLFile(this.document);
			} else if (operator.equals("deleteOne")) {

				List childelements = root.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改

				for (Iterator childs = childelements.iterator(); childs
						.hasNext();) {
					Element everyone = (Element) childs.next();
					String divElement = everyone.attributeValue("id");
					if (divElement.equals(childValue)) {
						root.remove(everyone);
					}
				}
				saveXMLFile(this.document);
			} else if (operator.equals("deleteAll")) {
				List childelements = root.elements();// 获取当前节点下的所有子节点，判断其值，以进行修改

				for (Iterator childs = childelements.iterator(); childs
						.hasNext();) {
					Element everyone = (Element) childs.next();
					List childeDiv = everyone.elements();
					for (Iterator childsDiv = childeDiv.iterator(); childsDiv
							.hasNext();) {
						Element everyoneDiv = (Element) childsDiv.next();
						everyone.remove(everyoneDiv);
					}
				}
			}
			saveXMLFile(this.document);

		}
		return result;
	}

	/**
	 * 递归元素操作
	 * 
	 * @param element
	 *            :要递归的元素
	 * @param fatherNode
	 *            :父节点名称
	 * @param fatherAttr
	 *            :父节点属性
	 * @param childName
	 *            ：要进行操作的节点
	 * @param childValue
	 *            ：操作后的节点值
	 * @param operator
	 *            : 要执行的操作名称
	 */
	private synchronized String XmlElementOperator(Element element,
			String fatherNode, String fatherAttr, String childName,
			String childValue, String operator, Map mapAttr) {
		String result = "";
		List elements = element.elements();
		for (Iterator it = elements.iterator(); it.hasNext();) {
			Element currentelement = (Element) it.next();
			if (!currentelement.getName().equals(fatherNode)) { // 当前元素并不是我们要查找的父元素时，继续查找
				XmlElementOperator(currentelement, fatherNode, fatherAttr,
						childName, childValue, operator, mapAttr);// 递归调用
			} else {
				if (currentelement.attributeCount() > 0) { // 当前元素存在属性值时
					for (Iterator list = currentelement.attributeIterator(); list
							.hasNext();) { // 遍历属性值
						Attribute attr = (Attribute) list.next(); // 获取属性值队列中的第一个元素
						if (attr.getValue().equals(fatherAttr)) {// 根据属性值确定惟一的父元素
							if (operator.equals("add")) {// 添加元素
								Element childelement = currentelement
										.addElement(childName); // 给当前元素添加一个子元素

								childelement.setText(childValue); // 设置子元素的数值
								Iterator itmapAttr = mapAttr.keySet()
										.iterator();
								while (itmapAttr.hasNext()) {
									String key = (String) itmapAttr.next();
									String value = mapAttr.get(key).toString();
									childelement.setAttributeValue(key, value);

								}

								// childelement.setAttributeValue("id", "m1");
								// childelement.setAttributeValue("name",
								// "module1");
								// childelement.setAttributeValue("url",
								// "index1.jsp");
							} else if (operator.equals("update")) {// 修改某个元素
								List childelements = currentelement
										.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改
								for (Iterator childs = childelements.iterator(); childs
										.hasNext();) {
									Element everyone = (Element) childs.next();
									everyone.setText(childValue); // 修改该元素值
								}
							} else if (operator.equals("delete")) { // 删除某个指定的元素

								List childelements = currentelement.elements();// 获取当前节点下的所有子节点，判断其值，以进行修改

								for (Iterator childs = childelements.iterator(); childs
										.hasNext();) {
									Element everyone = (Element) childs.next();
									currentelement.remove(everyone);
								}
							} else if (operator.equals("get")) {
								List childelements = currentelement
										.elements(childName);// 获取当前节点下的所有子节点，判断其值，以进行修改
								for (Iterator childs = childelements.iterator(); childs
										.hasNext();) {
									Element everyone = (Element) childs.next();
									// result = everyone.getText();
									result = everyone.attributeValue("id")
											+ "," + result;
								}
							} else {
								// 写日志
								// String loginfo =
								// "XmlFile Operator not exists!";
								// writelog.writeLogToEnd("LocalServerManager",loginfo);
							}
						}
					}
				}
			}
		}
		saveXMLFile(this.document);
		return result;
	}

	/**
	 * 保存XML文件
	 * 
	 * @param document
	 *            : XML文件名
	 */
	private void saveXMLFile(Document document) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(
			new FileOutputStream(new File(filePath)), format);
//			new OutputStreamWriter(new FileOutputStream(new File(filePath)), format);
//			new OutputStreamWriter(new FileOutputStream("file.xml"),"UTF-8")) ;
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			// String loginfo = StackTraceToString.getExceptionTrace(e);
			// writelog.writeLogToEnd("LocalServerManager", loginfo);
		}
	}

	/**
	 * 判断XML文件是否存在.
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean fileExist() {
		java.io.File objFile = new java.io.File(this.filePath);
		if (objFile.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	/*
	 * 
	 * @param fatherNode
	 *            :父节点名称
	 * @param fatherAttr
	 *            :父节点属性
	 * @param childName
	 *            ：子节点的name属性的值
	 * @param modchildValue
	 *            ：修改后的子节点的值
	 * 
	 * 通过父节点下的子节点的name属性值找到对应的子节点，然后修改该子节点的值为setChildValue
	 * 
	 */
	public void setChildValueByName(String fatherNode,String childNode,String childName,String setChildValue){
		String strChildName = "//" + fatherNode + "/" + childNode +"/" + "@name";
		List listChildName = document.selectNodes(strChildName);
		Iterator iterChildName = listChildName.iterator();
		while (iterChildName.hasNext()) {
            Attribute attribute = (Attribute) iterChildName.next();
            if (attribute.getValue().equals(childName)){
          	  Element element = (Element) attribute.getParent();
          	  element.setText(setChildValue);
            		System.out.println("setChildValueByName modifi order.xml is ok");
            }
         }
		 
		saveXMLFile(document);
        
	}
	
	
	public void setChildValueByid(String fatherNode,String childNode,String childid,String setChildValue){
		String strChildid = "//" + fatherNode + "/" + childNode +"/" + "@id";
		List listChildid = document.selectNodes(strChildid);
		Iterator iterChildid = listChildid.iterator();
		while (iterChildid.hasNext()) {
            Attribute attribute = (Attribute) iterChildid.next();
            if (attribute.getValue().equals(childid)){
          	  Element element = (Element) attribute.getParent();
          	  element.setText(setChildValue);
            		System.out.println("setChildValueByid modifi order.xml is ok");
            }
         }
		 
		saveXMLFile(document);
	}
	
	/*
	 * 通过根节点下的子节点的name属性值找到该子节点的值            
	 * 本系统用于通过模块名查找到模块对应的信息来源网站的编号（例如：通过"Gzdt"找1,2    即工作动态模块对应的信息来源网站编号为1和2）
	 *       
	 */
	public String getChildValueByName(String fatherNode,String childNode,String childName){
		return childOperator(fatherNode,childNode,childName , "@name");
	}
	
	//预留的函数，通过根节点下的子节点的id属性值找到该子节点的值  
	public String getChildValueByid(String fatherNode,String childNode,String childName){
		return childOperator(fatherNode,childNode,childName , "@id");
	}
	

	
	//预留的函数，通过根节点下的子节点的ID属性值找到该子节点的值  
	public String getChildValueByID(String fatherNode,String childNode,String childName){
		return childOperator(fatherNode,childNode,childName , "@ID");
	}
	
	/*通过根节点下的子节点的webId属性值和孙子节点名（该子节点下的字节点）找到孙子节点的值
	 * 本系统通过网站的编号查找对应网站的各属性，例如输入网站编号为1和要查找的子节点（webName,taskVersion,seed）的名字，即返回对应的值
	 */
	public String getChildValueBywebId(String fatherNode,String childNode,String childName, String grandchildNode ){
		Element elementChild = childOperatorElement(fatherNode,childNode,childName , "@webId");
		String result = elementChild.elementText(grandchildNode);  
		return result;
	}
	
	
	/*通过根节点下的子节点的webId属性值和孙子节点名（该子节点下的字节点）设置孙子节点的值
	 * 
	 */
	public void setChildValueBywebId(String fatherNode,String childNode,String childName, String grandchildNode ,String setGrandValue){
		Element elementChild = childOperatorElement(fatherNode,childNode,childName , "@webId");
		Element grandchildNodeElm = elementChild.element(grandchildNode);  
		grandchildNodeElm.setText(setGrandValue);
      	System.out.println("setChildValueBywebId modifi order.xml is ok");
		
      	saveXMLFile(document);
	}
	
	/*
	 * 通过父节点和子节点名取到该子节点的值,适用于从根节点到要查找的子节点的距离有且仅有三层，且子节点的值不是写在属性里
	 * 本系统用来查找website节点下的exjobsPath节点值
	 */
	public  String getChildValueByLabel(String fatherNode,String childName) {
		Element root = document.getRootElement(); // 拿到根节点
		Element fatherNodeElm=root.element(fatherNode);// 拿到根节点下的fatherNode节点          
		String result = fatherNodeElm.elementText(childName); // 拿到fatherNode根节点下的childName节点
		return result;
	}
	
	public  void setChildValueByLabel(String fatherNode,String childName ,String setChildValue) {
		Element root = document.getRootElement(); // 拿到根节点
		Element fatherNodeElm=root.element(fatherNode);// 拿到根节点下的fatherNode节点          
		Element childNameElm = fatherNodeElm.element(childName); // 拿到fatherNode根节点下的childName节点
		
		childNameElm.setText(setChildValue);
      	System.out.println("setChildValueByLabel modifi order.xml is ok");
		
      	saveXMLFile(document);
	}
	
	
	/*
	 * 
	 * @param fatherNode
	 *            :父节点名称
	 * @param childName
	 *            ：子节点的name属性的值
	 * @param setChildValue
	 *            ：修改后的子节点的值
	 * 
	 * 通过父节点下的子节点的name属性值找到对应的子节点，然后修改该子节点的值为setChildValue
	 * 
	 * 在fatherNode节点下加上一组子节点
	 * 
	 */
	public void addNode(String fatherNode, String childName,
			String setChildAttName , String setChildAttValue ,
			Map<String , String> grandchild ,List<String> grandchildKey) {
		Element root = document.getRootElement(); // 拿到根节点
		Element fatherEle = root;
		if(!root.getName().equals(fatherNode)){
			fatherEle = root.element(fatherNode);//找到该父节点
		}
		
		Element childEle = fatherEle.addElement(childName);//添加子节点
		childEle.addAttribute(setChildAttName, setChildAttValue);//设置子节点的属性名和值
		
		//设置孙子节点的名和值
		for(int i=0 ; i<grandchildKey.size() ; i++){
			Element grandchildEle1 = childEle.addElement(grandchildKey.get(i));
			grandchildEle1.setText((String)grandchild.get(grandchildKey.get(i)));
		}

		saveXMLFile(document);
		System.out.println("add Node is OK! hahaha");
	}
	
	/*
	 * 
	 * @param fatherNode
	 *            :父节点名称
	 * @param fatherAttr
	 *            :父节点属性
	 * @param childName
	 *            ：子节点的name属性的值
	 * @param setChildValue
	 *            ：修改后的子节点的值
	 * 
	 * 通过父节点下的子节点的name属性值找到对应的子节点，然后修改该子节点的值为setChildValue
	 * 
	 */
	private synchronized String childOperator(String fatherNode,String childNode,String childName,String findkey){
		String strChildName = "//" + fatherNode + "/" + childNode +"/" + findkey;
		List listChildName = document.selectNodes(strChildName);
		Iterator iterChildName = listChildName.iterator();
		String result = "";
		while (iterChildName.hasNext()) {
            Attribute attribute = (Attribute) iterChildName.next();
            if (attribute.getValue().equals(childName)){
            	Element element = (Element) attribute.getParent();
          	  	result = element.getTextTrim();	
            }
         }
		 return result ;
	}
	
	private synchronized Element childOperatorElement(String fatherNode,String childNode,String childName,String findkey){
		String strChildName = "//" + fatherNode + "/" + childNode +"/" + findkey;
		List listChildName = document.selectNodes(strChildName);
		Iterator iterChildName = listChildName.iterator();
		Element elementresult = null ;
		while (iterChildName.hasNext()) {
            Attribute attribute = (Attribute) iterChildName.next();
            if (attribute.getValue().equals(childName)){
            	elementresult = (Element) attribute.getParent();
            }
         }
		 return elementresult ;
	}

	
	public void setWebIdByModuleId(String ModuleId ,  String newWebId){
		 setChildValueByid("module","string",ModuleId , newWebId);
	}
	
	public String getWebIdByModuleId(String ModuleId){
		return getChildValueByid("module","string",ModuleId);
	}
	
	
	public String getWebIdByModuleName(String ModuleName){
		return getChildValueByName("module","string",ModuleName);
	}
	
	public void setWebIdByModuleName(String ModuleName , String newWebId){
		setChildValueByName("module","string", ModuleName ,newWebId);
	}
	
	public String getTaskVersionByWebId(String WebId){
		return getChildValueBywebId("website","string",WebId,"taskVersion");
	}
	
	public void setTaskVersionByWebId(String WebId , String newTaskVersion){
		setChildValueBywebId("website","string",WebId,"taskVersion",newTaskVersion);
	}
	
	
	
	public String getWebNameByWebId(String WebId){
		return getChildValueBywebId("website","string",WebId,"webName");
	}
	
	public void setWebNameByWebId(String WebId ,String newWebName){
		setChildValueBywebId("website","string",WebId,"webName",newWebName);
	}
	
	
	public String getSeedByWebId(String WebId){
		return getChildValueBywebId("website","string",WebId,"seed");
	}
	
	public void setSeedByWebId(String WebId , String newSeed){
		setChildValueBywebId("website","string",WebId,"seed",newSeed);
	}
	
	
	public String getExjobsPath(){
		return getChildValueByLabel("website","exjobsPath");
	}
	
	public void setExjobsPath(String newExjobsPath){
		setChildValueByLabel("website","exjobsPath",newExjobsPath);
	}
	
	public String getMaxWebId(){
		return getChildValueByLabel("website","maxWebId");
	}
	
	public void setMaxWebId(String newMaxWebId){
		setChildValueByLabel("website","maxWebId",newMaxWebId);
	}
	
	public String getMaxExSelectedWeb(){
		return getChildValueByLabel("website","maxExSelectedWeb");
	}
	
	public void setMaxExSelectedWeb(String newMaxExSelectedWeb){
		setChildValueByLabel("website","maxExSelectedWeb",newMaxExSelectedWeb);
	}
	
	//2个参数分别表示需要传入的webName、seed值
	public synchronized void addWebsite(String webName , String seed){
		int intNewWebId = Integer.valueOf(getMaxWebId()) + 1;//获取当前的最大webId值并加1
		String strNewWebId = String.valueOf(intNewWebId);//将新的最大webId值赋给新增加的网站,
		setMaxWebId(strNewWebId);//将maxWebId更新，maxWebId为几就表示有几个网站
		
		Map<String,String> mapGrandchild = new HashMap();
		mapGrandchild.put("webName", webName);
		mapGrandchild.put("taskVersion", "1");
		mapGrandchild.put("seed", seed);
		List<String> listGrandchildKey = new ArrayList();
		listGrandchildKey.add("webName");
		listGrandchildKey.add("taskVersion");
		listGrandchildKey.add("seed");
		addNode("website","string","webId",strNewWebId,mapGrandchild,listGrandchildKey);
	}
	
	
	
	public static void main(String[] args) throws DocumentException {
		// String filepath =
		// System.getProperty("user.dir")+"/XmlFiles/LocalServicesConfig.xml";
//		String filepath = "HeritrixXml.xml";
//		xmlOperate xmloperator = new xmlOperate(filepath);
//		xmloperator.getXmlFile();
//		xmloperator.setTaskVersionByWebId("2", "67");
//		xmloperator.setChildValueBywebId("website","string","1","taskVersion","66abc");
//		System.out.println(xmloperator.getWebIdByModuleId("3"));
//		
//		String strWebIds = xmloperator.getWebIdByModuleId(String.valueOf("3"));
//		String[] strWebId = strWebIds.split(",");
//		for(int i=0 ; i<strWebId.length ; i++){
//			System.out.println(strWebId[i]);
//		}
		
//		System.out.println(xmloperator.getChildValueBywebId("website","string","2","taskVersion"));
//		System.out.println(xmloperator.getChildValueByName("module","string","Zcfg"));
//		System.out.println(xmloperator.getChildValueByLabel("website","exjobsPath"));
//		xmloperator.setChildValueByLabel("website","exjobsPath","E:\\Workplace\\exjobs");
//		xmloperator.setChildValueByName("module","string","Gzdt","1,2");
//		xmloperator.setChildValueBywebId("website","string","1","taskVersion","66");
//		
//		Map<String,String> mapGrandchild = new HashMap();
//		mapGrandchild.put("webName", "webNametest");
//		mapGrandchild.put("taskVersion", "taskVersiontest");
//		mapGrandchild.put("seed", "seedtest");
//		List<String> listGrandchildKey = new ArrayList();
//		listGrandchildKey.add("webName");
//		listGrandchildKey.add("taskVersion");
//		listGrandchildKey.add("seed");
//		xmloperator.addNode("website","string","webId","test",mapGrandchild,listGrandchildKey);
		Calendar ca = Calendar.getInstance();
		Date now = new Date(); 
	      
	      int year = ca.get(Calendar.YEAR);//获取年份
	      int month=ca.get(Calendar.MONTH)+1;//获取月份 
	      int day=ca.get(Calendar.DATE);//获取日
	      int minute=ca.get(Calendar.MINUTE);//分 
	      int hour=ca.get(Calendar.HOUR);//小时 
	      int second=ca.get(Calendar.SECOND);//秒
	      System.out.println(year + ""+month+""+day+""+hour+""+minute+""+second);
	      
	      DateFormat d6 = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG); //显示日期。时间（精确到秒）
	      String str6 = d6.format(now);
	      System.out.println(str6);
	      
	      SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
	      String sDateSuffix = dateformat.format(now);
	      System.out.println(sDateSuffix);
//		Date data = new Date();
//		System.out.println(data.getMonth()+1);
//		System.out.println(data.getYear()+1900);
//		System.out.println(data.getDay());
//		System.out.println(data.getSeconds());
//		System.out.println(data.getMinutes());
//		System.out.println(data.getHours());
//		System.out.println(data.getTime());
//		System.out.println("1428932100790 data.getDate() :"+data.getDate());
//		System.out.println("data.getTimezoneOffset(): " + data.getTimezoneOffset());
	}
}
