<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

    <%
          request.setCharacterEncoding("UTF-8");
          String username;
          String password ;
          username=request.getParameter("username").trim();
          password=request.getParameter("password").trim();
          
          if((username!=null && "".equals(username))  ||  (password != null  && password.equals("")) ){
       	  out.print("<script>alert(' 用户名和密码不能为空! 请重新输入') ; location.href='index.jsp'; </script>");
          }else{
        	  //转发，只请求一次
        	  request.getRequestDispatcher("talkroom.jsp").forward(request, response);
        	  
          }
      
    %>