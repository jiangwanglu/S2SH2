package com.pp.test.bo;

import java.io.Serializable;

public class Maintenance implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;					//id
	private String unitid;			//设备id
	private String executiondata;	//执行日期
	private String dateofexecution; //实际执行日期
	private String enddate;			//结束日期
	private String whether;			//是否执行
	private String implementation;	//执行情况
	private String maintenancecategory;//名称
	private String content;			//内容
	private String degree;			//月度,季度,年度
	private String executor;		//执行人
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getExecutiondata() {
		return executiondata;
	}
	public void setExecutiondata(String executiondata) {
		this.executiondata = executiondata;
	}
	public String getDateofexecution() {
		return dateofexecution;
	}
	public void setDateofexecution(String dateofexecution) {
		this.dateofexecution = dateofexecution;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getWhether() {
		return whether;
	}
	public void setWhether(String whether) {
		this.whether = whether;
	}
	public String getImplementation() {
		return implementation;
	}
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}
	public String getMaintenancecategory() {
		return maintenancecategory;
	}
	public void setMaintenancecategory(String maintenancecategory) {
		this.maintenancecategory = maintenancecategory;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
}