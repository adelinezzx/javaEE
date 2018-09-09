package com.zzx.j2ee.service;

import java.util.List;

import com.zzx.j2ee.entity.User;

public interface UserService {
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	boolean register(User user);
	
	/**
	 * 登录操作
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * 判断用户是否已经存在
	 * @param user
	 * @return
	 */
	User showUsername(User user);
}
