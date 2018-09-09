package com.yc.mysql;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class UserBizTest {

	@Test
	public void testReg() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsUnameExists() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() throws Exception {
		UserBiz ub = new UserBiz();
		try {
			double r = ub.login("a", "a");
			if(r>0){
				System.out.println("ok");
			}else{
				System.out.println("no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
