<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
  
  
<DIV class="t" style="MARGIN-TOP: 15px" align="center">
	<FORM name="loginForm" action="admin.action?op=login" method="post">
		<br/> <p>用户名 &nbsp;<INPUT class="input" tabIndex="1"  type="text"    maxLength="20" size="35" name="username"> <p/>
		                    密　码 &nbsp;<INPUT class="input" tabIndex="2"  type="password"  maxLength="20" size="40" name="userpassword"> 
	    <br/> <p>验证码：&nbsp; &nbsp;<input type="text" name="valcode" />
	                             <img id="img" 	src="image.jsp" onclick="refreshValcode()" > 
	               <p/>
		<br/><INPUT class="btn"  tabIndex="6"  type="submit" value="登 录">
	</FORM>
</DIV>
</body>
</html>

 