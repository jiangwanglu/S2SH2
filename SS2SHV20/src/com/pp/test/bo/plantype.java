package com.pp.test.bo;

import java.io.Serializable;

public class plantype implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((unitid == null) ? 0 : unitid.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result
				+ ((valuename == null) ? 0 : valuename.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		plantype other = (plantype) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (unitid == null) {
			if (other.unitid != null)
				return false;
		} else if (!unitid.equals(other.unitid))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (valuename == null) {
			if (other.valuename != null)
				return false;
		} else if (!valuename.equals(other.valuename))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "plantype [id=" + id + ", unitid=" + unitid + ", name=" + name
				+ ", value=" + value + ", valuename=" + valuename + "]";
	}
	
	private int id;
	private String unitid;
	private String name;
	private String value;
	private String valuename;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValuename() {
		return valuename;
	}
	public void setValuename(String valuename) {
		this.valuename = valuename;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}