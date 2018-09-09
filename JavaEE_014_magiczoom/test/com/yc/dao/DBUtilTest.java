package com.yc.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBUtilTest {

	@Test
	public void testGetConn() {
		DBUtil db = new DBUtil();
		db.getConn();
		
	}
	
	@Test
	public void testGet() {
		DBUtil db = new DBUtil();
		db.get("select count(*) as num from resfood   ", null);
		
	}

}
