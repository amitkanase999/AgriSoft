package com.Fertilizer.hibernate;

import java.util.Date;

public class PesticideBillBean {

	private Long pkPesticideBillId;
	private String customerName;
	private String customertype;
	private String customerHiddenName;
	private Long fkCreditCustomerId;
	private String village;
	private Long contact;
	private Double TaxAmount;
	private Long aadhar;
	private Double transExpense;
	private Double hamaliExpense;
	private Double total;
	private Double grossTotal;
	private Double totalAfterSaleReturn;
	private Double returnAmount;
	private String paymentMode;
	private String nameOnCheck;
	private String checkNo;
	private Long cardNo;
	private String accNo;
	private String bankName;
	private Long catId;
	private Long supplierId;
	private Long fkGoodsReceive;
	private Long barcode;
	private String productName;
	private String company;
	private Double salePrice;
	private Double mrp;
	private Long quantity;
	private Double totalInGrid;
	private Double taxPercentage;
	private Double weight;
	private Long billNo;
	private Date insertDate;
	private String expiryDate;
	private Long pkSeedPesticideBillId;
	private String batchNumber;
	private Long returnQuantity;
	private Long quantityAfterReturn;
	private String hsn;
	private Double iGst;
	private Double newSalePrice;
	private Double pesticideBalance_Status;
	private String CustName;
	private Double pesticideinterestrate;
	private String unit;
	private String payCustType;

	public PesticideBillBean() {
		super();
	}

	public PesticideBillBean(Long pkPesticideBillId, String customerName,
			String customerHiddenName, Long fkCreditCustomerId, String village,
			Long contact, Long aadhar, Double transExpense,
			Double hamaliExpense, Double total, Double grossTotal,
			Double totalAfterSaleReturn, Double returnAmount,
			String paymentMode, String nameOnCheck, String checkNo,
			Long cardNo, String accNo, String bankName, Long catId,
			Long supplierId, Long fkGoodsReceive, Long barcode,
			String productName, String company, Double salePrice, Double mrp,
			Long quantity, Double totalInGrid, Double taxPercentage,
			Double weight, Long billNo, Date insertDate, String expiryDate,
			Long pkSeedPesticideBillId, String batchNumber,
			Long returnQuantity, Long quantityAfterReturn, String hsn,
			Double iGst, Double newSalePrice) {
		super();
		this.pkPesticideBillId = pkPesticideBillId;
		this.customerName = customerName;
		this.customerHiddenName = customerHiddenName;
		this.fkCreditCustomerId = fkCreditCustomerId;
		this.village = village;
		this.contact = contact;
		this.aadhar = aadhar;
		this.transExpense = transExpense;
		this.hamaliExpense = hamaliExpense;
		this.total = total;
		this.grossTotal = grossTotal;
		this.totalAfterSaleReturn = totalAfterSaleReturn;
		this.returnAmount = returnAmount;
		this.paymentMode = paymentMode;
		this.nameOnCheck = nameOnCheck;
		this.checkNo = checkNo;
		this.cardNo = cardNo;
		this.accNo = accNo;
		this.bankName = bankName;
		this.catId = catId;
		this.supplierId = supplierId;
		this.fkGoodsReceive = fkGoodsReceive;
		this.barcode = barcode;
		this.productName = productName;
		this.company = company;
		this.salePrice = salePrice;
		this.mrp = mrp;
		this.quantity = quantity;
		this.totalInGrid = totalInGrid;
		this.taxPercentage = taxPercentage;
		this.weight = weight;
		this.billNo = billNo;
		this.insertDate = insertDate;
		this.expiryDate = expiryDate;
		this.pkSeedPesticideBillId = pkSeedPesticideBillId;
		this.batchNumber = batchNumber;
		this.returnQuantity = returnQuantity;
		this.quantityAfterReturn = quantityAfterReturn;
		this.hsn = hsn;
		this.iGst = iGst;
		this.newSalePrice = newSalePrice;
	}

	
	
	
	public String getPayCustType() {
		return payCustType;
	}

