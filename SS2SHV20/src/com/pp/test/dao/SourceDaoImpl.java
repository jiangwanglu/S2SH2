package com.pp.test.dao;

import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.Source;
import com.pp.test.bo.plantype;

public class SourceDaoImpl extends BaseDaoImpl<Source,Integer> implements SourceDao {
	public List<String> MainName() throws Exception {
		String Sql = "select belong from Plancontent group by belong";
		return (List<String>)this.queryForList(Sql, String.class);
	}

	public List<Source> MainDeviceName(String name) throws Exception {
		String Sql = "Select name from equipment where belong = ?";
		return (List<Source>) this.queryForList(Sql,new String[]{name}, Source.class);
	}

	public List<Source> QuerySource(String name) throws Exception {
		String sql="select * from equipment";
		return (List<Source>) this.queryForList(sql, Source.class);
	}
	public String NameQueryId(String a) throws Exception{
		String sql = "select id from equipment where name = ?";
		return (String) this.queryForObject(sql,new String[]{a}, String.class);
	}

	public String queyrcent(String name) throws Exception {
		String sql = "select unitId from equipment where id = ?";
				
		return (String) this.queryForObject(sql, new String[]{name}, String.class);
	}

	
	public String querySourceName(String id) {
		String sql = "select name from equipment where id = ?";
		try {
			return (String) this.queryForObject(sql, new String[]{id}, String.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return sql;
	}

	
	public String querysheb(String id) {
		String sql = "select belong from equipment where id = ?";
		try {
			return (String)this.queryForObject(sql, new String[]{id}, String.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sql;

	}

	public List<String> query2Type(String name) {
		System.out.println("----"+name);
		String sql = "select belong from equipment where unitId LIKE '"+name+"%' group by belong";
		try {
			return (List<String>) this.queryForList(sql,String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public List<plantype> query1name(String name) {
		String sql = "select * from plantype where unitid LIKE '"+name+"%'";
		try {
			return (List<plantype>) this.queryForList(sql,plantype.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String queryinspectionvalue(String unitid,String date, String str) {
		String sql = "select ? from  inspection where unitid = ? and date = ? ";
		return sql;
		
	}
}