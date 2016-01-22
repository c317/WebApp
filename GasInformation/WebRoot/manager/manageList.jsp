<%@page
	import="java.util.ArrayList,java.util.HashMap,java.io.FileInputStream,java.io.InputStream,java.util.Properties,
	java.sql.Statement,java.sql.Connection,java.sql.ResultSet,java.sql.SQLException,java.sql.DriverManager,java.util.LinkedList,
	java.sql.PreparedStatement"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.util.*"%>

<%
	String moduleName = "";
	String normal = "";
	/* int tomodule = Integer.valueOf(request.getParameter("tomodule"));
	String title = request.getParameter("title");
	title = new String(title.getBytes("iso-8859-1"), "utf-8");
	Newsdao newsdao = DaoFactory.getInstance().getNewsDao(); */
	News news = (News)request.getAttribute("updateNews");
	/* news = newsdao.findByTitle(title, tomodule); */
	switch (news.getModule()) {
	case 1:moduleName = "工作动态";break;
	case 2:moduleName = "矿产管理";break;
	case 3:moduleName = "政策法规";break;
	case 4:moduleName = "一周热点";break;
	case 5:moduleName = "国际合作";break;
	case 6:moduleName = "科技进展";break;
	case 7:moduleName = "图片新闻";break;
	case 8:moduleName = "领导动态";break;
	case 9:moduleName = "统计数据";break;
	case 10:moduleName = "勘探开发";break;
	case 11:moduleName = "油价信息";break;
	case 12:moduleName = "动态热点";break;
	case 13:moduleName = "动态参考";break;
	default:moduleName = null;break;
	}
	switch (news.getType()) {
	case 0:normal = "常规";break;
	case 1:normal = "非常规";break;
	default:normal = null;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/link.css">
<title>油气资源信息动态采集后台管理系统</title>

</head>

<body>
	<div id="top">
		<%
			request.setAttribute("manageNav", "Ywzx");
		%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
				<jsp:include page="leftMenu.jsp"></jsp:include>
			</div>

			<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
				<!-- <table style="display: inline-block;width:100%;height:40px;margin-bottom: 5px;background-color: #f5f5f5;">
						<td width="99%"> -->
				<ol class="breadcrumb">
					<li><a href="#">业务中心</a></li>
					<li><a href="#" class="active">修改</a></li>
				</ol>
				<!-- </td>
						<td style="height:20px;">
							<a role="button" onclick="show()" class="btn btn-info"
							style="padding:1px;height:20px;width:40px;margin-top:5px;margin-bottom:5px;font-size:11px">更新</a>
						</td>
				</table> -->

				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<tr>
							<td height="100"><table style="margin:0px;padding:10px"
									width="100%" border="0" cellpadding="0" cellspacing="1"
									bgcolor="#FFFFFF">
									<tr>
										<td style="text-align:center;"><font size="3"><b><%=news.getTitle()%></b>
										</font>
										</td>
									</tr>

									<tr>
										<td width="1000px" style="text-align:center;"><%=news.getPubTime()%></td>

									</tr>
									<tr>
										<td><div id="content" style="text-align: left"><%=news.getContent()%></div>
										</td>
									</tr>
									<tr>
										<td><div id="content" style="text-align: left">
												分类：<%=moduleName%></div></td>
									</tr>
									<tr>
										<td><div id="content" style="text-align: left">
												常规/非常规:<%=normal%></div></td>
									</tr>
									<tr>
										<td><div id="content" style="text-align: left">
												权重：<%=news.getTotalWeight()%></div></td>
									</tr>
									<tr>
										<td><div id="content" style="text-align: left">
												区域：<%=news.getOilField()%></div></td>
									</tr>

									<tr>
										<td><div id="author"
												style="text-align: right; height: 0px">
												来源:<%=news.getOriginSource()%></div>
										</td>
									</tr>
									<tr>
										<td colspan="4" id="form2">
											<table width="197" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
											</table></td>
									</tr>
									<tr style="height:2px">
										<td bgcolor="#FFFFFF"></td>
									</tr>
								</table></td>
						</tr>
					</table>					
				</div>
			</div>
		</div>
		<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
</html>
