<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script type="text/javascript">
	//刷新验证码
	function refreshValcode() {
		document.getElementById("img").src = "image.jsp?" + new Date();
	}
</script>

<%@ include file="header.jsp"%>


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
<div id="login">
	<a id ="logintitle">登录</a>
	<form action="dologin.jsp" method="post">
		<p>用户名：&nbsp&nbsp&nbsp<input type="text" name="uname" value="<%=uname%>" /><p/>
		<p> 密&nbsp&nbsp&nbsp码：&nbsp&nbsp&nbsp<input type="password" name="upwd" value="<%=upwd%>" /><p/>
		<!-- ---------------------------------------------------------------------------------------------- -->
		<p>验证码：&nbsp&nbsp<input type="text" name="valcode" /> <img id="img"
			src="image.jsp" onclick="refreshValcode()"> <p/>
		<!-- ---------------------------------------------------------------------------------------------- -->
		<input type="checkbox" name="re" value="1"
			<%if (uname.equals("") == false) {
				out.println("checked");
			}%> />记住密码
		<input type="submit" value="登录" /><br />
	</form>
</div>



<%@ include file="bottom.jsp"%>