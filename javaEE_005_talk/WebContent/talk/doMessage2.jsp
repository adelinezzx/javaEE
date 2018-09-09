<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.yc.bean.JsonModel"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//取出message，存到list中，再存到application中，再跳到talk.jsp中
	List<String> messages = new ArrayList<String>();
	//如果数据池中的messages不为空  则将数据池中的数据都存到list集合中
	if (application.getAttribute("messages") != null) {
		messages = (List<String>) application.getAttribute("messages");
	}
	
	List<String> loginNames =(List<String>) application.getAttribute("loginNames");
	
	//取出用户输入的 信息内容 
	String message = request.getParameter("message");

	if (message!=null &&  !" ".equals("message")) {
		//发信息的人名
		String uname = (String) session.getAttribute("uname");
		//拼接  信息的内容
		String msg = uname + "说 :<br/>" + message + "<br/><span>"
				+ new Date() + "</span><hr/>";
		//将拼接的 内容 存到集合中
		messages.add(msg);
		//再存到application中
		application.setAttribute("messages", messages);
		
	}
	/* response.sendRedirect("talk.jsp");//跳转 */

	//1.创建一个jsonmodel对象 设置 code= 1  
	JsonModel jm = new JsonModel();
	jm.setCode(1);
	//jm.setObj(messages);
	
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("messageList",messages) ;
	map.put("userList",loginNames);
	jm.setObj(map) ;
	
	//2.利用gson 将jsonmodel 转为json字符串
	Gson gson = new Gson();
	String jsonstring = gson.toJson(jm);
	//3.利用out对象将字符串输出到客户端
	out.println(jsonstring);
	out.flush();
%>
