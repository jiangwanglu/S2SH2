package com.pp.test.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.pp.test.bo.Dianlang;
import com.pp.test.service.LoadDataService;
@Component
public class Month {
	@Autowired
	public LoadDataService loadDataService;

	public void setLoadDataService(LoadDataService loadDataService) {
		this.loadDataService = loadDataService;
	}
	
	@Scheduled(cron="0 0 0 1 * ?")
	protected void executeInternal()
			throws JobExecutionException {
		Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        String s = new SimpleDateFormat("yyyy/MM/").format(c.getTime())+"01";
        
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format.format(date);
		List<Dianlang>li;
		List<Dianlang>t;
		try {
			
			li =  loadDataService.Weekly(date1);
			t =  loadDataService.WeeklyUpper(s);

			for(Dianlang list : li){
		        File source = new File("D:/Folder/Month/Month.xls");
		        String ng = date1.split(" ")[0].replace("-", "/");
		    	String url = "D:/Folder/Month/月用电量"+ng+".xls";
		        File target = new File(url);
		        if(!target.exists()){
				    File targetDir = new File(System.getProperty("java.io.tmpdir"));
				    FileUtils.copyFile(source, target);
				    FileUtils.copyFileToDirectory(source, targetDir);
		        }
		        for(Dianlang l : t){
		        	HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(target));
					HSSFSheet sheet=wb.getSheetAt(0);
					
					HSSFCellStyle  style = wb.createCellStyle();      
			        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中 
			        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
			        HSSFFont font = wb.createFont();  
			        font.setFontName("黑体");  
			        font.setFontHeightInPoints((short) 16);
			        style.setFont(font);
					//1号时间
					HSSFRow row=sheet.getRow(4);
					HSSFCell cell =row.getCell((int)0);
					cell.setCellValue(list.getDate());
					//1号电度读数
					HSSFCell cel1 =row.getCell((int)2);
					cel1.setCellValue(list.getNh1());
					//1号用电量
					HSSFCell e =row.getCell((int)3);
					e.setCellValue(list.getNh1()-l.getNh1());
					HSSFRow row9=sheet.getRow(4);
					HSSFCell o =row9.getCell((int)4);
					o.setCellStyle(style); 
					o.setCellValue(list.getNh() - l.getNh());
					
					//2号时间
					HSSFRow row1=sheet.getRow(5);
					HSSFCell cell1 =row1.getCell((int)0);
					cell1.setCellValue(list.getDate());
					//2号电度读数
					HSSFCell cel2 =row1.getCell((int)2);
					cel2.setCellValue(list.getNh2());
					//1号用电量
					HSSFCell e1 = row1.getCell((int)3);
					e1.setCellValue(list.getNh2()-l.getNh2());
					
					//3号时间
					HSSFRow row2=sheet.getRow(6);
					HSSFCell cell2 =row2.getCell((int)0);
					cell2.setCellValue(list.getDate());
					//3号电度读数
					HSSFCell cel3 =row2.getCell((int)2);
					cel3.setCellValue(list.getNh3());
					//1号用电量
					HSSFCell e3 =row2.getCell((int)3);
					e3.setCellValue(list.getNh3()-l.getNh3());
					
					
					//4号时间
					HSSFRow row3=sheet.getRow(7);
					HSSFCell cell3 =row3.getCell((int)0);
					cell3.setCellValue(list.getDate());
					//4号电度读数
					HSSFCell cel4 =row3.getCell((int)2);
					cel4.setCellValue(list.getNh4());
					//4号用电量
					HSSFCell e4 =row3.getCell((int)3);
					e4.setCellValue(list.getNh4()-l.getNh4());
					
					
					//5号时间
					HSSFRow row4=sheet.getRow(8);
					HSSFCell cell4 =row4.getCell((int)0);
					cell4.setCellValue(list.getDate());
					//5号电度读数
					HSSFCell cel5 =row4.getCell((int)2);
					cel5.setCellValue(list.getNh5());
					//5号用电量
					HSSFCell e5 =row4.getCell((int)3);
					e5.setCellValue(list.getNh5()-l.getNh5());
					
					
					FileOutputStream os = new FileOutputStream(target);
					wb.write(os);
					os.close();
		        } 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			c = null;
			li = null;
			t = null;
		}
	}
}