<%@page import="com.yc.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.biz.UserBiz"%>
    
    <%
      request.setCharacterEncoding("UTF-8");
      //给首页赋初值
	 String uname = request.getParameter("uname").trim();
     String upass = request.getParameter("upass").trim();
	 
	/*-------------------------------------------------------------------------------------------------------------  */
	String valcode = request.getParameter("valcode");  //获取用户输入的验证码
	String sRand = (String)session.getAttribute("sRand");  //获取到系统显示在页面上的验证码
	
	//List<String> loginNames =(List<String>) application.getAttribute("loginNames"); 
	
	if(valcode.equals(sRand) == false){   //判断用户输入的验证码和系统显示的验证码是否相同
		out.println("<script>alert('验证码错误！');location.href='login.jsp';</script>");
	}
	/*-------------------------------------------------------------------------------------------------------------  */
	else  if ((uname == null || uname.equals("")) || ( upass == null || upass.equals(""))) {
		out.println("<script>alert('用户名和密码不能为空');location.href='login.jsp';</script>");
	} else {
		UserBiz ub = new UserBiz();
		try {
			//Double r = ub.login(uname, upass);   // 登录查询的另一个方法 ： User r  = ub.login(uname,upass);
			User user  = ub.loginl(uname,upass);
			if (user !=  null) {                                    //if (r>0)
				 
				session.setAttribute("user", user);//权限
				 
			    response.sendRedirect("index.jsp");
			} else {
				out.println("<script>alert('登录失败');location.href='login.jsp';</script>");
			}
		} catch (Exception ex) {
			out.println("<script>alert('登录失败," + ex.getMessage() + "');location.href='login.jsp';</script>");
		}
	}
      
      
    %>