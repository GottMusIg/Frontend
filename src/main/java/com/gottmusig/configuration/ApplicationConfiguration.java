package com.gottmusig.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.gottmusig.GottmusigApplication;
import com.gottmusig.dpsdifference.configuration.DPSDifferenceConfiguration;
import com.gottmusig.dpsdifference.domain.api.DPSDifference;
import com.gottmusig.model.SpecificationDPSListModel;

@Configuration
@Import( DPSDifferenceConfiguration.class )
public class ApplicationConfiguration {

	@Autowired DPSDifference dpsDifference;
	
	@Bean
	public GottmusigApplication gottmusigApplication() {
		return new GottmusigApplication();
	}
	
	@Bean
	public SpecificationDPSListModel specificationDPSListModel() {
		return new SpecificationDPSListModel(dpsDifference);
	}
	
}