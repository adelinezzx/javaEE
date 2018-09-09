<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>登录成功</title>
</head>
<body>
	<%
		//从session中以map类型的格式（key，value）取出一个值 
		String name = (String) session.getAttribute("loginUsername");
	    if(name == null ){
	    	session.setAttribute("errorMsg", "请先登录，再进行操作");
	    	response.sendRedirect("login.jsp");
	    }
	%>
	<h3>总访问次数：<%= application.getAttribute("count") %>
	</h3>
	<h3><%=name%>登录成功了
	</h3>
</body>
</html>