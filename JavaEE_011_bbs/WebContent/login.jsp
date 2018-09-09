<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<script type="text/javascript">
	function changeImage() {
		document.getElementById("image01").src = "image.jsp?" + new Date();
	}
</script>



<!--      用户登录表单        -->
<DIV class="t" style="MARGIN-TOP: 15px" align="center">

	<FORM name="loginForm" action="user.action" method="post">
		<input type="hidden" name="op" value="login" /> <br />用户名 &nbsp; <INPUT
			class="input" name="uname"> <br />
		<p>
			密 码 &nbsp;<INPUT class="input" type="password" name="upass">
		</p>
		验证码： <input type="text" name="val_code" /> 
		<img id="image01" src="image.jsp" /> 
		<a href="javascript:void(0)" 	onclick="changeImage()">看不清</a> <br /> 
		<INPUT class="btn" tabIndex="6" type="submit" value="登 录">
	</FORM>

</DIV>

<%@ include file="bottom.jsp"%>