package com.Fertilizer.hibernate;

import java.io.Serializable;

public class UpdateDcNumberBean implements Serializable{

	private Long poNum;
	private Long dcNum;
	private Long pkPOId;

	public UpdateDcNumberBean() {
		super();
	}

	public UpdateDcNumberBean(Long poNum, Long dcNum, Long pkPOId) {
		super();
		this.poNum = poNum;
		this.dcNum = dcNum;
		this.pkPOId = pkPOId;
	}

	public Long getPoNum() {
		return poNum;
	}
	public void setPoNum(Long poNum) {
		this.poNum = poNum;
	}
	public Long getDcNum() {
		return dcNum;
	}
	public void setDcNum(Long dcNum) {
		this.dcNum = dcNum;
	}
	public Long getPkPOId() {
		return pkPOId;
	}
	public void setPkPOId(Long pkPOId) {
		this.pkPOId = pkPOId;
	}
}
