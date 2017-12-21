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
public class Gps {
	@Autowired
	public LoadDataService loadDataService;

	public void setLoadDataService(LoadDataService loadDataService) {
		this.loadDataService = loadDataService;
	}
	@Scheduled(cron="0 41 23 * * ?")
	protected void executeInternal()throws JobExecutionException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = df.format(new Date());
		int i = 0;
		List<Inspection> list = new ArrayList<Inspection>();
		List<String> li = new ArrayList<String>();
		String gps = "给排水";
		//抓取数据
		String date2 = date1;
		String b = null;
		date1 = date1+" 00:00:00";
		date2 = date2+" 23:59:59";
		try {
			li = loadDataService.WaterPumpgps(date1, date2, gps);
			for(String str : li){
				b = str;
				list = loadDataService.WaterPumpid(b,gps);
				loadDataService.WaterPump(list, i, date1);
				i ++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			list = null;
			li = null;
		}
	}
}
