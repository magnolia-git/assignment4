package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class MeritBank {

	private static AccountHolder[] accountHolders;
	private static CDOffering[] cdOfferings;
	static long masterAccountNumber = 000000000;

	public static void addAccountHolder(AccountHolder accountHolder) {
		AccountHolder[] newArray = new AccountHolder[accountHolders.length + 1];
		int i = 0;
		for (i = 0; i < accountHolders.length; i++) {
			newArray[i] = accountHolders[i];
		}
		newArray[i] = accountHolder;
		accountHolders = newArray;
	}

	public static AccountHolder[] getAccountHolders() {
//		System.out.println("Account holders " + MeritBank.accountHolders.length);
		return accountHolders;
	}

	public static CDOffering[] getCDOfferings() {

		return cdOfferings;
	}

	public static CDOffering getBestCDOffering(double depositAmount) {
		CDOffering offering = new CDOffering();

		return offering;
	}

	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		CDOffering offering = new CDOffering();

		return offering;
	}

	public static void clearCDOfferings() {

		cdOfferings = new CDOffering[0];
			
		}


	public static void setCDOfferings(CDOffering[] offerings) {
		cdOfferings = new CDOffering[5];
		cdOfferings[0] = new CDOffering(1, 1.8 / 100);
		cdOfferings[1] = new CDOffering(2, 1.9 / 100);
		cdOfferings[2] = new CDOffering(3, 2.0 / 100);
		cdOfferings[3] = new CDOffering(5, 2.5 / 100);
		cdOfferings[4] = new CDOffering(10, 2.2 / 100);
	}

	static long getNextAccountNumber() {
		return masterAccountNumber;
	}

	public static double totalBalances() {
		double totalBalance = 0;

		return totalBalance;
	}
	
	public static boolean readFromFile(String fileName) {
		File input = new File(fileName);

		try (BufferedReader bufferRead = new BufferedReader(new FileReader(input))){

			masterAccountNumber = Long.valueOf(bufferRead.readLine());		// Sets the masterAccountNumber to 11
			
			// Reads the line with the number of CDOfferings...
			CDOffering[] newOfferings = new CDOffering[Integer.valueOf(bufferRead.readLine())];	// Creates new CDOffering array, sets it to 3
			
			for (int i = 0; i < newOfferings.length; i++) {
				// Assigns 
				newOfferings[i] = CDOffering.readFromString(bufferRead.readLine());

			}
			cdOfferings = newOfferings;
			
			AccountHolder[] newAccountHolders = new AccountHolder[Integer.valueOf(bufferRead.readLine())];
			for (int i = 0; i < newAccountHolders.length; i++) {
				newAccountHolders[i] = AccountHolder.readFromString(bufferRead.readLine());


				CheckingAccount[] newCheckingAccounts = new CheckingAccount[Integer.valueOf(bufferRead.readLine())];
				for (int j = 0; j < newCheckingAccounts.length; j++) {
					newAccountHolders[i].addCheckingAccount(CheckingAccount.readFromString(bufferRead.readLine()));

				}

				SavingsAccount[] newSavingsAccounts = new SavingsAccount[Integer.valueOf(bufferRead.readLine())];
				for (int j = 0; j < newSavingsAccounts.length; j++) {
					newAccountHolders[i].addSavingsAccount(SavingsAccount.readFromString(bufferRead.readLine()));
				}
				
				CDAccount[] newCDAccounts = new CDAccount[Integer.valueOf(bufferRead.readLine())];
				for (int j = 0; j < newCDAccounts.length; j++) {
					newAccountHolders[i].addCDAccount(CDAccount.readFromString(bufferRead.readLine()));
				}
			}
			accountHolders = newAccountHolders;

			return true;

		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found!");

		} catch (IOException e) {
			System.out.println("Error: Input / output error!");

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeToFile(String fileName) {
		try {
            FileWriter fr = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fr);

            bw.write(String.valueOf(masterAccountNumber));				// 11
            bw.newLine();
            bw.write(String.valueOf(cdOfferings.length));				// 3
            bw.newLine();
            for(int i = 0; i < cdOfferings.length; i++) {
                bw.write(cdOfferings[i].writeToString());
                bw.newLine();
            }
            bw.write(String.valueOf(accountHolders.length));
            bw.newLine();
            for(int i = 0; i < accountHolders.length; i++) {
                bw.write(accountHolders[i].writeToString());
                bw.newLine();
                bw.write(accountHolders[i].getNumberOfCheckingAccounts());
                for(int j = 0; j < accountHolders[i].getNumberOfCheckingAccounts();j++) {
                    bw.write(String.valueOf(accountHolders[i].getCheckingAccounts()[j].writeToString()));
                    bw.newLine();
                }
                for(int k = 0; k < accountHolders[i].getNumberOfSavingsAccounts();k++) {
                    bw.write(String.valueOf(accountHolders[i].getSavingsAccounts()[k].writeToString()));
                    bw.newLine();
                }
                for(int g = 0; g < accountHolders[i].getNumberOfCDAccounts();g++) {
                    bw.write(String.valueOf(accountHolders[i].getCDAccounts()[g].writeToString()));
                    bw.newLine();
                }
            }
            bw.close();
            return true;
		
        } catch (IOException e) {
            return false;
        }
	}
	
	public static AccountHolder[] sortAccountHolders() {
		
		Arrays.sort(accountHolders);
		return accountHolders;
		
	}
	
	public static void setNextAccountNumber(long nextAccountNumber) {
		nextAccountNumber = getNextAccountNumber() + 1;
	}
	
	// Existing futureValue methods that used to call Math.pow() should now call this method
	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		return 0.0;
	}
	
	public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		/*
		 * If transaction does not violate any constraints, deposit/withdraw values from the relevant BankAccounts and add the transaction to the relevant BankAccounts
		 * 
		 * If the transaction violates any of the basic constraints (negative amount, exceeds available balance) the relevant exception should be thrown and the processing should terminate
		 * 
		 * If the transaction violates the $1,000 suspicion limit, it should simply be added to the FraudQueue for future processing
		 * 
		 */
		return false;
	}
	
	public static FraudQueue getFraudQueue() {
		return null;
	}
	
	// Return null if account not found
	public static BankAccount getBankAccount(long accountId) {
		return null;
	}

}