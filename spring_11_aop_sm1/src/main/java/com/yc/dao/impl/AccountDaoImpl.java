package com.yc.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.yc.bean.Account;
import com.yc.dao.AccountDao;

@Repository
public class AccountDaoImpl extends SqlSessionDaoSupport implements AccountDao {

	@Resource(name = "sqlSession") // 因为 SqlSessionDaoSuppport这个父类需要
									// sqlsession,所以要注入
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate); // 调用父类的set方法完成注入
	}

	@Override
	public void updateAccount(Account account) {
		super.getSqlSession().update("com.yc.dao.AccountDaoMapper.updateAccount",account);
	}

	@Override
	public Account findAccount(int accountid) {
		return super.getSqlSession().selectOne("com.yc.dao.AccountDaoMapper.findAccount", accountid);
	}

}
