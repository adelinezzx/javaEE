package com.yc.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.bean.PageBean;
import com.yc.bean.Reply;
import com.yc.bean.Topic;
import com.yc.dao.DBUtil;

public class ReplyBizImpl {
	
	public int updateReply(Reply reply){
		String sql = "update tbl_reply   set   title = ? ,content = ? ,modifytime = now() where replyid  = ? ";
		return DBUtil.doUpdate(sql, reply.getTitle()  ,reply.getContent(),     reply.getReplyid()    ) ;
	}
	
	/**
	 * 删除回复的帖子
	 * @param reply
	 * @return
	 */
	public int delReply(Reply reply){
		String sql = "delete from tbl_reply where replyid = ? " ;
		return DBUtil.doUpdate(sql, reply.getReplyid()   ) ;
	}
	
	/**
	 * 对发表的添加回复
	 * @param reply
	 * @return
	 */
	public int addReply(Reply reply){
		StringBuffer sb = new StringBuffer() ;
		sb.append("insert into tbl_reply(title,content,publishtime,modifytime,uid,topicid) values(?,?,now(),now(),?,?)");
		return DBUtil.doUpdate(sb.toString(), reply.getTitle()  ,reply.getContent(),    reply.getUid() ,    reply.getTopicid()  ) ;
	}
	
	
	/**
	 *  找到所有的回复 并  分页
	 * @param topicid
	 * @param pages
	 * @param pagesize
	 * @return
	 */
	public List<Reply> findReply(int topicid, int pages, int pagesize) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select replyid ,title ,content,date_format( modifytime,'%y-%m-%d  %H:%I:%S')  as modifytime ,");
		sb.append("    date_format( modifytime,'%y-%m-%d  %H:%I:%S')  as modifytime ,tbl_reply.uid as userid , topicid, ");
		sb.append("   uname ,head,   date_format( regtime,'%y-%m-%d  %H:%I:%S')  as regtime  from tbl_reply ");
		sb.append("   inner join tbl_user on tbl_reply.uid = tbl_user.uid   where topicid = ?  ");
		sb.append("  order by modifytime desc   ");
		sb.append("    limit ?,? ;");

		//System.out.println("findReply：" + sb.toString());
		int start = (pages - 1) * pagesize >= 0 ? ((pages - 1) * pagesize) : 1;
		return DBUtil .list(Reply.class, sb.toString(), topicid, start, pagesize);
	}

	/**
	 * 查询当前主题下的回复数量
	 * 
	 * @param topicid
	 * @return
	 */
	public int findReplyCount(int topicid) {
		String sql = "select count(*) as total from  tbl_reply where topicid = ? ";
		Map<String, Object> map = DBUtil.get(sql, topicid);
		return Integer.parseInt(map.get("TOTAL").toString());

	}

	/**
	 *   得到回复列表的分页  存入PageBean对象 
	 * @param topicid
	 * @param pages
	 * @param pagesize
	 * @return
	 */
	public PageBean findPageBean(int topicid, int pages, int pagesize) {
		PageBean pb = new PageBean();
		List<Reply> list = findReply(topicid, pages, pagesize);
		//System.out.println("list:" + list);
		int total = findReplyCount(topicid);
		// 得到topic的数据后存入PageBean中
		pb.setList(list);
		pb.setPages(pages);
		pb.setPagesize(pagesize);
		pb.setTotal((long) total);

		int totalpages = total % pagesize == 0 ? total / pagesize : (total
				/ pagesize + 1);
		pb.setTotalPage((long) totalpages);

		return pb;

	}
}
