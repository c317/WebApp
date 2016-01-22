
<%@ page import="java.util.ArrayList,java.util.HashMap"
	errorPage="error.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.util.News"%>

<%
	ArrayList<News> listYjxx=(ArrayList<News>)request.getAttribute("listYjxx");
	ArrayList<News> listLddt=(ArrayList<News>)request.getAttribute("listLddt");
	ArrayList<News> listTjsj=(ArrayList<News>)request.getAttribute("listTjsj");
	session.setAttribute("listYjxx", listYjxx);
	session.setAttribute("listLddt", listLddt);
	session.setAttribute("listTjsj", listTjsj);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">
<head>
<meta charset ="UTF-8">
<title>油气网络信息动态采集系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../css/bootstrap.min.css" rel ="stylesheet"/>
</head>

<!-- <center> -->
<body>     
  <div id="all" class="container">
		<%request.setAttribute("menuIndex", "Qt");%> 
		<jsp:include page="../commonUser/nav.jsp"></jsp:include>
		
		<div class="row"> 
			<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3"> 
				<table id="left" class="table table-condensed">				
				<tr>
					<th colspan="2" style="border-radius:10px 10px 0px 0px;background-color:#4fb2d1" colspan="2">
					<span>&nbsp;<img src="../images/mainIcon.png" height="14px"/></span>
					<font face="宋体"  color="#FFFFFF"><b>&nbsp;领导动态</b></font></th>
				</tr>
				<%
							for (int i = 0; i <7; i++) {
							String title=String.valueOf(listLddt.get(i).getTitle());
				%>    
				<tr>	
							<td>* <a href="news?newsId=<%=listLddt.get(i).getId()%>&moduleId=8" title="<%=title%>" target="_blank"><%if(title.length()>8)
								out.print(title.substring(0,8)+"...");
							else
								out.print(title);
							%></a>
							</td>
							<td><%=listLddt.get(i).getPubTime() %></td>
				</tr>				
				<%
					}
				%>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td colspan="2" style="border-radius:10px 10px 0px 0px;background-color:#4fb2d1" colspan="2">
					<span>&nbsp;<img src="../images/mirCon.png" height="14px"/></span>
					<font face="宋体" color="#FFFFFF"><b>&nbsp;统计数据</b></font></td>
				</tr>
				<%
					for (int i =0; i<7; i++) {
									String title=String.valueOf(listTjsj.get(i).getTitle());
				%>
				<td>* <a href="news?newsId=<%=listTjsj.get(i).getId()%>&moduleId=9"
						target="_blank" title="<%=title%>"> 
						<%
 							if(title.length()>10)
     							out.print(title.substring(0,9)+"...");
     									else
     										out.print(title);
 						%> </a>
				</td>
				<td width="80"><%=listTjsj.get(i).getPubTime()%></td>
				</tr>
				<%
					}
				%> 
				</table>						
			</div> 
			
			 
			<div class="col-md-9 col-xs-9 col-sm-9 col-lg-9">
				<table id="right" class="table table-condensed">
				<tr>
					<td style="border-radius:10px 0px 0px 0px;background-color:#4fb2d1"  colspan="2">
						<div align="left"><span>&nbsp;<img src="../images/calCon.png" height="14px"/></span>
						<font face="宋体" color="#FFFFFF"><b>&nbsp;一周热点</b></font></div></td>
					<td style="border-radius:0px 10px 0px 0px;background-color:#4fb2d1;text-align:center;text-valign:middle">
							&nbsp;
					</td>									   
				</tr>

				<%
					for (int i =0; i <listYjxx.size(); i++) {
						String title=String.valueOf(listYjxx.get(i).getTitle());
				%>

				<tr>

					<td><div align="left">
							&nbsp;> <a href="news?newsId=<%=listYjxx.get(i).getId()%>&moduleId=4" target="_blank" title="<%=title%>"><%if(title.length()>25)
								out.print(title.substring(0,25)+"...");
							else
								out.print(title);
							%></a>
						</div></td>
					<td><%=listYjxx.get(i).getOriginSource()%></td>
					<td style="text-align: center"><%=listYjxx.get(i).getPubTime()%></td>
				</tr>
				<%
					}
				%>
	<!--  			<tr>
					<td colspan="4" align="center">
					<jsp:include page="pageroll.jsp" flush="true">
						<jsp:param value="getData?moduleId=4" name="action" />
					</jsp:include></td>
				</tr>  -->
			</table>
			</div>			  
		</div>	
  		
		<jsp:include page="../commonUser/footer.jsp"></jsp:include>
  </div> 
</body>
</html>