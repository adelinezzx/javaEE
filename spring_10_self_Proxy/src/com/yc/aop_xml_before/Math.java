package com.yc.aop_xml_before;

public class Math  implements  MyMath {

	@Override
	public int add(int a, int b) {
		 System.out.println( a + " + " + b + "= " + (a+b)  );
		 return  a+b ;
		
	}

	@Override
	public int sub(int a, int b) {
		System.out.println( a + " - " + b + "= " + (a+b)  );
		return a-b;
	}

	@Override
	public int mul(int a, int b) {
		System.out.println( a + " * " + b + "= " + (a+b)  );
		return a*b;
	}

	@Override
	public int div(int a, int b) {
		System.out.println( a + " / " + b + "= " + (a+b)  );
		return a/b;
	}
	
	 
}
