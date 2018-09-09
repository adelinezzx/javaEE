<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 
 <script type="text/javascript">
    $(function(){
    	 $('#dg').datagrid({/*  产品信息的表格 */
    	        url:'resadmin/resuser.action?op=show',
    	        pagination:true, 
    	        pageSize:100,
    	        pageList:[20,50,100,150,200,300],
    	        title:"用户列表",
    	        fiField:"userid",
    	        rownumbers:true,
    	        fit:true,
    	        nowrap:true,
    	        sortName:"userid",
    	        sortOrder:"desc",
    	        singleselect:true,
    	        columns:[[
    	            {field:'userid',title:'编号',width:100},
    	            {field:'username',title:'用户名',width:100},
    	            {field:'dealcount',title:'用户花费',width:100,align:'right'},
    	            {field:'email',title:'用户邮箱',width:100,align:'right'}  
    	        ]],
    	        toolbar: [{/*添加产品的弹框   */
    	    		iconCls: 'icon-add',
    	    		handler: function(){
    	    			$("#dlg").dialog('open').dialog('center').dialog('setTitle','新产品上架' );
    	    		}
    	    	},'-',{
    	    		iconCls: 'icon-help',
    	    		handler: function(){alert('help')}
    	    	}]
    	    });
    });
</script>
 </head>
<body>
 <table id="dg"></table>
</body>
</html>