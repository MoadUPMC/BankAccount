package com.businessbject;

import java.util.Date;

public class Operation {

	private TypeOperation typeOperation;
	
	private String nameOfOperation;
	
	private Integer amount;
	
	private String date;
	
	public Operation(TypeOperation typeOperation, String nameOfOperation, Integer amount, String date) {
		super();
		this.typeOperation = typeOperation;
		this.amount = amount;
		this.date = date;
		this.nameOfOperation = nameOfOperation;
	}

	public TypeOperation getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(TypeOperation typeOperation) {
		this.typeOperation = typeOperation;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the nameOfOperation
	 */
	public String getNameOfOperation() {
		return nameOfOperation;
	}

	/**
	 * @param nameOfOperation the nameOfOperation to set
	 */
	public void setNameOfOperation(String nameOfOperation) {
		this.nameOfOperation = nameOfOperation;
	}
	
	

}
