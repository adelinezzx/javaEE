package com.yc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Companyinfo;
import com.yc.bean.Infotype;
 
@WebServlet("/companyinfo.action")
public class CompanyinfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if("show".equals(op) ){
			 showOp(request,response);
		 }
	}


	private void showOp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String infotype = "企业简介" ;
		if(request.getParameter("typename") != null && !"" .equals(request.getParameter("typename") )){
			infotype = request.getParameter("typename");
		}
		ServletContext application = request.getServletContext() ;
		List<Infotype> infotypeList = (List<Infotype>) application.getAttribute("infotypeList");
		List<Companyinfo>  companyinfoList = (List<Companyinfo>) application.getAttribute("companyinfoList");
		
		for (Infotype it : infotypeList) {
			  for (Companyinfo ci : companyinfoList) {
				  if(it.getId() == ci.getTypeid()  && it.getTypename().equals(infotype) ){
					  request.setAttribute("companyinfo", ci);
				  }
			}
		}
		request.getRequestDispatcher("/WEB-INF/pages/companyinfo/companyinfo.jsp").forward(request, response);
		
	}

}
