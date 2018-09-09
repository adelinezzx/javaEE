package com.yc.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Acjob;
import com.yc.bean.Job;
import com.yc.bean.JsonModel;
import com.yc.bean.News;
import com.yc.bean.Product;
import com.yc.bean.Server;
import com.yc.biz.BaseBiz;
import com.yc.model.DBHelper;
import com.yc.model.DataGridModel;
import com.yc.model.PageBean;
import com.yc.utils.RequestUtils;

@WebServlet({"/job.action","/backlogin/job.action"})
public class JobServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if ("show".equals(op)) {
				showOp(request, response);
			}else if("detail".equals(op)){
				detailOp(request,response);
			}else if("acjob".equals(op)){//申请工作
				acjobOp(request,response);
			}else if("listJobInfo".equals(op)){//在easyUI 表格中显示招聘工作信息  
				listJobInfo(request,response);
			} 

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			String basePath = (String) request.getServletContext().getAttribute("basePath");
			
			request.getSession().setAttribute("errorMsg", e.getMessage());
			response.sendRedirect(basePath + "500.jsp");
		}
	}
	
	 
/**
 * 在easyUI 表格中显示招聘工作信息
 * @param request
 * @param response
 * @throws Exception 
 */
	private void listJobInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DBHelper db = new DBHelper() ;
		int total =  (int) db.selectFunc("select count(*) from job ");
		int start = (page-1) * rows ;
		List<Job> list = db.select(Job.class, "select * from job order by " + sort + " " + order  + " limit " + start + " , " + rows );
		
		DataGridModel dgm = new DataGridModel() ;
		dgm.setRows(list);
		dgm.setTotal(total);
		
		super.outJsonString(response, dgm);
		
	}

	/**
	 * 申请招聘
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void acjobOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer  jobid = Integer.parseInt( request.getParameter("jobid"));
		
		Acjob  acjob =RequestUtils.parseRequest(request, Acjob.class);
		
		 DBHelper db= new DBHelper() ;
		 
		 int result  = db.doUpdate("insert into acjob(jobid,username,sex,school,telephone,email) values(?,?,?,?,?,?)  ",jobid,acjob.getUsername(),acjob.getSex(),acjob.getSchool(),acjob.getTelephone(),acjob.getEmail() );
		 
		 if(result  >  0 ){
			 response.getWriter().println("<script>alert('申请成功'); location.href='index.jsp'</script>");
		 }else{
			 response.getWriter().println("<script>alert('申请失败'); location.href='index.jsp'</script>");
		 }
		
	}

	/**
	 * 显示发布的招聘信息的详细内容
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void detailOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	     Integer id = Integer.parseInt(     request.getParameter("id")     ) ;
		 DBHelper db= new DBHelper() ;
		 List<Job> list = db.select(Job.class, "select * from job where id =  ? ", id);
		 Job n = list != null && list.size()  >0 ? list.get(0) : null ;
		 request.setAttribute("job", n);
		 
		 request.getRequestDispatcher("/WEB-INF/pages/job/detail.jsp").forward(request, response);
		
		
	}

	/**
	 *显示发布的招聘信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void showOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		BaseBiz bb = new BaseBiz();
		 
		int start = (pages-1) * pagesize ;
		 
		PageBean 	jobPageBean   = bb 	.findByPage( "select count(*) from job ", 	
		 			null, 	" select * from job  order by join_date desc limit "+ start + ", " + pagesize ,
					null, pages, pagesize, Job.class);
		 
	      
         request.setAttribute("jobPageBean", jobPageBean);
         
         
		 request.getRequestDispatcher("/WEB-INF/pages/job/job.jsp").forward(request, response); 
	}

}
