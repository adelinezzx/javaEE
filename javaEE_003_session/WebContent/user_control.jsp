<%@page import="com.zzx.j2ee.service.impl.UserServiceImpl"%>
<%@page import="com.zzx.j2ee.service.UserService"%>
<%@page import="com.zzx.j2ee.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	request.setCharacterEncoding("utf-8");
	UserService us = new UserServiceImpl();
	// 取到请求参数
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	// System.out.println(String.format("用户名：%s -- 密码：%s" ,name,password));

	// 取到请求资源
	String uri = request.getRequestURI();
	String url = request.getRequestURL().toString();
	// System.out.println(String.format("URI：%s -- URL：%s" ,uri,url));

	// 请求者的IP地址
	String host = request.getRemoteHost();
	String local = request.getRemoteAddr();
	// -----------------------相应处理---------------------

	// 响应自己写的HTML数据
	response.setCharacterEncoding("utf-8");
	response.setContentType("charset = utf-8");

	// 取出登录数据 ，判断数据是否符合
	User user = us.login(new User(username, password));
	if (user != null) {
		response.sendRedirect("login_successful.jsp");
	} else {
		out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><script>alert('用户名或密码错误!!!');" + " location.href = 'login.html'</script></head></html>");

		out.flush();
		out.close();
	}
%>