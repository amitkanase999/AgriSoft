package com.Fertilizer.hibernate;

import java.util.Date;

public class ExpenseDetailForBillingAndGoodsReceiveBean {

	private Long pkExpenseForBillingId;
	private String expenseName;
	private Date insertDate;

	public ExpenseDetailForBillingAndGoodsReceiveBean() {
		super();
	}

	public ExpenseDetailForBillingAndGoodsReceiveBean(
			Long pkExpenseForBillingId, String expenseName, Date insertDate) {
		super();
		this.pkExpenseForBillingId = pkExpenseForBillingId;
		this.expenseName = expenseName;
		this.insertDate = insertDate;
	}

	public Long getPkExpenseForBillingId() {
		return pkExpenseForBillingId;
	}
	public void setPkExpenseForBillingId(Long pkExpenseForBillingId) {
		this.pkExpenseForBillingId = pkExpenseForBillingId;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}
