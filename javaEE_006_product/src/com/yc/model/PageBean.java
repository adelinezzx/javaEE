package com.yc.model;

import java.util.List;

public class PageBean {

	private int total; // �ܼ�¼��
	private int pages; // ��ǰ�ǵڼ�ҳ
	private int pagesize; // ÿҳ������
	private List<Object> list; // �浱ǰҳ�����м�¼
	private int totalpages; // �ܹ��ж���ҳ

	/**
	 * ��һҳ,�����ǰҳ���ǵ�һҳ��������һҳʱҳ����һ�������ǰҳ�Ѿ��ǵ�һҳ�򲻱�
	 * @return
	 */
	public int getPrePage(){
	  if( pages >1 ){
		  return pages -1 ;
	  }else{
		  return 1 ;
	  }
	 }
	/**
	 * ��һҳ���жϵ�ǰҳ�ǲ������һҳ����������һҳ���򲻱䣬�������һҳ�������һҳ=��ǰҳ��+1��
	 * @return
	 */
	public int getNextPage(){
		if(pages >= totalpages ){
			return totalpages ;
		}else{
			return pages + 1 ;
		}
	}
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public int getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}
	
	@Override
	public String toString() {
		return "PageBean [total=" + total + ", pages=" + pages + ", pagesize="
				+ pagesize + ", list=" + list + ", totalpages=" + totalpages
				+ "]";
	}

}
