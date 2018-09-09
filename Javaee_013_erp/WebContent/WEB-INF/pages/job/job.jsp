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
			               <c:forEach items ="${requestScope.jobPageBean.list }" var ="job">
			               
			                    <a  href="job.action?op=detail&id=${job.id }">  ${ job.inviter }  </a> 
			                        ----------- 
			                    <span style="color:gray;font-size:14px">${job.join_date }  </span>
			                    <hr  weidth="70px"/>
			                
			               </c:forEach>
			           
	                       <yc:pageBar href="job.action?op=show" pageBean="${requestScope.jobPageBean }"></yc:pageBar>
				</div>
		</div>
	</div>
	<%@ include file="../bottom.jsp"%>