package com.jwtweb.services;

import com.jwtweb.model.Commision;
import com.jwtweb.model.ServiceProvider;

public interface CommisionService extends CommonService<Commision>{
	
	public void saveChildFirst(ServiceProvider serviceProvider);

}
