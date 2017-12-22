package com.pp.test.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.myapp.common.service.BaseServiceImpl;
import com.pp.test.bo.Centent;
import com.pp.test.bo.Dianlang;
import com.pp.test.bo.Inspection;
import com.pp.test.bo.Maintenance;
import com.pp.test.bo.MaintenancePlan;
import com.pp.test.bo.PatrolRecord;
import com.pp.test.bo.Plancontent;
import com.pp.test.bo.RunTimeDate;
import com.pp.test.bo.Source;
import com.pp.test.bo.planned;
import com.pp.test.dao.AirRecordDao;
import com.pp.test.dao.CententDao;
import com.pp.test.dao.MaintainDao;
import com.pp.test.dao.MaintenanceDao;
import com.pp.test.dao.PatrolRecordDao;
import com.pp.test.dao.PatrolkDao;
import com.pp.test.dao.PlancontentDao;
import com.pp.test.dao.PrintDao;
import com.pp.test.dao.QueryCentenrDao;
import com.pp.test.dao.RunTimeDateDao;
import com.pp.test.dao.SourceDao;
import com.pp.test.dao.WaterPumpDao;
import com.pp.test.dao.WeeklyDao;
import com.pp.test.dao.plannedDao;
public class LoadDataServiceImpl extends BaseServiceImpl implements LoadDataService{               
	
	private PrintDao printdao;
	
	private AirRecordDao airrecorddao;
	
	private WaterPumpDao waterpumpdao;

	private WeeklyDao weeklydao;
	
	private PatrolkDao patrolkdao;
	
	private MaintainDao maintaindao;
	
	private RunTimeDateDao runtimedatedao;
	
	private SourceDao sourcedao;
	
	private PlancontentDao  plancontentdao;
	
	private QueryCentenrDao queryCentenrdao;
	
	private CententDao cententdao;
	
	private MaintenanceDao maintenancedao;
	
	private plannedDao planneddao;
	
	private PatrolRecordDao patrolrecorddao;
	
	public MaintenanceDao getMaintenancedao() {
		return maintenancedao;
	}

	public void setMaintenancedao(MaintenanceDao maintenancedao) {
		this.maintenancedao = maintenancedao;
	}

	public QueryCentenrDao getQueryCentenrdao() {
		return queryCentenrdao;
	}

	public void setQueryCentenrdao(QueryCentenrDao queryCentenrdao) {
		this.queryCentenrdao = queryCentenrdao;
	}
	
	public void setRuntimedatedao(RunTimeDateDao runtimedatedao) {
		this.runtimedatedao = runtimedatedao;
	}
	
	public void setMaintaindao(MaintainDao maintaindao){
		this.maintaindao = maintaindao;
	}
	
	public void setWaterpumpdao(WaterPumpDao waterpumpdao) {
		this.waterpumpdao = waterpumpdao;
	}
	
	public void setWeeklydao(WeeklyDao weeklydao) {
		this.weeklydao = weeklydao;
	}

	public void setPrintdao(PrintDao printdao) {
		this.printdao = printdao;
	}
	

	public void setAirrecorddao(AirRecordDao airrecorddao) {
		this.airrecorddao = airrecorddao;
	}

	//根据时间抓gpd
	public List<String> Patrol(String date1, String date2,String gpd,String jlg,String fdj) throws Exception {
		return this.printdao.Patrol(date1, date2,gpd,jlg,fdj);
	}
	
