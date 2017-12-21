package com.pp.test.dao;

import java.util.List;


import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.PatrolRecord;

public class PatrolRecordDaoImpl extends BaseDaoImpl<PatrolRecord,Integer> implements PatrolRecordDao {

	public PatrolRecord queryPatrolRecord(String unitId, String startdate,String index) {
		String ind = "1";
		String sql = "select * from patrolrecord where unitid = ? and date = ? and day = ? and type9 = ?";
		try {
			return (PatrolRecord) this.queryForObject(sql,new String[]{unitId,startdate,index,ind},PatrolRecord.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addRecord(PatrolRecord patrol) {
		try {
			this.save(patrol);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
