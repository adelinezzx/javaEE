<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <% 
  Context cxt = new InitialContext();  //创建容器的上下对象
  String hello = (String) cxt.lookupLink("java:comp/env/hello");
  System.out.println(hello);
  
  DataSource datasource = (DataSource)cxt.lookupLink("java:comp/env/jdbc/mysource");
  if(datasource.getConnection() == null){
	  %>
	  <h1>数据库连接失败！！！</h1>
	  <% 
  }else{
	  %>
	  <h1>数据库连接成功....</h1>
	  <% 
  }
%>
<h1>取到hello的值：<%= hello %></h1>