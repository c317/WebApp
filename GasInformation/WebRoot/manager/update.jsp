<%@page import="com.sun.xml.internal.bind.v2.model.core.ID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gasinfo.util.*,java.util.ArrayList,java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<title>油气网络信息动态采集系统</title>
<style type="text/css">
td {
	text-align: left;
	font-size: 12px;
}

<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

#left {
	margin-left: 2px;
}
-->
</style>
<%
	String moduleName = "";
	String normal="";
	int updateId = Integer.parseInt(request.getParameter("newsId"));
	int moduleId = Integer.parseInt(request.getParameter("moduleId"));
	Newsdao userdao = DaoFactory.getInstance().getNewsDao();
	News news = userdao.findById(updateId, moduleId);
	session.setAttribute("nowNews", news);
	switch(news.getModule()){
	case 1:moduleName = "工作动态";break;
	case 2:moduleName = "矿产管理";break;
	case 3:moduleName = "政策法规";break;
	case 4:moduleName = "一周热点";break;
	case 5:moduleName = "国际合作";break;
	case 6:moduleName = "科技进展";break;
	case 7:moduleName = "图片新闻";break;
	case 8:moduleName = "领导动态";break;
	case 9:moduleName = "统计数据";break;
	case 10:moduleName = "勘探开发";break;
	case 11:moduleName = "油价信息";break;
	case 12:moduleName = "动态热点";break;
	case 13:moduleName = "动态参考";break;
	default:moduleName=null;break;
	}
	switch(news.getType()){
	case 0:normal="常规";break;
	case 1:normal="非常规";break;
	default:normal=null;
	}
