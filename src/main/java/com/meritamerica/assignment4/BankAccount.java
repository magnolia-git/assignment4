package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

	public abstract class BankAccount {
		public double interestRate = 0;
		private double balance = 0;
		public static long accountNumber;
		private static java.util.Date accountOpenedOn;
		static private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		private List<Transaction> transactions = new ArrayList<Transaction>();

		public BankAccount(double openingBalance) {
			this.accountNumber = MeritBank.getNextAccountNumber();
			this.balance = openingBalance;
			this.interestRate = getInterestRate();
		}

		public BankAccount(double interestRate, double balance) {
			this.accountNumber = MeritBank.getNextAccountNumber();
			this.balance = balance;
			this.interestRate = interestRate;
		}

		public BankAccount(long accountNumber, double interestRate, double balance) {
			this.accountNumber = accountNumber;
			this.balance = balance;
			this.interestRate = interestRate;
		}

		public BankAccount(double interestRate, double balance, java.util.Date accountOpenedOn) {
			this.accountNumber = accountNumber;
			this.interestRate = interestRate;
			this.balance = balance;
			this.accountOpenedOn = accountOpenedOn;

		}

		public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
			this.accountNumber = accountNumber;
			this.interestRate = interestRate;
			this.balance = balance;
			this.accountOpenedOn = accountOpenedOn;
		}

		public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn,
				int term) {
			this.accountNumber = accountNumber;
			this.balance = balance;
			this.interestRate = interestRate;
			this.accountOpenedOn = accountOpenedOn;
			// TODO Auto-generated constructor stub
		}

		public java.util.Date accountOpenedOn() {


			return accountOpenedOn;
		}

		public java.util.Date getOpenedOn() {
			
			return accountOpenedOn;
		}
		
		public String writeToString() {
	
			return accountNumber + "," + this.getBalance();
		}

		public double futureValue(int years) {
//			 return balance * (Math.pow(1 + getInterestRate(), years));
			return MeritBank.recursiveFutureValue(balance, years, getInterestRate());
		}

		public boolean withdraw(double amount) {
			if (amount < 0) {
				System.out.println("You may not withdraw a negative amount.");
				return false;
			}
			
			
			if (amount > getBalance()) {
				System.out.println("amount: " + amount + ". getBalance: " + getBalance());
				System.out.println("There is not enough money in the checking account to make this withdrawal");
				return false;
			}
			balance = getBalance() - amount;
			return true;
		}

		public boolean deposit(double amount) {
			if (amount < 0) {
				System.out.println("You may not deposit a negative amount.");
				return false;
			}
			balance = getBalance() + amount;
			return true;
		}

		public double getInterestRate() {
			return this.interestRate;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

		public long getAccountNumber() {
			return this.accountNumber;
		}
		
		public String toString() {

			String string = "";
			
			string += ("Checking Account Balance: " + displayInUSD(getBalance()) + "\n" + 
					"Checking Account Interest Rate : " + String.format("%.5f", getInterestRate()) + " \n" +
					"Checking Account Balance in 3 years: " + displayInUSD(futureValue(3)) + "\n");
			
			return string;
		}

	/*
	 * returns the specified decimal formatted in United States Dollar
	 */
	public static String displayInUSD(double decimal) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

		return formatter.format(decimal);
	}

	public static java.util.Date getAccountOpenedOn() {
		return accountOpenedOn;
	}

	public static void setAccountOpenedOn(java.util.Date accountOpenedOn) {
		BankAccount.accountOpenedOn = accountOpenedOn;
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}	
}