<%@ page import="java.util.ArrayList,java.util.HashMap"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.util.*"%>
<%
	//工作动态
	
    ArrayList<News> listGzdt=(ArrayList<News>)request.getAttribute("listGzdt");
    ArrayList<News> listYzrd=(ArrayList<News>)request.getAttribute("listYzrd");
    ArrayList<News> listTpxw=(ArrayList<News>)request.getAttribute("listTpxw");
    ArrayList<HashMap<String, Integer>> listHotWord=(ArrayList<HashMap<String, Integer>>)request.getAttribute("listHotWord");
%>


<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>油气网络信息动态采集系统</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/calendar.css">
<script type="text/javascript" src="js/calendar.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/calendar-zh.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/calendar-setup.js" charset="UTF-8"></script>
<style type="text/css">
#bg {
	background: url(images/bg_top.png);
}

#picNews {
	overflow: hidden;
	border: 1px;
	width: 100%;
	height: 100px;
}

#picNews img {
	border: 3px solid #F2F2F2;
	height: 100px;
}

#inpic {
	float: left;
	width: 600%;
}

#pic1 {
	float: left;
}

#pic2 {
	float: left;
}

#bg {
	background: url(images/bg_top.png);
}
</style>
</head>

<!-- <center> -->
<body>
	<div id="all" class="container">
		<div id="nav" class="navbar navbar-default">
			<div id="bg" width="100%">
				<table style="text-align:center">
					<tr>
						<td width="150"><img src="images/logo.png" width="70"
							height="70" />
						</td>
						<td width="1"><img src="images/BoxOver_bd.png" width="1"
							height="40" />
						</td>
						<td width="500"><img src="images/title.gif" width="450"
							height="40" />
						</td>
						<td width="350">&nbsp;</td>
					</tr>
				</table>
			</div>

			<div>
				<%
					request.setAttribute("menuIndex", "index");
							String menu=(String)request.getAttribute("menuIndex");
				%>
				<ul class="nav nav-pills" id="myTab">
					<li <%="index".equals(menu)?"class=\"active\"":""%>><a
						href="index.action">首页</a>
					</li>
					<li <%="Zcfg".equals(menu)?"class=\"active\"":""%>><a
						href="commonUser/getData?moduleId=3">政策法规</a>
					</li>
					<li <%="Kcgl".equals(menu)?"class=\"active\"":""%>><a
						href="commonUser/getData?moduleId=2">油气管理</a></li>
					<li <%="Ktkf".equals(menu)?"class=\"active\"":""%>><a
						href="commonUser/getData?moduleId=10">勘探开发</a></li>
					<!--<li <%="Yqbg".equals(menu)?"class=\"active\"":""%>><a
						href="#">舆情报告</a></li> href="commonUser/getData?moduleId=14">舆情报告</a></li> -->
					<li <%="Kjjz".equals(menu)?"class=\"active\"":""%>><a
						href="commonUser/getData?moduleId=6">科技进展</a></li>
					<li <%="Gjhz".equals(menu)?"class=\"active\"":""%>><a
						href="commonUser/getData?moduleId=5">对外合作</a></li>
					<li <%="Qt".equals(menu)?"class=\"active\"":""%>><a
						href="commonUser/getData?moduleId=15">其它</a></li><!-- href="commonUser/getData?moduleId=15">其它</a></li> -->
					<li><a href="manager/manageLogin.jsp" target="_blank">管理员登录</a>
					</li>
				</ul>

				<div id="search" class="row">
					<form class="form-inline" method="post" action="commonUser/search"
						target="_blank">
						<div class="col-md-12" style="text-align:center;font-size:12px;">
							<marquee onmousemove="this.stop()" onmouseout="this.start()"
								direction="left" scrollamount="5" behavior="scroll"
								hidden="true"></marquee>
							&nbsp;
							<lable for="startTime">起始日期：</lable>
							<input name="startTime" id="startTime" type="text"
								onclick="return showCalendar('startTime', 'y-mm-dd');"
								style="width: 80px" />&nbsp;
							<lable for="endTime">终止日期：</lable>
							<input name="endTime" id="endTime" type="text"
								onclick="return showCalendar('endTime', 'y-mm-dd');"
								style="width: 80px" />&nbsp;
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
							</select> <select name="module" id="module" style="height:26px">
								<option value="-1">--全站--</option>
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
							</select> <select name="sel" id="sel" style="height:26px">
								<option value="0">标题检索</option>
								<option value="1">全文检索</option>
							</select> <label for="textfield"></label> <input name="textfield"
								id="textfield" type="text" placeholder="Search" /> <input
								type="submit" name="button" id="button" value="搜索"
								onclick="return researchWarn()" />
						</div>
					</form>
				</div>

			</div>
		</div>

		<div class="row">
			<div class="col-md-3 col-xs-3 col-sm-3 col-lg-3">
				<table id="left" class="table table-condensed">
					<tr>
						<th colspan="2"
							style="border-radius:10px 10px 0px 0px;background-color:#4fb2d1"
							colspan="2"><span>&nbsp;<img
								src="images/mainIcon.png" height="14px" />
						</span> <font face="宋体" color="#FFFFFF"><b>&nbsp;热点新闻</b>
						</font>
						</th>
					</tr>
					<%
						for (int i = 0; i <7 && i<listYzrd.size(); i++) {
										String title=String.valueOf(listYzrd.get(i).getTitle());
					%>
					<tr>
						<td>* <a
							href="commonUser/news?newsId=<%=listYzrd.get(i).getId()%>&moduleId=100"
							title="<%=title%>" target="_blank">
								<%
									if(title.length()>8)
														out.print(title.substring(0,8)+"...");
													else
														out.print(title);
								%>
						</a></td>
						<td><%=listYzrd.get(i).getPubTime()%></td>
					</tr>
					<%
						}
					%>
					
					
					
