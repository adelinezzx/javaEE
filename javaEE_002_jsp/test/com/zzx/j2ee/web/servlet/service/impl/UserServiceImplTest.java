package com.zzx.j2ee.web.servlet.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zzx.j2ee.entity.User;
import com.zzx.j2ee.service.UserService;
import com.zzx.j2ee.service.impl.UserServiceImpl;

public class UserServiceImplTest {

	@Test
	public void testRegister() {
		UserService us = new UserServiceImpl();
		boolean result = us.register(new User("cinsy", "q"));
		if(result){
			System.out.println("yes");
		}
	}

}
