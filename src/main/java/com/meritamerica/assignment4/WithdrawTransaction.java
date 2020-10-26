package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction {

	WithdrawTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
	}
	
	public void process() throws ExceedsAvailableBalanceException, NegativeAmountException, ExceedsFraudSuspicionLimitException {

		if (amount <= 0.0) {
			throw new NegativeAmountException();
		}
		else if (amount > targetAccount.getBalance()) {
			throw new ExceedsAvailableBalanceException();
		}
		else if (amount > 1000) {
			FraudQueue.addTransaction(this);
			throw new ExceedsFraudSuspicionLimitException();
		}
		else targetAccount.withdraw(amount);
	}
	
}
