package com.pp.test.dao;


import java.sql.SQLException;
import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.MaintenancePlan;
import com.pp.test.bo.Patrol;

public class MaintainDaoImpl extends BaseDaoImpl<MaintenancePlan,Integer> implements MaintainDao{
	
	public void addMaintain(MaintenancePlan plan) throws Exception {
		/*if(plan.getUpper().equals(" ")){
			this.save(plan);
		}else{
			String sql = "update MaintenancePlan set deviceclass=? , devicename=? , runningtime=? , upper=? ,executiondate=?,executor=?,executionCycle=?,content=?,whether=?,actualCondition=?,category=?,mechanism=? where devicename = ?";
			this.executeUpdate(sql,new String[]{plan.getDeviceclass(),plan.getDevicename(),plan.getRunningtime(),plan.getUpper(),plan.getExecutiondate(),plan.getExecutor(),plan.getExecutioncycle(),plan.getContent(),plan.getWhether(),plan.getActualcondition(),plan.getCategory(),plan.getMechanism(),plan.getDevicename()});
		}*/
		this.save(plan);
	}

	public List<String> querymaintain(String a,String b) throws Exception {
		String sql = "select Executiondate from MaintenancePlan where unitid = ? and content = ? group by executiondate";
		return (List<String>)this.queryForList(sql,new String[]{a,b}, String.class);
	}

	
	public List<MaintenancePlan> Display() throws Exception {
		String sql = "select * from MaintenancePlan";
		return (List<MaintenancePlan>) this.queryForList(sql,MaintenancePlan.class);
	}

	//修改运行时间
	public void Updatamainrun(MaintenancePlan l) throws SQLException {
		/*String sql ="update MaintenancePlan set runningtime = ? where devicename = ?";
		this.executeUpdate(sql, new String[]{l.getRunningtime(),l.getDevicename()});*/
	}
	
	//删除
	public void exectiondelete(String id,String con) throws Exception{
		String sql = "delete from MaintenancePlan where unitid=? and executioncycle = ? ";
		this.bulkUpdate(sql,new String[]{id,con});
	}
	
	public int judge(String a, String category) {
		String sql = "select * from MaintenancePlan where name = ? and executioncycle = ?";
		try {
			return this.queryForInt(sql,new String[]{a,category});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
