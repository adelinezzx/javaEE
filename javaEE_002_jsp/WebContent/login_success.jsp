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
		/*默认请求时 request 会携带浏览器的当前网站的Cookie  到  服务器 参加请求处理   */
		//String name = (String)request.getAttribute("loginUsername");
		/* String name = null;
			Cookie[] cs = request.getCookies();
			for(Cookie c : cs){
			 if(c.getName().intern() == "loginUsername"){
				  name = c.getValue();
			 }
			} */
			
			//从session中以map类型的格式（key，value）取出一个值 
			String name = (String)session.getAttribute("loginUsername"); 
	%>
	<h3><%=name%>登录成功了
	</h3>
</body>
</html>