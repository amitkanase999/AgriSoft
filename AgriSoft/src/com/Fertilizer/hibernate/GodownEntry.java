package com.Fertilizer.hibernate;

import java.io.Serializable;

public class GodownEntry implements Serializable{

	private Long PkGodownId;
	private String godownName;

	public GodownEntry() {
		super();
	}

	public GodownEntry(Long pkGodownId, String godownName) {
		super();
		PkGodownId = pkGodownId;
		this.godownName = godownName;
	}

	public Long getPkGodownId() {
		return PkGodownId;
	}
	public void setPkGodownId(Long pkGodownId) {
		PkGodownId = pkGodownId;
	}
	public String getGodownName() {
		return godownName;
	}
	public void setGodownName(String godownName) {
		this.godownName = godownName;
	}
}
