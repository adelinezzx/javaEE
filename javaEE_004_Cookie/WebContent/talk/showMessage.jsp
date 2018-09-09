<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 

    //建立一个存储信息的集合
	List<String> messages = new ArrayList<String>();
    //如果数据池中有 message 数据 ，则将message的数据都存到 集合 中
	if (application.getAttribute("messages") != null) {
		 messages =(List<String>)  application.getAttribute("messages");
	}
    //将集合中的messages数据显示输出 
	for(int i = 0 ; i<messages.size() ;i++){
		out.println(messages.get(i) + "<br/>");
	}
%>


