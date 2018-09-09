<%@ page language="java" contentType="text/html; charset=UTF-8 "
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	window.onload = function() {
		 
		var anodes = document.getElementsByTagName("a");
		for(var i = 0 ; i < anodes.length ; i ++ ){
			anodes[i].onclick = function(){
				
				var request = new XMLHttpRequest();
				var url = this.href ;
				var method  = "GET";
				
				request.open(method,url);
				request.send(null) ;
				
				request.onreadystatechange = function(){
					if(request.readyState == 4){
						if(request.status == 200 || request.status == 304){
							document.getElementById("details").innerHTML = request.responseText ;
								}
					}
				}
				return false ;
			}
		}


	}
</script>
</head>
<body>
	<a href="helloJava.html">java初级</a>
	<br />
	<a>java中级</a>
	<br />
	<a>java高级</a>
	<br />
	<div id="details"></div>
</body>
</html>