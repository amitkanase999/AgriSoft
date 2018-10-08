package com.Fertilizer.helper;

import java.util.List;

import com.Fertilizer.dao.DailySaleDao;

public class DailySaleHelper {

	public List getDay() {
		DailySaleDao dao =  new DailySaleDao();
		return dao.getDay();

	}

	public List getWeek()
	{
		DailySaleDao dao = new DailySaleDao();
		return dao.getWeek();
	}

	public List getMonth()
	{
		DailySaleDao dao = new DailySaleDao();
		return dao.getMonth();
	}

	//seed
	public List getDayForSeed() {
		DailySaleDao dao =  new DailySaleDao();
		return dao.getDayForSeed();

	}

	public List getWeekForSeed()
	{
		DailySaleDao dao = new DailySaleDao();
		return dao.getWeekForSeed();
	}

	public List getMonthForSeed()
	{
		DailySaleDao dao = new DailySaleDao();
		return dao.getMonthForSeed();
	}

	//pesticide
	public List getDayForPesticide() {
		DailySaleDao dao =  new DailySaleDao();
		return dao.getDayForPesti();

	}

	public List getWeekForPesticide()
	{
		DailySaleDao dao = new DailySaleDao();
		return dao.getWeekForPesti();
	}

	public List getMonthForPesticide()
	{
		DailySaleDao dao = new DailySaleDao();
		return dao.getMonthForPesti();
	}
}
