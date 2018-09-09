package com.zzx.j2ee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzx.j2ee.entity.User;
import com.zzx.j2ee.service.UserService;
import com.zzx.j2ee.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {

	/**
	 * 反序列化编号
	 */
	private static final long serialVersionUID = -8699755118181672984L;

	private UserService us;

	@Override
	// 初始化
	public void init(ServletConfig config) throws ServletException {
		us = new UserServiceImpl(); // 在初始化中将UserService对象实例化
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("get: i am coming");
	}

	@Override
	// post 请求的方法
	// HttpServletRequest 请求信息处理接口
	// HttpServletResponse 是相应信息处理接口
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("post: i am coming");
		// -----------------------请求处理---------------------
		// 设置请求数据的编码集
		req.setCharacterEncoding("utf-8");

		// 取到请求参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// System.out.println(String.format("用户名：%s -- 密码：%s" ,name,password));

		// 取到请求资源
		String uri = req.getRequestURI();
		String url = req.getRequestURL().toString();
		// System.out.println(String.format("URI：%s -- URL：%s" ,uri,url));

		// 请求者的IP地址
		String host = req.getRemoteHost();
		String local = req.getRemoteAddr();
		// System.out.println(String.format("请求者ip：%s -- 请求者本地：%s"
		// ,host,local));
		// -----------------------相应处理---------------------

		/*
		 * if(name != null && "admin" == name.intern() && "aa"
		 * ==password.intern() && password != null){
		 * resp.sendRedirect("login_successful.html"); //重定向，页面跳转 }else{
		 * resp.sendRedirect("login_fail.html"); //重定向，页面跳转 }
		 */

		// 响应自己写的HTML数据
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("charset = utf-8");
		PrintWriter out = resp.getWriter();
		/*
		 * if(name != null && "admin" == name.intern() && "aa"
		 * ==password.intern() && password != null){
		 * out.println("<h1>login successful</h1>"); }else{
		 * out.println("<h1>login fail</h1>"); }
		 */

		// 取出登录数据 ，判断数据是否符合
		User user = us.login(new User(username, password));
		if (user != null) {
			resp.sendRedirect("login_successful.jsp");
		} else {
			// <!DOCTYPE html><html><head><meta
			// charset='UTF-8'><script>alert('用户名或密码错误');
			// location.href = 'login.html';</script></head></html>
			out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><script>alert('用户名或密码错误!!!');" + " location.href = 'login.html'</script></head></html>");

			out.flush();
			out.close();
		}
	}

}
