package com.yc.dao.impl;


import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.yc.bean.Account;
import com.yc.dao.AccountDao;

 
public class AccountDaoImpl extends SqlSessionDaoSupport implements AccountDao {


	@Override
	public void updateAccount(Account account) {
		super.getSqlSession().update("com.yc.dao.AccountDaoMapper.updateAccount",account);
	}

	@Override
	public Account findAccount(int accountid) {
		return super.getSqlSession().selectOne("com.yc.dao.AccountDaoMapper.findAccount", accountid);
	}

}
