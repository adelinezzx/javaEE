package com.yc.test;

import java.io.IOException;

import org.jdom.JDOMException;

import com.yc.springframework.context.annotation.ApplicationContext;
import com.yc.springframework.context.annotation.ClassPathXMLApplicationContext;

public class Test {
	
	public static void main(String[] args) throws JDOMException, IOException {
		ApplicationContext   context =  new  ClassPathXMLApplicationContext("spring.xml");
		
		System.out.println(context.getBean("personBizImpl"));
		
		//System.out.println(context.getBean("personBizImpl"));
		
	}

}
