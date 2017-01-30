package com.gottmusig.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.gottmusig.GottmusigApplication;
import com.gottmusig.account.domain.api.AccountAdministration;
import com.gottmusig.dpsdifference.configuration.DPSDifferenceConfiguration;
import com.gottmusig.dpsdifference.domain.api.DPSDifference;
import com.gottmusig.models.RealmLocationListModel;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.models.SpecificationDPSListModel;
import com.gottmusig.searchcharacter.domain.api.SearchCharacter;
import com.gottmusig.searchcharacter.jpa.Location;

@Configuration
@Import( {DPSDifferenceConfiguration.class} )
public class ApplicationConfiguration {

	@Autowired DPSDifference dpsDifference;
	@Autowired SearchCharacter searchChar;
	@Autowired AccountAdministration accountAdministration;
	
	@Bean
	public GottmusigApplication gottmusigApplication() {
		return new GottmusigApplication();
	}
	
	@Bean
	public SpecificationDPSListModel specificationDPSListModel() {
		return new SpecificationDPSListModel(dpsDifference);
	}
	
	@Bean
	public RealmLocationListModel realmLocationListModel() {
		return new RealmLocationListModel(Location.getLocations());
	}
	
	@Bean
	public ServiceProxyModel<SearchCharacter> searchCharacterModel() {
		return new ServiceProxyModel<>(searchChar);
	}
	
	@Bean
	public ServiceProxyModel<AccountAdministration> accountAdministrationModel() {
		return new ServiceProxyModel<AccountAdministration>(accountAdministration);
	}
	
}