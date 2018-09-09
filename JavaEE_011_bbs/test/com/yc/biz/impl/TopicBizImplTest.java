package com.yc.biz.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class TopicBizImplTest {

	

	@Test
	public void testFindTopicByID() {
		TopicBizImpl tbi = new TopicBizImpl();
		tbi.findTopicById(16);
		System.out.println(tbi.findTopicById(16));
	}
	@Test
	public void testFindTopic() {
		TopicBizImpl tbi = new TopicBizImpl();
		tbi.findTopic(8, 0, 2);
		System.out.println(tbi.findTopic(8, 0, 2));
	}

	@Test
	public void testFindTopicCount() {
		TopicBizImpl tbi = new TopicBizImpl();
		tbi.findTopicCount(8);
		System.out.println(tbi.findTopicCount(8));
	}

	@Test
	public void testFindPageBean() {
		TopicBizImpl tbi = new TopicBizImpl();
		tbi.findPageBean(8, 0, 2);
		System.out.println(tbi.findPageBean(8, 0, 2));
	}

}
