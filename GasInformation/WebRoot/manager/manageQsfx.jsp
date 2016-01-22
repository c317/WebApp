<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>油气网络信息动态采集系统</title>
</head>
<body>
	<div id="top">
		<%
			request.setAttribute("manageNav", "Ywdz");
		%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>

	<div class="container-fluid">
		<div id="content">
			<div class="row">
				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
					<%
						request.setAttribute("manageLeftNav3", "Qsfx");
					%>
					<jsp:include page="leftMenu3.jsp"></jsp:include>
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					<ol class="breadcrumb">
						<li><a href="#">业务定制</a></li>
						<li><a href="#" class="active">高频词趋势分析</a></li>
					</ol>
							<table class="table table-condensed"
							style="background-color:#CCCCCC">
							<thead>
							<form action="manageQsfx1.jsp" target="_blank" method="post">
							<td align="right">
							
							<input type="text" name="keyword" id="keyword" placeholder="请输入词" />
								<lable for="startTime">起始日期：</lable>
									<input name="startTime" id="startTime" type="text"
									onclick="return showCalendar('startTime', 'y-mm-dd');"
									style="width: 80px" />&nbsp; <lable for="endTime">终止日期：</lable>
									<input name="endTime" id="endTime" type="text"
									onclick="return showCalendar('endTime', 'y-mm-dd');"
									style="width: 80px" />&nbsp;
									<input type="submit" name="butnQS" id="butnQS" value="生成趋势" />
									
									</td>
							</form>
							
							
							
					<table class="table table-bordered" height="400px"
							style="text-align: center;">
							<tr>
								<td>&nbsp;
									<!-- <img src="wordtrend.action" width="700" height="400" /> -->
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