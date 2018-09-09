<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   sessionid :<%= session.getId()  %><br>
   <a href ="sessionId2.jsp"> sessionid2</a>
   <!--session : 同一个浏览器上的sessionid值相同，体内通浏览器上的浏览器不同  ，session一般在
               服务器第一次发生请求的时候创建。需要注意只有访问JSP、Servlet等程序时才会创建Session，
               只访问HTML、IMAGE等静态资源并不会创建Session，可调用request.getSession(true)强制生成Session。
       
       session过期的几个情况 ： 1，session跟着Cookie走，如果Cookie的值被清除，则session的值也将被清除
       2， 请求超时，Cookie的值是有时间限定的，如果服务器的请求超过一定的时间，则session过期 ，
                   从session不活动的时候开始计算，如果session一直活动，session就总不会过期。
       
    -->
</body>
</html>