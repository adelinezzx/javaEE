<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
   <%@ include file ="../header.jsp" %>
   
  <script type="text/javascript" src="../js/jquery-1.11.1.js"> </script>
<script type="text/javascript"  src="back/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	window.onload = function() {
		CKEDITOR.replace('content');
	}
</script>
<br />
 	 
	<!--      导航        -->
	<DIV>
		&gt; &gt;<b><a href="index.jsp">论坛首页</a>&gt; &gt; 
		<A href="topic.action?op=list&boardid=${board.boardid }"> ${board.boardname  }</A> &gt; &gt;     发表话题 
		</b>
	</DIV>
	<br />
	
	<div class="t" style="margin-top: 15px" algin="center">
	       <center>  
	           发表话题 
	            <hr  width=80%  />
	            <form action="topic.action" method="post"><!-- 设置方法为post 后  用户名和密码信息被隐藏 需要重新传入才能得到UID -->
	                <input type="hidden" name = "op" value="post"/>
	               <input type="hidden"  name = "uid" value="${user.uid }">
	               <input type="hidden" name = "boardid" value="${board.boardid }"/>
	               <br/>
	               标题： &nbsp; 
	               <input  type="text" name="title"/>
	               <br/>
	               内容：
	               <textarea  name="content" rows="5" cols ="50"  > </textarea>
	               <br/>
	               <input class ="btn" type="submit"  tabindex="6" value="发表"/>
	            </form>      
	       
	       </center>
	
	
	</div>
	
	<%@  include file="../bottom.jsp" %>