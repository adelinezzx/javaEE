<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<script type="text/javascript">
 
$(function(){
	
	var editor = CKEDITOR	.replace('demand');//CKeditor的 编辑区
	
	$("#saveProduct").click(function(){//点击save  添加产品
		var explain = editor.getData();
	   //转换代码
	   demand = encodeURIComponent(  escape(   explain  ));
	   var job={
			   "inviter":$("#inviter").val(),
			   "number":$("#number").val(),
			   "address":$("#address").val(), 
			   "wage":$("#wage").val(),
			   "expressdate":$("#expressdate").val(),
			   "demand":demand
	   };
	 
	   $.ajaxFileUpload({
		   type:"POST",
		   secureuri:true,
		   url:'backlogin/uploadFile.action?op=addJob',
		   data:job,
		   dataType:"JSON",
		   success:function(data){
			   if(data.code == 1){
				    alert("添加成功！");
			   }else{
				   alert("添加失败！");
			   }
		   }
	   });
	});
    $('#dg').datagrid({/*  产品信息的表格 */
        url:'backlogin/job.action?op=listJobInfo',
        pagination:true, 
        pageSize:100,
        pageList:[20,50,100,150,200,300],
        title:"招聘列表",
        fiField:"id",
        rownumbers:true,
        fit:true,
        nowrap:true,
        sortName:"id",
        sortOrder:"desc",
        singleselect:true,
        columns:[[
            {field:'id',title:'编号',width:100},
            {field:'inviter',title:'招聘主题',width:100},
            {field:'join_date',title:'发表日期',width:100,align:'right'},
            {field:'expressdate',title:'要求到职时间 ',width:100,align:'right'},
            {field:'number',title:'招聘人数',width:100,align:'right'},
            {field:'demand',title:'要求 ',width:100,align:'right'},
            {field:'wage',title:'薪资 ',width:100,align:'right'},
            {field:'address',title:'工作地点 ',width:100,align:'right'} 
            
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
 
     <div id="dlg" class="easyui-dialog" title="新产品上架"  style="width:600px;height:300px;"
          data-options="iconCls:'icon-save',resizable:true,modal:true" closed="true" buttons="#dlg_buttons" >
          
			
						<form id="form"  method="post" novalidate  enctype="multipart/form-data">
							<lable>招聘主题：</lable>
							<input type="text" id="inviter" name="inviter" ><br/>
							<lable>招聘人数：</lable>
							 <input type="text" name="number" id="number"/> <br/>
							 <lable>工作地点：</lable>
							 <input type="text" name="address" id="address"/> <br/>
							<lable>薪资待遇：</lable>
							<input type="text" name="wage"  id="wage" /><br/>
							<lable>要求到职时间：</lable>
							 <input type="date" name="expressdate"  id="expressdate" /><br/>
							 <lable>招聘要求：</lable>
							 <div>
								    <textarea class ="ckeditor" id="demand"   name = "demand"  rows="10" cols="30">
								
								     </textarea>
								
							</div><br/>
						</form>
		 
     
       
    </div>
    <div id="dlg_buttons">
        <a  href="javascript:void(0)" id="saveProduct" class="easyui-linkbutton" iconCls="icon-ok" style="width:90px">save</a>
         <a  href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-concel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">cancel</a>
    </div>
    
</body>
</html>