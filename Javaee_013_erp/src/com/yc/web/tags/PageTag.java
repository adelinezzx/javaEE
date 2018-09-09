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
		 String pageBar = "��"+pb.getTotal()+" ����¼ / ÿҳ"+pb.getPagesize()+"�� ��"+pb.getTotalpages()+"ҳ/��ǰ��"+pb.getPages()+"ҳ";
		 pageBar += "<a href=\"" +href + "?pages=1\" >��һҳ</a>";
		 pageBar += "<a href=\"" +href +"?pages="+pb.getPrePage()+" \" >��һҳ</a>" ;
		 pageBar += "<a href=\"" +href +"?pages="+pb.getNextPage()+" \" >��һҳ</a>" ;
		 pageBar += "<a href=\"" +href +"?pages="+pb.getTotalpages()+" \" >���һҳ</a>" ;
		out.println(pageBar);
	}

}
