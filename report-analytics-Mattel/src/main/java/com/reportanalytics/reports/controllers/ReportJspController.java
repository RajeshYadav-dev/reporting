package com.reportanalytics.reports.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reportanalytics.reports.services.CallDetailService;

import com.reportanalytics.utilities.DateUtils;

@Controller
@RequestMapping("/report")
public class ReportJspController {

	@Autowired
	CallDetailService callDetailService;

	@GetMapping("/call-detail")
	public String callDetailServiceReportPage(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = DateUtils.getStartOfDay();
		String startDateStr = sdf.format(startDate);
		Date endDate = DateUtils.getEndOfDay();
		String endDateStr = sdf.format(endDate);
		model.addAttribute("list", callDetailService.getAllReportingData(startDateStr, endDateStr));
		return "report/call-detail-report";
	}

}
