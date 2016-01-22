package com.gasinfo.downloadword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gasinfo.util.News;
/**
 * 
 * @author 刘挺
 *
 */
public class SearchNewsConvert{
	private  ArrayList<News> downList = new ArrayList<News>();
	private  ArrayList<HashMap<String, String>> list;
	private  String[] selectedID;
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	
	public SearchNewsConvert(Object list,String[] selectedID){
		this.list = (ArrayList<HashMap<String, String>>) list;
		this.selectedID = selectedID;
	}
	
	public ArrayList<News> getNewsList(){
		News newsTemp = null;
		for(int i = 0;i<selectedID.length;i++){
			newsTemp = new News();
			
			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
	        Matcher m_html = p_html.matcher(list.get(Integer.parseInt(selectedID[i])).get("title"));  
	        String title = m_html.replaceAll(""); // 过滤html标签
	        newsTemp.setTitle(title);
	        
			newsTemp.setContent(list.get(Integer.parseInt(selectedID[i])).get("html"));
			newsTemp.setOriginSource(list.get(Integer.parseInt(selectedID[i])).get("source"));
			newsTemp.setPubTime(list.get(Integer.parseInt(selectedID[i])).get("time"));
			downList.add(newsTemp);
		}
		return downList;
	}
}
