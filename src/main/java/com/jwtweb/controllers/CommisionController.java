package com.jwtweb.controllers;

import java.security.Principal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtweb.model.Commision;
import com.jwtweb.model.ServiceProvider;
import com.jwtweb.services.CommisionService;
import com.jwtweb.services.ServiceProviderService;

@Controller
public class CommisionController {
	
	@Autowired
	CommisionService commisionService;
	
	@Autowired
	ServiceProviderService serviceProviderService;
	
	@GetMapping("getCommision")
	public @ResponseBody List<Commision> getCommisionList(Principal principal){
		
		if(principal != null){
			List<Commision> commisionList = commisionService.list();
			return commisionService.list();
		}
		return null;
	}
	
	@GetMapping("getCommision/{id}")
	public @ResponseBody Commision getCommisionById(Principal principal,@PathVariable String id){
		
		if(principal != null){
			Commision commision = commisionService.findByUniqueField("id", Long.parseLong(id));
			return commision;
		}
		return null;
	}

	
	@PostMapping("deleteCommision")
	public @ResponseBody String deleteCommision(Principal principal, @RequestParam("selected[]")String[] selectedItems){
		try{
			for(int i=0;i<selectedItems.length;i++){
				System.out.println(i);
				Commision commision = commisionService.findByUniqueField("id", Long.parseLong(selectedItems[i]));
				commisionService.delete(commision);
			}
		}catch(Exception e){
			e.printStackTrace();
			return "Record not Deleted due to "+e;
		}
		
		return JSONObject.quote("Delete Successful!!");
	}
	
	@PostMapping("addCommision")
	public @ResponseBody String addCommision(Principal principal, ServiceProvider serviceProvider, Commision commision){
		try{
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.YEAR, 3);
			Date nextYear = cal.getTime();
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			commision.setExpiryDateTime(formatter.format(nextYear));
			commision.setCreatedDateTime(formatter.format(new Date()));
			serviceProvider.setDateTimeRegistered(Calendar.getInstance());
			serviceProvider.setTimezone("Asia/Rangoon");
			System.out.println("LONG/INT:"+serviceProvider.getId()+""+commision.getId());
			ServiceProvider sp = new ServiceProvider();
			sp = serviceProvider;
			serviceProviderService.save(sp);
			System.out.println("LONG/INT:"+sp.getId()+""+commision.getId());
			commision.setServiceProvider(sp);
			System.out.println("LONG/INT:"+sp.getId()+""+commision.getId());
			commisionService.save(commision);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	@PostMapping("updateCommision")
	public @ResponseBody String editCommision(Principal principal, Commision commision, String serviceProviderId) {
		
		try{
			ServiceProvider serviceProvider = serviceProviderService.findByUniqueField("id", Long.parseLong(serviceProviderId));
			commision.setServiceProvider(serviceProvider);
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			commision.setLastUpdateDateTime(formatter.format(new Date()));
			commisionService.update(commision);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return "";
	}
}
