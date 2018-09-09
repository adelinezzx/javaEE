package com.yc.biz;

public class HelloProxyPersonBiz implements PersonBiz {

	private PersonBiz pb;

	public void setPb(PersonBiz pb) {
		this.pb = pb;
	}

	@Override
	public void add(int id) {
		hello();
		pb.add(id);
	}

	@Override
	public int del() {
		return pb.del();
	}

	@Override
	public int update(int id) {
		return pb.update(id);
	}

	@Override
	public void find() {
		pb.find();
	}

	private void hello() {
		System.out.println("hello");
	}

}
