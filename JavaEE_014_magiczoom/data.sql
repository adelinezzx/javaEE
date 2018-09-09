create database res ;

use adeline;
insert into resadmin(raname,rapwd) values( 'a','0cc175b9c0f1b6a831c399e269772661');
select * from resadmin;

 ---------------------------------------resuser-----------------------------------------------------------------------------------------

---用户表初始数据
insert into resuser( username, pwd,email) values( 'a', '0cc175b9c0f1b6a831c399e269772661','a@163.com');
insert into resuser( username, pwd,email) values( 'b', '0cc175b9c0f1b6a831c399e269772661','b@163.com');
select * from resuser ;
delete from resuser where username='c';

select * from (  select  resuser.userid  as userid  ,  username ,ifnull(   sum(realprice * num),0  ) as dealcount from resuser
  left join resorder   
  on  resuser.userid =resorder.userid 
  left join resorderitem 
  on  resorder.roid = resorderitem.roid
  group by userid , username 
)  a ;
------------------------------------------resfood--------------------------------------------------------------------------------------
select count(*) as num from resfood  where 1=1 
--根据  fid  查找 菜品的相关信息
select resfood.fid as fid ,fname ,ifnull(sum(num),0) as sellcount  from resfood 
         left join resorderitem
         on resfood.fid =resorderitem.fid 
         group by resfood.fid,fname 
         
  select  count(*)  as num from resfood where 1=1
--插入菜
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('素炒莴笋丝',22.0,20.0,'营养丰富','500008.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('蛋炒饭',22.0,20.0,'营养丰富','500022.jpg');
insert into resfood( fname,normprice,realprice,detail, fphoto)  values('酸辣鱼',42.0,40.0,'营养丰富','500023.jpg');
insert into resfood( fname,normprice,realprice,detail, fphoto)  values('鲁粉',12.0,10.0,'营养丰富','500024.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('西红柿蛋汤',12.0,10.0,'营养丰富','500025.jpg');
 
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('炖鸡',102.0,100.0,'营养丰富','500026.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('炒鸡',12.0,10.0,'营养丰富','500033.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('炒饭',12.0,10.0,'营养丰富','500034.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('手撕前女友',12.0,10.0,'营养丰富','500035.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('面条',12.0,10.0,'营养丰富','500036.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)  values('端菜',12.0,10.0,'营养丰富','500038.jpg');
insert into resfood(fname,normprice,realprice,detail, fphoto)   values('酸豆角',12.0,10.0,'营养丰富','500041.jpg');


update resfood  set fname = '酸豆角',normprice=12.0,realprice =10.0 ,fphoto = '500041.jpg'  where fid = 1
--------------------------------------------resorder------------------------------------------------------------------------------------
--不测试:   生成一条订单   a用户订了  1号菜1份,及2号菜2份
insert into resorder(userid,address,tel,ordertime,deliverytime,ps,status) 
values( 1,'湖南省衡阳市','13878789999',now(),now(),'送餐上门',0);
select * from resorder ;
select count(*) as total from resorder where 1=1
select  roid ,userid ,address ,tel,date_format(ordertime,'%Y-%c-%d %H:%i:%s') as ordertime,
               date_format(deliverytime,'%Y-%c-%d %H:%i:%s') as deliverytime,ps,status from resorder where 1=1

  select  roid ,userid ,address ,tel,date_format(ordertime,'%Y-%c-%d %H:%i:%s') as ordertime, date_format(deliverytime,'%Y-%c-%d %H:%i:%s') as deliverytime,ps,status from resorder where 1=1   order by roid  desc    limit 0  , 100           
        
  delete from resorder   where roid=20;
  
  update  resorder set status=1 where roid = 22 
  
  --------------------------------------------------resorderitem-------------------------------------------------------------------------
  insert into resorderitem(roid,fid,dealprice,num)
values( 1,1,20,1);

select * from resorderitem;

delete from resorderitem where roid=20
insert into resorderitem(roid,fid,realprice,num)
values( 1,2,20,1);

select  roiid ,roid ,resorderitem.fid,fname,resorderitem.realprice,num,resorderitem.realprice* num as smallcount from resorderitem 
 left join resfood on  resorderitem.fid =resfood.fid where roid = ? ;

--注意以上的三条语句要求在事务中处理. 
commit; 
