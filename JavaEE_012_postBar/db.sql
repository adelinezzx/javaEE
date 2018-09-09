
create database adeline ;
 use   talkroom ;
 
create table puser(
    uid  int primary key  auto_increment , 
    uname varchar(25),
    tel varchar (20),
    upass varchar(100)
)

 create table topic (
     tid int primary key auto_increment ,
     contents varchar(3000),
     publishtime datetime ,
     pic varchar(3000),
     uid int 
 )

insert into puser(uname,tel,upass) values('a','123454787','0cc175b9c0f1b6a831c399e269772661');

select * from puser;
drop table puser ;
drop table topic ;
insert into topic(contents , publishtime , pic, uid ) values('hell<br/>sa<br/>d<br/>asdfsdf<br/>o',now() , ' ' , 1);
insert into topic(contents , publishtime , pic, uid ) values('publish',now() , ' ' , 1);
insert into topic(contents , publishtime , pic, uid ) values('why',now() , ' ' , 1);
insert into topic(contents , publishtime , pic, uid ) values('you know',now() , ' ' , 1);
insert into topic(contents , publishtime , pic, uid ) values('helldsfdsgfjdghjgfhdsgjdghgfeuy<br/>sa<br/>d<br/>asdfsdf<br/>o',now() , ' ' , 1);
select * from topic ;
delete from topic where tid = 5;
-- 分页
select * from  topic order by publishtime desc ,tid desc limit 0,5 ;

--连接puser 和 topic 两张表
select  tid ,contents ,publishtime,pic,topic.uid  uname from  topic 
     inner join puser 
     on topic.uid = puser.uid 
order by publishtime desc ,tid desc limit 0,5 ;

select count(*) from topic where 1=1 ;

select * from puser where uname = 'cindy' or tel = 'cindy' and upass = '0cc175b9c0f1b6a831c399e269772661'