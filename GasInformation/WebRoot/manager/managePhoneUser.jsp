<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gasinfo.action.*,com.gasinfo.util.*"%>
<%
	ArrayList<PhoneUser> users = (ArrayList<PhoneUser>) request
			.getAttribute("users");
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


					<%
						request.setAttribute("manageLeftNav5", "ATMT2");
					%>
					<jsp:include page="leftMenu5.jsp"></jsp:include>
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">

				
				
					<ol class="breadcrumb">
						<li><a href="#">用户管理</a>
						</li>
					</ol>
					

					<ol class="breadcrumb">
						<li><a href="#">用户管理</a>
						</li>
					</ol>

					<div class="table-responsive">
						<form action="#" method="post" id="form1" name="form1">
						<table class="table table-bordered table-hover table-striped">
							<thead align="center">
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;账号</td>
									<td>姓名</td>
									<td>联系方式</td>
									<td>科室</td>
									<td>职务</td>
									<td>角色</td>
									<td>操作</td>
								</tr>
							</thead>
							<%
								
								System.out.println(users.size());
								for (int i = 0; i < users.size(); i++) {
							%>
							
							<tr><td>
								<input type="hidden" value="<%=users.get(i).getUserID()%>" name="userID" id="userID">
									<div align="center">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=users.get(i).getAccount()%></div>
								</td>
								<td>
									<div align="center"><%=users.get(i).getUsername()%></div></td>
								<td>
									<div align="center"><%=users.get(i).getTelephone()%></div></td>
								<td>
									<div align="center">
				
										<select  name="department" id="department<%=users.get(i).getUserID()%>" style="width:100px">
											<option id="1" value="<%=users.get(i).getDepartement()%>"><%=users.get(i).getDepartement()%></option>
											<option id="1" value="油气信息资料室">油气信息资料室</option>
											<option id="1" value="油气中心">油气中心</option>
											<option id="1" value="中心领导">中心领导</option>
											<option id="1" value="副总工程师">副总工程师</option>
											<option id="1" value="办公室">办公室</option>
											<option id="1" value="人事处">人事处</option>
											<option id="1" value="党群工作处">党群工作处</option>
											<option id="1" value="总工程师室">总工程师室</option>
											<option id="1" value="油气资源战略规划室">油气资源战略规划室</option>
											<option id="1" value="油气资源评价室">油气资源评价室</option>
											<option id="1" value="油气矿业权登记室">油气矿业权登记室</option>
											<option id="1" value="油气监督室">油气监督室</option>
											<option id="1" value="油气信息资料室">油气信息资料室</option>
											<option id="1" value="矿产开发室">矿产开发室</option>
											<option id="1" value="矿产储量室">矿产储量室</option>
											<option id="1" value="信访信息分析室">信访信息分析室</option>
											<option id="1" value="返聘人员">返聘人员</option>
										</select>
									</div></td>
								<td>
									<div align="center"><%=users.get(i).getJob()%></div></td>
								<td>
									<div align="center" >
										<select  name="role" id="role<%=users.get(i).getUserID()%>"  style="width:100px">
											<option id="1" value="<%=users.get(i).getRole() %>"><%=users.get(i).getRole() %></option>
											<option id="1" value="一般用户">一般用户</option>
											<option id="1" value="领导">领导</option>
											<option id="1" value="群组管理员">群组管理员</option>
										</select>
									</div>
								</td>
								<td>
									<div align="center">
										<input type="submit" value="提交" onclick="update(<%=users.get(i).getUserID()%>)">
										</div>
								</td>
							</tr>
							
							<%
								}
							%>
							</form>
							<tr>
								<td colspan="7" style="text-align:center"><jsp:include
										page="../commonUser/pageroll.jsp" flush="true">
										<jsp:param value="getAllUser?moduleId=1" name="action" /></jsp:include></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
function update(id){ 
		var selectObj = document.getElementById("department"+id);
		var selectObj2=document.getElementById("role"+id);
	    var selectIdx = selectObj.selectedIndex;
	    
	    var selectIdx2=selectObj2.selectedIndex;
	    var optionValue = selectObj.options[selectIdx].value;
	    var optionValue2=selectObj2.options[selectIdx2].value;
	   	document.form1.action= "changeRole?userID="+id+"&department="+optionValue+"&role="+optionValue2;
		alert(optionValue2);
	}
</script>
</html>
