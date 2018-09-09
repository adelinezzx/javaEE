package com.yc.aop_around;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.aop_xml_annotation.Math;

 
public class TestBefore {
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Aop_aroundAdevice.xml");
		// 观察构造方法的调用时间
		MyMath pp = (MyMath) context.getBean("math");
		
		System.out.println(pp.add(2, 5));
	}
	
	
}
