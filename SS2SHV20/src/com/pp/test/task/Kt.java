package com.pp.test.task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.pp.test.bo.Inspection;
import com.pp.test.service.LoadDataService;
@Component
public class Kt {
	@Autowired
	public LoadDataService loadDataService;

	public void setLoadDataService(LoadDataService loadDataService) {
		this.loadDataService = loadDataService;
	}
	@Scheduled(cron="0 43 23 * * ?")
	protected void executeInternal()throws JobExecutionException {
		
		String id;
		for(int j = 0;j <= 2;j++){
			if(j == 0){
				id = "DF-KT-40-001";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date1 = df.format(new Date());
				List<Inspection>list = new ArrayList<Inspection>();
				List<String>li = new ArrayList<String>();
				String kt = "空调";
				int i = 0;
				//抓取数据
				String date2 = date1;
				String b = null;
				date1 = date1+" 00:00:00";
				date2 = date2+" 23:59:59";
				try {
					li = loadDataService.Patrolkt(date1, date2,kt,id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				for(String str : li){
					try {
						b = str;
						list = loadDataService.Patrolktdata(b,id);
						loadDataService.PatrolFolder(list, i,date1,id);
						i++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally{
						list = null;
						li = null;
					}
				}
			}else if(j == 1){
				id = "DF-KT-40-002";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date1 = df.format(new Date());
				List<Inspection>list = new ArrayList<Inspection>();
				List<String>li = new ArrayList<String>();
				String kt = "空调";
				int i = 0;
				//抓取数据
				String date2 = date1;
				String b = null;
				date1 = date1+" 00:00:00";
				date2 = date2+" 23:59:59";
				try {
					li = loadDataService.Patrolkt(date1, date2,kt,id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				for(String str : li){
					try {
						b = str;
						list = loadDataService.Patrolktdata(b,id);
						loadDataService.PatrolFolder(list, i,date1,id);
						i++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						list = null;
						li = null;
					}
				}
			}else if(j == 2){
				id = "DF-KT-40-003";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date1 = df.format(new Date());
				List<Inspection>list = new ArrayList<Inspection>();
				List<String>li = new ArrayList<String>();
				String kt = "空调";
				int i = 0;
				//抓取数据
				String date2 = date1;
				String b = null;
				date1 = date1+" 00:00:00";
				date2 = date2+" 23:59:59";
				try {
					li = loadDataService.Patrolkt(date1, date2,kt,id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				for(String str : li){
					try {
						b = str;
						list = loadDataService.Patrolktdata(b,id);
						loadDataService.PatrolFolder(list, i,date1,id);
						i++;
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						list = null;
						li = null;
					}
				}
			}
		}
	}
}
