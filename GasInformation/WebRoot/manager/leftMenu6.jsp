<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../css/bootstrap.min.css" rel ="stylesheet">
</head>
<body>
<div class="list-group">
		<%String menu=String.valueOf(request.getAttribute("manageLeftNav5"));%>			
		<a href="manageUploadFile.action?module=1" class="list-group-item <%="newsFile".equals(menu)?"active\"":""%>">热点文件</a>
		<%-- <a href="accountManagement.jsp" class="list-group-item <%="ATMT".equals(menu)?"active\"":""%>">管理用户</a> --%>
		<a href="manageUploadFile.action?module=2" class="list-group-item <%="theme".equals(menu)?"active\"":""%>">专题文件</a>
		<a href="manageUploadFile.action?module=3" class="list-group-item <%="image".equals(menu)?"active\"":""%>">数据图片</a>		</div>
  </body>
</html>
