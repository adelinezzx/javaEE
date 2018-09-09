package com.yc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.JsonModel;
import com.yc.bean.News;
import com.yc.bean.Product;
import com.yc.biz.BaseBiz;
import com.yc.model.DBHelper;
import com.yc.model.DataGridModel;
import com.yc.model.PageBean;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet({"/news.action","/backlogin/news.action"})
public class NewServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if ("show".equals(op)) {
				showOp(request, response);
			}else if("detail".equals(op)){
				detailOp(request,response);
			}else if("list".equals(op)){
				listOp(request,response);
			}else if("getAllNewsType".equals(op)){
				getAllNewsType(request,response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			String basePath = (String) request.getServletContext().getAttribute("basePath");
			
			request.getSession().setAttribute("errorMsg", e.getMessage());
			response.sendRedirect(basePath + "500.jsp");
		}
		 
	}

	private void getAllNewsType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		 List<News> News_class =  (List<News>) request.getServletContext().getAttribute("news_classList");
		 JsonModel jm = new JsonModel() ;
		 jm.setCode(1);
		 jm.setObj(News_class);
		 super.outJsonString(response, jm);
		
	}

	private void listOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DBHelper db = new DBHelper() ;
		int total =  (int) db.selectFunc("select count(*) from news ");
		int start = (page-1) * rows ;
		List<News> list = db.select(News.class, "select * from news order by " + sort + " " + order  + " limit " + start + " , " + rows );
		
		DataGridModel dgm = new DataGridModel() ;
		dgm.setRows(list);
		dgm.setTotal(total);
		
		super.outJsonString(response, dgm);
		
	}

	/**
	 * 新闻的详情
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void detailOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//先取出新闻类别id  --》根据类别查找该类别下的新闻条数 --》 如果有则取第一条  没有则返回0
	 Integer id = Integer.parseInt(request.getParameter("id")) ;
	 DBHelper db= new DBHelper() ;
	 List<News> list = db.select(News.class, "select * from news where id =  ? ", id);
	 News n = list != null && list.size()  >0 ? list.get(0) : null ;
	 request.setAttribute("news", n);
	 
	 request.getRequestDispatcher("/WEB-INF/pages/news/detail.jsp").forward(request, response);
		 
	}

	/**
	 * 显示新闻的操作
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void showOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer  id = null ; //1.取到新闻类别的id编号
		//2.判断分类id是否为空  如果为空， 则查出所有的新闻列表，如果类别id不为空，则查询新闻按类别id来查取
		if(     request.getParameter("id"  )     != null  && !"".equals(   request.getParameter("id")   )   ){ 
			id= Integer.parseInt(      request.getParameter("id")      )  ;
		}
		
		//1.当点击“新闻中心” 时    显示最新的10条新闻
		
		BaseBiz bb = new BaseBiz();
		//2.显示10条新闻以上时 对展示的新闻进行分页
		int start = (pages-1) * pagesize ;
		//int end = pages* pagesize ;
		PageBean 	newsPageBean  = null ;
		
		if(  id== null ){
			//3  显示
		 	newsPageBean = bb 	.findByPage( "select count(*) from news", 	
		 			null,  	" select * from news  order by change_date desc limit "+ start + ", " + pagesize ,
					null, pages, pagesize, News.class);
		}else{
			//按新闻类别id号来查询新闻
		    newsPageBean = bb 	.findByPage( "select count(*) from news where typeid=" + id , 
		    		null, 	" select * from news where typeid=  "+ id +"  order by change_date desc limit "+ start + ", " + pagesize ,
					null, pages, pagesize, News.class);
		}
	      
         request.setAttribute("newsPageBean", newsPageBean);
         request.setAttribute("id", id);
		 request.getRequestDispatcher("/WEB-INF/pages/news/news.jsp").forward(request, response);//Javaee_013_erp/WebContent/WEB-INF/pages/news/news.jsp
		
	}

}
