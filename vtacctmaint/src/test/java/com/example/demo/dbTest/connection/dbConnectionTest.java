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
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;


@Slf4j
 class dbConnectionTest {
	
	MemberRepository memberRepository;
	

	@BeforeEach
	void beforeEach() {
		//DriverManager - 항상 새로운 커넥션 획득
		
		//DriverManagerDataSource dataSource = new DriverManagerDataSource(ConnectionCons.URL, ConnectionCons.USERNAME, ConnectionCons.PASSWORD);
		//memberRepository = new MemberRepository(dataSource);
		
		
		// 커넥션 풀링
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(ConnectionCons.URL);
		dataSource.setUsername(ConnectionCons.USERNAME);
		dataSource.setPassword(ConnectionCons.PASSWORD);
		
		// 히카리소스 정보
		dataSource.setPoolName("MinSeokDB");
		dataSource.setMaximumPoolSize(10);
		
		memberRepository = new MemberRepository(dataSource);
		
		
	}

	@Test
	void crud()throws SQLException {
		
		//save
		MemberDto member = new MemberDto("1946735","1946735","김민석","1");
		memberRepository.MemberSave(member);
		
		//findById
		MemberDto findMember = memberRepository.findById("1946735");
		log.info("findMember = {} ",findMember);
		assertThat(findMember).isNotNull();
		
		//update teamName 1 > 2 
		memberRepository.update(member.getMemberId(), member.getMemberPassword(), member.getMemberName(), "2");
		MemberDto updateMember = memberRepository.findById(member.getMemberId());
		assertThat(updateMember.getTeamName()).isEqualTo("2");
		
	}
}//class 
