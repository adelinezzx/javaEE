package com.yc.bean;

 

import java.io.IOException;

import org.jdom.JDOMException;
import org.junit.Test;

import com.yc.bean.Student;
import com.yc.springframework.YcApplicationContext;
import com.yc.springframework.YcClassPathXmlApplicationcontext;

public class TestSpring {
	
	@Test
	public void testHello() throws InstantiationException, IllegalAccessException, ClassNotFoundException, JDOMException, IOException{
		YcApplicationContext context = new YcClassPathXmlApplicationcontext("bean.xml");
		
		Student  s = (Student) context.getBean("student");
		System.out.println(s);
	}


}
