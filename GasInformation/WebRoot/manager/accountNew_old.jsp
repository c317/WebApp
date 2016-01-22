<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.action.*,com.gasinfo.util.Users"%>
<%
	ArrayList<Users> users =(ArrayList<Users>)request.getAttribute("users");
 %>
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

				
					<%request.setAttribute("manageLeftNav5", "ATMT");%>
					<jsp:include page="leftMenu5.jsp"></jsp:include>
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					<ol class="breadcrumb">
						<li><a href="#">用户管理</a></li>
						<li><a href="#" class="active">管理用户</a></li>
					</ol>
			
			 <table class="table table-bordered" height="400px"
						style="text-align: center;">
						<tr>
							<td>&nbsp;
			
			
				<form action="#" method="post" id="form10" name="form10">
					<div class="table-responsive">
					
						<table  align="center" style="width:70%" class="table table-bordered table-hover table-striped table-condensed " >
							<h4 style="color:#4fb2d1;font-weight:bold;">全部管理员信息列表</h>
						
							<col style="width:35%"/>
							<col style="width:35%"/>
							<thead align="center">
								<tr>
								    
									<td style="width:32.5%">用户名</td>
									<td style="width:37.5%">操作</td>
									
								</tr>
							</thead>
							
							<%
								for(int i =0; i<users.size(); i++)
								{
								
							%>

							<tr>
								
								<td>
									<div align="center">
									<input id="uname<%=i%>" value=<%=users.get(i).getUsername()%> name="username" type="hidden"/>
									 <input id="upd<%=i%>" value=<%=users.get(i).getPassword()%> name="password" type="hidden"/>
									<%=users.get(i).getUsername() %>
									</div>
								</td>
								<td>
									<div align="center" >
									
										<img src="../images/edt.gif"
											width="16" height="16" />
											<a style="cursor:pointer;" onclick="reUser(<%=i%>)" >修改</a>
											&nbsp;&nbsp; 
											<%if(!(users.get(i).isType())){
											 %>
											<img src="../images/del.gif" width="16"
											height="16" /><a onclick="delUser(<%=i%>)" style="cursor:pointer;">删除</a> &nbsp;&nbsp;
											<%}else{} %>

									</div></td>
									
							</tr>
							
							<%
								}
							//	System.out.print("共"+users.size()+"个用户");
						
							%>

							<tr>
					<td colspan="2"><div align="center">
					<img src="../images/yqbg_r2_c2.gif" width="16" height="16" />
					<a href="#" onclick="addUser()">增加新管理员</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div></td>
					
							</tr>
							</table>
						</div>
					
					</div>
					</form>
					</td>
						</tr>
					</table> 
					</div>
			
			</div>
		</div>
	</div>

	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
</html>