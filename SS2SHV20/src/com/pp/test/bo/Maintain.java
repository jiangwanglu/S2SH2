package com.pp.test.bo;

import java.io.Serializable;

public class Maintain implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;//id
	private String name;//设备名
	private String rundate;//运行时间
	private String upper;//上一次保养时间
	private String lower;//下一次保养时间
	private int state;//状态0:未到保养时间,1:需要保养
	private String button;//用来添加按钮
	

	public String getUpper() {
		return upper;
	}
	public void setUpper(String upper) {
		this.upper = upper;
	}
	public String getRundate() {
		return rundate;
	}
	public void setRundate(String rundate) {
		this.rundate = rundate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getLower() {
		return lower;
	}
	public void setLower(String lower) {
		this.lower = lower;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	
}