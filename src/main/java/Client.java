package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {

	private String idClient;
	private String lastName;
	private String firstName;
	private String mail;
	private List<Account> accountList;
	
	

	public Client(String idClient, String lastName, String firstName, String mail) {
		super();
		this.idClient = idClient;
		this.lastName = lastName;
		this.firstName = firstName;
		this.mail = mail;
	}

	/**
	 * @return the idClient
	 */
	public String getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the accountList
	 */
	public List<Account> getAccountList() {
		return accountList;
	}

	/**
	 * @param accountList the accountList to set
	 */
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public void addAccount(Account account) {
		if (accountList == null) {
			this.accountList = new ArrayList<>();
		}
		accountList.add(account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(accountList, firstName, idClient, lastName, mail);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(accountList, other.accountList) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(idClient, other.idClient) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(mail, other.mail);
	}

}
