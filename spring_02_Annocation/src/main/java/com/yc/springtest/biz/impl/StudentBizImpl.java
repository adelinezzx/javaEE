package com.yc.springtest.biz.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.yc.dao.StudentDao;
import com.yc.springtest.biz.StudentBiz;
@ComponentScan(basePackages="com.yc")
@Service
public class StudentBizImpl  implements StudentBiz{
	
	//缺点：StudentBizImpl是直接依赖于StudentDaoImpl类
	//private StudentDao studentDao = new StudentDaoImpl();
	
/*	@Autowired//相当于 <property bane="studentDao"  ref ="com.yc...">
	//当StudentDao有多个实现类的时候，@Autowired就会分不清调用哪一个实现类  就会报错 所以 
	//当只有一个实现类的时候  用@Autowired   多个时用@Autowired+ @Qualifier来指定
	 * 
	 * @Resource(name="studentRedisDaoImpl")    等价于  @Autowired	@Qualifier("studentRedisDaoImpl")
*/	
	/*@Autowired
	@Qualifier("studentRedisDaoImpl")*/
	
	@Resource(name="studentRedisDaoImpl")
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
