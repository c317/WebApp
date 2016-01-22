 <%@ page import="java.util.ArrayList,java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.gasinfo.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="link.css">
<title>油气网络信息动态采集系统</title>
</head>
<body>
	<div id="top">
		<%request.setAttribute("manageNav", "Ywzx");%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
	
<div class="container-fluid">
	<div id="content">
		<div class="row">
			<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
				<jsp:include page="leftMenu.jsp"></jsp:include>
			</div>
 		
 			<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
				<!-- <table style="display: inline-block;width:100%;height:40px;margin-bottom: 5px;background-color: #f5f5f5;">
						<td width="99%"> -->
							<ol class="breadcrumb">
								<li><a href="#">业务中心</a>
								</li>
								<li><a href="#" class="active">检索</a>
								</li>
							</ol>
						<!-- </td>
						<td style="height:20px;">
							<a role="button" onclick="show()" class="btn btn-info"
							style="padding:1px;height:20px;width:40px;margin-top:5px;margin-bottom:5px;font-size:11px">更新</a>
						</td>
				</table> -->
											
				<div class="table-responsive">
					<table class="table"> 		
  						<tr>
  							<td width="100%" height="20" style="text-align: center;">
  							<font size="3px" color="#FF0000"><b>抱歉！检索不到您要的结果</b></font>
  							</td>
  						</tr>  		
  					</table>
  				</div>
  			</div>
  		</div>
  	</div>
 </div>


	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
</html>
