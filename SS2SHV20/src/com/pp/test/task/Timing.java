package com.pp.test.task;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.planned;
import com.pp.test.service.LoadDataService;
@Component
public class Timing {
	@Autowired
	public LoadDataService loadDataService;

	public void setLoadDataService(LoadDataService loadDataService) {
		this.loadDataService = loadDataService;
	}
	
	@Scheduled(cron="0 55 23 * * ?")
	protected void executeInternal()throws JobExecutionException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = df.format(new Date());
		int i = 0;
		List<Inspection> list = new ArrayList<Inspection>();
		List<String> li = new ArrayList<String>();
		String gpd = "强电";
		String jlg = "计量柜";
		String 	fdj = "发电机";
		//抓取数据
		String date2 = date1;
		String b = null;
		date1 = date1+" 00:00:00";
		date2 = date2+" 23:59:59";
		try {
			li = loadDataService.Patrol(date1,date2,gpd,jlg,fdj);
			for(String str :li){
				b = str;
				list = loadDataService.PrintFunction(b,gpd,jlg,fdj);
				loadDataService.Folder(date1,list,i);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			list = null;
			li = null;
		}
		
		
		
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
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String date11 = df1.format(new Date());
		int i1 = 0;
		List<Inspection> list11 = new ArrayList<Inspection>();
		List<String> li1 = new ArrayList<String>();
		String gps = "给排水";
		//抓取数据
		String date21 = date11;
		String b1 = null;
		date11 = date11+" 00:00:00";
		date21 = date21+" 23:59:59";
		try {
			li1 = loadDataService.WaterPumpgps(date11, date21, gps);
			for(String str : li1){
				b1 = str;
				list11 = loadDataService.WaterPumpid(b1,gps);
				loadDataService.WaterPump(list11, i1, date11);
				i1 ++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			list11 = null;
			li1 = null;
		}
	}
}