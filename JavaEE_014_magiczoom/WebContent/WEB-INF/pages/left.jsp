<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function() {
	     $.ajax({/*  动态显示  用户登录和退出   */
	    	 url:"resuser.action?op=isResuserLogin",
	    	type:"POST",
	    	dataType:"JSON",
	    	success:function(data){
	    		var str = "";
	    		if(data.code == 1){
	    			str +=  "<ul>";
	    			str +=  "<li>欢迎您:   "+data.obj.username+"</li>";
	    			str +=  "<li><a href='resuser/resuser.action?op=logout'>安全退出 </a></li>";
	    			str +=  "<li><a href='resuser/resuser.action?op=sendMail'>修改密码 </a></li>";
	    			str +=  "<li><a href='resuser/resorder.action?op=showOrders'>查看历史订单 </a></li>";
	    			 
	    			str +=  " </ul> ";
	    		}else{
	    			
	    			str = "<a href='resuser.action?op=toLogin'>登录</a>| <a href='resuser.action?op=toReg'> 注册 </a>"
	    		}
	    		$("#loginStatus").html(str);
	    	}
	    	 
	     });
		
	     
	   /*   $.ajax({
	    	   url:"resfood.action?op=redis",
		    	type:"POST",
		    	dataType:"JSON",
		    	success:function(data){
		    		var str="";
		    		if(data){
		    			for(var i = 0 ; i<data.length ; i++ ){
		    				var arr = data[i].split("_");
		    				str+="<a  href='resfood.action?op=details&fid =  " +  
		    						arr[0] + 
		    						"' ><img src='"+arr[2]+"'  width='10px'  height='10px'/> "
		    						+arr[1] + "----</a><br/>" ;
		    			}
		    			$
		    		}
		    	}
	    	 
	     }); */
		

	});
</script>


<TABLE cellSpacing=0 cellPadding=0 width=776 align=center border=0>
	<TBODY>
		<TR vAlign=top>
			<TD width=181 background="images/002.gif">
			<TABLE cellSpacing=0 cellPadding=0 width=181 border=0>
				<TBODY>
					<TR>
						<TD><IMG height=234 src="images/left_top.jpg" width=181></TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD align=middle height=30><FONT color=#000000> <SCRIPT
							language=javascript src="images/DateTime2.js"></SCRIPT> <SCRIPT
							language=javascript>tick('cn');</SCRIPT> </FONT></TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD background="images/003.gif" height=4></TD>
					</TR>
					<TR>
						<TD>&nbsp;
						
						      <div id ="loginStatus" ></div>
						</TD>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD background="images/003.gif" height=4></TD>
					</TR>
					<TR>
						<TD align=middle>
						<TABLE cellSpacing=0 cellPadding=0 width="94%" border=0>
							<TBODY>
								<TR>
									<TD>&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
			
			<TD>