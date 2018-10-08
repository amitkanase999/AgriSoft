package com.Fertilizer.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.bean.GetProductDetailPO;
import com.Fertilizer.bean.GetPurchaseProduct;
import com.Fertilizer.dao.GoodsReceiveDao;
import com.Fertilizer.dao.ProductDetailsDao;

public class GoodsReceiveBean implements Serializable{

	private Long pkGoodsReceiveId;
	private Long pkPOId;
	private Long supplier;
	private Long dcNum ;
	private Double expenses;
	private Double taxPercentage;
	private Double taxAmount;
	private Long fkExpenseId;
	private Double grossTotal;
	private String productName;
	private Double buyPrice ;
	private Double salePrice ;
	private Double weight ;
	private Double totalAmount;
	private Double quantity ;
	private String batchNo;
	private Date expiryDate;
	private Date insertDate;
	private Long fkCategoryId;
	private Long fkGodownId;
	private Date purchaseDate;
	private Date dueDate;
	private String billType;
	private Double mrp;
	private CategoryDetailsBean categoryDetailsBean;
	private SupplierDetailsBean supplierDetailsBean ;
	private ProductDetailsBean productDetailsBean;
	private GodownEntry godownEntry ;
	private Double discount;
	private Double discountAmount;
	private String billNum;
	private Long barcodeNo;
	private String companyName;
	private ExpenseDetailForBillingAndGoodsReceiveBean expenseDetailForBillingAndGoodsReceiveBean;
	private Double dupQuantity;
	private Double returnAmount;
	private Double totalAfterReturn;
	private Double iGstPercentage;
	private String hsn;
	private Double GSTforHamali;
	private Double GSTForTrans;
	private Double hamali;
	private Long fk_Godown_Id;
	private String godownName;

	public GoodsReceiveBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsReceiveBean(
			Long pkGoodsReceiveId,
			Long pkPOId,
			Long supplier,
			Long dcNum,
			Double expenses,
			Double taxPercentage,
			Double taxAmount,
			Long fkExpenseId,
			Double grossTotal,
			String productName,
			Double buyPrice,
			Double salePrice,
			Double weight,
			Double totalAmount,
			Double quantity,
			String batchNo,
			Date expiryDate,
			Date insertDate,
			Long fkCategoryId,
			Long fkGodownId,
			Date purchaseDate,
			Date dueDate,
			String billType,
			Double mrp,
			CategoryDetailsBean categoryDetailsBean,
			SupplierDetailsBean supplierDetailsBean,
			ProductDetailsBean productDetailsBean,
			GodownEntry godownEntry,
			Double discount,
			Double discountAmount,
			String billNum,
			Long barcodeNo,
			String companyName,
			ExpenseDetailForBillingAndGoodsReceiveBean expenseDetailForBillingAndGoodsReceiveBean,
			Double dupQuantity, Double returnAmount, Double totalAfterReturn,
			Double iGstPercentage, String hsn, Double gSTforHamali,
			Double gSTForTrans, Double hamali) {
		super();
		this.pkGoodsReceiveId = pkGoodsReceiveId;
		this.pkPOId = pkPOId;
		this.supplier = supplier;
		this.dcNum = dcNum;
		this.expenses = expenses;
		this.taxPercentage = taxPercentage;
		this.taxAmount = taxAmount;
		this.fkExpenseId = fkExpenseId;
		this.grossTotal = grossTotal;
		this.productName = productName;
		this.buyPrice = buyPrice;
		this.salePrice = salePrice;
		this.weight = weight;
		this.totalAmount = totalAmount;
		this.quantity = quantity;
		this.batchNo = batchNo;
		this.expiryDate = expiryDate;
		this.insertDate = insertDate;
		this.fkCategoryId = fkCategoryId;
		this.fkGodownId = fkGodownId;
		this.purchaseDate = purchaseDate;
		this.dueDate = dueDate;
		this.billType = billType;
		this.mrp = mrp;
		this.categoryDetailsBean = categoryDetailsBean;
		this.supplierDetailsBean = supplierDetailsBean;
		this.productDetailsBean = productDetailsBean;
		this.godownEntry = godownEntry;
		this.discount = discount;
		this.discountAmount = discountAmount;
		this.billNum = billNum;
		this.barcodeNo = barcodeNo;
		this.companyName = companyName;
		this.expenseDetailForBillingAndGoodsReceiveBean = expenseDetailForBillingAndGoodsReceiveBean;
		this.dupQuantity = dupQuantity;
		this.returnAmount = returnAmount;
		this.totalAfterReturn = totalAfterReturn;
		this.iGstPercentage = iGstPercentage;
		this.hsn = hsn;
		GSTforHamali = gSTforHamali;
		GSTForTrans = gSTForTrans;
		this.hamali = hamali;
	}

	
	
