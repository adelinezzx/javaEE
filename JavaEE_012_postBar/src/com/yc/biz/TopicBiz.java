package com.yc.biz;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Topic;
import com.yc.model.DBHelper;
import com.yc.model.PageBean;

public class TopicBiz {

	private DBHelper db = new DBHelper();
	
    /**
     *  ���ݲ�Ʒ��  ��ѯ�Ƿ��Ѿ��������ݿ��� 
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
	 * ��ѯ�����ݿ������ݵ��ܼ�¼����
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
	 * ��ѯ��list��ÿһҳ�ļ�¼
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
		System.out.println("sql���Ϊ��" + sql); 
		return db.select(Topic.class , sql, null);
	}

	/**
	 * ��ҳ��ѯ
	 * 
	 * @throws Exception
	 */
	public PageBean findByPage(int pages, int pagesize, String ordercolumn,
			String orderway ) throws Exception {

		int total = findCount( );// �õ��ܵļ�¼����
		List list = findList(pages, pagesize, ordercolumn, orderway );

		PageBean pagebean = new PageBean();
		pagebean.setTotal(total);// �õ��ܵļ�¼������Ȼ�󸳸�pagebean����
		pagebean.setPages(pages);
		pagebean.setPagesize(pagesize);
		// ������ҳ�� ����
		int totalpages = total % pagesize == 0 ? total / pagesize : (total
				/ pagesize + 1);
		pagebean.setTotalpages(totalpages);// �Ѽ��������ҳ���浽pagebean������

		pagebean.setList(list);// ��ѯ��ÿһҳ�����ݡ�Ȼ�󸳸�pagebean����

		return pagebean; // ���ض���
	}
	
	/**
	 * ��������   post ��������
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
