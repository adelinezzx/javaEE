package com.zzx.j2ee.service.impl;

import java.util.List;

import com.zzx.j2ee.entity.User;
import com.zzx.j2ee.service.UserService;
import com.zzx.j2ee.util.DBHelp;
import com.zzx.j2ee.util.DBUtil;

public class UserServiceImpl  implements UserService{

	@Override
	public boolean register(User user) {
		String sql ="insert into usersc values(sql_usersc.nextval,?,?)";
		return DBUtil.doUpdate(sql,user.getUsername(),user.getPassword()) > 0;
	}

	@Override
	public User login(User user) {
		String sql ="select * from usersc where username = ? and password = ?";
		return DBUtil.get(User.class, sql, user.getUsername(),user.getPassword());
	}

	@Override
	public User showUsername(User user) {
		String sql ="select * from usersc where username = ? ";
		return DBUtil.get(User.class, sql, user.getUsername());
	}

}
