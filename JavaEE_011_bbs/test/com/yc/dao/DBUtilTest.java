package com.yc.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DBUtilTest {

	@Test
	public void testGetConn() {
		 DBUtil db = new DBUtil();
		 try {
			 db.getConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