	public void setPayCustType(String payCustType) {
		this.payCustType = payCustType;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPesticideinterestrate() {
		return pesticideinterestrate;
	}

	public void setPesticideinterestrate(Double pesticideinterestrate) {
		this.pesticideinterestrate = pesticideinterestrate;
	}

	public String getCustName() {
		return CustName;
	}

	public void setCustName(String custName) {
		CustName = custName;
	}

	public Double getPesticideBalance_Status() {
		return pesticideBalance_Status;
	}

	public void setPesticideBalance_Status(Double pesticideBalance_Status) {
		this.pesticideBalance_Status = pesticideBalance_Status;
	}

	public Double getNewSalePrice() {
		return newSalePrice;
	}
	public void setNewSalePrice(Double newSalePrice) {
		this.newSalePrice = newSalePrice;
	}
	public String getHsn() {
		return hsn;
	}
	public void setHsn(String hsn) {
		this.hsn = hsn;
	}
	public Double getiGst() {
		return iGst;
	}
	public void setiGst(Double iGst) {
		this.iGst = iGst;
	}
	public Long getQuantityAfterReturn() {
		return quantityAfterReturn;
	}
	public void setQuantityAfterReturn(Long quantityAfterReturn) {
		this.quantityAfterReturn = quantityAfterReturn;
	}
	public Long getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(Long returnQuantity) {
		this.returnQuantity = returnQuantity;
	}
	public Double getTotalAfterSaleReturn() {
		return totalAfterSaleReturn;
	}
	public void setTotalAfterSaleReturn(Double totalAfterSaleReturn) {
		this.totalAfterSaleReturn = totalAfterSaleReturn;
	}
	public Double getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerHiddenName() {
		return customerHiddenName;
	}

	public Long getPkPesticideBillId() {
		return pkPesticideBillId;
	}
	public void setPkPesticideBillId(Long pkPesticideBillId) {
		this.pkPesticideBillId = pkPesticideBillId;
	}
	public void setCustomerHiddenName(String customerHiddenName) {
		this.customerHiddenName = customerHiddenName;
	}
	public Long getFkCreditCustomerId() {
		return fkCreditCustomerId;
	}
	public void setFkCreditCustomerId(Long fkCreditCustomerId) {
		this.fkCreditCustomerId = fkCreditCustomerId;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public Long getContact() {
		return contact;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public Long getAadhar() {
		return aadhar;
	}
	public void setAadhar(Long aadhar) {
		this.aadhar = aadhar;
	}
	public Double getTransExpense() {
		return transExpense;
	}
	public void setTransExpense(Double transExpense) {
		this.transExpense = transExpense;
	}
	public Double getHamaliExpense() {
		return hamaliExpense;
	}
	public void setHamaliExpense(Double hamaliExpense) {
		this.hamaliExpense = hamaliExpense;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getGrossTotal() {
		return grossTotal;
	}
	public void setGrossTotal(Double grossTotal) {
		this.grossTotal = grossTotal;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getNameOnCheck() {
		return nameOnCheck;
	}
	public void setNameOnCheck(String nameOnCheck) {
		this.nameOnCheck = nameOnCheck;
	}

	public Long getCardNo() {
		return cardNo;
	}
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public Long getFkGoodsReceive() {
		return fkGoodsReceive;
	}
	public void setFkGoodsReceive(Long fkGoodsReceive) {
		this.fkGoodsReceive = fkGoodsReceive;
	}
	public Long getBarcode() {
		return barcode;
	}
	public void setBarcode(Long barcode) {
		this.barcode = barcode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Double getTotalInGrid() {
		return totalInGrid;
	}
	public void setTotalInGrid(Double totalInGrid) {
		this.totalInGrid = totalInGrid;
	}
	public Double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Long getBillNo() {
		return billNo;
	}
	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Long getPkSeedPesticideBillId() {
		return pkSeedPesticideBillId;
	}
	public void setPkSeedPesticideBillId(Long pkSeedPesticideBillId) {
		this.pkSeedPesticideBillId = pkSeedPesticideBillId;
	}
	public Double getTaxAmount() {
		return TaxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		TaxAmount = taxAmount;
	}
}
