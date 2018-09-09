package com.yc.biz;

import com.yc.dao.PersonDao;
import com.yc.dao.PersonDaoImpl;

//jdk只能对接口进行代理
public class PersonBizImpl implements PersonBiz {

	private PersonDao pb = new PersonDaoImpl();

	@Override
	public void add(int id) {
		pb.add(id);
	}

	@Override
	public int del() {
		return pb.del();
	}

	@Override
	public void find() {
		pb.find();
	}

	@Override
	public int update(int id) {
		return pb.update(id);
	}
}
