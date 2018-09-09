<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
	 
	 <%
	   String path = request.getContextPath();
	   String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
	   session.setAttribute("basePath", basePath);
	 %>
 
    <base  href="${basePath }"/>
 
	<%-- <%
	if( )
	%> --%>
<script type="text/javascript" src="js/jquery-1.11.1.js"> </script>
 
<meta charset="utf-8">
<TITLE>论坛--登录</TITLE>
 
<Link rel="stylesheet" type="text/css" href="style/style.css" />
 
 
 <script type="text/javascript">
       //发一个请求到后台，user.action ?op=islogin 得到json对象 code == 1 : 表示用户已经登录，code==0 表示没有登录
       //用jQuery来 发送ajax请求  
     $().ready(function(){
    	   $.ajax({
    		   url:"user.action?op=islogin",
    		   type:"POST",
    		   dataType:"JSON",
    		   success:function(data){
    			   if(data.code==1){
    				   $("#loginresult").html("欢迎您 &nbsp; &nbsp;" + data.obj.uname + "&nbsp;| &nbsp; <A href='javascript:logout()'>退出 </A>");
    			   }
    		   }
    	   });
       });
       
       function logout(){
    	   $.ajax({
    		   url:"user.action?op=logout",
    		   type:"POST",
    		   dataType:"JSON",
    		   success:function(data){
    			   if(data.code==1){
    				   $("#loginresult").html("您尚未 <a href='login.jsp'>登录</a> &nbsp;| &nbsp; <A href='reg.jsp'>注册</A>");
    			   }
    		   }
    	   });
       }
 </script>
</HEAD>

<BODY>
	<DIV>
		<IMG src="image/logo.gif">
	</DIV>
	<!--      用户信息、登录、注册        -->

	<DIV class="h" id="loginresult" >
		您尚未 <a href="login.jsp">登录</a> &nbsp;| &nbsp; <A href="reg.jsp">注册</A>
		|
		 
	</DIV>
	<BR />