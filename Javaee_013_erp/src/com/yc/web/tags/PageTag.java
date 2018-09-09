package com.yc.web.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yc.model.PageBean;

public class PageTag extends TagSupport {
	
 
	private Object pageBean ;
	private String href ;

	public void setPageBean(Object pageBean) {
		this.pageBean = pageBean;
	}
	
	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out  = super.pageContext.getOut();
		try {
			
			if(pageBean !=null && pageBean instanceof PageBean ){
				PageBean pb = (PageBean)pageBean;
				pageShow(pb,out);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.EVAL_BODY_INCLUDE;
	 
	}

	private void pageShow(PageBean pb, JspWriter out) throws IOException {
		 String pageBar = "共"+pb.getTotal()+" 条记录 / 每页"+pb.getPagesize()+"条 共"+pb.getTotalpages()+"页/当前第"+pb.getPages()+"页";
		 pageBar += "<a href=\"" +href + "?pages=1\" >第一页</a>";
		 pageBar += "<a href=\"" +href +"?pages="+pb.getPrePage()+" \" >上一页</a>" ;
		 pageBar += "<a href=\"" +href +"?pages="+pb.getNextPage()+" \" >下一页</a>" ;
		 pageBar += "<a href=\"" +href +"?pages="+pb.getTotalpages()+" \" >最后一页</a>" ;
		out.println(pageBar);
	}

}
