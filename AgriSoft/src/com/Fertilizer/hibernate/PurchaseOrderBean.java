package com.Fertilizer.hibernate;

import java.io.Serializable;
import java.util.Date;

public class PurchaseOrderBean implements Serializable {

	private Long pkPoId ;
	private Long dcNum ;
	private Long supplier;
	private Long fk_cat_id ;
	private Long productID ;
	private String productName;
	private Long poNumber;
	private Double buyPrice ;
	private Double salePrice ;
	private Double weight ;
	private Double totalAmount;
	private Long quantity ;
	private Double taxPercentage;
	private Double taxAmount;
	private Double mrp;
	private Double gross;
	private Double tokenRate;
	private String manufacturer;
	private String unitName;
	private Date insertDate;

	//for mapping 

	private CategoryDetailsBean categoryDetailsBean;
	private SupplierDetailsBean supplierDetailsBean;
	private ProductDetailsBean productDetailsBean;

	public PurchaseOrderBean() {
		super();
	}

	public PurchaseOrderBean(Long pkPoId, Long dcNum, Long supplier,
			Long fk_cat_id, Long productID, String productName,
			Long poNumber, Double buyPrice, Double salePrice,
			Double weight, Double totalAmount, Long quantity,
			Double taxPercentage, Double taxAmount, Double mrp,
			CategoryDetailsBean categoryDetailsBean,
			SupplierDetailsBean supplierDetailsBean,
			ProductDetailsBean productDetailsBean, Date insertDate) {
		super();
		this.pkPoId = pkPoId;
		this.dcNum = dcNum;
		this.supplier = supplier;
		this.fk_cat_id = fk_cat_id;
		this.productID = productID;
		this.productName = productName;
		this.poNumber = poNumber;
		this.buyPrice = buyPrice;
		this.salePrice = salePrice;
		this.weight = weight;
		this.totalAmount = totalAmount;
		this.quantity = quantity;
		this.taxPercentage = taxPercentage;
		this.taxAmount = taxAmount;
		this.mrp = mrp;
		this.categoryDetailsBean = categoryDetailsBean;
		this.supplierDetailsBean = supplierDetailsBean;
		this.productDetailsBean = productDetailsBean;
		this.insertDate = insertDate;
	}

	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Double getTokenRate() {
		return tokenRate;
	}
	public void setTokenRate(Double tokenRate) {
		this.tokenRate = tokenRate;
	}
	public Double getGross() {
		return gross;
	}
	public void setGross(Double gross) {
		this.gross = gross;
	}
	public Long getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(Long poNumber) {
		this.poNumber = poNumber;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public CategoryDetailsBean getCategoryDetailsBean() {
		return categoryDetailsBean;
	}
	public void setCategoryDetailsBean(CategoryDetailsBean categoryDetailsBean) {
		this.categoryDetailsBean = categoryDetailsBean;
	}
	public SupplierDetailsBean getSupplierDetailsBean() {
		return supplierDetailsBean;
	}
	public void setSupplierDetailsBean(SupplierDetailsBean supplierDetailsBean) {
		this.supplierDetailsBean = supplierDetailsBean;
	}
	public ProductDetailsBean getProductDetailsBean() {
		return productDetailsBean;
	}
	public void setProductDetailsBean(ProductDetailsBean productDetailsBean) {
		this.productDetailsBean = productDetailsBean;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getPkPoId() {
		return pkPoId;
	}
	public void setPkPoId(Long pkPoId) {
		this.pkPoId = pkPoId;
	}
	public Long getDcNum() {
		return dcNum;
	}
	public void setDcNum(Long dcNum) {
		this.dcNum = dcNum;
	}
	public Long getSupplier() {
		return supplier;
	}
	public void setSupplier(Long supplier) {
		this.supplier = supplier;
	}
	public Long getFk_cat_id() {
		return fk_cat_id;
	}
	public void setFk_cat_id(Long fk_cat_id) {
		this.fk_cat_id = fk_cat_id;
	}
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
}
