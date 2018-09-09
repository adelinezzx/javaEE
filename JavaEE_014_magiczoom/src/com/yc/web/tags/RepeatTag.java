package com.yc.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class RepeatTag extends TagSupport {
	
	private int times=0;
	private int count=0;

	@Override
	public int doAfterBody() throws JspException {
		count++;
		if( count>=times) {
			return super.SKIP_BODY;
		}else {
			return super.EVAL_BODY_AGAIN;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return super.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		return super.EVAL_BODY_INCLUDE;
	}

	public void setTimes(int times) {
		
		this.times = times;
	}
	
	
	
}
