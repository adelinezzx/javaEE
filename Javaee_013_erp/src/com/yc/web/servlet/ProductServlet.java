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
 
@WebServlet({"/product.action","/backlogin/product.action"})
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if ("show".equals(op)) {
				showOp(request, response);
			}else if("detail".equals(op)){
				detailOp(request,response);
			}else if("list".equals(op)){
				listOp(request,response);
			}else if("getAllProductClass".equals(op)){
				getAllProductClass(request,response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			String basePath = (String) request.getServletContext().getAttribute("basePath");
			
			request.getSession().setAttribute("errorMsg", e.getMessage());
			response.sendRedirect(basePath + "500.jsp");
		}
	}

	/**
	 * 在产品添加页面  显示产品类别在  选项框中
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getAllProductClass(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		 List<Product> product_classList = (List<Product>) request.getServletContext().getAttribute("product_classList");
		 JsonModel jm = new JsonModel() ;
		 jm.setCode(1);
		 jm.setObj(product_classList);
		 super.outJsonString(response, jm);
		
	}

	/**
	 * 产品详细信息展示
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void listOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DBHelper db = new DBHelper() ;
		int total =  (int) db.selectFunc("select count(*) from product ");
		int start = (page-1) * rows ;
		List<Product> list = db.select(Product.class, "select * from Product order by " + sort + " " + order  + " limit " + start + " , " + rows );
		
		DataGridModel dgm = new DataGridModel() ;
		dgm.setRows(list);
		dgm.setTotal(total);
		
		super.outJsonString(response, dgm);
	}

	/**
	 * 显示某类产品对应 的详细产品信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void detailOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//先取出产品信息类别id  --》根据类别查找该类别下的产品信息条数 --》 如果有则取第一条  没有则返回0
		Integer id = Integer.parseInt(request.getParameter("id")) ;
		 DBHelper db= new DBHelper() ;
		 List<Product> list = db.select(Product.class, "select * from product where id =  ? ", id);
		 Product n = list != null && list.size()  >0 ? list.get(0) : null ;
		 request.setAttribute("product", n);
		 
		 request.getRequestDispatcher("/WEB-INF/pages/product/detail.jsp").forward(request, response);
		
	}

	private void showOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer  product_class = null ; //1.取到产品信息类别的id编号
		//2.判断分类id是否为空  如果为空， 则查出所有的产品信息列表，如果类别id不为空，则查询产品信息按类别id来查取
		if(request.getParameter("product_class"  )     != null   &&  !(    request.getParameter("product_class").equals("")  )  ){ 
			product_class= Integer.parseInt(      request.getParameter("product_class")      )  ;
		}
		
		//1.当点击“产品信息” 时    显示
		
		BaseBiz bb = new BaseBiz();
		//2.显示10条产品信息以上时 对展示的新闻进行分页
		int start = (pages-1) * pagesize ;
		//int end = pages* pagesize ;
		PageBean 	newsPageBean  = null ;
		
		if(  product_class== null ){
			//3  显示
		 	newsPageBean = bb 	.findByPage( "select count(*) from product ", 	
		 			null, 	" select * from product  order by change_date desc limit "+ start + ", " + pagesize ,
					null, pages, pagesize, Product.class);
		}else{
			//按新闻类别id号来查询新闻
		    newsPageBean = bb 	.findByPage( "select count(*) from product where Product_class =" + product_class , 
		    		null, 	" select * from product where Product_class  =  "+ product_class +"  order by change_date desc limit "+ start + ", " + pagesize ,
					null, pages, pagesize, Product.class);
		}
	      
         request.setAttribute("productPagrBean", newsPageBean);
         
         request.setAttribute("product_class",product_class);
         
		 request.getRequestDispatcher("/WEB-INF/pages/product/product.jsp").forward(request, response);//Javaee_013_erp/WebContent/WEB-INF/pages/news/news.jsp
		
		
	}

}
