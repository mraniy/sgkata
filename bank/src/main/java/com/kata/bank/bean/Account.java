package com.kata.bank.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
	
	
	private String firstname;
	
	private String lastName;
	
	private BigDecimal balance;
	
	private List<Operation> listOperations;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	
	
	public List<Operation> getListOperations() {
		return listOperations;
	}

	public void setListOperations(List<Operation> listOperations) {
		this.listOperations = listOperations;
	}

	public Account(String firstname, String lastName, BigDecimal balance) {
		super();
		this.firstname = firstname;
		this.lastName = lastName;
		this.balance = balance;
		listOperations= new ArrayList<Operation>();
	}
	
	
	

}
