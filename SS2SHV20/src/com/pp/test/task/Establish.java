package com.pp.test.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pp.test.bo.PatrolRecord;
import com.pp.test.service.LoadDataService;
@Component
public class Establish {
	@Autowired
	public LoadDataService loadDataService;

	public void setLoadDataService(LoadDataService loadDataService) {
		this.loadDataService = loadDataService;
	}
	
	@Scheduled(cron="0 42 23 * * ?")
	public void addRecord(){
		PatrolRecord patrol = new PatrolRecord();
		Date today = new Date(); 
		Calendar month1 = Calendar.getInstance();
		month1.setTime(today);  
		int day=month1.get(Calendar.DATE); 
		month1.set(Calendar.DATE,day+1);
		String date =new SimpleDateFormat("yyyy-MM").format(month1.getTime()); 
		String day1 =new SimpleDateFormat("dd").format(month1.getTime()); 
		String unitId = null;
		for(int i=0;i<5;i++){
			if(i == 0){
				unitId = "DF-KT-KTJF";//空调机房
			}else if(i == 1){
				unitId = "DF-GP-SHBF";//生活泵房
			}else if(i == 2){
				unitId = "DF-RD-WYBF";//稳压泵房
			}else if(i == 3){
				unitId = "DF-RD-XFBF";//消防泵房
			}else if(i == 4){
				unitId = "DF-RD-XFZX";//消防中心
			}
			patrol.setUnitid(unitId);
			patrol.setDate(date);
			patrol.setDay(Integer.parseInt(day1));
			patrol.setType9("0");
			this.loadDataService.addRecord(patrol);
		}
	}
}
