package com.reportanalytics.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reportanalytics.entities.Response;
import com.reportanalytics.entities.Role;
import com.reportanalytics.entities.Users;
import com.reportanalytics.repository.UserRolesRepository;
import com.reportanalytics.repository.UsersRepository;
import com.reportanalytics.utilities.CommonUtils;
import com.reportanalytics.utilities.EncryptionDecryptionAES;

@Service
public class UserCreationService {

	private static final Logger log = LoggerFactory.getLogger(UserCreationService.class);

	@Autowired
	UserRolesRepository rolesRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UsersRepository authRepository;

	@Value("${login.response.key}")
	String key;

	public List<Role> getAllRoles() {
		List<Role> roles = new ArrayList<Role>();
		try {
			roles = rolesRepository.findAll();

		} catch (Exception e) {
			log.error("Exception : " + e);
		}
		return roles;
	}

	public Response upsertUser(Map<String, Object> userData) {
		Object userId = userData.get("userId");
		if (null != userId) {
			return updateUser(userData);
		} else
			return createNewUser(userData);
	}

	private Response createNewUser(Map<String, Object> userData) {
		Response resp = new Response();
		Users user = new Users();
		Map<String, String> respMap = new HashMap<String, String>();
		try {

			Optional<Role> role = rolesRepository
					.findById(Long.parseLong(CommonUtils.getValueFromMap(userData, "role")));
			if (null != role && null != role.get()) {
				user.setUserName(CommonUtils.getValueFromMap(userData, "username"));
				user.setLoginId(CommonUtils.getValueFromMap(userData, "loginId"));
				user.setPassword(
						EncryptionDecryptionAES.convertToBase64(CommonUtils.getValueFromMap(userData, "password")));
				user.setContact(CommonUtils.getValueFromMap(userData, "contact"));
				user.setDateOfBirth(null);
				user.setGender(CommonUtils.getValueFromMap(userData, "gender"));
				user.setEmpId(CommonUtils.getValueFromMap(userData, "empId"));
				user.setOrgEmailId(CommonUtils.getValueFromMap(userData, "orgEmailId"));
				user.setProfilePhoto(null);
				user.setActive(true);
				user.setDeletedOn(null);
				user.setDeleted(false);
				user.setLocked(false);
				user.setPasswordChanged(false);
				user.setRole(role.get());

				usersRepository.save(user);

				resp.setResponseCode("200");
				resp.setResponseMessage("user created successfully");

				respMap.put("username", user.getUserName());
				respMap.put("loginId", user.getLoginId());
				resp.setData(respMap);
			}

		} catch (DataIntegrityViolationException e) {
			log.error("Exception : " + e);
			resp.setResponseCode("409");
			resp.setResponseMessage("User already exists!");
		} catch (Exception e) {
			log.error("Exception : " + e);
			resp.setResponseCode("500");
			resp.setResponseMessage("failed to create user!");
		}
		return resp;
	}

	private Response updateUser(Map<String, Object> userData) {
		Response resp = new Response();
		Users user = new Users();
		Users oldUser = new Users();
		Map<String, String> respMap = new HashMap<String, String>();
		try {
			Long userId = Long.parseLong(userData.get("userId").toString());
			
			Optional<Users> existingUser = usersRepository.findById(userId);
			
			Optional<Role> role = rolesRepository.findById(Long.parseLong( null != CommonUtils.getValueFromMap(userData, "role") ? CommonUtils.getValueFromMap(userData, "role") : oldUser.getRole().getRoleId().toString()));
			
			if(null != existingUser) {
				if(null != role && null != role.get()) {
					oldUser = existingUser.get();
					user.setUserId(oldUser.getUserId());
					
					user.setUserName(null != CommonUtils.getValueFromMap(userData, "username") ? CommonUtils.getValueFromMap(userData, "username") :  oldUser.getUserName());
					user.setLoginId(null != CommonUtils.getValueFromMap(userData, "loginId") ? CommonUtils.getValueFromMap(userData, "loginId") : oldUser.getLoginId());
					
					String password = CommonUtils.getValueFromMap(userData, "password");
					
					user.setPassword(EncryptionDecryptionAES.isBase64(password) ? password : EncryptionDecryptionAES.convertToBase64(password));
					user.setContact(null != CommonUtils.getValueFromMap(userData, "contact") ? CommonUtils.getValueFromMap(userData, "contact") : oldUser.getContact());
					user.setDateOfBirth(oldUser.getDateOfBirth());
					user.setGender(null != CommonUtils.getValueFromMap(userData, "gender") ? CommonUtils.getValueFromMap(userData, "gender") : oldUser.getGender());
					user.setEmpId(null != CommonUtils.getValueFromMap(userData, "empId") ? CommonUtils.getValueFromMap(userData, "empId") : oldUser.getEmpId() );
					user.setOrgEmailId(null != CommonUtils.getValueFromMap(userData, "orgEmailId") ? CommonUtils.getValueFromMap(userData, "orgEmailId") : oldUser.getOrgEmailId());
					user.setProfilePhoto(oldUser.getProfilePhoto());
					user.setPasswordChanged(oldUser.isPasswordChanged());
					user.setRole(role.get());
					user.setActive(null != userData.get("isActive") ? userData.get("isActive").toString().equalsIgnoreCase("1")  ? true : false: oldUser.isActive());
					user.setDeletedOn(oldUser.getDeletedOn());
					user.setDeleted(oldUser.isDeleted());
					user.setLocked(null != userData.get("isLocked") ? userData.get("isLocked").toString().equalsIgnoreCase("1") ? true : false : oldUser.isLocked());
					user.setCreatedOn(oldUser.getCreatedOn());
					user.setLoginAttemptCounter(oldUser.getLoginAttemptCounter());
					
					if(!password.contentEquals(oldUser.getPassword()))
						user.setPasswordChanged(false);	
					else
						user.setPasswordChanged(oldUser.isPasswordChanged());	
					
					usersRepository.save(user);
	
	
					resp.setResponseCode("200");
					resp.setResponseMessage("user updated successfully");
	
					respMap.put("username", user.getUserName());
					respMap.put("loginId", user.getLoginId());
					resp.setData(respMap);
				}
			}else {
				createNewUser(userData);
			}

		} catch (Exception e) {
			log.error("Exception : " + e);
			resp.setResponseCode("500");
			resp.setResponseMessage("failed to create user!");
		}
		return resp;
	}

	public Response updateUserPassword(String password, String confirmPassword, String token) {
		Response resp = new Response();
		try {
			if (null != token && !token.equalsIgnoreCase("null") && password.equalsIgnoreCase(confirmPassword)) {
				String decryptedToken = EncryptionDecryptionAES.decrypt(token, key);
				JSONObject json = new JSONObject(decryptedToken);
				String authentication = (String) json.get("Authentication");
				if (null != authentication && authentication.equalsIgnoreCase("true")) {
					JSONObject userData = json.getJSONObject("userData");
					if (null != userData) {
						Long userId = Long.parseLong(userData.get("userId").toString());
						Users user = authRepository.findById(userId).get();
						if (null != user && user.isActive() == true) {
							String encryptedPassword = EncryptionDecryptionAES.convertToBase64(password);
							user.setPassword(encryptedPassword);
							user.setPasswordChanged(true);
							authRepository.save(user);
							resp.setResponseCode("200");
							resp.setResponseMessage("password updated");
						}
					}
				}
			}
		} catch (Exception e) {
			resp.setResponseCode("500");
			log.error("Exception : " + e);
		}
		return resp;
	}
}
