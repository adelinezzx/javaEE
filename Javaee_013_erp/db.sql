create database db1;

use db1 ;


--�˲���Ϣ��acjob
create table acjob(
	id int primary key auto_increment,
	jobid int ,
	username varchar(200),
	sex varchar(200),
	birthday date,
	ismarry varchar(200),
	school varchar(200),
	studydegree varchar(200),
	specialty varchar(200),
	gradyear date,
	telephone	varchar(2000),
	email	varchar(2000),
	address	varchar(2000),
	ability	varchar(2000),
	resumes	varchar(2000),
	join_date	date,
	position varchar(2000)
);

drop table acjob;
--2.����Ա��admin  ��Ӧ����   seq_admin_id
create table  admin(
	id	int primary key auto_increment,
	username	varchar(2000) not null,
	userpassword	varchar(2000) not null,
	join_time	date not null
);

--3.��˾��Ϣ����infotype  ��Ӧ����   seq_infotype_id
create table infotype(
	id	int primary key auto_increment,
  typename	varchar(2000) not null
);

--4.��˾��Ϣ��companyinfo   ��Ӧ����  seq_companyinfo_id
create table companyinfo(
	id	int primary key auto_increment,
	typeid	int not null,
	title	varchar(2000) not null,
	content	varchar(2000) not null,
	picture	varchar(2000),
	change_date	date
);

--5.�������ӣ�friendlink     ��Ӧ����  seq_friendlink_id
create table friendlink(
   id	int primary key auto_increment ,
   web_name	varchar(2000),
   web_address	varchar(2000)
);

--6.������Ϣ��job        ��Ӧ����  seq_job_id
create table job(
	id	int primary key auto_increment,
	inviter	varchar(2000),
	number	int,
	address	varchar(2000) not null,
	wage	varchar(2000),
	expressdate	date,
	demand	varchar(1500),
	join_date	date not null
);

--7.���Ա�leave_word     ��Ӧ����  seq_leave_word_id
create table leave_word(
	id	int primary key auto_increment,
	username	varchar(2000),
	firmname	varchar(2000),
	contactman	varchar(2000),
	ring	varchar(2000),
	fax	varchar(2000),
	email	varchar(2000),
	title	varchar(2000),
	content	varchar(2000),
	renew	varchar(2000),
	join_date	date
);


--8.���ű�news
create table news(
	id	int primary key auto_increment,
	title	varchar(2000),
	typeid	varchar(2000),
	content	text,
	picture	varchar(2000),
	laiz	varchar(2000),
	join_date	datetime,
	change_date	datetime,
	imgurl	varchar(2000),
	picnum	varchar(2000),
	imgtext	varchar(2000),
  imglink	varchar(2000),
  imgAlt	varchar(2000)
);

--9��������news_class
create table news_class(
	id	int primary key auto_increment,
	typename	varchar(2000)
);

select * from Product_class;
--10.��Ʒ��Ϣ��Product
create table Product(
	id	int primary key auto_increment,
	Product_class	varchar(2000),
	Product_name	varchar(2000),
	Product_in	varchar(2000) not null,
	Product_gain	varchar(2000),
	Product_spec	varchar(2000),
	Product_unit	varchar(2000),
	Product_remark	text,
	Product_explain	text,
	Product_picture	varchar(50),
	Product_auditing	int  ,
	index_show	int  ,
	join_date	datetime,
	change_date datetime
);

drop table Product ;
--11.��Ʒ����Product_class
create table Product_class(
	id	int primary key auto_increment,
	protype	varchar(2000)
);

--12.��վ�����pronunciament      ��Ӧ����    seq_ pronunciament_id
create table pronunciament(
	id	int primary key auto_increment,
	title	varchar(2000),
	content	varchar(2000),
	join_date	date
);

drop table pronunciament ;
--13.������Ϣ��server      ��Ӧ����    seq_server_id
create table server(
	id	int primary key auto_increment,
	title	varchar(2000),
	content	varchar(1500),
	pic	varchar(2000),
	join_date	date,
	picnum	varchar(100),
	picname	varchar(2000)
);

--14.��վ������Ϣ���ñ�webconfig      ��Ӧ����    seq_webconfig_id
create table webconfig(
	id	int primary key auto_increment,
	web_name	varchar(2000),
	join_date	date,
	Url	varchar(2000),
	logo	varchar(2000),
	email	varchar(2000),
	banner	varchar(2000),
	banquan	varchar(2000)
);
