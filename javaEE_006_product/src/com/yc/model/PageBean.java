package com.yc.model;

import java.util.List;

public class PageBean {

	private int total; // 总记录数
	private int pages; // 当前是第几页
	private int pagesize; // 每页多少条
	private List<Object> list; // 存当前页的所有记录
	private int totalpages; // 总共有多少页

	/**
	 * 上一页,如果当前页不是第一页，则点击上一页时页数减一。如果当前页已经是第一页则不变
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
	 * 下一页；判断当前页是不是最后一页，如果是最后一页，则不变，不是最后一页，点击下一页=当前页数+1；
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
