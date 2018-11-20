package main.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.Account;
import main.java.AccountType;
import main.java.Client;
import main.java.Operation;
import main.java.OperationType;
import main.service.AccountService;

class AccountTest {
	Client client;
	private static AccountService accountService;

	@Test
	public void testOverdrawLimit() {
		// create a client example
		client = new Client("1", "KALLEL", "Mohamed-Amine", "mkl@gmail.com");
		accountService = AccountService.getInstance();

		// create an account for given client with 0 balance and 100 overdrawn limit
		Account account = new Account("1 ", 0, client, AccountType.CURRENT, 100);
		// create different operation withdraw of 200
		Operation firstWithdrawl = new Operation("l", OperationType.WITHDRAWL, 200, account);
		// operation should exceed the overdrawn limit
		boolean checkOverdrawnLimit = accountService.checkOverdrawnLimit(account, firstWithdrawl);

		assertEquals(true, checkOverdrawnLimit);
		// create different operation withdraw of 50
		Operation secondwithdrawl = new Operation("1", OperationType.WITHDRAWL, 50, account);
		checkOverdrawnLimit = accountService.checkOverdrawnLimit(account, secondwithdrawl);
		// operation should exceed the overdrawn limit
		assertEquals(false, checkOverdrawnLimit);
	}

}
