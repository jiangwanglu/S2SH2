package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.Inspection;

public interface AirRecordDao {
	//抓取id
	List<String> Patrolkt(String date1,String date2,String kt,String id) throws Exception;
	
	//通过id抓取到对应的数据
	List<Inspection> Patrolktdata(String b,String id) throws Exception;
}
