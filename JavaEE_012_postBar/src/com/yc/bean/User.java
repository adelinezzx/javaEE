package com.yc.bean;

public class User {
	
	private  Integer  uid ;
	private String uname ;
	private String tel ;
	private String upass ;
	
	
	public User() {
	}

	public User(Integer uid, String uname, String tel, String upass) {
		this.uid =uid;
		this.uname = uname;
		this.tel = tel;
		this.upass = upass;
	}
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer tid) {
		this.uid = tid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", tel=" + tel
				+ ", upass=" + upass + "]";
	}
	
	

}
