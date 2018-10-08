package com.Fertilizer.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Fertilizer.dao.CreditNoteDao;

public class CreditNoteHelper {

	public void creditNoteGen(HttpServletRequest request, HttpServletResponse response )
	{
		String partyname=request.getParameter("partyname");
		String particular=request.getParameter("particular");
		String amount=request.getParameter("amount");

		HttpSession session = request.getSession();
		session.setAttribute("partyname", partyname);

		CreditNoteBean bean=new CreditNoteBean();

		if(partyname!=null)
		{
			bean.setPartyname(partyname);
		}
		else
		{
			bean.setPartyname("N/A");
		}

		if(particular!=null)
		{
			bean.setParticular(particular);
		}
		else
		{
			bean.setParticular("N/A");
		}

		if(partyname!=null)
		{
			bean.setAmount(Double.parseDouble(amount));
		}
		else
		{
			bean.setAmount(0.0d);
		}

		CreditNoteDao dao=new CreditNoteDao();
		dao.insertCreditNote(bean);
	}
}
