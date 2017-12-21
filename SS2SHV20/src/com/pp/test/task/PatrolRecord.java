package com.pp.test.task;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pp.test.service.LoadDataService;
@Component
public class PatrolRecord {
	@Autowired
	public LoadDataService loadDataService;
	public void setLoadDataService(LoadDataService loadDataService) {
		this.loadDataService = loadDataService;
	}
	@Scheduled(cron="0 42 23 * * ?")
	public void generate() throws Exception{
		File filename = null;
		String unitId = null;
		String url = null;
		String userName = null;
		for(int i = 0;i < 5;i++){
			if(i == 0){
				filename = new File("D:/Folder/Record/KT/FM-GC-006-001KT.xlt");
				unitId = "DF-KT-KTJF";//空调机房
				userName = "空调机房";
				url = "D:/Folder/Record/KT/";
			}else if(i == 1){
				filename = new File("D:/Folder/Record/SHBF/FM-GC-006-001SHBF.xlt");
				unitId = "DF-GP-SHBF";//生活泵房
				userName = "生活泵房";
				url = "D:/Folder/Record/SHBF/";
			}else if(i == 2){
				filename = new File("D:/Folder/Record/WYBF/FM-GC-006-001WYBF.xlt");
				unitId = "DF-RD-WYBF";//稳压泵房
				userName = "稳压泵房";
				url = "D:/Folder/Record/WYBF/";
			}else if(i == 3){
				filename = new File("D:/Folder/Record/XFBF/FM-GC-006-001XFBF.xlt");
				unitId = "DF-RD-XFBF";//消防泵房
				userName = "消防泵房";
				url = "D:/Folder/Record/XFBF/";
			}else if(i == 4){
				filename = new File("D:/Folder/Record/XFZX/FM-GC-006-001XFZX.xlt");
				unitId = "DF-RD-XFZX";//消防中心
				userName = "消防中心";
				url = "D:/Folder/Record/XFZX/";
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date1 = df.format(new Date());
			String ng = date1.split(" ")[0].replace("-", "/");
			// 备份的File对象
			File target = new File(url+ng.split("/")[0]+"/"+ng.split("/")[1]+"月.xls");
			if(!target.exists()){
				File targetDir = new File(System.getProperty("java.io.tmpdir"));
				FileUtils.copyFile(filename, target);
				FileUtils.copyFileToDirectory(filename, targetDir);
			}
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(target));
			HSSFSheet sheet=wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			int index=0;
			
			com.pp.test.bo.PatrolRecord record = this.loadDataService.queryPatrolRecord(unitId,date1.split("-")[0]+"-"+date1.split("-")[1],date1.split("-")[2]);
			row =sheet.getRow(2);
			cell =row.getCell((int) 0);
			
			cell.setCellValue(userName+"巡检----日期:"+date1.split("-")[0]+"-"+date1.split("-")[1]);
			
			if(record != null){
				if(record.getType().equals("1")){
					row =sheet.getRow(4+index);
					cell =row.getCell((int) record.getDay());
					cell.setCellValue("√");
					index ++;
				}else{
					index ++;
				}
				
				if(record.getType1().equals("1")){
					row =sheet.getRow(4+index);
					cell =row.getCell((int) record.getDay());
					cell.setCellValue("√");
					index ++;
				}else{
					index ++;
				}
				
				if(record.getType2().equals("1")){
					row =sheet.getRow(4+index);
					cell =row.getCell((int) record.getDay());
					cell.setCellValue("√");
					index ++;
				}else{
					index ++;
				}
				
				if(record.getType3().equals("1")){
					row =sheet.getRow(4+index);
					cell =row.getCell((int) record.getDay());
					cell.setCellValue("√");
					index ++;
				}else{
					index ++;
				}
				
				if(record.getType4().equals("1")){
					row =sheet.getRow(4+index);
					cell =row.getCell((int) record.getDay());
					cell.setCellValue("√");
					index ++;
				}else{
					index ++;
				}
				
				if(record.getType5().equals("1")){
					row =sheet.getRow(4+index);
					cell =row.getCell((int) record.getDay());
					cell.setCellValue("√");
					index ++;
				}else{
					index ++;
				}
				
				if(record.getType6().equals("1")){
					row =sheet.getRow(4+index);
					cell =row.getCell((int) record.getDay());
					cell.setCellValue("√");
					index ++;
				}else{
					index ++;
				}
				
				if(record.getType7().equals("1")){
					row =sheet.getRow(4+index);
					cell =row.getCell((int) record.getDay());
					cell.setCellValue("√");
					index ++;
				}else{
					index ++;
				}
				
				if(record.getType8().equals("1")){
					row =sheet.getRow(4+index);
					cell =row.getCell((int) record.getDay());
					cell.setCellValue("√");
					index ++;
				}else{
					index ++;
				}
				row =sheet.getRow(13);
				cell =row.getCell((int) record.getDay());
				cell.setCellValue(record.getName());
			}else{
				
			}
			FileOutputStream os = new FileOutputStream(target);
			wb.write(os);
			os.close();
		}
	}
}