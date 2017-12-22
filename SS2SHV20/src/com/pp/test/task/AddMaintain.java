package com.pp.test.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.chainsaw.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pp.test.bo.Maintenance;
import com.pp.test.bo.MaintenancePlan;
import com.pp.test.service.LoadDataService;

@Component
public class AddMaintain {
	@Autowired
	public LoadDataService loadDataService;

	public void setLoadDataService(LoadDataService loadDataService) {
		this.loadDataService = loadDataService;
	}
	
	@Scheduled(cron="0/5 * * * * ? ")
	public void addmain() throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date(); 
		String date = df.format(today);
		Maintenance addmain = new Maintenance();
		List<MaintenancePlan> plan = this.loadDataService.Display();
/*		for(MaintenancePlan l : plan){
			List<Maintenance> li = this.loadDataService.queryzhiding(l.getUnitid(),l.getExecutioncycle());
			for(Maintenance main : li){
				if(main.getDegree().equals("月度")){
					Calendar month1 = Calendar.getInstance();
					month1.setTime(today);  
				    month1.add(Calendar.MONTH, -1);// 今天-1一个月
				    Date tomorrow = month1.getTime(); 
				    if(main.getExecutiondata().equals(df.format(tomorrow))){
				    	addmain.setUnitid(main.getUnitid());
				    	addmain.setExecutiondata(date);
				    	addmain.setWhether("0");
				    	addmain.setMaintenancecategory(main.getMaintenancecategory());
				    	addmain.setContent(main.getContent());
				    	addmain.setDegree(main.getDegree());
				    	this.loadDataService.addMaintenance(addmain);
				    }
				}else if(main.getDegree().equals("季度")){
					Calendar month1 = Calendar.getInstance();
					month1.setTime(today);  
				    month1.add(Calendar.MONTH, -6);// 今天-1一个月
				    Date tomorrow = month1.getTime();
				    if(main.getExecutiondata().equals(df.format(tomorrow))){
				    	addmain.setUnitid(main.getUnitid());
				    	addmain.setExecutiondata(date);
				    	addmain.setWhether("0");
				    	addmain.setMaintenancecategory(main.getMaintenancecategory());
				    	addmain.setContent(main.getContent());
				    	addmain.setDegree(main.getDegree());
				    	this.loadDataService.addMaintenance(addmain);
				    }
				}else if(main.getDegree().equals("年度")){
					Calendar month1 = Calendar.getInstance();
					month1.setTime(today);  
				    month1.add(Calendar.MONTH, -12);// 今天-1一个月
				    Date tomorrow = month1.getTime();
				    if(main.getExecutiondata().equals(df.format(tomorrow))){
				    	addmain.setUnitid(main.getUnitid());
				    	addmain.setExecutiondata(date);
				    	addmain.setWhether("0");
				    	addmain.setMaintenancecategory(main.getMaintenancecategory());
				    	addmain.setContent(main.getContent());
				    	addmain.setDegree(main.getDegree());
				    	this.loadDataService.addMaintenance(addmain);
				    }
				}
			}
		}*/
		
	//判断是否过期
		Calendar day = Calendar.getInstance();
		day.setTime(today);  
		day.add(Calendar.DAY_OF_MONTH, -14);
		Date tomorrow = day.getTime();
		this.loadDataService.queryMaintenanceUpdata1(df.format(tomorrow),date);
		
		//判断是否待通知
		Calendar day7 = Calendar.getInstance();
		day7.setTime(today);  
		day7.add(Calendar.DAY_OF_MONTH, -7);
		tomorrow = day.getTime();
		this.loadDataService.queryMaintenanceUpdata(df.format(tomorrow));
	}
	public static void main(String[] args) throws ParseException {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        //跨年的情况会出现问题哦
	        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 1
	        Date fDate=sdf.parse("2016-01-02");
	        Date oDate=sdf.parse("2016-01-01");
	        Calendar aCalendar = Calendar.getInstance();
	        aCalendar.setTime(fDate);
	        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
	        aCalendar.setTime(oDate);
	        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
	        int days=day2-day1;
	        System.out.print(days);
	}
}