package com.yc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.dao.AccountDao;
import com.yc.dao.OpRecordDao;

@Repository
public class OpRecordDaoImpl extends SqlSessionDaoSupport implements OpRecordDao {

	@Resource(name = "sqlSession") // 因为 SqlSessionDaoSuppport这个父类需要
									// sqlsession,所以要注入
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate); // 调用父类的set方法完成注入
	}

	@Override
	public void insertOpRecord(OpRecord opRecord) {
		super.getSqlSession().insert("com.yc.dao.OpRecordDaoMapper.insertOpRecord",opRecord);

	}

	@Override
	public List<OpRecord> findAll() {
		return super.getSqlSession().selectList("com.yc.dao.OpRecordDaoMapper.findAll");
	}

}
