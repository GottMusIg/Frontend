package com.gottmusig.models;

import java.io.ObjectOutputStream;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class AccountServiceProxyModel<S> extends AbstractReadOnlyModel<S>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private volatile S service;

    public AccountServiceProxyModel(S service) {
        this.service = service;
    }

    @Override
    public S getObject() {
        return getService();
    }

    public S getService() {
        return service;
    }
    public void setService(S service) {
        this.service = service;
    }

    private void writeObject(ObjectOutputStream stream) {
        throw new RuntimeException("Trying to serialize a ServiceProxy with service: '" + service + "'!");
    }
    
}