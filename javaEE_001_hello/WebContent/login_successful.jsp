<%@page import="java.util.Scanner"%>
<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录成功</title>
</head>
<body>
	<h1>登录成功了</h1>
	<%!public String getStr() {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入一个名字：");
		return input.next();
	}

	public String name = "admin";
	%>
	
	<%
		for (int i = 0; i < 10; i++) {
			out.println("<h1>login successful</h1>"); 
			//out对象直接使用，因为在jsp转换成Java文件的时候，在Java文件的servers方法中，
			//已经创建了out对象，所以可以直接使用。像这样的对象叫jsp内置对象，共9个：
			//request ,out ,responce,config, application,pageContext ,exception,session,page
		}
	%>
     <!--调用变量表达式-->
     <h1><%= name %></h1>
 
     <!--调用方法表达式 -->
     <h3><%= getStr() %></h3>
</body>
</html>