	//通过Id获取到对应的数据
	public List<Inspection> PrintFunction(String b,String gpd,String jlg,String fdj) throws Exception {
		return this.printdao.PrintFunction(b,gpd,jlg,fdj);
	}
		//gpd读取电子档并创建一个新的电子档
		public void Folder(String date1,List<Inspection> list,int i) throws Exception {
			String G1 = " ";//1#变压器功率因素	
			String G2 = " ";//2#变压器功率因素
			String G3 = " ";//3#变压器功率因素
			String G4 = " ";//4#变压器功率因素
			String G5 = " ";//5#变压器功率因素
			
			String D1 = " ";//1#进线电压记录
			String D2 = " ";//2#进线电压记录
			String D3 = " ";//3#进线电压记录
			String D4 = " ";//4#进线电压记录
			String D5 = " ";//5#进线电压记录
			
			String L1 = " ";//1#进线电流记录
			String L2 = " ";//2#进线电流记录
			String L3 = " ";//3#进线电流记录
			String L4 = " ";//4#进线电流记录
			String L5 = " ";//5#进线电流记录
			
			String W1 = " ";//1#变压器温度记录
			String W2 = " ";//2#变压器温度记录
			String W3 = " ";//3#变压器温度记录
			String W4 = " ";//4#变压器温度记录	
			String W5 = " ";//5#变压器温度记录
			
			
			// 源File对象
	        File source = new File("D:/Folder/Power/gpd.xls");
	        String ng = date1.split(" ")[0].replace("-", "/");
	    	String url = "D:/Folder/Power/gpd供配电"+ng+".xls";
	    	// 备份的File对象
	        File target = new File(url);
	        if(!target.exists()){
	        	 //通过JVM读取java.io.tmpdir属性取得临时文件夹
			    File targetDir = new File(System.getProperty("java.io.tmpdir"));
			    //在同一个文件夹复制文件
			    FileUtils.copyFile(source, target);
			    // 根据指定的文件夹复制
			    FileUtils.copyFileToDirectory(source, targetDir);
			
	        }
			
	        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(target));
			HSSFSheet sheet=wb.getSheetAt(0);
			HSSFRow row=sheet.getRow(6+i);
			
	        for(Inspection li : list){
	        	HSSFRow row1 =sheet.getRow(2);
		        HSSFCell cellp =row1.getCell((int) 0);
		        cellp.setCellValue("日期:"+li.getB().split(" ")[0]);
		        //时间
	        	HSSFCell cell=row.getCell((int) 0);
				cell.setCellValue(li.getB().split(" ")[1].split(":")[0]+":"+li.getB().split(" ")[1].split(":")[1]);		
	        	if(li.getC().equals("1")){
	        		if(li.getId().equals("DF-QD-02-101")){
		        		W1 = W1 +li.getTransformer();
		        		G1 = G1 +li.getPowerFactor();
		        		L1 = L1 +li.getVoltage();
		        		D1 = D1 +li.getElectric();
		        		HSSFCell cell16 =row.getCell((int)16);
		    			cell16.setCellValue(li.getPatrolMan());
		        	}else if(li.getId().equals("DF-QD-02-201")){
		        		L2 = L2 +li.getVoltage();
		        		D2 = D2 +li.getElectric();
		        		W2 = W2 +li.getTransformer();
		        		G2 = G2 +li.getPowerFactor();
		        		HSSFCell cell16 =row.getCell((int)16);
		    			cell16.setCellValue(li.getPatrolMan());
		        	}else if(li.getId().equals("DF-QD-02-301")){
		        		L3 = L3 +li.getVoltage();
		        		D3 = D3 +li.getElectric();
		        		W3 = W3 +li.getTransformer();
		        		G3 = G3 +li.getPowerFactor();
		        		HSSFCell cell16 =row.getCell((int)16);
		    			cell16.setCellValue(li.getPatrolMan());
		        	}else if(li.getId().equals("DF-QD-02-401")){
		        		L4 = L4 +li.getVoltage();
		        		W4 = W4 +li.getTransformer();
		        		G4 = G4 +li.getPowerFactor();
		        		D4 = D4 +li.getElectric();
		        		HSSFCell cell16 =row.getCell((int)16);
		    			cell16.setCellValue(li.getPatrolMan());
		        	}else if(li.getId().equals("DF-QD-02-501")){
		        		L5 = L5 +li.getVoltage();
		        		D5 = D5 +li.getElectric();
		        		W5 = W5 +li.getTransformer();
		        		G5 = G5 +li.getPowerFactor();
		        		HSSFCell cell16 =row.getCell((int)16);
		    			cell16.setCellValue(li.getPatrolMan());
		        	}else if(li.getId().equals("DF-QD-01-11")){
		        		HSSFCell cell13 =row.getCell((int)13);
		    			cell13.setCellValue(li.getElectricAA());
		    			HSSFCell cell16 =row.getCell((int)16);
		    			cell16.setCellValue(li.getPatrolMan());
		        	}else if(li.getId().equals("DF-QD-01-13")){
		        		HSSFCell cell14 =row.getCell((int)14);
		    			cell14.setCellValue(li.getElectricAA());
		    			HSSFCell cell16 =row.getCell((int)16);
		    			cell16.setCellValue(li.getPatrolMan());
		        	}else if(li.getId().equals("DF-QD-02-001")){
		        		HSSFCell cell15 =row.getCell((int)15);
		    			cell15.setCellValue(li.getBattery());
		    			HSSFCell cell16 =row.getCell((int)16);
		    			cell16.setCellValue(li.getPatrolMan());
		        	}
	        		//记录人
	    			
	        	}
	        }
	        
