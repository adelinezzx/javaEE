package com.zzx.j2ee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzx.j2ee.entity.User;
import com.zzx.j2ee.service.UserService;
import com.zzx.j2ee.service.impl.UserServiceImpl;

public class register extends HttpServlet {
	// Map<String, String> passwordMap = new HashMap<String, String>(); //
	// 存储密码和确认密码
	// Map<String, Map<String, String>> usernameMap = new HashMap<String,
	// Map<String, String>>(); // 存储用户名对应的密码和确认密码
	/**
	 * 反序列化编号
	 */
	private static final long serialVersionUID = -8699755118181672984L;

	public register() {
		System.out.println("----------UserServlet实例化----------");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("----------UserServlet请求处理-----------");
	}

	@Override
	// post 请求的方法
	// HttpServletRequest 请求信息处理接口
	// HttpServletResponse 是相应信息处理接口
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("----------UserServlet请求处理-----------");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");
		PrintWriter out = resp.getWriter();
		System.out.println("用户名：" + username + "-- 密码：" + password + " -- 确认密码：" + repassword);

		UserService us = new UserServiceImpl();//初始化实例
		if (username != "" && password != "" && repassword != "") {
			User user = us.showUsername(new User(username));
			System.out.println(user);
			//密码和确认密码不一致
			/*if (password != repassword) {
				out.println("<h1>password is not equal with repassword</h1>");
			}*/
			//用户名存在
			if(user != null){
				out.println("<h1>the Username is already exist</h1>");
			}
			else {
				//开始注册
				UserService usc = new UserServiceImpl();
				boolean result = usc.register(new User(username, repassword));
				if (result) {
					out.println("<h1>register successful</h1>");
				}
			}
		}
	}

	@Override
	// servlet销毁时调用
	public void destroy() {
		System.out.println("----------UserServlet销毁-----------");
	}

	@Override
	// servlet初始化时调用
	public void init() throws ServletException {
		System.out.println("----------无参UserServlet初始化-----------");
	}

	@Override
	// ServletConfig是Servlet的配置信息
	public void init(ServletConfig config) throws ServletException {
		System.out.println("----------有参UserServlet初始化-----------");
		String charset = config.getInitParameter("charset");
		System.out.println("charset字符编码集：" + charset);

	}
}
