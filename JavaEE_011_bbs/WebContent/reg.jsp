<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<script type="text/javascript" src="js/common.js" ></script>

<script type="text/javascript">
function changeImage(){
	document.getElementById("image01").src="image.jsp?" + new Date();
}

//检测用户名
function checkUname( uname){
	createXMLHttpRequest();
	url="user.action?op=isUnameExist";
	queryString="uname="+uname;
	xmlHttp.open("POST", url);
	xmlHttp.onreadystatechange = handleStateChange;
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttp.send(queryString); 
}

function handleStateChange(){
	if(xmlHttp.readyState == 4) {        
		if(xmlHttp.status == 200) {     
			// jsonModel  ->   code : 1   成功    0失败   errmsg
			var jsonobj=eval('('+xmlHttp.responseText+')');
			if(  jsonobj.code==1){
				document.getElementById('result').innerHTML='用户名可以使用';
			}else{
				document.getElementById("result").innerHTML =jsonobj.errmsg;
			}
		} 
	}
}


</script>

<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>
</DIV>
<!--      用户注册表单        -->
<DIV  class="t" style="MARGIN-TOP: 15px" align="center">
	<FORM name="regForm" action="user.action" method="post">
	<input type="hidden" name="op" value="reg" />
		<br/>用&nbsp;户&nbsp;名 &nbsp;
			<INPUT class="input" tabIndex="1" tryp="text" maxLength="20" size="35" name="uname" onblur="checkUname( this.value)">
		
		     <div id="result" ></div>
		
		<br/>密&nbsp;&nbsp;&nbsp;&nbsp;码 &nbsp;
			<INPUT class="input" tabIndex="2" type="password" maxLength="20" size="40" name="upass">
		<br/>重复密码 &nbsp;
			<INPUT class="input" tabIndex="3" type="password" maxLength="20" size="40" name="upass1">
		<br/>性别 &nbsp;
			女<input type="radio" name="gender" value="1">
			男<input type="radio" name="gender" value="2" checked="checked" />
		<br/>请选择头像 <br/>
		<image src="image/head/1.gif"/><input type="radio" name="head" value="1.gif" checked="checked">
		<img src="image/head/2.gif"/><input type="radio" name="head" value="2.gif">
		<img src="image/head/3.gif"/><input type="radio" name="head" value="3.gif">
		<img src="image/head/4.gif"/><input type="radio" name="head" value="4.gif">
		<img src="image/head/5.gif"/><input type="radio" name="head" value="5.gif">
		<BR/>
		<img src="image/head/6.gif"/><input type="radio" name="head" value="6.gif">
		<img src="image/head/7.gif"/><input type="radio" name="head" value="7.gif">
		<img src="image/head/8.gif"/><input type="radio" name="head" value="8.gif">
		<img src="image/head/9.gif"/><input type="radio" name="head" value="9.gif">
		<img src="image/head/10.gif"/><input type="radio" name="head" value="10.gif">
		<BR/>
		<img src="image/head/11.gif"/><input type="radio" name="head" value="11.gif">
		<img src="image/head/12.gif"/><input type="radio" name="head" value="12.gif">
		<img src="image/head/13.gif"/><input type="radio" name="head" value="13.gif">
		<img src="image/head/14.gif"/><input type="radio" name="head" value="14.gif">
		<img src="image/head/15.gif"/><input type="radio" name="head" value="15.gif">
		<br/>
		
		验证码：
	<input type="text" name="val_code" />
	<img id="image01" src="image.jsp"/>
	<a href="javascript:void(0)"  onclick="changeImage()">看不清</a>
	<br/>
			<INPUT class="btn" tabIndex="4" type="submit" value="注 册">
	</FORM>
</DIV>

<%@ include file="bottom.jsp"%>