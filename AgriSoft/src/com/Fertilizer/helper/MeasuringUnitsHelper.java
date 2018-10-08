package com.Fertilizer.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.dao.MeasuringUnitsDao;
import com.Fertilizer.hibernate.MeasuringUnitsBean;

public class MeasuringUnitsHelper {

	public void unitDetails(HttpServletRequest request,
			HttpServletResponse response) {

		String unitName = request.getParameter("unitName");
		String unitDescription = request.getParameter("unitDescription");

		MeasuringUnitsBean bean = new MeasuringUnitsBean();

		bean.setUnitName(unitName);
		bean.setUnitDescription(unitDescription);
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		System.out.println(dateFormat1.format(dateobj));

		bean.setInsertDate(dateobj);

		MeasuringUnitsDao dao = new MeasuringUnitsDao();
		dao.addUnit(bean);
	}
}
