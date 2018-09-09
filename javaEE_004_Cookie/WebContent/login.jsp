<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file = "header.jsp" %>
	<%
		String uname = "";
		String upwd = "";
		//取cookie 
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals("uname")) {
					uname = c.getValue();
				}
				if (c.getName().equals("upwd")) {
					upwd = c.getValue();
				}
			}
		}
	%>
	登录
	<form action="dologin.jsp" method="post">
		用户名:<input type="text" name="uname" value="<%=uname%>" /><br />
		密码:<input type="password" name="upwd" value="<%=upwd%>" /><br /> <input
			type="checkbox" name="re" value="1"
			<%if (uname.equals("") == false) {
				out.println("checked");
			}%>  />记住密码
		<input type="submit" value="登录" /><br />
	</form>
	

<%@ include file = "bottom.jsp" %>