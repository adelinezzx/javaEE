package com.yc.aop_xml_annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component   //IOC的
@Aspect // aspect 声明为一个切面
public class HelloAdvice {
	// @Before为前置通知 通过 execution声明一个切点
	@Before("execution(* com.yc.aop_xml_annotation.Math.*(..))")
	public void before(JoinPoint jp) {
		System.out.println("adeline ---" + jp.getSignature().getName());
	}

}
