
create table jspuser(
  id integer primary key ,
  uname varchar2(20),
  upwd varchar2(20)
);

create sequence jspuser_seq ;

insert into jspuser values(jspuser_seq.nextval,'adeline','ad');

select * from jspuser ;

select * from jspuser where uname = 'b';

delete JSUpSER ;

select count(*) from jspuser where uname='a' and upwd='a';