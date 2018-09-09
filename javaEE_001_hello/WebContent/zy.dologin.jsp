<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
  <%
  
     request.setCharacterEncoding("utf-8");  //设置获取到的值的字符集
    String uname = request.getParameter("uname");
    String pwd = request.getParameter("pwd");
    
    String[] ins = request.getParameterValues("ins");//获取多个值
  %>
  
  <%= uname %>
  <%= pwd %>
  
  <%= ins  %>
  <hr/>
  <%
    for (String s : ins){
    	out.println(s);
    }
  %>
</body>
</html>