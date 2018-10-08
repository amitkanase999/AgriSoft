package com.Fertilizer.bean;

import java.util.List;

public class PurchaseDeatilsResultBean {
	List<PurchaseDetailsFromGoodsReceive> list  ;
	Double totalAmt = 0.0 ;

	public List<PurchaseDetailsFromGoodsReceive> getList() {
		return list;
	}
	public void setList(List<PurchaseDetailsFromGoodsReceive> list) {
		this.list = list;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	} 
}
