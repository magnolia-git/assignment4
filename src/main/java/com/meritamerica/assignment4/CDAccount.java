package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

	public class CDAccount extends BankAccount {
	
		private CDOffering offering;
//		private double balance;
		static private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		public CDAccount(CDOffering offering, double balance) {
			super(balance);
			this.offering = offering;
//			this.balance = balance;
		}
		
		public CDAccount(CDOffering offering, double balance, java.util.Date accountOpenedOn) {
			super(balance);
			this.offering = offering;
			
		}
		public CDAccount(long accountNumber, double balance, double rate, java.util.Date accountOpenedOn, int term) {
			super(BankAccount.accountNumber, balance, CDOffering.getInterestRate(), accountOpenedOn, CDOffering.getTerm());
			
		}
		
	
		public double getInterestRate() {return CDOffering.getInterestRate();}
	
		public int getTerm() {return offering.getTerm();}
	
		public Date getStartDate() {Date date = new Date();return date;}
	
		public long getAccountNumber() {return accountNumber;}
	
		public double futureValue() {
			return MeritBank.recursiveFutureValue(getBalance(), getTerm(), getInterestRate());
//			return getBalance() * (Math.pow(1 + getInterestRate(), getTerm()));
		}
		
		@Override
		public boolean deposit(double amount) {
			return false;
		}
		
		@Override
		public boolean withdraw(double amount) {
			return false;
		}
		
		public static CDAccount readFromString(String accountData) throws ParseException {
			CDAccount cdAcc;
			try {
				ArrayList<String> aL = new ArrayList<>(Arrays.asList(accountData.split(",")));
				cdAcc = new CDAccount(Long.parseLong(aL.get(0)), Double.parseDouble(aL.get(1)), Double.parseDouble(aL.get(2)), dateFormat.parse(aL.get(3)), Integer.parseInt(aL.get(4)));
			} catch (ParseException e) {
				throw new java.lang.NumberFormatException();
			}
			return cdAcc;
		}
		@Override
		public String writeToString() {
			return null;
		}
}