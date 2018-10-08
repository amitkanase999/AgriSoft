package com.Fertilizer.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

public class GetPurchaseProduct {

	private BigInteger pk_goods_receive_id;
	private String supplier_name;
	private BigInteger dc_number;
	private String product_name;
	private BigDecimal buy_price;
	private BigDecimal sale_price;
	private BigDecimal weight;
	private Double quantity;
	private String batch_no;
	private BigInteger fkCategoryId;
	private Date purchaseDate;
	private BigDecimal mrp;
	private BigDecimal tax_percentage;
	private BigInteger barcodeNo;
	private String company_Name;
	private Double dupQuantity;
	private Double dupQuantity1;
	private String catName;
	private String strDate;

	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public BigInteger getPk_goods_receive_id() {
		return pk_goods_receive_id;
	}
	public void setPk_goods_receive_id(BigInteger pk_goods_receive_id) {
		this.pk_goods_receive_id = pk_goods_receive_id;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public BigInteger getDc_number() {
		return dc_number;
	}
	public void setDc_number(BigInteger dc_number) {
		this.dc_number = dc_number;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public BigDecimal getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(BigDecimal buy_price) {
		this.buy_price = buy_price;
	}
	public BigDecimal getSale_price() {
		return sale_price;
	}
	public void setSale_price(BigDecimal sale_price) {
		this.sale_price = sale_price;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public BigInteger getFkCategoryId() {
		return fkCategoryId;
	}
	public void setFkCategoryId(BigInteger fkCategoryId) {
		this.fkCategoryId = fkCategoryId;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(java.util.Date o) {
		this.purchaseDate = (Date) o;
	}
	public BigDecimal getMrp() {
		return mrp;
	}
	public void setMrp(BigDecimal mrp) {
		this.mrp = mrp;
	}
	public BigDecimal getTax_percentage() {
		return tax_percentage;
	}
	public void setTax_percentage(BigDecimal tax_percentage) {
		this.tax_percentage = tax_percentage;
	}
	public BigInteger getBarcodeNo() {
		return barcodeNo;
	}
	public void setBarcodeNo(BigInteger barcodeNo) {
		this.barcodeNo = barcodeNo;
	}
	public String getCompany_Name() {
		return company_Name;
	}
	public void setCompany_Name(String company_Name) {
		this.company_Name = company_Name;
	}
	public Double getDupQuantity() {
		return dupQuantity;
	}
	public void setDupQuantity(Double dupQuantity) {
		this.dupQuantity = dupQuantity;
	}
	public Double getDupQuantity1() {
		return dupQuantity1;
	}
	public void setDupQuantity1(Double dupQuantity1) {
		this.dupQuantity1 = dupQuantity1;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}

}
