<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link type="text/css" rel="stylesheet" href="../css/calendar.css">
<script type="text/javascript" src="../js/calendar.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-zh.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-setup.js" charset="UTF-8"></script>
<style type="text/css">
#bg{
	background:url(../images/bg_top.png);
}
</style>
</head>
<body>
	<div id="nav" class="navbar navbar-default">
			<div id="bg" width="100%">
				<table style="text-align:center">
				<tr>
					<td width="150"><img src="../images/logo.png" width="70" height="70" /></td>
					<td width="1"><img src="../images/BoxOver_bd.png" width="1" height="40" /></td>
					<td width="500"><img src="../images/title.gif" width="450" height="40" /></td>
					<td width="350">&nbsp;</td>
				</tr>
				</table>
			</div>
			
			<div>					
				<%
					String menu=(String)request.getAttribute("menuIndex");
				 %>	
				<ul class="nav nav-pills" id="myTab"> 
					<li <%="index".equals(menu)?"class=\"active\"":""%>><a href="../index.action">首页</a></li>
					<li <%="Zcfg".equals(menu)?"class=\"active\"":""%>><a href="getData?moduleId=3">政策法规</a></li>
					<li <%="Kcgl".equals(menu)?"class=\"active\"":""%>><a href="getData?moduleId=2">油气管理</a> </li>
					<li <%="Ktkf".equals(menu)?"class=\"active\"":""%>><a href="getData?moduleId=10">勘探开发</a> </li>
					<!--<li <%="Yqbg".equals(menu)?"class=\"active\"":""%>><a href="#">舆情报告</a></li> href="commonUser/getData?moduleId=14">舆情报告</a></li> -->
					<li <%="Kjjz".equals(menu)?"class=\"active\"":""%>><a href="getData?moduleId=6">科技进展</a> </li>
					<li <%="Gjhz".equals(menu)?"class=\"active\"":""%>><a href="getData?moduleId=5">对外合作</a> </li>
					<li <%="Qt".equals(menu)?"class=\"active\"":""%>><a href="getData?moduleId=15">其它</a></li><!-- href="commonUser/getData?moduleId=15">其它</a></li> -->
					<li><a href="../manager/manageLogin.jsp" target="_blank">管理员登录</a> </li>
				</ul>
				
				<div id="search" class="row">
				<form class="form-inline" method="post" action="search" target="_blank">
					<div class="col-md-12" style="text-align:center;font-size:12px;">
					<marquee onmousemove="this.stop()" onmouseout="this.start()" direction="left" scrollamount="5" behavior="scroll" hidden="true"></marquee>
					&nbsp;<lable for="startTime">起始日期：</lable>
					<input name="startTime" id="startTime" type="text" onclick="return showCalendar('startTime', 'y-mm-dd');" style="width: 80px" />&nbsp;
					<lable  for="endTime">终止日期：</lable>
					<input name="endTime" id="endTime" type="text" onclick="return showCalendar('endTime', 'y-mm-dd');" style="width: 80px" />&nbsp;
					<lable for="area">区域：</lable>
					<select name="area" id="area" style="height:26px">
						<option value="">--请选择--</option>
						<option value="渤海湾">渤海湾盆地</option>
						<option value="塔里木">塔里木盆地</option>
						<option value="松辽">松辽盆地</option>
						<option value="鄂尔多斯">鄂尔多斯盆地</option>
						<option value="准噶尔">准噶尔盆地</option>
						<option value="珠江口">珠江口盆地</option>
						<option value="北部湾">北部湾盆地</option>
						<option value="海拉尔">海拉尔盆地</option>
						<option value="江汉">江汉盆地</option>
						<option value="四川">四川盆地</option>
						<option value="柴达木">柴达木盆地</option>
						<option value="二连">二连盆地</option>
						<option value="吐哈">吐哈盆地</option>
						<option value="苏北">苏北盆地</option>
						<option value="南襄">南襄盆地</option>
						<option value="沁水">沁水盆地</option>
						<option value="三水">三水盆地</option>
						<option value="兰坪思茅">兰坪思茅盆地</option>
						<option value="百色">百色盆地</option>
						<option value="琼东南">琼东南盆地</option>
						<option value="焉耆">焉耆盆地</option>
						<option value="莺歌海">莺歌海盆地</option>
					</select>				
					<select name="module" id="module" style="height:26px">
						<option value= "-1">--全站--</option>
						<option value="1">工作动态</option>
						<option value="2">油气管理</option>
						<option value="3">政策法规</option>
						<option value="4">一周热点</option>
						<option value="5">对外合作</option>
						<option value="6">科技进展</option>
						<option value="7">图片新闻</option>
						<option value="8">领导动态</option>
						<option value="9">统计数据</option>
						<option value="10">勘探开发</option>				
					</select>
					<select name="sel" id="sel" style="height:26px">
						<option value="0">标题检索</option>
						<option value="1">全文检索</option>										
					</select>
					<label for="textfield"></label>
					<input name="textfield" id="textfield" type="text" placeholder="Search"/>
					<input type="submit" name="button" id="button" value="搜索" onclick="return researchWarn()" />
					</div>
				</form>
				</div>
					  	
			</div>
		</div>
<script type="text/javascript" charset="UTF-8">
	function researchWarn() {
		var enter = document.getElementById("textfield");
		var turn = document.getElementById("turn");
		if (enter.value == "") {
			alert("请输入有效的关键字");
		
			return false;
		} else {
			return true;
		}
		}

</script>
</body>
</html>