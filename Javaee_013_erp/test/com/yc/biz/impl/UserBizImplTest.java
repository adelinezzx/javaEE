package com.yc.biz.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yc.bean.Admin;

public class UserBizImplTest {

	@Test
	public void testLogin() throws Exception {
	  UserBizImpl  ubi = new UserBizImpl() ;
	  ubi.login(new Admin("a","a"));
	}

}
