<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="header.jsp" %>
    
    
	
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD><IMG height=72 src="images/001.jpg" width=595></TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD width="90%">
						<div class='cnt'><marquee
							style="FONT-SIZE: 13px; COLOR: #0000FF" scrollamount='5'
							direction='left'><IMG height=15 src="images/tp009.gif"
							width=15> 欢迎您使用我学我会网上订餐系统，祝您用餐愉快！</marquee></div>
						&nbsp;&nbsp;</TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD align=right background="images/004.gif" height=19></TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width="96%" align=center border=0>
				<TBODY>
					<TR>
						<TD>

						<TABLE cellSpacing=1 cellPadding=1 width="100%" align=center
							bgColor=#c0c0c0 border=0>
							<TBODY>
								<TR bgColor=#dadada>

									<TD width="100%" align="right"><a href="resfood.action?op=addCart">
									     <img  src="images/lcart_cn.gif" border=0></a></TD>
								</TR>
							</TBODY>
						</TABLE>
						
						<!-- 分页 -->
						<yc:pageBar href="init.action?a" pageBean="${pageBean }"></yc:pageBar>
						
						<br /><br />
						<BR>
						
						<TABLE cellSpacing=2 cellPadding=1 width="100%" align=center
							border=0>
							<TBODY>
							
							
							<c:forEach items="${pageBean.list }" var="resfood" varStatus="vs" >
							
							   <c:if test="${vs.index%2==0 }">
								<TR>
							   </c:if>
								
									<TD>
									<TABLE height="100%" cellSpacing=1 cellPadding=2 border=0>
										<TBODY>
											<TR>
												<TD vAlign=top width=90 height=90>
												               <!--展示菜品的图片  点击图片可查看菜品详情 根据菜品的id 来查找对应的菜  显示详细信息 -->
												             <A href="resfood.action?op=details&fid=${resfood.fid }" target=_blank>
												                     <IMG height=80 alt=点击图片查看内容 src="images/${resfood.fphoto }" width=80 border=0>
												             </A></TD>
												<TD vAlign=top>
												<TABLE cellSpacing=1 cellPadding=0 width="100%" align=center 	border=0>
														<TBODY><!--展示菜品的菜名  价格   描述    -->
																<TR>
																	<TD><A href="resfood.action?op=details&fid=${resfood.fid }" target=_blank><STRONG>${resfood.fname }</STRONG></A></TD>
																</TR>
																<TR>
																	<TD height=21><FONT color=#ff0000>现价：人民币${resfood.realprice }元</FONT><BR>
																	<a href="resfood.action?op=details&fid=${resfood.fid }">${resfood.detail }</a></TD>
																</TR>
													</TBODY>
												</TABLE>
												</TD>
											</TR>
											<TR>
														<TD height=28>编号: ${resfood.fid }</TD>
														<TD>
														<TABLE cellSpacing=1 cellPadding=0 width=145 border=0>
																	<TBODY>
																			<TR>
																					<TD align="middle" width="70"><a href="resfood.action?op=addCart&fid=${resfood.fid }">
																					          <img src="images/buy_cn.gif" border="0" 	longdesc=""></a> <!--订购 -->
																					</TD>
																					<TD align=middle width=70><A href="resfood.action?op=details&fid=${resfood.fid }" target=_blank>
																					          <IMG 	src="images/detail_cn.gif" border=0></A><!-- 详情 根据菜品的id 来查找对应的菜  显示详细信息 -->
																					</TD>
																			</TR>
																	</TBODY>
														</TABLE>
														</TD>
											</TR>
										</TBODY>
									</TABLE>
									</TD>
									
									
									
									
									
									
							 <c:if test="${vs.index%2==1 }">
								</TR>
							  </c:if>
								
								</c:forEach>
								
							</TBODY>
						</TABLE>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
		
		


 <%@ include file="bottom.jsp" %>