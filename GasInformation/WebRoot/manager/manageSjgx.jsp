<%@ page import="com.gasinfo.config.*,com.gasinfo.util.*,java.io.*,java.net.*,java.util.ArrayList,java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;IE=7;IE=9; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../css/calendar.css">
<script type="text/javascript" src="../js/calendar.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-zh.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/calendar-setup.js" charset="UTF-8"></script>
<%
	DataUpdatePara dataUpdata = new DataUpdatePara();
	ArrayList<String[]> GzdtPara = dataUpdata.getWebIdAndNameByModuleId(1);
	ArrayList<String[]> KcglPara = dataUpdata.getWebIdAndNameByModuleId(2);
	ArrayList<String[]> ZcfgPara = dataUpdata.getWebIdAndNameByModuleId(3);
	ArrayList<String[]> YzrdPara = dataUpdata.getWebIdAndNameByModuleId(4);
	ArrayList<String[]> GjhzPara = dataUpdata.getWebIdAndNameByModuleId(5);
	ArrayList<String[]> KjjzPara = dataUpdata.getWebIdAndNameByModuleId(6);
    ArrayList<String[]> TpxwPara = dataUpdata.getWebIdAndNameByModuleId(7);
	ArrayList<String[]> LddtPara = dataUpdata.getWebIdAndNameByModuleId(8);
	ArrayList<String[]> TjsjPara = dataUpdata.getWebIdAndNameByModuleId(9);
	ArrayList<String[]> KtkfPara = dataUpdata.getWebIdAndNameByModuleId(10);
	ArrayList<String[]> YjxxPara = dataUpdata.getWebIdAndNameByModuleId(11);
	/* String[] WebIDs = {""};
	String[] WebIDsTemp = {""}; */
%>

</head>
<body>
	<div id="top">
		<%request.setAttribute("manageNav", "Xtgl");%>
		<jsp:include page="manageNav.jsp"></jsp:include>
	</div>
