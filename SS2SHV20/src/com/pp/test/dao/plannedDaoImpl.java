package com.pp.test.dao;
import java.sql.SQLException;
import java.util.List;
import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.planned;
public class plannedDaoImpl extends BaseDaoImpl<planned,Integer> implements plannedDao {
	public List<planned> queryPlanned() {
		//String sql = "select * from planned";
		try {
			return(List<planned>)this.loadAll();
			//return (List<planned>) this.queryForList(sql,planned.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addPlanned(String id, String name, String b) {
		String sql = "insert into inspection(id,name,b) values(?,?,?)";
		try {
			this.bulkUpdate(sql,new String[]{id,name,b});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updatePlan(String id, String unid, String name) {
		String sql = "update planned set name=?,unitid=? where id = ?";
		try {
			this.executeUpdateForRow(sql, new String[]{unid,name,id});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<planned> queryData(String id) {
		String sql = "select * from planned where id = ?";
		try {
			return (List<planned>) this.queryForList(sql,new String[]{id},planned.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updataData(String data1, String data2,String id) {
		String sql = "update planned set data=?,endata=? where id = ?";
		try {
			this.executeUpdate(sql, new String[]{data1,data2,id});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}