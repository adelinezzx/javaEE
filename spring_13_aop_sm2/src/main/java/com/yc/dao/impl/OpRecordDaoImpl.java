package com.yc.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.yc.bean.OpRecord;
import com.yc.dao.OpRecordDao;

public class OpRecordDaoImpl extends SqlSessionDaoSupport implements OpRecordDao {


	@Override
	public void insertOpRecord(OpRecord opRecord) {
		super.getSqlSession().insert("com.yc.dao.OpRecordDaoMapper.insertOpRecord",opRecord);

	}

	@Override
	public List<OpRecord> findAll() {
		return super.getSqlSession().selectList("com.yc.dao.OpRecordDaoMapper.findAll");
	}

}
