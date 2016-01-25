//提交上传文件参数
function file(id){
		var title = document.getElementById("title").value;
		var Index = document.getElementById("department").selectedIndex;
		var department = document.getElementById("department").options[Index].text ;	
		var fileName = document.getElementById("pic").value;
		
		var limit = limitExtension(id,fileName);
		

		document.fileForm.action = "uploadFile?module=" + id + "&title=" + title + "&department=" + department + "&fileName="+fileName;
	
		if(title == null||title==""){
			alert("标题不能为空");
			return false;
		}
		if(fileName == null||fileName==""){
			alert("请添加文件");
			return false;
		}
		
		return limit;
}

//限制上传文件后缀名
function limitExtension(type,fileName){
	var index1 = fileName.lastIndexOf(".") + 1;    
	var index2 = fileName.length; 
	var postf = fileName.substring(index1,index2);//后缀名
	
	if(type == 1 || type ==2){
		if(postf != "doc" && postf != "docx" && postf != "pdf" 
			&& postf != "xls" && postf != "xlsx"){
			alert("请输入常用文档格式，如doc、docx、pdf、xls、xlsx等");
			return false;
		}else
			return true;
	}else if(type == 3){
		if(postf != "jpg" && postf != "jpeg" && postf != "png" ){
			alert("请输入常用图片格式，如jpg、png、jpeg等");
			return false;
		}else 
			return true;
	}
}

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
