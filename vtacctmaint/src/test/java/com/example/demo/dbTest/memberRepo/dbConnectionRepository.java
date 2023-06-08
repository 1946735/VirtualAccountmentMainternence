package com.example.demo.dbTest.memberRepo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.vtacctmain.vtacctmain.Connection.ConnectionCons;
import com.vtacctmain.vtacctmain.Connection.DBConnectionUtil;
import com.vtacctmain.vtacctmain.controller.MemberDto;
import com.vtacctmain.vtacctmain.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
 class dbConnectionRepository {
	
	MemberRepository memberRepository = new MemberRepository();
	
	@Test
	void crud()throws SQLException {
		//save
		MemberDto member = new MemberDto("1946735","1946735","김민석1","1");
		memberRepository.MemberSave(member);
		
		//findById
		MemberDto findMember = memberRepository.findById(member.getMemberId());
		log.info("findMember = {} ",findMember);
		assertThat(findMember).isEqualTo(member);
		
		//update teamName 1 > 2 
		memberRepository.update(member.getMemberId(), member.getMemberPassword(), member.getMemberName(), "2");
		MemberDto updateMember = memberRepository.findById(member.getMemberId());
		assertThat(updateMember.getTeamName()).isEqualTo("2");
		
	}
}//class 
