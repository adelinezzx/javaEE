<%@page import="com.yc.bean.Product"%>
<%@page import="com.yc.model.PageBean"%>
<%@page import="com.yc.biz.ProductBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    <%
      request.setCharacterEncoding("UTF-8");
      //给首页赋初值
      int pages = 1 ;
      int pagesize = 5 ;
      String orderway = "desc" ;
      String ordercolumn = "pid";
      String pname = null;
      Product p = new Product();
     
      if(request.getParameter("pages") != null){
    	  pages =  Integer.parseInt(    request.getParameter("pages"));
      }
      if(request.getParameter("pagesize") != null){
    	  pagesize = Integer.parseInt(    request.getParameter("pagesize"));
      }
      if(request.getParameter("ordercolumn") != null){
    	  ordercolumn =  request.getParameter("ordercolumn");
      }
      if(request.getParameter("orderway") != null){
    	  orderway =  request.getParameter("orderway");
      }
      if(request.getParameter("pname") != null){
    	  pname =  request.getParameter("pname");
    	  p.setPname(pname);
    	  session.setAttribute("pname", pname);
      }
      //保留状态request  session application
      session.setAttribute("ordercolumn", ordercolumn);
      session.setAttribute("orderway", orderway);
      
      ProductBiz pb = new ProductBiz();
      PageBean pageBean = pb.findByPage(pages, pagesize,ordercolumn,orderway,p);
      
      session.setAttribute("pageBean", pageBean) ;
      request.getRequestDispatcher("show.jsp").forward(request, response);
      
      
    %>