%>
</head>
<body>
	<div id="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					background="../images/bg_top.png">
					<tr height="60">
						<td width="9%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
							src="../images/logo.png" width="60" height="60" /></td>
						<td width="3%"><img src="../images/BoxOver_bd.png" width="1"
							height="30" /></td>
						<td width="30%"><img src="../images/title.gif" width="450"
							height="30" /></td>
						<td width="58%">&nbsp;</td>
					</tr>
				</table>
			</tr>
			<tr>
				<td height="28" background="../images/main_36.gif"><table
						width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12%" height="28" background="../images/main_32.gif"
								style="background-repeat:no-repeat;">&nbsp;</td>
							<td width="100%"><img src="../images/main_36.gif" width="100%"
								height="28"></td>
							<td width="20%"><img src="../images/main_37.gif" height="28">
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>

	<div id="middle">
		<table id="left" width="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td width="168" id=frmTitle noWrap name="fmTitle" align="center"
					valign="top">
					<table width="10%" height="100%" border="0" cellpadding="0"
						cellspacing="0" style="table-layout:fixed;">
						<tr>
							<td width="168">
								<table width="163" height="100%" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td valign="top">
											<table width="151" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td>
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td height="23" background="../images/main_47.gif">
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td width="0%">&nbsp;</td>
																			<td width="100%" style="text-align:center"
																				class="STYLE1">业务中心</td>
																		</tr>
																	</table></td>
															</tr>
															<tr>
																<td background="../images/main_51.gif" id="submenu1">
																	<div>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table width="80%" border="0" align="center"
																						cellpadding="0" cellspacing="0">
																						<tr>
																							<td width="10%" height="25">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td width="90%" height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=1">工作动态</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=4">一周热点</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=3">政策法规</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=2">矿产管理</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=10">勘探开发</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=6">科技进展</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=5">国际合作</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=8">领导动态</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=11">油价信息</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=9">统计数据</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23">
																								<div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div></td>
																							<td height="23">
																								<table width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=7">图片新闻</a>
																										</td>
																									</tr>
																								</table></td>
																						</tr>
																						<tr>
																							<td height="23"><div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td height="23"><table width="95%"
																									border="0" cellspacing="0" cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=13">工作动态参考</a>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>
																						<tr>
																							<td height="23"><div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td height="23"><table width="95%"
																									border="0" cellspacing="0" cellpadding="0">
																									<tr>
																										<td height="20"><a
																											href="../manager/getManageData?moduleId=12">一周热点参考</a>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>
																					</table></td>
																			</tr>
																			
																			<tr>
																				<td height="5"><img src="../images/main_52.gif"
																					width="151" height="5" />
																				</td>
																			</tr>
																		</table>
																	</div></td>
															</tr>
														</table></td>
												</tr>
												<tr>
													<td><table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td height="23" background="../images/main_47.gif"
																	id="imgmenu2" class="menu_title"
																	onmouseover="this.className='menu_title2';"
																	onclick="showsubmenu(2)"
																	onmouseout="this.className='menu_title';"
																	style="cursor:hand">
																	<table width="100%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td width="0%">&nbsp;</td>
																			<td style="text-align:center" width="100%"
																				class="STYLE1">系统管理</td>
																		</tr>
																	</table></td>
															</tr>
															<tr>
																<td background="../images/main_51.gif" id="submenu2"><div
																		class="sec_menu">
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table width="80%" border="0" align="center"
																						cellpadding="0" cellspacing="0">
																						<tr>
																							<td width="10%" height="25"><div
																									align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td width="90%" height="23"><table
																									width="95%" border="0" cellspacing="0"
																									cellpadding="0">
																									<tr>
																										<td height="20"><a href="../index.action"
																											target="_blank">返回首页</a>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>
																						<tr>
																							<td height="23"><div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td height="23"><table width="95%"
																									border="0" cellspacing="0" cellpadding="0">
																									<tr>
																										<td height="20"><a href="peizhi.jsp"
																											target="_blank">系统设置</a>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>
																						<tr>
																							<td height="23"><div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td height="23"><table width="95%"
																									border="0" cellspacing="0" cellpadding="0">
																									<tr>
																										<td height="20"><a href="m_sjgx.jsp"
																											target="_blank">数据更新</a>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>
																						<tr>
																							<td height="23"><div align="center">
																									<img src="../images/left.gif" width="10"
																										height="10" />
																								</div>
																							</td>
																							<td height="23"><table width="95%"
																									border="0" cellspacing="0" cellpadding="0">
																									<tr>
																										<td height="20">网站维护</span>
																										</td>
																									</tr>
																								</table>
																							</td>
																						</tr>
																					</table></td>
																			</tr>
																			<tr>
																				<td height="5"><img src="../images/main_52.gif"
																					width="151" height="5" />
																				</td>
																			</tr>
																		</table>
																	</div>
																</td>
															</tr>
														</table></td>
												</tr>
											</table></td>
									</tr>
								</table></td>
						</tr>
					</table></td>
				<td width="10%">&nbsp;</td>
				<td width="100%" align="center" valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>&nbsp;</td>
						</tr>

						<form action="update_handler.jsp" method="post">
							<input type="hidden" name="id" value="<%=news.getId()%>"
								readonly="readonly"> <input type="hidden" name="webweight"
								value="<%=news.getWebWeight()%>"> <input
								type="hidden" name="charset" value="<%=news.getCharset()%>">
								 <input
								type="hidden" name="totalvisits" value="<%=news.getTotalVisits()%>">
								<input
								type="hidden" name="moduleId" value="<%=news.getModule()%>">
								<input
								type="hidden" name="basin" value="<%=news.getBasin()%>">
								<input
								type="hidden" name="company" value="<%=news.getCompany()%>">
								<input
								type="hidden" name="oil" value="<%=news.getOil()%>">
								<input
								type="hidden" name="output" value="<%=news.getOutput()%>">
								<input
								type="hidden" name="siteSource" value="<%=news.getSiteSource()%>">
							<tr>
								<td>标题：</td>
								<td><input type="text" style="width:300px; height:20px"
									name="title" value="<%=news.getTitle()%>">
								</td>
							</tr>
							<tr>
								<td>时间：</td>
								<td><input type="text" name="time"
									value="<%=news.getPubTime()%>">
								</td>
							</tr>
							<tr>
								<td>来源：</td>
								<td><input type="text" name="source"
									value="<%=news.getOriginSource()%>">
								</td>
							</tr>
							<tr>
								<td>类别：</td>
								<td><select id="class2" name="newmoduleId">
										<option value="<%=news.getModule()%>"><%=moduleName%></option>
										<option value="5">国际合作</option>
										<option value="1">工作动态</option>
										<option value="2">矿产管理</option>
										<option value="6">科技进展</option>
										<option value="10">勘探开发</option>
										<option value="8">领导动态</option>
										<option value="9">统计数据</option>
										<option value="11">油价信息</option>
										<option value="4">一周热点</option>
										<option value="3">政策法规</option>
										<option value="13">动态参考</option>
										<option value="12">动态热点</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>区域：</td>
								<td><select id="zone" name="oilField">
										<option value="<%=news.getOilField()%>"><%=news.getOilField()%></option>
										<option value="中石油">中石油</option>
										<option value="中海油">中海油</option>
										<option value="中石化">中石化</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>总权重：</td>
								<td><select id="weight" name="totalweight">
										<option value="<%=news.getTotalWeight()%>"><%=news.getTotalWeight()%></option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>常规/非常规：</td>
								<td><select id="normal" name="type">
										<option value="<%=news.getType()%>"><%=normal%></option>
										<option value="0">常规</option>
										<option value="1">非常规</option>
								</select>
								</td>
							</tr>
							<tr>
								<td valign="top">内容：</td>
								<td><textarea name="content"
										style="width:1000px; height:500px;"><%=news.getContent()%></textarea>
								</td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td><input type="submit" value="保存" />
								</td>
							</tr>
						</form>

						<tr>
							<td>&nbsp;</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	<table width="100%" border="1">
		<tr>
			<td bgcolor="#4CB3D3" width="100%"
				style="text-align: center;font-size:12px;"><br>
				地址：北京市西城区羊肉胡同17号（100034）<br> 版权所有：国土资源部油气资源战略研究中心
				京ICP备12040548号<br> <br>
			</td>
		</tr>
	</table>
</body>
</html>