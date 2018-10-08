package com.Fertilizer.hibernate;

import java.io.Serializable;
import java.util.Date;

public class MeasuringUnitsBean implements Serializable {
	private Long pkUnitId;
	private String unitName;
	private Date insertDate;
	private String unitDescription;

	public MeasuringUnitsBean() {
		super();
	}

	public MeasuringUnitsBean(Long pkUnitId, String unitName,
			Date insertDate) {
		super();
		this.pkUnitId = pkUnitId;
		this.unitName = unitName;
		this.insertDate = insertDate;
	}

	public String getUnitDescription() {
		return unitDescription;
	}
	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Long getPkUnitId() {
		return pkUnitId;
	}
	public void setPkUnitId(Long pkUnitId) {
		this.pkUnitId = pkUnitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
