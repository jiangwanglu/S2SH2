package com.pp.test.task;

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
	
	@Scheduled(cron="0 20 23 * * ? ")
	public void addmain() throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date(); 
		String date = df.format(today);
		Maintenance addmain = new Maintenance();
		List<MaintenancePlan> plan = this.loadDataService.Display();
		for(MaintenancePlan l : plan){
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
		}
		

/*	//判断是否过期
		Calendar day = Calendar.getInstance();
		day.setTime(today);  
		day.add(Calendar.DAY_OF_MONTH, +7);
		Date tomorrow = day.getTime();
		this.loadDataService.queryMaintenanceUpdata1(df.format(tomorrow));
		
		//判断是否待通知
		Calendar day7 = Calendar.getInstance();
		day7.setTime(today);  
		day7.add(Calendar.DAY_OF_MONTH, -7);
		tomorrow = day.getTime();
		this.loadDataService.queryMaintenanceUpdata(df.format(tomorrow));*/
	}
}