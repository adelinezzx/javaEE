package com.yc.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBHelper {
	//这一句可以不要: 为什么?
	//因为使用的jndi技术，由tomcat创建数据库联接，所以这句可不要.
	//但如果将来要用  原来的方案联接数据库的话，建议留着，
    //但一定要确保项目的  web-inf/lib目录 有数据库驱动.
	static {
		try {
			//oracle.jdbc.OracleDriver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public int doUpdate(String sql,List<Object> params) throws Exception{
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
	
	public int doUpdate(String sql, Object...params) throws Exception{
		List<Object> pp= new ArrayList<Object>();
		if(params != null &&params.length > 0){
			for (Object o :params) {
				pp.add(o);
			}
		}
		return doUpdate(sql, pp);
	}
	
	
	/**
	 * @return
	 * @throws SQLException
	 * @throws NamingException 
	 */
	public Connection getCon() throws Exception{
		//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:YC","scott","a");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/adeline","root","a"); 	
		//Context initCtx = new InitialContext();   //初始化容器
		//Context envCtx = (Context) initCtx.lookup("java:comp/env");   //查找jndi的资源
		//DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");    // 从jndi中查  名字叫jdbc/orcl的资源,它是一个DataSource,所以类型转换
		//Connection conn = ds.getConnection();   //获取数据库联接
		return con;
	}
	
	/**
	 * @param rs
	 * @param pstmt
	 * @param con
	 */
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	
	public double selectFunc(String sql,List<Object> params) throws Exception{
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
	
	public double selectFunc(String sql, Object...params) throws Exception{
		List<Object> pp= new ArrayList<Object>();
		if(params != null &&params.length > 0){
			for (Object o : params) {
				pp.add(o);
			}
		}
		return selectFunc(sql, pp);
	}
	/**执行查操作询
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,String>> select(String sql,List<Object> params) throws Exception{
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Connection con=getCon();
		PreparedStatement stmt=con.prepareStatement(sql);
		if(params!=null && params.size()>0){
			for(int i=0;i<params.size();i++){
				stmt.setString(i+1, params.get(i).toString());
			}
		}
		ResultSet rs=stmt.executeQuery();
		//结果集元数据   列名   列类型 
		ResultSetMetaData rsmd=rs.getMetaData();	
		int cc=rsmd.getColumnCount();    //获取列数 -》pid pname price 
		List<String> columnNames=new ArrayList<String>();
		for(int i=0;i<cc;i++){
			String cn=rsmd.getColumnName(i+1);
			columnNames.add(cn);	//将得到的列名存入集合中
		}
		while(rs.next()){
			//每行当做一个Map来存
			Map<String,String> map=new HashMap<String,String>();
			//循环列名  取每列的值  map(列名，列名对应的值)
			for(int i=0;i<columnNames.size();i++){
				String cn=columnNames.get(i);	
				String value=rs.getString(cn);	
				map.put(cn, value);		
			}
			list.add(map);
		}
		closeAll(rs,stmt,con);
		return list;
	}
	/**
	 *  升级  执行查询操作    
	 * @param cls
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public <T>  List<T> select(Class<T> cls,String sql,Object...params) throws Exception{
		List<T> list =new ArrayList<T>() ;
		List<Object> pp= new ArrayList<Object>();
		if(params != null &&  params.length > 0){
			for (Object o : params) {
				pp.add(o);
			}
		}
		List<Map<String,String>> listMap= select(sql, pp);
		//将listMap转化为List<T>
		for (Map<String, String> map : listMap) {
			T t = parseRequest(map,cls);
			list.add(t);
		}
		return list;
	}
	
	/**
	 * 将Map<String, String>的值取出 ，存到泛型类Class<T> 中的生成的set方法中---
	 * 1.创建clz的对象 反射实例   2..用allSetMethods取出所有set方法   3.循环m 取出每个参数、4.遍历出取到的set方法中所有的方法
	 * 5.   判断当前所有set方法中的参数类型是哪一种，再进行类型转换
	 *   
	 * @param m    Map对象
	 * @param clz   类
	 * @return   泛型
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T parseRequest(Map<String, String> m, Class<T> clz)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 1.创建clz的对象 反射实例
		T obj = clz.newInstance(); // Product p = new Product() ;
		// 2.用allSetMethods取出所有set方法
		List<Method> allSetMethods = getAllSetMethod(clz);
		// 3.循环m 取出每个参数、
		for (Map.Entry<String, String> entry : m.entrySet()) {
			String parameterName = entry.getKey();// 参数名
			String parameterValue = entry.getValue();// 值

			// 4.取出allSetMethods中所有的方法
			for (Method method : allSetMethods) { // 遍历
				String methodName = method.getName(); // 得到每一个方法的方法名
				// 5.判断当前method中的参数类型是哪一种，载进行类型转换
				if (methodName.equalsIgnoreCase("set" + parameterName)) { // 判断  方法名=  set+参数  ？  如果等于则继续
					String parameterTypeName = method.getParameterTypes()[0].getName();// 得到方法的类型
					if ("double".equals(parameterTypeName) || "java.lang.Double".equals(parameterTypeName)) {
						double v = Double.parseDouble(parameterValue);
						method.invoke(obj, v);//调用setXXX（v）;
					} else if ("int".equals(parameterTypeName) || "java.lang.Integer".equals(parameterTypeName)) {
						int v = Integer.parseInt(parameterValue);
						method.invoke(obj, v);
					} else if ("float".equals(parameterTypeName) 	|| "java.lang.Float".equals(parameterTypeName)) {
						float v = Float.parseFloat(parameterValue);
						method.invoke(obj, v);
					} else {
						// 激活方法
						method.invoke(obj, parameterValue);
					}
				}
			}
		}
		return obj;
	}

	/**
	 * 得到一个类中的所有的以set开头的方法存到List中
	 * 
	 * @param clz
	 * @return
	 */
	private static List<Method> getAllSetMethod(Class clz) {
		List<Method> allSetMethod = new ArrayList<Method>();
		Method[] ms = clz.getMethods();// 得到所有的方法
		for (Method m : ms) {
			if (m.getName().startsWith("set")) {// 如果方法时以set开头的。
				allSetMethod.add(m);
			}
		}
		return allSetMethod;
	}
}
