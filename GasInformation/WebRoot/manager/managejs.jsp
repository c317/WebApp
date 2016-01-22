<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../css/bootstrap.min.css" rel ="stylesheet">
<link type="text/css" rel="stylesheet" href="../css/calendar.css">
<script type="text/javascript" src="../js/calendar.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-zh.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-setup.js" charset="UTF-8"></script>
</head>
<body>
<div id="research" style="font-size:12px;">
	<table class="table">
	<tr>
		<td>						
			<lable for="startTime">起始日期：</lable>
				<input name="startTime" id="startTime" type="text"
					onclick="return showCalendar('startTime', 'y-mm-dd');"style="width: 80px" />&nbsp;
			<lable for="endTime">终止日期：</lable>
				<input name="endTime" id="endTime" type="text"
					onclick="return showCalendar('endTime', 'y-mm-dd');"style="width: 80px" />&nbsp;
			<lable>区域：</lable>
				<select name="oilfield" id="oilfield" style="height:26px">
					<option value="0" selected>--请选择--</option>
					<option value="大庆油田">大庆油田</option>  
			  		<option value="胜利油田">胜利油田</option>
					<option value="长庆油田">长庆油田</option>
					<option value="辽河油田">辽河油田</option>
					<option value="克拉玛依油田">克拉玛依油田</option>
					<option value="四川油田">四川油田</option>
					<option value="华北油田">华北油田</option>
					<option value="大港油田">大港油田</option>
					<option value="吉林油田">吉林油田</option>
					<option value="河南油田">河南油田</option>
					<option value="江汉油田">江汉油田</option>
					<option value="江苏油田">江苏油田</option>
					<option value="青海油田">青海油田</option>
					<option value="塔里木油田">塔里木油田</option>
					<option value="土哈油田">土哈油田</option>
					<option value="玉门油田">玉门油田</option>
					<option value="新疆油田">新疆油田</option>
					<option value="塔河油田">塔河油田</option>
					<option value="西南油田">西南油田</option>
					<option value="滇黔桂油田">滇黔桂油田</option>
					<option value="南海西部油气田">南海西部油气田</option>
					<option value="南海东部油气田">南海东部油气田</option>
					<option value="浙江油田">浙江油田</option>
					<option value="东海油气田">东海油气田</option>
					<option value="渤海油田">渤海油田</option>
					<option value="上海海洋油气田">上海海洋油气田</option>
					<option value="冀东油田">冀东油田</option>
					<option value="延长石油">延长石油</option>
					<option value="中原油田">中原油田</option>		
				</select>
				<select name="company" id="company" style="height:26px">
					<option value="0" selected>--请选择--</option>
					<option value="中国海油新闻网">中国海油新闻网</option>
					<option value="中国石油新闻网">中国石油新闻网</option>	
					<option value="中国石化新闻网">中国石化新闻网</option>
					<option value="中国国家统计局">中国国家统计局</option>
					<option value="中国国家财政部">中国国家财政部</option>	
					<option value="中国国家发改委">中国国家发改委</option>
					<option value="中国国土资源部">中国国土资源部</option>	
					<option value="中国海关总署">中国海关总署</option>
					<option value="中国环保部">中国环保部</option>
					<option value="中国商务部">中国商务部</option>
					<option value="塔里木油田">塔里木油田</option>	
					<option value="非常规油气网">非常规油气网</option>	
					<option value="国家能源网">国家能源网</option>	
					<option value="中国能源网">中国能源网</option>
					<option value="国家石油和化工网">国家石油和化工网</option>
					<option value="沙美石油公司">沙美石油公司</option>
					<option value="中国石油海外勘探开发公司">中国石油海外勘探开发公司</option>
				</select>
				<select name="basin" id="basin" style="height:26px">
					<option value="0" selected>--请选择--</option>
					<option value="渤海湾盆地">渤海湾盆地</option>  
				    <option value="塔里木盆地">塔里木盆地</option>  
				    <option value="松辽盆地">松辽盆地</option>  
				    <option value="鄂尔多斯盆地">鄂尔多斯盆地</option>  
				    <option value="准噶尔盆地">准噶尔盆地</option>  
				    <option value="珠江口盆地">珠江口盆地</option>  
				    <option value="北部湾盆地">北部湾盆地</option>  
				    <option value="海拉尔盆地">海拉尔盆地</option>  
				    <option value="江汉盆地">江汉盆地</option>  
				    <option value="四川盆地">四川盆地</option>  
				    <option value="柴达木盆地">柴达木盆地</option>  
				    <option value="二连盆地">二连盆地</option>  
				    <option value="吐哈盆地">吐哈盆地</option>  
				    <option value="苏北盆地">苏北盆地</option>  
				    <option value="南襄盆地">南襄盆地</option>  
				    <option value="沁水盆地">沁水盆地</option>  
				    <option value="三水盆地">三水盆地</option>  
				    <option value="兰坪思茅盆地">兰坪思茅盆地</option>  
				    <option value="百色盆地">百色盆地</option>  
				    <option value="琼东南盆地">琼东南盆地</option>  
				    <option value="焉耆盆地">焉耆盆地</option>  
				    <option value="莺歌海盆地">莺歌海盆地</option> 
				</select>
			<!-- <lable>常规：</lable>   -->
				<select name="regular" id="regular" style="height:26px">
					<option value="2" selected>--请选择--</option>
					<option value="0">常规</option>
					<option value="1">非常规</option>
				</select>  
				<lable>相关：</lable>  
				<select name="relative" id="relative" style="height:26px">
					<option value="2" selected>--请选择--</option>
					<option value="1">标题相关</option>
					<option value="0">内容相关</option>
				</select> 
				<!-- 添加关键词筛选选项 -->
				<lable>关键词：</lable>  
				<select name="Output" id="Output" style="height:26px">
					<option value="2" selected>--请选择--</option>
					<option value="1">关键词筛选</option>
					<option value="0">不进行筛选</option>
				</select> 

				<input type="submit" name="submit" value="检索" onclick="return check()"></td>
			</tr>
	</table>
</div> 
</body>
	<%String SearchId=String.valueOf(request.getAttribute("manageSearchId"));%>	
<script type="text/javascript">
	function check() {
		var checkObj1 = document.getElementById("startTime");
		var checkObj2 = document.getElementById("endTime");
		var checkObj3 = document.getElementById("oilfield");
		var checkObj4 = document.getElementById("company");
		var checkObj5 = document.getElementById("basin");
		var checkObj6 = document.getElementById("regular");
		var checkObj7 = document.getElementById("relative");
		//新加是否关键词筛选
		var checkObj8 = document.getElementById("Output");
		var i = 0;
		if(checkObj1.value==""&&checkObj2.value==""&&checkObj3.value==0&&checkObj4.value==0&&checkObj5.value==0&&checkObj6.value==2&&checkObj7.value==2&&checkObj8.value==2)	{
			alert("请至少选择一个检索条件");
			return false;
		}else{
			document.form1.action="manageNewsRetrieval?moduleId=<%=SearchId%>";
			document.form1.target="_blank";
		}
	}
</script>
</html>