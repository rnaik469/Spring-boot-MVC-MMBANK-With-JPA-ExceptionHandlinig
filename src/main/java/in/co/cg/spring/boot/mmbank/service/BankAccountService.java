package in.co.cg.spring.boot.mmbank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.cg.spring.boot.mmbank.dao.BankAccountRepository;
import in.co.cg.spring.boot.mmbank.exception.CustomerNotFoundException;
import in.co.cg.spring.boot.mmbank.exception.InsufficientBalanceException;
import in.co.cg.spring.boot.mmbank.pojo.BankAccount;

/**
 * @author Rohit Naik
 *
 */
@Service
public class BankAccountService implements BankAccountServiceI {

	@Autowired
	private BankAccountRepository accountDAO; // Instantiating BankAccountDAO

	private BankAccount customer = null; // creating reference of BankAccount
	private BankAccount Bsender; // creating reference of BankAccount
	private BankAccount Breceiver; // creating reference of BankAccount

	/*
	 * (non-Javadoc) Add New account
	 * 
	 * @see
	 * in.co.cg.spring.boot.mmbank.service.BankAccountServiceI#AddNewAccount(in.co.
	 * cg.mmbank. pojo.BankAccount)
	 * 
	 */
	@Override
	public void AddNewAccount(BankAccount account) {
		accountDAO.save(account);
	}

	/*
	 * (non-Javadoc) Search account
	 * 
	 * @return object of BankAccount if found or null if not
	 * 
	 * @see
	 * in.co.cg.spring.boot.mmbank.service.BankAccountServiceI#searchAccount(int)
	 */
	@Override
	public BankAccount searchAccount(int accountNO) throws CustomerNotFoundException {
		try {
			return accountDAO.findById(accountNO).get();
		}

		catch (NoSuchElementException |NullPointerException exception) {
			throw new CustomerNotFoundException("Customer Not Found");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @@return account number
	 * 
	 * @see in.co.cg.spring.boot.mmbank.service.BankAccountServiceI#getAccountNo()
	 */
	@Override
	public int getAccountNo() {
		System.out.println(BankAccount.getNextAccountNumber());
		return BankAccount.getNextAccountNumber();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @returns all accounts
	 * 
	 * @see in.co.cg.spring.boot.mmbank.service.BankAccountServiceI#viewAccount()
	 */
	@Override
	public List<BankAccount> viewAccount() {

		return accountDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @returns all customers
	 * 
	 * @see in.co.cg.spring.boot.mmbank.service.BankAccountServiceI#viewCustomer()
	 */

	/*
	 * (non-Javadoc) Deposit to account
	 * 
	 * @return account object
	 * 
	 * @see in.co.cg.spring.boot.mmbank.service.BankAccountServiceI#deposit(double,
	 * int)
	 */
	@Override
	public BankAccount deposit(double amount, int accNo) {
		customer = searchAccount(accNo);
		customer.deposit(amount);
		accountDAO.save(customer);
		return customer;
	}

	/*
	 * (non-Javadoc) withdraw to account
	 * 
	 * @return account object
	 * 
	 * @see
	 * in.co.cg.spring.boot.mmbank.service.BankAccountServiceI#withdrawl(double,
	 * int)
	 */
	@Override
	public BankAccount withdrawl(double amount, int accNo) {
		try {
			customer = searchAccount(accNo);
			customer.withdraw(amount);
			accountDAO.save(customer);
			return customer;
		} catch (InsufficientBalanceException exception) {
			throw new InsufficientBalanceException("Insufficent Balance");
		}
	}

	/*
	 * (non-Javadoc) update customer info
	 * 
	 * @see in.co.cg.spring.boot.mmbank.service.BankAccountServiceI#updateInfo(int,
	 * long, java.time.LocalDate, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateInfo(int accountNO, long contactNumber, LocalDate dateOfBirth, String customerName,
			String emailId) throws CustomerNotFoundException {

	}

	/*
	 * (non-Javadoc) transfer amount
	 * 
	 * @returns false if account not found or insufficient amount
	 * 
	 * @see
	 * in.co.cg.spring.boot.mmbank.service.BankAccountServiceI#Fundtransfer(int,
	 * int, double, java.lang.String)
	 */
	@Override
	public BankAccount Fundtransfer(int sender, int reciever, double amount) {
		Bsender = searchAccount(sender);
		Breceiver = searchAccount(reciever);
		System.out.println(Bsender + " --------------  " + Breceiver);
		Bsender.withdraw(amount);
		accountDAO.save(Bsender);
		Breceiver.deposit(amount);
		accountDAO.save(Breceiver);
		return Breceiver;

	}

}
