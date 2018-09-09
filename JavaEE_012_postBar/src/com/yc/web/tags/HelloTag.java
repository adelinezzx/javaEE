package com.yc.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class HelloTag  extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		// 
		JspWriter out  = super.pageContext.getOut();
		try {
			out.println("<h1>hello Word </h1>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.SKIP_BODY;
	}
}
