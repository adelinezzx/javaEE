<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
 <script type="text/javascript">
 $(function(){
	 
	  $('#resorderListTable').datagrid({/*  产品信息的表格 */
	        url:'resadmin/resorder.action?op=showOrderList',
	        pagination:true, 
	        pageSize:100,
	        pageList:[20,50,100,150,200,300],
	        title:"订单列表",
	        fiField:"roid",
	        rownumbers:true,
	        fit:true,
	        nowrap:true,
	        sortName:"roid",
	        sortOrder:"desc",
	        singleselect:true,
	        columns:[[
	            {field:'roid',title:'订单号',width:30},
	            {field:'userid',title:'用户编号',width:100},
	            {field:'address',title:'配送地址',width:100,align:'right'},
	            {field:'tel',title:'联系电话',width:100,align:'right'}  ,
	            {field:'ordertime',title:'下单时间',width:100,align:'right'},
	            {field:'deliverytime',title:'配送时间',width:100,align:'right'},
	            {field:'ps',title:'备注',width:100,align:'right'} ,
	            {field:'status',title:'处理状态',width:100,align:'right'},
	            {field:'_operate',    // _operate的作用是告诉easyUI 这个列不是   从json中取出来的
	            	title:'操作',width:150,align:'right',formatter:function(val,row,index){
	            	var str="<a href='javascript:void(0)' onclick='showResorderDetail("+index+")' >详情</a>";
	            	if(row.status=='0'){
	            		str+= "--<a href='javascript:void(0)' onclick='printOrder("+index+")' >打单</a>";
	            		str+= "--<a href='javascript:void(0)' onclick='transfer("+index+")' >配送</a>";
	            	}
	            	return  str ;
	            }
	            } 
	        ]] 
	  });
 });
 
 /*打单  */
 function printOrder(index){
	 $("#resorderListTable").datagrid("selectRow",index);
	 var row =  $("#resorderListTable").datagrid("getSelected");//得到选中的行
	 $.ajax({
		 url:"resadmin/resorder.action?op=printOrder",
		 type:"POST",
		 dataType:"JSON",
		 data:"roid=" + row.roid ,
		 success:function(data){
			 if(data.code == 1){
				 alert("打印成功！");
			 }
		 }
		 
	 });
 }
 
 /* 详情 */
 function  showResorderDetail(index){
	 $("#resorderListTable").datagrid("selectRow",index);
	 var row =  $("#resorderListTable").datagrid("getSelected");//得到选中的行 
 
		$("#resorderItemListTable")
					.datagrid(
							{
								url : "resadmin/resorder.action?op=showOrderItemList&roid="
										+ row["roid"],   //得到选中行的roid 来传送

								fitColumns : true,
								fit : true,
								nowrap : true,
								sortName : "roid",
								sortOrder : "desc",
								singleselect : true,
								//如果json中的数除了 total ，rows之外还有别的  就通过   onLoadSuccess来取
                                onLoadSuccess:function(data){
                                	$("#orderPs").html(data.msg);
                                },
                                columns:[[
                          	            {field:'fid',title:'菜品编号',width:30},
                          	            {field:'fname',title:'菜品名',width:30},
                          	            {field:'realprice',title:'菜品价格',width:30},
                          	            {field:'num',title:'菜品数量',width:30} ,
                          	            {field:'smallcount',title:'价格总计', width:30} 
                          	             ]]
							});
		}
 
 function  transfer(index){
	 $("#resorderListTable").datagrid("selectRow",index);
	 var row =  $("#resorderListTable").datagrid("getSelected");//得到选中的行 
	 var roid = row["roid"];
	 $.ajax({
		 url:"resadmin/resorder.action?op=transfer&roid=" + roid,
		 type:"POST",
		 dataType:"JSON",
		 success:function(data){
			  if(data.code==1){
				  $("#resorderListTable").datagrid("reload" );
			  }
		 }
	 });
 }
 
	</script>
</head>
 
<body  class="easyui-layout"     style=" width:100%;height:100%;"><!--easyui布局  -->
	
	<div data-options="region:'center',title:'订单',iconCls:'icon-ok'  "   style=" width:100%;height:70%;">
							 <table id="resorderListTable"></table>
	 </div>
	 
	 
	 
	  <div   data-options="region:'south',split:true" style="height:30%;"> 
	  
	          <div class="easyui-layout"   data-options="fit:true">
			            <div  data-options="region:'center',title:'订单详情',iconCls:'icon-ok'  "   style ="overflow:hidden;"  >
			                     	 <table id="resorderItemListTable"></table>
			            </div>
			            
			              <div data-options="region:'east',title:'附言',iconCls:'icon-ok'  "    style=" width:200px;overflow:hidden;"  >
			                     	 <div id="orderPs"></div>
			            </div>
	            
	            </div>
	  </div>
</body>
</html>