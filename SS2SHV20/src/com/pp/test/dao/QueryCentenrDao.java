package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.Centent;

public interface QueryCentenrDao {
	List<Centent> QueryCentenr(String name) throws Exception;
	
	void updatecentent(String name,String id,String arr)throws Exception;
	
	void addcontent(Centent s)throws Exception;
	

}
