<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> -->
<%@ include file="header.jsp"%>
<div id="login">
	<p>用户注册：<p/>

	<form action="doreg.jsp " method="post">
		<!--用户get传递方法的问题： 1 插入数据时数据可能为乱码 
	        2.密码和用户名在地址栏显示，不安全，3，如果较长的信息插入时，如果插入的数据超过了浏览器设置的长度限制，就会导致数据丢失-->
		<p>用户名：&nbsp&nbsp&nbsp&nbsp<input type="text" name="uname" /><p/>
		 <p>密&nbsp&nbsp&nbsp&nbsp码： &nbsp&nbsp<input	type="password" name="upwd" /><p/>
		<!--  <p>确认密码：<input	type="password" name="upwd" /><p/> -->
		 <p> &nbsp&nbsp&nbsp&nbsp<input type="submit" value="注册" /><p/>
	</form>
	</div>
<%@ include file="bottom.jsp"%>