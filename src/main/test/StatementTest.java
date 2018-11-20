package main.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import main.java.Account;
import main.java.AccountType;
import main.java.Client;
import main.java.Operation;
import main.java.OperationType;
import main.service.StatementService;

class StatementTest {

	private LocalDateTime currentDate;
	private LocalDateTime lastQuarter;
	private LocalDateTime lastHalf;
	private LocalDateTime lastYear;
	Client client;
	private static StatementService statemntService;

	@Test
	public void operationPeriod() {
		statemntService = StatementService.getInstance();
		client = new Client("1", "KALLEL", "Mohamed-Amine", "klm@gmail.com");
		currentDate = LocalDateTime.now();
		lastYear = currentDate.minusYears(1);
		lastQuarter = currentDate.minusMonths(3);
		lastHalf = currentDate.minusMonths(6);
		// create an account for given client with 0 balance and 100 overdrawn limit
		Account account = new Account("1 ", 0, client, AccountType.CURRENT, 100);
		Operation firstWithdrawl = new Operation("1", OperationType.WITHDRAWL, 200, account);
		account.addAccountOperation(firstWithdrawl);
		firstWithdrawl.setOperationDate(lastHalf);
		System.out.println(statemntService);
		boolean firstWithdrawlNotIncludedInperiod = statemntService
				.filterOperationsInPeriod(account.getAccountOperations(), lastQuarter, currentDate)
				.contains(firstWithdrawl);

		boolean firstWithdrawlIncludedInperiod = statemntService
				.filterOperationsInPeriod(account.getAccountOperations(), lastYear, currentDate)
				.contains(firstWithdrawl);
		assertEquals(true, firstWithdrawlIncludedInperiod);
		assertEquals(false, firstWithdrawlNotIncludedInperiod);
	}

	@Test
	public void testSumOperations() {
		// Create an account for a given client with balance 0 and 100 overdrawn Limit
		Account account = new Account("1", 0, client, AccountType.CURRENT, 100);
		Operation firstWithdrawl = new Operation("1", OperationType.WITHDRAWL, 200, account);
		account.addAccountOperation(firstWithdrawl);
		Operation secondWithdrawl = new Operation("2", OperationType.WITHDRAWL, 200, account);
		account.addAccountOperation(secondWithdrawl);
		Operation firstDeposit = new Operation("3", OperationType.DEPOSIT, 400, account);
		account.addAccountOperation(firstDeposit);
		Operation secondDeposit = new Operation("4", OperationType.DEPOSIT, 300, account);
		account.addAccountOperation(secondDeposit);
		double totalDeposit = statemntService.sumDepositsInPeriod(account.getAccountOperations());
		double totalWithdrawl = statemntService.sumWithdrawlsInPeriod(account.getAccountOperations());
		// compare values with delta 0
		assertEquals(400, totalWithdrawl);
		assertEquals(700, totalDeposit);
	}

}
