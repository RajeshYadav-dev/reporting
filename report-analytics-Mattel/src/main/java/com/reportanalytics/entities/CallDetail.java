package com.reportanalytics.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "call_detail")
@Getter
@Setter
public class CallDetail implements Serializable  {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ucid", nullable = false, unique = true, length = 25)
	private String ucid;

	@Column(name = "session_id", nullable = false, length = 50)
	private String sessionId;

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="Asia/Kolkata")
	@Column(name = "call_start_time", nullable = false)
	private Date callStartTime;

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="Asia/Kolkata")
	@Column(name = "call_end_time")
	private Date callEndTime;

	@Column(name = "call_duration")
	private int callDuration;

	@Column(name = "ani", nullable = false, length = 15)
	private String ani;

	@Column(name = "dnis", nullable = false, length = 12)
	private String dnis;

	@Column(name = "language", nullable = false)
	private String language;

	@Column(name = "terminate_type", nullable = false)
	private String terminateType;

	@Column(name = "terminate_reason", nullable = false)
	private String terminateReason;

}
