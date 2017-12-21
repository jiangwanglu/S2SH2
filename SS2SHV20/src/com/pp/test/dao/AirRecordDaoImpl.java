package com.pp.test.dao;
import java.util.List;
import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Inspection;
public class AirRecordDaoImpl extends BaseDaoImpl<Inspection,Integer>  implements AirRecordDao{
	public List<String> Patrolkt(String date1, String date2, String kt,String id)throws Exception{
		String sql= "select b from inspection where  b > ? and b < ?  and a = ? and id=? group by b";
		List<String>list =  (List<String>) this.queryForList(sql, new String[]{date1,date2,kt,id}, String.class);
		return list ;
	}

	
	public List<Inspection> Patrolktdata(String b,String id) throws Exception {
		String sql="from Inspection where B = ? and  Id = ?";
		Object[]values=new String[]{b,id};
		return (List<Inspection>)this.find(sql,values);
	}
}