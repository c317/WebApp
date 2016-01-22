package com.gasinfo.server.indexdatebase;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import com.gasinfo.model.News;

public class Checker {
	public boolean check(String title,String time,String source,String content){
		
		boolean bool=true;
		
		//筛选时间
		if(time.length()!=10){
			bool=false;
		}
		
		//筛选标题
		if(title.indexOf("|")!=-1||title.length()<5||title.indexOf("购物网站推荐")!=-1||title.indexOf("拒绝访问")!=-1||title.indexOf("页面没有找的")!=-1){
			bool=false;
		}

		//筛选正文
		if(content.isEmpty()||content.length()<=15){
			bool=false;
		}
		
		//筛选来源
		if(source.length()<3){
			bool=false;
		}
		
		return bool;
	}
	
	public boolean check(News news){
		
		boolean bool=true;
		
		//筛选时间
		if(news.getTime().length()!=10){
			bool=false;
		}
		
		//筛选标题
		if(news.getTitle().length()<5||news.getTitle().indexOf("购物网站推荐")!=-1||news.getTitle().indexOf("拒绝访问")!=-1||news.getTitle().indexOf("页面没有找的")!=-1){
			bool=false;
		}

		//筛选正文
		if(news.getContent().isEmpty()||news.getContent().length()<=15){
			bool=false;
		}
		
		//筛选原始来源
		if(news.getSource().length()<3){
			bool=false;
		}
		
		//筛选网站来源
		if(news.getSiteSource().length()<3){
			bool=false;
		}
		
		return bool;
	}
}
