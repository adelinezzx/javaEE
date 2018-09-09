<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <%@ include file="../header.jsp" %>

<script type="text/javascript">
   $(function(){
	   $(  "#regbtn"  ).click(    function(){
		   //  alert(   $("#regForm").serialize()    );
		  	     $.ajax({
		  	    	  url:'backlogin/admin.action?op=reg',
		  	    	  type:'POST',
		  	    	  dataType:'JSON',
		  	    	  data:    $("#regForm").serialize()     ,
		  	    	  success:function(data){
		  	    		  if(data.code == 1){
		  	    			  alert("注册成功！");
		  	    		  }
		  	    	  }
		  	     }  );  
	   });
	   
	   function clearAll(){
		   $("#username").val('');
		   $("#userpassword").val('');
	   }
	   
   });
</script>
</head>
<body>
 
<DIV class="t" style="MARGIN-TOP: 15px" align="center">
	<FORM id="regForm" action="backlogin/admin.action?op=reg" method="post">
		<br/> <p>用户名 &nbsp;<INPUT class="input" tabIndex="1"  type="text"    maxLength="20" size="35" name="username"> <p/>
		                    密　码 &nbsp;<INPUT class="input" tabIndex="2"  type="password"  maxLength="20" size="40" name="userpassword"> 
	   
		<br/><INPUT id="regbtn"  tabIndex="6"  type="button" value="注册">
	</FORM>
</DIV>
</body>
</html>
</body>
</html>