<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="messagesList">
    <% 
     //该方法是用list集合的形式在页面上， 当用ajax方法改进后不再使用该方法
      //建立一个存储信息的集合
	List<String> messages = new ArrayList<String>();
    //如果数据池中有 message 数据 ，则将message的数据都存到 集合 中
	if (application.getAttribute("messages") != null) {
		 messages =(List<String>)  application.getAttribute("messages");
	}
    //将集合中的messages数据显示输出 
	for(int i = 0 ; i<messages.size() ;i++){
		out.println( messages.get(i)  + "<br/> " );
	} 
   %>
   
   
</div>



