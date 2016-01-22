<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.gasinfo.util.*,java.io.*,java.net.*,java.util.ArrayList,java.util.HashMap"%>
<%
	request.setCharacterEncoding("utf-8");
	int newsId = Integer.parseInt(request.getParameter("id"));
	//原始位置
	/* News nownews = (News)session.getAttribute("nowNews"); */
	//更新之后的位置
	int nowmodule=Integer.parseInt(request.getParameter("moduleId"));
	int webWeight=Integer.parseInt(request.getParameter("webweight"));
	String charset=request.getParameter("charset");
	String title = request.getParameter("title");
	String pubtime = request.getParameter("time");
	String originsource = request.getParameter("source");
	int totalvisits=Integer.parseInt(request.getParameter("totalvisits"));
	int tomodule=Integer.parseInt(request.getParameter("newmoduleId"));
	String oilField = request.getParameter("oilField");
	int totalweight=Integer.parseInt(request.getParameter("totalweight"));
	int type = Integer.parseInt(request.getParameter("type"));
 	String content = request.getParameter("content");
 	String basin = request.getParameter("basin");
 	String company = request.getParameter("company");
 	String oil = request.getParameter("oil");
 	String Output = request.getParameter("output");
 	String siteSource=request.getParameter("siteSource");

 	News news=new News();
 	news.setId(newsId);
 	news.setModule(nowmodule);
 	news.setWebWeight(webWeight);
 	news.setCharset(charset);
 	news.setTitle(title);
 	news.setPubTime(pubtime);
 	news.setSiteSource(siteSource);
 	news.setOriginSource(originsource);
 	news.setToModule(tomodule);
 	news.setTotalVisits(totalvisits);
 	news.setOilField(oilField);
 	news.setTotalWeight(totalweight);
 	news.setType(type);
 	news.setContent(content);
 	news.setBasin(basin);
 	news.setCompany(company);
 	news.setOil(oil);
 	news.setOutput(Integer.valueOf(Output));
 	Newsdao newsdao =DaoFactory.getInstance().getNewsDao();
 	newsdao.Update(news);  
 	title =new String(title.getBytes("utf-8"),"iso-8859-1");
 	response.sendRedirect("newsModify.action?tomodule="+String.valueOf(tomodule)+"&title="+title);
%>
