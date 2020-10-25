package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction {
	DepositTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
	}
	
	public void process() {
		try {
			if (amount  <= 0) throw new NegativeAmountException("Error: cannot deposit a negative amount!");
			
			targetAccount.deposit(amount);
		} catch (NegativeAmountException e) {
			System.out.println(e);
		}
	}
}
