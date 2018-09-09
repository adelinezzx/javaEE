<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
    //建立存储用户名的集合list 
	List<String> loginNames = new ArrayList<String>();
   //如果数据池中的loginNames 不为空， 则将用户名 存到集合中， 再输出显示
	if (application.getAttribute("loginNames") != null) {
		loginNames = (List<String>) application
				.getAttribute("loginNames");
		out.println("<br /><br />当前在线人数：" + getNumber(loginNames.size() )+ "<br / >");
		out.println("<br /><br /> ");

		for (String ln : loginNames) {
			out.println(ln + " <br/>");
		}
	}
%>
      
<%!
     // 将在内线人数以图片的显示在div中
        String getNumber(int  size) {
		String s = size + ""; //将图片转化为字符串的形式
		StringBuffer sb = new StringBuffer();   //建立缓冲区
		//得到当前在下人数得每个数   再转化成图片的形式
		for (int i = 0; i < s.length(); i++) {
			String ch = s.substring(i, i + 1);  
			sb.append("   <img src = 'images/" + ch + ".gif ' />    ");
		}
		return sb.toString();
	}%>
<!-- <img src="images/3.gif" />
<img src="images/0.gif" /> -->