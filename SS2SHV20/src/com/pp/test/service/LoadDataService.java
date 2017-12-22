package com.pp.test.service;
import java.io.IOException;
import com.pp.test.bo.RunTimeDate;
import java.sql.SQLException;
import java.util.List;
import com.pp.test.bo.Centent;
import com.pp.test.bo.Dianlang;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.Maintenance;
import com.pp.test.bo.MaintenancePlan;
import com.pp.test.bo.PatrolRecord;
import com.pp.test.bo.Plancontent;
import com.pp.test.bo.Source;
import com.pp.test.bo.planned;
public interface LoadDataService {
	
	
	//供配电
	List<String> Patrol(String date1,String date2,String gpd,String jlg,String fdj) throws Exception;
	//抓取数据
	List<Inspection> PrintFunction(String b,String gpd,String jlg,String fdj) throws Exception;
	//写入到文档
	void Folder(String date1,List<Inspection> list,int i) throws Exception;
	
	
	
	//空调抓取id
	List<String> Patrolkt(String date1,String date2,String kt,String id) throws Exception;
	//空调通过id获取到对应的数据
	List<Inspection> Patrolktdata(String b,String id) throws Exception;
	//3号空调
	void PatrolFolder(List<Inspection>list,int i,String date1,String id) throws IOException;
	
	
	//给排水
	List<String> WaterPumpgps(String date1,String date2,String gps)throws Exception;
	
	List<Inspection>WaterPumpid(String b,String gps) throws Exception;
	
	void WaterPump(List<Inspection> list,int i,String date1)throws IOException;
	
	
	//抓取当天的数据
		 List<Dianlang>  Weekly(String date1) throws Exception;
		 
	//抓取周一和一号的数据
	List<Dianlang> WeeklyUpper(String str) throws Exception;
	
	
	//计划
	void Patrolk(String name ,String i) throws Exception;
	List<com.pp.test.bo.Patrol> plank() throws Exception;
	//通过数据抓取询价目录
	List<String> CatalogData() throws Exception;

	//查询出设备
	List<RunTimeDate> MaintainName() throws Exception;
	
	
	
	
	
	//找到树形(总)
	List<String> MainName() throws Exception;
	List<Source> MainDeviceName(String name) throws Exception;
	
	
	//找到树形(总)
	List<String> PlancontentName() throws Exception;//1
	List<Plancontent> PlancontentName2(String name1) throws Exception;//2
	List<String> PlancontentName3(String name2) throws Exception;//3
	List<String> PlancontentName4(String name)throws Exception;
	List<Centent> QueryCentent(String name)throws Exception;
	void updatacentent(String name,String id,String arr) throws Exception;
	void addcontent(Centent s)throws Exception;
	void addMaintain(MaintenancePlan plan) throws Exception;
	List<String> QueryMaintenancePlan(String a,String b) throws Exception;
	//获取主页面
	List<MaintenancePlan> Display() throws Exception;
	//修改运行时间
	void Updatamainrun(MaintenancePlan l) throws SQLException;
	void addcontenr(Centent cen) throws Exception;
	//通过名字查询id
	String NameQueryId(String a) throws Exception;
	//删除1
	void exectiondelete(String id,String con)throws Exception;
	List<Maintenance>Query2(String id)throws Exception;
	List<Maintenance>queryzhiding(String id,String content) throws Exception;
	void addMaintenance(Maintenance main)throws Exception;
	String Maxdata(String id,String con) throws Exception;
	//通过查询写入到输入框
	List<String> querymain() throws Exception;
	//查询内容
	String querycent(String name) throws Exception;
	
	//查询出全部的数据
	List<planned> queryPlanned();
	//写入计划到表
	void addPlanned(Inspection ti);
	String querySourceName(String id);
	void addPlan(String id,String unid,String name);
	String querysheb(String id);
	List<planned> queryData(String id);
	void updataData(String data1,String data2,String id);
	String QueryCentent2(String name,String id);
	void updataContent2(String name,String data,String value);
	Maintenance QueryMaintain(String date,String month);
	int judge(String a,String category);
	void queryMaintenanceUpdata(String date); 
	void queryMaintenanceUpdata1(String date,String date1);
	PatrolRecord queryPatrolRecord(String unitId,String startdate,String index);
	void addRecord(PatrolRecord patrol);
	void deleteMaintenance(String name,String id);
	List<Maintenance>Query3(String id)throws Exception;
}