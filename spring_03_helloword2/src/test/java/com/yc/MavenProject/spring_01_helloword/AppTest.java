package com.yc.MavenProject.spring_01_helloword;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adeline.bean.Container;
import com.adeline.bean.Person;
import com.adeline.bean.Student;
import com.adeline.biz.StudentBiz;
 
 
public class AppTest 
{
	@Test
	public void testHello(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		
		Student  s = (Student) context.getBean("student");
		System.out.println(s);
	}
	
	@Test
	public void testHello2(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		
		StudentBiz  s = (StudentBiz) context.getBean("studentBiz");
		s.add();
	}
	

	@Test
	public void testHello3(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		
		Container c = (Container) context.getBean("container");
		
		Person p1 = new Person("bob", 1.8, 60);
		Person p2 = new Person("bob2", 1.5, 100);
		Person p3 = new Person("bob3", 1.9, 90);
		Person p4 = new Person("bob3", 1.7, 80);
		
		Person p5 = new Person("bob", 1.8, 550);//数据异常
		try {
			c.save(p1);
			c.save(p2);
			c.save(p3);
			c.save(p4);
			c.save(p5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(c.getMax());
		System.out.println(c.getMin());
		System.out.println(c.getAvg());
	}
}
