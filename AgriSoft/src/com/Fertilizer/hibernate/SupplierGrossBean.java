package com.Fertilizer.hibernate;

public class SupplierGrossBean {

	private Long pk_gross_id;
	private Long fk_supplier_id;
	private String billno;
	private Double grosstotal;
	public Long getPk_gross_id() {
		return pk_gross_id;
	}
	public void setPk_gross_id(Long pk_gross_id) {
		this.pk_gross_id = pk_gross_id;
	}
	public Long getFk_supplier_id() {
		return fk_supplier_id;
	}
	public void setFk_supplier_id(Long fk_supplier_id) {
		this.fk_supplier_id = fk_supplier_id;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public Double getGrosstotal() {
		return grosstotal;
	}
	public void setGrosstotal(Double grosstotal) {
		this.grosstotal = grosstotal;
	}
	
	
	
	
}
