package com.yc.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Resuser implements Serializable {

	private static final long serialVersionUID = 1676684284807690359L;

	private int userid;
	private String username;
	private String pwd;
	private String email;

	private String repwd;    //重复密码
	
	private double dealcount;


	public double getDealCount() {
		return dealcount;
	}

	public void setDealCount(double dealCount) {
		this.dealcount = dealCount;
	}

	public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Resuser [userid=" + userid + ", username=" + username + ", pwd=" + pwd + ", email=" + email + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
