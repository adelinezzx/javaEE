package com.yc.dao.impl;

 
import org.springframework.stereotype.Repository;

import com.yc.dao.StudentDao;

@Repository  //相当于 <bean id="studnetDaoImopl" class="">   
public class StudentRedisDaoImpl   implements StudentDao {
	@Override
	public void add() {
		System.out.println("访问redis 完成添加操作");
		
	}

	@Override
	public void find() {
		System.out.println("访问redis，完成查询操作");	
	}
}
