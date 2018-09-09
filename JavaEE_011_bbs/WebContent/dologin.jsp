<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
      request.setCharacterEncoding("UTF-8");
    
      String uName =  request.getParameter("uName").trim();
      String uPass =  request.getParameter("uPass").trim();
      
     if( (uName ==null  ||   uName.equals("")  )   ||  (uPass ==null ||  uPass.equals("")) ){
    	 out.print("<script> alert('username or password  cannot be a null value!');location.href='login.html';</script>") ;
    	
     } else{
    	 response.sendRedirect("index.html");
     }
     //response.sendRedirect("login.html");
    //request.getRequestDispatcher("login.html").forward(request, response);
    
    %>