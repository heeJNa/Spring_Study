package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.MovieEntity;
/*
 		private int mno;
		private int cno;
		private String title,time,grade,reserve,genre,regdate,director,actor,showuser,poster,story,mkey;
 */
@Repository
// 제네릭스는 무조건 클래스형을 사용한다. (Wrapper)
public interface MovieDAO extends JpaRepository<MovieEntity, Integer>{
		// 목록
		@Query(value="SELECT mno,cno,title,time,grade,reserve,genre,regdate,director,actor,showuser,poster,story,mkey "
				+ "FROM movie WHERE cno=:cno ORDER BY mno ASC limit :start,12",nativeQuery = true)
		// JPQL => 자체 컬럼 생성
		public List<MovieEntity> movieListData(@Param("cno") Integer cno, @Param("start") Integer start);
		@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM movie "
				+ "WHERE cno=:cno",nativeQuery = true)
		public int movieTotalPage(@Param("cno") Integer cno);
		// 상세보기 
		public MovieEntity findByMno(int mno);
		
		// 검색 like findByTitleStarting() => A%
		// 검색 like findByTitleEnding() => %A
		public List<MovieEntity> findByTitleContaining(String title);
	
}
