package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.BooksVO;

public interface BooksMapper {
	@Select("SELECT title,price FROM book")
	public List<BooksVO> bookListData(); // 구현 완료
	
	@Select("SELECT title,price FROM book "
			+ "WHERE title LIKE '%'||#{title}||'%'")
	public List<BooksVO> bookFindData(String title);
	// 						mysql => concat('%',#{title},'%')
	// 	 NVL => ifnull, 인라인뷰, limit start, 갯수 => mysql
	//	   -------------  ---------- --
    //   resultType(리턴형)    id     parameterType(매개변수)	
	// <bean id="" resultType="" parameterType="">
	/*
	 * 		resultType : 값을 받아서 저장할 때 사용
	 * 					 List<BooksVO>
	 * 					 BooksVO
	 * 					 일반 데이터형 : String, int, double ....
	 * 					 ResultSet관련 => 없는 경우 (insert,update,delete)
	 * 
	 * 		parameterType : ?에 값을 채운다
	 * 						?가 여러개 있는 경우(VO에 있는 변수 => VO
	 * 											 VO에 없는 변수 => HashMap)
	 * 						?가 한개 있는 경우 : 일반 데이터형을 사용한다
	 * 												 String, int, double ...
	 * 						PreparedStatement와 관련
	 */
	
}