<div class="container-fluid">
	<div class="row">
	<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
		<%request.setAttribute("manageLeftNav2", "Sjgx");%>
		<jsp:include page="leftMenu2.jsp"></jsp:include>
	</div>
	<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10">
			<%String checkButton="";%>
 		<table class="table table-bordered">
 			<tr><td>
 			
 				<table class="table table-bordered">
					<tr>
 					<td>
 					<table>
 						<tr>
 							<td>数据导入/导出：</td>
 						</tr>
 						<tr>
 							
 							<td>数据导入</td>
 							<td>
 								<form action="increaseData" method="post" id="form2" name="form2">
 									<input id="import" type="submit" value="导入" class="btn btn-info"/>
 								</form>
 							</td>
 						</tr>
 						<tr>
 							<td>数据导出</td>
 							<form action="exportData" method="post" id="form3" name="form3">
 							<td>
 								<lable for="startTime">起始日期：</lable>
								<input name="startTime" id="startTime" type="text" onclick="return showCalendar('startTime', 'y-mm-dd');" style="width: 80px" />&nbsp;
								<lable  for="endTime">终止日期：</lable>
								<input name="endTime" id="endTime" type="text" onclick="return showCalendar('endTime', 'y-mm-dd');" style="width: 80px" />&nbsp;
							</td>
 							<td><input id="export" type="submit" value="导出" class="btn btn-info"/></td>
 							</form>
 						</tr>
 					</table>
 					</td>
 					</tr>					
 				 </table>
 			
 			
 				<table class="table table-bordered" >
 					<tr>
 					<td>
 					<table> 				
					<tr>
						<td>数据更新：</td>
					<tr>
						<form action="Update?ModuleID=<%=1%>" method="post" id="form1" name="form1"> 				
						<td width="50px"><input type="submit" value="更新"  onclick="return show()" class="btn btn-info"></td>
						<td width="80px">工作动态</td>	
						<%
								for(int i = 0 ; i < GzdtPara.size() ; i++){
									String[] strs = GzdtPara.get(i);
									String id = strs[0];
									String name = strs[1];	
									 %>																 
						<td><input name="WebIDs" id="WebIDs" type="checkbox" value="<%=id%>"/>&nbsp;&nbsp;<%=name%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 					
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>						
						</form>						
					</tr>
					<tr>
						<form action="Update?ModuleID=<%=2%>" method="post" id="form1" name="form1">
						<td><input type=submit value="更新" onclick="return show()" class="btn btn-info"></td> 
						<td>油气管理</td>
						<%
								for(int i = 0 ; i < KcglPara.size() ; i++){
									String[] strs = KcglPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>							 
						<td><input name="WebIDs" type="checkbox"  value="<%=id%>"/>&nbsp;&nbsp;<%=name%></td> 
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr>
					<tr>
						<form action="Update?ModuleID=<%=3%>" method="post" id="form1" name="form1">					
						<td><input type=submit value="更新"  onclick="return show()" class="btn btn-info"></td> 
						<td>政策法规</td>	
						<%
								for(int i = 0 ; i < ZcfgPara.size() ; i++){
									String[] strs = ZcfgPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>						 
						<td><input name="WebIDs" type="checkbox" value="<%= id%>"/>&nbsp;&nbsp;<%= name%></td>			
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr>
					<tr>
			 			<form action="Update?ModuleID=<%=10%>" method="post" id="form1" name="form1">
						<td><input type=submit value="更新" onclick="return show()" class="btn btn-info"></td>
						<td>勘探开发</td>
						<%
								for(int i = 0 ; i < KtkfPara.size() ; i++){
									String[] strs = KtkfPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>		 					 
						<td><input name="WebIDs" type="checkbox"  value="<%=id%>"/>&nbsp;&nbsp;<%=name%></td>
						<%
							if((i+1)%5==0){	
								out.print("</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr>
					<tr>
						<form action="Update?ModuleID=<%=4%>" method="post" id="form1" name="form1">		
 						<td><input type=submit value="更新" onclick="return show()" class="btn btn-info"></td>				
						<td>一周热点</td>
						<%
								for(int i = 0 ; i < YzrdPara.size() ; i++){
									String[] strs = YzrdPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>		 					 
						<td><input name="WebIDs" type="checkbox" value="<%=id%>"/>&nbsp;&nbsp;<%= name%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>	 	
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr>
					<tr>
						<form action="Update?ModuleID=<%=5%>" method="post" id="form1" name="form1">	
 						<td><input type=submit value="更新" onclick="return show()" class="btn btn-info"></td> 					
						<td>对外合作</td>
						<%
								for(int i = 0 ; i < GjhzPara.size() ; i++){
									String[] strs = GjhzPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>							 
						<td><input name="WebIDs" type="checkbox" value="<%=id%>"/>&nbsp;&nbsp;<%= name%></td>						
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr>
					<tr>
						<form action="Update?ModuleID=<%=6%>" method="post" id="form1" name="form1">
						<td><input type=submit value="更新" onclick="return show()" class="btn btn-info"></td> 	
						<td>科技进展</td>
						<%
								for(int i = 0 ; i < KjjzPara.size() ; i++){
									String[] strs = KjjzPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>						 
						<td><input name="WebIDs" type="checkbox" value="<%= id%>"/>&nbsp;&nbsp;<%= name%></td>		
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr>
					<!--  <tr>
						<form action="Update?ModuleID=<%=7%>" method="post" id="form1" name="form1">						
						<td><input type=submit value="更新"  onclick="return show()" class="btn btn-info"></td> 
						<td>图片新闻</td>
						<%
								for(int i = 0 ; i < TpxwPara.size() ; i++){
									String[] strs = TpxwPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>							 
						<td><input name="WebIDs" type="checkbox"  value="<%=id%>"/>&nbsp;&nbsp;<%= name%></td>
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr> -->
					<tr>
						<form action="Update?ModuleID=<%=8%>" method="post" id="form1" name="form1">
						<td><input type=submit value="更新" onclick="return show()" class="btn btn-info"></td>
						<td>领导动态</td>
						<%
								for(int i = 0 ; i < LddtPara.size() ; i++){
									String[] strs = LddtPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>		 					 					 
						<td><input name="WebIDs" type="checkbox" value="<%=id%>"/>&nbsp;&nbsp;<%= name%></td>
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr>
					<tr>
						<form action="Update?ModuleID=<%=9%>" method="post" id="form1" name="form1">
						<td><input type=submit value="更新" onclick="return show()" class="btn btn-info"></td>
						<td>统计数据</td>
						<%
								for(int i = 0 ; i < TjsjPara.size() ; i++){
									String[] strs = TjsjPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>		 					 
						<td><input name="WebIDs" type="checkbox" value="<%= id%>"/>&nbsp;&nbsp;<%= name%></td>
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr>
					
					<%-- <tr>
						<form action="Update?ModuleID=<%=11%>" method="post" id="form1" name="form1">	
						<td><input type=submit value="更新" onclick="return show()" class="btn btn-info"></td>
						<td>油价信息</td>
						<%
								for(int i = 0 ; i < YjxxPara.size() ; i++){
									String[] strs = YjxxPara.get(i);
									String id = strs[0];
									String name = strs[1];							
						 %>		 					 
						<td><input name="WebIDs" type="checkbox"  value="<%=id%>"/>&nbsp;&nbsp;<%=name%></td>
						<%
							if((i+1)%5==0){	
								out.print("</tr><tr><td>&nbsp;</td><td>&nbsp;</td>");						
						 	}else {} 					
						} %>
						</form>
					</tr> --%>
					<tr>
						<td><form action="UpdateAll" method="post" id="form1" name="form1">
						<input type=submit value="全部更新" onclick="return showAll()" class="btn btn-info"></form>
						</td>
					</tr>		
					</table>
					</td>
					</tr>
					</table>
					
 				 	</td>
 				</tr>
 				</table>			
		</div>
	</div>
	</div>
<jsp:include page="../commonUser/footer.jsp"></jsp:include>					
</body>
<script>
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
				window.location="manageSjgx.jsp"; 
			} 
		} 
	}

	function show( ) {
			//var webids = new Array();
			
			var checkObj = document.all("WebIDs");
			var j=0;
			for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){ 
					
					j++;
				}
			}
			if(j != 0) {
				showWindow('友情提示','数据更新中，请耐心等待...',200,300);
				return true;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}
     	
	} 
	
	function showAll() { 
			if(!confirm("确认更新？"))
				return false;					
		}
		

function browseFolder(path) {
/**//***
     path 要显示值的对象id
****/ 
 	try {
 		var Message ="\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
 		var Shell = new ActiveXObject("Shell.Application");
 		var Folder = Shell.BrowseForFolder(0, Message,64, 17); //起始目录为：我的电脑
 		//var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面
 		if (Folder != null) {
			Folder = Folder.items(); // 返回 FolderItems 对象
 			Folder = Folder.item(); // 返回 Folderitem 对象
 			Folder = Folder.Path; // 返回路径
 			if (Folder.charAt(Folder.length - 1) != "\\") {
 				Folder = Folder + "\\";
 			}
 			document.getElementById("path").value =Folder;
 			return Folder;
 		}
 	}
 	catch (e) {
 	alert(e.message);
}}
</script>
</html>