package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.Inspection;

public interface WaterPumpDao {
	List<Inspection> WaterPumpid(String b,String gps) throws Exception;
	
	//抓取巡检id
	List<String> WaterPumpgps(String date1, String date2,String gps) throws Exception;
}
