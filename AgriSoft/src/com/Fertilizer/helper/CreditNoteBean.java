package com.Fertilizer.helper;

public class CreditNoteBean 
{
	private Long pk_creditnote_id;
	private String partyname;
	private String particular;
	private Double amount;

	public Long getPk_creditnote_id() {
		return pk_creditnote_id;
	}
	public void setPk_creditnote_id(Long pk_creditnote_id) {
		this.pk_creditnote_id = pk_creditnote_id;
	}
	public String getPartyname() {
		return partyname;
	}
	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}
	public String getParticular() {
		return particular;
	}
	public void setParticular(String particular) {
		this.particular = particular;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
