<%@ page import="java.util.ArrayList,java.util.HashMap"%>
<%@ page language="java" import="java.util.*,com.gasinfo.util.*" pageEncoding="utf-8"%>
<%
	ArrayList<HashMap<String, Object>> fileList =(ArrayList<HashMap<String, Object>>)request.getAttribute("fileList"); 
	String uploadResult = "";
	if (request.getAttribute("tipMessage") == null
			|| request.getAttribute("tipMessage") == "") {
		uploadResult = "&nbsp;";
	} else
		uploadResult = (String) request.getAttribute("tipMessage");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>   
<meta http-equiv="Content-Type"
	content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<script type="text/javascript" src="../js/manage.js" charset="UTF-8"></script>
<title>油气网络信息动态采集系统</title>
<style type="text/css">
.file {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
}

.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}
</style>
</head>

<body>
<div id="top">
		<%
			request.setAttribute("manageNav", "Wjgl");
		%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
	<div class="container-fluid">
		<div id="content">
			<div class="row">
				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">


					<%
						request.setAttribute("manageLeftNav5", "theme");
					%>
					<jsp:include page="leftMenu6.jsp"></jsp:include>
				</div>
			<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					<div>
						<ol class="breadcrumb">
							<li>文件管理</li>
							<li>上传文件</li>
						</ol>

						
						<div class="table-responsive">
							<form action="#" method="post" enctype="multipart/form-data" id="fileForm" name="fileForm">
								<table class="table table-bordered table-hover table-striped">
									<tr>
										<td>标题：</td>
										<td><input type="text" id="title" name="title" style="width:300px"
											placeholder="请输入标题" /></td>
									</tr>
									<tr>
										<td>来源科室：</td>
										<td><div align="left">
												<select name="department" id="department"
													style="width:150px">
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
											</div>
										</td>
									</tr>

									<tr>
										<td>添加文件：</td>
										<td>		
											<input type="file" name="pic" id="pic"> 
										</td>
									</tr>
									<tr>
										<td colspan="2" style="text-align:center">
										<a
											href="javascript:;" class="file">确定上传
											 <input type="submit" name="submitFile" id="submitFile" onclick="return file(2)">
										</a>
										<font color="red" style="position: relative;line-height: 20px;overflow: hidden;
												padding: 4px 12px;display: inline-block;text-align:center;"><%=uploadResult%></font>
										</td>
									</tr>
								</table>			
						</form>
						</div>
					</div>

					<div>
						<ol class="breadcrumb">
							<li>文件管理</li>
							<li>上传记录</li>
						</ol>

						<div class="table-responsive">
							<table class="table table-bordered table-hover table-striped">
								<thead align="center">
									<tr>
										<td>序号</td>
										<td>标题</td>
										<td>上传时间</td>
										<td>来源</td>
									</tr>
								</thead>
								<%
									for(int i = 0;i < fileList.size();i++){
								 %>
								<tr style="text-align:center">
									<td hidden><%=fileList.get(i).get("ID")%></td>
									<td><%=i+1%></td>
									<td><%=fileList.get(i).get("fileName")%></td>
									<td><%=fileList.get(i).get("time")%></td>
									<td><%=fileList.get(i).get("department")%></td>
								</tr>
								<%}%>
							</table>
						</div>

					</div>

				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
</html>
