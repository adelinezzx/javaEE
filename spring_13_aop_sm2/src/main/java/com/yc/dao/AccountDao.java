package com.yc.dao;

import org.springframework.stereotype.Component;

import com.yc.bean.Account;

@Component
public interface AccountDao {
	
	public  void  updateAccount(Account  account);
	
	public Account  findAccount(int accountid );

}
