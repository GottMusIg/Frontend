package com.gottmusig;

import java.util.Optional;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.gottmusig.database.service.domain.account.Account;
import com.gottmusig.database.service.domain.account.AccountService;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
	
	private String username;
	
	public GottMusIgSession(Request request) {
		super(request);
	}

	public String getUsername() {
		return username;
	}
	
	public boolean custumSignIn(String username, String password, AccountService accountService) {
		Optional<Account> account = accountService.searchAccount(username);
		if(account.isPresent()) {
			if(account.get().getPassword().equals(password)) {
				this.username = username;
				signIn(true);
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected boolean authenticate(String username, String password) {
		throw new NotImplementedException();
	}

	@Override
	public Roles getRoles() {
		return null;
	}

	@Override
	public void signOut() {
		super.signOut();
		username = null;
	}
	
}