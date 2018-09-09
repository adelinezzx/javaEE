<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新产品</title>
<script type="text/javascript">
	var xmlHttp;

	function createXMLHttpRequest() {
		if (window.XMLHttpRequest) {//如果可以取得XMLHttpRequest
			xmlHttp = new XMLHttpRequest(); //mozilia   firefox   safari

		} else if (window.ActiveObject) { //如果可以取到ActiveObject
			xmlHttp = new ActiveObject("Microsoft.XMLHttp"); // Internet explorer 
		}
	}

	function op(url, params) {
		createXMLHttpRequest();
		xmlHttp.onreadystatechange = show;
		xmlHttp.open("POST", url);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(params);
	}

	//回调函数
	function show() {
		//
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				//将这个r 文本转成 JavaScript对象， 就可以 利用 对象名.属性名 
				//转换文本成对象 意味着要做一次  类似于Java的反射 
				var r = xmlHttp.responseText;  //接收到的是一个 json 对象，为了后面  以面向对象的方式来使用，将它转为JavaScript对象
				obj = eval("(" + r + ")");  //利用eval 来转换
				if(obj.code == 1){
					document.getElementById("result").style.display = 'inline';
					document.getElementById("result").innerHTML =obj.msg ;
				}else{
					document.getElementById("result").style.display = 'inline';
					document.getElementById("result").innerHTML = obj.msg;
				}
			}
		}
	}

	function checkpname(pname) {
		op("doCheckpname.jsp", "pname=" + pname);
	}
</script>
</head>
<body>

	<form action="doAdd.jsp" method="post" enctype="multipart/form-data">
		产品名： <input type="text" name="pname" onblur="checkpname(this.value)" />
		               <div id="result" style="display: none"></div><br/>
		价格：    <input type="text" name="price" />
        产品图片：<input type="file" name="pic" /> 
                      <input type="submit" value="添加" />
	</form>


	<!--
     不写的话默认为：enctype="application/x-www-form-urlencoded"  其编码方式为 pname=tt&price=22
   tomcat接到这个参数：String[] strs = str.split("&");  ->  String  ss = strs[0].split("-");
                                      ss:["pname",''tt"],["price","22"]    ->request.parameterMap("pname","tt")
                                                                                                                                             "price" ,"22")
         如果是上传文件的话： 
         客户端页面代码   
         <form action="doAdd.jsp" method="post"   enctype="multipart/form-data"  >    
         分隔线：=====================54853474614                
         =====================54853474614                      
        Content-Disposition: form-data; name="pname"
         tt
         =====================54853474614                      
       Content-Disposition: form-data; name="price"
         22
         =====================54853474614       
           tomcat接到这个参数：
           String.indexOf("=====================54853474614");                                                   
   -->
</body>
</html>