package com.Fertilizer.hibernate;

import java.io.Serializable;

public class UserDetailsBean implements Serializable{

	private long usrId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address1;
	private String address2;
	private String pan;
	private String city;
	private long contactNo;
	private String userName;
	private String password;
	private String rePassword;
	private String typeId;

	public UserDetailsBean() {
		super();
	}

	public UserDetailsBean(long usrId, String firstName,
			String middleName, String lastName, String address1,
			String address2, String pan, String city, long contactNo,
			String userName, String password, String rePassword,String typeId) {
		super();
		this.usrId = usrId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.pan = pan;
		this.city = city;
		this.contactNo = contactNo;
		this.userName = userName;
		this.password = password;
		this.rePassword = rePassword;
		this.setTypeId(typeId);
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getUsrId() {
		return usrId;
	}
	public void setUsrId(long usrId) {
		this.usrId = usrId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
}
