package main.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.Account;
import main.java.Operation;
import main.java.OperationType;

public class AccountService implements AccountServiceInterface {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());

	private static AccountService instance;

	private AccountService() {
	}

	public static AccountService getInstance() {
		if (instance == null) {
			instance = new AccountService();
		}
		return instance;
	}

	// return true if the operation don ' t exceed the limit
	public boolean checkOverdrawnLimit(Account account, Operation operation) {
		return (OperationType.WITHDRAWL.equals(operation.getOperationType())
				&& account.getOverdrawnLimit() < operation.getAmount() - account.getBalance());
	}

	private void logSuccesOperation(Operation operation) {
		LOGGER.log(Level.INFO, operation.getIdoperation() + "TYPE : " + operation.getOperationType() + "AMOUNT : "
				+ operation.getAmount());
	}

	// execute the operation on a given account
	public void doOperation(Account account, Operation operation) {
		// check if operation amount is greater than zero
		if (!operation.checkOperationAmount()) {
			LOGGER.log(Level.WARNING, "Cannot have an operation with negative amount");
		} else {
			// if the operation is a deposit than do it
			if (OperationType.DEPOSIT.equals(operation.getOperationType())) {
				account.addAccountOperation(operation);
				account.setBalance(account.getBalance() + operation.getAmount());
				logSuccesOperation(operation);
			}
			// else check if the user doesn ' t exceed the overdrawn limit
			else {
				if (checkOverdrawnLimit(account, operation)) {
					LOGGER.log(Level.WARNING, "Overdrawn Limit exceeded");
				} else {
					account.addAccountOperation(operation);
					account.setBalance(account.getBalance() - operation.getAmount());
					logSuccesOperation(operation);
				}
			}
		}
	}
}
