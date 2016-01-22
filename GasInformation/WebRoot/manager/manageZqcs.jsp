<%@ page
	import="java.util.ArrayList,java.util.HashMap,com.gasinfo.config.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>油气网络信息动态采集系统</title>
<link type="text/css" rel="stylesheet" href="../css/calendar.css">
<script type="text/javascript" src="../js/calendar.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-zh.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-setup.js" charset="UTF-8"></script>
<%
	Configuration rc = new Configuration("daoconfig.properties");
	String strTime = rc.getValue("Time_Heritrix");
	int intTime = Integer.valueOf(strTime);
	int intHourDefault = intTime / 3600;
	int intMinuteDefault = (intTime % 3600) / 60;
%>
</head>
<body>
	<div id="top">
		<%request.setAttribute("manageNav", "Xtgl");%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
			<%request.setAttribute("manageLeftNav2", "Zqcs");%>
			<jsp:include page="leftMenu2.jsp"></jsp:include>
		</div>
		<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
			<table>
				<tr>
					<td>抓取参数设置：</td>
				</tr>
				<tr>
					<table><tr>
						<td width="40">&nbsp;</td>
						<td>网页抓取时间&nbsp;&nbsp;&nbsp;</td>
						<form id="paChong" name="paChong"  action="modTime" method="post">
						<td><select id="strHour" name="strHour">
								<option value="<%=intHourDefault%>" selected><%=intHourDefault%></option>
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</select> 小时
							 <select id="strMinute" name="strMinute">
								<option value="<%=intMinuteDefault%>"><%=intMinuteDefault%></option>
								<option value="5">5</option>
								<option value="10">10</option>
								<option value="15">15</option>
								<option value="20">20</option>
								<option value="25">25</option>
								<option value="30">30</option>
								<option value="35">35</option>
								<option value="40">40</option>
								<option value="45">45</option>
								<option value="50">50</option>
								<option value="55">55</option>
							</select> 分&nbsp;&nbsp;&nbsp;</td>
						<td>
							<marquee onmousemove="this.stop()" onmouseout="this.start()" 
								direction="left" scrollamount="5" behavior="scroll" hidden="true"></marquee>
							&nbsp;<lable for="startTime">开始年份：</lable>&nbsp;
							<input name="startTime" id="startTime" type="text" onclick="return showCalendar('startTime', 'y-mm-dd');" style="width: 80px" />
						</td>
						<td><input type="submit" name="button" id="button" value="确定"></td>
						</form>
					</tr>
					<tr>
						<td width="40">&nbsp;</td>
						<td>指定时间设置&nbsp;&nbsp;&nbsp;</td>
						<form id="paChong" name="paChong"  action="modTime" method="post">
						<td><select id="strHour" name="strHour">
								<option value="<%=intHourDefault%>" selected><%=intHourDefault%></option>
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</select> 小时
							 <select id="strMinute" name="strMinute">
								<option value="<%=intMinuteDefault%>"><%=intMinuteDefault%></option>
								<option value="5">5</option>
								<option value="10">10</option>
								<option value="15">15</option>
								<option value="20">20</option>
								<option value="25">25</option>
								<option value="30">30</option>
								<option value="35">35</option>
								<option value="40">40</option>
								<option value="45">45</option>
								<option value="50">50</option>
								<option value="55">55</option>
							</select> 分&nbsp;&nbsp;&nbsp;</td>
						<td>
							<marquee onmousemove="this.stop()" onmouseout="this.start()" 
								direction="left" scrollamount="5" behavior="scroll" hidden="true"></marquee>
							
							
						</td>
						<td><input type="submit" name="button" id="button" value="确定"></td>
						</form>
					</tr>
				</table></tr></table>
		</div>
	</div>
</div>	
<jsp:include page="../commonUser/footer.jsp"></jsp:include>				
</body>
</html>