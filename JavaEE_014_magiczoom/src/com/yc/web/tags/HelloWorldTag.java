package com.yc.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
//��ǩ  tag
public class HelloWorldTag extends TagSupport {
	
	public HelloWorldTag() {
		System.out.println("HelloWorldTag����...");
	}

	@Override
	public int doEndTag() throws JspException {
		System.out.println("deEndTag����");
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		System.out.println(   "doStartTag");
		//��ȡ�����     pageContextҳ��������
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
