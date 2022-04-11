package com.sist.dao;
import java.util.*;
import java.sql.*;
/*
  		JDBC => 자바로 데이터베이스 연결 프로그램 => 라이브러리 (ORM = mybatis, hibernate,JPA)
  																	   =======================
  																	   dataset => 자동으로 sql문장 생성
  		=> Spring Framework, Spring-boot (XML을 제거 : 설정 파일이 없이 사용)
  			==============	 ===========
  				mybatis			 jpa
  		JDBC ====> DBCP ====> ORM 
 */
public class StudentDAO {
	private Connection conn; // MySql연결
	private PreparedStatement ps; // SQL문장 전송 => 결과값 받기
	private final String URL="jdbc:mysql://localhost:3306/mydb?serverTimeZone=UTC";
	
	// 드라이버 등록
	public StudentDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 연결
	public void getConnetion() {
		try {
			conn=DriverManager.getConnection(URL,"root","rlal!@1208");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 해제 
	public void disConnetion() {
			try {
				if(ps !=null) ps.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	// ==============================MyBatis의 SqlSessionFactory
	
	//전체 목록 읽기
	public List<StudentVO> stdListData(){
		List<StudentVO> list = new ArrayList<>();
		try {
			getConnetion();
			String sql="SELECT hakbun,name,kor,eng,math,date_format(regdate,'%y-%m-%d') "
					+ "FROM student";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				StudentVO vo = new StudentVO();
				vo.setHakbun(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setKor(rs.getInt(3));
				vo.setEng(rs.getInt(4));
				vo.setMath(rs.getInt(5));
				vo.setDbday(rs.getString(6));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnetion();
		}
		return list;
	}
}
