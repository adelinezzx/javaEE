create database bbs44;

--用户表
create table tbl_user(
	uid int primary key auto_increment,
	uname varchar(20),
	upass varchar(100),
	head varchar(100),
	regtime datetime,
	gender int
);

select * from tbl_user;
delete from tbl_user;
insert into tbl_user(uname,upass,head,regtime,gender) values('a','d7afde3e7059cd0a0fe09eec4b0008cd','1.gif',now(),1);

--板块表  板块id 板块名  
create table tbl_board(
	boardid int primary key auto_increment,
	boardname varchar(50),
	parentid int
);

select * from tbl_board order by parentid;

insert into tbl_board(boardname,parentid) values('.net版块',0);
insert into tbl_board(boardname,parentid) values('java版块',0);
insert into tbl_board(boardname,parentid) values('数据库版块',0);
insert into tbl_board(boardname,parentid) values('软件工程版块',0);


insert into tbl_board(boardname,parentid) values('ado.net',1);
insert into tbl_board(boardname,parentid) values('asp.net',1);
insert into tbl_board(boardname,parentid) values('vb.net',1);


insert into tbl_board(boardname,parentid) values('jsp',2);
insert into tbl_board(boardname,parentid) values('struts',2);
insert into tbl_board(boardname,parentid) values('hibernate',2);


insert into tbl_board(boardname,parentid) values('sql',3);
insert into tbl_board(boardname,parentid) values('oracle',3);
insert into tbl_board(boardname,parentid) values('mysql',3);


--------------------------------------------------------------------------------------------------------------------------------

--主题表  主题id  主题标题  内容   发表时间  修改时间  用户名id   板块id
create table tbl_topic(
	topicid int primary key auto_increment,
	title varchar(50),
	content varchar(1000),
	publishtime datetime,
	modifytime datetime,
	uid int,
	boardid int
);

 
select * from tbl_topic;
 
 select  a.topicid, title,content,publishtime,modifytime,uid as userid ,a.uname,boardid,total as replaycount from
(
      select   topicid , title , content ,date_format(publishtime,'%y-%m-%d  %H:%I:%S') as publishtime ,date_format(modifytime,'%y-%m-%d  %H:%I:%S') as modifytime,tbl_topic.uid ,boardid,tbl_user.uname
      from tbl_topic inner join tbl_user on tbl_topic.uid = tbl_user.uid where boardid=8 
      order  by modifytime desc limit 0,2 
) a 
left join 
(
    select topicid ,count(*) as total from tbl_reply group by topicid 
) b
   on a.topicid = b.topicid;

  select tbl_board.boardid,boardname,parentid 
  from tbl_board 
  inner join tbl_topic
  on tbl_board.boardid  = tbl_topic.boardid 
  where tbl_topic.topicid = 3 ;
   
   
 select  a.topicid, title,content,publishtime,modifytime,uid as userid ,a.uname,boardid,total as replaycount from   (  select   topicid , title , content ,date_format(publishtime,'%y-%m-%d  %H:%I:%S') as publishtime ,  date_format(modifytime,'%y-%m-%d  %H:%I:%S') as modifytime,tbl_topic.uid ,boardid,tbl_user.uname   from tbl_topic inner join tbl_user on tbl_topic.uid = tbl_user.uid where boardid=8  order  by modifytime desc limit 0,2   ) a   left join   ( select topicid ,count(*) as total from tbl_reply group by topicid     ) b  on a.topicid = b.topicid
  --findTopic by id
 select   topicid , title , content ,date_format(publishtime,'%y-%m-%d  %H:%I:%S') as publishtime ,
               date_format(modifytime,'%y-%m-%d  %H:%I:%S') as modifytime ,
               tbl_topic.uid as userid ,
               uname,
               head,
               date_format(regtime,'%y-%m-%d  %H:%I:%S') as regtime ,
               boardid
               from tbl_topic 
               inner join tbl_user on tbl_topic.uid =tbl_user.uid 
               where topicid = 5 

 select count(*) as total from  tbl_topic where boardid = 8;

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) values('netmet','it is so good ',now(),now(),'1','1')
go
alter table tbl_topic
   add constraint FK_topic_uid
      foreign key(uid) references tbl_user(uid);
      
alter table tbl_topic
   add constraint FK_topic_boardid
     foreign key(boardid) references tbl_board(boardid);
 --------------------------------------------------------------------------------------------------------------------------------  
     --回复表  回复id  回复主题  回复内容  发表的时间 修改的时间  用户id  回复的主题的id  
create table tbl_reply(
	replyid int primary key auto_increment,
	title varchar(50),
	content varchar(1000),
	publishtime datetime,
	modifytime datetime,
	uid int,
	topicid int
);

