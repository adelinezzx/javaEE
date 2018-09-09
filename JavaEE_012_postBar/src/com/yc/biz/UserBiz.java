package com.yc.biz;

import java.util.*;

import com.yc.bean.User;
import com.yc.model.DBHelper;
import com.yc.utils.Encrypt;


public class UserBiz {
	private static DBHelper db = new DBHelper();

	// 注册
	public int reg(String uname, String upwd) throws Exception {
		boolean r = isUnameExists(uname);
		if (r == false) {
			String sql = "insert into puser(uname,upass) values(?,?)";
			List<Object> params = new ArrayList<Object>();
			params.add(uname);
 			upwd = Encrypt.md5(upwd); // 加密
			params.add(upwd);
			int result = db.doUpdate(sql, params);
			return result;
		} else {
			throw new RuntimeException("uname" + uname + "exists");
		}
	}

	//查询名字是否已经存在
	public boolean isUnameExists(String uname) throws Exception {
		String sql = "select * from puser where uname = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		List<Map<String, String>> list = db.select(sql, params);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	//登录查询
	public double login(String uname, String upwd) throws Exception {

		String sql = "select count(*) from puser where uname=? and upass= ?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		upwd = Encrypt.md5(upwd); // 加密
		System.out.println(upwd);
		params.add(upwd);
		double result = db.selectFunc(sql, params);
		if (result <= 0) {
			throw new RuntimeException("登录失败！请先注册。。。");
		}
		return result;
	}
	
	 //登录查询得的另一个方法
	public User loginl(String uname,String upwd) throws Exception {
		List list = db.select(User.class, "select * from puser where uname = ? or tel = ? and upass = ?",uname ,uname,Encrypt.md5(upwd));
		if(list != null && list.size() > 0 ){
			return  (User)list.get(0);
		}else{
			return null ;
		}
	   
	}  
	
	public static void main(String[] args) throws Exception {
		new UserBiz().loginl("a", "a");
	}
}
