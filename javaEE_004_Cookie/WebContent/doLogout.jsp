<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
  session.removeAttribute("uname");

  session.invalidate();
  
   Cookie c = new Cookie("uname", "");
	Cookie c2 = new Cookie("upwd", "");
	response.addCookie(c);
	response.addCookie(c2);
	
	
  response.sendRedirect("login.jsp");
%>