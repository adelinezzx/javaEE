<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

  <%
    //request一次获取到多个属性值
     request.setCharacterEncoding("utf-8");  //设置获取到的值的字符集
      //将存在元素Map中的值用Map存放，在通过for循环遍历
     Map<String,String[]> map = request.getParameterMap();
     
     for(Map.Entry<String,String[]> entry : map.entrySet()  ){
    	  String  key = entry.getKey();  //得到属性名
    	  out.println(key + "	");
    	  
    	  String[] values = entry.getValue();  //得到属性名对应的value值的集合
    	  for(String s: values){  //遍历
    		  out.print(s+ "<br/>");
    	  }
     }
  %>
