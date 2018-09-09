package com.yc.aop_around;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect  //声明一个切面
@Component
@Order(1)
public class TimeAdvices {
	
	@Around(" execution(* com.yc.aop_around.Math.add(..))")
	public   Object  time(ProceedingJoinPoint   pjp) throws Throwable{
		
		long starttime = System.currentTimeMillis();
		System.out.println("timeadvice  start");
		Object  returnValue = pjp.proceed();
		
		System.out.println("timeadvice  start");
		
		long  endtime = System.currentTimeMillis();
		System.out.println("------------------------------------");
		System.out.println(endtime  -  starttime );
		return  returnValue ;
	}
	

}
