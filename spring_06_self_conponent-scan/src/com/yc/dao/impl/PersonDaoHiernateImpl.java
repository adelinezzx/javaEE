package com.yc.dao.impl;

import com.yc.dao.PersonDao;
import com.yc.springframework.context.annotation.Component;

@Component(value="personDao")
public class PersonDaoHiernateImpl  implements  PersonDao{

	@Override
	public void add() {
		System.out.println("hibernate的personDao到添加");
		
	}

}
