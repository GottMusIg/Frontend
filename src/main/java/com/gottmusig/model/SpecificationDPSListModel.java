package com.gottmusig.model;

import java.util.List;

import org.apache.wicket.model.AbstractReadOnlyModel;

import com.gottmusig.dpsdifference.domain.api.SpecificationDPS;

public class SpecificationDPSListModel extends AbstractReadOnlyModel<List<SpecificationDPS>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SpecificationDPS> specificationDPSList;

	public SpecificationDPSListModel(List<SpecificationDPS> specificationDPSList) {
		this.specificationDPSList = specificationDPSList;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<SpecificationDPS> getObject() {
		return specificationDPSList;
	}

	public int getMaxDPS() {
		return specificationDPSList.get(0).getSpecificationDPS();
	}
	
}