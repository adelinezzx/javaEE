<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<script type="text/javascript">
	var editFlag = undefined;
	$(function() {
		$('#resfoodListTable')
				.datagrid(
						{/*   信息的表格 */
							url : 'resadmin/resfood.action?op=resfoodInfoList',
							pagination : true,
							pageSize : 100,
							pageList : [ 20, 50, 100, 150, 200, 300 ],
							title : "菜品列表",
							fiField : "fid",
							rownumbers : true,
							fit : true,
							nowrap : true,
							sortName : "fid",
							sortOrder : "desc",
							singleselect : true,
							columns : [ [ {
								field : 'fid',
								title : '编号',
								width : 100
							}, {
								field : 'fname',
								title : '菜品名称',
								width : 100
							}, {
								field : 'normprice',
								title : '原价',
								width : 100,
								align : 'center'
							}, {
								field : 'realprice',
								title : '特价',
								width : 100,
								align : 'center'
							}, {
								field : 'detail',
								title : '详情',
								width : 100,
								align : 'center'
							} ] ],
							toolbar : [
									{/*添加产品的弹框   */
										text : "新菜品上架",
										iconCls : 'icon-add',
										handler : function() {
											$("#dlg").dialog('open').dialog(
													'center').dialog(
													'setTitle', '新菜品上架');
										}
									},
									'-',
									{
										text : "修改",
										iconCls : 'icon-edit',
										handler : function() {
											var rows = $('#resfoodListTable')
													.datagrid('getSelections');//选中一行进行编辑   得到选中的行 
											if (rows.length == 1) {
												//选中这一行 的话  触发该事件 
												//如果当前  的状态已经是  编辑状态时  退出编辑状态  
												if (editFlag != undefined) {
													$('#resfoodListTable')
															.datagrid(
																	'endEdit',
																	editFlag);//结束编辑   传入之间  编辑的行 
												}

												if (editFlag == undefined) {

													var index = $(
															"#resfoodListTable")
															.datagrid(
																	'getRowIndex',
																	rows[0]);
													$("#resfoodListTable")
															.datagrid(
																	'beginEdit',
																	index);
													editFlag = index;
												}
											}

										}
									},
									'-',
									{
										text : "保存",
										iconCls : 'icon-save',
										handler : function() {
											$('#resfoodListTable').datagrid(
													'endEdit', editFlag);//会触发  onAfterEdit事件 在那里会更新 代码 
										}
									},
									'-',
									{
										text : "撤销",
										iconCls : 'icon-redo',
										handler : function() {
											editFlag = undefined;
											$('#resfoodListTable').datagrid(
													'rejectChanges');//会触发  onAfterEdit事件 在那里会更新 代码 
										}
									}, '-' ],
							columns : [ [ {
								field : 'fid',
								title : '编号',
								width : 100,
								sortable : true
							}, {
								field : 'fname',
								title : '菜品名称',
								width : 100,
								editor : { //设置其为可编辑
									type : 'validatebox', //设置可编辑格式
									options : {
										required : true
									}
								//设置编辑格式
								}
							}, {
								field : 'normprice',
								title : '原价',
								width : 100,
								align : 'center',
								editor : { //设置其为可编辑
									type : 'validatebox', //设置可编辑格式
									options : {
										required : true
									}
								//设置编辑格式
								}
							}, {
								field : 'realprice',
								title : '特价',
								width : 100,
								align : 'center',
								editor : { //设置其为可编辑
									type : 'validatebox', //设置可编辑格式
									options : {
										required : true
									}
								//设置编辑格式
								}
							}, {
								field : '_operate',
								title : '操作',
								width : 100,
								align : 'center',
								formatter : function(val, row, index) {
									var str = "";
									return str;
								}
							} ] ],
							//当点击结束编辑时，会自动发出onAfterEdit事件  则这个事件处理代码被激活
							onAfterEdit : function(rowIndex, rowData, changes) {
								//在添加完endEdit后  保存时触发
								//
								editFlag = undefined; //重置
								//发请求
								$ .ajax({
											url : 'resadmin/resfood.action?op=updateResfoodInfo',
											data : rowData,
											type : 'POST',
											complete : function(data) {
												$.messager.alter("提示", "成功",
														"info")
											}
										});
							},
							onDblclickCell : function(rowIndex, field, value) {
								if (editFlag != undefined) {
									$("#resfoodListTable").datagrid('endEdit',
											editFlag);
								}

								if (editFlag == undefined) {
									$("#resfoodListTable").datagrid(
											'beginEdit', rowIndex);
									editFlag = rowIndex;
								}
							}

						});
	});
</script>
</head>


<body class="easyui-layout">
	<div data-options="region:'center',title:'订单',iconCls:'icon-ok'  "
		style="width: 100%; height: 70%;">
		<table id="resfoodListTable"></table>
	</div>

	<!-- 弹窗 -->
	<div id="dlg" class="easyui-dialog" title="新菜品上架"
		style="width: 600px; height: 300px;"
		data-options="iconCls:'icon-save',resizable:true,modal:true"
		closed="true" buttons="#dlg_buttons">


		<form id="form" method="post" novalidate enctype="multipart/form-data">
			<lable>菜品名：</lable>
			<input type="text" id="fname"><br />
			<lable>原价：</lable>
			<input type="text" name="normprice" id="normprice"> <br />
			<lable>特价：</lable>
			<input type="text" name="realprice" id="realprice" /> <br />
			<lable>详情：</lable>
			<div>
				<textarea class="ckeditor" id="detail" name="detail" rows="10"
					cols="30">
									
									     </textarea>

			</div>
			<br />
			<lable>图片：</lable>
			<input type="file" id="fpotho" name="fpotho">
		</form>



	</div>
	<div id="dlg_buttons">
		<a href="javascript:void(0)" id="saveProduct"
			class="easyui-linkbutton" iconCls="icon-ok" style="width: 90px">save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-concel" onclick="javascript:$('#dlg').dialog('close')"
			style="width: 90px">cancel</a>
	</div>


</body>
</html>