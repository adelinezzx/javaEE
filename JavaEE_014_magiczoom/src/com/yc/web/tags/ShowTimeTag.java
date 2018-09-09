package com.yc.web.tags;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ShowTimeTag extends TagSupport {

	private String format = "yyyy-MM-dd HH:mm:SS";

	@Override
	public int doStartTag() throws JspException {
		Date d=new Date();
		DateFormat df=new SimpleDateFormat(format);
		JspWriter out=super.pageContext.getOut();
		try {
			out.println(df.format(d));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.EVAL_BODY_INCLUDE;
	}

	public void setFormat(String format) {
		System.out.println("调用了set方法:"+ format);
		this.format = format;
	}


	
	

}
