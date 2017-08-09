package com.jwtweb.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ServiceProvider implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(unique = false, nullable = false)
	private int generalStatusId;
	
	@Column(unique = false, nullable = true, columnDefinition="smallint(5) DEFAULT NULL")
	private Integer serviceProviderTypeId;

	@Column(unique = false, nullable = false, columnDefinition="smallint(5)")
	private int currencyId;
	
	@Column(unique = false, nullable = true, columnDefinition="bigint(19)")
	private int serviceProviderId;
	
	@Column(unique = false, nullable = true, columnDefinition="VARCHAR(50) DEFAULT NULL")
	private String name;
	
	@Column(unique = false, nullable = true, columnDefinition="VARCHAR(100) DEFAULT NULL")
	private String callbackUrl;
	
	@Column(unique = false, nullable = true, columnDefinition="VARCHAR(50) DEFAULT NULL")
	private String contactEmail;

	@Column(unique = false, nullable = true, columnDefinition="VARCHAR(50) DEFAULT NULL")
	private String supportEmail;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="datetime DEFAULT NULL")
    private Calendar dateTimeRegistered;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition="datetime DEFAULT NULL")
    private Calendar lastUpdateDateTime;
	
	@Column(unique = false, nullable = true, columnDefinition="tinyint(1) DEFAULT NULL")
	private Boolean hasCustomStatus;
	
	@Column(unique = false, nullable = true, columnDefinition="enum('external','internal','none') CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL")
	private String settlementMode;
	
	@Column(unique = false, nullable = true, columnDefinition="varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL")
	private String timezone;
	
	@Column(unique = false, nullable = true, columnDefinition="int(11) DEFAULT NULL")
	private Integer voucherIdLength;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGeneralStatusId() {
		return generalStatusId;
	}

	public void setGeneralStatusId(int generalStatusId) {
		this.generalStatusId = generalStatusId;
	}

	public Integer getServiceProviderTypeId() {
		return serviceProviderTypeId;
	}

	public void setServiceProviderTypeId(int serviceProviderTypeId) {
		this.serviceProviderTypeId = serviceProviderTypeId;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public int getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(int serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getSupportEmail() {
		return supportEmail;
	}

	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}

	public Calendar getDateTimeRegistered() {
		return dateTimeRegistered;
	}

	public void setDateTimeRegistered(Calendar dateTimeRegistered) {
		this.dateTimeRegistered = dateTimeRegistered;
	}

	public Calendar getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Calendar lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public Boolean getHasCustomStatus() {
		return hasCustomStatus;
	}

	public void setHasCustomStatus(Boolean hasCustomStatus) {
		this.hasCustomStatus = hasCustomStatus;
	}

	public String getSettlementMode() {
		return settlementMode;
	}

	public void setSettlementMode(String settlementMode) {
		this.settlementMode = settlementMode;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Integer getVoucherIdLength() {
		return voucherIdLength;
	}

	public void setVoucherIdLength(int voucherIdLength) {
		this.voucherIdLength = voucherIdLength;
	}
}
