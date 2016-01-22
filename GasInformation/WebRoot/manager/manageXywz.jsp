<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.model.SelectModel,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/wz.js" charset="UTF-8"></script>
<%
    SelectModel selectmodel = new SelectModel();
    if((SelectModel)request.getAttribute("SelectModel")!=null){
    selectmodel = (SelectModel)request.getAttribute("SelectModel");
    }
%>

<title>油气网络信息动态采集系统</title>
<style>
#ModuleA_MK {
	height: 23px;
	padding: 1px;
}
</style>
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
					request.setAttribute("manageLeftNav2", "Xywz");
				%>
				<jsp:include page="leftMenu2.jsp"></jsp:include>
			</div>
			<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
				<form id="Xywz" name="Xywz" class="form-inline" method="post"
					action="SelectModelParaSet2" target="_blank">
					<table class="table table-bordered">
						<tr>
							<td>
								<table border="0" align="center">
									<tr>
										<td>&nbsp;</td>
									</tr>
									
									<tr>
										<td>&nbsp;</td>
										<td colspan="2" width="200px">网站名 
											<select id="WebName" name="WebID" onchange="padding()">
												<% 
												String WebName="---请选择网站名---";
												if (selectmodel.getWebName()!=""){
												WebName=selectmodel.getWebName();
												session.setAttribute("Webname", WebName);
												}else {
													selectmodel.setWebName("---请选择网站名---");
												}				
												%>
										
												<option selected name="WebID" value=<%=selectmodel.getWebID() %>><%=WebName%></option>
												<% 
													ArrayList<HashMap<String, String>> listAllWebs = (ArrayList<HashMap<String, String>>)request.getAttribute("listAllWebs");
													session.setAttribute("listAllWebs", listAllWebs);
													for(int i=0;i<listAllWebs.size();i++){ 
												%>
												 <option name="WebName"
													value="<%=listAllWebs.get(i).get("ID")%>"><%=listAllWebs.get(i).get("name")%></option>
												<%
													 } 
												%> 
										</select></td>									
										<td>网址
											<input id="seed" name="seed"  style="width:150px" type="text" value="<%=selectmodel.getSeed()%>"/></td>
										<td id="OriginPath">&nbsp;&nbsp;&nbsp;&nbsp;原始路径
											<input id="PrePostion" name="PrePostion" type="text" style="width:200px" value="<%=selectmodel.getPrePostion()%>"/>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;网站权重
											<select name="webWeight" >	
												<option value="1"<%="1".equals(selectmodel.getWebWeight())?"selected":""%>>1</option>
												<option value="2"<%="2".equals(selectmodel.getWebWeight())?"selected":""%>>2</option>
												<option value="3"<%="3".equals(selectmodel.getWebWeight())?"selected":""%>>3</option>
												<option value="4"<%="4".equals(selectmodel.getWebWeight())?"selected":""%>>4</option>
												<option value="5"<%="5".equals(selectmodel.getWebWeight())?"selected":""%>>5</option>
												<option value="6"<%="6".equals(selectmodel.getWebWeight())?"selected":""%>>6</option>
												<option value="7"<%="7".equals(selectmodel.getWebWeight())?"selected":""%>>7</option>
												<option value="8"<%="8".equals(selectmodel.getWebWeight())?"selected":""%>>8</option>
												<option value="9"<%="9".equals(selectmodel.getWebWeight())?"selected":""%>>9</option>
												<option value="10"<%="10".equals(selectmodel.getWebWeight())?"selected":""%>>10</option>
											</select>
										</td>		
									</tr>

									<tr><td>&nbsp;</td></tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>模块</td>
									</tr>
									
									<tr>
										<% String vol1=selectmodel.getModuleID();
											String MKA=selectmodel.getModuleA_MK();
										%>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
					
										<td><label><input type="radio" id="ModuleIDA" name="ModuleID" <%="00".equals(vol1)?"checked":""%>
												value="00" onclick="checkModule1()">一对一</label></td>
										<td><select id="ModuleA_MK" name="ModuleA_MK" <%="00".equals(vol1)?"":"disabled"%>>												
												<option value="1" <%="Gzdt".equals(MKA)?"selected":""%>>工作动态</option>
												<option value="2" <%="Kcgl".equals(MKA)?"selected":""%>>油气管理</option>
												<option value="3" <%="Zcfg".equals(MKA)?"selected":""%>>政策法规</option>
												<option value="4" <%="Yzrd".equals(MKA)?"selected":""%>>一周热点</option>
												<option value="5" <%="Gjhz".equals(MKA)?"selected":""%>>对外合作</option>
												<option value="6" <%="Kjjz".equals(MKA)?"selected":""%>>科技进展</option>
												<option value="7" <%="Tpxw".equals(MKA)?"selected":""%>>图片新闻</option>
												<option value="8" <%="Lddt".equals(MKA)?"selected":""%>>领导动态</option>
												<option value="9" <%="Tjsj".equals(MKA)?"selected":""%>>统计数据</option>
												<option value="10" <%="Ktkf".equals(MKA)?"selected":""%>>勘探开发</option>
												<option value="11" <%="Yjxx".equals(MKA)?"selected":""%>>油价信息</option>
										</select>
										</td>
									</tr>
									
									<tr>		
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="ModuleIDB" name="ModuleID"  <%="01".equals(vol1)?"checked":""%>
												value="01" onclick="checkModule2()">一对多</label>
										</td>
										<td>class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="ModuleB_MK"
											name="ModuleB_ClassName" value="<%=selectmodel.getModuleB_ClassName()%>" <%="01".equals(vol1)?"":"disabled"%>></td>
										<td colspan="2">
											<input type="button" id="btnAdd" <%="01".equals(vol1)?"":"disabled"%> onclick="add()" value="添加">
											<input type="button" id="btnDel" <%="01".equals(vol1)?"":"disabled"%> onclick="del()" value="删除">
										</td>
									</tr>
									<tr id="addTd">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan="2">
											<table id="tab">
												<%
												 	ArrayList<HashMap<String, String>> CNB=(ArrayList<HashMap<String, String>>)selectmodel.getModuleB_String();
													String PositionB="";
													String MKB="";
													for(int b=0;b<CNB.size();b++){
														PositionB=CNB.get(b).get("Postion");
														MKB=CNB.get(b).get("MK"); 
												%>
												<tr>	
													<td>
														<lable><input type="checkbox" name="ModuleCheckB"/></label>
														<input type="text" name=ModuleB_Postion value="<%=PositionB%>">至</td>
													<td><select id="ModuleB_MK" name="ModuleB_MK">
															<option value="1" <%="Gzdt".equals(MKB)?"selected":""%>>工作动态</option>
															<option value="2" <%="Kcgl".equals(MKB)?"selected":""%>>矿场管理</option>
															<option value="3" <%="Zcfg".equals(MKB)?"selected":""%>>政策法规</option>
															<option value="4" <%="Yzrd".equals(MKB)?"selected":""%>>一周热点</option>
															<option value="5" <%="Gjhz".equals(MKB)?"selected":""%>>对外合作</option>
															<option value="6" <%="Kjjz".equals(MKB)?"selected":""%>>科技进展</option>
															<option value="7" <%="Tpxw".equals(MKB)?"selected":""%>>图片新闻</option>
															<option value="8" <%="Lddt".equals(MKB)?"selected":""%>>领导动态</option>
															<option value="9" <%="Tjsj".equals(MKB)?"selected":""%>>统计数据</option>
															<option value=10 <%="Ktkf".equals(MKB)?"selected":""%>>勘探开发</option>
															<option value="11" <%="Yjxx".equals(MKB)?"selected":""%>>油价信息</option>
													</select></td>																																								
												</tr>
												<%}%>	
											</table>
										</td>		
									</tr>
									
									<tr>
										<% String VarC=selectmodel.getModuleC_Var();
											String ValueC=selectmodel.getModuleC_Value();
										%>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="ModuleIDC" name="ModuleID"  <%="02".equals(vol1)?"checked":""%>
												value="02" onclick="checkModule3()">一对多</label></td>
										<td>变量名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="ModuleC1_MK"
											name="ModuleC_Var" value="<%=VarC%>" <%="02".equals(vol1)?"":"disabled"%>>
										</td>
										<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;变量值<input type="text" id="ModuleC2_MK" name="ModuleC_Value"
											value="<%=ValueC%>" <%="02".equals(vol1)?"":"disabled"%>>										
											<input type="button" id="btnAdd1" <%="02".equals(vol1)?"":"disabled"%> onclick="add1()" value="添加">
											<input type="button" id="btnDel1" <%="02".equals(vol1)?"":"disabled"%> onclick="del1()" value="删除">
										</td>
									</tr>
									
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan="2">
											<table id="tab1">
												<%
												 	ArrayList<HashMap<String, String>> CNC=(ArrayList<HashMap<String, String>>)selectmodel.getModuleC_String();
													String PostionC="";
													String MKC="";
													for(int c=0;c<CNC.size();c++){
														PostionC=CNC.get(c).get("Postion");
														MKC=CNC.get(c).get("MK");
												%>
												<tr>
													<td>
														<lable><input type="checkbox" name="ModuleCheckC"/></label>
														<input type="text" name="ModuleC_Postion" value="<%=PostionC%>">至</td>
													<td><select id="ModuleC_MK" name="ModuleC_MK">
															<option value="1" <%="Gzdt".equals(MKC)?"selected":""%>>工作动态</option>
															<option value="2" <%="Kcgl".equals(MKC)?"selected":""%>>矿场管理</option>
															<option value="3" <%="Zcfg".equals(MKC)?"selected":""%>>政策法规</option>
															<option value="4" <%="Yzrd".equals(MKC)?"selected":""%>>一周热点</option>
															<option value="5" <%="Gjhz".equals(MKC)?"selected":""%>>对外合作</option>
															<option value="6" <%="Kjjz".equals(MKC)?"selected":""%>>科技进展</option>
															<option value="7" <%="Tpxw".equals(MKC)?"selected":""%>>图片新闻</option>
															<option value="8" <%="Lddt".equals(MKC)?"selected":""%>>领导动态</option>
															<option value="9" <%="Tjsj".equals(MKC)?"selected":""%>>统计数据</option>
															<option value="10" <%="Ktkf".equals(MKC)?"selected":""%>>勘探开发</option>
															<option value="11" <%="Yjxx".equals(MKC)?"selected":""%>>油价信息</option>
													</select></td>
													<%}%>
												</tr>
											</table></td>
									</tr>
									
									<tr><td>&nbsp;</td></tr>
									
									<tr>
										<% String title=selectmodel.getSelectTitleID();%>
										<td>&nbsp;</td>
										<td>标题</td>
										<td><select id="SelectTitleID" name="SelectTitleID">
												<option value="00" <%="00".equals(title)?"selected":""%>>标题</option>
												<option value="01" <%="01".equals(title)?"selected":""%>>来源---标题</option>
												<option value="02" <%="02".equals(title)?"selected":""%>>标题---来源</option>
										</select>
										</td>
									</tr>
									
									<tr><td>&nbsp;</td></tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>来源</td>
									</tr>
									
									<tr>
										<% String vol2=selectmodel.getSelectSourceID();%>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectSourceIDA"
												name="SelectSourceID" value="00" <%="00".equals(vol2)?"checked":""%> onclick="checkSource1()">在含有特定class属性值的标签中</label>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
											type="text" id="SelectSourceA_ClassName" name="SelectSourceA_ClassName" <%="00".equals(vol2)?"":"disabled"%> value="<%=selectmodel.getSelectSourceA_ClassName()%>">
										</td>
									</tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectSourceIDB"
												name="SelectSourceID" value="01" <%="01".equals(vol2)?"checked":""%> onclick="checkSource2()">在script标签值内的特定的变量中</label>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectSourceB_TagName" name="SelectSourceB_TagName" <%="01".equals(vol2)?"":"disabled"%>
											value="<%=selectmodel.getSelectSourceB_TagName()%>"></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;变量名<input type="text" id="SelectSourceB_Var" name="SelectSourceB_Var" <%="01".equals(vol2)?"":"disabled"%>
											value="<%=selectmodel.getSelectSourceB_Var()%>"></td>
									</tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectSourceIDC"
												name="SelectSourceID" value="02" <%="02".equals(vol2)?"checked":""%> onclick="checkSource3()">在特定标签的特定属性中</label>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectSourceC_Var"
											name="SelectSourceC_Var" <%="02".equals(vol2)?"":"disabled"%> value="<%=selectmodel.getSelectSourceC_Var()%>"></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;属性名<input type="text" <%="02".equals(vol2)?"":"disabled"%> id="SelectSourceC_Value" name="SelectSourceC_Value"
											value="<%=selectmodel.getSelectSourceC_Value()%>"></td>

									</tr>
									
									<tr><td>&nbsp;</td></tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>时间</td>
									</tr>
									
									<tr>
										<% String vol3=selectmodel.getSelectTimeID();%>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectTimeIDA"
												name="SelectTimeID" value="00" <%="00".equals(vol3)?"checked":""%> onclick="checkTime1()">在含有特定class属性值的标签中</label>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
											type="text" id="SelectTimeA_ClassName" name="SelectTimeA_ClassName" <%="00".equals(vol3)?"":"disabled"%> value="<%=selectmodel.getSelectTimeA_ClassName()%>"></td>
									</tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectTimeIDB"
												name="SelectTimeID" value="01" <%="01".equals(vol3)?"checked":""%> onclick="checkTime2()">在script标签的值中</label>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectTimeB_TagName" name="SelectTimeB_TagName"
											<%="01".equals(vol3)?"":"disabled"%> value="<%=selectmodel.getSelectTimeB_TagName()%>"></td>
									</tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectTimeIDC"
												name="SelectTimeID" value="02" <%="02".equals(vol3)?"checked":""%> onclick="checkTime3()">特定标签的特定属性中</label>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectTimeC_Var"
											name="SelectTimeC_Var" <%="02".equals(vol3)?"":"disabled"%> value="<%=selectmodel.getSelectTimeC_Var()%>"></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;属性名<input type="text" id="SelectTimeC_Value" name="SelectTimeC_Value"
											<%="02".equals(vol3)?"":"disabled"%> value="<%=selectmodel.getSelectTimeC_Value()%>"></td>
									</tr>
									
									<tr><td>&nbsp;</td></tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>正文</td>
									</tr>
									
									<tr>
										<% String vol4=selectmodel.getSelectContentID();%>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectContentIDA"
												name="SelectContentID" value="00" <%="00".equals(vol4)?"checked":""%> onclick="checkContent1()">在含有特定class属性值的标签中</label>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
											type="text" id="SelectContentA_ClassName" name="SelectContentA_ClassName" <%="00".equals(vol4)?"":"disabled"%> value="<%=selectmodel.getSelectContentA_ClassName()%>">
										</td>
									</tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectContentIDB"
												name="SelectContentID" value="01" <%="01".equals(vol4)?"checked":""%> onclick="checkContent2()">在某个特定名称标签中</label>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectContentB_TagName" name="SelectContentB_TagName"
											<%="01".equals(vol4)?"":"disabled"%> value="<%=selectmodel.getSelectContentB_TagName()%>"></td>
									</tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectContentIDC"
												name="SelectContentID" value="02" <%="02".equals(vol4)?"checked":""%> onclick="checkContent3()">特定标签的特定属性中</label>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectContentC_Var"
											name="SelectContentC_Var" <%="02".equals(vol4)?"":"disabled"%> value="<%=selectmodel.getSelectContentC_Var()%>"></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;属性名<input type="text" id="SelectContentC_Value" name="SelectContentC_Value"
											<%="02".equals(vol4)?"":"disabled"%> value="<%=selectmodel.getSelectContentC_Value()%>"></td>
									</tr>
									
									<tr>

										<td>&nbsp;</td>
										<td><input type="submit" name="button" id="button"
											value="确定" />
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
<script language="javascript">
	function padding() {
		var tmp = document.createElement("form");
		var WebID = document.getElementById("WebName").value;
		var action = "manageXywzMaintainModule?WebID=" + WebID;
		tmp.action = action;
		tmp.method = "post";
		document.body.appendChild(tmp);
		tmp.submit();
		return tmp;
	}
</script>
</html>