package main.service;

import main.java.Account;
import main.java.Operation;

public interface AccountServiceInterface {

	public boolean checkOverdrawnLimit(Account account, Operation operation);

	public void doOperation(Account account, Operation operation);
}
