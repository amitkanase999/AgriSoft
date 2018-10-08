package com.Fertilizer.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ItemStock implements Serializable {

	private Long pkItemStockId;
	private Long stock;
	private Long fkProductId;
	private Long fkCatId;
	private Long fkGodwonId;
	private ProductDetailsBean productDetailsBean;
	private GodownEntry godownEntry;
	private CategoryDetailsBean categoryDetailsBean;


	public ItemStock() {
		super();
	}

	public ItemStock(Long pkItemStockId, Long stock, Long fkProductId,
			Long fkCatId, Long fkGodwonId,
			ProductDetailsBean productDetailsBean, GodownEntry godownEntry,
			CategoryDetailsBean categoryDetailsBean) {
		super();
		this.pkItemStockId = pkItemStockId;
		this.stock = stock;
		this.fkProductId = fkProductId;
		this.fkCatId = fkCatId;
		this.fkGodwonId = fkGodwonId;
		this.productDetailsBean = productDetailsBean;
		this.godownEntry = godownEntry;
		this.categoryDetailsBean = categoryDetailsBean;
	}

	public Long getFkCatId() {
		return fkCatId;
	}
	public void setFkCatId(Long fkCatId) {
		this.fkCatId = fkCatId;
	}
	public Long getFkGodwonId() {
		return fkGodwonId;
	}
	public void setFkGodwonId(Long fkGodwonId) {
		this.fkGodwonId = fkGodwonId;
	}
	public GodownEntry getGodownEntry() {
		return godownEntry;
	}
	public void setGodownEntry(GodownEntry godownEntry) {
		this.godownEntry = godownEntry;
	}
	public CategoryDetailsBean getCategoryDetailsBean() {
		return categoryDetailsBean;
	}
	public void setCategoryDetailsBean(CategoryDetailsBean categoryDetailsBean) {
		this.categoryDetailsBean = categoryDetailsBean;
	}
	public Long getPkItemStockId() {
		return pkItemStockId;
	}
	public void setPkItemStockId(Long pkItemStockId) {
		this.pkItemStockId = pkItemStockId;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public Long getFkProductId() {
		return fkProductId;
	}
	public void setFkProductId(Long fkProductId) {
		this.fkProductId = fkProductId;
	}
	public ProductDetailsBean getProductDetailsBean() {
		return productDetailsBean;
	}
	public void setProductDetailsBean(ProductDetailsBean productDetailsBean) {
		this.productDetailsBean = productDetailsBean;
	}
}
