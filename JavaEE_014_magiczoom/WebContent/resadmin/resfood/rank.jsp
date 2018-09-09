<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../header.jsp" %>
    
<script type="text/javascript">
     $(function(){
    	 /*  去掉排序  使用默认 */
   	  $('#resorderListTable').datagrid({  
   	        url:'resadmin/resfood.action?op=showFoodSellInfoRank',
   	        pagination:true, 
   	        pageSize:100,
   	        pageList:[20,50,100,150,200,300],
   	        //title:"订单列表",
   	        fiField:"roid",
   	        loadMsg:"正在努力加载数据....",
   	        rownumbers:true,
   	        fit:true,
   	        nowrap:true,
   	        sortName:"sellcount",
   	        sortOrder:"desc",
   	        singleselect:true,
   	        columns:[[
   	            {field:'fid',title:'编号',width:40},
   	            {field:'fname',title:'菜名',width:70},
   	            {field:'sellcount',title:'销售次数',width:100,sortable:true,align:'center'} 
   	        ]] 
   	  });
    });
     
</script>
    </head>  
 <body  class="easyui-layout"  >
         <div data-options="region:'center',title:'订单',iconCls:'icon-ok'  "   style=" width:100%;height:70%;">
							 <table id="resorderListTable"></table>
	     </div>
	     
 </body>