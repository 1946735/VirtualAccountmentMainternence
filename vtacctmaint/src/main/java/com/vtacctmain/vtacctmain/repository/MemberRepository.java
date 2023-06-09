package com.vtacctmain.vtacctmain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;

import com.vtacctmain.vtacctmain.Connection.DBConnectionUtil;
import com.vtacctmain.vtacctmain.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

/*
 * JDBC-DriverManager 사용
 * 	String memberId; 
	String memberPassword;
	String memberName;
	String teamName;
	
 * */

@Slf4j
public class MemberRepository {

	private final DataSource dataSource;
	
	public MemberRepository(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public MemberDto MemberSave(MemberDto memberDto) throws SQLException {
		
	    String sql = "insert into gibis_member(memberId,memberPassword,memberName,teamName) values(?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement prst = null;
		
		try {
			
			conn = getConnection(); //커넥션 획득
			prst= conn.prepareStatement(sql);
			prst.setString(1, memberDto.getMemberId());
			prst.setString(2, memberDto.getMemberPassword());
			prst.setString(3, memberDto.getMemberName());
			prst.setString(4, memberDto.getTeamName());
			prst.executeQuery();
			return memberDto;
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("DB Error : " ,e);
			throw e;
		} finally {
			close(conn,prst,null);
			log.info("insert close");
		}
		
	}
	public MemberDto findById (String memberId) throws SQLException {
		
		String sql = "select * from gibis_member where memberId = ?";
		
		Connection conn= null;
		PreparedStatement prst = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			prst = conn.prepareStatement(sql);
			prst.setString(1, memberId);
			
			rs = prst.executeQuery(); // commit
			
			if (rs.next()) {
				
				MemberDto memberDto = new MemberDto(); // 형성
				memberDto.setMemberId(rs.getString("memberId"));
				memberDto.setMemberName(rs.getString("memberName"));
				memberDto.setMemberPassword(rs.getString("memberPassword"));
				memberDto.setTeamName(rs.getString("teamName"));
				return memberDto;
				
			}
			else {
				throw new NoSuchElementException("Member Not found MemberId = " + memberId);
			}
			
		}catch (SQLException e) {
			// TODO: handle exception
			log.error("DB Error Massage = ",e);
			throw e;
			
		} finally {
			// TODO: handle finally clause
			close(conn,prst,rs);
			log.info("findById close");
		}
		
	}

	
	// 회원 수정
	public void update (String memberId,String memberPassword, String memberName, String teamName) throws SQLException{
		
		String sql = "update gibis_member set memberPassword = ?, memberName = ?, teamName = ? where memberId = ?";
		
		Connection conn = null;
		PreparedStatement prst = null;
		
		try {
			conn = getConnection();
			prst = conn.prepareStatement(sql);
			prst.setString(1, memberPassword);
			prst.setString(2, memberName);
			prst.setString(3, teamName);
			
			// where
			prst.setString(4, memberId);
			
			int resultSize = prst.executeUpdate();
			log.info("resultSize = {}", resultSize);
			
		} catch (SQLException e) {
			// TODO: handle exception
			log.error("DB Update Error ",e);
			throw e;
			
		} finally {
			close(conn,prst,null);
			log.info("update close");
		}
		
	}
	
	// 회원 삭제
	public void delete (String memberId) throws SQLException{
		
		String sql = "delete from gibis_member where memberId = ?";
		
		Connection conn = null;
		PreparedStatement prst = null;
		
		try {
			
			conn = dataSource.getConnection();
			prst = conn.prepareStatement(sql);
			prst.setString(1, memberId);
			
			prst.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Delete Error = ", e);
			throw e;
		} finally {
			
			close(conn,prst,null);		
			log.info("delete close");
			
		}		
		
	}
	
	
	
	/* 기능메소드
	 * 
	 */

	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		
		JdbcUtils.closeConnection(conn);
		JdbcUtils.closeResultSet(rs);
		JdbcUtils.closeStatement(stmt);
		
	}

	
	// dataSource
	private Connection getConnection() throws SQLException{
		
		Connection conn= dataSource.getConnection();
		log.info("getConnection = {}, class = {}", conn, conn.getClass());
		return conn;
	}
	
}
