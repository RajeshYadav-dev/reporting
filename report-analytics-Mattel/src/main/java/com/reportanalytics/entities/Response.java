package com.reportanalytics.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

	private String responseCode;
	private String responseMessage;
	private Object data;
}
