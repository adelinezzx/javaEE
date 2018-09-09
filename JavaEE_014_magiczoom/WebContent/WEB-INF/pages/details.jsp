<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<link href="magiczoom/magiczoom.css" rel="stylesheet" type="text/css" media="screen"/>
<script src="magiczoom/magiczoom.js" type="text/javascript"></script>



<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="90" height="90" valign="top"><!-- 显示菜品图片 -->
			<img src="images/${resfood.fphoto }" border="0" width="80" height="80">
		</td>
		<td valign="top">
			<table width="98%" border="0" cellspacing="1" cellpadding="0" align="center">
				<tr>
					<td>
						<strong>${resfood.fname }</strong><!--  显示菜名-->
					</td>
				</tr>
				<tr>
					<td height="21">
						原价：<strike>人民币${resfood.normprice }元</strike><br> <font color="#ff0000">现价：人民币${resfood.realprice }元</font><br>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="30">编号: ${resfood.fid }</td>
		<td>
			<table width="145" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td align="center" width="70">
						<a href="resfood.action?op=addCart&fid=${resfood.fid }">
							<img src="images/buy_cn.gif" border=0><!--订购  -->
						</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<P align=center>
	<STRONG><FONT size=4>详细资料</FONT></STRONG>
</P>
<HR align=center width="100%" color=#000000 noShade SIZE=1>
<P align=center>


	
	<a href="images/${resfood.fphoto }" title="${resfood.fname }" class="MagicZoom">
		<img src="images/${resfood.fphoto }" width="50px" height="50px"/>
	</a>
	
	
</P>
<P align=center>${resfood.detail }</P>
<br>



<%@ include file="bottom.jsp"%>