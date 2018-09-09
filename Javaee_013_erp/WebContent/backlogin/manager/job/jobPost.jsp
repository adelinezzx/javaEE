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
   
});

 
</script>
</head>
<body>
 
 
     <div id="dlg" class=" " title="新产品上架"  style="width:600px;height:300px;"
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