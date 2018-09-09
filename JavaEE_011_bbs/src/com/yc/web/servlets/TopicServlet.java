package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Board;
import com.yc.bean.PageBean;
import com.yc.bean.Topic;
import com.yc.biz.impl.BoardBizImpl;
import com.yc.biz.impl.ReplyBizImpl;
import com.yc.biz.impl.TopicBizImpl;

@WebServlet("/topic.action")
public class TopicServlet extends BaseServlet {
	
	private  TopicBizImpl tbi = new TopicBizImpl() ;
	private ReplyBizImpl rbi = new ReplyBizImpl();
	private BoardBizImpl bbi = new BoardBizImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 
		try {
			if ("list".equals(op)) {
				listOp(req, resp);//显示板块操作
			} else if("detail".equals(op)){
				detailOp(req,resp);//显示帖子操作
			} else if("post".equals(op)){
				postOp(req,resp);//显示帖子操作
			}else {
				resp.sendRedirect("404.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("500.jsp");
		}
	}

	/**
	 * 发表帖子
	 * @param req
	 * @param resp
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 */
	private void postOp(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		 Topic topic  = (Topic) super.parseParameterToT(req, Topic.class);
		int r = tbi.addTopic(topic);
		 
		if(  r  !=0 ){
			resp.sendRedirect("topic.action?op=list&boardid=" + topic.getBoardid()   ); 
		}
	}

	/**
	 * 显示发表的帖子
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void detailOp(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	     //1.根据topicid查出帖子，  再存到session中  
		Topic topic =  (Topic) super.parseParameterToT(req, Topic.class);
		 
		
		topic = tbi.findTopicById(      topic.getTopicid()   );
		
		HttpSession session = req.getSession();
		
		Board board = bbi.findAllBoardByTopicid(     topic.getTopicid()     );
		
		session.setAttribute("board", board);
		
		// 2.再存到session中  
		 
		session.setAttribute("topic", topic);
		
	 
		//3.得到pageBean  
		PageBean pb = rbi.findPageBean(topic.getTopicid(), pages, pagesize);
		//4.存到session
		session.setAttribute("replyListPageBean", pb);
		 
		req.getRequestDispatcher("/WEB-INF/pages/detail.jsp").forward(req, resp); ///JavaEE_011_bbs/WebContent/WEB-INF/pages/detail.jsp
	}

	/**
	 * 显示所有的板块
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	private void listOp(HttpServletRequest req, HttpServletResponse resp) throws  Exception {
	 
		Board board = ( Board) super.parseParameterToT(req, Board.class)   ;  //boardid  获取到页面的board对象
		
		//显示导航栏
		//1.通过session取到 存在属性boardMap中的   存在数据库的所有信息
		//show页面到list页面通过 boardid 来衔接， 在list页面得到boardid后  在数据库中得到board 的所有信息， 对比show页面传送过来的boardid
		//和数据库中board 的boardid比较 相同则得到 数据库中对应boardid的所有信息 显示在页面
		HttpSession session = req.getSession();   
		Map<Integer, List<Board>> map = (Map<Integer, List<Board>>) session.getAttribute("boardMap");
		for (Map.Entry<Integer, List<Board>>  entry : map.entrySet()) {
			List<Board> listBoard = entry.getValue();
			 for (Board b : listBoard) {  
				if(b.getBoardid() == board.getBoardid() ){  //2.如果客户端的 boardid 和数据库的 boardid 相等  
					board = b ;
					break ;
				}
			}
		}
		session.setAttribute("board", board);//3.将board 存入 session 
		
		PageBean pb = tbi.findPageBean(board.getBoardid(), pages, pagesize);
		 
		req.setAttribute("pb", pb);
		req.getRequestDispatcher("/WEB-INF/pages/list.jsp").forward(req, resp);  ///JavaEE_011_bbs/WebContent/WEB-INF/pages/list.jsp
	}
}
