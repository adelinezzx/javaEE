package com.yc.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer stuId;
	private String stuName;
	private String stuSex;
	private Date stuBirthday;

	
	public String getStuBirthdayFormat(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(stuBirthday);
	}
	
	public Student(Integer stuId, String stuName, String stuSex, Date stuBirthday ) {
		this.stuId = stuId;
		this.stuName = stuName;
		this.stuSex = stuSex;
		this.stuBirthday = stuBirthday;
	}

	public Student() {
		super();
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public Date getStuBirthday() {
		return stuBirthday;
	}

	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

 
	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuName=" + stuName + ", stuSex=" + stuSex + ", stuBirthday="
				+ stuBirthday +   "]";
	}

}
