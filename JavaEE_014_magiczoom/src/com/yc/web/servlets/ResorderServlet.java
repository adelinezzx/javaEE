package com.yc.web.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.CartItem;
import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.biz.impl.ResorderBizImpl;

import static com.yc.utils.YcConstants.LOGINUSER;
import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.ERROR_500;
import static com.yc.utils.YcConstants.SESSIONCART;
import static com.yc.utils.YcConstants.SESSIONTOTAL;
@WebServlet("/resuser/resorder.action")
public class ResorderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ResorderBizImpl rsbi = new ResorderBizImpl() ; 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException   {
		try {
			if ("toFillForm".equals(op)) {
				toFillForm(request, response);
			}if ("makeOrder".equals(op)) {
				makeOrder(request, response);
			} else{
				response.sendRedirect(ERROR_404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(ERROR_500);
		}
	}

	/**
	 * 提交订单的事务
	 * 1.添加resorder,address ,userid ,tel , ps
	 * 2.取出 resorder中的roid  订单号 
	 * 3.循环购物车sessionCart  向resorderitem 中添加数据
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void makeOrder(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ServletException, IOException {
		//购物车
		Map<Integer,CartItem> shopcart =  (Map<Integer, CartItem>) session.getAttribute(SESSIONCART);
		//取到下单的用户
		Resuser resuser  = (Resuser) session.getAttribute(LOGINUSER);
		//取到下单页面的数据
		Resorder resorder = (Resorder) super.parseParameterToT(request, Resorder.class);
		try {
			rsbi.makeorder(resorder, shopcart, resuser);
			session.removeAttribute(SESSIONCART);
			session.setAttribute(SESSIONTOTAL, 0.0);
			request.getRequestDispatcher("/WEB-INF/pages/seeyou.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "makeorder failed !");
			request.getRequestDispatcher("/WEB-INF/pages/checkOut.jsp").forward(request, response);
		}
		
	}
	
/**
 *点击 提交订单  跳转到     提交订单   页面  
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	private void toFillForm(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 
		if(  session.getAttribute(SESSIONCART) == null &&  (  (Map) session.getAttribute(SESSIONCART)  ).size() <= 0 ){
			//如果购物车中没有商品 ，则 不能提交订单   显示  并返回购物车
			request.setAttribute("msg", "cart should not be empty !");
			request.getRequestDispatcher("/WEB-INF/pages/shopCart.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/WEB-INF/pages/checkOut.jsp").forward(request, response);
		}
	}

}
