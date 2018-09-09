package com.yc.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.bean.PageBean;
import com.yc.bean.Topic;
import com.yc.dao.DBUtil;

public class TopicBizImpl {
	
	 /**
	  * 发表帖子
	  * @param topic
	  * @return
	  */
	public int addTopic(Topic topic){
		StringBuffer sb = new StringBuffer() ;
		sb.append("insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid)  values(?,?,now(), now(),?,?);");
		return DBUtil.doUpdate(sb.toString(), topic.getTitle(),topic.getContent(),topic.getUid(),topic.getBoardid()   );
	}
	
	/**
	 * 通过topicid 查找 帖子  
	 * @param topicid
	 * @return
	 */
	public Topic findTopicById(int topicid){
		String sql = "  select   topicid , title , content ,date_format(publishtime,'%y-%m-%d  %H:%I:%S') as publishtime ," + 
							"  date_format(modifytime,'%y-%m-%d  %H:%I:%S') as modifytime ," + 
							"    tbl_topic.uid as userid ,  uname, head, date_format(regtime,'%y-%m-%d  %H:%I:%S') as regtime ,"+
							"  boardid     from tbl_topic  inner join tbl_user on tbl_topic.uid =tbl_user.uid   where topicid =?  ";
	//	System.out.println("findTopicByid : " + sql);
		return DBUtil.get(Topic.class, sql, topicid  );
	}
	
	/**
	 * 通过boardid 查找 帖子 并 分页
	 * @param boardid
	 * @param pages  当前多少页
	 * @param pagesize   每页多少条
	 * @return
	 */
	public List<Topic> findTopic(int boardid,int pages,int pagesize ){
		 StringBuffer sb = new StringBuffer() ;
		 sb.append("  select  a.topicid, title,content,publishtime,modifytime,uid as userid ,a.uname,boardid,total as replaycount from ");
		 sb.append("  (  select   topicid , title , content ,date_format(publishtime,'%y-%m-%d  %H:%I:%S') as publishtime ,");
		 sb.append("  date_format(modifytime,'%y-%m-%d  %H:%I:%S') as modifytime,tbl_topic.uid ,boardid,tbl_user.uname ");
		 sb.append("  from tbl_topic inner join tbl_user on tbl_topic.uid = tbl_user.uid where boardid=? ");
		 sb.append("  order  by modifytime desc limit ?,?   ) a   left join   ( select topicid ,count(*) as total from tbl_reply group by topicid  ");
		 sb.append("   ) b  on a.topicid = b.topicid   order by modifytime desc ");
				  
		 
		 //System.out.println("findtopic：" + sb.toString()); 
		 int start = (pages  - 1) * pagesize >= 0 ? ((pages  - 1) * pagesize) : 0;
		 return DBUtil.list(Topic.class, sb.toString(), boardid,start,pagesize); 
	}

	/**
	 * 查询分、当前板块下的帖子数量
	 * @param boardid
	 * @return
	 */
	public int findTopicCount(int boardid){
		String  sql = "select count(*) as total from  tbl_topic where boardid = ? " ;
		Map<String ,Object > map = DBUtil.get(sql, boardid);
				
		return Integer.parseInt(  map.get("TOTAL") .toString()  );
		
	}
	
	/**
	 * t帖子的分页
	 * @param boardid
	 * @param pages
	 * @param pagesize
	 * @return
	 */
	public PageBean findPageBean(int boardid ,int pages ,int pagesize ){
		PageBean pb = new PageBean();
		 List<Topic>  list =findTopic(boardid, pages, pagesize);
		 int total = findTopicCount(boardid);
		 //得到topic的数据后存入PageBean中
		 pb.setList(list);
		 pb.setPages(pages);
		 pb.setPagesize(pagesize);
		 pb.setTotal((long)total);
		 
		 int totalpages = total%pagesize ==0 ? total/pagesize :(total/pagesize + 1 ) ;
		 pb.setTotalPage((long)totalpages);
		 
		return pb;
		
	}
}
