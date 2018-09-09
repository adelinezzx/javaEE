package com.yc.bean;
import com.yc.bean.Person;

public class Classes {
	
	private Person  person ;

	 

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
