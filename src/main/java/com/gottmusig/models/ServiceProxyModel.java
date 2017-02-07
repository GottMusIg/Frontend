/*
 * DefaultServiceModel.java 12.09.2012
 *
 * Copyright (c) 2012 1&1 Internet AG. All rights reserved.
 *
 * $Id$
 */
package com.gottmusig.models;

import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * These is an {@link AbstractReadOnlyModel} for Services 
 * which not supports {@link Serializable}.
 * 
 * @author kkalmus
 *
 * @param <S> an Service.
 */
public class ServiceProxyModel<S> extends AbstractReadOnlyModel<S>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private volatile S service;

    public ServiceProxyModel(S service) {
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