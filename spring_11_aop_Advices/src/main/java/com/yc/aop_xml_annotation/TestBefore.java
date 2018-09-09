package com.yc.aop_xml_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"/Aop_beforeAdevice.xml"})*/
public class TestBefore {
	
/*	@Resource(name="math")
	private  Math  math  ;*/

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Aop_beforeAdevice2.xml");
		// 观察构造方法的调用时间
		Math pp = (Math) context.getBean("math");
		
		System.out.println(pp.add(2, 5));
		System.out.println(pp.sub(7, 5));
	}
	
	
}
