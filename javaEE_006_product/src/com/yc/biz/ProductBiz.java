package com.yc.biz;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Product;
import com.yc.dao.DBHelper;
import com.yc.model.PageBean;

public class ProductBiz {

	private DBHelper db = new DBHelper();

	public int add(Product product) throws Exception{
		System.out.println(product.getPname()+ "----" + product.getPrice());
		return db.doUpdate("insert into product43(pid,pname,price) values(sql_product43.nextval,?,?) ", product.getPname(),product.getPrice() );
	}
	/**
	 * ��ѯ�����ݿ������ݵ��ܼ�¼����
	 * 
	 * @return
	 * @throws Exception
	 */
	private int findCount(Product product) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) from product43 where 1=1 ");
		List<Object> parmas = new ArrayList<Object>();
		if (product != null) {
			if (product.getPname() != null) {
				sb.append("  and pname like ?  " );
				parmas.add("%"+product.getPname() +"%");
			}
			if (product.getPid() != null) {
				sb.append("  and pid =?   ");
				parmas.add(product.getPname());
			}
			if (product.getPrice() != null) {
				sb.append("  and price =?   ");
				parmas.add(product.getPrice());
			}
		}
       String sql = sb.toString();
       System.out.println(   sql + "---"+ parmas );
		int r = (int) db.selectFunc(sql, parmas);
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
			String orderway, Product product) throws Exception {

		int start = (pages - 1) * pagesize + 1; //
		int end = pages * pagesize;
		// String sql =
		// " select * from (select a.* ,row_number() over(order by a.pid desc) rn  from product43 a  ) where rn>="+start+"and rn <="
		// + end;
		StringBuffer sb = new StringBuffer();
		sb.append("    select * from (  ");
		sb.append("    select a.* , rownum as rn from (  ");
		sb.append("  	 select  *   ");
		sb.append(" 	 from product43 where 1=1   ");

		List<Object> parmas = new ArrayList<Object>();
		
		if (product != null) {
			if (product.getPname() != null) {
				sb.append("  and pname like ?  " );
				parmas.add("%"+product.getPname() +"%");
			}
			if (product.getPid() != null) {
				sb.append("  and pid =?   ");
				parmas.add(product.getPname());
			}
			if (product.getPrice() != null) {
				sb.append("  and price =?   ");
				parmas.add(product.getPrice());
			}
		}

		if (ordercolumn != null && !" ".equals(ordercolumn)) {
			orderway = orderway == null ? "asc" : orderway;
			sb.append(" order by " + ordercolumn + "  " + orderway);
		}

		sb.append("    ) a where rownum <=  " + end);
		sb.append("    ) where rn >=" + start);

		String sql = sb.toString();
		System.out.println("sql���Ϊ��" + sql);
		return db.select(sql, parmas);
	}

	/**
	 * ��ҳ��ѯ
	 * 
	 * @throws Exception
	 */
	public PageBean findByPage(int pages, int pagesize, String ordercolumn,
			String orderway, Product product) throws Exception {

		int total = findCount(product);// �õ��ܵļ�¼����
		List list = findList(pages, pagesize, ordercolumn, orderway, product);

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
}
