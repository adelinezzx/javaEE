package com.yc.bean;

 

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.jdom.JDOMException;
import org.junit.Test;

import com.yc.bean.Student;
import com.yc.biz.StudentBiz;
import com.yc.springframework.YcApplicationContext;
import com.yc.springframework.YcClassPathXmlApplicationcontext;

public class TestSpring {
	
	@Test
	public void testHello() throws InstantiationException, IllegalAccessException, ClassNotFoundException, JDOMException, IOException, IllegalArgumentException, InvocationTargetException{
		YcApplicationContext context = new YcClassPathXmlApplicationcontext("beans.xml");
		
		Student  s = (Student) context.getBean("student");
		System.out.println(s);
	}


	@Test
	public void testHello2() throws InstantiationException, IllegalAccessException, ClassNotFoundException, JDOMException, IOException, IllegalArgumentException, InvocationTargetException{
		YcApplicationContext context = new YcClassPathXmlApplicationcontext("beans.xml");
		
		StudentBiz  s = (StudentBiz) context.getBean("studentBiz");
		s.add();
	}
}
