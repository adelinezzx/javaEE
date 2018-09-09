package com.yc.biz;

import java.util.List;

import com.yc.model.DBHelper;
import com.yc.model.PageBean;

public class BaseBiz {
	
	protected DBHelper db = new DBHelper();
	
	/**
	 * 查询总记录条数
	 * @param sqlcount
	 * @param sqlcountParamter
	 * @return
	 * @throws Exception
	 */
	public  int findCount(String sqlcount , List<Object> sqlcountParamter) throws Exception{
		int result = (int) db.selectFunc(sqlcount, sqlcountParamter);
		return result ;
	}

	/**
	 * 查询当前页 记录的集合
	 * @param sqllist
	 * @param sqlcountParamter
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public List findList(String sqllist ,List<Object> sqlcountParamter , Class  cls) throws Exception{
		if(sqlcountParamter == null || sqlcountParamter.size()  <= 0 ){
			return db.select(cls ,  sqllist );
		}else{
			return db.select( cls,sqllist, sqlcountParamter );
		}
		 
	}
	/**
	 * 分页查询
	 * @param sqlcount
	 * @param sqlcountParamter
	 * @param sqllist
	 * @param sqllistParamter
	 * @param pages  当前页
	 * @param pagesize   每页的大小
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public PageBean findByPage(String sqlcount ,List<Object> sqlcountParamter , String sqllist,List<Object> sqllistParamter, int pages,int pagesize ,Class  cls) throws Exception{
		int total = findCount(sqlcount, sqlcountParamter);
		List list = findList(sqllist, sqllistParamter, cls);
		PageBean pageBean = new PageBean() ;
		pageBean.setTotal(total);
		pageBean.setPages(pages);
		pageBean.setPagesize(pagesize);
		int totalpages = total% pagesize == 0? total /pagesize :(total / pagesize + 1 )	;
		pageBean.setTotalpages(totalpages);
		pageBean.setList(list);
		return pageBean ;
	}
}
