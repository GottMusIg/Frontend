package com.gottmusig;

import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.gottmusig.database.service.domain.account.Account;
import com.gottmusig.database.service.domain.account.AccountService;

/**
 * These is the default {@link AuthenticatedWebSession} for the GottMusIg-Application.
 * 
 * @author kkalmus
 */
public class GottMusIgSession extends AuthenticatedWebSession {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Account account;
	
	public GottMusIgSession(Request request) {
		super(request);
	}

	public Account getAccount() {
		return account;
	}
	
	public boolean custumSignIn(String username, String password, AccountService accountService) {
		Optional<Account> gottMusIgAccount = accountService.searchAccount(username);
		if(gottMusIgAccount.isPresent() && gottMusIgAccount.get().getPassword().equals(password)) {
			this.account = gottMusIgAccount.get();
			signIn(true);
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean authenticate(String username, String password) {
		throw new NotImplementedException("Not implemented. ");
	}

	@Override
	public Roles getRoles() {
		return null;
	}

	@Override
	public void signOut() {
		super.signOut();
		account = null;
	}
	
}