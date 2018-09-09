<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加学生</title>
</head>
<body>
--------------------------存钱--------------------------------------------	<br />
	<form action="student.action" method="post">
		<input type="hidden" name="op" value="deposit" />
		账号:<input type="text" name="accountid" />
		金额：<input type="text" name="money" />
		<br />
		<input type="submit" value="提交" />
	</form>	<br />
	--------------------------取钱--------------------------------------------
	<br />
	<form action="student.action" method="post">
		<input type="hidden" name="op" value="withdraw" />
		账号:<input type="text" name="accountid" />
		金额：<input type="text" name="money" />
		<br />
		<input type="submit" value="提交" />
	</form>
		<br />
	--------------------------转账--------------------------------------------
	<br />
	<form action="student.action" method="post">
		<input type="hidden" name="op" value="transfer" />
		转入:<input type="text" name="inaccountid" />
		转出:<input type="text" name="outaccountid" />
		金额：<input type="text" name="money" />
		<br />
		<input type="submit" value="提交" />
	</form>
	
	

</body>
</html>