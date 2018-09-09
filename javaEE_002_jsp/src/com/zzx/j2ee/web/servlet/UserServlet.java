package com.zzx.j2ee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
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
		PrintWriter out = response.getWriter();

		// 取出登录数据 ，判断数据是否符合
		User user = us.login(new User(username, password));
		if (user != null) {
			// 在request中以Map类型的格式（key，value）存入一个值
			// request.setAttribute("loginUsername", username);
			// request.getRequestDispatcher("login_success.jsp").forward(request,
			// response);//转发(请求一次，请求处理多次)

			// Cookie 从服务器携带数据放到浏览器中的key。value数据
			/*
			 * Cookie cookie = new Cookie("loginUsername", username);
			 * response.addCookie(cookie); // 响应过程。把数据存放到浏览器
			 */
			HttpSession session = request.getSession(); //取到session对象
			session.setAttribute("loginUsername", username);
			response.sendRedirect("login_success.jsp"); // 重定向（两次请求）
		} else {
			out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><script>alert('用户名或密码错误!!!');" + " location.href = 'login.html'</script></head></html>");
			out.flush();
			out.close();
		}
	}

}
