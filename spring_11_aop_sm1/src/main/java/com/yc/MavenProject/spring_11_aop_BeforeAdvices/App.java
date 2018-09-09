package com.yc.MavenProject.spring_11_aop_BeforeAdvices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 
public class App {
	
	private  String  appName ;
	
	 
	private  MyService  serviceOp(){
		return new MyService();
	}
 
}
