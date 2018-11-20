package main.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.Account;
import main.java.AccountType;
import main.java.Client;
import main.java.Operation;
import main.java.OperationType;

class OperationTest {

	@Test
	public void testOperation() {
		// create a client example
		Client client = new Client("1", "KALLEL", "Mohamed-Amine", "klm@gmail.com");

		// create an account for given client with 0 balance and 100 overdrawn limit
		Account account = new Account("1", 0, client, AccountType.CURRENT, 100);
		Operation firstWithdrawl = new Operation("l", OperationType.WITHDRAWL, -200, account);
		Operation secondWithdrawl = new Operation("l", OperationType.WITHDRAWL, 200, account);
		// check operation amount is negative
		assertEquals(false, firstWithdrawl.checkOperationAmount());
		assertEquals(true, secondWithdrawl.checkOperationAmount());
	}
}
