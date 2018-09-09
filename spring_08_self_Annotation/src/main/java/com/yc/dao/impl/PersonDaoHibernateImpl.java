package com.yc.dao.impl;

import com.yc.bean.DataSourcePool;
import com.yc.dao.PersonDao;

public class PersonDaoHibernateImpl implements PersonDao {

	private DataSourcePool dsp;

	@Override
	public void add() {
		System.out.println("studentDaoHibernateImpl  .add ");
	}

}
