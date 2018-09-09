package com.yc.bean;

import java.beans.ConstructorProperties;

public class Person {
	private String name;
	private double height; // 米
	private double weight; // 公斤

	private Classes  cla ;
	public Person() {
		super();
	}
 
	public Person(String name, Classes cla) {
		super();
		this.name = name;
		this.cla = cla;
	}


	public Classes getCla() {
		return cla;
	}

	public void setCla(Classes cla) {
		this.cla = cla;
	}

	public Person(String name, double height, double weight) {
		super();
		this.name = name;
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", height=" + height + ", weight=" + weight + "]";
	}

}
