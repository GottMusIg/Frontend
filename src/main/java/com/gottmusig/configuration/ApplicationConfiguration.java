package com.gottmusig.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.gottmusig.GottmusigApplication;
import com.gottmusig.dpsdifference.configuration.DPSDifferenceConfiguration;

@Configuration
@Import( DPSDifferenceConfiguration.class )
public class ApplicationConfiguration {

	@Bean
	public GottmusigApplication gottmusigApplication() {
		return new GottmusigApplication();
	}
	
}