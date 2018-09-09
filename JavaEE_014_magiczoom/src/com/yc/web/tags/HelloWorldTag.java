package com.yc.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
//标签  tag
public class HelloWorldTag extends TagSupport {
	
	public HelloWorldTag() {
		System.out.println("HelloWorldTag构造...");
	}

	@Override
	public int doEndTag() throws JspException {
		System.out.println("deEndTag方法");
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		System.out.println(   "doStartTag");
		//获取输出流     pageContext页面上下文
		JspWriter out=super.pageContext.getOut();
		try {
			out.println("hello world");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//super.EVAL_BODY_INCLUDE
		return super.SKIP_BODY;    
	}
	
	
}
