package com.yc.bean;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = -6303304523799895737L;
	private Integer pid;
	private String pname;
	private Double price;
	
	
	public Product() {
		super();
	}

	public Product(Integer pid, String pname, Double price) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.price = price;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", price=" + price
				+ "]";
	}

}
