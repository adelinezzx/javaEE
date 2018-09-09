<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file = "../header.jsp" %>
 --%>
<%--  <%@ include file="checkRights.jsp"%> --%>

<html>
<head>
<title>聊天室</title>
<!-- <meta http-equiv="Refresh" content="3"> -->
<script type="text/javascript">
	setInterval("showNewMessage()", 5000);
	function showNewMessage() {
	/* 	  window.location.reload();  */
		 location.href = location.href; 
	}
</script>


<style type="text/css">
#alldiv {
	margin: 0px auto;
	width: 90%;
	height: 550px;
	border: 1px solid red;
}

#msgdiv {
	float: left;
	width: 79%;
	height: 80%;
	border: 1px solid red;
	overflow: scroll;
}

#namediv {
	float: right;
	width: 20%;
	height: 80%;
	border: 1px solid red;
	padding-left :5px;
}

#senddiv {
	clear: both;
	width:100%;
	border: 1px solid red;
}
</style>

</head>
<body>
	<div id="alldiv">
		<div id="msgdiv">
			<%@ include file="showMessage.jsp"%>
		</div>
		<div id="namediv">
			<%@ include file="names.jsp"%>
		</div>
		<div id="senddiv">
			<form action="doMessage.jsp" method="post">
				请输入发言：<input type="text" name="message" /><br /> <input
					type="submit" value="发送" /><br >
					<input type ="text" >
			</form>
			<a href="doLogout.jsp">安全退出</a>
		</div>
	</div>
</body>
</html>

<%-- <%@ include file = "../bottom.jsp" %> --%>