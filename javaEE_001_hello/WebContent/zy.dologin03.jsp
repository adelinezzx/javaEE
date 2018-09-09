<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
   request.setCharacterEncoding("utf-8");  //设置获取到的值的字符集
   String uname = request.getParameter("uname");
   String pwd = request.getParameter("pwd");
   
   //登录判断
   if("a".equals(uname) && "a".equals(pwd)){
	   out.println("<script>alert('登录成功！'); document.write('欢迎您："+ uname +" ' )</script>");
   }else{
	   out.println("<script>alert('登录失败！用户名或者密码错误！'); location.href= 'a/zy.login.jsp'</script>");
   }
%>
