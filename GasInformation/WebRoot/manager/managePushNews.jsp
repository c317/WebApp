<%@page import="java.util.ArrayList,com.gasinfo.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.action.*,com.gasinfo.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<title>油气网络信息动态采集系统</title>
</head>

<body>
	<div id="top">
		<%
			request.setAttribute("manageNav", "Yhgl");
		%>
		<%
			ArrayList<Group> groups=(ArrayList<Group>)request.getAttribute("groups");
		%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>

	<div class="container-fluid">
		<div id="content">
			<div class="row">
				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">

					<%
						request.setAttribute("manageLeftNav5", "XXTS");
					%>

					<jsp:include page="leftMenu5.jsp"></jsp:include>
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					+
					<ol class="breadcrumb">
						<li><a href="#">消息推送</a>
						</li>
					</ol>


					<form action="#" id="pushNews" name="pushNews" method="post">
						<table class="table table-bordered">
							<tr>
								<td width="15%">
									<table class="table">
										<tr>
											<td colspan="3">发送至：</td>
										</tr>
										<%
											for(int i=0;i<groups.size();i++) {
										%>
										<tr>
											<td width="8%">&nbsp;</td>
											<td width="1%"><input type="checkbox" id="ckGroup"
												name="ckGroup" value="<%=groups.get(i).getGroupID()%>" /></td>
											<td><%=groups.get(i).getGroupName()%></td>
										</tr>
										<%
											}
										%>
									</table>
								</td>
								<td width="85%">
									<div class="table-responsive">
										<table class="table table-bordered table-hover table-striped">
											<tr>
												<td width="8%">发布人：</td>
												<td><input type="text" id="publisher"
													style="width:300px" placeholder="请输入姓名"/>
												</td>
												</tr>
												<tr>
												<td width="8%">来源科室：</td>
												<td><div align="left">
														<select name="department"
															id="department"
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
													</div></td>
											</tr>
											<tr>
												<td width="8%">标题：</td>
												<td><input type="text" id="title"
													style="width:300px" placeholder="请输入标题"/>
												</td>
											</tr>
											<tr>
												<td>发布内容：</td>
												<td width="90%"><textarea id="pushNewsContent"
														name="pushNewsContent" rows="30"
														style="heigh:100%px;width:100%" placeholder="请编辑"></textarea>
												</td>
											</tr>
											<tr>
												<td colspan="2"><input type="submit" id="pushSubmit"
													name="pushSubmit" value="发布" onclick="push()" />
													</div></td>
											</tr>
										</table>
									</div></td>
							</tr>
						</table>
					</form>

				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function push() {
			var obj = document.all("ckGroup");
			var objString = "";
			var obj2 = document.getElementById("publisher").value;
			var obj3 = document.getElementById("pushNewsContent").value;
			var sIndex = document.getElementById("department").selectedIndex;
			var obj4 = document.getElementById("department").options[sIndex].text ;
			var obj5=document.getElementById("title").value;
			var objLenth = obj.length;
			if (obj2 == null || obj2 == "") {
				alert('发布者不能为空');
			}
			if (obj3 == null || obj3 == "") {
				alert('发布内容不能为空');
			}
			if(obj4==null||obj4=="") {
				alert('来源处室不能为空');			
			}
			var objYN = 0;
			for ( var i = 0; i < objLenth; i++) {
				if (obj[i].checked == true) {
					objString += obj[i].value + ",";
					objYN++;
				}
			}
			if (objYN == 0) {
				alert('请至少选择项');
				return false;
			} else {
				document.pushNews.action = "getPushNews?groups=" + objString
						+ "&publisher=" + obj2 + "&pushNewsContent=" + obj3+"&department="+obj4+"&title="+obj5;
				return true;
			}
		}
	</script>
	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
</html>
