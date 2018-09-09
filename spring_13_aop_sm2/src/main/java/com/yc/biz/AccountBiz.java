package com.yc.biz;

import org.springframework.stereotype.Repository;

import com.yc.bean.Account;
@Repository
public interface AccountBiz {

	// 存钱
	public void deposite(int accountid, double money);

	// 取钱
	public void withdraw(int accountid, double money);

	// 转账
	public void tansfer(int InAccountid, int outAccountid ,double money);

	// 查找账户
	public Account findAccount(int accountid);

	// 开户
	public Account openAccount(double money);

}
