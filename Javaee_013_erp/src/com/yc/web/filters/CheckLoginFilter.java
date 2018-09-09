package com.yc.web.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/back/*")
public class CheckLoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//ת���ͣ���ȥsession
		HttpServletRequest req = (HttpServletRequest) request ;
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("user" ) != null ){
			/*//ȡ��Ȩ��
			User u = (User)session.getAttribute("user");
			//ȡ�����е�Ȩ��
			Map<Integer,List<Func>>  rolemap =(Map<Integer,List<Func>>) req.getServletContext().getAttribute("rolemap");
		    //��ѯ�û�Ȩ���б�
			 
			List<Func> funclist = rolemap.get(  u.getRoleid()      );//����ridȡ����Ӧ���б�
		    session.setAttribute("funclist", funclist);*/
		    
		    chain.doFilter(request, response);
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			//ȡ������ַ
			String basePath = (String)request.getServletContext().getAttribute("basePath");
			out.println("<script>alert('����û�е�¼');location.href='"+ basePath+"login.jsp'</script>");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
