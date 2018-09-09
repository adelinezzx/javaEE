package com.yc.MavenProject.spring_02_Annocation;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.Student;
import com.yc.dao.StudentDao;
import com.yc.springtest.biz.StudentBiz;
import com.yc.springtest.biz.impl.StudentBizImpl;


public class AppTest {
 

	@Test
	public void testApp() {
		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		System.out.println("spring build ok ");
		Student  s = (Student) context.getBean("stu");
		System.out.println(s);
	}
	
	@Test
	public void testApp1() {
		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		System.out.println("spring build ok ");
		Student  s = (Student) context.getBean("student");
		System.out.println(s);
	}
	
	@Test
	public void testApp2() {
		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		System.out.println("spring build ok ");
		 StudentDao stu = (StudentDao) context.getBean("studentDaoImpl");
		 stu.add();
	}
	
	@Test
	public void testApp4() {
		ApplicationContext context = new AnnotationConfigApplicationContext(StudentBizImpl.class);
		System.out.println("spring build ok ");
		 StudentBiz  stubiz = (StudentBiz) context.getBean("studentBizImpl");
		 stubiz.add();
	}
}
