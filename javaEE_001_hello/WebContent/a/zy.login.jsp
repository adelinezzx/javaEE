<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //getContextPath : 项目的上下文  javaEE_001_hello
  String path = request.getContextPath() ;
 //                   htttp   ://                   localhost  :         8080 /javaEE_001_hello/
  String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path + "/";
   //    http://localhost:8080/javaEE_001_hello/
%>

<html>
<head>
<base href="<%= basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
http://localhost:8080/javaEE_001_hello/ 
    <img src="1.png" width = "200px " height = "200px"/>

    
get方式：
    <a href=" zy.dologin2.jsp?uname=zzx&pwd=a">登录</a>
    
    <hr/>
    
    <form action="zy.dologin02.jsp"  method="get">
              用户名：<input type="text" name ="uname"/>  <br/>
              密    码：<input type="password" name ="pwd"/>   <br/>
              
              兴趣：<input type ="checkbox" name ="ins"  value = "阅读"/>阅读 &nbsp&nbsp
             <input type = "checkbox" name = "ins" value = "游戏"> 游戏<br/>
            <input type ="submit"/>   
    </form>
    <hr/>
    
   登录：
    <a href=" zy.dologin2.jsp?uname=zzx&pwd=a">登录</a>
    
    <hr/>
    
    <form action="zy.dologin03.jsp"  method="get">
              用户名：<input type="text" name ="uname"/>  <br/>
              密    码：<input type="password" name ="pwd"/>   <br/>
            <input type ="submit"/>   
    </form>
    <hr/>
</body>
</html>