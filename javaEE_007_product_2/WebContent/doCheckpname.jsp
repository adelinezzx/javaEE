<%@page import="com.google.gson.Gson"%>
<%@page import="com.yc.bean.JsonModel"%>
<%@page import="com.yc.bean.Product"%>
<%@page import="com.yc.biz.ProductBiz"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
     request.setCharacterEncoding("UTF-8");
      
    String  pname= request.getParameter("pname");
    
    ProductBiz pb = new ProductBiz();
    Product p = pb.find(pname); 
    
    JsonModel jm = new JsonModel() ;
    
    
    if(p == null){
    	/* out.println("{code:1}");  //json  格式 */
    	jm.setCode(1);
    	jm.setMsg("产品名" + pname + "可以使用！");
    }else{
    	jm.setCode(0);
    	jm.setMsg("产品名已经被使用！");
    }
    
    //将这个  jm 对象转成字符串 再以流的方式传到前端 
    Gson gson = new Gson() ;
    String jsonstring = gson.toJson(jm);
    
    %>
    <%= jsonstring %>
    
    