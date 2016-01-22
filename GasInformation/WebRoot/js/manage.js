function isAll(){													// 是否全选
			var checkObj = document.all("cheboxId");		
				for(var i = 0;i<checkObj.length;i++){			
					if(checkObj[i].checked == true) 
						checkObj[i].checked = false;			
					else 
						checkObj[i].checked = true;		
			}	
		}
		
function deleteBatch(id) {							// 批量删除
		var result="";
		document.form1.action="../manager/deleteBatch?moduleId="+id;
		var checkObj = document.all("cheboxId");
		var j=0;
		for(var i = 0;i < checkObj.length;i++) {
				if(checkObj[i].checked == true){
					j++;
				}
			}
			if(j != 0) {
				result=confirm("确认删除？");
				if(result){
					return true;
				}else return false;
			}else {
				alert("请至少选择一个复选框");
				return false;
			}	
	}

/*
 * 选择下载文本类型
 */
function choseFileType(id){
	var tdDownload=document.getElementById("download");
	var inputToform="<td id='tdfileType' name='tdfileType'>" +
		"<input type='radio' value='1' name='fileType'>html" +
			"<input type='radio' value='2' name='fileType' checked>word" +
			"<input type='radio' value='3' name='fileType'>excel" +
			"<input type='submit' name='fileType' value='确定' onclick='return downloadBatch(" +id+
			")'></td>&nbsp&nbsp&nbsp";
	tdDownload.innerHTML+=inputToform;
	var downbtn=document.getElementById("downbtn");
	downbtn.disabled=true;
}

