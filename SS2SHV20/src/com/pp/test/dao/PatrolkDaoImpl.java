package com.pp.test.dao;

import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Patrol;

public class PatrolkDaoImpl extends BaseDaoImpl<Patrol,Integer>  implements PatrolkDao{
	public void patrolk(String name,String i) throws Exception {
		String sql = "update patrol set name = ? where date = ?";
		this.executeUpdateForRow(sql, new String[]{name,i});
	}

	public List<Patrol> plank() throws Exception {
		String sql = "select * from patrol";
		return (List<Patrol>) this.queryForList(sql,Patrol.class);
	}

	
	public List<String> CatalogData() throws Exception {
		String sql = "select name from equipment where maintenanceRecord = 1";
		return (List<String>)this.queryForList(sql, String.class);
	}
	
}