package com.gasinfo.config;

import java.util.ArrayList;

import com.gasinfo.action.newsAction;


public class DataUpdatePara {
	
	private xmlOperate xmloperator = new xmlOperate("HeritrixXml.xml");
	
	public ArrayList<String[]> getWebIdAndNameByModuleId(int ModuleId){
		
		ArrayList<String[]> listWebIdAndName = new ArrayList<String[]>();
		String strWebIds = xmloperator.getWebIdByModuleId(String.valueOf(ModuleId));
		//webId存储方式为1,2,3   故要用“,”将他们分开 ，然后用字符数字存放各个webId
		String[] strWebId = strWebIds.split(",");
			//System.out.println(strWebId[i]);
			//如果传入的要爬取的网站ID不包括某些ID时，就不爬取该网站
		for(int i=0 ; i<strWebId.length ; i++){
			String webName = xmloperator.getWebNameByWebId(strWebId[i]);
			String[] strsIDandName = new String[]{strWebId[i] , webName} ;
			listWebIdAndName.add(strsIDandName);
		}
		return listWebIdAndName;
	}
	

	public static void main(String args[]){
//		DataUpdatePara dataUpdata = new DataUpdatePara();
//		ArrayList<String[]> GzdtPara = dataUpdata.getWebIdAndNameByModuleId(1);
		DataUpdatePara dataUpdata = new DataUpdatePara();
		ArrayList<String[]> GzdtPara = dataUpdata.getWebIdAndNameByModuleId(6);
		for(int i = 0 ; i < GzdtPara.size() ; i++){
			String[] strs = GzdtPara.get(i);
			String id = strs[0];
			String name = strs[1];
			
			System.out.println(id);
			System.out.println(name);
		}
	}
}
