package com.yc.web.servlets;

import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.ERROR_500;
import static com.yc.utils.YcConstants.LOGINUSER;
import static com.yc.utils.YcConstants.LOGINPAGE;
import static com.yc.utils.YcConstants.REGPAGE;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Resuser;
import com.yc.biz.impl.ResuserBizImpl;
import com.yc.dao.DBUtil;
import com.yc.utils.Encrypt;

 
@WebServlet(  urlPatterns ={"/resuser.action","/resuser/resuser.action"})
public class ReuserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private ResuserBizImpl resuserbiz = new ResuserBizImpl();
    private DBUtil db = new DBUtil();
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if ("login".equals(op)) {
				loginOp(request, response);
			} else if ("isResuserLogin".equals(op)) {
				isResuserLogin(request, response);
			} else if ("logout".equals(op)) {
				loginoutOp(request, response);
			} else if ("toLogin".equals(op)) {
				toLoginOp(request, response);//到登录页面
			} else if ("toReg".equals(op)) {
				toRegOp(request, response);//到注册页面
			} else if ("reg".equals(op)) {
				regOp(request, response);// 注册页面
			}   else {
				response.sendRedirect(ERROR_404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(ERROR_500);
		}
	}


	/**
	 * 注册操作
	 */
private void regOp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		 
	 Resuser user = (Resuser) super.parseParameterToT(request, Resuser.class);
	 user.setPwd ( Encrypt.md5(user.getPwd())  );//密码加密
		//判断验证码
		 //从session中取出标准验证码
		 session  = request.getSession();
		 String rand = (String) session.getAttribute("rand");
	 
		 String valcode = request.getParameter("valcode");
		 
		 if(rand.equals(valcode)  == false ){
			 request.setAttribute("msg", "验证码错误");//输出出错信息
			 request.getRequestDispatcher("/index.jsp").forward(request,response);
		 }else{
			int result = db.doUpdate(
					"insert into resuser( username, pwd,email) values(?, ?,?)",
					user.getUsername(), user.getPwd(), user.getEmail());
			if (result > 0) {
				//session.setAttribute(LOGINUSER, user);
				request.getRequestDispatcher(LOGINPAGE).forward(request,
						response);
				session.setAttribute("msg", " ");
			} else {
				session.setAttribute("msg", "注册错误");
				request.getRequestDispatcher(LOGINPAGE).forward(request,
						response);
			}
		 }
			 
	}

/**
 * 到  注册页面
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	private void toRegOp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 request.getRequestDispatcher(REGPAGE).forward(request,response);
		
	}

/**
 * 到登录页面
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	private void toLoginOp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 request.getRequestDispatcher(LOGINPAGE).forward(request,response);
		
	}


	/**
	 * 用户退出
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void loginoutOp(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		 
		session.removeAttribute(LOGINUSER);
		response.sendRedirect("../index.jsp");
	}


	private void isResuserLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Resuser u = (Resuser) session.getAttribute(LOGINUSER) ;
		if(u == null ){
			jm.setCode(0);
		}else{
			 jm.setCode(1);
			 u.setPwd("");
			 jm.setObj( u);
		}
		
		super.outJsonString(jm, response);
	}

/**
 * 登录操作
 * @param request
 * @param response
 * @throws IllegalAccessException
 * @throws IllegalArgumentException
 * @throws InvocationTargetException
 * @throws InstantiationException
 * @throws ServletException
 * @throws IOException
 */
	private void loginOp(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ServletException, IOException {
		 Resuser user = (Resuser) super.parseParameterToT(request, Resuser.class);
		 
		//判断验证码
		 //从session中取出标准验证码
		 session  = request.getSession();
		 String rand = (String) session.getAttribute("rand");
	 
		 String valcode = request.getParameter("valcode");
		 
		 if(rand.equals(valcode)  == false ){
			 request.setAttribute("msg", "验证码错误");//输出出错信息
			 request.getRequestDispatcher("/index.jsp").forward(request,response);
		 }else{
			 user = resuserbiz.login(user);
			 if(user  != null){
				 session.setAttribute(LOGINUSER, user);
				 request.getRequestDispatcher(LOGINPAGE).forward(request,response);
				 session.setAttribute("msg", " ");
			 }else{
				 session.setAttribute("msg", "用户名或者密码错误");
				 request.getRequestDispatcher(LOGINPAGE).forward(request,response);
			 }
			 
		 }
	}

}
