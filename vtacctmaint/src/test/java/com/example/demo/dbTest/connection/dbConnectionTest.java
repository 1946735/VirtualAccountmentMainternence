package com.example.demo.dbTest.connection;

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
 class dbConnectionTest {
	
	MemberRepository memberRepository = new MemberRepository();

	@Test
	void connection() {
		Connection connection = DBConnectionUtil.getConnection();
		assertThat(connection).isNotNull();
	}
	
	/*
	//실행되기 직전에 호출 함
	@BeforeEach
	void beforeEach() {

	
		
	
		// DrviverManager DataSource를 먼저 사용해볼 것임 
		// 항상 새로운 Connenction을 획득함. 
		DriverManagerDataSource dataSource = new DriverManagerDataSource(ConnectionCons.URL, ConnectionCons.USERNAME, ConnectionCons.PASSWORD);
		
		memberRepository = new MemberRepository(dataSource); 
		
	}
	*/
	/*
	@Test
	void connection() {
		Connection connection = DBConnectionUtil.getConnection();
		assertThat(connection).isNotNull();
		
	}
	
	@Test
	void testConnection() {
		try {
			Connection conn=
					DriverManager.getConnection(
							"jdbc:oracle:thin:@210.120.28.127:2511:orcl",
							"gibis_mainternence",
							"gibis_mainternence");
			log.info("Connection conn = ",conn);
			
		}catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	void crud()throws SQLException {
		//save
		MemberDto member = new MemberDto("1946735","1946735","김민석","1");
		memberRepository.MemberSave(member);
		
		//findById
		MemberDto findMember = memberRepository.findById("1946735");
		log.info("findMember = {} ",findMember);
		assertThat(findMember).isEqualTo(member);
		
		//update teamName 1 > 2 
		memberRepository.update(member.getMemberId(), member.getMemberPassword(), member.getMemberName(), "2");
		MemberDto updateMember = memberRepository.findById(member.getMemberId());
		assertThat(updateMember.getTeamName()).isEqualTo("2");
		
	}
	*/
}//class 
