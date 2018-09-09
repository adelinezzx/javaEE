package com.yc.mysql;

import java.sql.SQLException;
import java.util.*;

import com.yc.utils.Encrypt;

public class UserBiz {
	private static DBHelper db = new DBHelper();

	// ע��
	public int reg(String uname, String upwd) throws Exception {
		boolean r = isUnameExists(uname);
		if (r == false) {
			String sql = "insert into jspuser(id,uname,upwd) values(jspuser_seq.nextval,?,?)";
			List<Object> params = new ArrayList<Object>();
			params.add(uname);
//			upwd = Encrypt.md5(upwd); // ����
			params.add(upwd);
			int result = db.doUpdate(sql, params);
			return result;
		} else {
			throw new RuntimeException("uname" + uname + "exists");
		}
	}

	//����û����Ƿ���ڣ���������������ע��Ĳ���
	public boolean isUnameExists(String uname) throws Exception {
		String sql = "select * from jspuser where uname = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		List<Map<String, String>> list = db.select(sql, params);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	// ��֤��¼����
	public double login(String uname, String upwd) throws Exception {

		String sql = "select count(*) from jspuser where uname=? and upwd= ?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		params.add(upwd);
		double result = db.selectFunc(sql, params);
		if (result <= 0) {
			throw new RuntimeException("��¼�����㻹û��ע�ᣡ");
		}
		return result;

	}
}
