package com.yc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 帮助访问数据库
 */
public class DBHelper {
	//因为静态块在整个程序运行时,只运行一次,而且是在程序加载到虚拟机的时候
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行增删改
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public int doUpdate(String sql,List<Object> params) throws SQLException{
		Connection con=getCon();
		PreparedStatement stmt=con.prepareStatement(sql);
		if(params!=null && params.size()>0){
			for(int i=0;i<params.size();i++){
				stmt.setString(i+1, params.get(i).toString());
			}
		}
		int r=stmt.executeUpdate();
		closeAll(null,stmt,con);
		return r;
	}
	/**
	 * 获取与数据库的链接
	 * @return
	 * @throws SQLException
	 */
	public Connection getCon() throws SQLException{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","study40","a");
		return con;
	}
	
	/**
	 * 关闭与数据库的链接
	 * @param rs
	 * @param pstmt
	 * @param con
	 */
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 统计查询
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	
	public double selectFunc(String sql,List<Object> params) throws SQLException{
		double result=0;
		Connection con=getCon();
		PreparedStatement stmt=con.prepareStatement(sql);
		if(params!=null && params.size()>0){
			for(int i=0;i<params.size();i++){
				stmt.setString(i+1, params.get(i).toString());
			}
		}
		ResultSet rs=stmt.executeQuery();
		if(rs.next()){
			result=rs.getDouble(1);
		}
		closeAll(rs,stmt,con);
		return result;
	}
	
	/**
	 * 通用的查询,查询的结果为    List<Map<String,String>>   <br/>
	 * Map<String,String>对应的一条数据	<br/>
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,String>> select(String sql,List<Object> params) throws SQLException{
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Connection con=getCon();
		PreparedStatement stmt=con.prepareStatement(sql);
		if(params!=null && params.size()>0){
			for(int i=0;i<params.size();i++){
				stmt.setString(i+1, params.get(i).toString());
			}
		}
		ResultSet rs=stmt.executeQuery();
		ResultSetMetaData rsmd=rs.getMetaData();	//通过元数据来知道有多少列
		int cc=rsmd.getColumnCount();
		List<String> columnNames=new ArrayList<String>();
		for(int i=0;i<cc;i++){
			String cn=rsmd.getColumnName(i+1);
			columnNames.add(cn);	//存好列名
		}
		//循环所有的结果集记录
		while(rs.next()){
			//每一条数据就是一个Map对象
			Map<String,String> map=new HashMap<String,String>();
			//循环每条记录所有的列名
			for(int i=0;i<columnNames.size();i++){
				String cn=columnNames.get(i);	//列名
				String value=rs.getString(cn);	//根据列名取值
				map.put(cn, value);		//存到map中, 键(列名)   值(值)
			}
			list.add(map);
		}
		closeAll(rs,stmt,con);
		return list;
	}
}
