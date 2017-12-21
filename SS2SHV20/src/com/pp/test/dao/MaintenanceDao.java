package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.Maintenance;

public interface MaintenanceDao {
	List<Maintenance> Query2(String id) throws Exception;
	
	List<Maintenance> queryzhiding(String id,String con) throws Exception;
	
	void addMaintenance(Maintenance main);
	String querymax(String id,String s) throws Exception;
	
	List<String> querymain() throws Exception;
	
	String QueryCentent2(String name,String id);

	void updataContent2(String name, String data, String value);
	
	Maintenance QueryMain(String date,String month);
	
	void queryMaintenanceUpdate(String date);
	
	void queryMaintenanceUpdate1(String date);
	
	void deleteMaintenance(String name,String id);
	
	List<Maintenance> Query3(String id) throws Exception;
}
