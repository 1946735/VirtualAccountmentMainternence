package com.vtacctmain.vtacctmain.controller;

import lombok.Data;

@Data
public class MemberDto {

	String memberId; 
	String memberPassword;
	String memberName;
	String teamName;
	
	public MemberDto() {
		
	}
	
	public MemberDto(String memberId, String memberPassword, String memberName, String teamName) {
		super();
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.teamName = teamName;
	}
	
	
	
}
