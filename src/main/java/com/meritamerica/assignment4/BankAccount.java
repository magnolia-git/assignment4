package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java. util. ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


	public abstract class BankAccount {
		public double interestRate = 0;
		private double balance = 0;
		protected static long accountNumber = 0;
		private static java.util.Date accountOpenedOn;
		static private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		public BankAccount() {
			accountNumber++;
			System.out.println("A bank account is made.");
		}

		public BankAccount(double openingBalance) {
			accountNumber++;
			this.balance = openingBalance;
			this.interestRate = getInterestRate();
			System.out.println("A bank account is made.");
		}

		public BankAccount(double interestRate, double balance) {
			accountNumber++;
			this.balance = balance;
			this.interestRate = interestRate;
			System.out.println("A bank account is made.");
		}

		public BankAccount(long accountNumber, double interestRate, double balance) {
			accountNumber++;
			BankAccount.accountNumber = accountNumber;
			this.balance = balance;
			System.out.println("A bank account is made.");
		}

	// Made accountOpenedOn static and created getters and setters
		public BankAccount(double interestRate, double balance, java.util.Date accountOpenedOn) {
			accountNumber++;
			this.interestRate = interestRate;
			this.balance = balance;
			this.accountOpenedOn = accountOpenedOn;
			System.out.println("A bank account is made.");

		}

		public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
			this.accountNumber = accountNumber++;
			this.interestRate = interestRate;
			this.balance = balance;
			this.accountOpenedOn = accountOpenedOn;
			System.out.println("A bank account is made.");
		}

		public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn,
				int term) {
			accountNumber = accountNumber++;
			this.balance = balance;
			this.interestRate = interestRate;
			this.accountOpenedOn = accountOpenedOn;
			// TODO Auto-generated constructor stub
			System.out.println("A bank account is made.");
		}

		public java.util.Date accountOpenedOn() {


			return accountOpenedOn;
		}

		public java.util.Date getOpenedOn() {
			
			return accountOpenedOn;
		}

/*
		public BankAccount writeToFile() throws IOException{
			
			// Error here after making BankAccount class abstract
			BankAccount obj = new BankAccount();
			File input = new File("accounts.txt");
			try {
				FileOutputStream fos = new FileOutputStream(input);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(obj);	
				
			} catch(FileNotFoundException ex11) {
				System.out.println("Error: file not found.");
			} catch (IOException ex1) {
				System.out.println("Error: incorrect input / output!");
			}
		// not sure what to return here
			return obj;
			
		}
*/
/*
		public static BankAccount readFromString(String accountData) throws ParseException {
			BankAccount bankAcc;
			try {
				ArrayList<String> aL = new ArrayList<>(Arrays.asList(accountData.split(",")));
				
				// Error here after making BankAccount class abstract
				bankAcc = new BankAccount(Long.parseLong(aL.get(0)), Double.parseDouble(aL.get(1)), Double.parseDouble(aL.get(2)), dateFormat.parse(aL.get(3)));
			} catch (ParseException e) {
				throw new java.lang.NumberFormatException();
			}
			return bankAcc;
		}
*/
	

		
		public String writeToString() {
	
			return accountNumber + "," + this.getBalance();
		}

		public double futureValue(int years) {

			// return balance * (Math.pow(1 + getInterestRate(), years));
			return MeritBank.recursiveFutureValue(balance, years, getInterestRate());
		}

		public boolean withdraw(double amount) {
			if (amount < 0) {
				System.out.println("You may not withdraw a negative amount.");
				return false;
			}
			if (amount > getBalance()) {
				System.out.println("There is not enough money in the checking account " + "to make this widrawal");
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
			return accountNumber++;
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
		
	}
	
	public List<Transaction> getTransactions() {
		return null;
		
	}
	
}
