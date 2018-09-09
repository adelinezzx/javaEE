package com.yc.biz.impl;


import com.yc.biz.PersonBiz;
import com.yc.dao.PersonDao;
import com.yc.spring.Resource;

public class PersonBizImpl implements PersonBiz {	
	
	private PersonDao  person ;

	@Override
	public void add() {
	    person.add();
	}

	@Resource(name = "personDaoMybatisImpl")
	public void setPerson(PersonDao person) {
		this.person = person;
	}

	public PersonDao getPerson() {
		return person;
	}

	
	
}
