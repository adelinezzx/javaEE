package com.yc.spring;

 

import com.yc.bean.Person;
import com.yc.biz.PersonBiz;
import com.yc.biz.impl.PersonBizImpl;
import com.yc.dao.PersonDao;
import com.yc.dao.impl.PersonDaoHiernateImpl;
import com.yc.dao.impl.PersonDaoMybatisImpl;
import com.yc.springframework.context.annotation.Bean;
import com.yc.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {
	
	@Bean
	public PersonBiz  personBizImpl(){
		return new PersonBizImpl();
	}
	@Bean
	public Person p(){
		return new Person();
		
	}
	@Bean
	public PersonDao personHiernateDaoImpl(){
		return new PersonDaoHiernateImpl();
	}
	
	public PersonDao personDaoMybatisImpl(){
		return new PersonDaoMybatisImpl();
	}
}
