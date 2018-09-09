<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>聊天室</title>

<style type="text/css">
body {
	/* background-image: url("../img/7.jpg"); */
	background-color: rgb(239,242,249);
	color: white;
}

#alldiv {
	margin: 0px auto;
	width: 800px;
	height: 550px;
	border: 1px solid white;
}
#loginNamesdiv{
    background-color: rgb(124,163,254);  
    width: 100%;
	height: 50px;
}
#msgdiv {
     background-color: rgb(159,186,255);
	float: left;
	width: 79%;
	height: 60%;
	border: 1px solid white;
	overflow: scroll;
}

#namediv {
 background-color: rgb(221,226,245);
	float: right;
	width: 19%;
	height: 60%;
	border: 1px solid white;
	padding-left: 5px;
}

#senddiv {
	clear: both;
	width: 100%;
	border: 1px solid white;
}

</style>
<script type="text/javascript">
      setInterval("refreshMessage()",2000);
     
     function refreshMessage(){
    	 var url = "doMessage2.jsp" ;
    	 ajaxSend(url,"",func) ;//定时2秒刷新一次页面，此时不带参数
     }
</script>

</head>
<body>
		<!-- ---------------------------------------------------------------------------------------------- -->
		<!-- 加入CKeditor第二步 -->
		<script src="../ckeditor/ckeditor.js"></script>
		<!-- ---------------------------------------------------------------------------------------------- -->
		<div id="alldiv">
		<div id="loginNamesdiv"></div>
			<div id="msgdiv">
			    
				         <%@ include file="showMessage.jsp"%>
			 
			</div>
			<div id="namediv">
			
				<%@ include file="names.jsp"%>
				
			</div>
			<div id="senddiv">
				<form name="myform"  action="doMessage.jsp" method="post">
					<!-- ---------------------------------------------------------------------------------------------- -->
					<!-- 加入CKeditor第三步 -->
					<textarea name="message" id="message" rows="5" cols="80"></textarea>
					<script>
						CKEDITOR.replace("message");
					</script>
					<br />
					<!-- ---------------------------------------------------------------------------------------------- -->
				<!-- 	<input type="submit" value="发送" /><br>   -->
					<!-- 使用submit需要刷新页面，当使用ajax异步请求来刷新发送的消息时，
					用button来调用一个函数可以避免刷新页面 -->
					
					<input type="button" value="发送"  onclick="report()"/><br/>
					
				</form>
				<a href="doLogout.jsp">安全退出</a>
			</div>
		</div>
<!-- 引入common.js -->
 
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">

function func() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
		 
			var r = xmlHttp.responseText;  //接收到的是一个 json 对象，为了后面  以面向对象的方式来使用，将它转为JavaScript对象
			 
			jsonModel = eval("("+r+")"); //利用eval 来转换 jsonModel
			                   
			if(       jsonModel.code == 1       ){
				document.getElementById("messagesList").innerHTML = ' ';  //清空页面
				var messageList  = jsonModel.obj.messageList ;
				var userList = jsonModel.obj.userList ;
				var str = "" ;
				for(var i = 0 ; i<messageList.length ;i++){
					str += messageList[i] ;
				}
				document.getElementById("messagesList").innerHTML = str ;
				//用户列表
				document.getElementById("namediv").innerHTML = '  ' ;
				var str2 = "<br /><br />当前在线人数：" + getNumber (userList.length ) + "<br / >" ;
				str2 += "他们是 : <br/>" ;
				for (var i = 0 ; i< userList.length ; i++ ){
					str2+= userList[i]  + "<br/> ";
				}
				document.getElementById("namediv").innerHTML = str2 ;
			} 
		}
	}
}

    function report(){
    	var  url = "doMessage2.jsp" ;   //doMessage2.jsp 是一个处理ajax请求的服务器页面  它返回一个json的
    	var  m = CKEDITOR.instances.message.getData() ;
    	/* alert( m ); */
    	/* //获取到CKeditor 输入框中的数据， 不能再用之前获取静态的HTML页面的方法来获取，
    	因为在CKeditor的输入框中是以一个动态的形式显示，所以必须使用CKeditor的方法来获取其内容 */
    	var params = "message=" + m ;
    	ajaxSend(    url,   params,    func   );  
    }
    
 // 将在内线人数以图片的显示在div中
    function getNumber (size) {
	var s = size + ""; //将图片转化为字符串的形式
	var sb = "" ;
	//得到当前在下人数得每个数   再转化成图片的形式
	for (var i = 0; i < s.length ; i++) {
		var ch = s.substring(i, i + 1);  
		sb+=   "   <img src = 'images/" + ch + ".gif ' />    "  ;
	}
	return sb ;
}
</script>

 
</body>
</html>

