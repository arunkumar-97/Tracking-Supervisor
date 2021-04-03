package com.jesperapps.tracksupervisor.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jesperapps.tracksupervisor.api.extra.AbstractAuditingEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizataionWithUserRequestEntity;
import com.jesperapps.tracksupervisor.api.message.OrganizationRequestEntity;

@Entity
@Table(name = "ORG_PROFILE_INFORMATION")
public class Organization extends AbstractAuditingEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORGANIZATION_ID", nullable = false)
//	@Size(max = 11)
	private Integer organizationId;

	@Column(name = "ORGANIZATION_NAME")
	@Size(max = 100)
	private String organizationName;

	@Column(name = "INDUSTRY_TYPE")
	@Size(max = 20)
	private String industryType;

	@Column(name = "CONTACT_PERSON")
	@Size(max = 11)

	private String contactPerson;

	@Column(name = "CONTACT_COUNTRY_CODE")
	@Size(max = 150)
	private String contactCountryCode;

	@Column(name = "CONTACT_PHONE_NUMBER")
	@Size(max = 50)
	private String contactPhoneNumber;

	@Column(name = "CONTACT_EMAIL")
	@Size(max = 100)
	private String contactEmail;

	@Column(name = "ADDRESS")
	@Size(max = 255)
	private String address;

	@Column(name = "POSTAL_CODE")
	@Size(max = 20)
	private String postalCode;

	@Column(name = "FISCAL_YEAR_START_WITH")
	@Size(max = 20)
	private String fiscalYearStart;

	@Column(name = "DEFAULT_TIMEZONE")
	@Size(max = 150)
	private String defaulTimezone;

	@Column(name = "REPORTING_CURRENCY ")
	@Size(max = 150)
	private String reportingCurrency;

	@ManyToOne
	@JoinColumn(name = "state_Id", referencedColumnName = "stateId")
	private State state;

	@ManyToOne
	@JoinColumn(name = "Country_Id", referencedColumnName = "countryId")
	private Country country;

	@ManyToOne
	@JoinColumn(name = "City_Id", referencedColumnName = "cityId")
	private City city;

	@JsonManagedReference("organization_attachment")
	@OneToOne(mappedBy = "organization")
//	@JoinColumn(name = "attachment")
	private Attachment attachment;
	
	@JsonIgnore
	@OneToMany(mappedBy = "organization" , cascade = CascadeType.ALL)
	private List<User> user;
	
	@ManyToOne
	@JoinColumn
	private Status status;

	public Organization() {

	}

