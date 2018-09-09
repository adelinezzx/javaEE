package com.yc.model;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员的数据表格对象
 * @author Administrator
 *
 */
public class DataGridModel  implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total  ;
	private List rows ;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	

}
