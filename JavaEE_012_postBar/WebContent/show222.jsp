<%@page import="com.yc.bean.User"%>
<%@page import="com.yc.bean.Topic"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.model.PageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#msgdiv {
	text-shadow: gray;
	height: 400px;
	width: 800px;
	border: 1px solid gray;
}
</style>
<script type="text/javascript">

    function hideAndShow( tid  )  {   //点击more ，关闭和显示内容
    	
    	if(    document.getElementById("getBeautifulContents_"+ tid   ) .style.display =='none'    ){
    		    document.getElementById("getBeautifulContents_"+ tid   ) .style.display ='block' ;
    		    document.getElementById("contents_"+ tid   ).style.display = 'none'    ;
    	}else{
    		document.getElementById("getBeautifulContents_" + tid   ).style.display = 'none'  ;
    		document.getElementById("contents_" + tid    ).style.display = 'block'  ;
    	}
    }
</script>
</head>
<body>
	<div id="head">

		<%-- <%@ include file="head.jsp"%> --%>
		<!--头部信息的显示。未登录和登录后的变化  -->
		<%
			if(session.getAttribute("user")  !=null &&  !session.getAttribute("user").equals("") ){
				 
		%>
		欢迎您:<a><%=(   (User)session.getAttribute("user")   ).getUname()%></a>
		<%
			}else{
		%>
		<%@ include file="head.jsp"%>
		<%
			}
		%>
		<br />
	</div>
	<hr />
	<a href="dologout.jsp" id="logout"> 退出登录</a>
	<hr />
	<div id="loginstatus">
		<span> <a href="back/post.jsp">发表</a>
		</span>
		<div id="welcome"></div>
	</div>
	<div id="welcome"></div>
	<div id="msgdiv">
		<%
			PageBean pageBean = (PageBean) session.getAttribute("pageBean");
				List  list =   pageBean.getList();
				for (Object object : list) {
				Topic t = (Topic) object ;
		%>
		<div id="getBeautifulContents_<%=t.getTid()%>">
			<%=t.getBeautifulContents()%></div>

		<div id="contents_<%=t.getTid()%>" style="display: none">
			<%=t.getContents()%>
			<br />
			<%
				if(    t.getPic() != null && !"".equals(   t.getPic()  )     ){
																			        	  out.print("<img height = 30px  width =30px src =" + t.getPic() + " />") ;
																			          }
			%>

		</div>
		<a href="javascript:void"
			onclick="hideAndShow(  <%=t.getTid()%>     )">more...</a> 作者:<%=t.getUname()%>


		<%
			if(  session.getAttribute("user") != null  ){
			    	  User user = (User) session.getAttribute("user");
			    	  if(   user.getUid() == t.getUid()    ){
			     		  out.println("<a href = 'back/doUpdate.jsp'>修改</a>");
			    	  }
			      }
		%><hr />
		<%
			}
		%>

	</div>


	共<%=pageBean.getTotal()%>
	条记录 / 每页<%=pageBean.getPagesize()%>条 共<%=pageBean.getTotalpages()%>页/当前第<%=pageBean.getPages()%>页
	<!-- //第一页 上一页 下一页 最后一页 -->
	<a href="javascript:void" onclick="goPage(1,5)">第一页</a>
	<a href="javascript:void"
		onclick="goPage(<%=pageBean.getPrePage()%>,5)">上一页</a>
	<a href="javascript:void"
		onclick="goPage(<%=pageBean.getNextPage()%>,5)">下一页</a>
	<a href="javascript:void"
		onclick="goPage(<%=pageBean.getTotalpages()%>,5)">最后一页</a>


	<script type="text/javascript">
	        //分页
		    function goPage(pages,pagesize){
		    	 document.myform.pages.value = pages;
		    	 document.myform.pagesize.value = pagesize;
		    	 document.myform.submit();
		     }
	        //退出登录
		   document.getElementById("logout").onclick = function(){
		     	var request = new XMLHttpRequest();
		     	var url = this.href;  //取到当前
		     	var method = "POST" ; 
		     	request.open(method,url);   
		     	request.send(null) ;
		     	request.onreadystatechange = function(){
		     	if(request.readyState == 4){
		     	       if(request.status == 200){
		     	        	  var str = "您尚未 <a href='login.jsp'>登录</a>|<a href='reg.jsp'>注册</a>"  ;
		     	             document.getElementById("head").innerHTML = str; 
		     	           }
		     	        }
		     	 }
		     	   return false;
		     	}
	    </script>
</body>
</html>