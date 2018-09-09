<%@page import="com.yc.bean.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="yc" uri="http://www/yc/core"%>
<%@  include file="../../header.jsp"%>

  <Link rel="stylesheet" type="text/css" href="../../style/style.css" />
<DIV class="t">
	<TABLE cellSpacing="0" cellPadding="0" width="100%">
		<TR class="tr2" align="center">
			<TD colSpan="2">论坛</TD>
			<TD style="WIDTH: 10%;">主题</TD>
			<TD style="WIDTH: 30%">最后发表</TD>
		</TR>
		<!--       主版块       -->

  <yc:getmapbykey var="list" key="0" map="${boardMap }">
       <c:forEach items ="${list }" var ="board">
		<TR class="tr3">
			<TD colspan="4">${board.boardname } </TD>
		</TR>
          
          <yc:getmapbykey var="sonList" key="${board.boardid }" map="${boardMap }"> 
	              <c:forEach items="${sonList }" var="sonBoard"> 
						         <TR class="tr3">
									<TD width="5%">&nbsp;</TD>
									<TH align="left">
										<IMG src="image/board.gif">
										<A href="topic.action?op=list&boardid=${sonBoard.boardid }">${ sonBoard.boardname}   </A>
									</TH>
									  <TD align="center">${ sonBoard.topicsum  }</TD>
									 <TH>
										<SPAN>
											<A href="topic.action?op=detail&topicid=${sonBoard.recenttopicid }">${ sonBoard.recenttopictitle } </A>
										</SPAN>
										<BR/>
										<SPAN>${ sonBoard.recenttopicusername}</SPAN>
									    <SPAN class="gray">${  sonBoard.recenttopicmodifytime} </SPAN> 
									</TH>  
								</TR>
			      </c:forEach>
	    	</yc:getmapbykey>
	</c:forEach>

</yc:getmapbykey>






	</TABLE>
</DIV>


<%@  include file="../../bottom.jsp"%>