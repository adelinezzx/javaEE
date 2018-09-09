package com.yc.biz.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bean.CartItem;
import com.yc.bean.Resfood;
import com.yc.bean.Resorder;
import com.yc.bean.Resorderitem;
import com.yc.bean.Resuser;
import com.yc.dao.DBUtil;

public class ResorderBizImpl {
	
   /**
    * 实现配送    ：  修改 订单的状态
    * @param resorder
    */
	public void transfer(Resorder resorder) {
		 String sql = "update  resorder set status=1 where roid =   " + resorder.getRoid()  ;
		 DBUtil.doUpdate(sql, null);
		
	}
	
	/**
	 * 根据  订单编号roid   查找 对应订单的详细信息
	 * @param resorder
	 * @return
	 */
	public List<Resorderitem> findResorderItem(Resorder resorder) {
		 String sql = "  select  roiid ,roid ,resorderitem.fid,fname,resorderitem.realprice,num,resorderitem.realprice*"+
	                          "   num as smallcount from resorderitem  "+
	                          "   left join resfood on  resorderitem.fid =resfood.fid where roid = " +  resorder.getRoid();
		return DBUtil.list(Resorderitem.class, sql, null);
	}
	
	/**
	 * 在后台查找订单
	 * @param resorder
	 * @return
	 */
	public List<Resorder> findResorder(Resorder resorder){
		String sql ="select  roid ,userid ,address ,tel,date_format(ordertime,'%Y-%c-%d %H:%i:%s') as ordertime," +
              " date_format(deliverytime,'%Y-%c-%d %H:%i:%s') as deliverytime,ps,status from resorder where 1=1 " ;
		List<Object> param = new ArrayList<Object>();
		if(resorder != null ){   //1.订单不为空
			if(resorder.getUserid() != null &&  !resorder.getUserid().equals("")){//2.用户已经登录  否则不查询 userID
				sql +="   and userid = ? ";
				param.add(resorder.getUserid()   );
			}
			
			if(resorder.getStatus() != null && !resorder.getStatus().equals("")){//3.订单的状态不为空 ，否者不查询 其订单状态
				sql += "  and status = ? ";
				param.add(resorder.getStatus()  );
			}
		}
		
		if(resorder != null && resorder.getOrderBy() != null ){//4.
			sql+="  order by " + resorder.getOrderBy() + "  " + resorder.getOrder()  + "  ";
		}
		if(resorder != null && resorder.getPages()  != null ){
			int start = (resorder.getPages() - 1) * resorder.getPageSize() ;
			
			sql+="  limit " + start + "  , " + resorder.getPageSize() + "  ";
		}
		
		return DBUtil.list(Resorder.class, sql, param.toArray()  ) ;//当查询不到  订单的状态，订单的提交用户id ，排序方式，则直接查询的最基本的信息
	}
	
	
	/**
	 * 查找后台订单数据的总条数
	 * @param resorder
	 * @return
	 */
	public Integer findCount(Resorder resorder){
		String sql = "select count(*) as total from resorder where 1=1 ";
		List<Object> params =  new ArrayList<Object>();
		if(resorder != null ){   //1.订单不为空
			if(resorder.getUserid() != null &&  !resorder.getUserid().equals("")){//2.用户已经登录  否则不查询 userID
				sql +="   and userid = ? ";
				params.add(resorder.getUserid()   );
			}
			
			if(resorder.getStatus() != null && !resorder.getStatus().equals("")){//3.订单的状态不为空 ，否者不查询 其订单状态
				sql += "and status = ? ";
				params.add(resorder.getStatus()  );
			}
		}
		
		int count = Integer.parseInt(   DBUtil .get( sql, params.toArray()).get("TOTAL").toString() ) ;
		 
		return count ;
	}
	
	public void makeorder(Resorder resorder ,Map<Integer,CartItem> cart ,Resuser resuser) throws SQLException{
		String sql = "insert into resorder(userid,address,tel,ordertime,deliverytime,ps,status)  values( ?,?,?,now(),date_add(now(),interval 1 hour ),?,0)";
		
		Connection conn= DBUtil.getConn();
		//事务处理
		
		try {
			conn.setAutoCommit(false);//关闭模式事务（一条sql语句自动提交一次）
			PreparedStatement  ps = conn.prepareStatement(sql);
			ps.setString(1, resuser.getUserid() + "");
			ps.setString(2, resorder.getAddress()+ "");
			ps.setString(3, resorder.getTel() + "");
			ps.setString(4, resorder.getPs() + "");
			
			ps.executeUpdate();
			
			sql = "select Max(roid)  as roid from resorder  ";
			ps =  conn.prepareStatement(sql);
			
			ResultSet result = ps.executeQuery();
			int roid = 0 ;
			if(result.next() ){
				roid = result.getInt(1);
			}
			//循环购物车
			for (Map.Entry<Integer,CartItem> entry : cart.entrySet()  ) {
				int fid = entry.getKey() ;
				CartItem ci = entry.getValue();
				Resfood resfood = ci.getResfood() ;
				int count  = ci.getCount() ;
				Double smallCount = ci.getSmallCount() ;
				sql="insert into resorderitem(roid,fid,realprice,num) values (?,?,?,?)";
				
				ps= conn.prepareStatement(sql);
				ps.setString(1, roid+"");
				ps.setString(2,fid+"");
				ps.setString(3, resfood.getRealprice()+"");
				ps.setString(4, count+"");
				ps.executeUpdate();
			}
			
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.setAutoCommit(true);
				conn.close();
			}
		}
	}


	 

	 

}
