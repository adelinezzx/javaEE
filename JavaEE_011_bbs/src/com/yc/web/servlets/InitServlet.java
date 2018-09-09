package com.yc.web.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.Board;
import com.yc.biz.impl.BoardBizImpl;

@WebServlet("/init.action")
public class InitServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	private BoardBizImpl bbi = new BoardBizImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<Integer, List<Board>> map = null;
		try {
			map = bbi.findAllBoard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("boardMap", map);

		request.getRequestDispatcher("WEB-INF/pages/show.jsp").forward(request,
				response);

	}

}
