package com.reportanalytics.reports.services;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reportanalytics.entities.CallDetail;

import com.reportanalytics.reports.repository.CallDetailRepository;

@Service
public class CallDetailService {

	private static final Logger log = LoggerFactory.getLogger(CallDetailService.class);
	
	@Autowired
	CallDetailRepository callDetailRepository;
	
	public CallDetail insertCallDetailData(CallDetail callDetailData) {
		CallDetail result = new CallDetail();
		try {
			CallDetail newCallDetailData = callDetailData;
			result = callDetailRepository.save(newCallDetailData);
		} catch (Exception e) {
			log.error("Error while inserting the CallDetail data : " + e);
		}
		return result;
	}
	

	public List<CallDetail> getAllReportingData(String startTime, String endTime) {
		List<CallDetail> reportingList = new ArrayList<CallDetail>();
		try {
			reportingList = callDetailRepository.getCallDetailByDate(startTime, endTime);
		} catch (Exception e) {
			log.error("Error while retriving the Call Detail data : " + e);
		}
		return reportingList;
	}

}
