package com.yc.biz.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.biz.AccountBiz;
import com.yc.dao.AccountDao;
import com.yc.dao.OpRecordDao;

/*@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)*/
public class AccountBizImpl implements AccountBiz {

	 @Resource(name ="accountDaoImpl") 
	private AccountDao accountDao;
	 @Autowired 
	private OpRecordDao opRecordDao;

	public AccountBizImpl() {
		super();
		System.out.println("构造方法。。。。");
	}

	@Override
	public Account findAccount(int accountid) {
		return accountDao.findAccount(accountid);
	}

	@Override
	public void deposite(int accountid, double money) {
		Account account = accountDao.findAccount(accountid);
		if (account == null) {
			throw new RuntimeException("查无此账户");
		}
		account.setAccountid(accountid);
		account.setBalance(money);
		accountDao.updateAccount(account);

		OpRecord oprecord = new OpRecord();
		oprecord.setAccountid(accountid);
		oprecord.setOpmoney(money);
		opRecordDao.insertOpRecord(oprecord);

	}

	@Override
	public void withdraw(int accountid, double money) {
		Account account = accountDao.findAccount(accountid);
		if (account == null) {
			throw new RuntimeException("查无此账户");
		}
		if (account.getBalance() <= money) {
			throw new RuntimeException("账户余额不足");
		}

		account.setAccountid(accountid);
		account.setBalance(-money);
		accountDao.updateAccount(account);

		OpRecord oprecord = new OpRecord();
		oprecord.setAccountid(accountid);
		oprecord.setOpmoney(-money);
		opRecordDao.insertOpRecord(oprecord);

	}

	@Override
	public void tansfer(int InAccountid, int outAccountid, double money) {
		deposite(InAccountid, money);
		withdraw(outAccountid, money);

	}

	@Override
	public Account openAccount(double money) {

		return null;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setOpRecordDao(OpRecordDao opRecordDao) {
		this.opRecordDao = opRecordDao;
	}

}
