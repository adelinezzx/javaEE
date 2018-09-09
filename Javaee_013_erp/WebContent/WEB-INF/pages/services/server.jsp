<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix = "yc" uri="http://www/yc/core" %>
  
  
<%@ include file="../header.jsp"%>
<div id="center_line"></div>
<div id="center_top">
	<div id="gongsijianjie">

		 
	</div>

	<div id="toutiaoxinwen">
		<div id="center_top_right_c">
				<div id="center_top_right_c_l"><!--显示产品信息  -->
			               <c:forEach items ="${requestScope.serverPageBean.list }" var ="server">
			               
			                    <a  href="server.action?op=detail&id=${server.id }">  ${ server.title }  </a> 
			                
			                    <hr  weidth="70px"/>
			                
			               </c:forEach>
			           
	                       <yc:pageBar href="server.action?op=show" pageBean="${requestScope.serverPageBean }"></yc:pageBar>
				</div>
		</div>
	</div>
	<%@ include file="../bottom.jsp"%>