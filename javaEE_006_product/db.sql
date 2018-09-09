create table product43(
  pid int primary key ,
  pname varchar2(20),
  price numeric(5,2)
)

drop table product43;

create  sequence sql_product43;

drop sequence sql_product43;

insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);
insert into product43 values(sql_product43.nextval,'apple',30);

insert into product43 values(sql_product43.nextval,'apple1',35);
insert into product43 values(sql_product43.nextval,'apple2',45);
insert into product43 values(sql_product43.nextval,'apple3',51);
insert into product43 values(sql_product43.nextval,'apple4',60);

insert into product43 values(sql_product43.nextval,222,50) 
select * from PRODUCT43;

delete  PRODUCT43 ;

select * from (
     select a.* ,row_number() over(order by a.pid desc) rn 
     from product43 a 
) where rn>=11 and rn <=15 ;

--µ¹Ðò
select * from (
		select a.* , rownum as rn from (
				 select  * 
				 from product43 where 1=1 
				 and pname like 'a%' and price > 5 
				 order by pid desc 	
		  ) a where rownum <=10 
) where rn >=6


select * from (      select a.* , rownum as rn from (    	 select  *    	 from product43 where 1=1    order by price  desc    ) a where rownum <=  15    ) where rn >=11