			//1,2号变压器功能因素
			HSSFCell cell1=row.getCell((int) 1);
			cell1.setCellValue(G1+"/"+G2);
			//3,4号变压器功能因素
			HSSFCell cell2=row.getCell((int) 2);
			cell2.setCellValue(G3+"/"+G4);
			//5号变压器功能因素
			HSSFCell cell3 =row.getCell((int) 3);
			cell3.setCellValue(G5);
			//1,2进线电压
			HSSFCell cell4 =row.getCell((int)4);
			cell4.setCellValue(L1+"/"+L2);
			//3,4进线电压
			HSSFCell cell5 =row.getCell((int)5);
			cell5.setCellValue(L3+"/"+L4);
			//5进线电压
			HSSFCell cell6 =row.getCell((int)6);
			cell6.setCellValue(L5);
			//1,2进线电流
			HSSFCell cell7 =row.getCell((int)7);
			cell7.setCellValue(D1+"/"+D2);
			//3,4进线电流
			HSSFCell cell8 =row.getCell((int)8);
			cell8.setCellValue(D3+"/"+D4);
			//5进线电流
			HSSFCell cell9 =row.getCell((int)9);
			cell9.setCellValue(D5);
			//1,2温度
			HSSFCell cell10 =row.getCell((int)10);
			cell10.setCellValue(W1+"/"+W2);
			//3,4温度
			HSSFCell cell11 =row.getCell((int)11);
			cell11.setCellValue(W3+"/"+W4);
			//5温度
			HSSFCell cell12 =row.getCell((int)12);
			cell12.setCellValue(W5);
			
