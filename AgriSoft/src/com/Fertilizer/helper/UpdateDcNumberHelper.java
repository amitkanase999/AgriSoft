package com.Fertilizer.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Fertilizer.dao.UpdateDcNumberDao;
import com.Fertilizer.hibernate.UpdateDcNumberBean;

public class UpdateDcNumberHelper {

	public void updateDC(HttpServletRequest request,
			HttpServletResponse response) {
		
		UpdateDcNumberDao dao = new UpdateDcNumberDao();

		Integer count = Integer.parseInt(request.getParameter("count"));
		System.out.println(count);
		UpdateDcNumberBean bean = new UpdateDcNumberBean();
		for(int i =0 ; i<=count;i++)
		{
			String poNum  = request.getParameter("poNum");
			String dcNum = request.getParameter("dcNum");
			String pkPOId = request.getParameter("pkPOId"+i);
			System.out.println(pkPOId+"poID");
			if (pkPOId == null) {

				break;
			}
			else {
				bean.setPkPOId(Long.parseLong(pkPOId));
			}
			bean.setPoNum(Long.parseLong(poNum));
			bean.setDcNum(Long.parseLong(dcNum));

			dao.updateDcNumber(bean);
		}
	}
}
