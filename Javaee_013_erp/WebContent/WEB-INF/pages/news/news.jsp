<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix = "yc" uri="http://www/yc/core" %>
  
  
<%@ include file="../header.jsp"%>
<div id="center_line"></div>
<div id="center_top">
	<div id="gongsijianjie">

		<div id="center_top_left_b">
			<ul>
				<c:forEach items="${news_classList }" var="news_class">
					<li><a
						href="news.action?op=show&id=${news_class.id }&typename=${news_class.typename }">${news_class.typename }</a></li>
				</c:forEach>

			</ul>
		</div>
	</div>

	<div id="toutiaoxinwen">
		<div id="center_top_right_c">
				<div id="center_top_right_c_l"><!--显示最新的10条新闻概要及发表时间  -->
			               <c:forEach items ="${requestScope.newsPageBean.list }" var ="news">
			                 <a  href="news.action?op=detail&id=${news.id }">  ${ news.title }  </a>
			                 ----------- 
			                 <span style="color:gray;font-size:14px">${news.change_date }  </span>
			                  <hr/>
			                
			               </c:forEach>
			           
	                       <yc:pageBar href="news.action?op=show&id=${id }" pageBean="${requestScope.newsPageBean }"></yc:pageBar>
				</div>
		</div>
	</div>
	<%@ include file="../bottom.jsp"%>