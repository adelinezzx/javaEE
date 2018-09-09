<%@page import="java.util.Map"%>
<%@page import="com.yc.dao.FileUploadUtils"%>
<%@page import="com.yc.biz.ProductBiz"%>
<%@page import="com.yc.dao.RequestUtils"%>
<%@page import="com.yc.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	 
     FileUploadUtils fuu = new FileUploadUtils();
     Map<String,String> map =  fuu.uploadFiles(pageContext);    //pageContext表示页面的上下文容器
     
     map.put("pic", map.get("pic_relativepath"));
     
     //map： pname price pic 
     
     Product p = RequestUtils.parseRequest(map, Product.class);
     
     ProductBiz pb = new ProductBiz();
     int r = pb.add(p);
     if(r> 0){
    	 out.println("<script>alert('添加成功！');location.href='index.jsp' ;</script>");
	 }else{
		 out.println("<script>alert('添加失败！');location.href='add.jsp' ;</script>");
	 }
%>
<%= p
%>
