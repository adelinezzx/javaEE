package com.yc.biz.impl;

import com.yc.bean.User;
import com.yc.dao.DBUtil;
import com.yc.utils.Encrypt;

public class UserBizImpl {
	/**
	 * 
	 * @param user:  uname ,upass
	 * @return  user:  所有的值都有     不成功则为null
	 */
	public User login( User user) {
		if(  user.getUname()==null ||"".equals(user.getUname())) {
			throw new RuntimeException("user name should not be empty");
		}
		String pwd=Encrypt.md5(  Encrypt.md5(user.getUpass()) );
		return DBUtil.get(User.class, "select * from tbl_user where uname=? and upass=?", user.getUname(),pwd);
	}
	
	
	public int reg( User user){
		if(   isUnameExists( user.getUname())!=null){
			throw new RuntimeException( user.getUname()+"已经存在，请更换注册名....");
		}
		String sql="insert into tbl_user(uname,upass,head,regtime,gender) values(?,?,?,now(),?)";
		String pwd=Encrypt.md5(Encrypt.md5(user.getUpass()));
		return DBUtil.doUpdate(sql, user.getUname(),pwd,user.getHead(),user.getGender());
	}
	
	public User isUnameExists( String uname){
		String sql="select * from tbl_user where uname=?";
		return DBUtil.get(User.class, sql, uname);
	}
}
