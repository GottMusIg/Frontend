package com.gottmusig.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.gottmusig.GottmusigApplication;
import com.gottmusig.database.service.configuration.DatabaseServiceConfiguration;
import com.gottmusig.database.service.domain.GottMusIg;
import com.gottmusig.database.service.domain.account.AccountService;
import com.gottmusig.database.service.domain.character.CharacterService;
import com.gottmusig.database.service.domain.realm.RealmService;
import com.gottmusig.database.service.domain.realm.jpa.Location;
import com.gottmusig.models.RealmLocationListModel;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.models.SpecificationDPSListModel;

/**
 * Configuration class for the {@link GottmusigApplication}.
 * 
 * @author kkalmus
 */
@Configuration
@Import( {DatabaseServiceConfiguration.class} )
public class ApplicationConfiguration {

	@Autowired GottMusIg gottMusIg;
	
	@Bean
	public GottmusigApplication gottmusigApplication() {
		return new GottmusigApplication();
	}
	
	@Bean
	public SpecificationDPSListModel specificationDPSListModel() {
		return new SpecificationDPSListModel(gottMusIg.dpsDifferenceService());
	}
	
	@Bean
	public RealmLocationListModel realmLocationListModel() {
		return new RealmLocationListModel(Location.getLocations());
	}
	
	@Bean
	public ServiceProxyModel<CharacterService> searchCharacterModel() {
		return new ServiceProxyModel<>(gottMusIg.characterService());
	}
	
	@Bean
	public ServiceProxyModel<AccountService> accountAdministrationModel() {
		return new ServiceProxyModel<AccountService>(gottMusIg.accountService());
	}
	
	@Bean
	public ServiceProxyModel<RealmService> realmServiceModel() {
		return new ServiceProxyModel<RealmService>(gottMusIg.realmService());
	}
	
}