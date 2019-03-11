package com.study.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDao {//데이터베이스 연결하는 코드는 전부 DAO에 작성
						//insert, delete, 등등 
						//메서드를 memberInsert() 이렇게?
						//회원체크 시에는? memberSearch() ??? 
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	private static MemberDao instance = new MemberDao();
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	public int insertMember(MemberDto dto) {
		
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into members values (?,?,?,?,?,?)";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.geteMail());
			pstmt.setTimestamp(5, dto.getrDate());
			pstmt.setString(6, dto.getAddress());
			pstmt.executeUpdate();
			ri = MemberDao.MEMBER_JOIN_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int confirmId(String id) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri = MemberDao.MEMBER_EXISTENT;
			}else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int userCheck(String id, String pw) {
		int ri = 0;
		String dbPw;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from members where id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			System.out.println("유저체크진행");
			
			if(set.next()) {
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {
					System.out.println("login ok");
					ri = MemberDao.MEMBER_LOGIN_SUCCESS; // 로그인 ok
				}else {
					System.out.println("login fail");
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD; // 비번 x
				}
			}else {
				System.out.println("login fail");
				ri = MemberDao.MEMBER_LOGIN_IS_NOT;		// 아이디 x
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;	
	}
	
	public MemberDto getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from members where id = ?";
		MemberDto dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new MemberDto();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
				dto.setrDate(set.getTimestamp("rDate"));
				dto.setAddress(set.getString("address"));
				System.out.println("겟멤버 ok");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;	
	}
	
	public int updateMember(MemberDto dto) {
		int ri = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update members set pw=?,eMail=?,address=? where id=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.geteMail());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getId());
			ri = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.printf("업데이트 ok, %d",ri);
		return ri;
	}
	
	
	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;

		try {
			//lookup 함수의 파라메터는 context.xml에 설정된 name과 동일해야한다.
			//name(jdbc/Oracle11g)
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		}catch(Exception e) {
			System.out.println("================ \n");
			e.printStackTrace();
		}
		
		return con;
	}
	
}
