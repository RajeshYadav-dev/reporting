package com.reportanalytics.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.reportanalytics.entities.Response;
import com.reportanalytics.entities.Users;
import com.reportanalytics.repository.UsersRepository;
import com.reportanalytics.utilities.EncryptionDecryptionAES;

@Service
public class SessionService {

	private static final Logger log = LoggerFactory.getLogger(SessionService.class);

	@Value("${login.response.key}")
	String key;

	@Value("${login.response.body}")
	String loginRespJSONTemp;

	@Value("${login.max.incorrect.attempts}")
	Integer maxIncorrectLoginAttempt;

	@Autowired
	UsersRepository userRepository;

	public Response getUserAuthDetails(String username, String password) {
		Users user = new Users();
		Response resp = new Response();
		try {
			password = EncryptionDecryptionAES.convertToBase64(password);
			user = userRepository.getUserAuthData(username, password);
			if (null != user && null != user.getRole()) {

				if (user.isActive() == true) {

					if (user.isLocked() == false) {

						// user authenticated
						resp.setResponseCode("200");
						resp.setResponseMessage("user authenticated successfully");
						resp.setData(EncryptionDecryptionAES.encrypt(populateValueInLoginJSONTemp(loginRespJSONTemp,
								"true", user.getUserId().toString(), new Date(), user.getRole().getRoleId().toString()),
								key));
						resetLoginAttemptCounter(username);
					} else {
						resp.setResponseCode("403");
						resp.setResponseMessage("user is locked");
					}

				} else {
					resp.setResponseCode("405");
					resp.setResponseMessage("user is not active");
				}

			} else {
				// user not authenticated
				increaseLoginAttemptCounter(username);

				resp.setResponseCode("401");
				resp.setResponseMessage("user failed to authenticate");
			}

		} catch (Exception e) {
			resp.setResponseCode("500");
			resp.setResponseMessage("error autenticating user");
			log.error("Exception : " + e);
		}
		return resp;
	}

	private String populateValueInLoginJSONTemp(String loginRespJSONTemp, String auth, String userId, Date loggedInTime,
			String role) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		loginRespJSONTemp = loginRespJSONTemp.replace("[[Authentication]]", auth);
		loginRespJSONTemp = loginRespJSONTemp.replace("[[userId]]", userId);
		loginRespJSONTemp = loginRespJSONTemp.replace("[[loggedInTime]]", sdf.format(loggedInTime).toString());
		loginRespJSONTemp = loginRespJSONTemp.replace("[[role]]", role);
		return loginRespJSONTemp;
	}

	public Map<String, Object> validateUserCookieToken(String token) {
		Map<String, Object> tokenDataMap = new HashMap<String, Object>();
		tokenDataMap.put("auth", false);
		tokenDataMap.put("isPasswordChanged", true);
		try {
			if (null != token && !token.equalsIgnoreCase("null")) {
				String decryptedToken = EncryptionDecryptionAES.decrypt(token, key);
				JSONObject json = new JSONObject(decryptedToken);
				String authentication = (String) json.get("Authentication");
				if (null != authentication && authentication.equalsIgnoreCase("true")) {
					JSONObject userData = json.getJSONObject("userData");
					if (null != userData) {
						Long userId = Long.parseLong(userData.get("userId").toString());
						Users user = userRepository.findById(userId).get();
						if (null != user && user.isActive() == true) {
							tokenDataMap.put("auth", true);
							tokenDataMap.put("role", user.getRole().getName());
							tokenDataMap.put("isPasswordChanged", user.isPasswordChanged());
							tokenDataMap.put("userId", userId);
							tokenDataMap.put("isActive", user.isActive());
							tokenDataMap.put("isLocked", user.isLocked());
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("Exception : " + e);
			return tokenDataMap;
		}
		return tokenDataMap;
	}

	private void increaseLoginAttemptCounter(String loginId) {
		try {
			int result = userRepository.increaseLoginAttemptCounterBy1(loginId);
			if (result > 0)
				lockUserAccount(loginId);
		} catch (Exception e) {
			log.error("Exception : " + e);
		}
	}

	private void resetLoginAttemptCounter(String loginId) {
		try {
			userRepository.resetLoginAttemptCounterBy1(loginId);
		} catch (Exception e) {
			log.error("Exception : " + e);
		}
	}

	private void lockUserAccount(String loginId) {
		try {
			userRepository.lockUserByAttemptCount(loginId, maxIncorrectLoginAttempt);
		} catch (Exception e) {
			log.error("Exception : " + e);
		}
	}
}
