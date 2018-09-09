package com.yc.biz.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yc.bean.Resuser;

public class ResuserBizImplTest {

	@Test
	public void testLogin() {
		ResuserBizImpl user = new ResuserBizImpl();
		user.login(new Resuser());
		
		
	}

}
