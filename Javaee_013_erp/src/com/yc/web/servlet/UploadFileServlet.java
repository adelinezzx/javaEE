package com.yc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.google.gson.Gson;
import com.yc.bean.Admin;
import com.yc.bean.Job;
import com.yc.bean.JsonModel;
import com.yc.bean.News;
import com.yc.bean.News_class;
import com.yc.bean.Product;
import com.yc.model.DBHelper;
import com.yc.utils.FileUploadUtils;
import com.yc.utils.RequestUtils;

 
@WebServlet("/backlogin/uploadFile.action")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	 /**
     * 将对象转化为json字符串格式
     * @param response
     * @param obj
     * @throws IOException
     */
    protected  void outJsonString(HttpServletResponse response , Object obj) throws IOException{
    	Gson  gson = new Gson();
		String jsonString = gson.toJson(obj);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out  = response.getWriter();
		out.println(jsonString);
		out.flush();
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = null;
		  if(request.getParameter("op") != null &&  !"".equals(request.getParameter("op"))   ){
			  op=request.getParameter("op");
		  }  
		try {
			if("product".equals(op)){
				productUploadFile(request,response);
			}if("news".equals(op)){
				newsUploadFile(request,response);
			}if("addJob".equals(op)){
				addJob(request,response);
			}else{
				response.sendRedirect( "404.jsp");
			}
		} catch (Exception e) {
			String basePath = (String) request.getServletContext().getAttribute("basePath");
			e.printStackTrace();
			response.sendRedirect(basePath + "500.jsp");
		}
		 
	}
	
	/**
	 * 发表招聘信息
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
private void addJob(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
	PageContext pageContext = javax.servlet.jsp.JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
	FileUploadUtils fuu = new FileUploadUtils();
	Map<String,String> map = fuu.uploadFiles(pageContext);
	DBHelper db = new DBHelper() ;
	 JsonModel jm = new JsonModel() ;
	 Job job = RequestUtils.parseRequest(map, Job.class);
     int result  = db.doUpdate(" insert into job (inviter,number,address,wage,expressdate,demand,join_date)  values (? ,? ,?,?,?,?,now())" ,
	                            job.getInviter(),job.getNumber(),job.getAddress(),job.getWage(),job.getExpressdate(),job.getDemand()    );
	
     if(result  > 0 ){
    	 jm.setCode(1);
    	 outJsonString(response, jm);
     }else{
    	 jm.setCode(0);
     }
		
	}
/**
 * 新闻发表
 * @param request
 * @param response
 * @throws IOException
 */
private void newsUploadFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			PageContext pageContext = javax.servlet.jsp.JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
			FileUploadUtils fuu = new FileUploadUtils();
			JsonModel jm = new JsonModel() ;
			try {
				Map<String,String> map = fuu.uploadFiles(pageContext);
				News news = RequestUtils.parseRequest(map, News.class);
				//以上两步只是完成了在Tomcat下保存一张图片的功能
				//下面是在数据中存入图片的操作
				 DBHelper db = new DBHelper() ;
				 
				//根据新闻类别名来查取新闻类别id
			     int  id = (int) db.selectFunc("select id  from news_class where typename = ?", news.getNewsType() );
			     
			     db.doUpdate("insert into news(title,typeid,  content, picture , laiz, join_date,  change_date)"+ 
                                         " values(?,?,? ,?,? ,now() , now()  )",
				                            news.getTitle(),id,news.getContent(),  map.get("picture_relativepath"),news.getLaiz()  );
				jm.setCode(1);
			} catch (Exception e) {
				e.printStackTrace();
					jm.setCode(0);  
		     }
			
			outJsonString(response, jm);
		
	}


/**
 * 添加新上架的 产品信息
 * @param request
 * @param response
 * @throws IOException
 */
	private void productUploadFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		PageContext pageContext = javax.servlet.jsp.JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		FileUploadUtils fuu = new FileUploadUtils();
		JsonModel jm = new JsonModel() ;
		try {
			Map<String,String> map = fuu.uploadFiles(pageContext);
			Product p = RequestUtils.parseRequest(map, Product.class);
			//以上两步只是完成了在Tomcat下保存一张图片的功能
			//下面是在数据中存入图片的操作
		 
			Admin admin = (Admin) request.getSession().getAttribute("admin");//取到登录的用户信息
			DBHelper db = new DBHelper() ;
		   db.doUpdate("insert into Product(Product_picture,Product_class,Product_name , 	Product_in	 ,Product_explain		, Product_auditing		,index_show	,join_date		,change_date	) "+
			                       "values(?,?,?,?,?,?,1,now() , now()   )",
			                       map.get("product_picture_relativepath"),p.getProduct_class(),p.getProduct_name(),p.getProduct_in(),p.getProduct_explain(),admin.getId()  );
		   
		   
			jm.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
 			jm.setCode(0);  
         }
		
		outJsonString(response, jm);
		
	}
}
