package com.yc.dao;

public class PersonDaoImpl implements PersonDao {

	@Override
	public void add(int id ) {
		System.out.println("add" + id );	
	}

	@Override
	public int  del() {
		System.out.println("del");
		return  0;
	}
 
	@Override
	public void find() {
		System.out.println("find");
		
	}

	@Override
	public int update(int id) {
		System.out.println("update" + id );
		return  id;
	}

}
