package com.reportanalytics.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static <T> ResponseEntity<Object> generateResponse(String message, HttpStatus status, T responseObj) {
		Map<String, Object> map = new HashMap<>();
		map.put("message", message);
		map.put("status", status.value());
		LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
//		map.put("timestamp", LocalDateTime.now());
		map.put("timestamp", DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(dateTime));
		if (responseObj != null) {
			map.put("data", responseObj);
		}
		return new ResponseEntity<>(map, status);
	}
}
