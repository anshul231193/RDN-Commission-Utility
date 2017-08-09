package com.jwtweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwtweb.daos.AbstractDAO;
import com.jwtweb.daos.CommisionDAO;
import com.jwtweb.model.Commision;
import com.jwtweb.model.ServiceProvider;

@Service
@Transactional
public class CommisionServiceImpl extends CommonServiceImpl<Commision> implements CommisionService{

	CommisionDAO commisionDAO;
	
	@Autowired
	ServiceProviderService serviceProviderService;
	
	@Autowired
	public CommisionServiceImpl(CommisionDAO commisionDAO) {
		super(commisionDAO);
		this.commisionDAO = commisionDAO;
		// TODO Auto-generated constructor stub
	}

	public void saveChildFirst(ServiceProvider serviceProvider) {
		// TODO Auto-generated method stub
		this.serviceProviderService.save(serviceProvider);
	}
	

}
