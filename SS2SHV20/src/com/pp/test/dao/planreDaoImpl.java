package com.pp.test.dao;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.planre;


public class planreDaoImpl extends BaseDaoImpl<planre,Integer> implements planreDao {
	public String queryCatalog(String name) {
		String sql = "select value from planre where unitid = ?";
		try {
			return this.queryForString(sql,new String[]{name});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sql;
	}
}