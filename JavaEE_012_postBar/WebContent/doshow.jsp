
<%@page import="com.yc.bean.User"%>
<%@page import="com.yc.bean.JsonModel"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <% 
   User uname =  (User )session.getAttribute("user");
    
   
   //1.创建一个jsonmodel对象 设置 code= 1 
	JsonModel jm = new JsonModel();
	jm.setCode(1);
	/* jm.setUname(uname); */
	 
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("uname",uname.getUname()  ) ;
	//map.put("upass",upass);
	jm.setObj(map) ;  
	
	//2.利用gson 将jsonmodel 转为json字符串
	Gson gson = new Gson();
	String jsonstring = gson.toJson(jm);
	//3.利用out对象将字符串输出到客户端
	out.println(jsonstring);
	out.flush();
  %>