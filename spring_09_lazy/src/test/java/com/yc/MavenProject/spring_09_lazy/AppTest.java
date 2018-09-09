package com.yc.MavenProject.spring_09_lazy;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.yc.bean.Person;
import com.yc.bean.StudentDao;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class AppTest

{
	@Resource(name="p1")
	private  StudentDao  stuDao  ;
	
	
	@Test
	public void testApp3() {
		 System.out.println(stuDao);
	}
	
	
	
	
	@Test
	public void testLazy() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 观察构造方法的调用时间

	}

	@Test
	public void testApp() {
		System.out.println("test2----------------------------");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 观察构造方法的调用时间
		Person pp = (Person) context.getBean("person2");
		System.out.println(pp);
		Person pp1 = (Person) context.getBean("person2");
		System.out.println(pp1);
	}

	@Test
	public void testApp2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("test3----------------------------");
		// 观察构造方法的调用时间
		Person pp = (Person) context.getBean("person3");
		System.out.println(pp);
		Person pp1 = (Person) context.getBean("person3");
		System.out.println(pp1);
	}
	 
}
