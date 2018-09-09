<%@page import="com.yc.mysql.UserBiz"%>
<%@page import="com.yc.mysql.DBHelper"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//获取参数 
		request.setCharacterEncoding("utf-8");
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");

		System.out.println("向数据库中插入了：" + uname + ",密码 ：" + upwd );

		UserBiz ub = new UserBiz();
		try {
			int r = ub.reg(uname, upwd);
			if (r > 0) {
				//用转发跳转页面 观察 转发的重复问题提交
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				out.println("<script>alert('注册失败！');location.href = 'reg.jsp';</script>");
			}
		} catch (Exception e) {
			out.println("<script>alert('uname is already exist ！');location.href = 'reg.jsp';</script>");
		}
		//重定向
		//response.sendRedirect("regSuccess.jsp");
	%>

</body>
</html>