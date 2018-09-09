<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
   <script src="ckeditor/ckeditor.js"></script>
   
<form name="myform"  action="doMessage.jsp" method="post">
					<!-- 加入CKeditor第三步 -->
					<textarea name="message" id="message " rows="5" cols="80"></textarea>
					<script>
						CKEDITOR.replace("message");
					</script>
					<br />
					<!-- ---------------------------------------------------------------------------------------------- -->
					<input type="button" value="发送"  onclick="report()"/><br>
				</form>
</body>
</html>