<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adeline talkroom</title>
<style>
     *{
        margin:0px;
        padding:0px;
     }
      #mydiv{
      width:600px;
      height:400px;
      margin-left:300px ;
      background-color: gray;
       }
       #myform {
          margin:100px;
          padding-top:100px;
       }
</style>
</head>
<body>
       <div id="mydiv">
           <form id="myform"   action="doindex.jsp">
             <p>  用户名:  <input type="text" name="username" id="username"/>  <p/></br>
             <p>  密&nbsp&nbsp&nbsp码:  <input type="text" name="password" id="password"/>  <p/> 
             <p>   <input type="submit" value="登录" name="relogin"  /></p>
           </form>
       </div>
</body>
</html>