<%@page import="java.util.*,javax.servlet.http.Cookie"%>
<%@page import="com.yc.mysql.UserBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//获取参数 
	request.setCharacterEncoding("utf-8");
	String uname = request.getParameter("uname").trim();
	String upwd = request.getParameter("upwd").trim();
	String re = request.getParameter("re");

	if ((uname == null || uname.equals("")) || (upwd == null || upwd.equals(""))) {
		out.println("<script>alert('用户名和密码不能为空');location.href='login.jsp';</script>");
	} else {
		UserBiz ub = new UserBiz();
		try {
			double r = ub.login(uname, upwd);
			if (r > 0) {
				//取出application数据 池中的所有人
				//建立一个集合，将所有的用户存到集合中
				List<String> loginNames = new ArrayList<String>();
				//如果用户名不为空，则将用户名存到集合中
				if(application.getAttribute("loginNames") != null){
					loginNames =  (List<String>)   application.getAttribute("loginNames") ;
				   //遍历集合中的用户名，如果已经存在，说明已经登录
					for(    String ln : loginNames   ){
						if(   ln.equals(uname)     ){
							out.println("<script>alert('登录失败,您已经登录，不能再次登录');location.href='login.jsp';</script>");
							return;
						}
					}
				}
				//TODO: 记住信息 利用cookie来记住密码
				if ("1".equals(re)) {
					Cookie c = new Cookie("uname", uname);
					Cookie c2 = new Cookie("upwd", upwd);
					response.addCookie(c);
					response.addCookie(c2);
				}
				
				session.setAttribute("uname", uname);//权限
				//将所有登录的用户名加入application数据池 和 集合
				loginNames.add(uname);
				application.setAttribute("loginNames", loginNames);
				
				
				//用转发跳转页面,观察转发的重复提交问题
				//request.getRequestDispatcher("talk/talk.jsp").forward(request, response);
				//重定向方法
				response.sendRedirect("talk/talk.jsp ");
			} else {
				out.println("<script>alert('登录失败');location.href='login.jsp';</script>");
			}
			//request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
		} catch (Exception ex) {
			out.println("<script>alert('登录失败," + ex.getMessage() + "');location.href='login.jsp';</script>");
		}
	}

	//业务操作（判断用户名和密码）
	// if("a".equals(uname) && "a".equals(upwd)){
	//跳转页面
	//重定向： 两次请求
	//response.sendRedirect("http://www.taobao.com");
	//1.地址栏（dologin.jsp /welcome.jsp）      welcome.jsp
	//2.是否可以（request.getParameter("uname")）    null 
	//3.是否可以跳转到外部站点（www.taobao.com）     可以

	//转发：只请求一次
	//转发的语法： request.getRequestDispatcher("地址").forward(request, response);
	//request.getRequestDispatcher("welcome.jsp").forward(request, response);  //http://www.taobao.com 
	//1.地址栏（dologin.jsp /welcome.jsp）      dologin.jsp
	//2.是否可以（request.getParameter("uname")）    a 
	//3.是否可以跳转到外部站点（www.taobao.com）    不 可以

	//利用转发可以访问 web-inf下的资源
	//request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);

	//重定向  地址栏重写
	//  out.println("<script>alert('登录成功！');location.href='welcome.jsp';</script>");
	// }else{
	//  response.sendRedirect("login.jsp");
	//}
%>