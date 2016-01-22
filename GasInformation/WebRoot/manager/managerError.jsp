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
						request.setAttribute("manageLeftNav3", "");
					%>
					<jsp:include page="leftMenu3.jsp"></jsp:include>
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					<ol class="breadcrumb">
						<li><a href="#">业务定制</a></li>
					</ol>

					
					<table class="table table-bordered" height="400px"
						style="text-align: center;">
						<tr>
							<td>		
								<p style="text-align:center;height:400px"><font size="3px" color="#FF0000"><b>"抱歉！该时间段没数据，无法生成图表"</b></font></p>
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