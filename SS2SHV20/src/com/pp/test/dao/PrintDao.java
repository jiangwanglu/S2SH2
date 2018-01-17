package com.pp.test.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pp.test.bo.Inspection;

public interface PrintDao {
	List<Inspection> PrintFunction(String b,String gpd,String jlg,String fdj) throws Exception;
	
	//抓取巡检id
	List<String> Patrol(String date1, String date2,String gpd,String jlg,String fdj) throws Exception;

	void addPlanned(Inspection ti);

	//动态生成(查询所有设备)
	List<String> plann(String data,String name) throws Exception;
}
