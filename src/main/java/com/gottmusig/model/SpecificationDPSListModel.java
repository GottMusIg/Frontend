package com.gottmusig.model;

import java.util.List;

import org.apache.wicket.model.AbstractReadOnlyModel;

import com.gottmusig.dpsdifference.domain.api.DPSDifference;
import com.gottmusig.dpsdifference.jpa.SpecificationDPSEntity;

public class SpecificationDPSListModel extends AbstractReadOnlyModel<List<SpecificationDPSEntity>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DPSDifference dpsDifference;

	public SpecificationDPSListModel(DPSDifference dpsDifference) {
		this.dpsDifference = dpsDifference;
	}
	
	@Override
	public List<SpecificationDPSEntity> getObject() {
		return  dpsDifference.getAllDPSValues();
	}

	public int getMaxDPS() {
		return dpsDifference.getMaxDPSValue();
	}
	
}