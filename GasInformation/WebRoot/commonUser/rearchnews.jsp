<%@page import="com.gasinfo.util.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<meta charset ="UTF-8">
<%
        /* News news=(News)request.getAttribute("newsData"); */
%>
<title><%=request.getAttribute("title")%></title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../css/bootstrap.min.css" rel ="stylesheet"/>
</head>
<!-- <center> -->
<body>
	
	<div id="all" class="container"> 
		<jsp:include page="../commonUser/nav.jsp"></jsp:include>
		
		<div id="news">
		<table class="table table-condensed">

			<tr>
				<td height="23" colspan="4">&nbsp;</td>
			</tr>

			<tr>
				<tr>
					<td><div id="title" style="text-align: center">
							<font size="3"><b><%=request.getAttribute("title")%></b> </font>
						</div>
					</td>
				</tr>
				<tr>
					<td><div id="time" style="text-align: center"><%=request.getAttribute("time")%>
						</div>
					</td>
				</tr>
				<tr>
					<tr>
						<td><div id="content" style="text-align: left"><%=request.getAttribute("html")%></div>
						</td>
					</tr>
					<tr>
						<td><div id="author" style="text-align: right">
								来源:<%=request.getAttribute("source")%></div>
						</td>
					</tr>
		</table>
		</div>
		
		<jsp:include page="../commonUser/footer.jsp"></jsp:include>
	</div>
</body>
</html>
