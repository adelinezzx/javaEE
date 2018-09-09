<%@page import="com.yc.biz.ProductBiz"%>
<%@page import="com.yc.dao.RequestUtils"%>
<%@page import="com.yc.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	/*  //1自动从request中取出所有参数
	 String pname = request.getParameter("pname");
	 Double price = Double.parseDouble(request.getParameter("price"));
	 
	 //2 自动调用一个Product对象中的setXXX（）方法，将这个request中的值传进去
	 Product  p = new Product();
	 p.setPname(request.getParameter("pname"));
	 p.setPrice( Double.parseDouble(   request.getParameter("price")  ) );
	 */
	//以后的调用： Product p = XXX.parseRequest(request,Product.class)

	//3  
	Product p  = RequestUtils.parseRequest(request, Product.class);
	 
	 ProductBiz pb = new ProductBiz();
	 int r = pb.add(p);
	 if(r>0){
		 out.println("<script>alert('添加成功！');location.href='index.jsp' ;</script>");
	 }else{
		 out.println("<script>alert('添加失败！');location.href='add.jsp' ;</script>");
	 }
%>
<%= p  %>