<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>   
	<input type="text" name="titleKeyWord" id="titleKeyWord" style="height:25px" placeholder="请输入关键字">
	<input type="button" name="titleSearch" value="标题检索" onclick="return checkSubmit()">
</body>
<%String SearchId=String.valueOf(request.getAttribute("manageSearchId"));%>
<script type="text/javascript">
function checkSubmit(){											
	var titleKeyWord = document.getElementById("titleKeyWord").value;
	var tmp = document.createElement("form");
	title=encodeURI(titleKeyWord);
	title=encodeURI(titleKeyWord);
	var action = "manageNewsTitleRetrieval?titleKeyWord="+title+"&moduleId=<%=SearchId%>";
	tmp.action = action;
	tmp.method = "post";
	tmp.target="_blank";
	document.body.appendChild(tmp);
	tmp.submit();
	return tmp;
	return true;
}
</script>
</html>