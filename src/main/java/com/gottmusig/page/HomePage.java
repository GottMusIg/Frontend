package com.gottmusig.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gottmusig.component.ClassDPSPanel;
import com.gottmusig.dpsdifference.domain.api.ClassSpecification;
import com.gottmusig.dpsdifference.domain.api.SpecificationDPS;
import com.gottmusig.dpsdifference.domain.api.WOWClass;
import com.gottmusig.model.SpecificationDPSListModel;

public class HomePage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
//	@Autowired DPSDifference dpsDifference;
	
	public HomePage(final PageParameters parameters) {
		super(parameters);

		List<SpecificationDPS> specList = new ArrayList<>();
		
		SpecificationDPS specDps = new SpecificationDPS() {
			
			@Override
			public Id getId() {
				return new com.gottmusig.dpsdifference.domain.Entity.Id() {
					@Override
					public String displayValue() {
						return "0";
					}
				};
			}
			
			@Override
			public int getSpecificationDPS() {
				return 240000;
			}
			
			@Override
			public ClassSpecification getSpecification() {
				return new ClassSpecification() {
					
					@Override
					public Id getId() {
						return new com.gottmusig.dpsdifference.domain.Entity.Id() {
							@Override
							public String displayValue() {
								return "0";
							}
						};
					}
					
					@Override
					public WOWClass getWOWClass() {
						return new WOWClass() {
							
							@Override
							public Id getId() {
								return new com.gottmusig.dpsdifference.domain.Entity.Id() {
									@Override
									public String displayValue() {
										return "0";
									}
								};
							}
							
							@Override
							public String getName() {
								return "Priest";
							}
						};
					}
					
					@Override
					public String getName() {
						return "Shadow";
					}
				};
			}
		};
		
		SpecificationDPS specDps2 = new SpecificationDPS() {
			
			@Override
			public Id getId() {
				return new com.gottmusig.dpsdifference.domain.Entity.Id() {
					@Override
					public String displayValue() {
						return "1";
					}
				};
			}
			
			@Override
			public int getSpecificationDPS() {
				return 220000;
			}
			
			@Override
			public ClassSpecification getSpecification() {
				return new ClassSpecification() {
					
					@Override
					public Id getId() {
						return new com.gottmusig.dpsdifference.domain.Entity.Id() {
							@Override
							public String displayValue() {
								return "1";
							}
						};
					}
					
					@Override
					public WOWClass getWOWClass() {
						return new WOWClass() {
							
							@Override
							public Id getId() {
								return new com.gottmusig.dpsdifference.domain.Entity.Id() {
									@Override
									public String displayValue() {
										return "1";
									}
								};
							}
							
							@Override
							public String getName() {
								return "Warrior";
							}
						};
					}
					
					@Override
					public String getName() {
						return "Fury";
					}
				};
			}
		};
		
		specList.add(specDps);
		specList.add(specDps2);
		
		IModel<List<SpecificationDPS>> specificationDPSModel = new SpecificationDPSListModel(specList);
		
		add(new ClassDPSPanel("classDPSPanel", specificationDPSModel));
		
    }
	
}