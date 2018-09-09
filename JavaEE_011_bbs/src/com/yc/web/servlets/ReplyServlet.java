package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.JsonModel;
import com.yc.bean.PageBean;
import com.yc.bean.Reply;
import com.yc.bean.Topic;
import com.yc.bean.User;
import com.yc.biz.impl.BoardBizImpl;
import com.yc.biz.impl.ReplyBizImpl;
import com.yc.biz.impl.ReplyRedisBizImpl;
import com.yc.biz.impl.TopicBizImpl;

@WebServlet("/reply.action")
public class ReplyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private TopicBizImpl tbi = new TopicBizImpl();
	private ReplyBizImpl rbi = new ReplyBizImpl();
	private BoardBizImpl bbi = new BoardBizImpl();
	
	private ReplyRedisBizImpl rrbi = new ReplyRedisBizImpl() ;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			if ("post".equals(op)) {
				postOp(req, resp);// 显示帖子操作
			}else if ("glktimes".equals(op)) {
			    glktOp(req, resp);// 显示帖子操作
			}else if ("glk".equals(op)) {
				 glkOp(req, resp);// 显示帖子操作
			} else if ("del".equals(op)) {
				 delOp(req, resp);// 显示帖子操作 
			} else if ("toupdate".equals(op)) {
				 updateOp(req, resp);// 显示帖子操作
			} else if ("doupdate".equals(op)) {
				 dodateOp(req, resp);// 显示帖子操作
			} else {
				resp.sendRedirect("404.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("500.jsp");
		}
	}

	private void dodateOp(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		Reply reply = (Reply) super.parseParameterToT(req, Reply.class);
		Topic topic  =  (Topic) req.getSession().getAttribute("topic");
		
		rbi.updateReply(reply);
		
		 resp.sendRedirect("topic.action?op=detail&topicid=" + topic.getTopicid()   );
	}

	/**
	 * 修改发表的帖子
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		int replyid = Integer.parseInt(    req.getParameter("replyid")       ) ; //取到当前的replyid
		//取出replyid  再查出reply  
		PageBean replyListPageBean = (PageBean) req.getSession().getAttribute("replyListPageBean"); //取到列表中的replyList
		
		List<Reply> list = replyListPageBean.getList() ;
		Reply reply = null ;
		for (Reply re : list) {
			if(   replyid == re.getReplyid()   ){  //如果当前的replyid  和列表中的replyid  相等  ，则取出在 列表中的对应的replyid的所有信息
				reply = re ;//存入reply对象中
				break;
			}
		}
		
		req.getSession().setAttribute("toupdatereply", reply);
		
		req.getRequestDispatcher("/back/updatereply.jsp").forward(req, resp);
	}

	/**
	 * 删除帖子
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	
	private void delOp(HttpServletRequest req, HttpServletResponse resp) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		HttpSession session = req.getSession();
		Topic topic = (Topic) session.getAttribute("topic");
		 Reply reply = (Reply) super.parseParameterToT(req, Reply.class);
		 
		  reply.setTopicid(       topic.getTopicid()      );
		 int r = rbi.delReply(reply);
		 resp.sendRedirect("topic.action?op=detail&topicid=" + reply.getTopicid()   );//topic.action?op=detail&topicid=16
		
	}

	/**
	 * 点赞数量
	 * @param req
	 * @param resp
	 */
	private void glktOp(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		
		 Reply reply = (Reply) super.parseParameterToT(req, Reply.class);
		 Long a = rrbi.gettimes (reply);
		 JsonModel jm = new JsonModel() ;
		 jm.setCode(1);
		 jm.setObj(a + "");
		 super.outJsonString(jm, resp);
	}

	
	/**
	 * 点赞的操作
	 * @param req
	 * @param resp
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IOException
	 */
	private void glkOp(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		
		 Reply reply = (Reply) super.parseParameterToT(req, Reply.class);
		 JsonModel jm = new JsonModel() ;
		 HttpSession session = req.getSession();
		 User u = (User) session.getAttribute("user");
		 
		 reply.setUid(    u.getUid()     );
		 
		 Long a = rrbi.glkReply(reply);
		 jm.setCode(1);
		 jm.setObj(a + "");
		 super.outJsonString(jm, resp);
	}
	
	
	/**
	 * 发表帖子
	 * @param req
	 * @param resp
	 */
	private void postOp(HttpServletRequest req, HttpServletResponse resp)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, IOException {

		Reply reply = (Reply) super.parseParameterToT(req, Reply.class);
		int r = rbi.addReply(reply);

		resp.sendRedirect("topic.action?op=detail&topicid=" + reply.getTopicid());

	}

}
