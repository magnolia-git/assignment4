package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction {

	WithdrawTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
	}
	
	public void process() {
		try {
			if (amount  <= 0) throw new NegativeAmountException("Error: cannot withdraw a negative amount!");
			if (targetAccount.getBalance() < amount) throw new ExceedsAvailableBalanceException("Error: amount being withdrawn exceeds current funds!");
			else targetAccount.withdraw(amount);
			
		} catch (NegativeAmountException e) {
			System.out.println(e);
		} catch (ExceedsAvailableBalanceException e) {
			System.out.println(e);
		}
	}
	
}
