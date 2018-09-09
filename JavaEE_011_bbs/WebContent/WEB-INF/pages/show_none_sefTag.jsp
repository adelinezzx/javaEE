<%@page import="com.yc.bean.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  include file="../../header.jsp"%>

<DIV class="t">
	<TABLE cellSpacing="0" cellPadding="0" width="100%">
		<TR class="tr2" align="center">
			<TD colSpan="2">论坛</TD>
			<TD style="WIDTH: 10%;">主题</TD>
			<TD style="WIDTH: 30%">最后发表</TD>
		</TR>
		<!--       主版块       -->

		<%
			Map<Integer, List<Board>> map = (Map<Integer, List<Board>>) session .getAttribute("boardMap");
	    	 
			if  (   map.containsKey(     new Integer(0)    )) {
				 
				List<Board> list = map.get(   new Integer(0)    );
				for (Board b : list) {
					 
		%>
		<TR class="tr3">
			<TD colspan="4"><%=b.getBoardname() %></TD>
			
		</TR>
         <%
             List<Board> sonlist = map.get(  b.getBoardid()  );
			 if(sonlist != null ){
				 for(Board sonBoard: sonlist ){
				 
         %>
         <TR class="tr3">
			<TD width="5%">&nbsp;</TD>
			<TH align="left">
				<IMG src="image/board.gif">
				<A href="list.html"><%=sonBoard.getBoardname() %></A>
			</TH>
			<TD align="center"><%=sonBoard.getTopicsum() %></TD>
			<TH>
				<SPAN>
					<A href="detail.html"><%=sonBoard.getRecenttopictitle() %> </A>
				</SPAN>
				<BR/>
				<SPAN><%=sonBoard.getRecenttopicusername() %></SPAN>
			    <SPAN class="gray">[<%= sonBoard.getRecenttopicmodifytime() %>] </SPAN> 
			</TH>
		</TR>
		<%
				 }
			 }
			}
			}
		%>







	</TABLE>
</DIV>


<%@  include file="../../bottom.jsp"%>