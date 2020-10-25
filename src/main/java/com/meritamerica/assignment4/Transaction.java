package com.meritamerica.assignment4;

public abstract class Transaction {
	
	BankAccount sourceAccount;
	BankAccount targetAccount;
	java.util.Date transactionDate;
	double amount;
	String rejectionReason;
	boolean isProcessed;
	
	public BankAccount getSourceAccount() {
		return sourceAccount;
	}
	
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	
	public BankAccount getTargetAccount() {
		return targetAccount;
	}
	
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public java.util.Date getTransactionDate() {
		return this.transactionDate;
	}
	
	public void setTransactionDate(java.util.Date date) {
		this.transactionDate = date;
	}
	
	public String writeToString() {
		return null;
	}
	
	public static Transaction readFromString(String transactionDataString) {
		return null;
	}
	
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
	
	public boolean isProcessedByFraudTeam() {
		if (this.amount > 1000) {
			return isProcessed = true;
		} else {
			return false;
		}
	}
	
	public void setProcessedByFraudTeam(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	public String getRejectionReason() {
		return rejectionReason;
	}
	
	public void setRejectionReason(String reason) {
		this.rejectionReason = reason;
	}

}
