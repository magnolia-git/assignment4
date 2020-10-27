package com.meritamerica.assignment4;

public class MeritAmericaBankApp {
	public static void main(String[] args) throws ExceedsCombinedBalanceLimitException,
			ExceedsFraudSuspicionLimitException, NegativeAmountException, ExceedsAvailableBalanceException {
		MeritBank.readFromFile("src/test/testMeritBank_good.txt");
	}
}