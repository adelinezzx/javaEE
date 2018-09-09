package com.zzx.j2ee.util;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zzx.j2ee.entity.User;

public class DBHelp {
	private static final Logger LOG = Logger.getLogger(DBHelp.class);
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

	// 关闭连接
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
	public static boolean doUpdate(String sql,Object...params) {
		Connection con = null;
		Statement st = null;
		try {
			con = DBHelp.getConn();
			// 3.创建sql平台
			st = con.createStatement();
			// LOG.error("创建sql品台成功。。。");
			// 4.执行sql语句
			int rows = st.executeUpdate(sql);
			// 返回受影响的行数（数据库操作的表中。有几条记录受到的影响）
			return rows > 0;
		} catch (SQLException e) {
			LOG.error("update数据失败！！！", e);
		} finally {
			// 5.关闭平台
			DBHelp.close(con, st, null);
		}
		return false;
	}

	public static <T> T get(Class<T> clazz, String sql) { // 泛型
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		T t = null;
		try {
			con = DBHelp.getConn();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				t = clazz.newInstance();// 通过反射创建对象
				Field[] fs = clazz.getDeclaredFields();   //取到类中的对象
				for (Field f : fs) {    //遍历对象
						System.out.println(f.getName() + "   "  // 取到属性名
								+ rs.getObject(f.getName())); // 取到属性名对应的数据类型   
                        //Object：代表所有的类型
						f.setAccessible(true);
						// f.set(t, rs.getObject(f.getName())); //给指定的属性赋值
						String fieldName = f.getType().getName();
					try {
						if (fieldName.intern() == Integer.class.getName()
								|| fieldName.intern() == int.class.getName().intern()) {
							f.set(t, rs.getInt(f.getName())); // Integer int
						} else if (fieldName.intern() == Double.class.getName()
								|| fieldName.intern() == double.class.getName()
										.intern()) {
							f.set(t, rs.getDouble(f.getName())); // Double
						} else if (fieldName.intern() == String.class.getName()) {
							f.set(t, rs.getString(f.getName())); // String
						} else {
							f.set(t, rs.getObject(f.getName()));// others
						}
					} catch (Exception e) {
						LOG.error("取值失败！！！", e);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("执行操作失败！！！", e);
		} finally {
			// 5.关闭平台
			DBHelp.close(con, st, rs);
		}
		return t;
	}

	public static List<User> list(String sql) {
		List<User> users = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBHelp.getConn();// 3.创建sql平台
			st = con.createStatement();// 4.执行sql语句
			rs = st.executeQuery(sql); // 执行sql语句 Query：结果集
			users = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				users.add(user);
			}
		} catch (SQLException e) {
			LOG.error("连接数据库失败！！！");
		} finally {
			// 5.关闭平台
			DBHelp.close(con, st, rs);
		}
		return users;
	}
}
