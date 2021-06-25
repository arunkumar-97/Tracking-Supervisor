package com.jesperapps.tracksupervisor.api.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jesperapps.tracksupervisor.api.message.ReportsRequestEntity;

@Entity
public class Reports {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reportId;
	private Date date;
	private Time time;
	private Double latitude;
	private Double longitude;
	private Long noOfWorkers;
	private String day;
	private String address;
	private String status;
	private String userStatus;
	
	
	@ManyToOne
	@JoinColumn(name="userId")    
	private User user;
	
	
	public Reports() {
		
	}
	
	public Reports(ReportsRequestEntity reportsRequestEntity) {
		this.reportId=reportsRequestEntity.getReportId();
		this.date=reportsRequestEntity.getDate();
		this.time=reportsRequestEntity.getTime();
		this.latitude=reportsRequestEntity.getLatitude();
		this.longitude=reportsRequestEntity.getLongitude();
		this.noOfWorkers=reportsRequestEntity.getNoOfWorkers();
		this.day=reportsRequestEntity.getDay();
		this.address=reportsRequestEntity.getAddress();
		this.status=reportsRequestEntity.getStatus();
		this.userStatus=reportsRequestEntity.getUserStatus();
		this.user=reportsRequestEntity.getUser();
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Long getNoOfWorkers() {
		return noOfWorkers;
	}
	public void setNoOfWorkers(Long noOfWorkers) {
		this.noOfWorkers = noOfWorkers;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Reports [reportId=" + reportId + ", date=" + date + ", time=" + time + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", noOfWorkers=" + noOfWorkers + ", day=" + day + ", address=" + address
				+ ", status=" + status + ", userStatus=" + userStatus + ", user=" + user + "]";
	}
	
	
	

}
