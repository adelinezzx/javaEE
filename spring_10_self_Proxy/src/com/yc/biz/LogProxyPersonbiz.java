package com.yc.biz;

import java.text.SimpleDateFormat;
import java.util.Date;

//LogProxyPersonbiz:代理主题       实现同一个接口(抽象接口)
public class LogProxyPersonbiz implements PersonBiz {

	private PersonBiz pb;

	public void setPb(PersonBiz pb) {
		this.pb = pb;
	}

	// 必须要有一个真实主题的引用
	@Override
	public void add(int id) {
		pb.add(id); // 调入真实主题被代理的方法
		log(); // 后置增强
	}

	@Override
	public int del() {
		log();// 前置增强
		return pb.del();
	}

	@Override
	public int update(int id) {
		int result = 0;
		try {
			result = pb.update(id); // 加入try catch : 环绕增强
		} catch (Exception e) {
			e.printStackTrace();
		}
		log();
		return result;

	}

	@Override
	public void find() {
		pb.find();
		log();
	}

	// 关注点
	private void log() {
		System.out.println("------------");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD  HH:mm:ss");
		System.out.println("操作时间：" + sdf.format(d));
	}

}
