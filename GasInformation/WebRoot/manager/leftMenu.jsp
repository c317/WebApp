<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="../css/bootstrap.min.css" rel ="stylesheet">
</head>
<body>
     <div class="panel panel-info">
   <div class="panel-heading">
      <h6 class="panel-title">业务列表</h6>
   </div>  
	<div class="list-group">
		<%String menu=String.valueOf(request.getAttribute("manageLeftNav"));%>	
		<a href="getManageData?moduleId=1" class="list-group-item <%="Gzdt".equals(menu)?"active\"":""%>">工作动态</a>
		<a href="getManageData?moduleId=4" class="list-group-item <%="Yzrd".equals(menu)?"active\"":""%>">一周热点</a>
		<a href="getManageData?moduleId=3" class="list-group-item <%="Zcfg".equals(menu)?"active\"":""%>">政策法规</a>
		<a href="getManageData?moduleId=2" class="list-group-item <%="Kcgl".equals(menu)?"active\"":""%>">油气管理</a>
		<a href="getManageData?moduleId=10" class="list-group-item <%="Ktkf".equals(menu)?"active\"":""%>">勘探开发</a>
		<a href="getManageData?moduleId=6" class="list-group-item <%="Kjjz".equals(menu)?"active\"":""%>">科技进展</a>
		<a href="getManageData?moduleId=5" class="list-group-item <%="Gjhz".equals(menu)?"active\"":""%>">对外合作</a>
		<a href="getManageData?moduleId=8" class="list-group-item <%="Lddt".equals(menu)?"active\"":""%>">领导动态</a>
   <%-- <a href="getManageData?moduleId=11" class="list-group-item <%="Yjxx".equals(menu)?"active\"":""%>">油价信息</a> --%>
		<a href="getManageData?moduleId=9" class="list-group-item <%="Tjsj".equals(menu)?"active\"":""%>">统计数据</a>
		<%-- <a href="getManageData?moduleId=7" class="list-group-item <%="Tpxw".equals(menu)?"active\"":""%>">图片新闻</a> --%>
	</div>
	</div>
	     <div class="panel panel-info">
   <div class="panel-heading">
      <h6 class="panel-title">生成参考</h6>
   </div>  
	<div class="list-group">
		
		<a href="getManageData?moduleId=12" class="list-group-item <%="Dtrd".equals(menu)?"active\"":""%>">一周热点参考</a>
		<a href="getManageData?moduleId=13" class="list-group-item <%="Dtck".equals(menu)?"active\"":""%>">工作动态参考</a>
	</div>
	</div>
</body>
</html>