package com.zzx.j2ee.util;

import static org.junit.Assert.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

public class DBUtilTest {

	@Test
	public void testGetConn() {
		assertNotNull(DBUtil.getConn());
	}

	@Test
	public void testJNDI() throws NamingException {
		Context cxt = new InitialContext();  //创建容器的上下对象
		String hello = (String) cxt.lookupLink("java:comp/env/hello");
		System.out.println(hello);
	}
}
