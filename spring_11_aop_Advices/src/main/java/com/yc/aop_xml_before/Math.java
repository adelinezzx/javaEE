package com.yc.aop_xml_before;

public class Math   {

	 
	public int add(int a, int b) {
		 System.out.println( a + " + " + b + "= " + (a+b)  );
		 return  a+b ;
		
	}

	 
	public int sub(int a, int b) {
		System.out.println( a + " - " + b + "= " + (a+b)  );
		return a-b;
	}

	 
	public int mul(int a, int b) {
		System.out.println( a + " * " + b + "= " + (a+b)  );
		return a*b;
	}

	 
	public int div(int a, int b) {
		System.out.println( a + " / " + b + "= " + (a+b)  );
		return a/b;
	}
	
	 
}
