package com.yc.biz;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.PageBean;
import com.yc.bean.Resfood;
import com.yc.dao.DBUtil;

public class ResfoodBizImpl {
	public List<Resfood> findResfood(Resfood resfood) throws Exception {
		String sql = " select * from resfood where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (resfood != null) {
			// TODO: 以后加入查询的条件   两个查询的条件必须完成一致,  
		}
		if (resfood != null && resfood.getOrderBy() != null && resfood.getOrder() != null) {
			sql += " order by " + resfood.getOrderBy() + " " + resfood.getOrder(); // order
																					// by
																					// realprice
																					// desc
		}
		if (resfood != null && resfood.getPages() != null && resfood.getPageSize() != null) {
			int pages = resfood.getPages();
			int pageSize = resfood.getPageSize();
			int start = (pages - 1) * pageSize;
			sql += " limit ?,? ";
			params.add(start);
			params.add(pageSize);
		}
		return DBUtil.list(Resfood.class, sql, params.toArray());
	}

	public Long findResfoodCount(Resfood resfood) throws Exception {
		String sql = "select count(*) as num from resfood  where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (resfood != null) {
			// TODO: 以后加入查询的条件
		}
		Object num= DBUtil.get(sql, params.toArray()).get("NUM");
		if( num!=null) {
			return Long.parseLong(    num.toString());
		}else {
			return 0L;
		}
	}
	//                               
	public PageBean findResfoodByPage(Resfood resfood) throws Exception {
		long total = findResfoodCount(resfood);
		List<Resfood> allResfoodList = findResfood(resfood);
		PageBean pageBean=new PageBean();
		pageBean.setList(allResfoodList);
		pageBean.setPages(resfood.getPages());
		pageBean.setPagesize(resfood.getPageSize());
		pageBean.setTotal(total);
		long totalPage= total%resfood.getPageSize()==0?total/resfood.getPageSize():total/resfood.getPageSize()+1;
		pageBean.setTotalPage(  totalPage  );
		return pageBean;
	}
}
