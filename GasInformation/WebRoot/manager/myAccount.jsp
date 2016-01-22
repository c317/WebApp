<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.action.ChartAction"
	import="com.gasinfo.util.Users"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="../css/calendar.css">
<script type="text/javascript" src="../js/calendar.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-zh.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-setup.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="../js/manage.js" charset="UTF-8"></script>
<title>油气网络信息动态采集系统</title>
</head>
<body>
	<div id="top">
		<%
			request.setAttribute("manageNav", "Yhgl");
		%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>

	<div class="container-fluid">
		<div id="content">
			<div class="row">
				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
				<%--判断语句，普通用户转leftMenu4.jsp，管理用户leftMenu5.jsp
				
				<%request.setAttribute("manageLeftNav4", "MYAT");%>
					<jsp:include page="leftMenu4.jsp"></jsp:include>
				
				 --%>
				 
					<%request.setAttribute("manageLeftNav5", "MYAT");%>
					<%Users us=(Users)session.getAttribute("us"); %>
					<%boolean type = us.isType();  %>
					
					<%if(type) { %>
					<jsp:include page="leftMenu5.jsp"></jsp:include>
					<%}else{ %>
					<jsp:include page="leftMenu4.jsp"></jsp:include>
					<%} %>
					
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					<ol class="breadcrumb">
						<li><a href="#">用户管理</a></li>
						<li><a href="#" class="active">我的信息</a></li>
					</ol>

					
					
		
											
					  <table class="table table-bordered" height="40px"
						style="text-align: center;">
						<tr>
							<td>&nbsp;
							
					<!-- 加入显示用户信息的代码 -->
					<%
						
						String username = us.getUsername();
						String password = us.getPassword();	
						out.println("用户名："+username);
						out.println("<br/>");
						out.println("&nbsp;");
						out.println("&nbsp;");
						out.println("&nbsp;");
						
						out.println("<br/>");
					%>
					<input id="uname" value=<%=username%> type="hidden"/>
					<input id="upd" value=<%=password%> type="hidden"/>
					<a onclick="rePd()">修改密码</a>
							
						</td>
						</tr>
					</table> 
					<table  height="360px"></table>


				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
	 <script type="text/javascript" charset="UTF-8">
  </body>
</html>