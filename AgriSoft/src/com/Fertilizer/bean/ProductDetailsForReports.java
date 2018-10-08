package com.Fertilizer.bean;

public class ProductDetailsForReports {

	private String productName;
	private Double buyPrice;
	private Double salePrice;
	private String ManufacturingCompany;

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
	public String getManufacturingCompany() {
		return ManufacturingCompany;
	}
	public void setManufacturingCompany(String manufacturingCompany) {
		ManufacturingCompany = manufacturingCompany;
	}

}
