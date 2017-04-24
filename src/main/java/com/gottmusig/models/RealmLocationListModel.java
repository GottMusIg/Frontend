package com.gottmusig.models;

import java.util.List;

import org.apache.wicket.model.AbstractReadOnlyModel;

import com.gottmusig.database.service.domain.realm.jpa.Location;

/**
 * {@link AbstractReadOnlyModel} for {@link Location}.
 * 
 * @author kkalmus
 */
public class RealmLocationListModel extends AbstractReadOnlyModel<List<Location>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Location> locations;

	public RealmLocationListModel(List<Location> locations) {
		this.locations = locations;
	}
	
	@Override
	public List<Location> getObject() {
		return locations;
	}
	
}