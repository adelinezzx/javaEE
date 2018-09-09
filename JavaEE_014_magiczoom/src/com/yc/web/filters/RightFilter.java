package com.yc.web.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.yc.utils.YcConstants.LOGINUSER;
import static com.yc.utils.YcConstants.LOGINPAGE;;
@WebFilter("/resuser/*")
public class RightFilter implements Filter {

 
    public RightFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpSession session=req.getSession();
		if( session.getAttribute(LOGINUSER)==null) {
			HttpServletResponse resp=(HttpServletResponse) response;
			resp.setContentType("text/html;charset=utf-8");
		   request.getRequestDispatcher(LOGINPAGE).forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
