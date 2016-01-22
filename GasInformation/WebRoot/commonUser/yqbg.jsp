
<%@ page import="java.util.ArrayList,java.util.HashMap"
	errorPage="error.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.util.News"%>

<%
	ArrayList<News> listYqbg =(ArrayList<News>)request.getAttribute("listYqbg");
	ArrayList<News> listQyyq =(ArrayList<News>)request.getAttribute("listQyyq");
	ArrayList<HashMap<String, Integer>> listHotWord=(ArrayList<HashMap<String, Integer>>)request.getAttribute("listHotWord");

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
  		<%request.setAttribute("menuIndex", "Yqbg");%> 
		<jsp:include page="../commonUser/nav.jsp"></jsp:include>
		
		<div class="row"> 
			<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3"> 
				<table id="left" class="table table-condensed">				
				<tr>
					<th colspan="2" style="border-radius:10px 10px 0px 0px;background-color:#4fb2d1" colspan="2">
					<span>&nbsp;<img src="../images/mainIcon.png" height="14px"/></span>
					<font face="宋体"  color="#FFFFFF"><b>&nbsp;企业舆情</b></font></th>
				</tr>
				<%
							for (int i = 28; i <35; i++) {
							String title=String.valueOf(listQyyq.get(i).getTitle());
				%>    
				<tr>	
							<td>* <a href="news?newsId=<%=listQyyq.get(i).getId()%>&moduleId=4" title="<%=title%>" target="_blank"><%if(title.length()>8)
								out.print(title.substring(0,8)+"...");
							else
								out.print(title);
							%></a>
							</td>
							<td><%=listQyyq.get(i).getPubTime() %></td>
				</tr>				
				<%
					}
				%>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td colspan="2" style="border-radius:10px 10px 0px 0px;background-color:#4fb2d1" colspan="2">
					<span>&nbsp;<img src="../images/mirCon.png" height="14px"/></span>
					<font face="宋体" color="#FFFFFF"><b>&nbsp;热词分析</b></font></td>
				</tr>
				<tr>
					<td><font color="#0000FF"><b>热词</b></font></td>
					<td><font color="#0000FF"><b>关注度</b></font></td>
				</tr>
				<%
					//热词分析
							for (HashMap<String, Integer> dat : listHotWord) {
							for(java.util.Map.Entry<String, Integer> entry : dat.entrySet()) {
				%>
				<tr>
					<td><%=entry.getKey()%></td>
					<td><%=entry.getValue()%></td>
				</tr>
				<%
					}
							}
				%>  
				</table>						
			</div> 
			
			 
			<div class="col-md-9 col-xs-9 col-sm-9 col-lg-9">
				<table id="right" class="table table-condensed">
				<tr>
					<td style="border-radius:10px 0px 0px 0px;background-color:#4fb2d1"  colspan="2">
						<div align="left"><span>&nbsp;<img src="../images/calCon.png" height="14px"/></span>
						<font face="宋体" color="#FFFFFF"><b>&nbsp;舆情报告</b></font></div></td>
					<td style="border-radius:0px 10px 0px 0px;background-color:#4fb2d1;text-align:center;text-valign:middle">
							&nbsp;
					</td>									   
				</tr>

				<%
					for (int i =0; i <listYqbg.size(); i++) {
						String title=String.valueOf(listYqbg.get(i).getTitle());
				%>

				<tr>

					<td><div align="left">
							&nbsp;> <a href="news?newsId=<%=listYqbg.get(i).getId()%>str_MK=Yqbg" target="_blank" title="<%=title%>"><%if(title.length()>25)
								out.print(title.substring(0,25)+"...");
							else
								out.print(title);
							%></a>
						</div></td>
					<td><%=listYqbg.get(i).getOriginSource()%></td>
					<td style="text-align: center"><%=listYqbg.get(i).getPubTime()%></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="4" align="center">
					<jsp:include page="pageroll.jsp" flush="true">
						<jsp:param value="getData?str_MK=Yqbg" name="action" />
					</jsp:include></td>
				</tr>
			</table>
			</div>			  
		</div>	
  		
		<jsp:include page="../commonUser/footer.jsp"></jsp:include>
  </div> 
</body>
</html>