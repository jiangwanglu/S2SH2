package com.pp.test.dao;


import java.sql.SQLException;
import java.util.List;

import com.pp.test.bo.MaintenancePlan;

public interface MaintainDao {
	
	
	void addMaintain(MaintenancePlan plan) throws Exception;
	List<String> querymaintain(String a,String b) throws Exception;
	List<MaintenancePlan> Display() throws Exception;
	//修改运行时间
	void Updatamainrun(MaintenancePlan l) throws SQLException;	
	//删除
	void exectiondelete(String id,String con)throws Exception;
	
	int judge(String a,String category);
}