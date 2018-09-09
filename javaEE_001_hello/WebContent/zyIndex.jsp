<%@page import="java.util.Random"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>jsp页面元素</title>
</head>
<style>
.red {
	color: red;
}
</style>
<body>

	<%
		//小脚本
		out.print("hello world ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		out.println("时间：<span class = 'red'>" + sdf.format(new Date()) + "</span>");
	%>
	<hr>
	时间：
	<span class="red"> <%=sdf.format(new Date())%>
	</span>
	<%!//声明
	int count = 0; //全局变量

	//私有方法，在别的页面无法使用
	//对象名 . 方法名（）
	int GetRamdonNumber() {
		Random r = new Random();
		return r.nextInt();
	}%>
	
	<%
	  count++ ;
	%>
	
	<hr>
	访问次数：<%=count  %>
	<hr>
	随机数：<%= GetRamdonNumber() %>
	
</body>
</html>