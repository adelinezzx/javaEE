package com.yc.web.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;










import com.google.gson.Gson;
import com.yc.bean.Admin;
import com.yc.bean.JsonModel;
import com.yc.biz.impl.UserBizImpl;
import com.yc.model.DBHelper;
import com.yc.model.DataGridModel;
import com.yc.utils.Encrypt;
import com.yc.utils.RequestUtils;
 

 
@WebServlet("/backlogin/admin.action")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserBizImpl ubi = new UserBizImpl() ;
	private DBHelper db = new DBHelper();
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if ("login".equals(op)) {

				loginOp(request, response);
			}else if("toLogin".equals(op)){
				toLogin(request,response);
			}else if("list".equals(op)){
				listOp(request,response);
			}else if("reg".equals(op)){
				regOp(request,response);
			}
		} catch (Exception e) {

			e.printStackTrace();
			e.printStackTrace();
			String basePath = (String) request.getServletContext()
					.getAttribute("basePath");

			request.getSession().setAttribute("errorMsg", e.getMessage());
			response.sendRedirect(basePath + "500.jsp");

		}
	}

	/**
	 * 管理员注册
	 * @param request
	 * @param response
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException 
	 */
	private void regOp(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Admin admin =  RequestUtils.parseRequest(request , Admin.class);
		admin.setUserpassword(       Encrypt.md5(Encrypt.md5(     admin.getUserpassword()    )   )  );
		
		JsonModel jm = new JsonModel();
		
		DBHelper db = new DBHelper();
		try {
			db.doUpdate("insert into admin(username , userpassword , join_time) values(?,?,now()  )", admin.getUsername(),admin.getUserpassword());
		    jm.setCode(1);
		} catch (Exception e) {
		   jm.setCode(0);
			e.printStackTrace();
		}
		super.outJsonString(response, jm);
	}
/**
 * 管理员列表
 * @param request
 * @param response
 * @throws Exception
 */
	private void listOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DBHelper db = new DBHelper() ;
		
		int total = (int) db.selectFunc("select count(*) from admin ");
		
		int start = (page -1) * rows ;
		List< Admin> list= db.select(Admin.class, "select * from admin  order by " + sort + " " + order+  " limit  " + start +", " + rows );
		DataGridModel  dgm = new DataGridModel() ;
		dgm.setRows(list);
		dgm.setTotal(total);
		
		//再将对象格式转化为json格式   --》  gson
	 super.outJsonString(response, dgm);
	}
/**
 * 跳转到登录
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	private void toLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	 
		 request.getRequestDispatcher("login.jsp").forward(request,response);
	}

	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void loginOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    Admin admin =  RequestUtils.parseRequest(request , Admin.class);
		 
			//判断验证码
		   //从session中取出标准验证码
		 
		  String rand = (String) session.getAttribute("rand");
		 
		 String valcode = request.getParameter("valcode");
		 if(rand.equals(valcode)  == false ){
			 response.getWriter().print("<script>alert('验证码错误！');location.href = 'backlogin/login.jsp';</script>");
		 }else{
			 List  list  =db.select(Admin.class, "select * from admin where username = ? and userpassword = ? ", admin.getUsername(),Encrypt.md5(  Encrypt.md5(  admin.getUserpassword()  )   )  );
			 if(list  != null  &&   list.size() >  0 ){
				 admin = (Admin) list.get(0);
				 session.setAttribute("admin", admin);
				 request.getRequestDispatcher("/backlogin/manager/index.jsp").forward(request,response);
			 }else{
				 response.getWriter().print("<script>alert('用户名或者密码错误！');location.href = 'backlogin/login.jsp';</script>");
			 }
		
	}

	}
}
