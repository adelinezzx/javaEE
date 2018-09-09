package com.yc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yc.biz.AccountBiz;

@WebServlet("/account.action")
public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("op");

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
		AccountBiz accountBiz = (AccountBiz) ctx.getBean("accountBizImpl");
		if (op.equals("deposite")) {
			int accountid = Integer.parseInt(req.getParameter("accountid"));
			double money = Double.parseDouble(req.getParameter("money"));
			accountBiz.deposite(accountid, money);
			System.out.println("deposite   ok ");
		} else if (op.equals("withdraw")) {
			int accountid = Integer.parseInt(req.getParameter("accountid"));
			double money = Double.parseDouble(req.getParameter("money"));
			accountBiz.withdraw(accountid, money);
			System.out.println("deposite   ok ");
		} else if (op.equals("transfer")) {
			int inaccountid = Integer.parseInt(req.getParameter("inaccountid"));
			int outaccountid = Integer.parseInt(req.getParameter("outaccountid"));
			double money = Double.parseDouble(req.getParameter("money"));
			accountBiz.tansfer(inaccountid, outaccountid, money);
			System.out.println("deposite   ok ");
		}

	}
}
