package com.jesperapps.tracksupervisor.api.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jesperapps.tracksupervisor.api.message.UserResponseEntity;
import com.jesperapps.tracksupervisor.api.message.UserTypeResponseEntity;
import com.jesperapps.tracksupervisor.api.model.UserType;
import com.jesperapps.tracksupervisor.api.service.UserTypeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserTypeController {
	
	UserTypeResponseEntity res = new UserTypeResponseEntity();

	@Autowired
	private UserTypeService userTypeService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/user-type")
	public ResponseEntity listUserType() {
		List<UserType> userTypeDatas = userTypeService.findAll();
		if (userTypeDatas.isEmpty()) {
			UserResponseEntity userResponseEntity = new UserResponseEntity();
			userResponseEntity.setErrorCode(204);
			userResponseEntity.setMessage("No Data");
			return new ResponseEntity(userResponseEntity, HttpStatus.NOT_FOUND);
		}
		List<UserTypeResponseEntity> resourceResponseEntity1 = new ArrayList<UserTypeResponseEntity>();
		for (UserType userType : userTypeDatas) {
			if (userType.getStatus() == null || userType.getStatus().equals("Active")
					|| userType.getStatus().equals("InActive") || userType.getStatus().equals("Pending")
					|| userType.getStatus().equals("Hold")) {
				UserTypeResponseEntity resourceResponseEntity = new UserTypeResponseEntity(userType);

				resourceResponseEntity1.add(resourceResponseEntity);
			}
		}
		return new ResponseEntity(resourceResponseEntity1, HttpStatus.OK);
	}
	
	
}
