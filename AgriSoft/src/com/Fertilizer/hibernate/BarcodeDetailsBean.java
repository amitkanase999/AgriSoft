package com.Fertilizer.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;

public class BarcodeDetailsBean implements Serializable {

	private Long pkBarcodeId;
	private Long barcodeNumber;
	private Long fkProductId;
	private ProductDetailsBean productDetailsBean;

	public ProductDetailsBean getProductDetailsBean() {
		return productDetailsBean;
	}
	public void setProductDetailsBean(ProductDetailsBean productDetailsBean) {
		this.productDetailsBean = productDetailsBean;
	}
	public Long getPkBarcodeId() {
		return pkBarcodeId;
	}
	public void setPkBarcodeId(Long pkBarcodeId) {
		this.pkBarcodeId = pkBarcodeId;
	}
	public Long getBarcodeNumber() {
		return barcodeNumber;
	}
	public void setBarcodeNumber(Long barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}
	public Long getFkProductId() {
		return fkProductId;
	}
	public void setFkProductId(Long fkProductId) {
		this.fkProductId = fkProductId;
	}
	public BarcodeDetailsBean(Long pkBarcodeId, Long barcodeNumber,
			Long fkProductId, ProductDetailsBean productDetailsBean) {
		super();
		this.pkBarcodeId = pkBarcodeId;
		this.barcodeNumber = barcodeNumber;
		this.fkProductId = fkProductId;
		this.productDetailsBean = productDetailsBean;
	}
	public BarcodeDetailsBean() {
		super();
	}
}
