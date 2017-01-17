package com.gottmusig.model;

import java.util.List;

import org.apache.wicket.model.AbstractReadOnlyModel;

import com.gottmusig.dpsdifference.domain.api.DPSDifference;
import com.gottmusig.dpsdifference.domain.api.SpecificationDPS;

public class SpecificationDPSListModel extends AbstractReadOnlyModel<List<SpecificationDPS>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SpecificationDPS> specDps;
	private int maxDps;

	public SpecificationDPSListModel(DPSDifference dpsDifference) {
		this.specDps = dpsDifference.getAllDPSValues();
		this.maxDps = dpsDifference.getMaxDPSValue();
	}
	
	@Override
	public List<SpecificationDPS> getObject() {
		return  specDps;
	}

	public int getMaxDPS() {
		return maxDps;
	}
	
}