<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
			request.setAttribute("manageNav", "Ywdz");
		%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>

	<div class="container-fluid">
		<div id="content">
			<div class="row">
				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
					<%
						request.setAttribute("manageLeftNav3", "Xxtj");
					%>
					<jsp:include page="leftMenu3.jsp"></jsp:include>
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					<ol class="breadcrumb">
						<li><a href="#">业务定制</a></li>
						<li><a href="#" class="active">信息统计</a></li>
					</ol>


					<div class="table-responsive">
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
</html>