package com.yc.test;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.Classes;
import com.yc.bean.DataSourcePool;
import com.yc.bean.Person;
import com.yc.biz.PersonBiz;
import com.yc.dao.PersonDao;
import com.yc.spring.AppConfig;
import com.yc.spring.YcAnnotationConfigApplicationContext;
import com.yc.spring.YcApplicationContext;

public class AppTest {
	 
	public static void main(String[] args) throws IllegalArgumentException, InvocationTargetException {

		YcApplicationContext context = new YcAnnotationConfigApplicationContext(AppConfig.class);
		Person  p = (Person) context.getBean("p");
		System.out.println(p);
		
		
		PersonDao    pd = (PersonDao)context.getBean("personDaoMybatisImpl");
		pd.add();
		
		PersonBiz  ppb =  (PersonBiz)context.getBean("personBizImpl");
		ppb.add();
		
		
       
}
	 
}
