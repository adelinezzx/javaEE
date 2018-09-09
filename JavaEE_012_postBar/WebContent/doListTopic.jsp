<%@page import="com.yc.model.PageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.yc.biz.*"%>

    
    <%
 
    request.setCharacterEncoding("UTF-8");
    //给首页赋初值
    int pages = 1 ;
    int pagesize = 5 ;
    String orderway = "desc" ;
    String ordercolumn = "publishtime";
    String contents = null;
     
   
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
    
    //保留状态request  session application
    session.setAttribute("ordercolumn", ordercolumn);
    session.setAttribute("orderway", orderway);
    
    TopicBiz tb = new TopicBiz();
    PageBean pageBean = tb.findByPage(pages, pagesize,ordercolumn,orderway);
    
    session.setAttribute("pageBean", pageBean) ;
    request.getRequestDispatcher("show.jsp").forward(request, response);
    
    
    
    %>