package com.yc.web.tags;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ForEachTag extends TagSupport {
	private List items;
	private String var;
	int index=0;
	int size=0;
	
	@Override
	public int doStartTag() throws JspException {
		size=items.size();
		//取出items中第一个元素，存到  以var为键的  pageContext
		Object obj=items.get(index);
		super.pageContext.setAttribute(var, obj);
		return super.EVAL_BODY_INCLUDE;
	}
	

	@Override
	public int doAfterBody() throws JspException {
		index++;
		if(  index>=size) {
			return super.SKIP_BODY;
		}else {
			Object obj=items.get(index);
			super.pageContext.setAttribute(var, obj);
			return super.EVAL_BODY_AGAIN;
		}
	}



	public void setItems(List items) {
		this.items = items;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
