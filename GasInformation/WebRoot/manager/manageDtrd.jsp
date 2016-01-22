<%@ page import="java.util.ArrayList,java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.gasinfo.util.*,java.io.*,java.net.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css">
<title>油气网络信息动态采集系统</title>
<body>
	<div id="top">
		<%request.setAttribute("manageNav", "Ywzx");%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
	
<div class="container-fluid">
	<div id="content">
		<div class="row">
			<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
				<%request.setAttribute("manageLeftNav", "Dtrd");%>
				<jsp:include page="leftMenu.jsp"></jsp:include>
			</div>
		
			<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
				<ol class="breadcrumb">
					<li><a href="#">业务中心</a></li>
					<li><a href="#" class="active">一周热点参考</a></li>				
				</ol>				
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead  align="center">
							<tr>
								<td><form action="deleteBatch?moduleId=12"
										method="post" id="form1" name="form1">
									<input type="checkbox" name="checkbox" onclick="b()"></td>
								<td>序号</td>
								<td>标题</td>
								<td>发布时间</td>
								<td>起始来源</td>
								<td>网站来源</td>
								<td>基本操作</td>
							</tr>
						</thead>
						<%
						ArrayList<News> list = (ArrayList<News>) request.getAttribute("list");
							for (int i =0; i <list.size(); i++) {
								String title=String.valueOf(list.get(i).getTitle());
								if(title.length()>20){
									title=title.substring(0,20)+"...";}
								else title=title;
						%>
						
						<tr>
							<td>
								<div align="center">
								<input type="checkbox" id="cheboxId" name="newsIds" value="<%=list.get(i).getId()%>">
								</div>
							</td>
							<td>
								<div align="center"><%=list.get(i).getRownumber()%></div>
							</td>
							<td>
								<div align="left">
								<a href="manageNews?newsId=<%=list.get(i).getId()%>&moduleId=12"target="_blank"
										title="<%=list.get(i).getTitle()%>" target="_blank"><%=title%>
								</a>
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
									<img src="../images/arrow_top.png" width="16" height="16"/>
										<a href="updownstick?moduleId=12&updown=up&updownId=<%=list.get(i).getUpdownId()%>&newsId=<%=list.get(i).getId()%>">
											上移</a>&nbsp;&nbsp;
									<img src="../images/arrow_down.png" width="16" height="16"/>
										<a href="updownstick?moduleId=12&updown=down&updownId=<%=list.get(i).getUpdownId()%>&newsId=<%=list.get(i).getId()%>">
											下移</a>&nbsp;&nbsp;
									<img src="../images/top.png" width="16" height="16"/>
										<a href="updownstick?moduleId=12&updown=stick&updownId=<%=list.get(i).getUpdownId()%>&newsId=<%=list.get(i).getId()%>">
											置顶</a>&nbsp;&nbsp;
									<img src="../images/edt.gif" width="16" height="16" />
										<a href="manageNewsModify?newsId=<%=list.get(i).getId()%>&moduleId=12">
											修改</a>&nbsp;&nbsp;
									<img src="../images/del.gif" width="16" height="16" />
										<a href="DeleteSingle?newsId=<%=list.get(i).getId()%>&moduleId=12">
											删除</a>												
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
								<td width="65%">								
									<table>
										<tr>
											<td><input type="submit" name="submit" value="删除" onclick="return sc()" ></td>
											<td><input type="submit" name="submit" value="清空" onclick="return qk()" ></td>																			
											<td><input type="submit" name="submit" value=" 生成  [ 一周热点参考  ] "  onclick="return rdck()"></td>	
										</tr>
									</table>
								</td>
								<td>
								</td>
							</tr>
							</table>
							</td>
						</tr>
							</form>
							
						<tr>
							<td colspan="7" style="text-align:center">
							<jsp:include page="../commonUser/pageroll.jsp" flush="true">
							<jsp:param value="getManageData?moduleId=12" name="action" /></jsp:include></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
	
	<jsp:include page="../commonUser/footer.jsp"></jsp:include>
</body>
<script>
function rdck(){
	document.form1.action="downloadBatch?moduleId=12&fileType=2";			
	}
	
function sc(){
			var checkObj = document.all("cheboxId");
			var j=0;
			for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){
					j++;
				}
			}
			if(j != 0) {
				return true;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}
	}
	
	
