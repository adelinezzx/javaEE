package com.yc.dao;


import java.util.List;

import org.springframework.stereotype.Component;

import com.yc.bean.OpRecord;
@Component
public interface OpRecordDao {
	
	public   void  insertOpRecord(OpRecord  opRecord  );
	
	public List<OpRecord>   findAll() ;
	

}
