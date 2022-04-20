package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.BoardEntity;

public interface BoardDAO extends JpaRepository<BoardEntity, Integer>{
	// 목록
	@Query(value="SELECT no,name,subject,content,pwd,regdate,hit "
			+ "FROM board ORDER BY no DESC "
			+ "limit :start,10",nativeQuery=true)
	// MySql, MariaDB => limit
	public List<BoardEntity> boardListData(@Param("start") Integer start);
	// 총페이지 
	@Query(value="SELECT CEIL(COUNT(*)/10.0) FROM board",nativeQuery=true)
	public int boardTotalPage();
	// 내용보기
	public BoardEntity findByNo(int no);
	// hit증가
//	@Query(value="UPDATE board SET hit=hit+1 "
//			+ "WHERE no=:no",nativeQuery = true)
//	public void hitIncrement(@Param("no") Integer no);
	// 수정
	// 수정 데이터 읽기
	// 삭제
	// 데이터 추가
}
