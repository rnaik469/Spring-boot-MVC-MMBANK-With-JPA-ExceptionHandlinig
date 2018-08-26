//This abstract class will be implemented by various types of BankAccount.

package in.co.cg.spring.boot.mmbank.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import in.co.cg.spring.boot.mmbank.exception.InsufficientBalanceException;

@Table
@Entity
public class BankAccount {
	@Id
	private final int accountNumber;

	private String accountHolder;
	private double accountBalance;
	private String accountType;
	// maintains the next accountNumber
	private static int accountId;

	// gets invoked whenever a class is loaded
	static {
		accountId = 100;
	}

	// gets invoked whenever a new object is created
	{
		this.accountNumber = ++accountId;
	}

	// For Non-Zero Balance Account.
	public BankAccount(String accountHolder, double accountBalance, String accountType) {
		this.accountHolder = accountHolder;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}

	// For Zero Balance account holders
	public BankAccount(String accountHolder, String accountType) {
		this.accountHolder = accountHolder;
		this.accountType = accountType;
	}

	public BankAccount() {

	}

	// getters and setters
	public int getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	// Method to deposit the amount.
	public void deposit(double amountToBeDeposited) {
		if (amountToBeDeposited > 0) {
			this.accountBalance += amountToBeDeposited;
		} else {
			throw new InsufficientBalanceException("Balance must be +ve");
		}
	}

	public void withdraw(double amountToBeWithDrawn) {
		if (accountBalance >= amountToBeWithDrawn) {
			accountBalance -= amountToBeWithDrawn;
		} else {
			throw new InsufficientBalanceException("");
		}
	}

	// It will give the next account number that will assigned to next account
	// object
	public static int getNextAccountNumber() {
		return accountId + 1;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolder
				+ ", accountBalance=" + accountBalance + "]";
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
