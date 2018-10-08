package com.Fertilizer.bean;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SaleReports {

	private Long orderId;
	private BigDecimal quantity ;
	private BigDecimal Quantity3;
	private BigInteger quantity1;
	private  String soldDate;
	private  String Qnty;
	private  String type;
	private Double discount;
	private Double iGSTPerc;
	private Double totalAmount;
	private Double tax;
	private Double netAmount;
	private Double TaxAmnt;
	private Double SgstAmnt;
	private Double CgstAmnt;
	private Double IgstAmnt;
	private Double SalePrice;
	private BigDecimal salePriceforsale;
	private Integer customerBill;
	private Double newTotAmt;
	private String itemName ;
	private BigInteger itemId ;
	private BigDecimal discountforsalereturn;
	private String color ;
	private BigDecimal expenses;
	private BigDecimal transExpense;
	private BigDecimal hamaliexpense;
	private String cusomerName;
	private String paymentMode;
	private String reason;
	private Double cashAmount;
	private Double chequeAmount;
	private Double neftAmount;
	private Double cardAmount;
	private Long billNo;
	private Long catId;
	private String supplierName;
	private Double buyPrice;
	private Double quanti;
	private Double fivePercentageGST;
	private Double twelwePercentageGST;
	private Double eighteenPercentageGST;
	private Double twentyEightPercentageGST;
	private Double iGSTFivePercentage;
	private Double iGSTTwelwePercentage;
	private Double iGSTEighteenPercentage;
	private Double iGSTTwentyeightPercentage;
	private Double totalTaxAmount;
	private Long serialnumber;
	private String saleDate;
	private String gstNumber;
	private String hsnNumber;
	private Double returnedAmount;
	private Double transTaxAmount;
	private Double hamaliTransExpense;
	
	private Double grossTotal;

	
	
	
	public Double getGrossTotal() {
		return grossTotal;
	}
	public void setGrossTotal(Double grossTotal) {
		this.grossTotal = grossTotal;
	}
	public Double getQuanti() {
		return quanti;
	}
	public void setQuanti(Double quanti) {
		this.quanti = quanti;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getHsnNumber() {
		return hsnNumber;
	}
	public void setHsnNumber(String hsnNumber) {
		this.hsnNumber = hsnNumber;
	}
	public Long getBillNo() {
		return billNo;
	}
	public void setBillNo(Long billNo) {
		this.billNo = billNo;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public Double getFivePercentageGST() {
		return fivePercentageGST;
	}
	public void setFivePercentageGST(Double fivePercentageGST) {
		this.fivePercentageGST = fivePercentageGST;
	}
	public Double getTwelwePercentageGST() {
		return twelwePercentageGST;
	}
	public void setTwelwePercentageGST(Double twelwePercentageGST) {
		this.twelwePercentageGST = twelwePercentageGST;
	}
	public Double getEighteenPercentageGST() {
		return eighteenPercentageGST;
	}
	public void setEighteenPercentageGST(Double eighteenPercentageGST) {
		this.eighteenPercentageGST = eighteenPercentageGST;
	}
	public Double getTwentyEightPercentageGST() {
		return twentyEightPercentageGST;
	}
	public void setTwentyEightPercentageGST(Double twentyEightPercentageGST) {
		this.twentyEightPercentageGST = twentyEightPercentageGST;
	}
	public Double getiGSTFivePercentage() {
		return iGSTFivePercentage;
	}
	public void setiGSTFivePercentage(Double iGSTFivePercentage) {
		this.iGSTFivePercentage = iGSTFivePercentage;
	}
	public Double getiGSTTwelwePercentage() {
		return iGSTTwelwePercentage;
	}
	public void setiGSTTwelwePercentage(Double iGSTTwelwePercentage) {
		this.iGSTTwelwePercentage = iGSTTwelwePercentage;
	}
	public Double getiGSTEighteenPercentage() {
		return iGSTEighteenPercentage;
	}
	public void setiGSTEighteenPercentage(Double iGSTEighteenPercentage) {
		this.iGSTEighteenPercentage = iGSTEighteenPercentage;
	}
	public Double getiGSTTwentyeightPercentage() {
		return iGSTTwentyeightPercentage;
	}
	public void setiGSTTwentyeightPercentage(Double iGSTTwentyeightPercentage) {
		this.iGSTTwentyeightPercentage = iGSTTwentyeightPercentage;
	}
	public Double getTotalTaxAmount() {
		return totalTaxAmount;
	}
	public void setTotalTaxAmount(Double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}
	public Long getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(Long serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getCusomerName() {
		return cusomerName;
	}
	public void setCusomerName(String cusomerName) {
		this.cusomerName = cusomerName;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public BigDecimal getSalePriceforsale() {
		return salePriceforsale;
	}
	public void setSalePriceforsale(BigDecimal salePriceforsale) {
		this.salePriceforsale = salePriceforsale;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setCustomerBill(Integer customerBill) {
		this.customerBill = customerBill;
	}
	public BigDecimal getDiscountforsalereturn() {
		return discountforsalereturn;
	}
	public void setDiscountforsalereturn(BigDecimal discountforsalereturn) {
		this.discountforsalereturn = discountforsalereturn;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public BigInteger getItemId() {
		return itemId;
	}
	public void setItemId(BigInteger itemId) {
		this.itemId = itemId;
	}
	public int getCustomerBill() {
		return customerBill;
	}
	public void setCustomerBill(int customerBill) {
		this.customerBill = customerBill;
	}
	public Double getSalePrice() {
		return SalePrice;
	}
	public void setSalePrice(Double salePrice) {
		SalePrice = salePrice;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getSoldDate() {
		return soldDate;
	}
	public void setSoldDate(String soldDate) {
		this.soldDate = soldDate;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	public Double getNewTotAmt() {
		return newTotAmt;
	}
	public void setNewTotAmt(Double newTotAmt) {
		this.newTotAmt = newTotAmt;
	}
	public BigInteger getQuantity1() {
		return quantity1;
	}
	public void setQuantity1(BigInteger quantity1) {
		this.quantity1 = quantity1;
	}
	public BigDecimal getExpenses() {
		return expenses;
	}
	public void setExpenses(BigDecimal expenses) {
		this.expenses = expenses;
	}
	public BigDecimal getTransExpense() {
		return transExpense;
	}
	public void setTransExpense(BigDecimal transExpense) {
		this.transExpense = transExpense;
	}
	public BigDecimal getHamaliexpense() {
		return hamaliexpense;
	}
	public void setHamaliexpense(BigDecimal hamaliexpense) {
		this.hamaliexpense = hamaliexpense;
	}
	public Double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public Double getChequeAmount() {
		return chequeAmount;
	}
	public void setChequeAmount(Double chequeAmount) {
		this.chequeAmount = chequeAmount;
	}
	public Double getNeftAmount() {
		return neftAmount;
	}
	public void setNeftAmount(Double neftAmount) {
		this.neftAmount = neftAmount;
	}
	public Double getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(Double cardAmount) {
		this.cardAmount = cardAmount;
	}
	public Double getReturnedAmount() {
		return returnedAmount;
	}
	public void setReturnedAmount(Double returnedAmount) {
		this.returnedAmount = returnedAmount;
	}
	public Double getTransTaxAmount() {
		return transTaxAmount;
	}
	public void setTransTaxAmount(Double transTaxAmount) {
		this.transTaxAmount = transTaxAmount;
	}
	public Double getHamaliTransExpense() {
		return hamaliTransExpense;
	}
	public void setHamaliTransExpense(Double hamaliTransExpense) {
		this.hamaliTransExpense = hamaliTransExpense;
	}
	public BigDecimal getQuantity3() {
		return Quantity3;
	}
	public void setQuantity3(BigDecimal quantity3) {
		Quantity3 = quantity3;
	}
	public Double getiGSTPerc() {
		return iGSTPerc;
	}
	public void setiGSTPerc(Double iGSTPerc) {
		this.iGSTPerc = iGSTPerc;
	}
	public Double getSgstAmnt() {
		return SgstAmnt;
	}
	public void setSgstAmnt(Double sgstAmnt) {
		SgstAmnt = sgstAmnt;
	}
	public Double getCgstAmnt() {
		return CgstAmnt;
	}
	public void setCgstAmnt(Double cgstAmnt) {
		CgstAmnt = cgstAmnt;
	}
	public Double getIgstAmnt() {
		return IgstAmnt;
	}
	public void setIgstAmnt(Double igstAmnt) {
		IgstAmnt = igstAmnt;
	}
	public Double getTaxAmnt() {
		return TaxAmnt;
	}
	public void setTaxAmnt(Double taxAmnt) {
		TaxAmnt = taxAmnt;
	}
	public String getQnty() {
		return Qnty;
	}
	public void setQnty(String qnty) {
		Qnty = qnty;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
