package com.pp.test.dao;

import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.Plancontent;

public class PlancontentDaoImpl extends BaseDaoImpl<Plancontent,Integer> implements PlancontentDao {
	public List<String> planName() throws Exception {//1
		String sql = "select belong from Plancontent group by belong";
		return (List<String>)this.queryForList(sql, String.class);
	}
	
	public List<Plancontent> planName2(String name1) throws Exception {//2
		String sql = "select * from Plancontent where belong = ?";
		return (List<Plancontent>)this.queryForList(sql, new String[]{name1},Plancontent.class);
	}
	
	public List<String> planName3(String name2) throws Exception {//3
		String sql = "select name from equipment where unitid = ?";
		return (List<String>)this.queryForList(sql,new String[]{name2}, String.class);
	}

	public List<String> planName4(String name) throws Exception {
		String sql = "select units from plancontent where unitid = ?";
		return (List<String>)this.queryForList(sql,new String[]{name}, String.class);
	}
}