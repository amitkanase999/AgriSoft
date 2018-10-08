package com.Fertilizer.hibernate;

import java.io.Serializable;
import java.util.Date;

public class ExpenditureDetailsBean implements Serializable{

	private Long pkExpenseDetailsId;
	private String expenseName;
	private Date insertDate;

	public ExpenditureDetailsBean() {
		super();
	}

	public ExpenditureDetailsBean(Long pkExpenseDetailsId, String expenseName,
			Date insertDate) {
		super();
		this.pkExpenseDetailsId = pkExpenseDetailsId;
		this.expenseName = expenseName;
		this.insertDate = insertDate;
	}

	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Long getPkExpenseDetailsId() {
		return pkExpenseDetailsId;
	}
	public void setPkExpenseDetailsId(Long pkExpenseDetailsId) {
		this.pkExpenseDetailsId = pkExpenseDetailsId;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
}
