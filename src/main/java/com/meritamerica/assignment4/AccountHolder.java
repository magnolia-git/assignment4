package com.meritamerica.assignment4;

	public class AccountHolder implements Comparable<AccountHolder> {
		public static final long MAX_ALLOWED = 250000;
		private String firstName;
		private String middleName;
		private String lastName;
		private String ssn;

		private CheckingAccount[] checkingAccounts = new CheckingAccount[0];
		private SavingsAccount[] savingsAccounts = new SavingsAccount[0];
		private CDAccount[] cdAccounts = new CDAccount[0];
		private Transaction[] transactions = new Transaction[0];
		private FraudQueue[] frauds = new FraudQueue[0];

		public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
			super();
				this.firstName = firstName;
				this.middleName = middleName;
				this.lastName = lastName;
				this.ssn = ssn;
		}
		
// getters and setters
		
		public String getFirstName() {return firstName;}
		public void setFirstName(String firstName) {this.firstName = firstName;}

		public String getMiddleName() {return middleName;}
		public void setMiddleName(String middleName) {this.middleName = middleName;}

		public String getLastName() {return lastName;}
		public void setLastName(String lastName) {this.lastName = lastName;}

		public String getSSN() {return ssn;}
		public void setSsn(String ssn) {this.ssn = ssn;}

		public CheckingAccount[] getCheckingAccounts() {return checkingAccounts;}
		public void setCheckingAccount(CheckingAccount[] checkingAccount) {checkingAccounts = checkingAccount;}

		public SavingsAccount[] getSavingsAccounts() {return savingsAccounts;}
		public void setSavingsAccount(SavingsAccount[] savingsAccount) {savingsAccounts = savingsAccount;}
		
/* Makes a savings account with an opening balance as the argument. 
 * Returns the addSavingsAccount() method with the newly made account as an argument.
 */
		
		public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
			
			SavingsAccount newname = new SavingsAccount(openingBalance);
			
			if (getCombinedBalance() + openingBalance >= MAX_ALLOWED) {
				System.out.println("You have reached the maximum total balance across all accounts. Cannot create another.");
				throw new ExceedsCombinedBalanceLimitException();
			} else {
				return addSavingsAccount(newname);
			}
		}

/* Makes a new array for the savings accounts. It'll be one greater than the already existing array
 * so that the new savings account can be added to it.
 */
		
		public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException {
			if(getCombinedBalance() + savingsAccount.getBalance() >= MAX_ALLOWED) {
				System.out.println("You have reached the maximum total balance across all accounts. Cannot create another.");
				throw new ExceedsCombinedBalanceLimitException();
			} else {
				SavingsAccount[] newArray = new SavingsAccount[savingsAccounts.length + 1];
				int i;
				for (i = 0; i < savingsAccounts.length; i++) {
					newArray[i] = savingsAccounts[i];
				}
				newArray[i] = savingsAccount;
				savingsAccounts = newArray;
				return savingsAccount;
			}
		}
		
// Goes through the account holder's savings accounts and returns the sum of them.
		
		public double getSavingsBalance() {
			double savingsBalance = 0;
			for (int i = 0; i < savingsAccounts.length; i++) {
				System.out.println("Savings account number " + i + ": " + savingsAccounts[i].getBalance());
				savingsBalance += savingsAccounts[i].getBalance();
			}
		return savingsBalance;
	}
		
