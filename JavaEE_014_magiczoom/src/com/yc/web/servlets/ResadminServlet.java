package com.yc.web.servlets;

import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.ERROR_500;
import static com.yc.utils.YcConstants.LOGINPAGE;
import static com.yc.utils.YcConstants.LOGINUSER;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Resadmin;
import com.yc.biz.impl.ResadminBizImpl;
import com.yc.biz.impl.ResuserBizImpl;
 
@WebServlet("/backresadmin.action")
public class ResadminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
   private ResadminBizImpl  rbi = new ResadminBizImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if ("login".equals(op)) {
				loginOp(request, response);
			}else{
				response.sendRedirect(ERROR_404);
			}
		} catch ( Exception e) {
			e.printStackTrace();
			response.sendRedirect(ERROR_500);
		}
		 
	}

	
	private void loginOp(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ServletException, IOException {
		 Resadmin resadmin = (Resadmin) super.parseParameterToT(request, Resadmin.class);
		//判断验证码
		 //从session中取出标准验证码
		 session  = request.getSession();
		 String rand = (String) session.getAttribute("rand");
	 
		 String valcode = request.getParameter("valcode");
		 
		 if(rand.equals(valcode)  == false ){
			 request.setAttribute("msg", "验证码错误");//输出出错信息
			 request.getRequestDispatcher("backlogin.jsp").forward(request,response);
		 }else{
			 resadmin = rbi.login(resadmin);
			 if(resadmin  != null){
				 session.setAttribute("login_resadmin", resadmin);//存入登录的用户名
				 request.getRequestDispatcher("/resadmin/main.jsp").forward(request,response);
			 }else{
				 request.getRequestDispatcher("backlogin.jsp").forward(request,response);
			 }
			 
		 }
	}

}
