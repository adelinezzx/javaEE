<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//取出message，存到list中，再存到application中，再跳到talk.jsp中
	List<String> messages = new ArrayList<String>();
  //如果数据池中的messages不为空  则将数据池中的数据都存到list集合中
	if (application.getAttribute("messages") != null) {
		messages = (List<String>) application.getAttribute("messages");
	}
	//取出用户输入的 信息内容 
	String message = request.getParameter("message");

	//发信息的人名
	String uname = (String) session.getAttribute("uname");
	//拼接  信息的内容
	String msg = uname + ":<br/>" + message + "<br/><span>"+ new Date() + "</span><hr/>";  
	//将拼接的 内容 存到集合中
	messages.add(msg);  
	//再存到application中
	application.setAttribute("messages", messages); 
	
	response.sendRedirect("talk.jsp");//跳转

%>
