package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.JsonModel;
import com.yc.bean.User;
import com.yc.biz.impl.UserBizImpl;

@WebServlet("/user.action")
public class UserServlet extends BaseServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			if ("login".equals(op)) {
				loginOp(req, resp);
			}else if ("reg".equals(op)) {
				regOp(req, resp);
			}else if("isUnameExist".equals(op)){
				isUnameExistOp( req,resp);
			} else if("islogin".equals(op)){
				isLogin( req,resp);
			}  else if("logout".equals(op)){
				isLogoutOp( req,resp);
			} else {
				resp.sendRedirect("404.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("500.jsp");
		}
	}

	private void isLogoutOp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//1 得到 session
		   HttpSession session = req.getSession() ;
		   session.removeAttribute("user");
		   session.invalidate();
		   JsonModel jm = new JsonModel() ;
		   jm.setCode(1); 
		   super.outJsonString(jm, resp);
	}

	/**
	 * 判断用户是否登录
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void isLogin (HttpServletRequest req, HttpServletResponse resp) throws  Exception {
		//1 实例化  JsonModel  UserBizImpl   
		 JsonModel jm = new JsonModel() ;
		 //2 得到 session
		  HttpSession session = req.getSession() ;
		  //3 判断用户是否已经存在  没有存在则 取出 user  
		  if(session.getAttribute("user"   ) != null){
			  User u = (User) session.getAttribute("user") ;
			  u.setUpass("");
			  jm.setCode(1);  //表示已经登录
			  jm.setObj(u);   //把user对象存入 jm
		  }else{
			  jm.setCode(0);   //用户不存在， code = 0 
		  }
		super.outJsonString(jm, resp);
	}

	private void isUnameExistOp(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		JsonModel jm = new JsonModel() ;
		 UserBizImpl ubi = new UserBizImpl() ;
		 //2  从客户端取出user的信息 
		 User u = (User) super.parseParameterToT(req, User.class) ;
		 //3  判断用户是否已经登录  没有登录 则将code的值设为1 ，否则将code设置为0 表示用户已经登录
		 User r = ubi.isUnameExists(u.getUname());
		if(r==null) {
		    jm.setCode(1);	
		}else{
			jm.setCode(0);
			jm.setErrmsg(u.getUname()+"被占用，请更换注册名");
		}
		super.outJsonString(jm, resp);
	}

	private void regOp(HttpServletRequest req, HttpServletResponse resp) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		String val_code=req.getParameter("val_code");
		HttpSession session=req.getSession();
		String rand=(String) session.getAttribute("rand");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		if(  ! val_code.equals(rand)) {
			out.println("<script>alert('验证码错误');location.href='reg.jsp';</script>");
			out.flush();
			return;
		}
		User user=(User) super.parseParameterToT(req, User.class	);
		if( user.getUpass().equals(user.getUpass())==false){
			out.println("<script>alert('两次输入的密码不一样');location.href='reg.jsp';</script>");
			out.flush();
			return;
		}
		UserBizImpl ubi=new UserBizImpl();
		try {
			ubi.reg(user);
			out.println("<script>alert('注册成功');location.href='login.jsp';</script>");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<script>alert('注册失败,"+e.getMessage()+"');location.href='reg.jsp';</script>");
			out.flush();
		}
	}

	private void loginOp(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ServletException, IOException {
		String val_code=req.getParameter("val_code");
		HttpSession session=req.getSession();
		String rand=(String) session.getAttribute("rand");
		
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		if(  ! val_code.equals(rand)) {
			out.println("<script>alert('验证码错误');location.href='login.jsp';</script>");
			out.flush();
			return;
		}
		
		User user=(User) super.parseParameterToT(req, User.class	);
		UserBizImpl ubi=new UserBizImpl();
		try {
			User u=ubi.login(user);
			if(u!=null) {
				//取session
				session.setAttribute("user", u);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}else {
				out.println("<script>alert('用户名或密码错误');location.href='login.jsp';</script>");
				out.flush();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
