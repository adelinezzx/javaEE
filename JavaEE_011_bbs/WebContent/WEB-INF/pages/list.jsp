<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="yc" uri="http://www/yc/core"%>
<%@include file="../../header.jsp" %>
<!--  导航-->

<DIV>
       &gt; &gt;<b><a href="index.jsp">论坛首页</a>&gt; &gt;${board.boardname  } </b>
</DIV>


<!--      新帖        -->
<DIV>
	<A href="back/post.jsp"><IMG src="image/post.gif" name="td_post"
		border="0" id=td_post></A>
</DIV>
<!--         翻 页         -->
  <DIV>
	<yc:pageBar href="topic.action?op=list&boardid=${param.boardid }" pageBean="${pb  }"></yc:pageBar>
</DIV>

<DIV class="t">
	<TABLE cellSpacing="0" cellPadding="0" width="100%">
		<TR>
			<TH class="h" style="WIDTH: 100%" colSpan="4"><SPAN>&nbsp;</SPAN></TH>
		</TR>
		<!--       表 头           -->
		<TR class="tr2">
			<TD>&nbsp;</TD>
			<TD style="WIDTH: 80%" align="center">文章</TD>
			<TD style="WIDTH: 10%" align="center">作者</TD>
			<TD style="WIDTH: 10%" align="center">回复</TD>
		</TR>
		<!--         主 题 列 表        -->
		<c:forEach items="${pb.list }" var="topic">
				<TR class="tr3">
					<TD><IMG src="image/topic.gif" border=0></TD>
					<TD style="FONT-SIZE: 15px"><A href="topic.action?op=detail&topicid=${topic.topicid }"> ${topic.title  } </A></TD>
					<TD align="center">${topic.uname }</TD>
					<TD align="center">${topic.replycount  }</TD>
				</TR>

		</c:forEach>
		
	</TABLE>
	
</DIV>

	<DIV>
			<yc:pageBar href="topic.action?op=list&boardid=${param.boardid }" pageBean="${pb  }"></yc:pageBar>
	</DIV>
	
	<%@ include file="../../bottom.jsp" %>