<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String username=request.getParameter("username");request.setAttribute("username",username);%>
<%String password=request.getParameter("password");request.setAttribute("password",password);%>
<link type="text/css" rel="stylesheet" href="../css/log.css">
<title>油气网络信息动态采集系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
   
<script>
var browserName=navigator.appName;
if (browserName=="Netscape")
{
function closeme()
{
window.open('','_parent','');
window.close();
}
}
else
{
if (browserName=="Microsoft Internet Explorer")
{
function closynoshowsme()
{
window.opener = "whocares";
window.close();
}
}
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!-- ImageReady Slices (login.psd) -->
<table id="Table_01" width="588" height="568" border="0" cellpadding="0" cellspacing="0"  align="center">
	<tr>
		<td>
			<img src="../images/logincp_01.jpg" width="117" height="49" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_02.jpg" width="135" height="49" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_03.jpg" width="128" height="49" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_04.jpg" width="122" height="49" alt=""></td>
		<td>
			<img src="../images/logincp_05.jpg" width="86" height="49" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/logincp_06.jpg" width="117" height="49" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_07.jpg" width="135" height="49" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_08.jpg" width="128" height="49" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_09.jpg" width="122" height="49" alt=""></td>
		<td>
			<img src="../images/logincp_10.jpg" width="86" height="49" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/logincp_11.jpg" width="117" height="49" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_12.jpg" width="135" height="49" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_13.jpg" width="128" height="49" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_14.jpg" width="122" height="49" alt=""></td>
		<td>
			<img src="../images/logincp_15.jpg" width="86" height="49" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/logincp_16.jpg" width="117" height="49" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_17.jpg" width="135" height="49" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_18.jpg" width="128" height="49" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_19.jpg" width="122" height="49" alt=""></td>
		<td>
			<img src="../images/logincp_20.jpg" width="86" height="49" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/logincp_21.jpg" width="117" height="60" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_22.jpg" width="135" height="60" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_23.jpg" width="128" height="60" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_24.jpg" width="122" height="60" alt=""></td>
		<td>
			<img src="../images/logincp_25.jpg" width="86" height="60" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/logincp_26.jpg" width="117" height="43" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_27.jpg" width="98" height="43" alt=""></td>
		<td colspan="4">
		<form id="form0" name="login"   width="94" height="35" action="loginAction.action" method="post">
		<span style="text-align:center" >
        <input name="username" id="username" type="text" placeholder="请输入用户名"  style="position:relative; "  size="17" onkeypress="check_values();">
		</span></td>
		<td colspan="2">
			<img src="../images/logincp_29.jpg" width="105" height="43" alt=""></td>
		<td>
			<img src="../images/logincp_30.jpg" width="86" height="43" alt=""></td>
	</tr>
	<tr>
		<td colspan="10">
			<img src="../images/logincp_31.jpg" width="588" height="11" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/logincp_32.jpg" width="117" height="43" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_33.jpg" width="98" height="43" alt=""></td>
		<td colspan="4">
		<span style="text-align:center">
      <input name="password" type="password" id="password" style="position:relative; " onkeypress="check_values();"   size="17" placeholder="请输入密码" />
    </span></td>
		<td colspan="2">
			<img src="../images/logincp_35.jpg" width="105" height="43" alt=""></td>
		<td>
			<img src="../images/logincp_36.jpg" width="86" height="43" alt=""></td>
	</tr>
	<tr>
		<td colspan="10">
			<img src="../images/logincp_138.jpg" width="588" height="25" alt=""></td>
	</tr>
	<tr>
		<td colspan="2">
			<img src="../images/logincp_38.jpg" width="158" height="35" alt=""></td>
		<td colspan="2">
		
		
            <input name="submit" type="image" value="登录系统" src="images/logincp_39.jpg" />
            </form>
			</td>
		<td>
			<img src="../images/logincp_40.jpg" width="58" height="35" alt=""></td>
		<td colspan="3">
		<form id="form2" name="exit"  width="95" height="35">
		
                <input name="exit" type="image" value="退出窗口" src="images/logincp_41.jpg" title="关闭窗口" onclick="closeme();" />
            </form>  
			</td>
		<td colspan="2">
			<img src="../images/logincp_42.jpg" width="183" height="35" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/logincp_43.jpg" width="117" height="60" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_44.jpg" width="135" height="60" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_45.jpg" width="128" height="60" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_46.jpg" width="122" height="60" alt=""></td>
		<td>
			<img src="../images/logincp_47.jpg" width="86" height="60" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/logincp_48.jpg" width="117" height="60" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_49.jpg" width="135" height="60" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_50.jpg" width="128" height="60" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_51.jpg" width="122" height="60" alt=""></td>
		<td>
			<img src="../images/logincp_52.jpg" width="86" height="60" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/logincp_53.jpg" width="117" height="34" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_54.jpg" width="135" height="34" alt=""></td>
		<td colspan="2">
			<img src="../images/logincp_55.jpg" width="128" height="34" alt=""></td>
		<td colspan="3">
			<img src="../images/logincp_56.jpg" width="122" height="34" alt=""></td>
		<td>
			<img src="../images/logincp_57.jpg" width="86" height="34" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="../images/spacer.gif" width="117" height="1" alt=""></td>
		<td>
			<img src="../images/spacer.gif" width="41" height="1" alt=""></td>
		<td>
			<img src="../images/spacer.gif" width="57" height="1" alt=""></td>
		<td>
			<img src="../images/spacer.gif" width="37" height="1" alt=""></td>
		<td>
			<img src="../images/spacer.gif" width="58" height="1" alt=""></td>
		<td>
			<img src="../images/spacer.gif" width="70" height="1" alt=""></td>
		<td>
			<img src="../images/spacer.gif" width="17" height="1" alt=""></td>
		<td>
			<img src="../images/spacer.gif" width="8" height="1" alt=""></td>
		<td>
			<img src="../images/spacer.gif" width="97" height="1" alt=""></td>
		<td>
			<img src="../images/spacer.gif" width="86" height="1" alt=""></td>
	</tr>
</table>
<!-- End ImageReady Slices -->
</body>
</html>