<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="head">
	您尚未 <a href="login.jsp">登录</a>|<a href="reg.jsp">注册</a>
</div>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	 //setInterval("refreshMessage()", 2000);
/* 
	function refreshMessage() {
		var url = "doshow.jsp";
		ajaxSend(url, "", func);//定时2秒刷新一次页面，此时不带参数
	}

	function func() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				 
				var r = xmlHttp.responseText; //接收到的是一个 json 对象，为了后面  以面向对象的方式来使用，将它转为JavaScript对象

				jsonModel = eval("(" + r + ")"); //利用eval 来转换 jsonModel

				if (jsonModel.code == 1) {
					document.getElementById("head").innerHTML = ' '; //清空页面
					var uname = jsonModel.obj.uname;
					var str2 = "欢迎您:<a>" + uname + "<a/>";
					document.getElementById("head").innerHTML = str2;
				}
			}
		}
	} */
</script>