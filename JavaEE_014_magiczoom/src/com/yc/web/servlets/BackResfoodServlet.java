package com.yc.web.servlets;

import static com.yc.utils.YcConstants.ERROR_404;
import static com.yc.utils.YcConstants.ERROR_500;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Resfood;
import com.yc.biz.impl.ResfoodBizImpl;

 
@WebServlet("/resadmin/resfood.action")
public class BackResfoodServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
  
	private ResfoodBizImpl  resfoodbiz = new ResfoodBizImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		try {
			if("showFoodSellInfoRank".equals(op)){
				showFoodSellInfoList(request,response);
			}if("resfoodInfoList".equals(op)){
				resfoodInfoList(request,response);
			}if("updateResfoodInfo".equals(op)){
				updateResfoodInfo(request,response);
			}else{
				response.sendRedirect(ERROR_404);
			}
		} catch (Exception e) {
			response.sendRedirect(ERROR_500);
			e.printStackTrace();
		}
	}

	/**
	 * 菜单浏览
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void resfoodInfoList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map  map = new HashMap();  //  total  rows 
		try {
			Resfood resfood = (Resfood) super.parseParameterToT(request, Resfood.class);
			
			resfood.setOrderBy(this.sort);
			resfood.setOrder(this.order);
			resfood.setPages(this.page);
			resfood.setPageSize(this.rows);
			 List<Resfood> listresfood = resfoodbiz.findResfood(resfood);
			 long total = resfoodbiz.findResfoodCount(resfood);
			 map.put("total", total) ;
			 map.put("rows", listresfood);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.outJsonString(map, response);
	 
	}

	/**
	 * 修改菜品信息
	 */
	private void updateResfoodInfo(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		 Map map= new HashMap();
		 Resfood  resfood = (Resfood) super.parseParameterToT(request, Resfood.class);
		 resfoodbiz. updateResfoodInfo(resfood);
		 map.put("code", 1);
		 super.outJsonString(map, response);
		
	}

	/**
	 * 后台实现  销售排行   
	 * 1.定义map 来存入 分页所需的 total 和 rows 
	 * 2.查询resfood的相关信息  
	 * 3，再通过json传到前端
	 * @param request
	 * @param response
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 */
	private void showFoodSellInfoList(HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		 
		Map  map = new HashMap();  //  total  rows 
		try {
			Resfood resfood = (Resfood) super.parseParameterToT(request, Resfood.class);
			
			resfood.setOrderBy(this.sort);
			resfood.setOrder(this.order);
			resfood.setPages(this.page);
			resfood.setPageSize(this.rows);
			 List<Resfood> listresfood = resfoodbiz.findResfoodSellCountList(resfood);
			 
			 long total = resfoodbiz.findResfoodCount(resfood);
			 map.put("total", total) ;
			 map.put("rows", listresfood);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		super.outJsonString(map, response);
		
	}

}
