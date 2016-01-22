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
		<a href="myAccount.jsp" class="list-group-item <%="MYAT".equals(menu)?"active\"":""%>">我的信息</a>
		<%-- <a href="accountManagement.jsp" class="list-group-item <%="ATMT".equals(menu)?"active\"":""%>">管理用户</a> --%>
<<<<<<< HEAD
		<a href="accountAction.action" class="list-group-item <%="ATMT".equals(menu)?"active\"":""%>">管理网站用户</a>
		<a href="getAllUser.action" class="list-group-item <%="ATMT2".equals(menu)?"active\"":""%>">管理手机用户</a>	
		<a href="getAllGroup.action" class="list-group-item <%="XXTS".equals(menu)?"active\"":""%>">消息推送</a>		
</div>
=======
		<a href="accountAction.action" class="list-group-item <%="ATMT".equals(menu)?"active\"":""%>">管理用户</a>
		<a href="getAllUser.action" class="list-group-item <%="ATMT2".equals(menu)?"active\"":""%>">管理手机用户</a>		</div>
>>>>>>> refs/remotes/origin/master
  </body>
</html>
