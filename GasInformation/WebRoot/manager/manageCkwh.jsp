<%@page
	import="java.util.ArrayList,com.gasinfo.action.dealWordsAction,com.gasinfo.server.indexdatebase.Words"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<String> listWords = (ArrayList<String>) request
			.getAttribute("listWords");
	String WordsType = "";
	WordsType = (String) request.getAttribute("WordsType");
	// System.out.println(WordsType[0]);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>油气网络信息动态采集系统</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="../js/manage.js" charset="UTF-8"></script>
</head>
<body>
	<div id="top">
		<%
			request.setAttribute("manageNav", "Xtgl");
		%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
				<%
					request.setAttribute("manageLeftNav2", "Ckwh");
				%>
				<jsp:include page="leftMenu2.jsp"></jsp:include>
			</div>

			<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
				<div id="listContent">
					<form action="getWords" id="getWords" method="post">
						<table class="table table-condensed table-bordered">
							<thead>

								<%
									/* String WordsType="(String)request.getAttribute("WordsType")";
									if((String)request.getAttribute("WordsType")==null){
										WordsType = "Oil"; 
									}else {
										WordsType = (String)request.getAttribute("WordsType");
									} */

									String TypeName = "请选择";
									String TypeName2 = "请选择";
									if (WordsType.length()!=0){
									TypeName2 =WordsType;
									}
									if (WordsType != null) {
										if (WordsType.equals("Oil")) {
											TypeName = "油田";
											TypeName2 = "请选择";
										} else if (WordsType.equals("Company")) {
											TypeName = "公司";
										} else if (WordsType.equals("Basin")) {
											TypeName = "盆地";
										} else if (WordsType.equals("KeyWords")) {
											TypeName = "其它";
										} else if (WordsType.equals("Blacklist")) {
											TypeName = "黑名单";
										}
									} else {
										TypeName = "请选择";
									}
									/* session.setAttribute("WordsType", WordsType); */
								%>

								<td colspan="5"><label for="WordsType">词库类别：</label>
								 <select id="WordsType" name="WordsType1" onchange="return scan()">
										<option value="<%=WordsType%>" selected><%=TypeName%></option>
										<option value="Oil">油田</option>
										<option value="Company">公司</option>
										<option value="Basin">盆地</option>
										<option value="KeyWords">其它</option>
										<option value="Blacklist">黑名单</option>

								</select>
									<form action="getWebKeyWords" id="getWebKeyWords" method="post">
										<label for="WordsType2">网站关键词：</label> 
										<select id="WordsType2" name="WordsType2" onchange="return scan2()">
											<option value="<%=WordsType%>"selected><%=TypeName2%></option>
											<option value="中国能源网">中国能源网</option>
											<option value="国家能源网">国家能源网</option>
											<option value="中国石油新闻网">中国石油新闻网</option>
											<option value="中国石化新闻网">中国石化新闻网</option>
											<option value="中国海油新闻网">中国海油新闻网</option>
											<option value="中国国家统计局">中国国家统计局</option>
											<option value="中国国家财政部">中国国家财政部</option>
											<option value="中国国家发改委">中国国家发改委</option>
											<option value="中国国土资源部">中国国土资源部</option>
											<option value="国家石油和化工网">国家石油和化工网</option>
											<option value="中国海关总署">中国海关总署</option>
											<option value="中国环保部">中国环保部</option>
											<option value="中国商务部">中国商务部</option>
											<option value="塔里木油田">塔里木油田</option>
											<option value="非常规油气网">非常规油气网</option>
											<option value="中国石油海外勘探开发公司">中国石油海外勘探开发公司</option>
										</select>
									</form> <input type="button" value="更新" onclick="return updateWord()" />
								</td>
							</thead>
							<tr>
								<td><table id="words"
										class="table table-condensed table-bordered">
										<tbody id="wordsBody">
											<tr>
												<%
													if (listWords != null) {
														session.setAttribute("listWords", listWords);
														for (int i = 0; i < listWords.size(); i++) {
												%>
												<td><input id="WordsName" name="WordsName" type="radio"
													value="<%=listWords.get(i)%>" /> <%=listWords.get(i)%></td>

												<%
													if ((i + 1) % 5 == 0) {
																out.print("</tr><tr>");
															} else {
															}
														}
													}
												%>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="5"><input type="button" id="btnAddW"
									value="增加" onclick="return addWords()" /> <input type="button"
									id="btnRemoeW" value="删除" onclick="return removeWords()" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
</html>