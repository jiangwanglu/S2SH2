package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.Patrol;

public interface PatrolkDao {
	void patrolk(String name,String i) throws Exception;
	
	List<Patrol> plank() throws Exception;
	
	List<String>CatalogData() throws Exception;
}