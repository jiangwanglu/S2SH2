package com.pp.test.dao;

import java.sql.SQLException;
import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Maintenance;

public class MaintenanceDaoImpl extends BaseDaoImpl<Maintenance,Integer> implements MaintenanceDao {
	public List<Maintenance> Query2(String id) throws Exception {
		String sql = "select * from Maintenance where maintenancecategory=?";
		return (List<Maintenance>)this.queryForList(sql,new String[]{id},Maintenance.class);
	}

	public List<Maintenance> queryzhiding(String id,String con) throws Exception {
		String sql = "select * from  maintenance where executiondata=(select MAX(executiondata) from  Maintenance where unitid=? and degree=?) ";
		return (List<Maintenance>)this.queryForList(sql,new String[]{id,con} ,Maintenance.class);
	}

	
	public void addMaintenance(Maintenance main) {
		try {
			this.save(main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String querymax(String id,String con) throws Exception {
		String sql = "select max(executiondata) from Maintenance where unitid = ? and degree = ? ";
		return (String) this.queryForObject(sql,new String[]{id,con}, String.class);
	}

	public List<String> querymain() throws Exception {
		String sql = "select maintenancecategory from maintenance group by maintenancecategory";
		return (List<String>)this.queryForList(sql, Maintenance.class);
	}

	public String QueryCentent2(String name, String id) {
		String state = "0";
		String sql  = "select content from maintenance where unitid = ? and executiondata = ? and  whether = ?";
		try {
			return (String)this.queryForObject(sql,new String[]{name,id,state}, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		};
		return sql;
	}

	public void updataContent2(String name, String data, String value) {
		String sql = "update maintenance set content = ? where maintenancecategory = ? and  executiondata = ?";
		try {
			this.executeUpdate(sql,new String[]{value,name,data});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Maintenance QueryMain(String date, String month) {
		String sql = "select * from maintenance where date = ? and month = ?";
		try {
			return (Maintenance) this.queryForObject(sql,new String[]{date,month}, Maintenance.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void queryMaintenanceUpdate(String date) {
		String ind = "0";
		String sql = "select * from maintenance where executiondata = ? and whether = ?";
		try {
			List<Maintenance> main = (List<Maintenance>) this.queryForList(sql,new String[]{date,ind},Maintenance.class);
			for(Maintenance ma : main){
				if(ma.getWhether().equals("0")){
					ma.setWhether("3");
				}
				this.save(ma);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void queryMaintenanceUpdate1(String date) {
		String ind = "0";
		String sql = "select * from maintenance where executiondata = ? and whether = 0";
		try {
			List<Maintenance> main = (List<Maintenance>) this.queryForList(sql,new String[]{date,ind},Maintenance.class);
			for(Maintenance ma : main){
				if(ma.getWhether().equals("0")){
					ma.setWhether("2");
				}
				this.save(ma);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMaintenance(String name, String id) {
		String s = "0";
		String sql = "delete from Maintenance where unitid = ? and executiondata = ? and whether = ?";
		try {
			this.bulkUpdate(sql,new String[]{name,id,s});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Maintenance> Query3(String id) throws Exception {
		String s = "0";
		String sql = "select * from Maintenance where maintenancecategory=? and whether = ?";
		return (List<Maintenance>)this.queryForList(sql,new String[]{id,s},Maintenance.class);
	}
	
}