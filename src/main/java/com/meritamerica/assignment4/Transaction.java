package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.Arrays;

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
		Transaction trans;
		ArrayList<String> aL = new ArrayList<>(Arrays.asList(transactionDataString.split(",")));
		trans = new Transaction();
	}
	
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
	
	
	// Checks if it's been processed by the fraud team yet
	public boolean isProcessedByFraudTeam() {
		if (isProcessed == true) {
			return true;
		} else {
			return false;
		}
	}
	
	// Marks the transaction as processed by the fraud team
	public void setProcessedByFraudTeam(boolean isProcessed) {
		this.isProcessed = true;
	}
	
	public String getRejectionReason() {
		return rejectionReason;
	}
	
	// Gets string, maybe specified by user or something
	public void setRejectionReason(String reason) {
		this.rejectionReason = reason;
	}

}
