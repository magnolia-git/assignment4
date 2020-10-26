package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction {
	
	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
	}
	
	public void process() {
		if (amount < sourceAccount.getBalance()) {
			if (amount >= 1000) {
				// add to fraud queue
			} else {
				sourceAccount.withdraw(amount);
				targetAccount.deposit(amount);
			}
		}
		// transfer amount should be less than balance in source account
		// transfer amount should be a positive number
		// transfers exceeding 1000 must be reviewed by fraud team
	}
}
