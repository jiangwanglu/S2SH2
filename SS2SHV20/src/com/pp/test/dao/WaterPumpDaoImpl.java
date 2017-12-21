package com.pp.test.dao;

import java.util.ArrayList;
import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Inspection;

public class WaterPumpDaoImpl extends BaseDaoImpl<Inspection,Integer>  implements WaterPumpDao{

	public List<Inspection> WaterPumpid(String b,String gps) throws Exception {
		String sql=" from Inspection where b = ? and a = ?";
		Object[]values=new String[]{b,gps};
		return (ArrayList<Inspection>)this.find(sql,values);
	}

	public List<String> WaterPumpgps(String date1, String date2, String gps)
			throws Exception {
		String sql= "select b from inspection where  b > ? and b < ?  and a = ? group by b";
		return (List<String>) this.queryForList(sql, new String[]{date1,date2,gps}, String.class);
	}

}
