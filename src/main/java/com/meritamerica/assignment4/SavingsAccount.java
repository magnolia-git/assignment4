package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

	public class SavingsAccount extends BankAccount {
		
		static private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		public SavingsAccount(double openingBalance) {
			super(openingBalance);
			this.interestRate = 0.01;
		}		

		public SavingsAccount(double interestRate, double balance) {
			super(interestRate, balance);

		}

		public SavingsAccount(long accountNumber, double interestRate, double balance) {
			super(accountNumber, interestRate, balance);
		}

		public SavingsAccount(double interestRate, double balance, java.util.Date accountOpenedOn) {
			super(interestRate, balance, accountOpenedOn);
		}

		public SavingsAccount(long accountNumber, double interestRate, double balance, java.util.Date accountOpenedOn) {
			super(accountNumber, interestRate, balance, accountOpenedOn);
		}

		public double getInterestRate() {return interestRate;}
		
		public static SavingsAccount readFromString(String accountData) throws ParseException {
			SavingsAccount saveAcc;
			try {
				ArrayList<String> aL = new ArrayList<>(Arrays.asList(accountData.split(",")));
				saveAcc = new SavingsAccount(Long.parseLong(aL.get(0)), Double.parseDouble(aL.get(1)), Double.parseDouble(aL.get(2)), dateFormat.parse(aL.get(3)));
			} catch (ParseException e) {
				throw new java.lang.NumberFormatException();
			}
			return saveAcc;
		}
}