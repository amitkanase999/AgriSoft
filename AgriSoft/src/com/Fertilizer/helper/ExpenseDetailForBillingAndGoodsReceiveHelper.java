package com.Fertilizer.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.dao.ExpenseDetailForBillingAndGoodsReceiveDao;
import com.Fertilizer.hibernate.ExpenseDetailForBillingAndGoodsReceiveBean;

public class ExpenseDetailForBillingAndGoodsReceiveHelper {

	public void expenseDetails(HttpServletRequest request,
			HttpServletResponse response) {


		String expenseName = request.getParameter("expenseName");

		ExpenseDetailForBillingAndGoodsReceiveBean bean = new ExpenseDetailForBillingAndGoodsReceiveBean();
		bean.setExpenseName(expenseName);

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		System.out.println(dateFormat1.format(dateobj));

		bean.setInsertDate(dateobj);

		ExpenseDetailForBillingAndGoodsReceiveDao dao = new ExpenseDetailForBillingAndGoodsReceiveDao();
		dao.addExpenseDetails(bean);
	}
}
