<%@page import="com.yc.biz.TopicBiz"%>
<%@page import="com.yc.utils.RequestUtils"%>
<%@page import="com.yc.bean.Topic"%>
<%@page import="java.util.Map"%>
<%@page import="com.yc.utils.FileUploadUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
       FileUploadUtils  fuu = new FileUploadUtils();
       Map<String,String> map = fuu.uploadFiles(pageContext);//pageContext 表示页面的上下文容器  
       map.put("pic", map.get("pic_relativepath")   ) ;
       
       Topic p = RequestUtils.parseRequest(map, Topic.class);
       
       TopicBiz pb = new TopicBiz();
       int r = pb.add(p);
       
       if(r>0){
    	   out.print("<script>alert('添加成功！');location.href='../index.jsp';</script>");
       }else{
    	   out.print("<script>alert('添加失败！');location.href='../index.jsp';</script>");
       }
       
       
    
    %>
 