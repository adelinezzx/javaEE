package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Resadmin;
import com.yc.bean.Resuser;
import com.yc.dao.DBUtil;
import com.yc.utils.Encrypt;

public class ResadminBizImpl {
	
	/**
	 * 登录语句的操作
	 * @param user
	 * @return
	 */
	public Resadmin login(Resadmin admin ){
		String sql = "select * from resadmin where raname = ? and  rapwd = ?  ";
		List<Object> params = new  ArrayList<Object>() ;
		params.add(admin.getRaname());
		params.add( Encrypt.md5( admin.getRapwd()     )    );//加密密码信息
		List<Resadmin> list = DBUtil.list(Resadmin.class, sql, params.toArray()  );//toArray  params的数据以数组的形式传入
		if(list == null || list.size()  <=  0 ){
			return null ;
		}
		return list.get(0);
	}

	 
}
