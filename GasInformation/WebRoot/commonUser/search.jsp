<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
      String searchIdCount=String.valueOf(request.getAttribute("searchIdCount"));
      String searchCurrentPage=String.valueOf(request.getAttribute("searchCurrentPage"));
 %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/manage.js" charset="UTF-8"></script>
<title>油气网络信息动态采集系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../css/bootstrap.min.css" rel ="stylesheet"/>
</head>

<body>     
  <div id="all" class="container"> 
		<jsp:include page="../commonUser/nav.jsp"></jsp:include>
		
		<div id="seaResult">
		<form action="#" method="post" id="form1" name="form1">
			<table class="table table-condensed">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<%
					ArrayList<HashMap<String, String>> listCurrpage = (ArrayList<HashMap<String, String>>)request.getAttribute("listCurrpage");
					session.setAttribute("1"+searchIdCount,listCurrpage);
					for(int i = 0;i < listCurrpage.size();i ++) {
				%>
				<tr>
					<td width="5%">&nbsp;</td>
					<td width="5%"><input type="checkbox" id="cheboxId"
						name="What_select" value="<%=i%>">
					</td>
					<td width="45%" align="middle"><div style="text-align:left">
							<a href="rearchNews?titleNO=<%=i%>&str_MK=<%=searchIdCount%>&searchCurrentPage=<%=searchCurrentPage%>" target="blank"><%=listCurrpage.get(i).get("title")%></a>
						</div></td>
					<td width="20%" align="left"><%=listCurrpage.get(i).get("time")%></td>
					<td width="20%" align="left"><%=listCurrpage.get(i).get("source")%></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td width="5%"><input type="hidden" name="searchIdCount" value="<%="1"+searchIdCount%>"></td>
					<!-- <td valign="top"><input type="submit" name=xiazai value="下载"
						onclick="return downloadBatch(55)">
					</td> -->
					<td valign="top" id="download" name="download">
						<input type="button" name="submit" value="下载" id="downbtn" onclick="choseFileType(55)">
											</td>
			<td colspan="4" align="center">
			    <jsp:include page="searchPage.jsp" flush="true">
					<jsp:param value="pageroll" name="action" />
					<jsp:param value="<%=searchIdCount%>" name="searchIdCount" />
				</jsp:include></td>
			</tr>
		</table>
			</form>
		</div>
  		
		<jsp:include page="../commonUser/footer.jsp"></jsp:include>
  </div> 				
</body>
</center>
</html>

