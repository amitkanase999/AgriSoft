package com.Fertilizer.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.dao.CategoryDetailsDao;
import com.Fertilizer.hibernate.CategoryDetailsBean;

public class CategoryDetailsHelper {

	public void catDetails(HttpServletRequest request,
			HttpServletResponse response) {

		String categoryName = request.getParameter("categoryName");

		CategoryDetailsBean cdb = new CategoryDetailsBean();
		cdb.setCategoryName(categoryName);

		CategoryDetailsDao cdd = new CategoryDetailsDao();
		cdd.addCategory(cdb);
	}
}
