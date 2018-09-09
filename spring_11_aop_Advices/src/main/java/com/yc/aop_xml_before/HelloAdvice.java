package com.yc.aop_xml_before;

import org.aspectj.lang.JoinPoint;

public class HelloAdvice {
	
	public void   before(){
		//增强
		System.out.println("adeline");
	}
	
	//这个jp是spring容器利用  di 机制动态注入经历啊的  它保存了连接点的信息
	public void   adeline(JoinPoint  jp ){
		
		//增强
		System.out.println("JoinPoint连接点 即加入了增强点的信息");
		
		System.out.println(jp.getTarget()  );   //得到目标对象
		System.out.println(jp.getSignature()); //得到切入点的方法类型 以及  方法的路径  int com.yc.aop_xml_before.Math.add(int,int)
		 if(jp.getArgs() != null && jp.getArgs().length > 0 ){
			 for (Object obj : jp.getArgs()) {
				System.out.println(obj);  //循环列出方法中的参数
			}
		 }
	}

}