			FileOutputStream os = new FileOutputStream(target);
			wb.write(os);
			os.close();
		}
	
		
		
		//空调获取到全部id
		public List<String> Patrolkt(String date1, String date2,String kt,String id)throws Exception {
			return this.airrecorddao.Patrolkt(date1, date2,kt,id);
		}
		
		//空调通过Id获取到对应的数据
		public List<Inspection> Patrolktdata(String b,String id) throws Exception {
			return this.airrecorddao.Patrolktdata(b,id);
		}
		
		//空调
		public void PatrolFolder(List<Inspection>list,int i,String date1,String id) throws IOException{
			if(!list.isEmpty()){
				String d = id.split("00")[1];
				date1 = date1.split(" ")[0];
				 // 源File对象
		        File source = new File("D:/Folder/AirConditioner/kt.xls");
		        String date = date1.replace("-", "/");
		    	String url = "D:/Folder/AirConditioner/kt--"+d+"空调"+date+".xls";
		    	// 备份的File对象
		        File target = new File(url);
		        if(!target.exists()){
		        	 //通过JVM读取java.io.tmpdir属性取得临时文件夹
				    File targetDir = new File(System.getProperty("java.io.tmpdir"));
				    //在同一个文件夹复制文件
				    FileUtils.copyFile(source, target);
				    // 根据指定的文件夹复制
				    FileUtils.copyFileToDirectory(source, targetDir);
		        }
				for(Inspection li : list){
					HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(url));
					HSSFSheet sheet=wb.getSheetAt(0);
					HSSFRow row=sheet.getRow(4+i);
					HSSFRow row1=sheet.getRow(1);
					HSSFCell cel=row1.getCell((int) 10);
					HSSFCell kt =row1.getCell((int) 27);
					kt.setCellValue("日期:"+li.getB().split(" ")[0]);
					//时间
					HSSFCell cell0=row.getCell((int) 0);
					cell0.setCellValue(li.getB().split(" ")[1].split(":")[0]+":"+li.getB().split(" ")[1].split(":")[1]);
					if(li.getC().equals("1")){
						if(li.getId().equals("DF-KT-40-003")){
							cel.setCellValue("3#");
							//冷冻出水温度
							HSSFCell cell=row.getCell((int) 1);
							cell.setCellValue(li.getChilledWater());
							//冷冻进水温度
							HSSFCell cell1=row.getCell((int) 2);
							cell1.setCellValue(li.getChilledWaterA());
							//冷冻进水压力
							HSSFCell cell2=row.getCell((int) 3);
							cell2.setCellValue(li.getFreezinginletPressure());
							//冷冻水出水压力
							HSSFCell cell3=row.getCell((int) 4);
							cell3.setCellValue(li.getFreezingWaterPressure());
							//冷却出水温度
							HSSFCell cell4=row.getCell((int)5); 
							cell4.setCellValue(li.getCoolingWaterTemperature());
							//冷却进水温度
							HSSFCell cell5=row.getCell((int)6);
							cell5.setCellValue(li.getCoolingInletTemperature());
							//冷却进水压力
							HSSFCell cell6=row.getCell((int)7);
							cell6.setCellValue(li.getCoolingInletPressure());
							//冷却出水压力
							HSSFCell cell7=row.getCell((int)8);
							cell7.setCellValue(li.getCoolingWaterPressure());
							//1号机
							//吸气压力
							HSSFCell cell14=row.getCell((int)15);
							cell14.setCellValue(li.getInspiratory());
							//蒸发温度
							HSSFCell cell15=row.getCell((int)16);
							cell15.setCellValue(li.getEvaporating());
							//排气压力
							HSSFCell cell16=row.getCell((int)17);
							cell16.setCellValue(li.getExhaustPressure());
							//冷凝温度
							HSSFCell cell17=row.getCell((int)18);
							cell17.setCellValue(li.getCondensing());
							//油压差
							HSSFCell cell20=row.getCell((int)19);
							cell20.setCellValue(li.getPressureDifference());
							//2号机
							//吸气压力
							HSSFCell cell21=row.getCell((int)20);
							cell21.setCellValue(li.getInspiratory1());
							//蒸发温度
							HSSFCell cell22=row.getCell((int)21);
							cell22.setCellValue(li.getEvaporating1());
							//排气压力
							HSSFCell cell23=row.getCell((int)22);
							cell23.setCellValue(li.getExhaustPressure1());
							//冷凝温度
							HSSFCell cell24=row.getCell((int)23);
							cell24.setCellValue(li.getCondensing1());
							//油压差
							HSSFCell cell27=row.getCell((int)24);
							cell27.setCellValue(li.getPressureDifference1());
							//3号机
							//吸气压力
							HSSFCell cell28=row.getCell((int)25);
							cell28.setCellValue(li.getInspiratory2());
							//蒸发温度
							HSSFCell cell29=row.getCell((int)26);
							cell29.setCellValue(li.getEvaporating2());
							//排气压力
							HSSFCell cell30=row.getCell((int)27);
							cell30.setCellValue(li.getExhaustPressure2());
							//冷凝温度 	
							HSSFCell cell31=row.getCell((int)28);
							cell31.setCellValue(li.getCondensing2());
							//油压差
							HSSFCell cell34=row.getCell((int)29);
							cell34.setCellValue(li.getPressureDifference2());
							//记录人
							HSSFCell cell35=row.getCell((int)30);
							cell35.setCellValue(li.getPatrolMan());
						}else if(id.equals("DF-KT-40-001")){
							cel.setCellValue("1#");
							//冷冻出水温度
							HSSFCell cell=row.getCell((int) 1);
							cell.setCellValue(li.getChilledWater());
							//冷冻进水温度
							HSSFCell cell1=row.getCell((int) 2);
							cell1.setCellValue(li.getChilledWaterA());
							//冷冻进水压力
							HSSFCell cell2=row.getCell((int) 3);
							cell2.setCellValue(li.getFreezinginletPressure());
							//冷冻水出水压力
							HSSFCell cell3=row.getCell((int) 4);
							cell3.setCellValue(li.getFreezingWaterPressure());
							//冷却出水温度
							HSSFCell cell4=row.getCell((int)5);
							cell4.setCellValue(li.getCoolingWaterTemperature());
							//冷却进水温度
							HSSFCell cell5=row.getCell((int)6);
							cell5.setCellValue(li.getCoolingInletTemperature());
							//冷却进水压力
							HSSFCell cell6=row.getCell((int)7);
							cell6.setCellValue(li.getCoolingInletPressure());
							//冷却出水压力
							HSSFCell cell7=row.getCell((int)8);
							cell7.setCellValue(li.getCoolingWaterPressure());
							//线电压
							HSSFCell cell8=row.getCell((int)9);
							cell8.setCellValue(li.getLineVoltage());
							//线电流
							HSSFCell cell9=row.getCell((int)10);
							cell9.setCellValue(li.getLineCurrent());
							//电机温度
							HSSFCell cell10=row.getCell((int)11);
							cell10.setCellValue(li.getMotorTemperature());
							//导叶开启度
							HSSFCell cell11=row.getCell((int)12);
							cell11.setCellValue(li.getGuideVane());
							//油温
							HSSFCell cell12=row.getCell((int)13);
							cell12.setCellValue(li.getOilTemperature());
							//压缩机(离心机)排气温度
							HSSFCell cell99=row.getCell((int)14);
							cell99.setCellValue(li.getLeaveExhaustPressure());
							//1号机
							//吸气压力
							HSSFCell cell18=row.getCell((int)15);
							cell18.setCellValue(li.getInspiratory());
							//蒸发温度
							HSSFCell cell19=row.getCell((int)16);
							cell19.setCellValue(li.getEvaporating());
							//排气压力
							HSSFCell cell20=row.getCell((int)17);
							cell20.setCellValue(li.getExhaustPressure());
							//冷凝温度
							HSSFCell cell21=row.getCell((int)18);
							cell21.setCellValue(li.getCondensing());
							//油压差
							HSSFCell cell24=row.getCell((int)19);
							cell24.setCellValue(li.getPressureDifference());
							//记录人
							HSSFCell cell35=row.getCell((int)30);
							cell35.setCellValue(li.getPatrolMan());
						}else if(id.equals("DF-KT-40-002")){
							cel.setCellValue("2#");
							//冷冻出水温度
							HSSFCell cell=row.getCell((int) 1);
							cell.setCellValue(li.getChilledWater());
							//冷冻进水温度
							HSSFCell cell1=row.getCell((int) 2);
							cell1.setCellValue(li.getChilledWaterA());
							//冷冻进水压力
							HSSFCell cell2=row.getCell((int) 3);
							cell2.setCellValue(li.getFreezinginletPressure());
							//冷冻水出水压力
							HSSFCell cell3=row.getCell((int) 4);
							cell3.setCellValue(li.getFreezingWaterPressure());
							//冷却出水温度
							HSSFCell cell4=row.getCell((int)5);
							cell4.setCellValue(li.getCoolingWaterTemperature());
							//冷却进水温度
							HSSFCell cell5=row.getCell((int)6);
							cell5.setCellValue(li.getCoolingInletTemperature());
							//冷却进水压力
							HSSFCell cell6=row.getCell((int)7);
							cell6.setCellValue(li.getCoolingInletPressure());
							//冷却出水压力
							HSSFCell cell7=row.getCell((int)8);
							cell7.setCellValue(li.getCoolingWaterPressure());
							//线电压
							HSSFCell cell8=row.getCell((int)9);
							cell8.setCellValue(li.getLineVoltage());
							//线电流
							HSSFCell cell9=row.getCell((int)10);
							cell9.setCellValue(li.getLineCurrent());
							//电机温度
							HSSFCell cell10=row.getCell((int)11);
							cell10.setCellValue(li.getMotorTemperature());
							//导叶开启度
							HSSFCell cell11=row.getCell((int)12);
							cell11.setCellValue(li.getGuideVane());
							//油温
							HSSFCell cell12=row.getCell((int)13);
							cell12.setCellValue(li.getOilTemperature());
							//压缩机(离心机)排气温度
							HSSFCell cell99=row.getCell((int)14);
							cell99.setCellValue(li.getLeaveExhaustPressure());
							//1号机
							//吸气压力
							HSSFCell cell18=row.getCell((int)15);
							cell18.setCellValue(li.getInspiratory());
							//蒸发温度
							HSSFCell cell19=row.getCell((int)16);
							cell19.setCellValue(li.getEvaporating());
							//排气压力
							HSSFCell cell20=row.getCell((int)17);
							cell20.setCellValue(li.getExhaustPressure());
							//冷凝温度
							HSSFCell cell21=row.getCell((int)18);
							cell21.setCellValue(li.getCondensing());
							//油压差
							HSSFCell cell24=row.getCell((int)19);
							cell24.setCellValue(li.getPressureDifference());
							//记录人
							HSSFCell cell35=row.getCell((int)30);
							cell35.setCellValue(li.getPatrolMan());
						}
					}
					FileOutputStream os = new FileOutputStream(url);
					wb.write(os);
					os.close();
				}
			}
			
		}

		
		//抓取巡检id
		public List<String> WaterPumpgps(String date1, String date2,
				String gps) throws Exception {
			return this.waterpumpdao.WaterPumpgps(date1, date2,gps);
		}


		public List<Inspection> WaterPumpid(String b,String gps) throws Exception {
			return this.waterpumpdao.WaterPumpid(b,gps);
		}


		public void WaterPump(List<Inspection> list, int i, String date1) throws IOException {
			if(!list.isEmpty()){
				// 源File对象
		        File source = new File("D:/Folder/WaterPump/gps.xls");
		        String ng = date1.split(" ")[0].replace("-", "/");
		    	String url = "D:/Folder/WaterPump/gps给排水"+ng+".xls";
		    	// 备份的File对象
		        File target = new File(url);
		        if(!target.exists()){
		        	 //通过JVM读取java.io.tmpdir属性取得临时文件夹
				    File targetDir = new File(System.getProperty("java.io.tmpdir"));
				    //在同一个文件夹复制文件
				    FileUtils.copyFile(source, target);
				    // 根据指定的文件夹复制
				    FileUtils.copyFileToDirectory(source, targetDir);
		        }
		        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(target));
				HSSFSheet sheet=wb.getSheetAt(0);
				HSSFRow row=sheet.getRow(5+i);
				HSSFSheet sheet1=wb.getSheetAt(0);
				HSSFRow row1=sheet1.getRow(2);
				HSSFCell celp=row1.getCell((int) 6);
				for(Inspection li : list){
					HSSFCell cel=row.getCell((int) 0);
					cel.setCellValue(li.getB().split(" ")[1].split(":")[0]+":"+li.getB().split(" ")[1].split(":")[1]);
					celp.setCellValue("日期:"+li.getB().split(" ")[0]);
					if(li.getC().equals("1")){
						if(li.getId().equals("DF-GP-30-001")){
							HSSFCell cel1=row.getCell((int) 1);
							cel1.setCellValue(li.getPressure());
							HSSFCell cel2=row.getCell((int) 2);
							cel2.setCellValue(li.getWaterlevel());
						}else if(li.getId().equals("DF-GP-32-005")){
							HSSFCell cel3=row.getCell((int) 3);
							cel3.setCellValue(li.getPressure());
							HSSFCell cel4=row.getCell((int) 4);
							cel4.setCellValue(li.getWaterlevel());
						}else if(li.getId().equals("DF-GP-35-030")){
							HSSFCell cel5=row.getCell((int) 5);
							cel5.setCellValue(li.getPressure());
							HSSFCell cel6=row.getCell((int) 6);
							cel6.setCellValue(li.getWaterlevel());
						}
						HSSFCell cel7=row.getCell((int) 7);
						cel7.setCellValue(li.getPatrolMan());
					}
				}
				FileOutputStream os = new FileOutputStream(url);
				wb.write(os);
				os.close();
			}
		}
		//抓取当天的数据
		public List<Dianlang> Weekly(String date) throws Exception {
			return this.weeklydao.Weekly(date);
		}
		//抓取1号或周一的数据
		public List<Dianlang> WeeklyUpper(String str) throws Exception {
			return this.weeklydao.weeklydao(str);
		}

		public void Patrolk(String name,String i) throws Exception {
			this.patrolkdao.patrolk(name,i);
		}



		public void setPatrolkdao(PatrolkDao patrolkdao) {
			this.patrolkdao = patrolkdao;
		}
		
		
		//获取所以日期
		public List<com.pp.test.bo.Patrol> plank() throws Exception {
			return this.patrolkdao.plank();
		}
		//通过数据抓取询价目录
		public List<String> CatalogData() throws Exception {
			return this.patrolkdao.CatalogData();
		}

		
		

		//查询出设备
		public List<com.pp.test.bo.RunTimeDate> MaintainName() throws Exception{
			return this.runtimedatedao.MaintainName();
		}
		
		//获取需要保养的设备
		public List<RunTimeDate> MaintainQueryName() throws Exception {
			return this.runtimedatedao.MaintainQueryName();
		}


		
		//
		//
		public SourceDao getSourcedao() {
			return sourcedao;
		}

		public void setSourcedao(SourceDao sourcedao) {
			this.sourcedao = sourcedao;
		}		
		
		public List<String> MainName() throws Exception {
			return sourcedao.MainName();
		}

		public List<Source> MainDeviceName(String name) throws Exception {
			return sourcedao.MainDeviceName(name);
		}

		
		//
		public PlancontentDao getPlancontentdao() {
			return plancontentdao;
		}

		public void setPlancontentdao(PlancontentDao plancontentdao) {
			this.plancontentdao = plancontentdao;
		}

		
		
		/////
		public List<String> PlancontentName() throws Exception {
			return this.plancontentdao.planName();
		}
		public List<Plancontent> PlancontentName2(String name1) throws Exception {
			return this.plancontentdao.planName2(name1);
		}
		public List<String> PlancontentName3(String name2) throws Exception {
			return this.plancontentdao.planName3(name2);
		}
		public List<String> PlancontentName4(String name) throws Exception {
			return this.plancontentdao.planName4(name);
		}

		public List<Centent> QueryCentent(String name) throws Exception {
			return this.queryCentenrdao.QueryCentenr(name);
		}

		public void updatacentent(String name,String id,String arr) throws Exception {
			this.queryCentenrdao.updatecentent(name, id, arr);
		}

		
		public void addcontent(Centent s) throws Exception {
			this.queryCentenrdao.addcontent(s);
		}

		public List<Source> QuerySource(String name) throws Exception {
			return this.sourcedao.QuerySource(name);
		}

		
		
		public void addMaintain(MaintenancePlan plan) throws Exception {
			this.maintaindao.addMaintain(plan);
		}

		public List<String> QueryMaintenancePlan(String a,String b) throws Exception {
			return this.maintaindao.querymaintain(a,b);
			
		}

		//获取主页面
		public List<MaintenancePlan> Display() throws Exception {
			return this.maintaindao.Display();
		}
		//修改运行时间
		public void Updatamainrun(MaintenancePlan l) throws SQLException {
			this.maintaindao.Updatamainrun(l);
		}
		
		public CententDao getCententdao() {
			return cententdao;
		}

		public void setCententdao(CententDao cententdao) {
			this.cententdao = cententdao;
		}
		//添加保养内容
		public void addcontenr(Centent cen) throws Exception {
			this.cententdao.AddCentent(cen);
		}
		
		//名字查询id
		public String NameQueryId(String a) throws Exception{
			return this.sourcedao.NameQueryId(a);
		}
		//删除
		public void exectiondelete(String id,String con) throws Exception {
			this.maintaindao.exectiondelete(id,con);
		}
		//查询2
		public List<Maintenance> Query2(String id)throws Exception{
			return this.maintenancedao.Query2(id);
		}

		public List<Maintenance> queryzhiding(String id,
				String content) throws Exception {
			return this.maintenancedao.queryzhiding(id, content);
		}

		public void addMaintenance(Maintenance main) throws Exception {
			this.maintenancedao.addMaintenance(main);
		}

		public String Maxdata(String id,String con) throws Exception {
			return this.maintenancedao.querymax(id,con);
		}
		
		public List<String> querymain() throws Exception {
			return this.maintenancedao.querymain();
		}

		public String querycent(String name) throws Exception {
			return this.sourcedao.queyrcent(name);
		}


		public plannedDao getPlanneddao() {
			return planneddao;
		}

		public void setPlanneddao(plannedDao planneddao) {
			this.planneddao = planneddao;
		}
		
		public List<planned> queryPlanned() {
			return this.planneddao.queryPlanned();
		}

		public void addPlanned(Inspection ti) {
	
			this.printdao.addPlanned(ti);
		}

		public String querySourceName(String id) {
			return this.sourcedao.querySourceName(id);
		}

		public void addPlan(String id, String unid, String name) {
			this.planneddao.updatePlan(id,unid,name);
		}

		public String querysheb(String id) {
			return this.sourcedao.querysheb(id);
		}

		public List<planned> queryData(String id) {
			return this.planneddao.queryData(id);
		}

		public void updataData(String data1, String data2,String id) {
			this.planneddao.updataData(data1,data2,id);
		}

		public String QueryCentent2(String name, String id) {
			return this.maintenancedao.QueryCentent2(name, id);
		}

		public void updataContent2(String name, String data, String value) {
			this.maintenancedao.updataContent2(name,data,value);
		}

		public Maintenance QueryMaintain(String date, String month) {
			return this.maintenancedao.QueryMain(date,month);
		}
		public int judge(String a,String category) {
			return this.maintaindao.judge(a,category);
		}

		public void queryMaintenanceUpdata(String date) {
			this.maintenancedao.queryMaintenanceUpdate(date);
		}
		public void queryMaintenanceUpdata1(String date,String date1){
			this.maintenancedao.queryMaintenanceUpdate1(date,date1);
		}

		public PatrolRecordDao getPatrolrecorddao() {
			return patrolrecorddao;
		}

		public void setPatrolrecorddao(PatrolRecordDao patrolrecorddao) {
			this.patrolrecorddao = patrolrecorddao;
		}

		public PatrolRecord queryPatrolRecord(String unitId,String startdate,String index) {
			return this.patrolrecorddao.queryPatrolRecord(unitId,startdate,index);
		}

		public void addRecord(PatrolRecord patrol) {
			this.patrolrecorddao.addRecord(patrol);
		}

		public void deleteMaintenance(String name, String id) {
			this.maintenancedao.deleteMaintenance(name, id);
		}

		//查询2
		public List<Maintenance> Query3(String id)throws Exception{
			return this.maintenancedao.Query3(id);
		}
}
