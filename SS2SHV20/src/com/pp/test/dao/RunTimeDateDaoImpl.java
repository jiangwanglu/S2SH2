package com.pp.test.dao;

import java.util.List;

import com.myapp.common.dao.BaseDaoImpl;
import com.pp.test.bo.Maintain;
import com.pp.test.bo.RunTimeDate;

public class RunTimeDateDaoImpl extends BaseDaoImpl<RunTimeDate,Integer> implements RunTimeDateDao {

	public List<RunTimeDate> MaintainName() throws Exception {
		
		String sql = "select * from runtime";
		return (List<RunTimeDate>) this.queryForList(sql,RunTimeDate.class);
	}
	
	//获取需要保养的设备
		public List<RunTimeDate> MaintainQueryName() throws Exception {
			String sql = "select local from runtime";
			return (List<RunTimeDate>)this.queryForList(sql, RunTimeDate.class);
		}

	//根据设备名去查询数据
	public List<RunTimeDate> MaintainByName(String name) throws Exception {
		String sql = "select * from runtime where local = ?";
		return (List<RunTimeDate>) this.queryForList(sql, new String[]{name},RunTimeDate.class);
	}

}