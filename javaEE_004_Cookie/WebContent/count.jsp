<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   
//全部的访问量
      int count = 0 ;
      if(application.getAttribute("count") !=null) {
    	  count = Integer.parseInt(  application.getAttribute("count") .toString());
    	  
      }
      count++ ;
      application.setAttribute("count", count) ;
      
      //用户访问量
      
      int sessioncount = 0; 
      if(session.getAttribute("sessioncount") !=null) {
    	  sessioncount = Integer.parseInt(  session.getAttribute("sessioncount") .toString());
      }
      sessioncount++ ;
      session.setAttribute("sessioncount", sessioncount) ;
      
      out.println("当前用户访问了"+ sessioncount + "次" + "<br/>"); 
      out.println("总访问了" + count + "次");
 %>