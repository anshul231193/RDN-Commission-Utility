package com.jwtweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwtweb.daos.AbstractDAO;
import com.jwtweb.daos.ServiceProviderDAO;
import com.jwtweb.model.ServiceProvider;

@Service
@Transactional
public class ServiceProviderServiceImpl extends CommonServiceImpl<ServiceProvider> implements ServiceProviderService {

	ServiceProviderDAO serviceProviderDAO;
	
	@Autowired
	public ServiceProviderServiceImpl(ServiceProviderDAO serviceProviderDAO) {
		super(serviceProviderDAO);
		this.serviceProviderDAO = serviceProviderDAO;
		// TODO Auto-generated constructor stub
	}

}
