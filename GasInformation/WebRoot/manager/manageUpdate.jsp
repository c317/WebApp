<%@page import="com.sun.xml.internal.bind.v2.model.core.ID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gasinfo.util.*,java.util.ArrayList,java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="link.css">
<title>油气网络信息动态采集系统</title>
<%
	String moduleName = "";
	String normal="";
	int updateId = Integer.parseInt(request.getParameter("newsId"));
	int moduleId = Integer.parseInt(request.getParameter("moduleId"));
	Newsdao userdao = DaoFactory.getInstance().getNewsDao();
	News news = userdao.findById(updateId, moduleId);
	session.setAttribute("nowNews", news);
	switch(news.getModule()){
	case 1:moduleName = "工作动态";break;
	case 2:moduleName = "矿产管理";break;
	case 3:moduleName = "政策法规";break;
	case 4:moduleName = "一周热点";break;
	case 5:moduleName = "国际合作";break;
	case 6:moduleName = "科技进展";break;
	case 7:moduleName = "图片新闻";break;
	case 8:moduleName = "领导动态";break;
	case 9:moduleName = "统计数据";break;
	case 10:moduleName = "勘探开发";break;
	case 11:moduleName = "油价信息";break;
	case 12:moduleName = "动态热点";break;
	case 13:moduleName = "动态参考";break;
	default:moduleName=null;break;
	}
	switch(news.getType()){
	case 0:normal="常规";break;
	case 1:normal="非常规";break;
	default:normal=null;
	}
%>
</head>
<body>
	<div id="top">
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
	
	<div id="content">				
		<table>		
			<form action="update_handler.jsp" method="post">
			<input type="hidden" name="id" value="<%=news.getId()%>" readonly="readonly"> 
			<input type="hidden" name="webweight" value="<%=news.getWebWeight()%>"> 
			<input type="hidden" name="charset" value="<%=news.getCharset()%>">
			<input type="hidden" name="totalvisits" value="<%=news.getTotalVisits()%>">
			<input type="hidden" name="moduleId" value="<%=news.getModule()%>">
		<tr>
			<td>标题：</td>
			<td><input type="text" style="width:300px; height:20px" name="title" value="<%=news.getTitle()%>"></td>
		</tr>
		<tr>
			<td>时间：</td>
			<td><input type="text" name="time" value="<%=news.getPubTime()%>"></td>
		</tr>
		<tr>
			<td>来源：</td>
			<td><input type="text" name="source" value="<%=news.getOriginSource()%>"></td>
		</tr>
		<tr>
			<td>类别：</td>
			<td><select id="class2" name="newmoduleId">
				<option value="<%=news.getModule()%>" selected><%=moduleName%></option>
				<option value="5">国际合作</option>
				<option value="1">工作动态</option>
				<option value="2">矿产管理</option>
				<option value="6">科技进展</option>
				<option value="10">勘探开发</option>
				<option value="8">领导动态</option>
				<option value="9">统计数据</option>
				<option value="11">油价信息</option>
				<option value="4">一周热点</option>
				<option value="3">政策法规</option>
				<option value="13">动态参考</option>
				<option value="12">动态热点</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>区域：</td>
			<td><select id="zone" name="oilField">
				<option value="<%=news.getOilField()%>" selected><%=news.getOilField()%></option>
				<option value="中石油">中石油</option>
				<option value="中海油">中海油</option>
				<option value="中石化">中石化</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>总权重：</td>
			<td><select id="weight" name="totalweight">
				<option value="<%=news.getTotalWeight()%>" selected><%=news.getTotalWeight()%></option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>常规/非常规：</td>
			<td><select id="normal" name="type">
				<option value="<%=news.getType()%>" selected><%=normal%></option>
				<option value="0">常规</option>
				<option value="1">非常规</option>
				</select>
			</td>
		</tr>
		<tr>
			<td valign="top">内容：</td>
			<td><textarea name="content" style="width:1000px; height:500px;"><%=news.getContent()%></textarea></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><input type="submit" value="保存" /></td>
		</tr>
		</form>
		<tr>
			<td>&nbsp;</td>
		</tr>
		</table>
	</div>
	
	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
</html>