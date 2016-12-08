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
	
	private List<SpecificationDPSEntity> specDps;
	private int maxDps;

	public SpecificationDPSListModel(DPSDifference dpsDifference) {
		this.specDps = dpsDifference.getAllDPSValues();
		this.maxDps = dpsDifference.getMaxDPSValue();
	}
	
	@Override
	public List<SpecificationDPSEntity> getObject() {
		return  specDps;
	}

	public int getMaxDPS() {
		return maxDps;
	}
	
}