package com.yc.mysql;

import java.sql.SQLException;
import java.util.*;

import com.yc.utils.Encrypt;

public class UserBiz {
	private static DBHelper db = new DBHelper();

	// 注册
	public int reg(String uname, String upwd) throws SQLException {
		boolean r = isUnameExists(uname);
		if (r == false) {
			String sql = "insert into jspuser(id,uname,upwd) values(jspuser_seq.nextval,?,?)";
			List<Object> params = new ArrayList<Object>();
			params.add(uname);
//			upwd = Encrypt.md5(upwd); // 加密
			params.add(upwd);
			int result = db.doUpdate(sql, params);
			return result;
		} else {
			throw new RuntimeException("uname" + uname + "exists");
		}
	}

	//检查用户名是否存在，不存在则可以完成注册的操作
	public boolean isUnameExists(String uname) throws SQLException {
		String sql = "select * from jspuser where uname = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		List<Map<String, String>> list = db.select(sql, params);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 验证登录操作
	public double login(String uname, String upwd) throws SQLException {

		String sql = "select count(*) from jspuser where uname=? and upwd= ?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		params.add(upwd);
		double result = db.selectFunc(sql, params);
		if (result <= 0) {
			throw new RuntimeException("登录错误，你还没有注册！");
		}
		return result;

	}
}
