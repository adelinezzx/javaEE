package com.zzx.j2ee.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DBUtil {
	private static final Logger LOG = Logger.getLogger(DBUtil.class);
	private static final String DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USERNAME = "study40";
	private static final String PASSWORD = "a";

	// 加载驱动
	static {
		try {
			Class.forName(DRIVER_CLASS_NAME); // 加载驱动
		} catch (ClassNotFoundException e) {
			LOG.error("加载数据库驱动失败。。。", e);
		}
	}

	// 连接数据库
	public static Connection getConn() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 连接数据库
		} catch (SQLException e) {
			LOG.error("加载数据库失败！！！", e);
		}
		return con;
	}

	/**
	 * 关闭操作
	 * 
	 * @param con
	 *            关闭连接
	 * @param st
	 *            关闭平台
	 * @param rs
	 *            关闭执行操作
	 */
	public static void close(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			LOG.error("关闭数据库失败！！！");
		}
	}

	// insert , update , delete 操作的处理
	public static int doUpdate(String sql, Object... param) {// 三个点表示可以没有参数，也可以是一个参数或者多个参数
		Connection con = null;
		PreparedStatement ps = null; // 执行工具
		try {
			con = DBUtil.getConn(); // 获得连接
			// intse into users(id ,username,password)
			// values(?,?,?),每一个问号表示一个参数,称为占位符
			ps = con.prepareStatement(sql); // 创建预处理sql语句工具
			setParameterValue(ps, param); // 给占位符注值
			return ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error("update数据失败！！！", e);
		} finally {
			DBUtil.close(con, ps, null);
		}
		return 0;
	}

	/**
	 * 给预处理工具对象的占位符注值
	 */
	private static void setParameterValue(PreparedStatement ps,
			Object... params) {
		if (params == null || params.length == 0) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			try {
				if (params[i] instanceof Integer) {
					ps.setInt(i + 1, (int) params[i]); // 第1个参数表示第几个问号，第2个参数表示要替换占位符的值

				} else if (params[i] instanceof Double) {
					ps.setDouble(i + 1, (double) params[i]); // 第1个参数表示第几个问号，第2个参数表示要替换占位符的值

				} else if (params[i] instanceof String) {
					ps.setString(i + 1, (String) params[i]); // 第1个参数表示第几个问号，第2个参数表示要替换占位符的值

				} else if (params[i] instanceof Date) {
					ps.setDate(i + 1,
							new java.sql.Date(((Date) params[i]).getTime()));

				} else if (params[i] instanceof java.sql.Date) {
					ps.setDate(i + 1, (java.sql.Date) params[i]); // 第1个参数表示第几个问号，第2个参数表示要替换占位符的值
				} else {
					ps.setObject(i + 1, params[i]);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 做聚合查询操作 count() .avd()/ max() /min() /sum()
	 * 
	 * @param sql
	 *            聚合查询sql语句
	 * @param params
	 *            查询sql语句对应的参数数据
	 * @return 统计总数
	 */
	public static int doAggregation(String sql, Object... params) {
		Connection con = null;
		PreparedStatement ps = null; // 执行工具
		ResultSet rs = null;

		try {
			con = DBUtil.getConn(); // 获得连接
			ps = con.prepareStatement(sql); // 创建预处理sql语句工具
			setParameterValue(ps, params); // 给占位符注值
			rs = ps.executeQuery(sql);// 执行sql语句，返回结果集
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error("查询数据失败！！！", e);
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return 0;
	}

	/**
	 * 查询的操作 select clazz: 返回类型的类对象 params : 查询sql语句对应的参数数据 sql ： 查询sql语句 T 对象
	 */
	public static <T> List<T> dolist(Class<T> clazz, String sql,
			Object... params) {
		Connection con = null;
		PreparedStatement ps = null; // 执行工具
		ResultSet rs = null;
		List<T> list = null;

		try {
			con = DBUtil.getConn(); // 获得连接
			ps = con.prepareStatement(sql); // 创建预处理sql语句工具
			setParameterValue(ps, params); // 给占位符注值
			rs = ps.executeQuery();// 执行sql语句，返回结果集
			Field[] fs = clazz.getDeclaredFields(); // 取到返回要返回类型对象的所有属性对象
			list = new ArrayList<T>();
			while (rs.next()) {
				try {
					T t = clazz.newInstance();
					for (Field f : fs) {
						try {
							String typeName = f.getType().getName(); // 取到属性的类型名：int
																		// /Integer
																		// /String
							Object value = null;
							if (typeName.intern() == "java.lang.Integer"
									|| typeName.intern() == "int") {
								value = rs.getInt(f.getName());
							} else if (typeName.intern() == "java.lang.Double"
									|| typeName.intern() == "double") {
								value = rs.getDouble(f.getName());
							} else if (typeName.intern() == "java.lang.String") {
								value = rs.getString(f.getName());
							} else if (typeName.intern() == "java.sql.Date") {
								value = rs.getDate(f.getName());
							} else if (typeName.intern() == "java.util.Date") {
								value = new Date(rs.getDate(f.getName())
										.getTime());
							} else {
								value = rs.getObject(f.getName());
							}
							f.setAccessible(true); // 可以操作私有private属性
							f.set(t, value);
						} catch (Exception e) {
						}
					}
					list.add(t);
				} catch (Exception e) {
				}
			}
			return list;
		} catch (Exception e) {
			LOG.error("查询数据失败！！！", e);
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return null;
	}

	public static <T> T get(Class<T> clazz, String sql, Object... params) {
		Connection con = null;
		PreparedStatement ps = null; // 执行工具
		ResultSet rs = null;
		T t = null ;
		try {
			con = getConn(); // 获得连接
			ps = con.prepareStatement(sql); // 创建预处理sql语句工具
			setParameterValue(ps, params); // 给占位符注值
			rs = ps.executeQuery();// 执行sql语句，返回结果集
			Field[] fs = clazz.getDeclaredFields(); // 取到返回要返回类型对象的所有属性对象
			if (rs.next()) {
				try {
					 t = clazz.newInstance();
					for (Field f : fs) {
						try {
							String typeName = f.getType().getName();
							// 取到属性的类型名：int /Integer /String
							Object value = null;
							if (typeName.intern() == "java.lang.Integer"
									|| typeName.intern() == "int") {
								value = rs.getInt(f.getName());
							} else if (typeName.intern() == "java.lang.Double"
									|| typeName.intern() == "double") {
								value = rs.getDouble(f.getName());
							} else if (typeName.intern() == "java.lang.String") {
								value = rs.getString(f.getName());
							} else if (typeName.intern() == "java.sql.Date") {
								value = rs.getDate(f.getName());
							} else if (typeName.intern() == "java.util.Date") {
								value = new Date(rs.getDate(f.getName())
										.getTime());
							} else {
								value = rs.getObject(f.getName());
							}
							f.setAccessible(true); // 可以操作私有private属性
							f.set(t, value);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return t;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			LOG.error("查询数据失败！！！", e);
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return null;
	}
}
