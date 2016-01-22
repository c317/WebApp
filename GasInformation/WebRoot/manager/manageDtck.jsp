<%@ page import="java.util.ArrayList,java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.util.*,java.io.*,java.net.*"%>
<%

	String columnsName[] = (String[]) request
	.getAttribute("columnsName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<title>油气网络信息动态采集系统</title>
<body>
	<div id="top">
		<%
			request.setAttribute("manageNav", "Ywzx");
		%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>

	<div class="container-fluid">
		<div id="content">
			<div class="row">
				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
					<%
						request.setAttribute("manageLeftNav", "Dtck");
					%>
					<jsp:include page="leftMenu.jsp"></jsp:include>
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					<ol class="breadcrumb">
						<li><a href="#">业务中心</a>
						</li>
						<li><a href="#" class="active">工作动态参考</a>
						</li>
					</ol>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-striped">
							<thead align="center">
								<tr>
									<form action="deleteBatch?moduleId=13" method="post"
											id="form1" name="form1">
									<td>标题</td>
									<td>发布时间</td>
									<td>起始来源</td>
									<td>网站来源</td>
									<td>基本操作</td>
									<td>专栏</td>
									<td>确认</td>
								</tr>
							</thead>
							<%
								ArrayList<News> list = (ArrayList<News>) request.getAttribute("list");
																						for (int i =0; i <list.size(); i++) {
																							String title=String.valueOf(list.get(i).getTitle());
																							if(title.length()>20){
																								title=title.substring(0,20)+"...";}
																							else title=title;
							%>

							<tr>
									<div align="center" style="display:none">
										<input type="checkbox" id="cheboxId<%=list.get(i).getId()%>" name="newsIds"
											value="<%=list.get(i).getId()%>">
									</div>
									<td>
									<div align="left">
										<a
											href="manageNews?newsId=<%=list.get(i).getId()%>&moduleId=13"
											target="_blank" title="<%=list.get(i).getTitle()%>"
											target="_blank"><%=title%> </a>
									</div></td>
								<td>
									<div align="center"><%=list.get(i).getPubTime()%></div></td>
								<td>
									<div align="center"><%=list.get(i).getOriginSource()%></div></td>
								<td>
									<div align="center"><%=list.get(i).getSiteSource()%></div></td>
								<td>
									<div align="center">
										<img src="../images/arrow_top.png" width="16" height="16" />
										<a
											href="updownstick?moduleId=13&updown=up&updownId=<%=list.get(i).getUpdownId()%>&columnOrder=<%=list.get(i).getColumnOrder()%>&newsId=<%=list.get(i).getId()%>">
											上移</a>&nbsp;&nbsp; <img src="../images/arrow_down.png" width="16"
											height="16" /> <a
											href="updownstick?moduleId=13&updown=down&updownId=<%=list.get(i).getUpdownId()%>&columnOrder=<%=list.get(i).getColumnOrder()%>&newsId=<%=list.get(i).getId()%>">
											下移</a>&nbsp;&nbsp; <img src="../images/top.png" width="16"
											height="16" /> <a
											href="updownstick?moduleId=13&updown=stick&updownId=<%=list.get(i).getUpdownId()%>&columnOrder=<%=list.get(i).getColumnOrder()%>&newsId=<%=list.get(i).getId()%>">
											置顶</a>&nbsp;&nbsp; <img src="../images/edt.gif" width="16"
											height="16" /> <a
											href="manageNewsModify?newsId=<%=list.get(i).getId()%>&moduleId=13">
											修改</a>&nbsp;&nbsp; <img src="../images/del.gif" width="16"
											height="16" /> <a
											href="DeleteSingle?newsId=<%=list.get(i).getId()%>&moduleId=13">
											删除</a>
									</div></td>
								<td>
									<div align="center">
										<select id="columnSelectes<%=list.get(i).getId()%>">
											<option id="columnSelected" selected><%=list.get(i).getColumnName()%></option>
											<%
												for(int columnIndex=0;columnIndex<columnsName.length;columnIndex++){
													%>
													<option id="columnSelected"><%=columnsName[columnIndex]%></option>				
											<%
												}
											%>
										</select>
									</div></td>
								<td>
									<div align="center"><input type="submit" name="submit" value="确定"
															onclick="return commit(<%=list.get(i).getId()%>)"></div></td>
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
														<td><input type="submit" name="submit" value="删除"
															onclick="return sc()">
														</td>
														<td><input type="submit" name="submit" value="清空"
															onclick="return qk()">
														</td>
														<td><input type="submit" name="submit"
															value=" 生成  [ 工作动态参考 ] " onclick="return dtck()">
														</td>
													</tr>
												</table></td>
											<td></td>
										</tr>
									</table></td>
							</tr>
							</form>

							<tr>
								<td colspan="7" style="text-align:center"><jsp:include
										page="../commonUser/pageroll.jsp" flush="true">
										<jsp:param value="getManageData?moduleId=13" name="action" /></jsp:include></td>
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
function commit(id){
	var newsId = document.getElementById("cheboxId"+id).value;
	var selectObj = document.getElementById("columnSelectes"+id);
    var selectIdx = selectObj.selectedIndex;
    var optionValue = selectObj.options[selectIdx].value;
    document.form1.action="modifyColumnName?id=" + newsId + "&toColumnName=" + optionValue + "&moduleId=13";
    var result = confirm("是否要修改？");
    if(result == true){
		return true;
    }else{   	
    	 return false;
    } 
}

function sc(){
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

function dtck(){
	document.form1.action="downloadBatch?moduleId=13&fileType=2";
	}
	
function qk(){
	document.form1.action="DeleteAll?moduleId=13";	
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
			document.form1.action="manageDownloadSelect.jsp?moduleId=13";
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
			return false;
		}else{
			document.form1.action="manageNewsRetrieval?moduleId=13";
		}
		}
</script>
</html>
