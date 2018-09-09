package com.yc.MavenProject.spring_11_aop_BeforeAdvices;

import org.springframework.stereotype.Component;

@Component
public class Person1 implements Student, Classes {

	private int studntid;

	@Override
	public void find() {
		System.out.println("class");
	}

	public int getStudntid() {
		return studntid;
	}

	public void setStudntid(int studntid) {
		this.studntid = studntid;
	}

	@Override
	public void getClassNum() {
		// TODO Auto-generated method stub

	}

}
