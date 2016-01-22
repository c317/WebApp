<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.action.ChartAction"%>

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
						request.setAttribute("manageLeftNav3", "Rcps");
					%>
					<jsp:include page="leftMenu3.jsp"></jsp:include>
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					<ol class="breadcrumb">
						<li><a href="#">业务定制</a></li>
						<li><a href="#" class="active">热词频数分析</a></li>
					</ol>

					
						<table class="table table-condensed"
							style="background-color:#CCCCCC">
							<thead>
							 <form action="updateword" method="post">
								<td><input type="submit" name="btnHotW" id="btnHotW" value="更新热词"/></td>
							</form>
							<form action="jfreechart" method="post">
								<td align="right"><select id="moduleMK" name="moduleMK">
										<option value="1">工作动态</option>
										<option value="2">矿场管理</option>
										<option value="3">政策法规</option>
										<option value="4">一周热点</option>
										<option value="5">国际合作</option>
										<option value="6">科技进展</option>
										<option value="7">图片新闻</option>
										<option value="8">领导动态</option>
										<option value="9">统计数据</option>
										<option value="10">勘探开发</option>
										<option value="11">油价信息</option>
								</select> <marquee onmousemove="this.stop()" onmouseout="this.start()"
										direction="left" scrollamount="5" behavior="scroll"
										hidden="true"></marquee> <lable for="startTime">起始日期：</lable>
									<input name="startTime" id="startTime" type="text"
									onclick="return showCalendar('startTime', 'y-mm-dd');"
									style="width: 80px" />&nbsp; <lable for="endTime">终止日期：</lable>
									<input name="endTime" id="endTime" type="text"
									onclick="return showCalendar('endTime', 'y-mm-dd');"
									style="width: 80px" />&nbsp; 
									<select id="chartid" name="chartid">
										<option value="1">柱状图</option>
										<option value="2">饼状图</option>
										<option value="3">分析图</option>
								</select> <input type="submit" name="button" id="button" value="分析热词"
									onclick="return researchWarn()" />
									</td>
							</form>
							</thead>
						</table>					
					<table class="table table-bordered" height="400px"
						style="text-align: center;">
						<tr>
							<td>
								<img src="jfreechart.action" width="700" height="400" />
							</td>
						</tr>
					</table>

				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
	<!-- <script type="text/javascript" charset="UTF-8">
	function researchWarn() {
		var startTime = document.getElementById("startTime");
		var endTime = document.getElementById("endTime");
		if (startTime.value == "") {
			alert("请输入起始时间");
		
			return false;
		} else if (endTime.value == "") {
			alert("请输入终止时间");
		
			return false;
		}else {
			return true;
		}
		}

</script>-->
</body>
</html>