package com.reportanalytics.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reportanalytics.entities.Response;
import com.reportanalytics.entities.Users;
import com.reportanalytics.repository.UsersRepository;

@Service
public class UserService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UsersRepository usersRepository;

	public Users getUserByUserId(Long userId) {
		Users user = new Users();
		try {
			Optional<Users> userOpt = usersRepository.findById(userId);
			if (null != userOpt) {
				user = userOpt.get();
			}

		} catch (Exception e) {
			log.error("Exception : " + e);
		}
		return user;
	}

	public List<Users> getAllUsers() {
		List<Users> userList = new ArrayList<Users>();
		try {
			userList = usersRepository.findAll();
		} catch (Exception e) {
			log.error("Exception : " + e);
		}
		return userList;
	}

	public Response deleteUserByUserId(String userId) {
		Response resp = new Response();
		try {
			Optional<Users> user = usersRepository.findById(Long.parseLong(userId));
			if (null != user) {
				usersRepository.delete(user.get());
				resp.setResponseCode("200");
				resp.setResponseMessage("User deleted successfully | user id :" + userId);
			} else {
				resp.setResponseCode("404");
				resp.setResponseMessage("User not found | user id :" + userId);
			}

		} catch (Exception e) {
			log.error("Exception : " + e);
			resp.setResponseCode("500");
			resp.setResponseMessage("Failed to delete User | user id :" + userId);
		}
		return resp;
	}
}
