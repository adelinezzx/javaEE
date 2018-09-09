<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!doctype html>
<html>
<head>
<meta charset="utf-8">
<TITLE>论坛--登录</TITLE>
<Link rel="stylesheet" type="text/css" href="style/style.css"/>

<script type="text/javascript">
function refreshValcode(){
	//清除缓存  保证每一个验证码都是新的
	document.getElementById("img").src='image.jsp?' + new Date() ;
	
}
</script>
</HEAD>

<BODY>
<DIV>
	<IMG src="image/logo.gif">
</DIV>
<!--      用户信息、登录、注册        -->

<%@ include file="head.jsp" %>


<BR/>
<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>
</DIV>
<!--      用户登录表单        -->
<DIV class="t" style="MARGIN-TOP: 15px" align="center">
	<FORM name="loginForm" action="dologin.jsp" method="post">
		<br/> <p>用户名 &nbsp;<INPUT class="input" tabIndex="1"  type="text"    maxLength="20" size="35" name="uname"> <p/>
		                  密　码 &nbsp;<INPUT class="input" tabIndex="2"  type="password"  maxLength="20" size="40" name="upass"> 
	    <br/> <p>验证码：&nbsp &nbsp<input type="text" name="valcode" /> <img id="img"
			src="image.jsp" onclick="refreshValcode()"> <p/>
		<br/><INPUT class="btn"  tabIndex="6"  type="submit" value="登 录">
	</FORM>
</DIV>
<!--      声明        -->
<BR/>
<CENTER class="gray">Adeline</CENTER>
</BODY>
</HTML>
 