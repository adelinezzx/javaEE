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
				<c:forEach items="${product_classList }" var="product_class">
					<li><a
						href="product.action?op=show&product_class=${product_class.id }&typename=${product_class.protype }">${product_class.protype }</a></li>
				</c:forEach>

			</ul>
		</div>
	</div>

	<div id="toutiaoxinwen">
		<div id="center_top_right_c">
				<div id="center_top_right_c_l"><!--显示点击产品的详细内容  -->
			                <h2>${server.title }</h2>
			                <br/>
			                
			                创建时间:${server.join_date  }<br/>
			              
			                <hr/>
			                <c:if test="${server.pic != null && server.pic!= ' ' }">
			                        <img   src="${server.pic }" width="100px"  height ="100px"/>
			                </c:if>
			                <br/>
			                ${server.content   }
			            
				</div>
		</div>
	</div>
	<%@ include file="../bottom.jsp"%>