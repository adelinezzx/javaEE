package com.yc.aop_around;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
 
public class Hello2  implements  Ordered{
	
	@Before("execution(* com.yc.aop_around.Math.add(..))")
	public  Object hello(){
		System.out.println("hello--------------------------2");
		
		return null;
	}

	@Override
	public int getOrder() {
		 
		return 200;
	}

}
