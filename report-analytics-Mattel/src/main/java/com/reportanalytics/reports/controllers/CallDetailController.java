package com.reportanalytics.reports.controllers;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reportanalytics.entities.CallDetail;

import com.reportanalytics.reports.services.CallDetailService;
import com.reportanalytics.utilities.CallDetailExcel;
import com.reportanalytics.utilities.ResponseHandler;



@CrossOrigin
@RestController
@RequestMapping("/call-detail/reporting")
public class CallDetailController {

	private static final Logger log = LoggerFactory.getLogger(CallDetailController.class);

	@Autowired
	CallDetailService callDetailService;

	@PostMapping
	public ResponseEntity<?> insertReportingData(@RequestBody CallDetail jsonData) {
		CallDetail result = callDetailService.insertCallDetailData(jsonData);
		return ResponseHandler.generateResponse("CallDetail Report data inserted successfully", HttpStatus.OK, result);
	}

	@GetMapping
	public ResponseEntity<?> getAllReportingData(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime) {

		List<CallDetail> result = callDetailService.getAllReportingData(startTime, endTime);

		return ResponseHandler.generateResponse("CallDetail Report data fetched successfully", HttpStatus.OK, result);
	}

	@GetMapping("/export")
	public ResponseEntity<?> exportAllReportingData(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime) throws Exception {

		List<CallDetail> result = callDetailService.getAllReportingData(startTime, endTime);
		if (null != result && !result.isEmpty()) {
		
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());
			String fileName = "CallDetail_report_" + currentDateTime + ".xlsx";
			CallDetailExcel excelExporter = new CallDetailExcel();
			ByteArrayInputStream in = excelExporter.export(fileName, result);
			InputStreamResource file = new InputStreamResource(in);
			
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName).contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		} else {
			log.debug("result empty");
			return ResponseHandler.generateResponse("Can not create excel with empty data",HttpStatus.INTERNAL_SERVER_ERROR, result);
		}
	}
}
