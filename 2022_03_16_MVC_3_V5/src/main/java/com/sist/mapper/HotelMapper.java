package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
/* 
 *  DML
 	@Select() : 데이터 검색
 	@SelectKey() : 자동 증가번호 => SEQUENCE가 만들어져있는 경우에는 사용하지 않는다. 
 	@Insert() : 데이터 추가
 	@Update() : 데이터 수정
 	@Delete() : 데이터 삭제
 	
 	XML => <select id="" resultType="" parameterType="">
 				 -------  ------------  ----------------
 	Annotation =>method명     리턴형       매개변수
 	
 	MyBatis처리
 	1) 연결 (getConnection())
 	2) PreparedStatement 자동처리 => SQL을 전송, 값을 받는다
 	3) ResultSet 자동처리
 	4) conn.close() 자동 처리
 	----------------------------------------------------------------
 	MyBatis => SQL문장, 매개변수 처리
 	----------------------------------------------------------------
 	XML / Annotation => 60/40 ==> JSP(ASPX) => Hibernate, C#(miplatform)
 	=> ?값을 첨부
 		#{일반 데이터},${컬럼명, 테이블명} 
 		  '홍길동'			''=> 없다
 	
 */
public interface HotelMapper {
	@Select("SELECT no,name,poster,score,num "
			+ "FROM (SELECT no,name,poster,score,rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(seoul_hotel sh_no_pk)*/ no,name,poster,score "
			+ "FROM seoul_hotel)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	// SQL문장을 수행하는 메소드를 선언 = MyBatis에서 자동 구현
	public List<HotelVO> hotelListData(Map map); // ${start} = map.get("start")
	
	// 총페이지 구하기
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM seoul_hotel")
	public int hotelTotalPage(); 
	
	// 상세보기 => VO 
	@Select("SELECT no,name,poster,score,images,address "
			+ "FROM seoul_hotel "
			+ "WHERE no=#{no}")
	public HotelVO hotelDetailData(int no);
}
