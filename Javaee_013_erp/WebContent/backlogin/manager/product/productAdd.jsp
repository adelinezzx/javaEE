<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<script type="text/javascript">
 
$(function(){
	
	var editor = CKEDITOR	.replace('product_explain');//CKeditor的 编辑区
	
	$("#saveProduct").click(function(){//点击save  添加产品
		var explain = editor.getData();
	   //转换代码
	   explain = encodeURIComponent(  escape(   explain  ));
	   var product={
			   "product_name":$("#product_name").val(),
			   "product_class":$("#product_class").val(),
			   "product_in":$("#product_in").val(),
			   "product_explain":explain
	   };
	   //图片：这里不是用form表单来提交数据  而是用 ajax  所以 要用ajax来提交文件 
	   $.ajaxFileUpload({
		   type:"POST",
		   fileElementId:['product_picture'],
		   secureuri:true,
		   url:'backlogin/uploadFile.action?op=product',
		   data:product,
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
    
    			$.ajax({  /* 显示产品类别 */
    				url:"backlogin/product.action?op=getAllProductClass",
    				type:"POST",
    				dataType:"JSON",
    				success:function(data){
    					$("#product_class").html(' ');
    					var selectString = " ";
    					if(data.code ==1 ){
    						for(var i = 0 ; i < data.obj.length ; i++){
    							var item = data.obj[i];
    							selectString +='<option value="' + item.protype+'">' + item.protype +'</option>';
    							}
    						$("#product_class").html(selectString);
    					}
    					
    				}
    			});
    			$("#dlg").dialog('open').dialog('center').dialog('setTitle','新产品上架' );
    	 
   
});

 
</script>
<body>
 <div id="dlg" class="" title="新产品上架"  style="width:600px;height:300px;"
          data-options="iconCls:'icon-save',resizable:true,modal:true" closed="true" buttons="#dlg_buttons" >
          
			
						<form id="form"  method="post" novalidate  enctype="multipart/form-data">
							<lable>产品名：</lable>
							<input type="text" id="product_name"><br/>
							<lable>产品类型：</lable>
							<select  name="product_class" id="product_class">
							                       
							  </select><br/>
							<lable>来源：</lable>
							<input type="text" name="product_in" id="product_in"/> <br/>
							<lable>图片：</lable>
							<input type="file" name="product_picture"  id="product_picture" /><br/>
							<lable>详情：</lable>
							<div>
								    <textarea class ="ckeditor" id="product_explain"   name = "Product_explain"  rows="10" cols="30">
								
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