select * from tbl_reply;

insert into tbl_reply(title,content,publishtime,modifytime,uid,topicid) values('beautiful reply','it is so beautiful reply',now(),now(),1,5)
insert into tbl_reply(title,content,publishtime,modifytime,uid,topicid) values('areply','it is so beautiful reply',now(),now(),1,5)
insert into tbl_reply(title,content,publishtime,modifytime,uid,topicid) values('bbdxd',' beautiful reply',now(),now(),1,5)

update tbl_reply   set   title = '22  ok ' content ='22  ok '  ,modifytime = now() where replyid  = ? 

-- findReply
select replyid ,title ,content,date_format( modifytime,'%y-%m-%d  %H:%I:%S')  as modifytime ,
      date_format( modifytime,'%y-%m-%d  %H:%I:%S')  as modifytime ,tbl_reply.uid as userid , topicid,
      uname ,
      head,
      date_format( regtime,'%y-%m-%d  %H:%I:%S')  as regtime
      from tbl_reply
      inner join tbl_user on tbl_reply.uid = tbl_user.uid 
      where topicid = 5 
      order by modifytime desc 
      limit 0,2 ;
      
      select count(*) as total from  tbl_reply where topicid = 5
      
--外键
go
alter table tbl_reply
   add constraint FK_reply_uid
      foreign key(uid) references tbl_user(uid);
      
alter table tbl_reply
	add constraint FK_reply_topicid
	   foreign key(topicid) references tbl_topic(topicid);
--------------------------------------------------------------------------------------------------------------------------------
 --使用左外联接。将板块信息和 最新的帖子信息整合在一起
select a.boardid,boardname,parentid,total as topicsum ,topicid as recenttopicid ,title as recenttopictitle ,date_format( modifytime,'%y-%m-%d  %H:%I:%S')  as recenttopicmodifytime ,uname as recenttopicusername
from
   (
     select tbl_board.boardid,boardname,parentid, count(topicid) as total
      from tbl_board 
        left join tbl_topic
        on tbl_board.boardid = tbl_topic.boardid 
        group by tbl_board.boardid,boardid,boardname,parentid
   ) a  
   left join (
   select topicid,title,a.modifytime,uname,a.boardid
         from
         (
            select topicid ,title,modifytime,uname,boardid 
            from tbl_topic  
            left join tbl_user
             on tbl_topic.uid = tbl_user.uid
          )  a,
            
          ( 
		       select boardid ,max(modifytime) as modifytime
		       from tbl_topic 
		       group by boardid 
		   ) b 
		    where a.boardid = b.boardid and a.modifytime = b.modifytime
   ) b
   on a.boardid = b.boardid 
    
	    select a.boardid,boardname,parentid,total as topicsum ,topicid as recenttopicid ,title as recenttopictitle ,modifytime as recenttopicmodifytime ,uname as recenttopicusername from  (  select tbl_board.boardid,boardname,parentid, count(topicid) as total from tbl_board left join tbl_topic   on tbl_board.boardid = tbl_topic.boardid   group by tbl_board.boardid,boardid,boardname,parentid   ) a  left join (    select topicid,title,a.modifytime,uname,a.boardid from  (   select topicid ,title,modifytime,uname,boardid  from tbl_topic    left join tbl_user on tbl_topic.uid = tbl_user.uid  )  a, (   select boardid ,max(modifytime) as modifytime from tbl_topic group by boardid   ) b  where a.boardid = b.boardid and a.modifytime = b.modifytime   ) b  on a.boardid = b.boardid 

	     select a.boardid,boardname,parentid,total as topicsum ,topicid as recenttopicid ,title as recenttopictitle ,modifytime as recenttopicmodifytime ,uname as recenttopicusername from  (  select tbl_board.boardid,boardname,parentid, count(topicid) as total from tbl_board left join tbl_topic   on tbl_board.boardid = tbl_topic.boardid   group by tbl_board.boardid,boardid,boardname,parentid   ) a  left join (    select topicid,title,a.modifytime,uname,a.boardid from  (   select topicid ,title,modifytime,uname,boardid  from tbl_topic    left join tbl_user on tbl_topic.uid = tbl_user.uid  )  a, (   select boardid ,max(modifytime) as modifytime from tbl_topic group by boardid   ) b  where a.boardid = b.boardid and a.modifytime = b.modifytime   ) b  on a.boardid = b.boardid 
------------------------------------------------------------------------------------------------------------------------------------------     
insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp  good',' good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('ado.net is very good','very good,i agree',now(), now(),2,5);
--------------------------------------------------------------------------------------------------------------------------------
	   