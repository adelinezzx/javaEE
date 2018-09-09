<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ include file="header.jsp" %>

<script type="text/javascript">
     $(function(){
    	 var  newsTreeData=[
				 {
						"id" : 1,
						"text" : "新闻上架",
						"attributes" : {
							"url" : "<iframe id='main' width='100%' style='border:0px;' iframeborder='0'  scrolling='no'    height='100%' style='border:0px;' src='backlogin/manager/news/newsAdd.jsp'   />"
						}
					}, {
						"id" : 2,
						"text" : "新闻浏览",
						"attributes" : {
							"url" : "<iframe id='main' width='100%'   style='border:0px;' iframeborder='0'  scrolling='no'  height='100%' src='backlogin/manager/news/newsShow.jsp'   />"
						}
					},  {
						"id" : 3,
						"text" : "新闻排名",
						"attributes" : {
							"url" : "<iframe id='main' width='100%'  style='border:0px;' iframeborder='0'  scrolling='no'   height='100%' src='backlogin/manager/news/newsRank.jsp'   />"
						}
					} ];
    	 var  productTreeData=[
							{
								"id" : 1,
								"text" : "产品列表",
								"attributes" : {
									"url" : "<iframe id='main' width='100%'  style='border:0px;' iframeborder='0'  scrolling='no'  height='100%' src='backlogin/manager/product/productList.jsp'    />"
								}
							}, {
								"id" : 3,
								"text" : "产品排名",
								"attributes" : {
									"url" : "<iframe id='main' width='100%'  style='border:0px;' iframeborder='0'  scrolling='no'   height='100%' src='backlogin/manager/product/productRank.jsp'   />"
								}
							},{
								"id" : 4,
								"text" : "产品上架",
								"attributes" : {
									"url" : "<iframe id='main' width='100%'  style='border:0px;' iframeborder='0'  scrolling='no'   height='100%' src='backlogin/manager/product/productAdd.jsp'   />"
								}
							} ];
    	 var  adminTreeData=[
    	   				 {
    	   						"id" : 1,
    	   						"text" : "管理员列表",
    	   						"attributes" : {
    	   							"url" : "<iframe id='main'  width='100%'  style='border:0px;' iframeborder='0'  scrolling='no'    height='100%' src='backlogin/manager/admin/adminList.jsp'   />"
    	   						}
    	   					}, {
    	   						"id" : 2,
    	   						"text" : "管理员注册",
    	   						"attributes" : {
    	   							"url" : "<iframe id='main' width='100%'  style='border:0px;' iframeborder='0'  scrolling='no'   height='100%' src='backlogin/manager/admin/adminReg.jsp'   />"
    	   						}
    	   					}  ];
    	 var  jobTreeData=[
        	   				 {
        	   						"id" : 1,
        	   						"text" : "工作招聘",
        	   						"attributes" : {
        	   							"url" : "<iframe id='main'  width='100%'  style='border:0px;' iframeborder='0'  scrolling='no'    height='100%' src='backlogin/manager/job/jobShow.jsp'   />"
        	   						}
        	   					}, {
        	   						"id" : 2,
        	   						"text" : "发表招聘",
        	   						"attributes" : {
        	   							"url" : "<iframe id='main' width='100%'  style='border:0px;' iframeborder='0'  scrolling='no'   height='100%' src='backlogin/manager/job/jobPost.jsp'   />"
        	   						}
        	   					}  ];
    	 showTree("newsTree",newsTreeData);
    	 showTree("productTree",productTreeData);
    	 showTree("adminTree",adminTreeData);
    	 showTree("jobTree",jobTreeData);
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
<body  class="easyui-layout" style="width:100%;height:100%;"    ><!--easyui布局  -->
         <!--将布局分为五大板块 东 南 西 北  中间  -->
        <div data-options="region:'north'" style="height:50px">north</div>
        <div data-options="region:'south',split:true" style="height:50px;">south</div>
        <div data-options="region:'east',split:true" title="East" style="width:200px;">east</div>
        <!-- west板块以树的形式来显示手风琴布局 -->
        <div data-options="region:'west',split:true" title="menus" style="width:200px;">
				<div class="easyui-accordion" style="width: 500px; height: 300px;">
				     <!-- 根目录 -->
					<div title="新闻中心" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">
						     <ul id ="newsTree"  class="easyui-tree"><!--子目录  树  -->
						          
						     </ul>
					</div>
					 
					<div title="产品信息" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;width:100p%;height:100%;">
                                <ul id ="productTree"  class="easyui-tree" style="width:100%;height:100%;"><!--子目录  树  -->
						          
						     </ul>
                    </div>
					
					<div title="管理员" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">
		                 
						     <ul id ="adminTree"  class="easyui-tree"><!--子目录  树  -->
						          
						     </ul>
					</div>			
					<div title="工作信息" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">
		                 
						     <ul id ="jobTree"  class="easyui-tree"><!--子目录  树  -->
						          
						     </ul>
					</div>
				 
				<!--    <div title="configuration" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">configuration</div>
				   
				   <div title="dataDict" data-options="iconCls:'icon-ok'" style="overflow: auto; padding: 10px;">dataDict</div> -->
				</div>
        	</div>
		 
	    <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'  , border:false " style="width:100%;height:100%;">
							<div class="easyui-tabs"  id ="mainTabs"   style="width:100%;height:100%;">
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