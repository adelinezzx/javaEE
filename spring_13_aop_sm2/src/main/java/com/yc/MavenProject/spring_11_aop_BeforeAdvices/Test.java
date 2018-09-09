package com.yc.MavenProject.spring_11_aop_BeforeAdvices;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
	
	@org.junit.Test
	  public void test (){
		ApplicationContext context = new  AnnotationConfigApplicationContext(MyService.class);
		MyService  pp = (MyService) context.getBean("myService");
		System.out.println(pp);
	  }

}
