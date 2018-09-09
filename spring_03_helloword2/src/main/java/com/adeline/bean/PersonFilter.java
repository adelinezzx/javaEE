package com.adeline.bean;

import org.springframework.stereotype.Component;

@Component
public class PersonFilter implements Filter {

	@Override
	public boolean filter(Object obj) {
		 if(  obj==null){
			 return false ;
		 }
		 if( !(obj instanceof Person)){
			 return false;
		 }
	 
		 Person p = (Person) obj;
		 if(p.getHeight() <1 || p.getHeight() > 2.5){
			 return false ;
		 }
		 if(p.getWeight()<20 || p.getWeight() >500){
			 return false ;
		 }
		return true;
	}

}
