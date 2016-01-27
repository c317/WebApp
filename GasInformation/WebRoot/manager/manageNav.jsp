<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../css/bootstrap.min.css" rel ="stylesheet">
<style type="text/css">
#bg{
	background:url(../images/bg_top.png);
}
</style>
</head>
<body>
	<div id="nav" class="navbar navbar-default">
		<div id="bg" width="100%">
			<table style="text-align:center">
			<tr>
				<td width="150"><img src="../images/logo.png" width="70" height="70" /></td>
				<td width="1"><img src="../images/BoxOver_bd.png" width="1" height="40" /></td>
				<td width="500"><img src="../images/title.gif" width="450" height="40" /></td>
				<td width="350">&nbsp;</td>
			</tr>				
			</table>			
		</div>
		<div id="manageNav">
			<ul class="nav nav-tabs">
				<%String menu=(String)request.getAttribute("manageNav");%>			 
					<li <%="Ywzx".equals(menu)?"class=\"active\"":""%>><a href="getManageData?moduleId=1">业务中心</a></li>
			<!-- 	<li <%="Ywzx".equals(menu)?"class=\"active\"":""%>><a href="manageGzdt.jsp">业务中心</a></li> -->
					<li <%="Ywdz".equals(menu)?"class=\"active\"":""%>><a href="manageRcfx.jsp">热词分析</a></li>
					<li <%="Xtgl".equals(menu)?"class=\"active\"":""%>><a href="manageXzwz.jsp">系统管理</a></li>
					<li <%="Yhgl".equals(menu)?"class=\"active\"":""%>><a href="myAccount.jsp">用户管理</a></li>
					<li <%="Wjgl".equals(menu)?"class=\"active\"":""%>><a href="manageUploadFile.action?module=1">文件管理</a></li>
					<li <%="Fhqtsy".equals(menu)?"class=\"active\"":""%>><a href="../index.action">返回前台首页</a></li>
			</ul>
		</div>					
	</div>
</body>
</html>