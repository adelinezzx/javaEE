package com.yc.bean;

import java.io.Serializable;

public class JsonModel  implements Serializable {

	private Integer code ;
	private Object obj ;
	private String uname ;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	@Override
	public String toString() {
		return "JsonModel [code=" + code + ", obj=" + obj + ", msg=" + uname
				+ "]";
	}
	
	
}
