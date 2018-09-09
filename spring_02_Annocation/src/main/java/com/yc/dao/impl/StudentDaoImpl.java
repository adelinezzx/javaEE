package com.yc.dao.impl;

 
import org.springframework.stereotype.Repository;

import com.yc.dao.StudentDao;

@Repository  //相当于 <bean id="studnetDaoImopl" class="">
public class StudentDaoImpl   implements StudentDao {

	@Override
	public void add() {
		System.out.println("访问数据库。完成添加操作");
		
	}

	@Override
	public void find() {
		System.out.println("访问数据库，完成查询操作");
		
	}

	
 
}
