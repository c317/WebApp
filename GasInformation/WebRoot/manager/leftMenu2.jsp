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
		<%String menu=String.valueOf(request.getAttribute("manageLeftNav2"));%>			
		<a href="manageXzwz.jsp" class="list-group-item <%="Xzwz".equals(menu)?"active\"":""%>">新增网站设置</a>
		<a href="manageXywzMaintain" class="list-group-item <%="Xywz".equals(menu)?"active\"":""%>">现有网站维护</a>
		<a href="manageSjgx.jsp" class="list-group-item <%="Sjgx".equals(menu)?"active\"":""%>">系统数据更新</a>
		<a href="getWords?WordsType=Oil" class="list-group-item <%="Ckwh".equals(menu)?"active\"":""%>">系统词库维护</a>
		<a href="manageZqcs.jsp" class="list-group-item <%="Zqcs".equals(menu)?"active\"":""%>">抓取参数设置</a>		
	</div>
</body>
</html>