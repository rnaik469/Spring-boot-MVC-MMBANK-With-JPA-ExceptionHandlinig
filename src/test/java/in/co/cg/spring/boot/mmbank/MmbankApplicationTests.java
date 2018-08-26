package in.co.cg.spring.boot.mmbank;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import in.co.cg.spring.boot.mmbank.exception.CustomerNotFoundException;
import in.co.cg.spring.boot.mmbank.exception.InsufficientBalanceException;
import in.co.cg.spring.boot.mmbank.service.BankAccountService;
import in.co.cg.spring.boot.mmbank.service.BankAccountServiceI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MmbankApplicationTests {

	BankAccountServiceI service = new BankAccountService();

	@Test(expected = CustomerNotFoundException.class)
	public void CustomerNotFoundDuringDeposit() {
		service.deposit(100, 105);
	}

	@Test(expected = CustomerNotFoundException.class)
	public void CustomerNotFoundDuringWithdrawl() {
		service.withdrawl(100, 105);
	}

	@Test(expected = InsufficientBalanceException.class)
	public void InsufficientBalanceInWithdrawl() {
		assertEquals(null,service.withdrawl(100, 101));
	}

	@Test(expected = CustomerNotFoundException.class)
	public void CustomerNotFoundDuringFundTransfer() {
		service.Fundtransfer(105, 102, 1000);
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void InsufficientBalanceDuringFundTransfer() {
		service.Fundtransfer(102, 103, 10000);
	}
	
	@Test()
	public void Deposit() {

		service.deposit(1000, 101);
	}

	@Test()
	public void Withdrawl() {
		service.withdrawl(100, 103);
	}

	@Test()
	public void FundTransfer() {
		service.Fundtransfer(103, 101, 100);
	}

}
