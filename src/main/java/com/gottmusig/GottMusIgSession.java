package com.gottmusig;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

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