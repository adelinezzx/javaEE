package com.adeline.biz.impl;

import com.adeline.biz.StudentBiz;
import com.adeline.dao.StudentDao;
import com.adeline.dao.impl.StudentDaoImpl;

public class StudentBizImpl  implements StudentBiz{
	
	//缺点：StudentBizImpl是直接依赖于StudentDaoImpl类
	//private StudentDao studentDao = new StudentDaoImpl();
	
   private StudentDao studentDao ;//studentDao接口
	
	@Override
	public void add() {
		studentDao.add();
		
	}

	@Override
	public void find() {
		studentDao.find();
		
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	
}
