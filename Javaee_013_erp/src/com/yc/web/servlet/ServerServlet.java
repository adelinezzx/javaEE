package com.yc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Product;
import com.yc.bean.Server;
import com.yc.biz.BaseBiz;
import com.yc.model.DBHelper;
import com.yc.model.PageBean;

 
@WebServlet("/server.action")
public class ServerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if ("show".equals(op)) {
				showOp(request, response);
			}else if("detail".equals(op)){
				detailOp(request,response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			String basePath = (String) request.getServletContext().getAttribute("basePath");
			
			request.getSession().setAttribute("errorMsg", e.getMessage());
			response.sendRedirect(basePath + "500.jsp");
		}
	}

	private void detailOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		 DBHelper db= new DBHelper() ;
		 List<Server> list = db.select(Server.class, "select * from server where id =  ? ", id);
		 Server n = list != null && list.size()  >0 ? list.get(0) : null ;
		 request.setAttribute("server", n);
		 
		 request.getRequestDispatcher("/WEB-INF/pages/services/detail.jsp").forward(request, response);
		
	}

	private void showOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	 
		BaseBiz bb = new BaseBiz();
		 
		int start = (pages-1) * pagesize ;
		 
		PageBean 	serverPageBean   = bb 	.findByPage( "select count(*) from server ", 	
		 			null, 	" select * from server  order by join_date desc limit "+ start + ", " + pagesize ,
					null, pages, pagesize, Server.class);
		 
	      
         request.setAttribute("serverPageBean", serverPageBean);
         
         
		 request.getRequestDispatcher("/WEB-INF/pages/services/server.jsp").forward(request, response); 
	}

}
