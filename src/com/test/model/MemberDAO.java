package com.test.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class MemberDAO extends DAO{

	
	// 싱글턴 객체 만들기
	// 1. 싱글턴 객체를 만들 때는, 우선적으로 접근지정자는 private으로 선언한다.
	// 2. 정적 멤버로 선언한다. - static으로 선언을 한다.
	private static MemberDAO instance = new MemberDAO();
	
	// 3. 기본 생성자는 외부에서 접근이 불가능하도록 해야한다. - private으로 생성자 만듬. 
	//    외부에서 new를 사용하지 못하게 하는 접근 기법.
	private MemberDAO() {}
	
	// 4. 생성자 대신에 싱글턴 객체를  return 해 주는 getInstance() 메소드를 만들어 주자.
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	

	
	/*******************************
	 *--  회원에 관련된 DAO 메소드 작성  --*
	 *--      @param mno         --*
	 *--      @return pwd        --*
	 *******************************/
	
	// 입력한 회원의 정보를 가져오는 메소드
	public MemberDTO getmember(String id, String pwd) {
		MemberDTO dto = new MemberDTO();
		try {
			con = openConn();
			sql = "select * from member_in where m_id=? and m_pwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setM_id(rs.getString("m_id"));
				dto.setM_name(rs.getString("m_name"));
				dto.setM_pwd(rs.getString("m_pwd"));
				dto.setM_email(rs.getString("m_email"));
				dto.setM_date(rs.getString("m_date"));
				dto.setM_no(rs.getInt("m_no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(con, pstmt, rs);
		}
		return dto;
	}
	
	
	// 회원번호에 맞는 pwd를 반환하는 메소드
	public String getUserPwd(int mno) {
		String pwd = null;
		try {
			con = openConn();
			sql = "select m_pwd from member_in where m_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pwd = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(con, pstmt, rs);
		}
		return pwd;
	}	//getUserPwd() end
	
}
