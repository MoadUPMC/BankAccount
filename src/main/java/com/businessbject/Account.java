package com.businessbject;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Account {

	public Account() {}

	private List<Operation> operations; 

	private Integer actualAmount;
	/**
	 * @return the operations
	 */
	public List<Operation> getOperations() {
		return operations;
	}

	/**
	 * @param operations the operations to set
	 */
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	/**
	 * @return the actualAmount
	 */
	public Integer getActualAmount() {
		return operations.stream()
				.map(op -> {
//					Integer signOfAmount = op.getAmount();
//					if (op.getTypeOperation() == TypeOperation.Withdrawal) { op.setAmount(-1 * signOfAmount); }
					return op; 
				}).mapToInt(op -> op.getAmount()).sum() ;

	}

}
