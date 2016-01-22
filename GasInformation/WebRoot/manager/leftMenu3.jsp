<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../css/bootstrap.min.css" rel ="stylesheet">
</head>
<body>
	<div class="list-group">
		<%String menu=String.valueOf(request.getAttribute("manageLeftNav3"));%>			
		<a href="manageRcfx.jsp" class="list-group-item <%="Rcfx".equals(menu)?"active\"":""%>">高频词频数分析</a>
		<a href="manageWbjl.jsp" class="list-group-item <%="Wbjl".equals(menu)?"active\"":""%>">高频词增量分析</a>
		<a href="manageQsfx.jsp" class="list-group-item <%="Qsfx".equals(menu)?"active\"":""%>">高频词趋势分析</a>
		<%-- <a href="manageXxtj.jsp" class="list-group-item <%="Xxtj".equals(menu)?"active\"":""%>">信息统计</a> --%>	
	</div>
</body>
</html>