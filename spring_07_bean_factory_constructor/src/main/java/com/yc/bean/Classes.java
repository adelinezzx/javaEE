package com.yc.bean;

import com.yc.bean.Person;

public class Classes {

	private String claName;

	private Person person;

	public Classes() {
		super();
	}

	public Classes(String claName, Person person) {
		super();
		this.claName = claName;
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getClaName() {
		return claName;
	}

	public void setClaName(String claName) {
		this.claName = claName;
	}

	@Override
	public String toString() {
		return "Classes [person=" + person + "]";
	}

}
