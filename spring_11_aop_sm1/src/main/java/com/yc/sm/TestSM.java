package com.yc.sm;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.biz.AccountBiz;
import com.yc.dao.AccountDao;
import com.yc.dao.OpRecordDao;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "/bean.xml")
public class TestSM {

	@Resource(name = "dataSource")
	private BasicDataSource dataSource;

	@Resource(name = "sqlSessionFactory")
	private DefaultSqlSessionFactory sqlSessionFactory;

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Resource(name = "accountDaoImpl")
	private AccountDao accountDao;

	@Resource(name = "opRecordDaoImpl")
	private OpRecordDao opRecordDao;
	
	@Resource(name = "accountBizImpl")
	private AccountBiz  accountBiz;

	
	@Test
	public void demo1() {
		//accountBiz.deposite(2, 200);
		accountBiz.tansfer(1, 2, 620);
		
		
	}
	@Test
	public void Test6() throws SQLException {
		OpRecord op = new OpRecord();
		op.setAccountid(2);
		op.setOpmoney(1000.0);
		opRecordDao.insertOpRecord(op);
		System.out.println("ok");
	}

	@Test
	public void Test7() throws SQLException {

		List<OpRecord> list = opRecordDao.findAll();
		for (OpRecord opRecord : list) {
			System.out.println(opRecord.getId());
		}
		System.out.println("ok");
	}

	@Test
	public void Test4() throws SQLException {
		Account a = accountDao.findAccount(2);
		System.out.println(a);
	}

	@Test
	public void Test5() throws SQLException {
		Account a = accountDao.findAccount(2);
		a.setBalance(1000.0);
		accountDao.updateAccount(a);
		System.out.println("ok");
	}

	@Test
	public void TestDataSource() throws SQLException {
		System.out.println(dataSource.getConnection());
		dataSource.getConnection();
		Assert.assertNotNull(dataSource);
	}

	@Test
	public void Test2() throws SQLException {
		System.out.println(sqlSessionFactory);
	}

	@Test
	public void Test3() throws SQLException {
		System.out.println(sqlSession);
	}
}
