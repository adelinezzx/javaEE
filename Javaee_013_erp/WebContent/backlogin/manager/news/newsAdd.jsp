<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>


 <script type="text/javascript">
$(function(){
	 
	 
	 var editor = CKEDITOR.replace('content');//CKeditor的 编辑区
		
		$("#saveNews").click(function(){//点击save  添加新闻
			var explain = editor.getData();
		   //转换代码
		   explain = encodeURIComponent(  escape(   explain  ));
		   var news={
				   "title":$("#title").val(),
				   "laiz":$("#laiz").val(),
				   "newsType":$("#newsType").val(),
				   "content":explain
		   };
		   //图片：这里不是用form表单来提交数据  而是用 ajax  所以 要用ajax来提交文件 
		   $.ajaxFileUpload({
			   type:"POST",
			   fileElementId:['picture'],
			   secureuri:true,
			   url:'backlogin/uploadFile.action?op=news',
			   data:news,
			   dataType:"JSON",
			   success:function(data){
				   if(data.code == 1){
					   if(data.code == 1){
						    alert("添加成功！");
						    loaction.href('backlogin/manager/index.jsp');
					   }else{
						   alert("添加失败！");
						   loaction.href('backlogin/manager/index.jsp');
					   }
				   }
			   }
		   });
		   
		});
	 
		$.ajax({  /* 显示新闻类别 */ 
			url:"backlogin/news.action?op=getAllNewsType",
			type:"POST",
			dataType:"JSON",
			success:function(data){
				$("#newsType").html(' ');
				var selectString = " ";
				if(data.code ==1 ){
					for(var i = 0 ; i < data.obj.length ; i++){
						var item = data.obj[i];
						selectString +='<option value="' + item.typename+'">' + item.typename +'</option>';
						}
					$("#newsType").html(selectString);
				}
				$("#dlg").dialog('open').dialog('center');
			}
		});
}); 
	 
</script>
<body>
  <div id="dlg" class=" " title="新闻上架"  style="width:600px;height:300px;"
          data-options="iconCls:'icon-save',resizable:true,modal:true" closed="true" buttons="#dlg_buttons" >
          
			
						<form id="form"  method="post" novalidate  enctype="multipart/form-data">
							<lable>新闻主题：</lable>
							<input type="text" id="title"><br/>
							<lable>新闻类别：</lable>
							 <select name="newsType" id="newsType"  >
							 
							 </select><br/>
							<lable>新闻来源：</lable>
							<input type="text" name="laiz" id="laiz"/> <br/>
							<lable>新闻图片：</lable>
							<input type="file" name="picture"  id="picture" /><br/>
							 <lable>新闻内容：</lable>
							<textarea class ="ckeditor" id="content"   name = "content"  rows="10" cols="30">
								
							 </textarea><br/>
						</form>
		 
     
       
    </div>
    
     <div id="dlg_buttons">
        <a  href="javascript:void(0)" id="saveNews" class="easyui-linkbutton" iconCls="icon-ok" style="width:90px">save</a>
         <a  href="javascript:void(0)"  class="easyui-linkbutton" iconCls="icon-concel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">cancel</a>
    </div>
</body>
</html>