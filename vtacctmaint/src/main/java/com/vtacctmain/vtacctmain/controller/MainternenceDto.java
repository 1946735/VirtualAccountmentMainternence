package com.vtacctmain.vtacctmain.controller;

import java.util.Date;

import lombok.Data;

@Data
public class MainternenceDto {
	
	//gibis
	Long scheduleNo; // 시퀀스 번호 (자동증가 ++)
	Date date; // yyyy년도mm월dd일hh24시mm분ss초

	// 업무처리 현황
	String workingStatus;
	
	// 고객정보
	String clientSigun;
	String clientTeam;
	String clientName;
	String clientPhone;

	// 요청사항과 처리내용
	String requestByClient;
	String answerToClient;
	
	public MainternenceDto() {
	}

	public MainternenceDto(String memberId, String memberPassword, String memberName, String teamName, Date date,
			String workingStatus, String clientSigun, String clientTeam, String clientName, String clientPhone,
			String requestByClient, String answerToClient) {
		super();
		this.date = date;
		this.workingStatus = workingStatus;
		this.clientSigun = clientSigun;
		this.clientTeam = clientTeam;
		this.clientName = clientName;
		this.clientPhone = clientPhone;
		this.requestByClient = requestByClient;
		this.answerToClient = answerToClient;
	}

	
	
}
