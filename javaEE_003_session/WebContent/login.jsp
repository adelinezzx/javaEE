<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<style type="text/css">
#loginform {
	width: 500px;
	height: 300px;
	text-align: center;
	/* margin-left:500px; */
	border: 1px solid;
	margin-left: 300px;
	padding-top: 50px;
}

#loginform p table {
	color: red;
}
</style>
</head>
<body>
	<form id="loginform" action="login" method="post">
		<p>
		<table><%=session.getAttribute("errorMsg") == null ? " " : session.getAttribute("errorMsg")%></table>
		</p>
		<%
			session.removeAttribute("errorMsg"); //根据key 移除session的值
		%>
		<br />
		<p>
			<input type="text" placeholder="请输入用户名" / name="username">
		</p>
		<br />
		<p>
			<input type="password" placeholder="请输入密码" name="password" />
		</p>
		<br />
		<p>
			<button>登录</button>
		</p>
	</form>
</body>
</html>