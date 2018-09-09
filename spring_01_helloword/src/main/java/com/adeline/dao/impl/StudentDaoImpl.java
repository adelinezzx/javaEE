package com.adeline.dao.impl;

import com.adeline.biz.StudentBiz;
import com.adeline.dao.StudentDao;

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
