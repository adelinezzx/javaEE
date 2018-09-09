package com.yc.dao.impl;

import javax.annotation.Resource;

 
import com.yc.dao.PersonDao;

public class PersonDaoMybatisImpl implements PersonDao {

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
