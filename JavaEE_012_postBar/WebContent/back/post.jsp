<%@page import="com.yc.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 

<%@ include file="checkRights.jsp"   %>

<%@ include file="../head.jsp"%>
 
 
<center>
	<%
		String uid = "";
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			uid = user.getUid() + "";
		}
	%>

	<form action="dopost.jsp" method="post" enctype="multipart/form-data">
		<input type="hidden" name="uid" value="<%=uid%>" /> 内容:<br />
		<textarea rows="5" cols="20" name="contents"></textarea>
		<br /> 贴图:<input type="file" name="pic" /> <br /> <input type="submit" />
	</form>

	<center>Adeline</center>
</center>