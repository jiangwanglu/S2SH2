package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.RunTimeDate;


public interface RunTimeDateDao {
		List<RunTimeDate> MaintainName() throws Exception;
		
		//获取需要保养的设备
		List<RunTimeDate> MaintainQueryName() throws Exception;
		
		//根据设备名去查询数据
		List<RunTimeDate> MaintainByName(String name) throws Exception;
}
