package com.yc.spring;


import com.yc.bean.Person;
import com.yc.biz.PersonBiz;
import com.yc.biz.impl.PersonBizImpl;
import com.yc.dao.PersonDao;
import com.yc.dao.impl.PersonDaoHibernateImpl;
import com.yc.dao.impl.PersonDaoMybatisImpl;

//相当于整个项目的spring.xml
@Configuration
public class AppConfig {

	@Bean 
	public PersonBiz personBizImpl() {// 方法名personBizImpl做id 激活这个方法得到的返回值
										// personBizImpl叫做map中的值
		return new PersonBizImpl();

	}

	@Bean 
	public Person p() {
		return new Person();
	}

	@Bean
	public PersonDao personDaoHibernateImpl() {
		return  new PersonDaoHibernateImpl();
	}

	@Bean
	public PersonDao personDaoMybatisImpl() {
		return new PersonDaoMybatisImpl();
	}

}
