package in.co.cg.spring.boot.mmbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.co.cg.spring.boot.mmbank.exception.CustomerNotFoundException;
import in.co.cg.spring.boot.mmbank.exception.InsufficientBalanceException;
import in.co.cg.spring.boot.mmbank.pojo.BankAccount;
import in.co.cg.spring.boot.mmbank.service.BankAccountService;


//MMBANK controller performs view, deposit, withdraw, fund transfer, search account
@RestController
public class BankController {

	ResponseEntity<BankAccount> entity; //Creating reference of ResponseEntity<BankAccount> 
	@Autowired
	BankAccountService accountService; //Creating reference of BankAccountService

	@RequestMapping(value = "/viewaccount", method = RequestMethod.GET)
	public List<BankAccount> viewAllAccount() {
		return accountService.viewAccount(); //Invoking View All customer
	}

	@RequestMapping(value = "/deposit/{acNO}/{amt}", method = RequestMethod.POST)
	public ResponseEntity<BankAccount> depositAmount(@PathVariable("acNO") int acNO,
			@PathVariable("amt") double c_amount) throws CustomerNotFoundException, InsufficientBalanceException {
		try {
			entity = new ResponseEntity<BankAccount>(accountService.deposit(c_amount, acNO), HttpStatus.OK);//Invoking Deopsit and set response to ok i.e. 200
		} catch (CustomerNotFoundException exception) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); //Set response to not found i.e. 404
		} catch (InsufficientBalanceException exception) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); //Set response to not found i.e. 406
		}
		return entity;
	}

	@RequestMapping(value = "/withdraw/{acNo}/{amount}", method = RequestMethod.POST)
	public ResponseEntity<BankAccount> withdrawAmount(@PathVariable("acNo") int acNo,
			@PathVariable("amount") double amount) {

		try {
			entity = new ResponseEntity<BankAccount>(accountService.withdrawl(amount, acNo), HttpStatus.OK); //Invoking Withdrawl and set response to ok i.e. 200
		 } catch (CustomerNotFoundException exception) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); //Set response to not found i.e. 404
		} catch (InsufficientBalanceException exception) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); //Set response to not found i.e. 406
		}
		return entity;
	}

	@RequestMapping(value = "/fundtransfer/{sender}/{reciever}/{amount}", method = RequestMethod.POST)
	public ResponseEntity<BankAccount> fundT(@PathVariable("sender") int sender, @PathVariable("reciever") int reciever,
			@PathVariable("amount") double amount) {
		try {
			entity = new ResponseEntity<BankAccount>(accountService.Fundtransfer(sender, reciever, amount), //Invoking Fundtransfer and set response to ok i.e. 200
					HttpStatus.OK);
		 } catch (CustomerNotFoundException exception) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); //Set response to not found i.e. 404
			} catch (InsufficientBalanceException exception) {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE); //Set response to not found i.e. 406
			}
		return entity;
	}

	@RequestMapping(value = "/searchCustomer/{accno}", method = RequestMethod.POST)
	public ResponseEntity<BankAccount> searchCust(@PathVariable("accno") int accno) {
		try {
			entity = new ResponseEntity<BankAccount>(accountService.searchAccount(accno), HttpStatus.OK); //Invoking Search Account and set response to ok i.e. 200
		} catch (CustomerNotFoundException exception) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); //Set response to not found i.e. 404
		}
		return entity;
	}

}
