package com.yc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.yc.bean.Companyinfo;
import com.yc.bean.Infotype;
import com.yc.bean.News;
import com.yc.bean.Product;
import com.yc.bean.Pronunciament;
import com.yc.biz.BaseBiz;
import com.yc.model.DBHelper;
import com.yc.model.PageBean;

@WebServlet("/init.action")
public class InitServlet extends BaseServlet {

	private Logger logger = Logger.getLogger(InitServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		BaseBiz bb = new BaseBiz();

		PageBean pronunciament = null;
		PageBean product = null;
		PageBean news = null;
		try {

			pronunciament = bb
					.findByPage(
							"select count(*) from Pronunciament  ",
							null,
							" select * from Pronunciament order by join_date , id  desc  limit 0, 10 ",
							null, 1, 10, Pronunciament.class);

			product = bb
					.findByPage(
							"select count(*) from product ",
							null,
							" select * from product   order by change_date desc limit 0, 10 ",
							null, 1, 10, Product.class);

			news = bb
					.findByPage(
							"select count(*) from news",
							null,
							" select * from news  order by change_date desc limit 0, 10    ",
							null, 1, 10, News.class);

			/*logger.info("初始化查询成功 。。 ");*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化查询失败：" + e);
		}
		HttpSession session = req.getSession();

		session.setAttribute("pronunciamentPageBean", pronunciament);
		session.setAttribute("productPageBean", product);
		session.setAttribute("newsPageBean", news);

		String infotype = "企业简介" ;
		ServletContext application = req.getServletContext() ;
		List<Infotype> infotypeList = (List<Infotype>) application.getAttribute("infotypeList");
		List<Companyinfo>  companyinfoList = (List<Companyinfo>) application.getAttribute("companyinfoList");
		
		for (Infotype it : infotypeList) {
			  for (Companyinfo ci : companyinfoList) {
				  if(it.getId() == ci.getTypeid()   && it.getTypename().equals(infotype)     ){
					  session.setAttribute("companyinfo", ci);
				  }
			}
		}
		
		req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
		
		
		
		
	}

}
