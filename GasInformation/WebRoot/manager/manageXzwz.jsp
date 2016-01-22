<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/wz.js" charset="UTF-8"></script>
<title>油气网络信息动态采集系统</title>
<style>
	#ModuleA_MK{
		height:23px;
		padding:1px;
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
			<%request.setAttribute("manageLeftNav2", "Xzwz");%>
				<jsp:include page="leftMenu2.jsp"></jsp:include>
			</div>
			<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">		
			<form action="upload" method="post" enctype="multipart/form-data">
			<table>	
				<tr>										
					<td height="20px"><input id="load" type="file" name="upload"/></td>
					<td><input type="submit" value="导入" onclick="return checkLoad()" style="height:25px;width:60px;margin:0;padding:0"/></td>
				</tr>
			</table>
			</form>			
				<form class="form-inline" method="post" action="SelectModelParaSet"
					target="_blank">
						<table class="table table-bordered">
						<tr><td>
 								<table border="0" align="center">
									<tr>
										<td>&nbsp;</td>	
									</tr>
									<tr>
										
										<td>&nbsp;</td>
										<td colspan="2" width="200px">网站名
											<input name="WebName" type="text" placeholder="如：中国石油新闻网"/>
										</td>
										<td>网址
											<input name="seed" type="text" style="width:150px" placeholder="http://news.cnpc.com.cn"/>
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;原始路径
											<input name="PrePostion" type="text" style="width:200px" placeholder="mirror\news.cnpc.com.cn\system"/>
										</td>						
										<td>&nbsp;&nbsp;&nbsp;&nbsp;网站权重
											<select name="webWeight">	
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5" selected>5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
												<option value="10">10</option>
											</select>
										</td>																
									</tr>
									
									<tr><td>&nbsp;</td></tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>模块</td>
									</tr>
									
									<tr>

										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="ModuleIDA" name="ModuleID"
												value="00" onclick="checkModule1()">一对一</label>
										</td>
										<td><select id="ModuleA_MK" name="ModuleA_MK" disabled="disabled">
												<option value="1" selected>工作动态</option>
												<option value="2">油气管理</option>
												<option value="3">政策法规</option>
												<option value="4">一周热点</option>
												<option value="5">对外合作</option>
												<option value="6">科技进展</option>
												<option value="7">图片新闻</option>
												<option value="8">领导动态</option>
												<option value="9">统计数据</option>
												<option value="10">勘探开发</option>
												<option value="11">油价信息</option>
										</select></td>
										<td>&nbsp;</td>
									</tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>&nbsp;</td>				
										<td><label><input type="radio" id="ModuleIDB" name="ModuleID" value="01" onclick="checkModule2()">一对多</label></td>
										<td colspan="3">class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
											id="ModuleB_MK"  name="ModuleB_ClassName" value="" disabled="disabled" placeholder="如：lan12">
											<input type="button" id="btnAdd" onclick="add()" disabled="disabled" value="添加">
											<input type="button" id="btnDel" onclick="del()" disabled="disabled" value="删除">
										</td>						
									</tr>
									
									<tr id="addTd">
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan="2">
											<table id="tab">
													<tr>
														<td><input type="text" id="ModuleB_Postion" name="ModuleB_Postion" placeholder="如：公司新闻">至</td>
														<td><select id="ModuleB_select" name="ModuleB_MK">
																<option value="1" selected>工作动态</option>
																<option value="2">油气管理</option>
																<option value="3">政策法规</option>
																<option value="4">一周热点</option>
																<option value="5">对外合作</option>
																<option value="6">科技进展</option>
																<option value="7">图片新闻</option>
																<option value="8">领导动态</option>
																<option value="9">统计数据</option>
																<option value="10">勘探开发</option>
																<option value="11">油价信息</option>
															</select>
														</td>								
													</tr>
											</table>							
										</td>									
									</tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="ModuleIDC" name="ModuleID"
												value="02" onclick="checkModule3()">一对多</label>
										</td>
										<td>变量名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
											id="ModuleC1_MK" name="ModuleC_Var" value="" disabled="disabled" placeholder="如：name"></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;变量值<input type="text" id="ModuleC2_MK" name="ModuleC_Value" value="" disabled="disabled" placeholder="如：value">
											<input type="button" id="btnAdd1" onclick="add1()" disabled="disabled" value="添加">
											<input type="button" id="btnDel1" onclick="del1()" disabled="disabled" value="删除">										
										</td>
									</tr>
									
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan="2">
											<table id="tab1">
													<tr>
														<td><input type="text" id="ModuleC_Postion" name="ModuleC_Postion" placeholder="如：公司新闻">至</td>
														<td><select id="ModuleC_select" name="ModuleC_MK">
																<option value="1" selected>工作动态</option>
																<option value="2">油气管理</option>
																<option value="3">政策法规</option>
																<option value="4">一周热点</option>
																<option value="5">对外合作</option>
																<option value="6">科技进展</option>
																<option value="7">图片新闻</option>
																<option value="8">领导动态</option>
																<option value="9">统计数据</option>
																<option value="10">勘探开发</option>
																<option value="11">油价信息</option>
															</select>
														</td>				
													</tr>
											</table>							
										</td>
									</tr>
									
									<tr><td>&nbsp;</td></tr>
									
									
									<tr>
										
										<td>&nbsp;</td>
										<td>标题</td>
										<td><select id="SelectTitleID" name="SelectTitleID">
												<option value="00" selected>标题</option>
												<option value="01">来源---标题</option>
												<option value="02">标题---来源</option>
										</select></td>
									</tr>
									
									<tr><td>&nbsp;</td></tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>来源</td>
									</tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectSourceIDA"
												name="SelectSourceID" value="00" onclick="checkSource1()">在含有特定class属性值的标签中</label></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectSourceA_ClassName"
											name="SelectSourceA_ClassName" value="" disabled="false"  placeholder="如：place-date">
										</td>
									</tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectSourceIDB"
												name="SelectSourceID" value="01" onclick="checkSource2()">在script标签值内的特定的变量中</label></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectSourceB_TagName" name="SelectSourceB_TagName"
											value="" disabled="false" placeholder="如：script">
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;变量名<input type="text" id="SelectSourceB_Var"
											name="SelectSourceB_Var" value="" disabled="false" placeholder="如：source">
										</td>
									</tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectSourceIDC"
												name="SelectSourceID" value="02" onclick="checkSource3()">在特定标签的特定属性中</label></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectSourceC_Var"
											name="SelectSourceC_Var" value="" disabled="false" placeholder="如：name">
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;属性名<input type="text" id="SelectSourceC_Value" name="SelectSourceC_Value"
											value="" disabled="false" placeholder="如：value">
										</td>
									</tr>
									
									<tr><td>&nbsp;</td></tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>时间</td>
									</tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectTimeIDA"
												name="SelectTimeID" value="00" onclick="checkTime1()">在含有特定class属性值的标签中</label></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectTimeA_ClassName"
											name="SelectTimeA_ClassName" value="" disabled="false" placeholder="如：lan41">
										</td>
									</tr>
									
									<tr>									
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectTimeIDB"
												name="SelectTimeID" value="01" onclick="checkTime2()">在script标签的值中</label></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectTimeB_TagName" name="SelectTimeB_TagName"
											value="" disabled="false" placeholder="如：pubDate">
										</td>
									</tr>
									
									<tr>										
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectTimeIDC"
												name="SelectTimeID" value="02" onclick="checkTime3()">特定标签的特定属性中</label></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectTimeC_Var"
											name="SelectTimeC_Var" value="" disabled="false" placeholder="如：name">
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;属性名<input type="text" id="SelectTimeC_Value" name="SelectTimeC_Value"
											value="" disabled="false" placeholder="如：value">
										</td>
									</tr>
									
									<tr><td>&nbsp;</td></tr>
									
									<tr>		
										<td>&nbsp;</td>
										<td>正文</td>
									</tr>
									
									<tr>			
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectContentIDA"
												name="SelectContentID" value="00" onclick="checkContent1()">在含有特定class属性值的标签中</label></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;class&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectContentA_ClassName"
											name="SelectContentA_ClassName" value="" disabled="false" placeholder="如：land41">
										</td>
									</tr>
									
									<tr>			
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectContentIDB"
												name="SelectContentID" value="01" onclick="checkContent2()">在某个特定名称标签中</label></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectContentB_TagName" name="SelectContentB_TagName"
											value="" disabled="false" placeholder="如：con">
										</td>
									</tr>
									
									<tr>						
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td><label><input type="radio" id="SelectContentIDC"
												name="SelectContentID" value="02" onclick="checkContent3()">特定标签的特定属性中</label></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;标签名&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="SelectContentC_Var"
											name="SelectContentC_Var" value="" disabled="false" placeholder="如：name">
										</td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;属性名<input type="text" id="SelectContentC_Value" name="SelectContentC_Value"
											value="" disabled="false" placeholder="如：value">
										</td>
									</tr>
									
									<tr>
										
										<td>&nbsp;</td>
										<td>
										<input type="submit" name="button" id="button" value="确定"/>
									</tr>
					</table></td></tr></table>
				</form>
				
			</div>
		</div>
	</div>
	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
<script language="javascript">
function checkLoad(){
	var load=document.getElementById("load").value;
	if(load==null||load==""){
		alert("请输入路径");
		return false;
	}
	return true;
}
/*function addinput(){
 	var _tr;
 	var _td;
	var _input;  
  
	for(var i = 0; i < 2; i++) {
	    _td = document.createElement("td");  
	    document.getElementById("addTd").appendChild(_td);  	     
	    _input = document.createElement("input"); 
	    _input.setAttribute("name", "myModule"+i);
	    document.getElementById("addTd").appendChild(_input);   
	}
}*/
</script>
</html>