package com.pp.test.dao;

import java.util.List;

import com.pp.test.bo.Source;

public interface SourceDao {
	List<String> MainName() throws Exception;
	List<Source> MainDeviceName(String name) throws Exception;
	List<Source> QuerySource(String name) throws Exception;
	String NameQueryId(String a) throws Exception;
	String queyrcent(String name) throws Exception;
	
	String querySourceName(String id);
	
	String querysheb(String id);
}