function qk(){
	document.form1.action="DeleteAll?moduleId=12";			
	}

function b(){
			var checkObj = document.all("cheboxId");		
				for(var i = 0;i<checkObj.length;i++){			
					if(checkObj[i].checked == true) 
						checkObj[i].checked = false;			
					else 
						checkObj[i].checked = true;		
			}	
		}
		
		function a() {
			var checkObj = document.all("cheboxId");
			var j=0;
			for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){
					j++;
				}
			}
			if(j != 0) {
				return true;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}
		} 
		function c() {
			document.form1.action="FriendServlet?method=download_select&moduleId=12";
			var checkObj = document.all("cheboxId");
			var j=0;
			for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){
					j++;
				}
			}
			if(j != 0) {
				return true;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}
		}
		
		function d() {

		var checkObj1 = document.all("checkbox1");
		var checkObj2 = document.all("checkbox2");
		var checkObj3 = document.all("checkbox3");
		var checkObj4 = document.all("checkbox4");
		var checkObj5 = document.all("checkbox5");
		var i = 0;
		if(checkObj1.checked==false&&checkObj2.checked==false&&checkObj3.checked==false&&checkObj4.checked==false&&checkObj5.checked==false)	{
		
			alert("请至少选择一个检索条件");
			return false;
		}else{
			document.form1.action="manageNewsRetrieval?moduleId=12";
		}
		}
		
	//判断是否为数组 
	function isArray(v) { 
		return v && typeof v.length == 'number' && typeof v.splice == 'function'; 
	} 
	//创建元素 
	function createEle(tagName) { 
		return document.createElement(tagName); 
	} 
	//在body中添加子元素 
	function appChild(eleName) { 
		return document.body.appendChild(eleName); 
	} 
	//从body中移除子元素 
	function remChild(eleName) { 
		return document.body.removeChild(eleName); 
	} 
	//弹出窗口，标题（html形式）、html、宽度、高度、是否为模式对话框(true,false)、按钮（关闭按钮为默认，格式为['按钮1',fun1,'按钮2',fun2]数组形式，前面为按钮值，后面为按钮onclick事件） 
	function showWindow(title,html,width,height,modal,buttons) { 
		//避免窗体过小 
		if (width < 100) { 
			width = 100; 
		} 
		if (height < 100) { 
			height = 100; 
		} 
		//声明mask的宽度和高度（也即整个屏幕的宽度和高度） 
		var w,h; 
		//可见区域宽度和高度 
		var cw = document.body.clientWidth; 
		var ch = document.body.clientHeight; 
		//正文的宽度和高度 
		var sw = document.body.scrollWidth; 
		var sh = document.body.scrollHeight; 
		//可见区域顶部距离body顶部和左边距离 
		var st = document.body.scrollTop; 
		var sl = document.body.scrollLeft; 
		w = cw > sw ? cw:sw; 
		h = ch > sh ? ch:sh; 
		//避免窗体过大 
		if (width > w) { 
			width = w; 
		} 
		if (height > h) { 
			height = h; 
		} 
		//如果modal为true，即模式对话框的话，就要创建一透明的掩膜 
		if (modal) { 
			var mask = createEle('div'); 
			mask.style.cssText = "position:absolute;left:0;top:0px;background:#fff;filter:Alpha(Opacity=30);opacity:0.5;z-index:100;width:" + w + "px;height:" + h + "px;"; 
			appChild(mask); 
		} 
		//这是主窗体 
		var win = createEle('div'); 
		win.style.cssText = "position:absolute;left:" + (sl + cw/2 - width/2) + "px;top:" + (st + ch/2 - height/2) + "px;background:#f0f0f0;z-index:101;width:" + width + "px;height:" + height + "px;border:solid 2px #afccfe;"; 
		//标题栏 
		var tBar = createEle('div'); 
		//afccfe,dce8ff,2b2f79 
		tBar.style.cssText = "margin:0;width:" + width + "px;height:25px;background:#2793c1;border-top-left-radius:5px;border-top-right-radius:5px;"; 
		//标题栏中的文字部分 
		var titleCon = createEle('div'); 
		titleCon.style.cssText = "float:left;margin:3px;"; 
		titleCon.innerHTML = title;//firefox不支持innerText，所以这里用innerHTML 
		tBar.appendChild(titleCon); 
		//标题栏中的“关闭按钮” 
		var closeCon = createEle('div'); 
		closeCon.style.cssText = "float:right;width:20px;margin:3px;cursor:pointer;";//cursor:hand在firefox中不可用 
		closeCon.innerHTML = "×"; 
		tBar.appendChild(closeCon); 
		win.appendChild(tBar); 
		//窗体的内容部分，CSS中的overflow使得当内容大小超过此范围时，会出现滚动条 
		var htmlCon = createEle('div'); 
		htmlCon.style.cssText = "text-align:center;width:" + width + "px;height:" + (height - 50) + "px;overflow:auto;"; 
		htmlCon.innerHTML = html; 
		win.appendChild(htmlCon); 
		//窗体底部的按钮部分 
		var btnCon = createEle('div'); 
		btnCon.style.cssText = "width:" + width + "px;height:25px;text-height:20px;text-align:center;padding-bottom:5px;"; 
		//如果参数buttons为数组的话，就会创建自定义按钮 
		if (isArray(buttons)) { 
			var length = buttons.length; 
			if (length > 0) { 
				if (length % 2 == 0) { 
					for (var i = 0; i < length; i = i + 2) { 
						//按钮数组 
						var btn = createEle('button'); 
						btn.innerHTML = buttons[i];//firefox不支持value属性，所以这里用innerHTML 
						// btn.value = buttons[i]; 
						btn.onclick = buttons[i + 1]; 
						btnCon.appendChild(btn); 
						//用户填充按钮之间的空白 
						var nbsp = createEle('label'); 
						nbsp.innerHTML = "  "; 
						btnCon.appendChild(nbsp); 
					} 
				} 
			} 
		} 
		//这是默认的关闭按钮 
		var btn = createEle('button'); 
		// btn.setAttribute("value","取消"); 
		btn.innerHTML = "取消"; 
		// btn.value = '取消'; 
		btnCon.appendChild(btn); 
		win.appendChild(btnCon); 
		appChild(win); 
		//顶部的标题栏和底部的按钮栏中的“关闭按钮”的关闭事件 
		btn.onclick = closeCon.onclick = function() { 
			remChild(win); 
			if (mask) { 
				window.location="FriendServlet?method=work"; 
			} 
		} 
	}
	 
	function addCheckbox(name,value,id,click) { 
		var str = "<input type='checkbox' checked='checked' name='" + name + "' value='" + value + "' id='" + id + "' onclick='" + (click == null ? '':click) + "'/> <label for='" + id + "'>" + value + "</label>"; 
		return str; 
	} 
	
	function show() { 
		var str = "<div><div style='border:1px blue'><table cellspacing='2'>"; 
		str += "</table></div><br/><div><table cellspacing='2'>"; 
		str += "<tr><td>" + addCheckbox('class_cb','中石油新闻网','1') + "</td></tr>";
		str += "<tr><td>" + addCheckbox('class_cb','中石化新闻网','2') + "</td></tr>";  
		str += "</table></div></div>"; 
		showWindow('请选择来源',str,200,300,true,['确定',fun1]); 
	} 

	function fun1() { 
		var cbox = document.getElementsByName('class_cb'); 
		var str = new Array(); 
		for (var i=0;i<cbox.length;i++) { 
			if(cbox[i].checked) { 
				str[i] = cbox[i].id;
			} 
		}  	
 		if(str) {
 			showWindow('友情提示','数据更新中，请耐心等待...',200,300);
			var tmp = document.createElement("form"); 
	     	var action = "FriendServlet?method=RefreshData&i=<%=1%>";
	     	tmp.action = action; 
	     	tmp.method = "post"; 
	     	document.body.appendChild(tmp); 
	     	var input = document.createElement("input"); 
	     	input.type = "hidden";
	     	input.name = "param";
	     	input.value = str;
	     	tmp.appendChild(input); 
	     	tmp.submit(); 
	     	return tmp;	      
     	} else {
     	alert("请至少选择一个");
     	}
	} 
</script>
</html>
