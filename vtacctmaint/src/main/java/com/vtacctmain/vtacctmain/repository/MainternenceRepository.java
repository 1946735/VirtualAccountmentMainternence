package com.vtacctmain.vtacctmain.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.support.JdbcUtils;

import com.vtacctmain.vtacctmain.Connection.DBConnectionUtil;
import com.vtacctmain.vtacctmain.controller.MainternenceDto;

import lombok.extern.slf4j.Slf4j;

/*
 * 
 * 	//gibis
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
 * 
 * */

@Slf4j
public class MainternenceRepository {

	private MainternenceDto MainterneceSave(MainternenceDto mainDto) throws SQLException {
		
		
		//String sql = "insert into gibis_Mainternece(scheduleNo,date,workingStatus,clientSigun,clientTeam,clientName,clientPhone,requestByClient,answerToClient) values (?,?,?,?,?,?,?,?,?)";
		String sql = "insert into gibis_Mainternece(clientSigun,clientTeam,clientName,clientPhone,requestByClient,answerToClient) values (?,?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement prst = null;
		
		try {
			conn = getConnection();
			prst= conn.prepareStatement(sql);		
			
			prst.setString(2, mainDto.getWorkingStatus());
			prst.setString(3, mainDto.getClientName());
			prst.setString(4, mainDto.getClientSigun());
			prst.setString(5, mainDto.getClientTeam());
			prst.setString(6, mainDto.getClientPhone());
			prst.setString(7, mainDto.getClientPhone());
			prst.setString(8, mainDto.getRequestByClient());
			prst.setString(9, mainDto.getAnswerToClient());
			
		} catch (Exception e) {
			log.error("DB mainternenceDto Error : " ,e);
			throw e;
		} finally {
			close(conn,prst,null);
		}
		
		return mainDto;
		
	}

	private void close(Connection conn, Statement stmt, ResultSet rs) {
		
		JdbcUtils.closeConnection(conn);
		JdbcUtils.closeResultSet(rs);
		JdbcUtils.closeStatement(stmt);
		
	}


	private Connection getConnection() {
		return DBConnectionUtil.getConnection();
	}

	
}
