package com.yc.biz.impl; 

import java.util.List;

import com.yc.bean.Admin;
import com.yc.model.DBHelper;
import com.yc.utils.Encrypt;

public class UserBizImpl {
	private DBHelper  db = new DBHelper() ;
	
	
	public List<Admin> login(Admin admin) throws Exception{
	 
		String sql = "select * from admin where username = ? and userpassword = ? ";
	/*	Map<String,String>  params = new HashMap<String, String>();
		params.put("uname", user.getUname());
		params.put("upass", user.getUpass());*/
		 
	   return  db.select(Admin.class, sql, admin.getUsername(),Encrypt.md5(  Encrypt.md5(  admin.getUserpassword()  )   )  );
	}

}
