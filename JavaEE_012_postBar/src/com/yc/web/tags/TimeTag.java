package com.yc.web.tags;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TimeTag extends TagSupport {
	private String format ="YYY年MM月dd日HH:mm:ss";
	
     @Override
    public int doStartTag() throws JspException {
    	// 
 		JspWriter out  = super.pageContext.getOut();
 		try {
 			out.println("<p>时间: </p>" + new Date() );
 			out.flush();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		return super.EVAL_BODY_INCLUDE;
    }


	public void setFormat(String format) {
		
		this.format = format;
	}
     
     
}
