<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="link.css">
<title>油气网络信息动态采集系统</title>
</head>
<body>
	<div id="top">
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
	
<div class="container-fluid">
	<div id="content">
		<div class="row">
			<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
				<%request.setAttribute("manageLeftNav", "Gzdt");%>
				<jsp:include page="leftMenu.jsp"></jsp:include>
			</div>
		
			<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
				
					<ol class="breadcrumb">
						<li><a href="#">业务中心</a></li>
						<li><a href="#" class="active">工作动态</a>
						</li>
						<a style="text-align:right" role="button" onclick="show()" class="btn btn-info">更新</a>										
					</ol>
				<div class="table-responsive">
					<table>
						<tr>
							<td>&nbsp;</td>
							<td>网站特征：</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td colspan="2">网站名<input type="text"/></td>
							<td>网址<input type="text" /></td>									
							<td>保存路径<input type="text" /></td>
							<td>所属模块
								<select id="module" name="module">						
									<option value="1" selected>工作动态</option>
									<option value="2">矿场管理</option>
									<option value="3">政策法规</option>
									<option value="4">一周热点</option>
									<option value="5">国际合作</option>
									<option value="6">科技进展</option>
									<option value="7">图片新闻</option>
									<option value="8">领导动态</option>
									<option value="9">统计数据</option>
									<option value="10">勘探开发</option>
									<option value="11">油价信息</option>								
								</select>
							</td>
							<td>&nbsp;</td>												
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>标题</td>
							<td><select id="title" name="title">						
									<option value="0" selected>纯标题</option>
									<option value="1">来源---标题</option>
									<option value="2">标题---来源</option>								
								</select>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>来源</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><label><input type="radio" name="radio1" value="0">在class标签中</label> </td>
							<td>class&nbsp;&nbsp;&nbsp;<input type="text" value=""></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><label><input type="radio" name="radio1" value="1">在特殊标签中</label> </td>
							<td>特殊标签<input type="text" value=""></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>										
							<td><label><input type="radio" name="radio1" value="2">在变量值标签中</label> </td>
							<td>变量名&nbsp;&nbsp;<input type="text" value=""></td>
							<td>变量值<input type="text" value=""></td>
							<td>&nbsp;</td>
						</tr>			
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>时间</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>	
							<td><label><input type="radio" name="radio2" value="0">在class标签中</label> </td>
							<td>class&nbsp;&nbsp;&nbsp;<input type="text" value=""></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><label><input type="radio" name="radio2" value="1">在特殊标签中</label> </td>
							<td>特殊标签<input type="text" value=""></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>									
							<td><label><input type="radio" name="radio2" value="2">在变量值标签中</label> </td>
							<td>变量名&nbsp;&nbsp;<input type="text" value=""></td>
							<td>变量值<input type="text" value=""></td>
						</tr>						
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>正文</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>	
							<td><label><input type="radio" name="radio3" value="0">在class标签中</label> </td>
							<td>class&nbsp;&nbsp;&nbsp;<input type="text" value=""></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><label><input type="radio" name="radio3" value="1">在特殊标签中</label> </td>
							<td>特殊标签<input type="text" value=""></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>									
							<td><label><input type="radio" name="radio3" value="2">在变量值标签中</label> </td>
							<td>变量名&nbsp;&nbsp;<input type="text" value=""></td>
							<td>变量值<input type="text" value=""></td>
						</tr>	
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><input type="button" value="确定"></td>
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