
<%@ page import="java.util.ArrayList,java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.util.*,java.io.*,java.net.*"%>

<%
	ArrayList<News> list = (ArrayList<News>) request
			.getAttribute("list");
	String columnsName[] = (String[]) request
			.getAttribute("columnsName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<script type="text/javascript" src="../js/manage.js" charset="UTF-8"></script>
<title>油气网络信息动态采集系统</title>
</head>
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
						request.setAttribute("manageLeftNav", "Yzrd");
					%>
					<jsp:include page="leftMenu.jsp"></jsp:include>
				</div>

				<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
					<!-- <table style="display: inline-block;width:100%;height:40px;margin-bottom: 5px;background-color: #f5f5f5;">
						<td width="99%"> -->
					<ol class="breadcrumb">
						<li><a href="#">业务中心</a>
						</li>
						<li><a href="#" class="active">一周热点</a>
						</li>
					</ol>
					<!-- </td>
						<td style="height:20px;">
							<a role="button" onclick="show()" class="btn btn-info"
							style="padding:1px;height:20px;width:40px;margin-top:5px;margin-bottom:5px;font-size:11px">更新</a>
						</td>
				</table> -->
					<form action="#" method="post" id="form1" name="form1">
						<%
							request.setAttribute("manageSearchId", "4");
						%>
						<jsp:include page="managejs.jsp"></jsp:include>
						<div class="table-responsive">
							<table class="table table-bordered table-hover table-striped">
								<thead align="center">
									<tr>
										<td><input type="checkbox" name="checkbox"
											onclick="isAll()"></td>
										<td>序号</td>
										<td>标题</td>
										<td>发布时间</td>
										<td>起始来源</td>
										<td>网站来源</td>
										<td>基本操作</td>
									</tr>
								</thead>
								<%
									for (int i = 0; i < list.size(); i++) {
										String title = String.valueOf(list.get(i).getTitle());
										if (title.length() > 20) {
											title = title.substring(0, 20) + "...";
										} else
											title = title;
								%>

								<tr>
									<td>
										<div align="center">
											<input type="checkbox" id="cheboxId" name="newsIds"
												value="<%=list.get(i).getId()%>">
										</div>
									</td>
									<td>
										<div align="center"><%=list.get(i).getRownumber()%></div>
									</td>
									<td>
										<div align="left">
											<a
												href="manageNews?newsId=<%=list.get(i).getId()%>&moduleId=4"
												target="_blank" title="<%=list.get(i).getTitle()%>"
												target="_blank"><%=title%> </a>
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
											<img src="../images/dow.gif" width="16" height="16" /> <a
												href="../manager/downloadSingle?newsId=<%=list.get(i).getId()%>&moduleId=4">
												下载</a>&nbsp; &nbsp; <img src="../images/edt.gif" width="16"
												height="16" /> <a
												href="manageNewsModify?newsId=<%=list.get(i).getId()%>&moduleId=4">
												修改</a>&nbsp;&nbsp; <img src="../images/del.gif" width="16"
												height="16" /> <a
												href="DeleteSingle?newsId=<%=list.get(i).getId()%>&moduleId=4"
												onclick="return isDelete()"> 删除</a>&nbsp;&nbsp;
											<%
												String isPub = "";
													String pub = "";
													if (list.get(i).getVisible() == 0) {
														isPub = "已发布";
														pub = "pub";
													} else if (list.get(i).getVisible() == 1) {
														isPub = "未发布";
														pub = "nopub";
													} else {
													}
											%>

											<img src="../images/<%=pub%>.gif" height="18" />&nbsp;<a
												href="isVisible?newsId=<%=list.get(i).getId()%>&moduleId=4&visible=<%=list.get(i).getVisible()%>"><%=isPub%></a>

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
												<td align="left">
													<table>
														<tr>
															<td id="download" name="download"><input
																type="button" name="submit" value="下载" id="downbtn"
																onclick="choseFileType(4)"></td>
															<td><input type="submit" name="submit" value="删除"
																onclick="return deleteBatch(4)"></td>
															<td><input type="submit" name="submit"
																value=" 导出到  [ 一周热点参考  ] " onclick="return dtrd(4)">
															</td>
															<td><select name="colNameOption" id="colNameOption"
																style="height:26px" onchange="exportVisible()">
																	<option value="0" selected>导出到 [工作动态参考 ]目标专栏</option>
																	<%
																		int columnsIndex = 0;
																		for (columnsIndex = 0; columnsIndex < columnsName.length; columnsIndex++) {
																	%>
																	<option value="<%=columnsIndex + 1%>"><%=columnsName[columnsIndex]%></option>
																	<%
																		}
																	%>
															</select></td>
															<td><input type="submit" name="exportGZDT"
																id="exportGZDT" style="display:none" value="导出"
																onclick="return dtck(4)"></td>
														</tr>
													</table></td>
												<td align="right"><jsp:include
														page="manageSearchByTitle.jsp"></jsp:include></td>
											</tr>
										</table>
									</td>
								</tr>
								</form>

								<tr>
									<td colspan="7" style="text-align:center"><jsp:include
											page="../commonUser/pageroll.jsp" flush="true">
											<jsp:param value="getManageData?moduleId=4" name="action" /></jsp:include></td>
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
