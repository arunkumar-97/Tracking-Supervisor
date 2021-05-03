package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jesperapps.tracksupervisor.api.message.IndustryTypeResponseEntity;
import com.jesperapps.tracksupervisor.api.model.IndustryType;
import com.jesperapps.tracksupervisor.api.service.IndustryTypeService;




@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class IndustryTypeController {
	
	IndustryTypeResponseEntity res = new IndustryTypeResponseEntity();

	@Autowired
	private IndustryTypeService industryTypeService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/industry-type")
	public ResponseEntity listIndustryType() {
		List<IndustryType> industryTypeDatas = industryTypeService.findAll();
		if (industryTypeDatas.isEmpty()) {
			IndustryTypeResponseEntity userResponseEntity = new IndustryTypeResponseEntity();
			userResponseEntity.setErrorCode(204);
			userResponseEntity.setMessage("No Data");
			return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
		}
		List<IndustryTypeResponseEntity> resourceResponseEntity1 = new ArrayList<IndustryTypeResponseEntity>();
		for (IndustryType industryType : industryTypeDatas) {
			if (industryType.getStatus() == null || industryType.getStatus().equals("Active")
					|| industryType.getStatus().equals("InActive") || industryType.getStatus().equals("Pending")
					|| industryType.getStatus().equals("Hold")) {
				IndustryTypeResponseEntity resourceResponseEntity = new IndustryTypeResponseEntity(industryType);

				resourceResponseEntity1.add(resourceResponseEntity);
			}
		}
		return new ResponseEntity(resourceResponseEntity1, HttpStatus.OK);
	}
	

}
