package com.yc.biz;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yc.bean.Acjob;

public class BaseBizTest {

	@Test
	public void testFindCount() throws Exception {
		 BaseBiz  b = new BaseBiz() ;
		System.out.println( b.findCount("select count(*) from acjob  ",  null)  ) ;
	}

	@Test
	public void testFindList() {
		    BaseBiz  b = new BaseBiz() ;
			//System.out.println( b.findList("select jodid  , username  , sex  from acjob " ,  Acjob.class)   ) ;
	}

	@Test
	public void testFindByPage() {
		fail("Not yet implemented");
	}

}
