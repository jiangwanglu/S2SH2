package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.planned;

public interface plannedDao {
	List<planned> queryPlanned();
	
	void addPlanned(String id,String name,String b);
	
	void updatePlan(String id,String unid,String name);
	
	List<planned> queryData(String id);
	
	void updataData(String data1,String data2,String id);
}
