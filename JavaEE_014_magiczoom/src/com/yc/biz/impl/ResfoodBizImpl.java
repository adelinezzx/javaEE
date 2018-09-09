package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Resfood;
import com.yc.dao.DBUtil;

public class ResfoodBizImpl {

	/**
	 * 查找菜品的  详细信息
	 * @param resfood
	 * @return
	 */
	public List<Resfood> findResfoodSellCountList(Resfood resfood) {
		String sql = "select resfood.fid as fid ,fname ,ifnull(sum(num),0) as sellcount  from resfood "+
			             	" left join resorderitem  "+
			             	"  on resfood.fid =resorderitem.fid   "+
			             	"   group by resfood.fid,fname ";
		if(  resfood != null && resfood.getOrderBy() != null  && resfood.getOrder() != null ){
			sql += " order by " + resfood.getOrderBy() + "   " + resfood.getOrder() ;
		}
		
		if(resfood != null && resfood.getPages() != null && resfood.getPageSize() != null ){
			 
			int pages = resfood.getPages() ;
			int pagesize = resfood.getPageSize() ;
			int start =  (pages - 1)*pagesize ;
			sql += " limit " + start + "," + pagesize ;
		}
		 
		return DBUtil.list(Resfood.class, sql, null);
	}

	public long findResfoodCount(Resfood resfood) {
		String  sql = "select  count(*)  as num from resfood where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if(resfood != null ){
			//TODO以后加入的查询条件
			
		}
		Object num =DBUtil.get(sql, params.toArray()).get("NUM");
		if(num != null ){
			return Long.parseLong(num.toString());
		}else{
			return 0L;
		}
	}

	public int  updateResfoodInfo(Resfood resfood) {
		 String sql = "update resfood  set fname = ?,normprice=?,realprice = ?, detail= ? ,fphoto = ?  where fid = ?";
		 return	DBUtil.doUpdate(sql, resfood.getFname(),resfood.getNormprice(),resfood.getRealprice(),resfood.getDetail(),resfood.getFphoto(),resfood.getFid());
		
	}

	public List<Resfood> findResfood(Resfood resfood) {
		String sql = "select * from resfood where 1= 1 " ;
		List<Object> params = new ArrayList<Object>();
		if(resfood != null ){
			//加入以后的查询条件 两个查询条件必须一致
		}
		if(resfood != null && resfood.getOrder() != null && resfood.getOrderBy() != null ){
			sql += " order by " + resfood.getOrderBy() + "  " + resfood.getOrder() ;
		}
		
		if(resfood != null && resfood.getPages() !=null && resfood.getPageSize() != null){
			int pages = resfood.getPages() ;
			int pageSize = resfood.getPageSize() ;
			int start = (pages  -1 )* pageSize ;
			sql +="  limit ?, ? ";
			params.add(start);
			params.add(pageSize);
		}
		return DBUtil.list(Resfood.class, sql, params.toArray());
	}
	
 

}
