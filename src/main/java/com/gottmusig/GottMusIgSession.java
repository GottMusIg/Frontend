package com.gottmusig;

import java.util.Optional;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.gottmusig.account.domain.api.Account;
import com.gottmusig.account.domain.api.AccountAdministration;

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
	
	public boolean custumSignIn(String username, String password, AccountAdministration accAdmin) {
		Optional<Account> account = accAdmin.searchAccount(username);
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
		if(username.equals("Test") 
		   && password.equals("6e88464163c155be27bdba872861b7188a7ea1486c9fd66d82e50754ea916514")) {
			this.username = username;
			signIn(true);
			return true;
		}
		return false;
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