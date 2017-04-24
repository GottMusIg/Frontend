package com.gottmusig.models;

import java.util.List;

import org.apache.wicket.model.AbstractReadOnlyModel;

import com.gottmusig.database.service.domain.dpsdifference.DPSDifferenceService;
import com.gottmusig.database.service.domain.dpsdifference.SpecificationDPS;

/**
 * {@link AbstractReadOnlyModel} 
 * 
 * @author kkalmus
 */
public class SpecificationDPSListModel extends AbstractReadOnlyModel<List<SpecificationDPS>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SpecificationDPS> specDps;
	private int maxDps;

	public SpecificationDPSListModel(DPSDifferenceService dpsDifferenceServic) {
		this.specDps = dpsDifferenceServic.getDPSDifference();
		this.maxDps = dpsDifferenceServic.getMaxDPSValue();
	}
	
	@Override
	public List<SpecificationDPS> getObject() {
		return  specDps;
	}

	public int getMaxDPS() {
		return maxDps;
	}
	
}