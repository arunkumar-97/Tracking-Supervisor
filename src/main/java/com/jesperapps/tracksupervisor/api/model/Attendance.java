package com.jesperapps.tracksupervisor.api.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.jesperapps.tracksupervisor.api.extra.AbstractAuditingEntity;
import com.jesperapps.tracksupervisor.api.message.AttendanceRequestEntity;

@Entity
@Table(name = "attendance")
public class Attendance extends AbstractAuditingEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceId;

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
	@JoinColumn
	private User user;

	public Attendance() {

	}

	public Attendance(AttendanceRequestEntity attendanceRequestEntity) {
		super();
		this.attendanceId = attendanceRequestEntity.getAttendanceId();
		this.date = attendanceRequestEntity.getDate();
		this.time = attendanceRequestEntity.getTime();
		this.latitude = attendanceRequestEntity.getLatitude();
		this.longitude = attendanceRequestEntity.getLongitude();
		this.noOfWorkers = attendanceRequestEntity.getNoOfWorkers();
		this.day = attendanceRequestEntity.getDay();
		this.address = attendanceRequestEntity.getAddress();
		this.status = "Present";
		this.userStatus = "Active";
		this.user = attendanceRequestEntity.getUser();
	}


	public Attendance(Attendance attendanceRequestEntity) {
		this.attendanceId = attendanceRequestEntity.getAttendanceId();
		this.date = attendanceRequestEntity.getDate();
		this.time = attendanceRequestEntity.getTime();
		this.latitude = attendanceRequestEntity.getLatitude();
		this.longitude = attendanceRequestEntity.getLongitude();
		this.noOfWorkers = attendanceRequestEntity.getNoOfWorkers();
		this.day = attendanceRequestEntity.getDay();
		this.address = attendanceRequestEntity.getAddress();
		this.status = attendanceRequestEntity.getStatus();
	}

	public Attendance(String string, Attendance attendanceRequestEntity) {
		this.attendanceId = attendanceRequestEntity.getAttendanceId();
		this.date = attendanceRequestEntity.getDate();
		this.time = attendanceRequestEntity.getTime();
		this.latitude = attendanceRequestEntity.getLatitude();
		this.longitude = attendanceRequestEntity.getLongitude();
		this.noOfWorkers = attendanceRequestEntity.getNoOfWorkers();
		this.day = attendanceRequestEntity.getDay();
		this.address = attendanceRequestEntity.getAddress();
		this.status = attendanceRequestEntity.getStatus();
		this.userStatus = "InActive";
	}

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", date=" + date + ", time=" + time + ", latitude="
				+ latitude + ", longitude=" + longitude + ", noOfWorkers=" + noOfWorkers + ", day=" + day + ", address="
				+ address + ", status=" + status + "]";
	}

}
