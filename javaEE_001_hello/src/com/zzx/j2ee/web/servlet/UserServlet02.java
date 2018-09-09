package com.zzx.j2ee.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserServlet02 extends HttpServlet{
	
	/**
	 * 反序列化编号
	 */
	private static final long serialVersionUID = -8699755118181672984L;
	
	public UserServlet02() {
		System.out.println("----------UserServlet实例化----------");
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("----------UserServlet请求处理-----------");
	}

	@Override  //post 请求的方法  
	//HttpServletRequest 请求信息处理接口
	//HttpServletResponse  是相应信息处理接口
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("----------UserServlet请求处理-----------");
	}


	@Override   //servlet销毁时调用
	public void destroy() {
		System.out.println("----------UserServlet销毁-----------");
	}

	@Override   //servlet初始化时调用
	public void init() throws ServletException {
		System.out.println("----------无参UserServlet初始化-----------");
	}
	
	@Override //ServletConfig是Servlet的配置信息
	public void init(ServletConfig config) throws ServletException {
		System.out.println("----------有参UserServlet初始化-----------");
		String charset = config.getInitParameter("charset");
		System.out.println("charset字符编码集：" + charset);
		
	}
}
