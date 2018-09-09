package com.yc.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.Classes;
import com.yc.bean.DataSourcePool;
import com.yc.bean.Person;

public class AppTest {
	@Test
	public void testHello() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

		DataSourcePool s = (DataSourcePool) context.getBean("dataSourcePool");
		System.out.println(s.getCon());
	}
	
	@Test
	public void testHello2() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

		 Person  p = (Person) context.getBean("person");
		 System.out.println(p);
	}

	

	@Test
	public void testHello3() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

		 Classes  p = (Classes) context.getBean("c43");
		 System.out.println(p);
	}
	
	@Test
	public void testHello4() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

		 TestData  p = (TestData) context.getBean("testData");
		 System.out.println(p);
	}
	
	@Test
	public void testHello5() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		Person  p = (Person) context.getBean("p-namespace");
		 System.out.println(p);
	}
	
	@Test
	public void testHello6() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
		Person  p = (Person) context.getBean("classes");
		 System.out.println(p);
	}
}
