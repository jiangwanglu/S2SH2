package com.pp.test.dao;

import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Centent;
import com.pp.test.bo.Inspection;

public class QueryCentenrDaoImpl extends BaseDaoImpl<Centent,Integer> implements QueryCentenrDao {

	public List<Centent> QueryCentenr(String name) throws Exception {
		String sql = "select * from content where name = ?";
		return (List<Centent>) this.queryForList(sql,new String[]{name}, Centent.class);
	}

	
	public void updatecentent(String name, String id, String arr)
			throws Exception {
		String sql = null;
		if(id != null&&id.equals("1")){
			sql = "update content set month=? where name=?";
		}else if(id != null&&id.equals("2")){
			sql = "update content set season=? where name=?";
		}else if(id != null&&id.equals("3")){
			sql = "update content set year=? where name=?";
		}
		this.executeUpdateForRow(sql,new String[]{arr,name});
	}


	
	public void addcontent(Centent s) throws Exception {
		String sql = "update content set whole = ? where name = ?";
		this.executeUpdateForRow(sql,new String[]{s.getWhole(),s.getName()});
	}

	
	
}