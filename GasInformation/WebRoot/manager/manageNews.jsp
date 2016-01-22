<%@page
	import="java.util.ArrayList,java.util.HashMap,java.io.FileInputStream,java.io.InputStream,java.util.Properties,
	java.sql.Statement,java.sql.Connection,java.sql.ResultSet,java.sql.SQLException,java.sql.DriverManager,java.util.LinkedList,
	java.sql.PreparedStatement"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gasinfo.util.*"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/link.css">
<title><%=request.getAttribute("title")%></title>
</head>
<body>
	<div id="top">
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
	
<div class="container-fluid">
	<div id="content">
		<div class="row">
			<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
				<%request.setAttribute("manageLeftNav", "");%>
				<jsp:include page="leftMenu.jsp"></jsp:include>
			</div>
		
			<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
				<table style="display: inline-block;width:100%;height:40px;margin-bottom: 5px;background-color: #f5f5f5;">
						<td width="99%">
						<ol class="breadcrumb">
						<li><a href="#">业务中心</a></li>							
						<%
							String moduleName="";
							String module =(String) request.getAttribute("moduleId");
							int moduleID=Integer.valueOf(module);
							switch(moduleID){
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
								case 12:moduleName = "一周热点参考";break;
								case 13:moduleName = "工作动态参考";break;
							}
						%>
							
						<li><a href="#" class="active"><%=moduleName%></a></li>																
					</ol></td>
					<td style="height:20px;">
						<a href="manageNewsModify?newsId=<%=request.getAttribute("newsId")%>&
							moduleId=<%=request.getAttribute("moduleId")%>"  class="btn btn-info" style="padding:1px;height:20px;width:40px;margin-top:5px;margin-bottom:5px;font-size:11px">修改</a>
					</td>
				</table>	
				<div class="table">
					<table class="table table-condensed">																			
					<tr>
						<td style="text-align:center;">
						<font size="3"><b><%=request.getAttribute("title")%></b></font></td>
					</tr>
					<tr>
						<td style="text-align:center;"><%=request.getAttribute("time")%></td></tr>
					<tr>
						<td><div id="content" style="text-align: left"><%=request.getAttribute("html")%></div></td>
					</tr>
					<tr>
						<td><div id="author" style="text-align: right;">
								来源:<%=request.getAttribute("source")%></div></td>
					</tr>											
					</table>																															
				</div>
			</div>
		</div>
	</div>
</div>
	
	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
</html>