//	public Organization(Organization organization) {
//		super();
//		this.organizationId = organization.getOrganizationId();
//		this.organizationName = organization.getOrganizationName();
//		if(organization.getAttachment() != null) {
//			this.attachment = organization.getAttachment();
//			this.attachment.setOrganization(this);
//		}
//		
//	}

	public Organization(Organization organization, Integer employeeId) {
		super();
		this.organizationId = organization.getOrganizationId();
		this.organizationName = organization.getOrganizationName();
	}

	public Organization(Organization organization) {
		super();
		this.organizationId = organization.getOrganizationId();
		this.organizationName = organization.getOrganizationName();
//		if(organization.getAttachment() != null) {
//			this.attachment = organization.getAttachment();
//			this.attachment.setOrganization(this);
//		}
		
	}

	public Organization(Integer organizationId2) {
		this.organizationId = organizationId2;
	}

	public Organization(OrganizationRequestEntity organizationRequestEntity) {
		super();
		this.organizationId = organizationRequestEntity.getOrganizationId();
		this.organizationName = organizationRequestEntity.getOrganizationName();
		this.industryType = organizationRequestEntity.getIndustryType();
		this.contactPerson = organizationRequestEntity.getContactPerson();
		this.contactCountryCode = organizationRequestEntity.getContactCountryCode();
		this.contactPhoneNumber = organizationRequestEntity.getContactPhoneNumber();
		this.contactEmail = organizationRequestEntity.getContactEmail();
		this.address = organizationRequestEntity.getAddress();
		this.postalCode = organizationRequestEntity.getPostalCode();
		this.fiscalYearStart = organizationRequestEntity.getFiscalYearStart();
		this.defaulTimezone = organizationRequestEntity.getDefaulTimezone();
		this.reportingCurrency = organizationRequestEntity.getReportingCurrency();
		if (organizationRequestEntity.getAttachment() == null) {

		} else {
			this.attachment = organizationRequestEntity.getAttachment();
			this.attachment.setOrganization(this);
		}
	}

	public Organization(OrganizataionWithUserRequestEntity organizationRequestEntity) {
		// TODO Auto-generated constructor stub
		
		this.organizationId = organizationRequestEntity.getOrganization().getOrganizationId();
		this.organizationName = organizationRequestEntity.getOrganization().getOrganizationName();
		this.industryType = organizationRequestEntity.getOrganization().getIndustryType();
		this.contactPerson = organizationRequestEntity.getOrganization().getContactPerson();
		this.contactCountryCode = organizationRequestEntity.getOrganization().getContactCountryCode();
		this.contactPhoneNumber = organizationRequestEntity.getOrganization().getContactPhoneNumber();
		this.contactEmail = organizationRequestEntity.getOrganization().getContactEmail();
		this.address = organizationRequestEntity.getOrganization().getAddress();
		this.postalCode = organizationRequestEntity.getOrganization().getPostalCode();
		this.fiscalYearStart = organizationRequestEntity.getOrganization().getFiscalYearStart();
		this.defaulTimezone = organizationRequestEntity.getOrganization().getDefaulTimezone();
		this.reportingCurrency = organizationRequestEntity.getOrganization().getReportingCurrency();
		this.attachment = organizationRequestEntity.getOrganization().getAttachment();
	}

	public Organization(OrganizationRequestEntity organizationRequestEntity,
			OrganizationRequestEntity organizationRequestEntity2) {
		// TODO Auto-generated constructor stub
		this.organizationId = organizationRequestEntity.getOrganizationId();
		this.organizationName = organizationRequestEntity.getOrganizationName();
		this.industryType = organizationRequestEntity.getIndustryType();
		this.contactPerson = organizationRequestEntity.getContactPerson();
		this.contactCountryCode = organizationRequestEntity.getContactCountryCode();
		this.contactPhoneNumber = organizationRequestEntity.getContactPhoneNumber();
		this.contactEmail = organizationRequestEntity.getContactEmail();
		this.address = organizationRequestEntity.getAddress();
		this.postalCode = organizationRequestEntity.getPostalCode();
		this.fiscalYearStart = organizationRequestEntity.getFiscalYearStart();
		this.defaulTimezone = organizationRequestEntity.getDefaulTimezone();
		this.reportingCurrency = organizationRequestEntity.getReportingCurrency();
		if (organizationRequestEntity.getAttachment() == null) {

		} else {
			this.attachment = organizationRequestEntity.getAttachment();
			this.attachment.setOrganization(this);
		}
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactCountryCode() {
		return contactCountryCode;
	}

	public void setContactCountryCode(String contactCountryCode) {
		this.contactCountryCode = contactCountryCode;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getFiscalYearStart() {
		return fiscalYearStart;
	}

	public void setFiscalYearStart(String fiscalYearStart) {
		this.fiscalYearStart = fiscalYearStart;
	}

	public String getDefaulTimezone() {
		return defaulTimezone;
	}

	public void setDefaulTimezone(String defaulTimezone) {
		this.defaulTimezone = defaulTimezone;
	}

	public String getReportingCurrency() {
		return reportingCurrency;
	}

	public void setReportingCurrency(String reportingCurrency) {
		this.reportingCurrency = reportingCurrency;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}




}
