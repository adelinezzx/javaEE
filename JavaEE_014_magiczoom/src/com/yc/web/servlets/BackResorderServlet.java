package com.yc.web.servlets;

import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.ERROR_500;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.JsonModel;
import com.yc.bean.Resadmin;
import com.yc.bean.Resorder;
import com.yc.bean.Resorderitem;
import com.yc.biz.impl.ResorderBizImpl;
import com.yc.print.SalesTicket;
import com.yc.print.YCPrinter;

 
@WebServlet("/resadmin/resorder.action")
public class BackResorderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    ResorderBizImpl  resorderbi = new ResorderBizImpl() ;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			if ("showOrderList".equals(op)) {
				showOrderList(request, response);
			} else if ("showOrderItemList".equals(op)) {
				showOrderItemList(request, response);
			} else if ("printOrder".equals(op)) {
				printOrder(request, response);
			}else if ("transfer".equals(op)) {
				transfer(request, response);
			} else {
				response.sendRedirect(ERROR_404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(ERROR_500);
		}
	}

	/**
	 * 在后台实现   配送  操作
	 * @param request
	 * @param response
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 */
	private void transfer(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		 try {
			Resorder resorder = (Resorder) super.parseParameterToT(request, Resorder.class);
			this.resorderbi.transfer(resorder);
			jm.setCode(1);
		} catch (Exception e) {
			 jm.setCode(0);
			 jm.setErrmsg(e.getMessage());
			e.printStackTrace();
		}
		 super.outJsonString(jm, response);
	}

	/**
	 * 在后台实现打单操作
	 * @param request
	 * @param response
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 */
	private void printOrder(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		 JsonModel jsonmodel = new JsonModel() ;
		 Resadmin resadmin = null ;
		 String admin = "admin" ;
		 if(request.getSession().getAttribute("login_resadmin") != null ){
			 resadmin = (Resadmin) request.getSession().getAttribute("login_resadmin");
			 admin = resadmin.getRaname();
		 }
		try {
			Resorder resorder = (Resorder) super.parseParameterToT(request, Resorder.class);//订单详情
			List<Resorderitem> list = resorderbi.findResorderItem(resorder);
			//计算销售总金额
			double count= 0 ;
			for(int i = 0 ;i<list.size() ;i++){
				Resorderitem ritem = list.get(i);
				count += ritem.getRealprice()*ritem.getNum() ;
				
			}
			//商品列表  操作员 订单编号 商品总数  总金额   实收款   找零
			SalesTicket  stk = new SalesTicket(list,admin,list.get(0).getRoid()+" ",list.size()+" " ,count+" " ,count+" " , "0");
			YCPrinter  p = new YCPrinter(stk);
			p.printer();
			jm.setCode(1);
		} catch (PrinterException e) {
			jm.setCode(0);
			e.printStackTrace();
		}
		
		super.outJsonString(jm, response);
	}

	/**
	 * 显示订单列表  的详情数据 
	 * @param request
	 * @param response
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 */
private void showOrderItemList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		 
		Map  map =new HashMap();
		try {
			Resorder resorder = (Resorder) super.parseParameterToT(request, Resorder.class);  //得到  roid=17
			List<Resorderitem> list = resorderbi.findResorderItem(resorder) ;  //通通过传送过来的 roid  来查询 到对用 的roid的所有信息
		/*	System.out.println(list);*/
			 
			map.put("total", list.size()   ) ;    //存入分页的信息
			map.put("rows", list);
			
			//取附言   因为附言是存在于  resorder中的 
			HttpSession session = request.getSession();
			if(session.getAttribute("ListResorder")  != null ){//如果订单列表 不为空  ，则取出列表 并循环  
				//如果发送过来的roid  和列表中的 roid  相等  则取出  订单列表中的  附言  并 存入 map 中
				List<Resorder> listResOrder = (List<Resorder>) session.getAttribute("ListResorder"); 
				//从orderJsonModel 中取出订单的附言
				for (Resorder re : listResOrder) { 
					 if(re.getRoid() ==  resorder.getRoid()   ) 
						 map.put("msg",  re.getPs()  )  ;
					     break ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.outJsonString(map, response);
	}

/**
 * 后台显示订单列表
 * 要求：按状态  显示下单时间
 * @param request
 * @param response
 * @throws IllegalAccessException
 * @throws IllegalArgumentException
 * @throws InvocationTargetException
 * @throws InstantiationException
 * @throws IOException 
 */
	private void showOrderList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
	 
		Map map = new HashMap ();
		try {
			Resorder resorder = (Resorder) super.parseParameterToT(request, Resorder.class);
			resorder.setOrderBy(this.sort);
			resorder.setOrder(this.order);
			resorder.setPages(this.pages);
			resorder.setPageSize(this.rows);
			List<Resorder>  resorderlist = resorderbi.findResorder(resorder);
			int total  = resorderbi.findCount(null);
			
			map.put("total", total);
			map.put("rows", resorderlist);
			
			//存好当前页面显示的order  的列表
			
			HttpSession session = request.getSession() ;
			session.setAttribute("ListResorder", resorderlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.outJsonString(map, response);
	}

}
