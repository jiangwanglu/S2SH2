package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.Plancontent;

public interface PlancontentDao {
	List<String> planName() throws Exception;
	List<Plancontent> planName2(String name1) throws Exception;
	List<String> planName3(String name2) throws Exception;
	List<String> planName4(String name) throws Exception;
}