	public Long getFk_Godown_Id() {
		return fk_Godown_Id;
	}

	public void setFk_Godown_Id(Long fk_Godown_Id) {
		this.fk_Godown_Id = fk_Godown_Id;
	}

	public String getGodownName() {
		return godownName;
	}

	public void setGodownName(String godownName) {
		this.godownName = godownName;
	}

	public Double getHamali() {
		return hamali;
	}
	public void setHamali(Double hamali) {
		this.hamali = hamali;
	}
	public Double getGSTforHamali() {
		return GSTforHamali;
	}
	public void setGSTforHamali(Double gSTforHamali) {
		GSTforHamali = gSTforHamali;
	}
	public Double getGSTForTrans() {
		return GSTForTrans;
	}
	public void setGSTForTrans(Double gSTForTrans) {
		GSTForTrans = gSTForTrans;
	}
	public String getHsn() {
		return hsn;
	}
	public void setHsn(String hsn) {
		this.hsn = hsn;
	}
	public Double getiGstPercentage() {
		return iGstPercentage;
	}
	public void setiGstPercentage(Double iGstPercentage) {
		this.iGstPercentage = iGstPercentage;
	}
	public Double getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}
	public Double getTotalAfterReturn() {
		return totalAfterReturn;
	}
	public void setTotalAfterReturn(Double totalAfterReturn) {
		this.totalAfterReturn = totalAfterReturn;
	}
	public Double getDupQuantity() {
		return dupQuantity;
	}
	public void setDupQuantity(Double dupQuantity) {
		this.dupQuantity = dupQuantity;
	}
	public Long getPkGoodsReceiveId() {
		return pkGoodsReceiveId;
	}
	public void setPkGoodsReceiveId(Long pkGoodsReceiveId) {
		this.pkGoodsReceiveId = pkGoodsReceiveId;
	}
	public Long getPkPOId() {
		return pkPOId;
	}
	public void setPkPOId(Long pkPOId) {
		this.pkPOId = pkPOId;
	}
	public Long getSupplier() {
		return supplier;
	}
	public void setSupplier(Long supplier) {
		this.supplier = supplier;
	}
	public Long getDcNum() {
		return dcNum;
	}
	public void setDcNum(Long dcNum) {
		this.dcNum = dcNum;
	}
	public Double getExpenses() {
		return expenses;
	}
	public void setExpenses(Double expenses) {
		this.expenses = expenses;
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
	public Long getFkExpenseId() {
		return fkExpenseId;
	}
	public void setFkExpenseId(Long fkExpenseId) {
		this.fkExpenseId = fkExpenseId;
	}
	public Double getGrossTotal() {
		return grossTotal;
	}
	public void setGrossTotal(Double grossTotal) {
		this.grossTotal = grossTotal;
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
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Long getFkCategoryId() {
		return fkCategoryId;
	}
	public void setFkCategoryId(Long fkCategoryId) {
		this.fkCategoryId = fkCategoryId;
	}
	public Long getFkGodownId() {
		return fkGodownId;
	}
	public void setFkGodownId(Long fkGodownId) {
		this.fkGodownId = fkGodownId;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
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
	public GodownEntry getGodownEntry() {
		return godownEntry;
	}
	public void setGodownEntry(GodownEntry godownEntry) {
		this.godownEntry = godownEntry;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	public ExpenseDetailForBillingAndGoodsReceiveBean getExpenseDetailForBillingAndGoodsReceiveBean() {
		return expenseDetailForBillingAndGoodsReceiveBean;
	}
	public void setExpenseDetailForBillingAndGoodsReceiveBean(
			ExpenseDetailForBillingAndGoodsReceiveBean expenseDetailForBillingAndGoodsReceiveBean) {
		this.expenseDetailForBillingAndGoodsReceiveBean = expenseDetailForBillingAndGoodsReceiveBean;
	}
	public Long getBarcodeNo() {
		return barcodeNo;
	}
	public void setBarcodeNo(Long barcodeNo) {
		this.barcodeNo = barcodeNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
