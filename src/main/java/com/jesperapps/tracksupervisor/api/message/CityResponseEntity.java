package com.jesperapps.tracksupervisor.api.message;

import com.jesperapps.tracksupervisor.api.extra.BaseResponse;
import com.jesperapps.tracksupervisor.api.model.City;

public class CityResponseEntity extends BaseResponse{

	
	private Integer cityId;
	private String cityName;
	
	
	public CityResponseEntity() {
		
	}
	
	
	public CityResponseEntity(City city) {
		this.cityId=city.getCityId();
		this.cityName=city.getCityName();
		
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
}
