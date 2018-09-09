package com.yc.bean;
import com.yc.bean.Person;

public class Classes {
	
	private Person  person ;

	 

	public Classes() {
	System.out.println("class的构造方法");
	}



	public Person getPerson() {
		return person;
	}



	public void setPerson(Person person) {
		this.person = person;
	}



	@Override
	public String toString() {
		return "Classes [person=" +person + "]";
	}
	
	

}