<!-- 					空行到七行 -->
  					<%
						if (listYzrd.size()<7)
						for(int iii=7-listYzrd.size();iii>0;iii--)
					{
					%>
					<tr>
						<td> &nbsp;
						</td>
						<td>&nbsp;</td>
					</tr>
					<%
					}
					%> 
					
					

					
					
					
					
					
					
					
					
					
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"
							style="border-radius:10px 10px 0px 0px;background-color:#4fb2d1"
							colspan="2"><span>&nbsp;<img src="images/mirCon.png"
								height="14px" />
						</span> <font face="宋体" color="#FFFFFF"><b>&nbsp;热词分析</b>
						</font>
						</td>
					</tr>
					<tr>
						<td><font color="#0000FF"><b>热词</b>
						</font>
						</td>
						<td><font color="#0000FF"><b>关注度</b>
						</font>
						</td>
					</tr>
					<%
						//热词分析
										for (HashMap<String, Integer> dat : listHotWord) {
										for(java.util.Map.Entry<String, Integer> entry : dat.entrySet()) {
					%>
					<tr>
						<td><%=entry.getKey()%></td>
						<td><%=entry.getValue()%></td>
					</tr>
					<%
						}
										}
					%>
				</table>
			</div>


			<div class="col-md-9 col-xs-9 col-sm-9 col-lg-9">
				<table id="right" class="table table-condensed">
					<tr>
						<td	colspan="2" style="border-radius:10px 0px 0px 0px;background-color:#4fb2d1;align:left">				
							<span>&nbsp;<img src="images/calCon.png" height="14px" /></span> 
							<font face="宋体" color="#FFFFFF"><b>&nbsp;工作动态</b></font>
						</td>
						<td style="border-radius:0px 10px 0px 0px;background-color:#4fb2d1;text-align:center;text-valign:middle;">
							<a href="./commonUser/getData?moduleId=1" target="_blank"> 
							<span><img src="images/more.gif" height=12px />
							</span>
						</a></td>
					</tr>

					<%
						for (int i =0; i <listGzdt.size(); i++) {
									String title=String.valueOf(listGzdt.get(i).getTitle());
					%>

					<tr>

						<td><div align="left">
								&nbsp;> <a
									href="commonUser/news?newsId=<%=listGzdt.get(i).getId()%>&moduleId=1"
									target="_blank" title="<%=title%>">
									<%
										if(title.length()>25)
															out.print(title.substring(0,25)+"...");
														else
															out.print(title);
									%>
								</a>
							</div>
						</td>
						<td><%=listGzdt.get(i).getOriginSource()%></td>
						<td style="text-align: center"><%=listGzdt.get(i).getPubTime()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
<!-- hidden="true"为隐藏图片滚动条 -->
		<div id="pic" hidden="true">
			<table class="table  table-condensed" border="1">
				<tr>
					<td width="20px"
						style="text-align: center;border-radius:5px 5px 5px 5px;background-color:#4fb2d1">
						<a href="FriendServlet?method=photo"><font face="宋体"
							color="#FFFFFF"><b>图片新闻</b>
						</font>
					</a></td>
					<td>
						<div id="picNews">
							<div id="inpic">
								<div id="pic1">
									<%
										for(int i=listTpxw.size()-1;i>=0;i--) {
									%>
									<a href="photomore.jsp?photoId=<%=listTpxw.get(i).getId()%>"
										title="<%=listTpxw.get(i).getTitle()%>" target="_blank"><img
										src="<%=listTpxw.get(i).getImagePath()%>" width="150"
										height="100" border="0" />
									</a>
									<%
										}
									%>
								</div>
								<div id="pic2" width="0px"></div>
							</div>
						</div></td>
				</tr>
			</table>
		</div>

		<jsp:include page="commonUser/footer.jsp"></jsp:include>
	</div>


	<script>
	<!--
		var speed = 30;
		var tab = document.getElementById("picNews");
		var tab1 = document.getElementById("pic1");
		var tab2 = document.getElementById("pic2");
		tab2.innerHTML = tab1.innerHTML;
		function Marquee() {
			if (tab2.offsetWidth - tab.scrollLeft <= 0)
				tab.scrollLeft -= tab1.offsetWidth;
			else {
				tab.scrollLeft++;
			}
		}
		var MyMar = setInterval(Marquee, speed);
		tab.onmouseover = function() {
			clearInterval(MyMar);
		};
		tab.onmouseout = function() {
			MyMar = setInterval(Marquee, speed);
		};

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