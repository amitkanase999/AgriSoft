package com.Fertilizer.hibernate;

import java.io.Serializable;
import java.util.Date;

public class ProductDetailsBean implements Serializable {

	private Long prodctId;
	private Long fk_cat_id;
	private Long fk_supplier_id;
	private Long fk_unit_id;
	private Long poNumber;
	private String productName;
	private String manufacturingCompany;
	private Double buyPrice;
	private Double salePrice;
	private Double creditSalePrice;
	private Double weight;
	private Date insertDate;
	private Long status;
	private Long fkTaxId;
	private Double taxPercentage;
	private Double mrp;
	private String hsn;

	//for mapping 

	private CategoryDetailsBean categoryDetailsBean;
	private SupplierDetailsBean supplierDetailsBean;
	private MeasuringUnitsBean MeasuringUnitsBean;
	private TaxCreationBean taxCreationBean;

	public ProductDetailsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDetailsBean(
			Long prodctId,
			Long fk_cat_id,
			Long fk_supplier_id,
			Long fk_unit_id,
			Long poNumber,
			String productName,
			String manufacturingCompany,
			Double buyPrice,
			Double salePrice,
			Double creditSalePrice,
			Double weight,
			Date insertDate,
			Long status,
			Long fkTaxId,
			Double taxPercentage,
			Double mrp,
			String hsn,
			CategoryDetailsBean categoryDetailsBean,
			SupplierDetailsBean supplierDetailsBean,
			com.Fertilizer.hibernate.MeasuringUnitsBean measuringUnitsBean,
			TaxCreationBean taxCreationBean) {
		super();
		this.prodctId = prodctId;
		this.fk_cat_id = fk_cat_id;
		this.fk_supplier_id = fk_supplier_id;
		this.fk_unit_id = fk_unit_id;
		this.poNumber = poNumber;
		this.productName = productName;
		this.manufacturingCompany = manufacturingCompany;
		this.buyPrice = buyPrice;
		this.salePrice = salePrice;
		this.creditSalePrice = creditSalePrice;
		this.weight = weight;
		this.insertDate = insertDate;
		this.status = status;
		this.fkTaxId = fkTaxId;
		this.taxPercentage = taxPercentage;
		this.mrp = mrp;
		this.hsn = hsn;
		this.categoryDetailsBean = categoryDetailsBean;
		this.supplierDetailsBean = supplierDetailsBean;
		MeasuringUnitsBean = measuringUnitsBean;
		this.taxCreationBean = taxCreationBean;
	}

	public String getHsn() {
		return hsn;
	}
	public void setHsn(String hsn) {
		this.hsn = hsn;
	}
	public Long getFkTaxId() {
		return fkTaxId;
	}
	public void setFkTaxId(Long fkTaxId) {
		this.fkTaxId = fkTaxId;
	}
	public Double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public TaxCreationBean getTaxCreationBean() {
		return taxCreationBean;
	}
	public void setTaxCreationBean(TaxCreationBean taxCreationBean) {
		this.taxCreationBean = taxCreationBean;
	}
	public Double getCreditSalePrice() {
		return creditSalePrice;
	}
	public void setCreditSalePrice(Double creditSalePrice) {
		this.creditSalePrice = creditSalePrice;
	}
	public Long getProdctId() {
		return prodctId;
	}
	public void setProdctId(Long prodctId) {
		this.prodctId = prodctId;
	}
	public Long getFk_cat_id() {
		return fk_cat_id;
	}
	public void setFk_cat_id(Long fk_cat_id) {
		this.fk_cat_id = fk_cat_id;
	}
	public Long getFk_supplier_id() {
		return fk_supplier_id;
	}
	public void setFk_supplier_id(Long fk_supplier_id) {
		this.fk_supplier_id = fk_supplier_id;
	}
	public Long getFk_unit_id() {
		return fk_unit_id;
	}
	public void setFk_unit_id(Long fk_unit_id) {
		this.fk_unit_id = fk_unit_id;
	}
	public Long getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(Long poNumber) {
		this.poNumber = poNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getManufacturingCompany() {
		return manufacturingCompany;
	}
	public void setManufacturingCompany(String manufacturingCompany) {
		this.manufacturingCompany = manufacturingCompany;
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
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
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
	public MeasuringUnitsBean getMeasuringUnitsBean() {
		return MeasuringUnitsBean;
	}
	public void setMeasuringUnitsBean(MeasuringUnitsBean measuringUnitsBean) {
		MeasuringUnitsBean = measuringUnitsBean;
	}
}
