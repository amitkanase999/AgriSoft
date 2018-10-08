package com.Fertilizer.hibernate;

import java.util.Date;

public class PurchaseReturnBean {

	private Long purchase_return_pk;
	private String supplier_name;
	private Long bill_no;
	private String product_name;
	private String category;
	private String batch_no;
	private Long packing;
	private Double buy_price;	
	private Double sale_price;
	private Double MRP;
	private Double tax_per;
	private Long quantity;
	private Long retun_quantity;
	private String dc_no;
	private Long barcode_no;
	private Date purchase_date;
	private Date return_date;

	public Long getPurchase_return_pk() {
		return purchase_return_pk;
	}
	public void setPurchase_return_pk(Long purchase_return_pk) {
		this.purchase_return_pk = purchase_return_pk;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public Long getBill_no() {
		return bill_no;
	}
	public void setBill_no(Long bill_no) {
		this.bill_no = bill_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public Long getPacking() {
		return packing;
	}
	public void setPacking(Long packing) {
		this.packing = packing;
	}

	public Double getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(Double buy_price) {
		this.buy_price = buy_price;
	}
	public Double getSale_price() {
		return sale_price;
	}
	public void setSale_price(Double sale_price) {
		this.sale_price = sale_price;
	}
	public Double getMRP() {
		return MRP;
	}
	public void setMRP(Double mRP) {
		MRP = mRP;
	}
	public Double getTax_per() {
		return tax_per;
	}
	public void setTax_per(Double tax_per) {
		this.tax_per = tax_per;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getRetun_quantity() {
		return retun_quantity;
	}
	public void setRetun_quantity(Long retun_quantity) {
		this.retun_quantity = retun_quantity;
	}
	public String getDc_no() {
		return dc_no;
	}
	public void setDc_no(String dc_no) {
		this.dc_no = dc_no;
	}
	public Long getBarcode_no() {
		return barcode_no;
	}
	public void setBarcode_no(Long barcode_no) {
		this.barcode_no = barcode_no;
	}
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
}
