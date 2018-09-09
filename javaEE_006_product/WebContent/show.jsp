<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.model.PageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
     <form name ="myform" action="doProduct.jsp" method="post" >
     
         <input type="hidden" name="pages"  value="1"/>
         <input type="hidden" name="pagesize"  value="5"/>
         
         名字：
         <input type="text" name="pname" value="<%= session.getAttribute("pname")==null?"" : (String)session.getAttribute("pname") %>" /> <br>
         排序列：<select name="ordercolumn">
                          <% 
                              //判断session中存的orderColumn 是哪个列
                            if(session.getAttribute("ordercolumn") != null){
                            	String ordercolumn = (String) session.getAttribute("ordercolumn");
                            	if( "pid".equals(ordercolumn)){
                          %>
				                          <option value="pid" selected>编号</option>
				                          <option value="pname">产品名</option>
				                          <option value="price">价格</option>
                          <%
                            	}else if("pname".equals(ordercolumn) ){
                            		
                            %>
	                            		<option value="pid">编号</option>
	                                    <option value="pname" selected>产品名</option>
	                                    <option value="price">价格</option>
                            <%
                                 	}else{
                            %>
			                            <option value="pid">编号</option>
			                            <option value="pname" selected>产品名</option>
			                            <option value="price">价格</option>
			                 <%
                                 	}
                              }
			                 %>
                    </select><br/>
          排序方式：
              
              <%
		              if(session.getAttribute("orderway") != null){
		              	String orderway = (String) session.getAttribute("orderway");
		              	if("desc".equals(orderway)){
              %>
				             <input type="radio" name="orderway" value="desc" checked/>降序
				             <input type="radio" name="orderway" value="asc" />升序<br/>
		       <%
		              	}else{
		       %>
		                     <input type="radio" name="orderway" value="desc"  />降序
				             <input type="radio" name="orderway" value="asc" checked/>升序<br/>
				<%
		              	}
		           }
				%>    
				         
             <input type ="submit" value="查询"/>
             
     </form>
	<table border="1px" width="500px">
		<thead>
			<tr>
				<td>编号</td>
				<td>产品名</td>
				<td>价格</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<%
				PageBean pageBean = (PageBean) session.getAttribute("pageBean");
				List<Object> list = (List<Object>) pageBean.getList();
				for (Object object : list) {
					Map<String, String> map = (Map<String, String>) object;
			%>
			<tr>
				<td><%=map.get("PID")%></td>
				<td><%=map.get("PNAME")%></td>
				<td><%=map.get("PRICE")%></td>
				<td>操作</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	共<%=pageBean.getTotal()%>
	条记录 / 每页<%=pageBean.getPagesize()%>条 共<%=pageBean.getTotalpages()%>页/当前第<%=pageBean.getPages()%>页
	<!-- //第一页 上一页 下一页 最后一页 -->
	<a href="javascript:void" onclick="goPage(1,5)">第一页</a>
	<a href="javascript:void" onclick="goPage(<%= pageBean.getPrePage() %>,5)">上一页</a>
	<a href="javascript:void" onclick="goPage(<%= pageBean.getNextPage() %>,5)">下一页</a>
	<a href="javascript:void" onclick="goPage(<%= pageBean.getTotalpages() %>,5)">最后一页</a>
	
	<%-- <a href="doProduct.jsp?pages=1&pagesize=5">第一页</a>
	<a href="doProduct.jsp?pages=<%=pageBean.getPrePage()%>&pagesize=5">上一页</a>
	<a href="doProduct.jsp?pages=<%=pageBean.getNextPage()%>&pagesize=5">
		下一页</a>
	<a
		href="doProduct.jsp?pages=<%=pageBean.getTotalpages()%>&pagesize=5">
		最后一页</a> --%>
		
		<script type="text/javascript">
		     function goPage(pages,pagesize){
		    	 document.myform.pages.value = pages;
		    	 document.myform.pagesize.value = pagesize;
		    	 document.myform.submit();
		     }
		</script>
</body>
</html>