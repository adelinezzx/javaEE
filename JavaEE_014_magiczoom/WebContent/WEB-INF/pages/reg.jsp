<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
 
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/styles.css" type="text/css" />

<%@ include file ="header.jsp" %>
  
<script language="javascript">

	function checkUserInfo() {
	 if(document.loginForm.username.value==""){
	    alert("用户名不能为空");
	    return false;
	 }
	 if(document.loginForm.pwd.value==""){
	    alert("密码不能为空");
	    return false;
	 }
	 
	 if(document.loginForm.valcode.value==""){
		    alert("验证码不能为空");
		    return false;
		 }
	 
	 return true ;
	}
	
</script>

 
<BODY leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
			<!-- 用postForm表单向result.jsp用GET请求提交数据，注意method属性和action属性的设置
				username参数用来保存用户名 
				pwd参数用来保存密码
			-->
			<form method="POST" name="loginForm" onSubmit="return checkUserInfo()" action="resuser.action?op=reg">
			     <input type="hidden" name="op" value="reg"  >
			<table width="100%" border="0">
				<tr>
					<td width="15%">&nbsp;</td>
					<td width="12%">&nbsp;</td>
					<td width="29%">&nbsp;</td>
					<td width="44%">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">用户名：</td>
					<td valign="top">
					         <input type="text" name="username" size="19" 	class="input"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">密&nbsp;&nbsp;码：</td>
					<td valign="top">
					             <input type="password" name="pwd" size="20" 		class="input"></td>
					<td>&nbsp;</td>
				</tr>
					<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">email：</td>
					<td valign="top">
					             <input type="text" name="email" size="20" 		class="input"></td>
					<td>&nbsp;</td>
				</tr>
					<tr>
					<td>&nbsp;</td>
					<td valign="middle" align="center">验证码：</td>
					<td valign="top">
					            <input type="text" name="valcode" size="20" 	class="input"></td>
					<td>   <img id="img" 	src="image.jsp" onclick="checkVilidatacode(this)" title="验证码"></td>
			        <td>&nbsp;</td>
				  </tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2" align="center"><input type="submit"
						name="Submit" value="登录"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td height="33">&nbsp;${msg }</td>
					 
				</tr>
			</table>
			</form>
	 
 
 </BODY>
<%@ include file="bottom.jsp" %>