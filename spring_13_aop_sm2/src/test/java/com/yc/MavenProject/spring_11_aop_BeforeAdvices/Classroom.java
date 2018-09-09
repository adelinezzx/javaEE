package com.yc.MavenProject.spring_11_aop_BeforeAdvices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@ComponentScan(basePackages="com.yc.MavenProject.spring_11_aop_BeforeAdvices")
public class Classroom {
	
	@Autowired 
	public   Person1   p ;
	
	public void  print(){
		System.out.println("testAutowired");
	}

	public void setP(Person1 p) {
		this.p = p;
	}
	
   
}
