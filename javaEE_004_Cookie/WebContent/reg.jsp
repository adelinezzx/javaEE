<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	用户注册：

	<form action="doreg.jsp " method="post">
		<!--用户get传递方法的问题： 1 插入数据时数据可能为乱码 
	        2.密码和用户名在地址栏显示，不安全，3，如果较长的信息插入时，如果插入的数据超过了浏览器设置的长度限制，就会导致数据丢失-->
		用户名： <input type="text" name="uname" /><br>
		 密码： <input	type="password" name="upwd" /><br>
		 确认密码： <input type="submit" value="登录" /><br>
	</form>
</body>
</html>