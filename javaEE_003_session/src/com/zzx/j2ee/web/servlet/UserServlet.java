package com.zzx.j2ee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zzx.j2ee.entity.User;
import com.zzx.j2ee.service.UserService;
import com.zzx.j2ee.service.impl.UserServiceImpl;

public class UserServlet extends BaseServlet {

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("get: i am coming");
	}

	@Override
	// post 请求的方法
	// HttpServletRequest 请求信息处理接口
	// HttpServletResponse 是相应信息处理接口
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// -----------------------请求处理---------------------
		// 设置请求数据的编码集
		request.setCharacterEncoding("utf-8");

		// 取到请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// -----------------------相应处理---------------------

		// 响应自己写的HTML数据
		response.setCharacterEncoding("utf-8");
		response.setContentType("charset = utf-8");

		// 取出登录数据 ，判断数据是否符合
		User user = us.login(new User(username, password));
		HttpSession session = request.getSession(); // 取到session对象
		if (user != null) {
			session.setAttribute("loginUsername", username);

			// 统计网站访问总次数
			// session 不能满足要求，只能统计当前用户的访问次数，所以只能用更发作用域的存储空间application
			/*
			 * if(session.getAttribute("count") == null){
			 * session.setAttribute("count", 1); }else{
			 * session.setAttribute("count",
			 * (Integer)session.getAttribute("count")+ 1); }
			 */

			// 使用application（ServletContext）
			ServletContext application = request.getServletContext();
			if (application.getAttribute("count") == null) {
				application.setAttribute("count", 1);
			} else {
				application.setAttribute("count", (Integer) application.getAttribute("count") + 1);
			}

			Map<String, HttpSession> userSession = (Map<String, HttpSession>) application.getAttribute("user_session");
			if (userSession == null) {
				userSession = new HashMap<String,HttpSession>();
				application.setAttribute("user_session", userSession );
			}

			if (userSession.containsKey(username)) {//判断用户是否登录
				HttpSession otherSession = userSession.get(username);
				otherSession.setAttribute("errorMsg", "账号在已其他地方登录！！！");
				otherSession.removeAttribute("loginUsername");//踢掉对方自己登录
			}
			userSession.put(username, session);
			
			response.sendRedirect("login_success.jsp"); // 重定向（两次请求）
		} else {
			session.setAttribute("errorMsg", "用户名或密码错误！");
			response.sendRedirect("login.jsp"); // 重定向（两次请求）
		}
	}

}
