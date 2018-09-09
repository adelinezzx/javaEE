package com.yc.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

 
@WebFilter("/broserRecord.action")
public class BroserRecordFilter implements Filter {
  
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	   String  op = request.getParameter("op");
		if("redis".equals(op)){
			 redisOp(request,response);
		 }
		chain.doFilter(request, response);
	}

	private void redisOp(ServletRequest request, ServletResponse response) { 
		String ip = request.getRemoteAddr();
	}

	@Override
	public void destroy() { }

	@Override
	public void init(FilterConfig arg0) throws ServletException { }

 

}
