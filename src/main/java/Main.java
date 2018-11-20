package main.java;

import java.time.LocalDateTime;

import main.service.AccountService;
import main.service.StatementService;

public class Main {
	static AccountService accountService = AccountService.getInstance();
	static StatementService statementService = StatementService.getInstance();

	public static void main(String[] args) {
		// create a client example
		Client client = new Client("1", "KALLEL", "Mohamed_Amine", "klm@gmail");

		// create an account for given client with 800 as starting balance 200 overdrawn
		// limits
		Account account = new Account("1 ", 800, client, AccountType.CURRENT, 200);
		// create different operations type with different amounts to play different
		// scenarios

		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime lastMonth = currentDate.minusMonths(1);
		LocalDateTime lastQuarter = currentDate.minusMonths(3);
		LocalDateTime lastHalf = currentDate.minusMonths(6);

		Operation monthTestOperation = new Operation("1", OperationType.WITHDRAWL, 100, account);
		Operation halfTestOperation = new Operation("2", OperationType.WITHDRAWL, 200, account);
		Operation thirdWithdrawl = new Operation("3 ", OperationType.WITHDRAWL, 2000, account);
		Operation statementTestOperation = new Operation("4 ", OperationType.DEPOSIT, 100, account);
		Operation quarterTestOperation = new Operation("5", OperationType.DEPOSIT, 200, account);
		Operation negatifAmountTestOperation = new Operation("6", OperationType.DEPOSIT, -300, account);
		Operation lastYearTestOperation = new Operation("7", OperationType.DEPOSIT, 300, account);

		// test statement
		statementTestOperation.setOperationDate(currentDate.minusHours(1));
		accountService.doOperation(account, statementTestOperation);
		statementService.getStatement(account, currentDate.minusDays(1), currentDate);
		
		
		// test negative amount operations
		accountService.doOperation(account, negatifAmountTestOperation);
		statementService.getStatement(account, LocalDateTime.now().minusDays(1), LocalDateTime.now());
		// test exceed overdrawn limit
		accountService.doOperation(account, thirdWithdrawl);
		statementService.getStatement(account, LocalDateTime.now().minusDays(1), LocalDateTime.now());

		// test Last Month Statement
		monthTestOperation.setOperationDate(currentDate.minusDays(2));
		accountService.doOperation(account, monthTestOperation);
		statementService.getLastMonthStatement(account);
		// test Last 3 Months Statement
		quarterTestOperation.setOperationDate(lastMonth);
		accountService.doOperation(account, quarterTestOperation);
		statementService.getLastQuarterStatement(account);
		// test Last 6 Months Statement
		halfTestOperation.setOperationDate(lastQuarter);
		accountService.doOperation(account, halfTestOperation);
		statementService.getLastHalfStatemennt(account);
		// test Last Year Statement
		lastYearTestOperation.setOperationDate(lastHalf);
		accountService.doOperation(account, lastYearTestOperation);
		statementService.getLastYearStatement(account);

	}
}
