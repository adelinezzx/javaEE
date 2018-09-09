package com.yc.web.tag;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class GetmapbykeyTag extends TagSupport {
	 
	private static final long serialVersionUID = 1L;
	private  Object key ;
	private String var ;
	private Map map ;
	
	@Override
	public int doStartTag() throws JspException {
		 Object obj = null ;
		 //TODO : map   反射得到的类型。 再来转换key的类型 
		 Integer  k = new Integer(key.toString());
		 obj = map.get(k);    //List<Board>
		 super.pageContext.setAttribute(var, obj);
		return super.EVAL_BODY_INCLUDE;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	
}
