package com.yc.biz.impl;


import com.yc.biz.StudentBiz;
import com.yc.dao.StudentDao;
 
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
		System.out.println("spring 调用set方法完成注入studentDao对象");
	}

	
}
