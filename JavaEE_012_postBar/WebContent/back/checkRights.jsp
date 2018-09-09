<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
       
       if(  session.getAttribute("user")  == null ){
    	 
    	    out.print("<script>alert('您还没有登录！请先登录！'); location.href='../login.jsp';</script>");
       }
    
    %>
 