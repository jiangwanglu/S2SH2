package com.pp.test.dao;

import java.util.List;
import java.util.ArrayList;
import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Inspection;

public class PrintDaoImpl extends BaseDaoImpl<Inspection,Integer> implements PrintDao{
	public List<Inspection> PrintFunction(String b,String gpd,String jlg,String fdj) throws Exception {
		String sql=" from Inspection where b = ? and (a = ? or a = ? or a = ?)";
		Object[]values=new String[]{b,gpd,jlg,fdj};
		return (ArrayList<Inspection>)this.find(sql,values);
	}

	//抓取巡检id
	public List<String> Patrol(String date1, String date2,String gpd,String jlg,String fdj)throws Exception {
		String strsql="select b from inspection where a > ? and a < ? and( a = ? or a = ? or a  = ?) group by b";
		String sql = "select b from inspection where ( a = ? or a = ? or a  = ?) and b > ? and b < ? group by b";
		return (List<String>) this.queryForList(sql, new String[]{gpd,jlg,fdj,date1,date2}, String.class);
	}

	
	public void addPlanned(Inspection ti) {
		try {
			save(ti);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
