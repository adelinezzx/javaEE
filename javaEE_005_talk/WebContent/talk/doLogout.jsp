<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String uname = (String) session.getAttribute("uname");

	List<String> loginNames = new ArrayList<String>();

	if (application.getAttribute("loginNames") != null) {
		loginNames = (List<String>) application
				.getAttribute("loginNames");

		loginNames.remove(uname);
	}
	application.setAttribute("loginNames", loginNames);
	session.removeAttribute("uname");
	session.invalidate();

	Cookie c = new Cookie("uname", "");
	Cookie c2 = new Cookie("upwd", "");
	response.addCookie(c);
	response.addCookie(c2);

	response.sendRedirect("../login.jsp");
%>