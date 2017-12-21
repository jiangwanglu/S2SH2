package com.pp.test.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.pp.test.bo.Centent;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.Maintenance;
import com.pp.test.bo.MaintenancePlan;
import com.pp.test.bo.Patrol;
import com.pp.test.bo.Plancontent;
import com.pp.test.bo.planned;
import com.pp.test.service.LoadDataService;
import com.pp.test.bo.RunTimeDate;
public class LoadDataAction  implements ServletRequestAware{
	private LoadDataService loadDataService;
	private HttpServletRequest request;
	private String result;
	private String jsonstr;
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	public LoadDataService getLoadDataService() {
		return loadDataService;
	}
	public void setLoadDataService(LoadDataService loadDataService) {
		this.loadDataService = loadDataService;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	//供配电
		public void PrintFunction() throws Exception{
			int i = 0;
			List<Inspection> list = new ArrayList<Inspection>();
			List<String> li = new ArrayList<String>();
			String gpd = "强电";
			String jlg = "计量柜";
			String 	fdj = "发电机";
			//抓取数据
			String date1 = request.getParameter("date1"); 
			String date2 = date1;
			String b = null;
			date1 = date1+" 00:00:00";
			date2 = date2+" 23:59:59";
			li = loadDataService.Patrol(date1,date2,gpd,jlg,fdj);
			for(String str :li){
				b = str;
				list = loadDataService.PrintFunction(b,gpd,jlg,fdj);
				loadDataService.Folder(date1,list,i);
				i++;
			}
		}
		//空调
		public void AirConditioner() throws Exception{
			List<Inspection>list = new ArrayList<Inspection>();
			List<String>li = new ArrayList<String>();
			String kt = "空调";
			int i = 0;
			//抓取数据
			String date1 = request.getParameter("date1");
			String id = request.getParameter("id");
			String date2 = date1;
			String b = null;
			date1 = date1+" 00:00:00";
			date2 = date2+" 23:59:59";
			li = loadDataService.Patrolkt(date1, date2,kt,id);
			for(String str : li){
				b = str;
				list = loadDataService.Patrolktdata(b,id);
				loadDataService.PatrolFolder(list, i,date1,id);
				i++;
			}
		}
		//水泵
		public void WaterPump() throws Exception{
			List<Inspection>list = new ArrayList<Inspection>();
			List<String>li = new ArrayList<String>();
			String gps = "给排水";
			int i = 0;
			//抓起数据
			String date1 = request.getParameter("date1");
			String date2 = date1;
			String b = null;
			date1 = date1+" 00:00:00";
			date2 = date2+" 23:59:59";
			li = loadDataService.WaterPumpgps(date1, date2, gps);
			for(String str : li){
				b = str;
				list = loadDataService.WaterPumpid(b,gps);
				loadDataService.WaterPump(list, i, date1);
				i ++;
			}
		}
		//计划
		public void Patrolk() throws Exception{
			String name = " ";
			String name1 = request.getParameter("name");
			String i = request.getParameter("i");
			if(!name1.equals("")){
				String a = name1.substring(name1.indexOf("[")+1,name1.lastIndexOf("]"));
				String [] ar  = a.split(",");
				for(String j : ar){
					String n = j.substring(j.indexOf("\"")+1,j.lastIndexOf("\""));
					name = name + n+",";
				}
				loadDataService.Patrolk(name,i);
			}
		}
		//获取数据库
		public String plan() throws Exception{
			List<Patrol>li = loadDataService.plank();
			HttpSession session = ServletActionContext.getRequest().getSession();
			for(Patrol l : li){
				session.setAttribute(""+l.getId()+"patrol", l);
			}
			return "plank";
		}
		//通过数据库创建询价目录
		public String queryJSP()throws Exception{
			List<String> li = this.loadDataService.CatalogData();
			result = "";
			for(int i = 0;i < li.size();i++){
				result = result + li.get(i)+",";
			}

			return "load_success";
		}
		public String aaa(){
			 Format f = new SimpleDateFormat("yyyy-MM-dd");   
		     Date today = new Date(); 
		     Calendar c = Calendar.getInstance();  
		     c.setTime(today);  
		     c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天  
		     Date tomorrow = c.getTime();  
			List<planned> list1 = this.loadDataService.queryPlanned();
			for(planned l : list1){
				if(!l.getUnitid().equals("")&&l.getUnitid() != null){
					String[]id = l.getUnitid().split(",");
					for(int r = 0;r < id.length;r++){
						Inspection ti = new Inspection();
						ti.setId(id[r]);
						ti.setB(f.format(tomorrow)+" "+l.getData().split("-")[0]);
						ti.setC("0");
						ti.setName(this.loadDataService.querySourceName(id[r]));
						ti.setA(this.loadDataService.querysheb(id[r]));
						ti.setD(f.format(tomorrow));
						this.loadDataService.addPlanned(ti);
					}
				}
			}	
			return "load_success";
		}
		
		public String QueryCentent2(){
			String content = request.getParameter("name");
			String name = content.split(",")[0];
			String id = content.split(",")[1];
			result = this.loadDataService.QueryCentent2(name,id);
			/*result =JSONArray.fromObject(this.loadDataService.QueryCentent2(name,id)).toString();*/
			return "load_success";
		}
		public void updataContent2(){
			String name = request.getParameter("name");
			String data = request.getParameter("data");
			String value = request.getParameter("value");
			this.loadDataService.updataContent2(name,data,value);
		}
		
		
		public String MainName() throws Exception{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			if(id.equals("0")){
				List<String> list1 = this.loadDataService.PlancontentName();//1
				result = JSONArray.fromObject(list1).toString();
			}else if(id.equals("1")){
				List<Plancontent> list2 = this.loadDataService.PlancontentName2(name);
				result = JSONArray.fromObject(list2).toString();
			}else if(id.equals("2")){
				List<String> list3 = this.loadDataService.PlancontentName3(name);
				result = JSONArray.fromObject(list3).toString();
			}
			return "load_success";
		}
		
		public String QueryCentent() throws Exception{
			String name = request.getParameter("name");
			List<String> li = this.loadDataService.PlancontentName4(name);
			for(String l : li){
				name = l;
			}
			List<Centent>list = this.loadDataService.QueryCentent(name);
			result = JSONArray.fromObject(list).toString();
			return "load_success";
		}
		
		public String UpdateCentent() throws Exception{
			String arr = request.getParameter("centent");
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			List<String> list = this.loadDataService.PlancontentName4(name);
			for(String s : list){
				name = s;
			}
			this.loadDataService.updatacentent(name,id,arr);
			return "load_success";
		}
		
		public String addcontent() throws Exception{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			List<String> li = this.loadDataService.PlancontentName4(id);
			for(String l : li){
				id = l;
			}
			List<Centent>cen = this.loadDataService.QueryCentent(id);
			for(Centent s : cen){
				s.setWhole(s.getWhole()+","+name);
				this.loadDataService.addcontent(s);
			}
			return "load_success";
		}
		
		public String addmaintain() throws Exception{
			String executor = request.getParameter("Executor");				//执行人
			String executioncycle = request.getParameter("Content");		//执行周期
			String category = request.getParameter("DeviceClass");			//类别
			String name  = request.getParameter("DeviceName");				//名称
			String data = request.getParameter("ExecutionDate");
			MaintenancePlan plan = new MaintenancePlan();
			plan.setExecutioncycle(executioncycle);	//执行周期
			plan.setExecutor(executor);				//执行人
			plan.setMechanism("鼎丰");					//执行机构
			plan.setData(data);
			List<String> planc = this.loadDataService.PlancontentName4(category);
		    for(String s : planc){
		    	plan.setCategory(s);	//类别
		    }
		    for(String a : name.split(",")){
		    	int s = this.loadDataService.judge(a,executioncycle);
		    	if(s != 0){
		    		this.loadDataService.exectiondelete(this.loadDataService.NameQueryId(a),executioncycle);
		    	}
	    		//2
		    	Maintenance main = new Maintenance();
		    	main.setMaintenancecategory(a);
		    	main.setUnitid(this.loadDataService.NameQueryId(a));
		    	main.setExecutiondata(data);
		    	main.setWhether("0");
		    	List<Centent>list = this.loadDataService.QueryCentent(plan.getCategory());
		    	for(Centent cen : list){
					if(executioncycle.equals("月度")){
						main.setContent(cen.getMonth());
					}else if(executioncycle.equals("季度")){
						main.setContent(cen.getSeason());
					}else if(executioncycle.equals("年度")){
						main.setContent(cen.getYear());
					}
				}
		    	main.setDegree(executioncycle);
		    	this.loadDataService.addMaintenance(main);
		    	plan.setName(a);
		    	List<RunTimeDate> time = this.loadDataService.MaintainName();
		    	plan.setUnitid(this.loadDataService.NameQueryId(a));
		    	for(RunTimeDate runn : time){
		    		if(runn.getLocal().equals(plan.getName())){
		    			if(runn.getDianliu() == 1){
							if(runn.getTimen() == 0){
								Date da = new Date();
								Long ti = da.getTime();
								Long lo = (long) runn.getTimep();
								ti = ti - (lo*1000);
								ti = ti / (1000 * 60 * 60);
								plan.setRunning(ti.toString());//运行时间
							}else{
								Date da = new Date();
								Long ti = da.getTime();
								Long lo = (long) runn.getTimen();
								ti = (ti - (lo*1000)) + runn.getCounttime();
								ti = ti / (1000*60*60);
								plan.setRunning(ti.toString());//运行时间
							}
						}else{
							Long ti = (long) (runn.getCounttime() / 120);
							plan.setRunning(ti.toString());//运行时间
						}
		    		}
		    	}
		    	this.loadDataService.addMaintain(plan);
	    	
	   }
		   return "load_success";
		}
		//显示主页面
		public String Display() throws Exception{
			List<MaintenancePlan> list = this.loadDataService.Display();
			List<RunTimeDate> time = this.loadDataService.MaintainName();
			for(MaintenancePlan l : list){
				for(RunTimeDate date : time){
					if(l.getName().equals(date.getLocal())){
						if(date.getDianliu() == 1){
							if(date.getCounttime() == 0){
								Date da = new Date();
								Long ti = da.getTime();
								Long lo = (long) date.getTimep();
								ti = ti - (lo*1000);
								ti = ti / (1000 * 60 * 60);
								l.setRunning(ti.toString());
							}else{
								Date da = new Date();
								Long ti = da.getTime();
								Long lo = (long) date.getTimen();
								ti = (ti - (lo*1000)) + date.getCounttime();
								ti = ti / (1000*60*60);
								l.setRunning(ti.toString());//运行时间
							}
						}else{
							Long ti = (long) (date.getCounttime() / 120);
							l.setRunning(ti.toString());//运行时间
						}
						this.loadDataService.Updatamainrun(l);
					}
				}
			}
			result = JSONArray.fromObject(list).toString();
			return "load_success";
		}
		//删除
		public String exectiondelete() throws Exception{
			String unitid = request.getParameter("unitid").split(",")[0];
			String con = request.getParameter("unitid").split(",")[1];
			this.loadDataService.exectiondelete(unitid,con);
			return "load_success";
		}
		//查询2
		public String Query2() throws Exception{
			String unitid = request.getParameter("unitid");
			List<Maintenance> list = this.loadDataService.Query2(unitid);
			result = JSONArray.fromObject(list).toString();
			return "load_success";
		}
		//添加保养内容
		public String addcontenr() throws Exception{
			Centent ten = new Centent();
			String s = request.getParameter("s");
			String yue = request.getParameter("yue");
			String nian = request.getParameter("nian");
			String qb = request.getParameter("qbnr");
			String ji = request.getParameter("ji");
			List<String> list = this.loadDataService.PlancontentName4(s);//通过id找到名字
			for(String name : list){
				ten.setName(name);
				ten.setMonth(yue);
				ten.setSeason(ji);
				ten.setYear(nian);
				ten.setWhole(qb);
			}
			this.loadDataService.addcontenr(ten);
			return "load_success";
		}
		
		//进行查询写入到输入框
		public String querymain() throws Exception{
			List<String>list = this.loadDataService.querymain();
			result = JSONArray.fromObject(list).toString();
			return "load_success";
		}
		
		//查询出所以的
		public String queryPlan(){
			List<planned> list = this.loadDataService.queryPlanned();
			result = JSONArray.fromObject(list).toString();
			return "load_success";
		}
		//修改计划设备
		public String updatePlan() throws Exception{
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			String[] str = name.split(",");
			String unid = "";
			for(String i : str){
				unid+=this.loadDataService.NameQueryId(i)+",";
				if(unid.equals("null,")){
					unid = " ";
				}
			}
			this.loadDataService.addPlan(id,name,unid);
			return "load_success";
		}
		//查询出时间
		public String queryData(){
			String id = request.getParameter("id");
			result = JSONArray.fromObject(this.loadDataService.queryData(id)).toString();
			return "load_success";
		}
		//修改时间
		public void updataData(){
			String data1 = request.getParameter("data1");
			String data2 = request.getParameter("data2");
			String id = request.getParameter("id");
			this.loadDataService.updataData(data1,data2,id);
		}
		//计划
		public void Test(){
			String date = request.getParameter("date");
			List<planned> list = this.loadDataService.queryPlanned();
			for(planned l : list){
				if(!l.getUnitid().equals("")&&l.getUnitid() != null){
					String[]id = l.getUnitid().split(",");
					for(int i = 0;i < id.length;i++){
						Inspection ti = new Inspection();
						ti.setId(id[i]);
						ti.setB(date+" "+l.getData().split("-")[0]);
						ti.setC("0");
						ti.setName(this.loadDataService.querySourceName(id[i]));
						ti.setA(this.loadDataService.querysheb(id[i]));
						this.loadDataService.addPlanned(ti);
					}
				}
			}
		}
		//删除2
		public void deleteMaintenance(){
			String content = request.getParameter("name");
			String name = content.split(",")[0];
			String id = content.split(",")[1];
			this.loadDataService.deleteMaintenance(name,id);
		}
		public String Query3() throws Exception{
			String unitid = request.getParameter("unitid");
			List<Maintenance> list = this.loadDataService.Query3(unitid);
			result = JSONArray.fromObject(list).toString();
			return "load_success";
		}
}