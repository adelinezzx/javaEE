package com.yc.biz;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Topic;
import com.yc.model.DBHelper;
import com.yc.model.PageBean;

public class TopicBiz {

	private DBHelper db = new DBHelper();
	
    /**
     *  根据产品名  查询是否已经存在数据库中 
     * @param pname
     * @return
     * @throws Exception
     */
	public  Topic find(String pname) throws Exception{
		String sql = "select * from puser where pname = ?" ;
		List<Topic> list = db.select(Topic.class, sql, pname);
		return list != null && list.size()>0  ? list.get(0) : null;
	}
	
	 
	/**
	 * 查询到数据库中数据的总记录条数
	 * 
	 * @return
	 * @throws Exception
	 */
	private int findCount() throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) from topic where 1=1 ");
		List<Object> parmas = new ArrayList<Object>();
		int r = (int) db.selectFunc(sb.toString(), parmas);
		return r;
	}

	/**
	 * 查询到list中每一页的记录
	 * 
	 * @param pages
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	private List findList(int pages, int pagesize, String ordercolumn,
			String orderway ) throws Exception {

		int start = (pages - 1) * pagesize  ; //
		 
		StringBuffer sb = new StringBuffer();
		sb.append("    select  tid ,contents ,publishtime,pic,topic.uid,  uname from  topic  inner join puser    on topic.uid = puser.uid ");
		 
		if (ordercolumn != null && !" ".equals(ordercolumn)) {
			orderway = orderway == null ? "asc" : orderway;
			sb.append(" order by  " + ordercolumn + "  " + orderway + ",tid  desc  ");
		}

		sb.append("   limit  " + start  + " , "+ pagesize);

		String sql = sb.toString();
		System.out.println("sql语句为：" + sql); 
		return db.select(Topic.class , sql, null);
	}

	/**
	 * 分页查询
	 * 
	 * @throws Exception
	 */
	public PageBean findByPage(int pages, int pagesize, String ordercolumn,
			String orderway ) throws Exception {

		int total = findCount( );// 得到总的记录条数
		List list = findList(pages, pagesize, ordercolumn, orderway );

		PageBean pagebean = new PageBean();
		pagebean.setTotal(total);// 得到总的记录条数。然后赋给pagebean对象
		pagebean.setPages(pages);
		pagebean.setPagesize(pagesize);
		// 计算总页数 求余
		int totalpages = total % pagesize == 0 ? total / pagesize : (total
				/ pagesize + 1);
		pagebean.setTotalpages(totalpages);// 把计算出的总页数存到pagebean对象中

		pagebean.setList(list);// 查询到每一页的数据。然后赋给pagebean对象

		return pagebean; // 返回对象
	}
	
	/**
	 * 更新数据   post 插入数据
	 * @param product
	 * @return
	 * @throws Exception
	 */
	public int add(Topic topic) throws Exception{
		return  db.doUpdate("insert into topic(contents ,publishtime,pic,uid) values(?,now() ,?,?)", topic.getContents(),topic.getPic(),topic.getUid());
	}
	
	public static void main(String[] args) throws Exception {
		new TopicBiz().findByPage(1, 5, "publishtime", "desc");
	}
}
