package com.yc.aop_around;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class Hello1 {
	
	@Before("execution(* com.yc.aop_around.Math.add(..))")
	public  Object hello(){
		System.out.println("hello--------------------------1");
		
		return null;
	}

}
