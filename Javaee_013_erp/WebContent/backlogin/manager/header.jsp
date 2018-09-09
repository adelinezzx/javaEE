<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> </title>

 <%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +path +  "/" ;
  
      request.setAttribute("basePath", basePath);
  %>
    <base href="${basePath }">
    <link rel="stylesheet" type="text/css" href="backlogin/manager/jquery-easyui-1.5.4.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="backlogin/manager/jquery-easyui-1.5.4.5/themes/icon.css">
    
    <script type="text/javascript" src="backlogin/manager/jquery-easyui-1.5.4.5/jquery.min.js"></script>
    <script type="text/javascript" src="backlogin/manager/jquery-easyui-1.5.4.5/jquery.easyui.min.js"></script>
     
     
     <script type="text/javascript" src="backlogin/manager/ckeditor/ckeditor.js"></script>
     
     <script type="text/javascript" src="backlogin/js/ajaxfileupload2.js"></script>
</head>
 