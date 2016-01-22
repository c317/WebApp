<%@ page import="java.util.ArrayList,java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.gasinfo.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
   int moduleId=Integer.valueOf(String.valueOf(request.getAttribute("indexModuleId")));
   int ModuleIdcount=Integer.valueOf(String.valueOf(request.getAttribute("moduleIdcount")));
%>
<meta http-equiv="Content-Type"
	content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/link.css">
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
				<ol class="breadcrumb">
					<li><a href="#">业务中心</a></li>
					<li><a href="#" class="active">检索</a></li>
				</ol>
					
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead  align="center">
							<tr>
								<td><form action="FriendServlet?method=delete_select&moduleId=<%=moduleId%>"
										method="post" id="form1" name="form1">
									<input type="checkbox" name="checkbox" onclick="b()"></td>
								<td>序号</td>
								<td>标题</td>
								<td>发布时间</td>
								<td>起始来源</td>
								<td>网站来源</td>
								<td>基本操作</td>
							</tr>
						</thead>
						<%
							ArrayList<News> list = (ArrayList<News>) request.getAttribute("m_research");
							for (int i =0; i <list.size() ; i++) {
								String s=String.valueOf(list.get(i).getTitle());
							if(s.length()>20){
								s=s.substring(0,20)+"...";
								}else s=s;
						%>
						
						<tr>
							<td>
								<div align="center">
								<input type="checkbox" id="cheboxId" name="newsIds" value="<%=list.get(i).getId()%>">
								</div>
							</td>
							<td>
								<div align="center"><%=list.get(i).getRownumber()%></div>
							</td>
							<td>
								<div align="left">
								<a href="manageNews?newsId=<%=list.get(i).getId()%>&moduleId=<%=moduleId%>" target="_blank"
										title="<%=list.get(i).getTitle()%>" target="_blank"><%=s%>
								</a>
								</div>
							</td>
							<td>
								<div align="center"><%=list.get(i).getPubTime()%></div>
							</td>
							<td>
								<div align="center"><%=list.get(i).getOriginSource()%></div>
							</td>
							<td>
								<div align="center"><%=list.get(i).getSiteSource()%></div>
							</td>
							<td>
								<div align="center">
									<img src="../images/dow.gif" width="16" height="16" />
										<a href="../manager/downloadSingle?newsId=<%=list.get(i).getId()%>&moduleId=1"
											onclick>下载</a>&nbsp; &nbsp; 
									<img src="../images/edt.gif" width="16" height="16" />
										<a href="manageNewsModify?newsId=<%=list.get(i).getId()%>&moduleId=5">
											修改</a>&nbsp;&nbsp;
									<img src="../images/del.gif" width="16" height="16" />
										<a href="DeleteSingle?newsId=<%=list.get(i).getId()%>&moduleId=5">
											删除</a>&nbsp;&nbsp;
										<%
											String isPub="";
											String pub="";
											if(list.get(i).getVisible()==0){
												isPub="已发布";
												pub="pub";
											}else if(list.get(i).getVisible()==1) { 
												isPub="未发布";
												pub="nopub";
											}else { }
										%>

									<img src="../images/<%=pub%>.gif" height="18" />&nbsp;<a
										href="isVisible?newsId=<%=list.get(i).getId()%>&moduleId=5&visible=<%=list.get(i).getVisible()%>"><%=isPub%></a>														
								</div>
							</td>
						</tr>
						<%
							}
						%>
						
						<tr>
							<td colspan="7">
							<table width="100%">
							<tr>
								<td width="65%">								
									<table>
										<tr>
											<td><input type="submit" name="submit" value="下载" onclick="return downloadBatch()"></td>
											<td><input type="submit" name="submit" value="删除" onclick="return a()"></td>
											<td><input type="submit" name="submit" value="导出到一周热点" onclick="return dtrd()"></td>
											<td><input type="submit" name="submit" value="导出到工作动态与参考" onclick="return dtck()"></td>
										</tr>
									</table>
								</td>								
							</tr>
							</table>
							</td>
						</tr>
						</form>
							
						<tr>
							<td colspan="7" style="text-align:center">
							<jsp:include page="searchPageroll.jsp" flush="true">
			                         <jsp:param name="action" value="manageNewsRetrievalPage" />
								     <jsp:param name="moduleId" value="<%=moduleId%>" />
									 <jsp:param name="moduleIdcount" value="<%=ModuleIdcount%>" />
							</jsp:include>
						</tr>
					</table>
				</div>
			</div>
							
		</div>
	</div>
</div>	

		
	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
<script>
function downloadBatch() {
			document.form1.action="../manager/downloadBatch?moduleId=<%=moduleId%>";
			var checkObj = document.all("cheboxId");
			var j=0;
			for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){
					j++;
				}
			}
			if(j != 0) {
				return true;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}
		}
		
		function b(){
			var checkObj = document.all("cheboxId");		
				for(var i = 0;i<checkObj.length;i++){			
					if(checkObj[i].checked == true) 
						checkObj[i].checked = false;			
					else 
						checkObj[i].checked = true;		
			}	
		}
		
		
		function a() {
		document.form1.action="../manager/deleteBatch?moduleId=<%=moduleId%>";
			var checkObj = document.all("cheboxId");
			var j=0;
			for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){
					j++;
				}
			}
			if(j != 0) {
				return true;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}
		}
		
		
		function c() {
			
			document.form1.action="manageDownloadSelect.jsp?moduleId=<%=moduleId%>";
			var checkObj = document.all("cheboxId");
			var j=0;
			for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){
					j++;
				}
			}
			if(j != 0) {
				return true;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}
		}

		function dtck() {
			document.form1.action="manageNewsDtck?moduleId=<%=moduleId%>";
			var checkObj = document.all("cheboxId");
			var j=0;
			for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){
					j++;
				}
			}
			if(j != 0) {
				return true;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}
		}
		
		function dtrd() {
			document.form1.action="FriendServlet?method=addDtrd&moduleId=1";
			var checkObj = document.all("cheboxId");
			var j=0;
			for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){
					j++;
				}
			}
			if(j != 0) {
				return true;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}
		}
		

		function d() {

		var checkObj1 = document.all("checkbox1");
		var checkObj2 = document.all("checkbox2");
		var checkObj3 = document.all("checkbox3");
		var checkObj4 = document.all("checkbox4");
		var checkObj5 = document.all("checkbox5");
		var i = 0;
		if(checkObj1.checked==false&&checkObj2.checked==false&&checkObj3.checked==false&&checkObj4.checked==false&&checkObj5.checked==false)	{
		
			alert("请至少选择一个检索条件");
			document.form1.action="";
		}else{
			document.form1.action="FriendServlet?method=retrieval&moduleId=<%=moduleId%>";	
		}
		}	
		
		function e() {

				alert("请到E:\\Workplace\\Downloadfile查看已下载信息");
		}	
		
			
			</script>
</html>
