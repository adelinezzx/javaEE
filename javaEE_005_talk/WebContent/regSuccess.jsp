<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="checkRights.jsp"%>
<!-- 包含 ：必须保证包含的两个jsp文件（包含和被包含两个jsp文件）的编码集一致，不一致就会导致编号为500的错误-->

<%@ include file="header.jsp"%>

恭喜您
<%=(String) session.getAttribute("uname")%>
注册成功！

<a href="doLogout.jsp">安全退出</a>

<br>
<a href="account.jsp">查看账户、</a>


<%@ include file="bottom.jsp"%>