<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="yc" uri="http://www/yc/core"%>
<%@include file="../../header.jsp"%>

  <script type="text/javascript">
     	$(function(){
     		   $.ajax({
     			      url:"reply.action?op=glktimes&topicid=${param.topicid }",
     			      type:"POST",
     			      datatype:"JSON",
     			      success:function(data){
     			    	 if(data.code==1){
     	    				   $("#glktimes").html("点赞数：" + data.obj);
     	    			   }
     			      }
     			   
     		   });
     	});
     	
    function glk(  topicid){
	     		$.ajax({
				      url:"reply.action?op=glk&topicid=" + topicid ,
				      type:"POST",
				      datatype:"JSON",
				      success:function(data){
				    	 if(data.code==1){
		    				   $("#glktimes").html("点赞数：" + data.obj);
		    			   }else if(data.code == 0){
		    				   $("#glktimes").html("点赞数：" + data.errmsg);
		    			   }
				      }
				   
			   });
     		
     	}
    
    function del(replyid){
    	var  r = confirm("是否确定删除？"  + replyid);
    	if(r   ==  true ){
    		location.href="reply.action?op=del&replyid=" + replyid ;
    		
    	}
    	
    }
</script>

<DIV>
	<br />
	<!--      导航        -->
	<DIV>
		&gt; &gt;<b><a href="index.jsp">论坛首页</a>&gt; &gt;${board.boardname  }
		</b>
	</DIV>
	<br />
	<!--      回复、新帖        -->
	<DIV>
		<A href="back/postreply.jsp"><IMG src="image/reply.gif" border="0"  id=td_post></A> 
		<A href="back/post.jsp"><IMG src="image/post.gif"   border="0" id=td_post></A>
		
		 <!--点赞  -->
		 <c:if test="${user != null  }">
		      <a href="javascript:glk(${param.topicid })">点赞</a>
		 </c:if>
		 <div class="h"  id="glktimes">
		     点赞数：
		 </div>
	</DIV>
	
 
	<!--         翻 页         -->
	<DIV>
		<yc:pageBar href="topic.action?op=detail&topicid=${param.topicid }"  pageBean="${replyListPageBean }"></yc:pageBar>

	</DIV>
	<!--      本页主题的标题        -->
	<DIV>
		<TABLE cellSpacing="0" cellPadding="0" width="100%">
			<TR>
				<TH class="h">本页主题: 灌水</TH>
			</TR>
			<TR class="tr2">
				<TD>&nbsp;</TD>
			</TR>
		</TABLE>
	</DIV>

	<!--      主题        -->

	<DIV class="t">
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed"
			cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%"><B> ${topic.uname }</B><BR /> <image
						src="image/head/${topic.head }" /><BR /> 注册:${topic.regtime }<BR /></TH>
				<TH>
					<H4>${topic.title  }</H4>
					<DIV>${topic.content  }</DIV>
					<DIV class="tipad gray">发表：[${topic.publishtime  }] &nbsp;
						最后修改:[${topic.modifytime  }]</DIV>
						<%-- <a>${topic.boardid  }</a> --%>
				</TH>
			</TR>
		</TABLE>
	</DIV>

	<!--      回复        -->
	<c:forEach items="${replyListPageBean.list }" var="reply">
		<DIV class="t">
			<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed"
				cellSpacing="0" cellPadding="0" width="100%">
				<TR class="tr1">
					<TH style="WIDTH: 20%"><B>${reply.uname }</B><BR /> <BR /> <image
							src="image/head/${reply.head }" /><BR /> 注册:${reply.regtime }<BR /></TH>
					<TH>
						<H4>${reply.title }</H4>
						<DIV>${reply.content }</DIV>
						<DIV class="tipad gray">
							发表：[${reply.publishtime }] &nbsp; 最后修改:[${reply.modifytime }] 
							
							<c:if test="${user.uid == reply.userid  }"> 
									 <A href="javascript:del(${reply.replyid })">[删除]</A> 
									 <A href="reply.action?op=toupdate&replyid=${reply.replyid }">[修改]</A>
							 
							 </c:if>
						</DIV>
					</TH>
				</TR>
			</TABLE>
		</DIV>
	</c:forEach>


	<DIV>
	       <yc:pageBar href="topic.action?op=detail&topicid=${param.topicid }"  pageBean="${replyListPageBean }"></yc:pageBar>
	</DIV>
</DIV>


<%@ include file="../../bottom.jsp"%>