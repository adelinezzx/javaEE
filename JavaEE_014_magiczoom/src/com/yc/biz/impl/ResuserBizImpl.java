package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.dao.DBUtil;
import com.yc.utils.Encrypt;

public class ResuserBizImpl {
	
	/**
	 * 后台显示客户数据
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	public Map findResuserContribution(int page,int rows,String sort,String order ){
		
	     //int count = this.foundCount();
		String sql = "select * from (  select  resuser.userid  as userid  ,  username ,ifnull(   sum(realprice * num),0.00  ) as dealcount , email  from resuser" +
                             "  left join resorder   "+
                             "  on  resuser.userid =resorder.userid  "+
                             "   left join resorderitem  "+
                             "  on  resorder.roid = resorderitem.roid "+
                             " group by userid , username  "+
                             "   )  a  ";
		if( sort != null && order != null ){
			sql += "order by " + sort + "  " + order + "  " ;
		} 
		
		int start = (page-1) * rows ;
		sql += "limit  " + start + "," + rows ;
		
		List<Resuser> list = DBUtil.list(Resuser.class, sql,null);
		Map<String,Object> map = new HashMap<String, Object>();
	 
		map.put("total", list.size()  );
		map.put("rows", list);
		return map ;
	}
	
	/**
	 * 在后台查找客户的所有记录条数
	 * @param resuser
	 * @return
	 */
	public Integer findCount(){
		String sql = "select count(*) as total from resuser where 1=1 ";
		List<Object> params =  new ArrayList<Object>();
		 
		
		int count = Integer.parseInt(   DBUtil .get( sql, params.toArray()).get("TOTAL").toString() ) ;
		 
		return count ;
	}
	
	public List<Resuser> findAll(int page,int rows,String sort,String order){
		String sql = "select * from resuser where 1=1  " ;
		if( sort != null && order != null ){
			sql += " order by " + sort + "  " + order ;
		}
		
		int start = (page-1 ) * rows ;
		sql+="   limit " + start + " , " + rows ;
		
		 Resuser resuser = DBUtil.get(Resuser.class, sql);
		return  null;
	}
	
	/**
	 * 登录语句的操作
	 * @param user
	 * @return
	 */
	public Resuser login(Resuser user ){
		String sql = "select * from resuser where username = ? and  pwd = ?  ";
		List<Object> params = new  ArrayList<Object>() ;
		params.add(user.getUsername());
		params.add( Encrypt.md5(user.getPwd())    );//加密密码信息
		List<Resuser> list = DBUtil.list(Resuser.class, sql, params.toArray()  );//toArray  params的数据以数组的形式传入
		if(list == null || list.size()  <=  0 ){
			return null ;
		}
		return list.get(0);
	}

	 
}
