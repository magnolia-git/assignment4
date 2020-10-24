package com.meritamerica.assignment4;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

	public class CheckingAccount extends BankAccount {
		
		
		static private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		public CheckingAccount(double openingBalance) {
			super(openingBalance);
			this.interestRate = 0.0001;
		}		

		public CheckingAccount(double interestRate, double balance) {
			super(interestRate, balance);

		}

		public CheckingAccount(long accountNumber, double interestRate, double balance) {
			super(accountNumber, interestRate, balance);
		}

		public CheckingAccount(double interestRate, double balance, java.util.Date accountOpenedOn) {
			super(interestRate, balance, accountOpenedOn);
		}

		public CheckingAccount(long accountNumber, double interestRate, double balance, java.util.Date accountOpenedOn) {
			super(accountNumber, interestRate, balance, accountOpenedOn);
		}
		
		public double getInterestRate() {return interestRate;}
		
		public static CheckingAccount readFromString(String accountData) throws ParseException {
			CheckingAccount checkAcc;
			try {
				ArrayList<String> aL = new ArrayList<>(Arrays.asList(accountData.split(",")));
				checkAcc = new CheckingAccount(Long.parseLong(aL.get(0)), Double.parseDouble(aL.get(1)), Double.parseDouble(aL.get(2)), dateFormat.parse(aL.get(3)));
			} catch (ParseException e) {
				throw new java.lang.NumberFormatException();
			}
			return checkAcc;
		}
}