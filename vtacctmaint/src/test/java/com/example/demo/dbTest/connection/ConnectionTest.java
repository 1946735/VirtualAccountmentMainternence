package com.example.demo.dbTest.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.vtacctmain.vtacctmain.Connection.ConnectionCons;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionTest {

	@Test
	void driverManger() throws SQLException {
		
		Connection conn1 = DriverManager.getConnection(ConnectionCons.URL,ConnectionCons.USERNAME,ConnectionCons.PASSWORD);
		Connection conn2 = DriverManager.getConnection(ConnectionCons.URL,ConnectionCons.USERNAME,ConnectionCons.PASSWORD);
		log.info("connection = {}, class1 = {}", conn1,conn1.getClass());
		log.info("connection = {}, class2 = {}", conn2,conn2.getClass());
		
		
	}

	@Test
	void dataSourceDriverManager() throws SQLException {
		//항상 새로운 커넥션을 획득함.
		DriverManagerDataSource dataSource = new DriverManagerDataSource(ConnectionCons.URL,ConnectionCons.USERNAME,ConnectionCons.PASSWORD);
		useDataSource(dataSource);
	}

	@Test
	void dataSourceConnectionPool() throws SQLException,InterruptedException {
			
		//Spring boot 에서 jdbc를 library import 하면 자동으로 생성
		//
		HikariDataSource dataSource = new HikariDataSource();
		
		// 연결하기위한 커넥션 부
		dataSource.setJdbcUrl(ConnectionCons.URL);
		dataSource.setUsername(ConnectionCons.USERNAME);
		dataSource.setPassword(ConnectionCons.PASSWORD);
		
		// 확인하기 위한소스
		dataSource.setJdbcUrl(ConnectionCons.PASSWORD);
		dataSource.setMaximumPoolSize(10);
		dataSource.setPoolName("My Data Pool");
		
		useDataSource(dataSource);
		Thread.sleep(1000);
		
	}
	
	private void useDataSource(DataSource dataSource) throws SQLException {
		
		Connection conn1 = dataSource.getConnection();
		Connection conn2 = dataSource.getConnection();
		log.info("connection = {}, class1 = {}", conn1,conn1.getClass());
		log.info("connection = {}, class2 = {}", conn2,conn2.getClass());
	}
}


