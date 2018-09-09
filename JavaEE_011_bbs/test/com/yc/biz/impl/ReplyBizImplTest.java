package com.yc.biz.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReplyBizImplTest {

	@Test
	public void testFindReply() {
		ReplyBizImpl rbi = new ReplyBizImpl();
		rbi.findReply(5, 0, 5);
		System.out.println(rbi.findReply(5, 0, 5));
	}

	@Test
	public void testFindReplyCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindPageBean() {
		fail("Not yet implemented");
	}

	
	@Test
	public void test() {
		ReplyBizImpl rbi = new ReplyBizImpl();
		//rbi.updateReply(16);
	}
}
