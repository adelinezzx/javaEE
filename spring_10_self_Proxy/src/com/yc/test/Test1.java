package com.yc.test;

import com.yc.biz.HelloProxyPersonBiz;
import com.yc.biz.LogProxyPersonbiz;
import com.yc.biz.PersonBiz;
import com.yc.biz.PersonBizImpl;

import JDKProxy.HelloLogCgligProxy;
import JDKProxy.HelloProxy;
import JDKProxy.LogCgligProxy;
import JDKProxy.LogProxy;

public class Test1 {

	public static void main(String[] args) {
		/*
		 * PersonBiz pb = new PersonBizImpl(); //目标 真实主题
		 * 
		 * pb.add(1); pb.del(); pb.update(3); pb.find();
		 * 
		 * PersonBiz log = new LogProxyPersonbiz();//创建一个代理对象 //给这个代理对象设置
		 * 被代理的真实主题 ((LogProxyPersonbiz)log).setPb(pb);
		 * 
		 * log.add(1); log.del(); log.update(3); log.find();
		 * 
		 * System.out.println(
		 * "--------------------------------------------------");
		 * 
		 * PersonBiz hello = new HelloProxyPersonBiz();
		 * ((HelloProxyPersonBiz)hello).setPb(pb); hello.add(10); hello.del();
		 * hello.update(3); hello.find();
		 */

		System.out.println("-----------------动态代理---------------------------------");
		// 前置增强
		PersonBiz pb = new PersonBizImpl(); // 目标 真实主题
		LogProxy logproxy = new LogProxy();
		Object proxy = logproxy.createProxy(pb);
		PersonBiz pp = (PersonBiz) proxy;

		pp.add(2); // jvm 自动回调 invoke

		// 后置增强
		PersonBiz pb1 = new PersonBizImpl(); // 目标 真实主题
		HelloProxy hello = new HelloProxy();
		Object proxy1 = hello.createProxy(pb1);
		/*
		 * PersonBiz pp1= (PersonBiz) proxy1;
		 * 
		 * pp1.add(2); //jvm 自动回调 invoke
		 */
		System.out.println("-----------------代理对象再代理---------------------------------");
		// jdk的代理是可以对代理对象再代理
		HelloProxy hello2 = new HelloProxy();
		Object proxy2 = hello2.createProxy(proxy1);
		PersonBiz pp2 = (PersonBiz) proxy2;
		pp2.add(10);
		pp2.del();
		pp2.update(40);
		pp2.find();

		System.out.println("-----------------CGLib代理--------------------------------");
		PersonBizImpl  pbn = new PersonBizImpl() ;
		LogCgligProxy logcg = new LogCgligProxy();
		Object logcgProxy = logcg.createProxy(pbn);
		((PersonBizImpl)logcgProxy).add(90);
	 
		
		HelloLogCgligProxy hellologcg = new HelloLogCgligProxy();
		Object logcgProxy1 = hellologcg.createProxy(pbn);
		((PersonBizImpl)logcgProxy1).add(90);
	}

}
