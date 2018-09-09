
create table usersc (
  id integer primary key ,
  username varchar2(20),
  password varchar2(20)
)

create sequence sql_usersc start with 1000;

insert into usersc values(sql_usersc.nextval,'cindy','a')
select * from usersc ;