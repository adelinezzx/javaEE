package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.bean.Board;
import com.yc.bean.Topic;
import com.yc.dao.DBHelper;
import com.yc.dao.DBUtil;

public class BoardBizImpl {
	
	
	 public Board findAllBoardByTopicid(int topicid){
		 StringBuffer sb = new StringBuffer();
		 sb.append("select tbl_board.boardid,boardname,parentid   from tbl_board   inner join tbl_topic on tbl_board.boardid  = tbl_topic.boardid    where tbl_topic.topicid = ? ;");
		 // System.out.println("findAllBoardByTopicid :" +sb.toString()   );
		 return  DBUtil.get(Board.class, sb.toString(), topicid) ;
		 
	 }
	/**
	 * 取出所有的板块信息 Map<Integer,List<Board>> 0
	 * 
	 * @return
	 * @throws Exception 
	 */
	public Map<Integer, List<Board>> findAllBoard() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.boardid,boardname,parentid,total as topicsum ,topicid as recenttopicid ,title as recenttopictitle ,date_format( modifytime,'%y-%m-%d  %H:%I:%S') as recenttopicmodifytime ,uname as recenttopicusername from  ( ");
		sb.append(" select tbl_board.boardid,boardname,parentid, count(topicid) as total from tbl_board left join tbl_topic   on tbl_board.boardid = tbl_topic.boardid  ");
		sb.append(" group by tbl_board.boardid,boardid,boardname,parentid   ) a  left join (    select topicid,title,a.modifytime,uname,a.boardid from  (   select topicid ,title,modifytime,uname,boardid ");
		sb.append(" from tbl_topic    left join tbl_user on tbl_topic.uid = tbl_user.uid  )  a, (   select boardid ,max(modifytime) as modifytime from tbl_topic group by boardid  ");
		sb.append(" ) b  where a.boardid = b.boardid and a.modifytime = b.modifytime   ) b  on a.boardid = b.boardid ");
	//	System.out.println(sb.toString());

		
		 List<Board> list = DBUtil.list(Board.class, sb.toString(), null);
		//System.out.println(list);
		Map<Integer, List<Board>> map = new HashMap<Integer, List<Board>>();

		// 循环所有的list
		// 取出每个Board 中parentid
		// 根据parentid到map中查是否有这个父板块的id 的子版块列表
		// 如果有 则将这个board取到 这个子版块的列表中
		// 如果没有 则创建一个List<Board> 将这个board 存到List<Board> ，再将这个List<Board>存到map中
		for (Board b : list) {
			List<Board> sonlist = null;
			if (map.containsKey(   b.getParentid()   )) {
				sonlist = map.get(   b.getParentid()   );
			} else {
				sonlist = new ArrayList<Board>();
			}
			sonlist.add(b);
			map.put(b.getParentid(), sonlist);
		}
		return map ;
	}

}
