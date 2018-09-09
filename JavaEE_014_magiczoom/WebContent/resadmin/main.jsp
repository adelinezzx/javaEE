<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ include file ="header.jsp" %>
 <script type="text/javascript">
     $(function(){
    	 var  resfoodTreeData=[
				 {
						"id" : 1,
						"text" : "菜品上架",
						"attributes" : {
							"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/news/newsAdd.jsp'   />"
						}
					}, {
						"id" : 2,
						"text" : "菜品浏览",
						"attributes" : {
							"url" : "<iframe width='100%'  height='100%' src='resadmin/resfood/show.jsp'   />"
						}
					},  {
						"id" : 3,
						"text" : "销售排行",
						"attributes" : {
							"url" : "<iframe width='100%'  height='100%' src='resadmin/resfood/rank.jsp'   />"
						}
					} ];
    	 var  resorderTreeData=[
							{
								"id" : 1,
								"text" : "订单处理",
								"attributes" : {
									"url" : "<iframe width='100%'  height='100%' src='resadmin/resorder/resordermanage.jsp'    />"
								}
							}, {
								"id" : 2,
								"text" : "产品浏览",
								"attributes" : {
									"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/product/productShow.jsp'   />"
								}
							},  {
								"id" : 3,
								"text" : "产品排名",
								"attributes" : {
									"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/product/productRank.jsp'   />"
								}
							},{
								"id" : 4,
								"text" : "产品上架",
								"attributes" : {
									"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/product/productAdd.jsp'   />"
								}
							} ];
    	 var  resuserTreeData=[
    	   				 {
    	   						"id" : 1,
    	   						"text" : "客户浏览",
    	   						"attributes" : {
    	   							"url" : "<iframe width='100%'  height='100%' src='resadmin/resuser/rank.jsp'   />"
    	   						}
    	   					}, {
    	   						"id" : 2,
    	   						"text" : "客户贡献排名",
    	   						"attributes" : {
    	   							"url" : "<iframe width='100%'  height='100%' src='resadmin/resuser/show.jsp'   />"
    	   						}
    	   					}  ];
    	 var  databaseTreeData=[
    	    	   				 {
    	    	   						"id" : 1,
    	    	   						"text" : "浏览表",
    	    	   						"attributes" : {
    	    	   							"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/admin/adminList.jsp'   />"
    	    	   						}
    	    	   					}, {
    	    	   						"id" : 2,
    	    	   						"text" : "查询表",
    	    	   						"attributes" : {
    	    	   							"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/admin/adminReg.jsp'   />"
    	    	   						}
    	    	   					},
    	    	   				      {
    	    	   						"id" : 3,
    	    	   						"text" : "备份",
    	    	   						"attributes" : {
    	    	   							"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/admin/adminReg.jsp'   />"
    	    	   						}
    	    	   					} ];
    	 var  websiteTreeData=[
   	    	   				 {
   	    	   						"id" : 1,
   	    	   						"text" : "搜索引擎优化",
   	    	   						"attributes" : {
   	    	   							"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/admin/adminList.jsp'   />"
   	    	   						}
   	    	   					}, {
   	    	   						"id" : 2,
   	    	   						"text" : "版权",
   	    	   						"attributes" : {
   	    	   							"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/admin/adminReg.jsp'   />"
   	    	   						}
   	    	   					},  {
   	    	   						"id" : 3,
   	    	   						"text" : "广告位管理",
   	    	   						"attributes" : {
   	    	   							"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/admin/adminList.jsp'   />"
   	    	   						}
   	    	   					}, {
   	    	   						"id" : 4,
   	    	   						"text" : "在线客服",
   	    	   						"attributes" : {
   	    	   							"url" : "<iframe width='100%'  height='100%' src='backlogin/manager/admin/adminReg.jsp'   />"
   	    	   						}
   	    	   					}  ];
    	 showTree("resfoodTree",resfoodTreeData);
    	 showTree("resorderTree",resorderTreeData);
    	 showTree("resuserTree",resuserTreeData);
    	 showTree("databaseTree",databaseTreeData);
    	 showTree("websiteTree",websiteTreeData);
	});
     
     function showTree(treeId ,treeData){
    	 
    	 $("#" + treeId).tree({
    		 data:treeData,
    		 onClick: function(node){
    					//alert(node.text);   
    					if(node && node.attributes ){
    						openTab(  node );
    					}
    				}
    		 
    	 });
     }
     
     function  openTab(node){
    	 if(  $("#mainTabs" ).tabs("exists",node.text)   ){ //判断选择的 节点是否已经在窗口上
    		   $("#mainTabs" ).tabs("select",node.text);//有则 选中  高亮显示 
    	 }else{
    		   $("#mainTabs" ).tabs("add",{//没有存在则  添加到 窗口上  
    			 title: node.text  ,
    			 closable:true ,
    			 selected:true,
    			 content: node.attributes.url       //显示内容 
    		 });
    	 }
     }
</script>
<body  class="easyui-layout"     style=" width:100%;height:100%;"><!--easyui布局  -->
         <!--将布局分为五大板块 东 南 西 北  中间  -->
        <div data-options="region:'north'" style="height:50px">north</div>
        <div data-options="region:'south',split:true" style="height:50px;">south</div>
        <div data-options="region:'east',split:true" title="East" style="width:200px;">east</div>
        <!-- west板块以树的形式来显示手风琴布局 -->
        <div data-options="region:'west',split:true" title="menus" style="width:200px;">
				<div class="easyui-accordion" style="width: 500px; height: 300px;">
				     <!-- 根目录 -->
					<div title="菜品管理" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">
						     <ul id ="resfoodTree"  class="easyui-tree"><!--子目录  树  -->
						          
						     </ul>
					</div>
					 
					<div title="订单信息" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">
                                <ul id ="resorderTree"  class="easyui-tree"><!--子目录  树  -->
						          
						     </ul>
                    </div>
					
					<div title="客户管理" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">
		                 
						     <ul id ="resuserTree"  class="easyui-tree"><!--子目录  树  -->
						          
						     </ul>
					</div>			
					
				 
					
					<div title="数据库管理" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">
                                <ul id ="databaseTree"  class="easyui-tree"><!--子目录  树  -->
						          
						     </ul>
                     </div>
						 
				  	<div title="网站管理" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">
                                <ul id ="websiteTree"  class="easyui-tree"><!--子目录  树  -->
						          
						     </ul>
                     </div>
				</div>
        	</div>
		 
	    <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'  "   style=" width:100%;height:100%;">
							<div class="easyui-tabs"  id ="mainTabs"   style=" width:100%;height:100%;">
								<div title="欢迎您" style="padding: 10px">
									 Adeline 欢迎您  ;  现在时刻：
									 <%  Date s = new Date() ;
									         SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
									  %>
									  <%= sdf.format(s) %>
								</div>
							 </div>
	   </div>

</body>
</html>