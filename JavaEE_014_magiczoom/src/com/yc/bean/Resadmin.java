package com.yc.bean;

import java.io.Serializable;

public class Resadmin implements Serializable {
	private static final long serialVersionUID = 8278328989794399992L;
	private Integer raid;
	private String raname;
	private String rapwd;

	

	public Integer getRaid() {
		return raid;
	}

	public void setRaid(Integer raid) {
		this.raid = raid;
	}

	public String getRaname() {
		return raname;
	}

	@Override
	public String toString() {
		return "Resadmin [raid=" + raid + ", raname=" + raname + ", rapwd=" + rapwd + "]";
	}

	public void setRaname(String raname) {
		this.raname = raname;
	}

	public String getRapwd() {
		return rapwd;
	}

	public void setRapwd(String rapwd) {
		this.rapwd = rapwd;
	}

}
