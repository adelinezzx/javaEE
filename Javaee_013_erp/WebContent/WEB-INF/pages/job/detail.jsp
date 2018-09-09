<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix = "yc" uri="http://www/yc/core" %>
  
  
<%@ include file="../header.jsp"%>
<div id="center_line"></div>
<div id="center_top">
	<div id="gongsijianjie">

		<div id="center_top_left_b">
			<ul>
				

			</ul>
		</div>
	</div>

	<div id="toutiaoxinwen">
		<div id="center_top_right_c">
				<div id="center_top_right_c_l"><!--显示点击产品的详细内容  -->
			                <h2>${job.inviter }</h2>
			                <br/>
			                 <hr/>
			                创建时间:${job.join_date  }<br/>
			               <br/>
			               应聘人数:${job.number  }
			               工作地点:${job.address  }<br/>
			               要求：     ${job.demand  }<br/>
			               薪资：     ${job.wage  }<br/>
			               要求到职时间：${job.expressdate  }<br/>
			               <hr/>
			               <br/>
			               <br/>
			                  
			            
				</div>
				
				 <div id="center_top_right_c_r">
                    
                         <center>
			                  申请职位 <br/><br/>
	                          <form action="job.action?op=acjob&jobid=${job.id }" method="post">
	                               <input type="hidden" name="jobid" value="${job.id }"/>
	                               
	                               姓名：<input type="text" name="username" ><br/>
	                               
	                               性别：<input type="radio" name="sex">boy
	                                         <input type="radio" name="sex">girl<br/>
	                               学校：<input type="text" name="school" ><br/>
	                               
	                               电话：<input type="text" name="telephone" ><br/>
	                               
	                               邮件：<input type="text" name="email" ><br/>
	                               <input type="submit"  value="提交申请信息"/>
	                          </form>		           
			           </center>
                          
                    </div>
		</div>
	</div>
	<%@ include file="../bottom.jsp"%>