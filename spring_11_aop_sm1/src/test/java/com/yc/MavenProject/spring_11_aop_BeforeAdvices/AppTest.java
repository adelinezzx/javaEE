package com.yc.MavenProject.spring_11_aop_BeforeAdvices;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
	@org.junit.Test
	public void demo1() {
		ApplicationContext context = new AnnotationConfigApplicationContext(Classroom.class);
		 Person1  p = new Person1() ;
		Classroom  clar = (Classroom) context.getBean("classroom");
		 p.setStudntid(13);
		 clar.setP(p);
		clar.print();
	}
}
