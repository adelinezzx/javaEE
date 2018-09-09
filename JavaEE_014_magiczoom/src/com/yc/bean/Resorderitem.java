package com.yc.bean;

import java.io.Serializable;

public class Resorderitem implements Serializable {
	private static final long serialVersionUID = 4415732811555252222L;
	private int roiid ;
	private int roid ;
	private int fid   ;
	private double realprice ;
	private int num ;
	
	private String fname ;
	private double smallcount ;
	
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public double getSmallcount() {
		return smallcount;
	}
	public void setSmallcount(double smallcount) {
		this.smallcount = smallcount;
	}
	public int getRoiid() {
		return roiid;
	}
	public void setRoiid(int roiid) {
		this.roiid = roiid;
	}
	public int getRoid() {
		return roid;
	}
	public void setRoid(int roid) {
		this.roid = roid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public double getRealprice() {
		return realprice;
	}
	public void setRealprice(double realprice) {
		this.realprice = realprice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Resorderitem [roiid=" + roiid + ", roid=" + roid + ", fid=" + fid + ", dealprice=" + realprice
				+ ", num=" + num + "]";
	}
	
	
}
