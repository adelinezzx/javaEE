package com.yc.biz.impl;

import javax.annotation.Resource;

import com.yc.biz.PersonBiz;
import com.yc.dao.PersonDao;
import com.yc.springframework.context.annotation.Autowired;
import com.yc.springframework.context.annotation.Component;

@Component(value="personBizImpl")
public class PersonBizImpl implements PersonBiz {

	@Autowired
	private PersonDao personDao ;
	
	
	@Override
	public void add() {
		personDao.add();
	}
	public PersonDao getPersonDao() {
		return personDao;
	}
	
	@Resource(name="personDaoMybatisImpl")
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
