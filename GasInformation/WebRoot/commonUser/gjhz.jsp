
<%@ page import="java.util.ArrayList,java.util.HashMap"
	errorPage="error.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.util.News"%>

<%
	ArrayList<News> listGjhz =(ArrayList<News>)request.getAttribute("listGjhz");
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
  		<%request.setAttribute("menuIndex", "Gjhz");%> 
		<jsp:include page="../commonUser/nav.jsp"></jsp:include>
		
		<div class="row"> 
			<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3"> 
				<table id="left" class="table table-condensed">
				<tr>
					<td colspan="2" style="border-radius:10px 10px 0px 0px;background-color:#4fb2d1">
					<span>&nbsp;<img src="../images/mainIcon.png" height="14px"/></span>
					<font face="宋体" color="#FFFFFF"><b>&nbsp;国际石油公司</b></font></td>
				</tr>
				<tr>
					
				</tr>
				<tr>
					<td>*<a href="http://www.saudiaramco.com/en/home.html"
						target="_blank">沙美石油公司-世界最大的石油公司</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.petrobras.com/en/home.htm"
						target="_blank">巴西石油公司 (Petrobras)</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.petronas.com.my/Pages/default.aspx"
						target="_bank">马来西亚国家石油公司(Petronas)</a>
					</td>
				</tr>
				<tr>
					<td>*<a
						href="http://www.pemex.com/Paginas/default.aspx#.VFeK1smu3-0"
						target="_blank">墨西哥国家石油公司(PEMEX)</a>
					</td>
				</tr>
				<tr height="20" width="250">
					<td>*<a href="http://corporate.exxonmobil.com/en/"
						target="_blank">埃克森美孚公司(ExxonMobil)</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.bp.com/" target="_blank">英国石油公司
							(British Petroleum)</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.cnbc.com/id/1504950" target="_blank">康菲石油公司
							(ConocoPhillips)</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="https://www.bsp.com.bn/main/index.htm"
						target="_bank">壳牌公司 (Shell Petroleum)</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.chevron.com/" target="_blank">雪佛龙公司(Chevron Corporation)</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.devonenergy.com/" target="_blank">美国最大的独立油气公司-DEVON</a>
					</td>
				</tr>
				<tr>
					<td>*<a
						href="http://www.bhpbilliton.com/home/Pages/default.aspx"
						target="_bank">澳大利亚毕和毕拓公司(BHP Billiton)</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.inpex.co.jp/" target="_bank">日本帝国石油公司</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.yukosnewsonline.com/" target="_bank">俄罗斯尤科斯(YUKOS)石油公司</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.huskyenergy.com/" target="_bank">赫斯基能源
							(Husky Energy) 公司</a>
					</td>
				</tr>
				<tr>
					<td>*<a href="http://www.anadarko.com/Home/Pages/Home.aspx"
						target="_bank">Anadarko 石油公司</a>
					</td>
				</tr>		
			</table>						
			</div> 
			
			 
			<div class="col-md-9 col-xs-9 col-sm-9 col-lg-9">
				<table id="right" class="table table-condensed">
				<tr>
					<td style="border-radius:10px 0px 0px 0px;background-color:#4fb2d1"  colspan="2">
						<div align="left"><span>&nbsp;<img src="../images/calCon.png" height="14px"/></span>
						<font face="宋体" color="#FFFFFF"><b>&nbsp;对外合作</b></font></div></td>
					<td style="border-radius:0px 10px 0px 0px;background-color:#4fb2d1;text-align:center;text-valign:middle">
							&nbsp;
					</td>									   
				</tr>

				<%
					for (int i =0; i <listGjhz.size(); i++) {
						String title=String.valueOf(listGjhz.get(i).getTitle());
				%>

				<tr>

					<td><div align="left">
							&nbsp;> <a href="news?newsId=<%=listGjhz.get(i).getId()%>&moduleId=5" target="_blank" title="<%=title%>"><%if(title.length()>25)
								out.print(title.substring(0,25)+"...");
							else
								out.print(title);
							%></a>
						</div></td>
					<td><%=listGjhz.get(i).getOriginSource()%></td>
					<td style="text-align: center"><%=listGjhz.get(i).getPubTime()%></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="4" align="center">
					<jsp:include page="pageroll.jsp" flush="true">
						<jsp:param value="getData?moduleId=5" name="action" />
					</jsp:include></td>
				</tr>
			</table>
			</div>			  
		</div>	
  		
		<jsp:include page="../commonUser/footer.jsp"></jsp:include>
  </div> 
</body>
</html>




