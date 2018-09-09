package com.yc.web.servlets;

import static com.yc.utils.YcConstants.ERROR_404;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Resuser;
import com.yc.biz.impl.ResuserBizImpl;

/**
 * Servlet implementation class BackResuserServlet
 */
@WebServlet("/resadmin/resuser.action")
public class BackResuserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	ResuserBizImpl  rbi = new ResuserBizImpl() ;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if ("showUserContributionList".equals(op)) {
				showUserContributionList(request, response);// 注册页面
			} else  if ("show".equals(op)) {
				showOp(request, response);// 注册页面
			} else {
				response.sendRedirect(ERROR_404);
			}
	}
	
	
	private void showOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 List<Resuser> list =  rbi.findAll(page, rows, sort, order);
		 int count = rbi.findCount();
		 Map<String , Object> map =new HashMap<String, Object>();
		 map.put("total", count);
		 map.put("rows", list);
		 
		 super.outJsonString(map, response);
		
	}
	/**
	 * easyUI表格中取出用户数据并xianshi
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void showUserContributionList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
	 Map<String,Object> map =  rbi.findResuserContribution(page, rows, sort, order);
	super.outJsonString(map, response);
	}

}
