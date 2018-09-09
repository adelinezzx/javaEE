package com.yc.biz.impl;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yc.bean.Board;

public class BoardBizImplTest {

	@Test
	public void testFindAllBoard() throws Exception {
		BoardBizImpl bb = new BoardBizImpl() ;
		Map<Integer,List<Board> >map = bb.findAllBoard();
		System.out.println(  map.get(new Integer(0)  )  );
	}

}
