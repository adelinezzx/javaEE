package com.yc.web.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.CartItem;
import com.yc.bean.JsonModel;
import com.yc.bean.PageBean;
import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBizImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

//静态导入
import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.ERROR_500;
import static com.yc.utils.YcConstants.RESFOOD;
import static com.yc.utils.YcConstants.SESSIONCART;
import static com.yc.utils.YcConstants.SESSIONTOTAL;

@WebServlet("/resfood.action")
public class ResfoodServlet extends BaseServlet {
	private ResfoodBizImpl resfoodBiz = new ResfoodBizImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			if ("details".equals(op)) {
				detailsOp(req, resp);
			}else if("addCart".equals(op)) {
				addCartOp( req,resp);
			}else if("clearCart".equals(op)) {
				clearCart( req,resp);
			}else if("changeCount".equals(op)) {
				changeCount( req,resp);
			}else if("toCart".equals(op)) {
				toCartOp( req,resp);
			} else {
				resp.sendRedirect(ERROR_404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(ERROR_500);
		}
	}
	private void toCartOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/shopCart.jsp").forward(req, resp);
		
	}
	/**
	 * 修改订购数
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void changeCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int fid = Integer.parseInt(req.getParameter("fid"));
		int num=Integer.parseInt(req.getParameter("num"));
		//取出购物车
		Map<Integer,CartItem> cart=(Map<Integer, CartItem>) session.getAttribute( SESSIONCART  );
		CartItem ci=cart.get(fid);
		ci.setCount(  ci.getCount()+ num ); 
		if(   ci.getCount()<=0  ) {
			cart.remove(   fid );
		}else {
			cart.put(    fid, ci);
		}
		ci.getSmallCount();   //更新小计值
		session.setAttribute(SESSIONCART , cart);
		//计算总价格
		double total=countTotal(   cart );
		//回送到前台数据
		jm.setCode(1);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("total", total	);
		map.put("cartItem", ci );
		jm.setObj(   map );
		
		super.outJsonString(jm, resp);
		
		
	}
	/**
	 * 清空购物车
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute( SESSIONCART );
		session.removeAttribute( SESSIONTOTAL );
		req.getRequestDispatcher("/WEB-INF/pages/shopCart.jsp").forward(req, resp);
	}

	/**
	 * 商品详情
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void detailsOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int fid = Integer.parseInt(req.getParameter("fid"));
		Resfood resfood = getResfoodFromSession( fid);//根据ID得到该ID对应的所有信息
		session.setAttribute(RESFOOD, resfood);//存入session  再在页面上取出  显示该菜的详细信息
		req.getRequestDispatcher("/WEB-INF/pages/details.jsp").forward(req, resp);
	}

	/**
	 * 根据fid到session的pageBean中查这个商品
	 * @param req
	 * @param fid
	 * @return
	 */
	private Resfood getResfoodFromSession( int fid) {
		PageBean pageBean = (PageBean) session.getAttribute("pageBean");
		List<Resfood> list = pageBean.getList();
		for (Resfood rf : list) {
			if (rf.getFid() == fid) {
				return rf;
			}
		}
		return null;
	}

	/**
	 * 添加到购物车
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addCartOp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 取 fid
		int fid = 0  ;
		if(req.getParameter("fid") != null && !"".equals(req.getParameter("fid") ) ){
			fid= Integer.parseInt(req.getParameter("fid"));
		}
		 
		Resfood resfood = getResfoodFromSession( fid);   //取出要买的   resfood
		// 3.查出这个菜
		// 4. 从session取购物车
		Map<Integer, CartItem> cart = new HashMap<Integer, CartItem>();
		if (session.getAttribute(SESSIONCART) != null) {
			cart = (Map<Integer, CartItem>) session.getAttribute(SESSIONCART);
		}
		// 5. 判断购物车是否有这个fid
		CartItem ci = null;
		if (!cart.containsKey(fid)) {
			ci = new CartItem();	// 6. 没有, 则创建一个新的CartItem
			ci.setResfood(resfood);
			ci.setCount(1);      //购物车中没有则设置菜品的数量为1
		} else {
			ci = cart.get(fid);
			ci.setCount(ci.getCount() + 1);//// 有,则取出这个cartItem,将数量加一
		}
		cart.put(fid, ci);

		// 7. 将这个购物车又存到session中
		session.setAttribute(SESSIONCART, cart);

		double total = countTotal(cart);    //得到总价格  加入session
		session.setAttribute(SESSIONTOTAL, total);

		req.getRequestDispatcher("/WEB-INF/pages/shopCart.jsp").forward(req, resp);
	}
	/**
	 * 计算总价
	 * @param cart
	 * @return
	 */
	private double countTotal(Map<Integer, CartItem> cart) {
		// 统计总价
		double total = 0.0;
		for (Map.Entry<Integer, CartItem> entry : cart.entrySet()       ) {
			total += entry.getValue().getSmallCount();//得到每一种菜品的价格  再一次累加
		}
		return total;
	}

}
