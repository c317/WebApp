	function $(id) {
		return document.getElementById(id);
	}
	function $F(name) {
		return document.getElementsByTagName(name);
	}

     function add(){
        var otr = document.getElementById("tab").insertRow(-1);   
        var otd1 = document.createElement("td");
        otd1.innerHTML = '<lable><input type="checkbox" name="ModuleCheckB" checked/></label><input type="text" name="ModuleB_Postion" id="module'+($('tab').rows.length-1)+'" value=""/>至';
        var otd2 = document.createElement("td");
        otd2.innerHTML = '<select id="ModuleB_MK" name="ModuleB_MK"><option value="1" selected>工作动态</option><option value="2">矿场管理</option><option value="3">政策法规</option><option value="4">一周热点</option><option value="5">国际合作</option><option value="6">科技进展</option><option value="7">图片新闻</option><option value="8">领导动态</option><option value="9">统计数据</option><option value="10">勘探开发</option><option value="11">油价信息</option></select></td>';
        otr.appendChild(otd1);
        otr.appendChild(otd2);
     }

	function del() {
		 var tab=document.getElementById("tab");
			for (var i=tab.rows.length - 1 ;i>=0;i--)
			 {
				if(tab.rows[i].cells[0].getElementsByTagName('input')[0].checked)
				{
					tab.deleteRow(i);
				}
				/* document.getElementById("tab").deleteRow($('tab').rows.length - 1); */
			}
		}
		
     function add1(){
        var otr = document.getElementById("tab1").insertRow(-1);   
        var otd1 = document.createElement("td");
        otd1.innerHTML = '<lable><input type="checkbox" name="ModuleCheckC" checked/></label><input type="text" name="ModuleC_Postion" id="module'+($('tab1').rows.length-1)+'" value=""/>至';
        var otd2 = document.createElement("td");
        otd2.innerHTML = '<select id="ModuleC_MK" name="ModuleC_MK"><option value="1" selected>工作动态</option><option value="2">矿场管理</option><option value="3">政策法规</option><option value="4">一周热点</option><option value="5">国际合作</option><option value="6">科技进展</option><option value="7">图片新闻</option><option value="8">领导动态</option><option value="9">统计数据</option><option value="10">勘探开发</option><option value="11">油价信息</option></select></td>';
        otr.appendChild(otd1);
        otr.appendChild(otd2);
     }

	function del1() {
		 var tab=document.getElementById("tab1");
			for (var i=tab.rows.length - 1 ;i>=0;i--)
			 {
				if(tab.rows[i].cells[0].getElementsByTagName('input')[0].checked)
				{
					tab.deleteRow(i);
				}
				/* document.getElementById("tab1").deleteRow($('tab').rows.length - 1); */
			}
		}
		
	function checkModule1(){
		if(document.getElementById("ModuleIDA").checked==true){
			document.getElementById("ModuleA_MK").disabled=false;
			document.getElementById("ModuleB_MK").disabled=true;
			document.getElementById("btnAdd").disabled=true;
			document.getElementById("btnDel").disabled=true;
			document.getElementById("ModuleC1_MK").disabled=true;
			document.getElementById("ModuleC2_MK").disabled=true;
			document.getElementById("btnAdd1").disabled=true;
			document.getElementById("btnDel1").disabled=true;
		}else if(document.getElementById("ModuleIDA").checked==false){
			document.getElementById("ModuleA_MK").disabled=true;
			document.getElementById("ModuleB_MK").disabled=true;
			document.getElementById("btnAdd").disabled=true;
			document.getElementById("btnDel").disabled=true;
			document.getElementById("ModuleC1_MK").disabled=true;
			document.getElementById("ModuleC2_MK").disabled=true;
			document.getElementById("btnAdd1").disabled=true;
			document.getElementById("btnDel1").disabled=true;
		}
	}
	
	function checkModule2(){	
		if(document.getElementById("ModuleIDB").checked==true){
			document.getElementById("ModuleA_MK").disabled=true;
			document.getElementById("ModuleB_MK").disabled=false;
			document.getElementById("btnAdd").disabled=false;
			document.getElementById("btnDel").disabled=false;
			document.getElementById("ModuleC1_MK").disabled=true;
			document.getElementById("ModuleC2_MK").disabled=true;
			document.getElementById("btnAdd1").disabled=true;
			document.getElementById("btnDel1").disabled=true;
		}else if(document.getElementById("ModuleIDB").checked==false){
			document.getElementById("ModuleA_MK").disabled=true;
			document.getElementById("ModuleB_MK").disabled=true;
			document.getElementById("btnAdd").disabled=true;
			document.getElementById("btnDel").disabled=true;
			document.getElementById("ModuleC1_MK").disabled=true;
			document.getElementById("ModuleC2_MK").disabled=true;
			document.getElementById("btnAdd1").disabled=true;
			document.getElementById("btnDel1").disabled=true;
		}
	}

	function checkModule3(){		
		if(document.getElementById("ModuleIDC").checked==true){
			document.getElementById("ModuleA_MK").disabled=true;
			document.getElementById("ModuleB_MK").disabled=true;
			document.getElementById("btnAdd").disabled=true;
			document.getElementById("btnDel").disabled=true;
			document.getElementById("ModuleC1_MK").disabled=false;
			document.getElementById("ModuleC2_MK").disabled=false;
			document.getElementById("btnAdd1").disabled=false;
			document.getElementById("btnDel1").disabled=false;
		}else if(document.getElementById("ModuleIDC").checked==false){
			document.getElementById("ModuleA_MK").disabled=true;
			document.getElementById("ModuleB_MK").disabled=true;
			document.getElementById("btnAdd").disabled=true;
			document.getElementById("btnDel").disabled=true;
			document.getElementById("ModuleC1_MK").disabled=true;
			document.getElementById("ModuleC2_MK").disabled=true;
			document.getElementById("btnAdd1").disabled=true;
			document.getElementById("btnDel1").disabled=true;
		}
	}
	
	function checkSource1(){
		if(document.getElementById("SelectSourceIDA").checked==true){
			document.getElementById("SelectSourceA_ClassName").disabled=false;
			document.getElementById("SelectSourceB_TagName").disabled=true;
			document.getElementById("SelectSourceB_Var").disabled=true;
			document.getElementById("SelectSourceC_Var").disabled=true;
			document.getElementById("SelectSourceC_Value").disabled=true;
		}else if(document.getElementById("SelectSourceIDA").checked==false){
			document.getElementById("SelectSourceA_ClassName").disabled=true;
			document.getElementById("SelectSourceB_TagName").disabled=true;
			document.getElementById("SelectSourceB_Var").disabled=true;
			document.getElementById("SelectSourceC_Var").disabled=true;
			document.getElementById("SelectSourceC_Value").disabled=true;
		}
	}
	
	function checkSource2(){	
		if(document.getElementById("SelectSourceIDB").checked==true){
			document.getElementById("SelectSourceA_ClassName").disabled=true;
			document.getElementById("SelectSourceB_TagName").disabled=false;
			document.getElementById("SelectSourceB_Var").disabled=false;
			document.getElementById("SelectSourceC_Var").disabled=true;
			document.getElementById("SelectSourceC_Value").disabled=true;
		}else if(document.getElementById("SelectSourceIDB").checked==false){
			document.getElementById("SelectSourceA_ClassName").disabled=true;
			document.getElementById("SelectSourceB_TagName").disabled=true;
			document.getElementById("SelectSourceB_Var").disabled=true;
			document.getElementById("SelectSourceC_Var").disabled=true;
			document.getElementById("SelectSourceC_Value").disabled=true;
		}
	}

	function checkSource3(){		
		if(document.getElementById("SelectSourceIDC").checked==true){
			document.getElementById("SelectSourceA_ClassName").disabled=true;
			document.getElementById("SelectSourceB_TagName").disabled=true;
			document.getElementById("SelectSourceB_Var").disabled=true;
			document.getElementById("SelectSourceC_Var").disabled=false;
			document.getElementById("SelectSourceC_Value").disabled=false;
		}else if(document.getElementById("SelectSourceIDC").checked==false){
			document.getElementById("SelectSourceA_ClassName").disabled=true;
			document.getElementById("SelectSourceB_TagName").disabled=true;
			document.getElementById("SelectSourceB_Var").disabled=true;
			document.getElementById("SelectSourceC_Var").disabled=true;
			document.getElementById("SelectSourceC_Value").disabled=true;
		}
	}
	
	function checkTime1(){
		if(document.getElementById("SelectTimeIDA").checked==true){
			document.getElementById("SelectTimeA_ClassName").disabled=false;
			document.getElementById("SelectTimeB_TagName").disabled=true;
			document.getElementById("SelectTimeC_Var").disabled=true;
			document.getElementById("SelectTimeC_Value").disabled=true;
		}else if(document.getElementById("SelectTimeIDA").checked==false){
			document.getElementById("SelectTimeA_ClassName").disabled=true;
			document.getElementById("SelectTimeB_TagName").disabled=true;
			document.getElementById("SelectTimeC_Var").disabled=true;
			document.getElementById("SelectTimeC_Value").disabled=true;
		}
	}
	
	function checkTime2(){	
		if(document.getElementById("SelectTimeIDB").checked==true){
			document.getElementById("SelectTimeA_ClassName").disabled=true;
			document.getElementById("SelectTimeB_TagName").disabled=false;
			document.getElementById("SelectTimeC_Var").disabled=true;
			document.getElementById("SelectTimeC_Value").disabled=true;
		}else if(document.getElementById("SelectTimeIDB").checked==false){
			document.getElementById("SelectTimeA_ClassName").disabled=true;
			document.getElementById("SelectTimeB_TagName").disabled=true;
			document.getElementById("SelectTimeC_Var").disabled=true;
			document.getElementById("SelectTimeC_Value").disabled=true;
		}
	}

	function checkTime3(){		
		if(document.getElementById("SelectTimeIDC").checked==true){
			document.getElementById("SelectTimeA_ClassName").disabled=true;
			document.getElementById("SelectTimeB_TagName").disabled=true;
			document.getElementById("SelectTimeC_Var").disabled=false;
			document.getElementById("SelectTimeC_Value").disabled=false;
		}else if(document.getElementById("SelectTimeIDC").checked==false){
			document.getElementById("SelectTimeA_ClassName").disabled=true;
			document.getElementById("SelectTimeB_TagName").disabled=true;
			document.getElementById("SelectTimeC_Var").disabled=true;
			document.getElementById("SelectTimeC_Value").disabled=true;
		}
	}
	
	function checkContent1(){
		if(document.getElementById("SelectContentIDA").checked==true){
			document.getElementById("SelectContentA_ClassName").disabled=false;
			document.getElementById("SelectContentB_TagName").disabled=true;
			document.getElementById("SelectContentC_Var").disabled=true;
			document.getElementById("SelectContentC_Value").disabled=true;
		}else if(document.getElementById("SelectContentIDA").checked==false){
			document.getElementById("SelectContentA_ClassName").disabled=true;
			document.getElementById("SelectContentB_TagName").disabled=true;
			document.getElementById("SelectContentC_Var").disabled=true;
			document.getElementById("SelectContentC_Value").disabled=true;
		}
	}
	
	function checkContent2(){	
		if(document.getElementById("SelectContentIDB").checked==true){
			document.getElementById("SelectContentA_ClassName").disabled=true;
			document.getElementById("SelectContentB_TagName").disabled=false;
			document.getElementById("SelectContentC_Var").disabled=true;
			document.getElementById("SelectContentC_Value").disabled=true;
		}else if(document.getElementById("SelectContentIDB").checked==false){
			document.getElementById("SelectContentA_ClassName").disabled=true;
			document.getElementById("SelectContentB_TagName").disabled=true;
			document.getElementById("SelectContentC_Var").disabled=true;
			document.getElementById("SelectContentC_Value").disabled=true;
		}
	}

	function checkContent3(){		
		if(document.getElementById("SelectContentIDC").checked==true){
			document.getElementById("SelectContentA_ClassName").disabled=true;
			document.getElementById("SelectContentB_TagName").disabled=true;
			document.getElementById("SelectContentC_Var").disabled=false;
			document.getElementById("SelectContentC_Value").disabled=false;
		}else if(document.getElementById("SelectContentIDC").checked==false){
			document.getElementById("SelectContentA_ClassName").disabled=true;
			document.getElementById("SelectContentB_TagName").disabled=true;
			document.getElementById("SelectContentC_Var").disabled=true;
			document.getElementById("SelectContentC_Value").disabled=true;
		}
	}