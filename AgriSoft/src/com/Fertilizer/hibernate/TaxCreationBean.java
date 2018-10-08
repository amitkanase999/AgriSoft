package com.Fertilizer.hibernate;

import java.io.Serializable;

public class TaxCreationBean implements Serializable
{

	private long txId;
	private String taxType;
	private double taxPercentage;

	public TaxCreationBean(long txId, String taxType, double taxPercentage) {
		super();
		this.txId = txId;
		this.taxType = taxType;
		this.taxPercentage = taxPercentage;
	}

	public TaxCreationBean() {
		super();
	}

	public long getTxId() {
		return txId;
	}
	public void setTxId(long txId) {
		this.txId = txId;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	public double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
}
