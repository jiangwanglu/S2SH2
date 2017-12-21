package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.PatrolRecord;

public interface PatrolRecordDao {
	PatrolRecord queryPatrolRecord(String unitId,String startdate,String index);

	void addRecord(PatrolRecord patrol);
}
