package com.Fertilizer.bean;

import java.math.BigInteger;
import java.util.Date;

public class GetEmployeeDetails {

	private String firstName;
	private String middleName;
	private String lastName;
	private String addresst;
	private String email;
	private Double salary;
	private BigInteger contactNo;
	private BigInteger zipCode;
	private Date joiningDate;
	
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
	public String getAddresst() {
		return addresst;
	}
	public void setAddresst(String addresst) {
		this.addresst = addresst;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public BigInteger getContactNo() {
		return contactNo;
	}
	public void setContactNo(BigInteger contactNo) {
		this.contactNo = contactNo;
	}
	public BigInteger getZipCode() {
		return zipCode;
	}
	public void setZipCode(BigInteger zipCode) {
		this.zipCode = zipCode;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

}