function downloadBatch(id) {										// 批量下载
			document.form1.action="../manager/downloadBatch?moduleId="+id;
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
		
function getIndex(){
	var obj = document.getElementById("colNameOption"); //定位id
	var index = obj.selectedIndex; // 选中索引
	return index;
}

function exportVisible(){
	var type = document.getElementById("exportGZDT");
	var index = getIndex();
	if(index !=0){
		type.style.display="block";		
	}else 
		type.style.display="none";	
}

function dtck(id){ 								// 导出到动态参考
	var index = getIndex();
	var text = document.getElementById("colNameOption").options[index].text ; 		// 选中文本
	document.form1.action="manageNewsDtck?moduleId="+id+"&columnName="+text;
	var checkObj = document.all("cheboxId");
	var j=0;
	for(var i = 0;i < checkObj.length;i++) {
		if(checkObj[i].checked == true){
			j++;
		}
	}
	
	if(j != 0) {
		if(index == 0){
			alert("请选择专栏");
		}else{		
			var r = cofirm("是否确定列入"+text+"专栏？");
			if(r==false){	
				return false;			
			}
			return true;
		}
	}else if(j == 0) {
		alert("请至少选择一个复选框");
		return false;
	}
}

function dtrd(id) {												// 导入到一周热点
			document.form1.action="manageNewsDtrd?moduleId="+id;
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
		
function isDelete(){										// 单个删除前确认
	var result=confirm("确认删除？");
	if(result){
		return true;
	}else return false;
}


function scan(){										// managCkwh.jsp 词库浏览
	var WordsType = document.getElementById("WordsType").value;
	if(document.getElementById("WordsType").value==0){
		alert("请选择词库类别");
		return false;
	}else {		
		var tmp = document.createElement("form");				
		var action = "getWords?WordsType="+WordsType;
		tmp.action = action;
		tmp.method = "post";
		document.body.appendChild(tmp);
		tmp.submit();
		return tmp;
		return true;
	}
}
function scan2(){										// managCkwh.jsp 词库浏览
	var WordsType = document.getElementById("WordsType2").value;
	if(document.getElementById("WordsType").value==0){
		alert("请选择词库类别");
		return false;
	}else {		
		var tmp = document.createElement("form");				
		var action = "getWebKeyWords?WordsType="+WordsType;
		tmp.action = action;
		tmp.method = "post";
		document.body.appendChild(tmp);
		tmp.submit();
		return tmp;
		return true;
	}
}


function updateWord(){										// managCkwh.jsp
															// 词库更新
	var WordsType = document.getElementById("WordsType").value;
	var tmp = document.createElement("form");
	if(WordsType=="Blacklist"){								
		var action = "UpdateblackOrWhite?output=1";
	}else{
		var action = "UpdateblackOrWhite?output=0";
	}
	tmp.action = action;
	tmp.method = "post";
	document.body.appendChild(tmp);
	tmp.submit();
	return tmp;
	return true;
}


function addWords(){								// managCkwh.jsp 词库添加关键词
	var WordsType = document.getElementById("WordsType").value;
   if(WordsType=="Oil" || WordsType=="Company"|| WordsType=="Basin"|| WordsType=="KeyWords"|| WordsType=="Blacklist" ){
	var text=prompt("请输入单词：");
	if(text!=null||text!=""){
		var tab= document.getElementById("words");		
		var len=tab.rows.length-1;
		var i=tab.rows[len].cells.length;
		if(i>4){
			var tabBody= document.getElementById("wordsBody");
			var newTr=document.createElement("tr");
			var newTd=document.createElement("td");
			newTd.innerHTML='<input id="WordsName" name="WordsName" type="radio"/>'+text;
			tabBody.appendChild(newTr);
			newTr.appendChild(newTd);
		}else{
			var newTd=document.createElement("td");
			newTd.innerHTML='<input id="WordsName" name="WordsName" type="radio"/>'+text;
			tab.rows[len].appendChild(newTd);
		}
		var tmp = document.createElement("form");				
		var action = "AddWords?WordsType="+WordsType+"&WordsName="+text;
		tmp.action = action;
		tmp.method = "post";
		document.body.appendChild(tmp);
		tmp.submit();
		return tmp;
		return true;
	}
	else return false;
	}else{
		//添加关键词的方法
		var text=prompt("请输入单词：");
		if(text!=null||text!=""){
			var tab= document.getElementById("words");		
			var len=tab.rows.length-1;
			var i=tab.rows[len].cells.length;
			if(i>4){
				var tabBody= document.getElementById("wordsBody");
				var newTr=document.createElement("tr");
				var newTd=document.createElement("td");
				newTd.innerHTML='<input id="WordsName" name="WordsName" type="radio"/>'+text;
				tabBody.appendChild(newTr);
				newTr.appendChild(newTd);
			}else{
				var newTd=document.createElement("td");
				newTd.innerHTML='<input id="WordsName" name="WordsName" type="radio"/>'+text;
				tab.rows[len].appendChild(newTd);
			}
			var tmp = document.createElement("form");				
			var action = "AddWebKeyWords?WordsType="+WordsType+"&WordsName="+text;
			tmp.action = action;
			tmp.method = "post";
			document.body.appendChild(tmp);
			tmp.submit();
			return tmp;
			return true;
		}
		else return false;
	}
	
}

function removeWords(){								// managCkwh.jsp 词库移除关键词
	/* document.getWords.action="../manager/RemoveWords"; */
	var WordsType = document.getElementById("WordsType").value;
	var checkObj = document.getElementsByName("WordsName");	
	var WordsName;
	
	var j=0;
	var result;
	for(var i = 0;i < checkObj.length;i++) {
		if (checkObj[i].checked){
			j++;
			WordsName = checkObj[i].value;
			result=confirm("确认删除   "+WordsName+"  ?");
			if(result==false){
				return false;
			}
		}
	}
	 if(WordsType=="Oil" || WordsType=="Company"|| WordsType=="Basin"|| WordsType=="KeyWords"|| WordsType=="Blacklist" ){
	if(j != 0) {
		var tmp = document.createElement("form");				
		var action = "RemoveWords?WordsType="+WordsType+"&WordsName="+WordsName;
		tmp.action = action;
		tmp.method = "post";
		document.body.appendChild(tmp);
		tmp.submit();
		return tmp;
		return true;
	}else {
		alert("请至少选择一个复选框");
		return false;
	}	
	}else{
		if(j != 0) {
			var tmp = document.createElement("form");				
			var action = "RemoveWebKeyWords?WordsType="+WordsType+"&WordsName="+WordsName;
			tmp.action = action;
			tmp.method = "post";
			document.body.appendChild(tmp);
			tmp.submit();
			return tmp;
			return true;
		}else {
			alert("请至少选择一个复选框");
			return false;
		}	
		
		
	}
}




/*
 * //更新数据自定义提示框 判断是否为数组 function isArray(v) { return v && typeof v.length ==
 * 'number' && typeof v.splice == 'function'; } //创建元素 function
 * createEle(tagName) { return document.createElement(tagName); } //在body中添加子元素
 * function appChild(eleName) { return document.body.appendChild(eleName); }
 * //从body中移除子元素 function remChild(eleName) { return
 * document.body.removeChild(eleName); }
 * //弹出窗口，标题（html形式）、html、宽度、高度、是否为模式对话框(true,false)、按钮（关闭按钮为默认，格式为['按钮1',fun1,'按钮2',fun2]数组形式，前面为按钮值，后面为按钮onclick事件）
 * function showWindow(title,html,width,height,modal,buttons) { //避免窗体过小 if
 * (width < 100) { width = 100; } if (height < 100) { height = 100; }
 * //声明mask的宽度和高度（也即整个屏幕的宽度和高度） var w,h; //可见区域宽度和高度 var cw =
 * document.body.clientWidth; var ch = document.body.clientHeight; //正文的宽度和高度
 * var sw = document.body.scrollWidth; var sh = document.body.scrollHeight;
 * //可见区域顶部距离body顶部和左边距离 var st = document.body.scrollTop; var sl =
 * document.body.scrollLeft; w = cw > sw ? cw:sw; h = ch > sh ? ch:sh; //避免窗体过大
 * if (width > w) { width = w; } if (height > h) { height = h; }
 * //如果modal为true，即模式对话框的话，就要创建一透明的掩膜 if (modal) { var mask = createEle('div');
 * mask.style.cssText =
 * "position:absolute;left:0;top:0px;background:#fff;filter:Alpha(Opacity=30);opacity:0.5;z-index:100;width:" +
 * w + "px;height:" + h + "px;"; appChild(mask); } //这是主窗体 var win =
 * createEle('div'); win.style.cssText = "position:absolute;left:" + (sl + cw/2 -
 * width/2) + "px;top:" + (st + ch/2 - height/2) +
 * "px;background:#f0f0f0;z-index:101;width:" + width + "px;height:" + height +
 * "px;border:solid 2px #afccfe;"; //标题栏 var tBar = createEle('div');
 * //afccfe,dce8ff,2b2f79 tBar.style.cssText = "margin:0;width:" + width +
 * "px;height:25px;background:#2793c1;border-top-left-radius:5px;border-top-right-radius:5px;";
 * //标题栏中的文字部分 var titleCon = createEle('div'); titleCon.style.cssText =
 * "float:left;margin:3px;"; titleCon.innerHTML =
 * title;//firefox不支持innerText，所以这里用innerHTML tBar.appendChild(titleCon);
 * //标题栏中的“关闭按钮” var closeCon = createEle('div'); closeCon.style.cssText =
 * "float:right;width:20px;margin:3px;cursor:pointer;";//cursor:hand在firefox中不可用
 * closeCon.innerHTML = "×"; tBar.appendChild(closeCon); win.appendChild(tBar);
 * //窗体的内容部分，CSS中的overflow使得当内容大小超过此范围时，会出现滚动条 var htmlCon = createEle('div');
 * htmlCon.style.cssText = "text-align:center;width:" + width + "px;height:" +
 * (height - 50) + "px;overflow:auto;"; htmlCon.innerHTML = html;
 * win.appendChild(htmlCon); //窗体底部的按钮部分 var btnCon = createEle('div');
 * btnCon.style.cssText = "width:" + width +
 * "px;height:25px;text-height:20px;text-align:center;padding-bottom:5px;";
 * //如果参数buttons为数组的话，就会创建自定义按钮 if (isArray(buttons)) { var length =
 * buttons.length; if (length > 0) { if (length % 2 == 0) { for (var i = 0; i <
 * length; i = i + 2) { //按钮数组 var btn = createEle('button'); btn.innerHTML =
 * buttons[i];//firefox不支持value属性，所以这里用innerHTML // btn.value = buttons[i];
 * btn.onclick = buttons[i + 1]; btnCon.appendChild(btn); //用户填充按钮之间的空白 var nbsp =
 * createEle('label'); nbsp.innerHTML = " "; btnCon.appendChild(nbsp); } } } }
 * //这是默认的关闭按钮 var btn = createEle('button'); // btn.setAttribute("value","取消");
 * btn.innerHTML = "取消"; // btn.value = '取消'; btnCon.appendChild(btn);
 * win.appendChild(btnCon); appChild(win); //顶部的标题栏和底部的按钮栏中的“关闭按钮”的关闭事件
 * btn.onclick = closeCon.onclick = function() { remChild(win); if (mask) {
 * window.location="FriendServlet?method=work"; } } }
 * 
 * function addCheckbox(name,value,id,click) { var str = "<input
 * type='checkbox' checked='checked' name='" + name + "' value='" + value + "'
 * id='" + id + "' onclick='" + (click == null ? '':click) + "'/> <label for='" +
 * id + "'>" + value + "</label>"; return str; }
 * 
 * function show() { var str = "<div><div style='border:1px blue'><table
 * cellspacing='2'>"; str += "</table></div><br/><div><table
 * cellspacing='2'>"; str += "<tr><td>" + addCheckbox('WebIDs','中石油新闻网','1') + "</td></tr>";
 * str += "<tr><td>" + addCheckbox('WebIDs','中石化新闻网','2') + "</td></tr>";
 * str += "</table></div></div>";
 * showWindow('请选择来源',str,200,300,true,['确定',fun1]); }
 * 
 * function fun1() { var cbox = document.getElementsByName('class_cb'); var str =
 * new Array(); for (var i=0;i<cbox.length;i++) { if(cbox[i].checked) { str[i] =
 * cbox[i].id; } } if(str) { showWindow('友情提示','数据更新中，请耐心等待...',200,300); var
 * tmp = document.createElement("form"); var action = "Update?ModuleID=<%=1%>";
 * tmp.action = action; tmp.method = "post"; document.body.appendChild(tmp); var
 * input = document.createElement("input"); input.type = "hidden"; input.name =
 * "param"; input.value = str; tmp.appendChild(input); tmp.submit(); return tmp; }
 * else { alert("请至少选择一个"); } }
 */



function addUser(){								// accountManagement.jsp 词库添加用户
	var els =document.getElementsByName("username");
	var flag = true;
	var username = prompt("用户名：");
	for (var i = 0,j = els.length;i < j;i++){
		if(els[i].value==username){
			flag=false;
		}
	}
	//
	var s=username;
	if(s.contains("!")||s.contains("@")||s.contains("#")||
			s.contains("$")||s.contains("%")||s.contains("^")||s.contains("&")
			||s.contains("*")||s.contains("(")||s.contains(")")||s.contains("_")||
			s.contains("+")||s.contains(" | ")||s.contains(";")) {
		alert("请输入字母或数字");
	}
	else{
	//
		if(flag == true){
			if(username.length > 2){
				var password = prompt("密码：");
				if(password.length >=3){
					var tmp = document.createElement("form");				
					var action = "addUser?username="+username+"&password="+password;
					tmp.action = action;
					tmp.method = "post";
					document.body.appendChild(tmp);
					tmp.submit();
					return tmp;
					return true;
				}
				else {
					alert("请设置3位以上的密码");
				}
			}
			else {
				alert("输入的用户名太短");
			}
		}
		else{
			alert("用户名已存在");
		}
	}	
		
}
function rePd() {
    var oldpd = document.getElementById("upd").value;
    var inOldpd =prompt("原密码：");
    if(inOldpd==oldpd){
        var password = prompt("新密码：");
        var username = document.getElementById("uname").value;
        if(password.length >=3){
            alert("修改成功，退出页面后生效");
            var tmp = document.createElement("form");                
            var action = "rePd?username="+username+"&password="+password;
            tmp.action = action;
            tmp.method = "post";
            document.body.appendChild(tmp);
            tmp.submit();
            return tmp;
            return true;
        }
        else {
            alert("请设置3位以上的密码");
        }
    }
    else{alert("密码错误");}
} 

function reUser(i){                                // accountManagement.jsp词库修改用户密码
    var id1 ="uname"+i;
    var id2 = "upd"+i;
    var oldpd = document.getElementById(id2).value;
    var inOldpd =prompt("原密码：");
    if(oldpd==inOldpd){
        var password = prompt("密码：");
        var username = document.getElementById(id1).value;
        if(password.length >=3){
                alert("修改成功");
                var tmp = document.createElement("form");                
                var action = "reUser?username="+username+"&password="+password;
                tmp.action = action;
                tmp.method = "post";
                document.body.appendChild(tmp);
                tmp.submit();
                return tmp;
                return true;
        }
        else {
                alert("请设置3位以上的密码");
        }
    }
    else{alert("密码错误");}
}

function delUser(i){								// accountManagement.jsp 词库删除用户
	var id ="uname"+i;
	var username = document.getElementById(id).value;		
	var result;
	result=confirm("确认删除   "+username+"  ?");
	if(result==false){
		return false;
	}
	else{
		var tmp = document.createElement("form");				
		var action = "delUser?username="+username;
		tmp.action = action;
		tmp.method = "post";
		document.body.appendChild(tmp);
		tmp.submit();
		return tmp;
		return true;
	}	
}