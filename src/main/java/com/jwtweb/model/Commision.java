package com.jwtweb.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Commision implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@OneToOne
	@JoinColumn
	private ServiceProvider serviceProvider;
	
	@Column(unique = false, nullable = false, columnDefinition="decimal(10,2) DEFAULT '0.00'")
	private double merchantCommision;

	@Column(unique = false, nullable = false, columnDefinition="decimal(10,2) DEFAULT '0.00'")
	private double partnerCommision;
	
    @Column(nullable=false)
    private String createdDateTime;

    @Column(nullable=true, columnDefinition="datetime DEFAULT NULL")
    private String lastUpdateDateTime;
	
	@Column(nullable=false)
	private boolean isActive;
	
	@Column(nullable=false)
	private String expiryDateTime;
	
	@Column(unique = false, nullable = true, columnDefinition="decimal(10,2) DEFAULT NULL")
	private double mavCommision;
	
	@Column(unique = false, nullable = true, columnDefinition="decimal(10,2) DEFAULT NULL")
	private double reddotbeforeayacutCommision;
	
	@Column(unique = false, nullable = true, columnDefinition="decimal(10,2) DEFAULT NULL")
	private double aya;
	
	@Column(unique = false, nullable = true, columnDefinition="int(5) DEFAULT NULL")
	private int rdnMinimumCommission;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public double getMerchantCommision() {
		return merchantCommision;
	}

	public void setMerchantCommision(double merchantCommision) {
		this.merchantCommision = merchantCommision;
	}

	public double getPartnerCommision() {
		return partnerCommision;
	}

	public void setPartnerCommision(double partnerCommision) {
		this.partnerCommision = partnerCommision;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(String lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getExpiryDateTime() {
		return expiryDateTime;
	}

	public void setExpiryDateTime(String expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
	}

	public double getMavCommision() {
		return mavCommision;
	}

	public void setMavCommision(double mavCommision) {
		this.mavCommision = mavCommision;
	}

	public double getReddotbeforeayacutCommision() {
		return reddotbeforeayacutCommision;
	}

	public void setReddotbeforeayacutCommision(double reddotbeforeayacutCommision) {
		this.reddotbeforeayacutCommision = reddotbeforeayacutCommision;
	}

	public double getAya() {
		return aya;
	}

	public void setAya(double aya) {
		this.aya = aya;
	}

	public int getRdnMinimumCommission() {
		return rdnMinimumCommission;
	}

	public void setRdnMinimumCommission(int rdnMinimumCommission) {
		this.rdnMinimumCommission = rdnMinimumCommission;
	}
}
