package com.jesperapps.tracksupervisor.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesperapps.tracksupervisor.api.extra.OtpRequest;
import com.jesperapps.tracksupervisor.api.extra.OtpResponse;
import com.jesperapps.tracksupervisor.api.model.Attendance;
import com.jesperapps.tracksupervisor.api.model.Organization;
import com.jesperapps.tracksupervisor.api.model.User;
import com.jesperapps.tracksupervisor.api.model.UserType;
import com.jesperapps.tracksupervisor.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
private EmailService emailService;

	@Override
	public Optional<User> createUser(User user) {
		User userRes = userRepository.save(user);
		return userRepository.findById(userRes.getUserId());
	}

	@Override
	public User updatePassCode(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User save(User users) {
		System.out.println("usersimpl"+users);
		User usr =  userRepository.save(users);
		System.out.println("usr"+usr);
		 return usr;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User deleteUser(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User dbUser = user.get();
			dbUser.setStatus("Deleted");
			Set<Attendance> attendance = new HashSet<Attendance>();
			for (Attendance att : dbUser.getAttendance()) {
				att.setStatus("Deleted");
				attendance.add(att);
				dbUser.setAttendance(attendance);
			}

			return userRepository.save(dbUser);
		} else {
			return null;
		}
	}

	@Override
	public List<User> findAllByPhoneNumber(String phoneNumber) {
		return userRepository.findAllByPhoneNumber(phoneNumber);
	}

	@Override
	public List<User> findAllByPhoneNumberOrAlternatePhoneNumber(String phoneNumber, String phoneNumber2) {
		return userRepository.findAllByPhoneNumberOrAlternatePhoneNumber(phoneNumber, phoneNumber2);
	}

	@Override
	public User findByUserStatus(String string) {
		return userRepository.findByUserStatus(string);
	}

	@Override
	public User findByUserStatusAndUserId(String string, Long userId) {
		return userRepository.findByUserStatusAndUserId(string, userId);
	}

	@Override
	public List<User> findAllByAlternatePhoneNumberOrPhoneNumber(String phoneNumber, String alternatePhoneNumber) {
		// TODO Auto-generated method stub
		return userRepository.findAllByAlternatePhoneNumberOrPhoneNumber(phoneNumber, alternatePhoneNumber);
	}

	@Override
	public List<User> findAllByOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return userRepository.findAllByOrganization(organization);
	}

	@Override
	public List<User> findEmployeeByUserTypeAndOrganization(UserType users, Organization organization) {
		// TODO Auto-generated method stub
		return userRepository.findEmployeeByUserTypeAndOrganization(users, organization);
	}

	@Override
	public User findUserByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmail(userEmail);
	}
	
	@Override
	public List<User> findAllByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findAllByEmail(email);
	}

	@Override
	public Optional<User> findByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return userRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public void sendSms(String string, String phone) {
		try {

			String apiKey = "elNIWdPL4TVuhKAGt7BnjMoEw9ZFyYU6cXx5kg2J8zHaiOs01Dn50wUgxpFkDubhRT9Ba87Ny6vlMtWr";
			String sendId = "FSTSMS";
			// important step...
			string = URLEncoder.encode(string, "UTF-8");
			String language = "english";

			String route = "t";

			String myUrl = "https://www.fast2sms.com/dev/bulk?authorization=" + apiKey + "&sender_id=" + sendId
					+ "&message=" + string + "&language=" + language + "&route=" + route + "&numbers=" + phone;

			// sending get request using java..

			URL url = new URL(myUrl);

			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			System.out.println("Wait..............");

			int code = con.getResponseCode();

			System.out.println("Response code : " + code);

			StringBuffer response = new StringBuffer();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				response.append(line);
			}

			System.out.println(response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndPassword(email,password);
	}

	@Override
	public boolean checkPasswordIsSame(String password, String password2) {
		// TODO Auto-generated method stub
		if(password.equals(password2)) {
			return true;
		}else {
			
		}
		return false;
		
	}

	@Override
	public User findUserByEmailOrPhoneNumber(String email, String phoneNumber) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmailOrPhoneNumber(email,phoneNumber);
	}

	@Override
	public List<User> findAllByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userRepository.findAllByUserId(userId);
	}

	@Override
	public User findByOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return userRepository.findByOrganization(organization);
	}

	@Override
	public List<User> findAllByPhoneNumberOrEmail(String phoneNumber, String email) {
		// TODO Auto-generated method stub
		return userRepository.findAllByPhoneNumberOrEmail(phoneNumber,email);
	}

	@Override
	public List<User> findByUser(List<User> user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserId(userId);
	}

	@Override
	public List<OtpResponse> validateOTP(List<OtpRequest> emailOtpRequest) {
		List<OtpResponse> responseList = new ArrayList<>();

		for (OtpRequest request : emailOtpRequest) {
			OtpResponse response = new OtpResponse(400, "Bad request");
			User requestUser = this.findUserByEmail(request.getEmail());
			if (requestUser != null) {
				if (emailService.checkOTP(requestUser, request.getOtp())) {
					User user = this.findUserByEmail(requestUser.getEmail());
					user.setVerificationStatus(1);
					userRepository.save(user);
					response.setStatusCode(200);
					response.setDescription("Otp Matched");
				} else {
					response.setStatusCode(400);
					response.setDescription("Otp Mismatch");
				}
			} else {
				response.setStatusCode(409);
				response.setDescription("No user found");
			}
			responseList.add(response);
		}

		return responseList;
	}

	
}
