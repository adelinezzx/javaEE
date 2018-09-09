
--通告的插入 
insert into pronunciament(title,content,join_date) values('通告1','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告2','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告3','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告4','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告5','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告6','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告7','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告8','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告9','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告10','这是一个新的通告',now()   ) ;
insert into pronunciament(title,content,join_date) values('通告11','这是一个新的通告',now()   ) ;

insert into pronunciament(title,content,join_date) values('关于十九大会议召开的一条通告','这是一个新的通告',now()   ) ;

--新闻类别的插入
insert into news_class(typename) values('业界动态');
insert into news_class(typename) values('国内新闻');
insert into news_class(typename) values('时尚潮流');
insert into news_class(typename) values('最新热点');
select * from news_class;

select id from  news_class where typename = '最新热点'
--新闻
insert into news(title,typeid,  content, picture , laiz, join_date,  change_date,  imgurl,  picnum , imgtext , imglink , imgAlt)
   values('一关于来着小猪佩奇的新闻','1','very good ' ,' ', '小猪佩奇' ,now() , now() ,' ' ,' ' , ' ' , ' ' ,' ' );
insert into news(title,typeid,  content, picture , laiz, join_date,  change_date,  imgurl,  picnum , imgtext , imglink , imgAlt)
   values('一关于来着小猪佩奇的新闻','1','very good ' ,' ', '小猪佩奇' ,now() , now() ,' ' ,' ' , ' ' , ' ' ,' ' );
insert into news(title,typeid,  content, picture , laiz, join_date,  change_date,  imgurl,  picnum , imgtext , imglink , imgAlt)
   values('一关于来着小猪佩奇的新闻','1','very good ' ,' ', '小猪佩奇' ,now() , now() ,' ' ,' ' , ' ' , ' ' ,' ' );
insert into news(title,typeid,  content, picture , laiz, join_date,  change_date,  imgurl,  picnum , imgtext , imglink , imgAlt)
   values('一关于来着小猪佩奇的新闻','1','very good ' ,' ', '小猪佩奇' ,now() , now() ,' ' ,' ' , ' ' , ' ' ,' ' );
insert into news(title,typeid,  content, picture , laiz, join_date,  change_date,  imgurl,  picnum , imgtext , imglink , imgAlt)
   values('一关于来着小猪佩奇的新闻','1','very good ' ,' ', '小猪佩奇' ,now() , now() ,' ' ,' ' , ' ' , ' ' ,' ' );
insert into news(title,typeid,  content, picture , laiz, join_date,  change_date,  imgurl,  picnum , imgtext , imglink , imgAlt)
   values('一关于来着小猪佩奇的新闻','2','very good ' ,' ', '小猪佩奇' ,now() , now() ,' ' ,' ' , ' ' , ' ' ,' ' );
   
   select * from News ;
--友情链接

  insert into friendlink(web_name , web_address ) values('baidu' , 'http://www.baidu.com');
  insert into friendlink(web_name , web_address ) values('sina' , 'http://www.sina.com');
  insert into friendlink(web_name , web_address ) values('taobao' , 'http://www.taobao.com');
  insert into friendlink(web_name , web_address ) values('sohu' , 'http://www.sohu.com');
  
  --产品展示
  select * from Product ; 
  
 insert into  Product(  	Product_class	 ,
                    	Product_name ,
						Product_in	 ,
	Product_gain		,
	Product_spec	,
	Product_unit		,
	Product_remark		,
	Product_explain		,
	Product_picture		,
	Product_auditing		,
	index_show	,
	join_date		,
	change_date	
)   values('1' ,'鼠标垫',  'adeline','a','d','e','l','i','a','d','e','l','i')

insert into  Product(Product_class,Product_name,Product_in,Product_auditing,index_show) values('办公产品','笔记本','笔记本','1','1')
	delete from  Product where id = 1 ;
	--网站信息
	insert into webconfig(web_name,  join_date , url , logo , email ,banner , banquan  )
	    values('Adeline科技有限公司' , now() , 'www.Adeline.com' , 'images/logo.gif' , '123456789@qq.com' , 'asdfkjskd' , '123456789' ); 
	    
	    delete from  webconfig ;
	    update webconfig set banner = ' ' ;
	
	    select * from webconfig ;
	
	    
	    --公司信息类别
	    
	    insert into infotype(typename) values('企业简介');
	    insert into infotype(typename) values('企业文化');
	    insert into infotype(typename) values('组织机构');
	    insert into infotype(typename) values('成长历程');
	    insert into infotype(typename) values('联系我们');
	     
	    select * from infotype;   select * from infotype
	  
	    --公司信息
	    insert into  companyinfo(typeid , title , content , picture , change_date ) values (1,'公司简介' , '本公司' , ' '  , now()   );
	    select * from companyinfo ;
	    
	    --产品类别 
	    
	    insert  into Product_class(protype) values('笔记本电脑');
	    insert  into Product_class(protype) values('鼠标');	    
	    insert  into Product_class(protype) values('鼠标垫');	
	    insert  into Product_class(protype) values('键盘');
	    
	    select * from Product_class  ;
	    
	    
	    --服务于支持
	    
		insert into server(title,content,pic,join_date,picnum,picname)  values('adeline???' ,'you happy jiu ok ?' ,'', now() , '', '');
		 insert into server(title,content,pic,join_date,picnum,picname)  values('adeline???' ,'you happy jiu ok ?' ,'', now() , '', '');
		insert into server(title,content,pic,join_date,picnum,picname)  values('adeline???' ,'you happy jiu ok ?' ,'', now() , '', '');
		 insert into server(title,content,pic,join_date,picnum,picname)  values('adeline???' ,'you happy jiu ok ?' ,'', now() , '', '');
		 
		 
		 --招贤纳士
		 select * from job where id =  1
		 insert into job (inviter,number,address,wage,expressdate,demand,join_date)  values ('java工程师' ,'5' ,'england','1w+', now(),'本科以上学历',now());
		 insert into job (inviter,number,address,wage,expressdate,demand,join_date)  values ('java工程师' ,'5' ,'england','1w+', now(),'本科以上学历',now());
		 insert into job (inviter,number,address,wage,expressdate,demand,join_date)  values ('java工程师' ,'5' ,'england','1w+', now(),'本科以上学历',now());
		 
		 --admin
		 insert into admin(username, userpassword,join_time) values('a','d7afde3e7059cd0a0fe09eec4b0008cd',now());
		 
		 
		 --插入申请工作的  人员信息
		 select * from acjob;
		 insert into acjob(jobid,username,sex,school,telephone,email) values(1,'a','boy','hh','45678','123454@163.com')  
		 