/* Makes a checking account with an opening balance as the argument. 
 * Returns the addCheckingAccount() method with the newly made account as an argument.
 */
		
		public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
			CheckingAccount newname = new CheckingAccount(openingBalance);
			try {
				if(getCombinedBalance() + openingBalance >= MAX_ALLOWED) {
					System.out.println("You have reached the maximum total balance across all accounts. Cannot create another.");
					throw new ExceedsCombinedBalanceLimitException("You have reached the maximum total balance across all accounts. Cannot create another.");
				} else {
					// Just added this as per the Assignment 4 instructions
					newname.deposit(openingBalance);
					return addCheckingAccount(newname);
				}
			} catch (Exception e) {
				
			}
		}

		public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException{
			if(getCombinedBalance() + checkingAccount.getBalance() >= MAX_ALLOWED) {
				System.out.println("You have reached the maximum total balance across all accounts. Cannot create another.");
				throw new ExceedsCombinedBalanceLimitException();
			} else {
				CheckingAccount[] newArray = new CheckingAccount[checkingAccounts.length + 1];
				int i;
				for (i = 0; i < checkingAccounts.length; i++) {
					newArray[i] = checkingAccounts[i];
				}
			
				newArray[i] = checkingAccount;
				checkingAccounts = newArray;
				System.out.println("Added checking account to array");
				return checkingAccount;
			}
		}

		public double getCheckingBalance() {
		double checkingBalance = 0;
		

		for (int i = 0; i < checkingAccounts.length; i++) {
			System.out.println("CheckingAccount balance number " + i + ": " + checkingAccounts[i].getBalance());
			checkingBalance = checkingBalance + checkingAccounts[i].getBalance();
			
		}
		System.out.println("Checkingbalance: " + checkingBalance);
		return checkingBalance;
	}

	
	

	public CDAccount[] getCDAccounts() {return cdAccounts;}
	

	
	// This first one creates a new CDAccount...
	
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) {
	CDAccount newName = new CDAccount(offering, openingBalance);
	return addCDAccount(newName);
	}
	
	// ... and this second one adds it to the CDAccount array.
	
	public CDAccount addCDAccount(CDAccount cdAccount) {
		CDAccount[] newArray = new CDAccount[cdAccounts.length + 1];
		int i;
		for (i = 0; i < cdAccounts.length; i++) {
			newArray[i] = cdAccounts[i];
		}
		newArray[i] = cdAccount;
		cdAccounts = newArray;
		return cdAccount;
	}



	public double getCDBalance() {

		double sum = 0;

		for (int i = 0; i < cdAccounts.length; i++) {
			System.out.println("CDAccount balance number " + i + ": " + cdAccounts[i].getBalance());
			sum += cdAccounts[i].getBalance();

		}
//		}
		System.out.println("CDBalance sum: " + sum);
		return sum;
	}


	public double getCombinedBalance() {
		double combinedBalance = 0;
		System.out.println("Combined: " + (getCheckingBalance() + getSavingsBalance() + getCDBalance()));
		combinedBalance += getCheckingBalance();
		combinedBalance += getSavingsBalance();
		combinedBalance += getCDBalance();
		System.out.println("Combined: " + combinedBalance);
//		return combinedBalance;
		return getCheckingBalance() + getSavingsBalance() + getCDBalance();
	}

	public int getNumberOfCheckingAccounts() {
		System.out.println("Number of checking accounts: " + checkingAccounts.length);
		return checkingAccounts.length;
		
	}

	public int getNumberOfSavingsAccounts() {
		System.out.println("Number of savings accounts: " + savingsAccounts.length);
		return savingsAccounts.length;
	}

	public int getNumberOfCDAccounts() {
		System.out.println("Number of cd accounts: " + cdAccounts.length);
			return cdAccounts.length;		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * returns the state of the object as a string.
	 */
	@Override
	public String toString() {
		return generateStringForToString();
	}

	
	private String generateStringForToString() {
		StringBuilder str = new StringBuilder();

		// append the name
		str.append("Name: " + getFirstName() + " " + getMiddleName() + " " + getLastName() + "\n");

		
		str.append("SSN: " + getSSN() + "\n");

		
		str.append(getCheckingAccounts().toString());

		// append savings account balance
		// append savings account interest rate
		// append the savings account balance in 3 years
		str.append(getSavingsAccounts().toString());

		// return the StringBuilder object as a string.
		return str.toString();
	}

	
/* compareTo compares this account with another account. If this account's combined balance is
 * greater than the other account, then it returns 1. If not, then it returns -1.
 */
	@Override
	public int compareTo(AccountHolder otherAccountHolder) {
		if(this.getCombinedBalance() > otherAccountHolder.getCombinedBalance()) return 1;
		else return -1;
	}
	
	public String writeToString() {
		return firstName + "," + middleName + "," + lastName + "," + ssn;
	}
	
	public static AccountHolder readFromString(String accountHolderData) throws Exception {
		System.out.println("reading from string in account holder.");
		String[] str = accountHolderData.split(",");
		System.out.println(str[0] + str[1] + str[2] + str[3]);
		return new AccountHolder(str[0],str[1], str[2], str[3]);
	}
	
}