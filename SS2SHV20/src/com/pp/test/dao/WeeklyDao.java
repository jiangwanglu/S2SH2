package com.pp.test.dao;
import java.util.List;
import com.pp.test.bo.Dianlang;
public interface WeeklyDao {
	//抓取当天的数据
	List<Dianlang> Weekly(String date) throws Exception;
	
	//抓取周一或1号的数据
	List<Dianlang> weeklydao(String str) throws Exception;
}