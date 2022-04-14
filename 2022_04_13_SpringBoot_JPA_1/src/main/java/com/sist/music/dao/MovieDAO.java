package com.sist.music.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.music.entity.MovieEntity;
@Repository
public interface MovieDAO extends JpaRepository<MovieEntity, Integer>{
	// 영화 목록
	// value 랑 nativeQuery가 빠지면 JPQL 
	@Query(value="SELECT mno,cno,"+ 
			"title,time,grade,reserve,genre,regdate,"+ 
			"director,actor,showuser,poster,story,mkey "
			+ "FROM movie WHERE cno=:cno ORDER BY mno ASC "
			+ "limit :start,20 ",nativeQuery = true)
	public List<MovieEntity> movieListData(@Param("cno") Integer cno, @Param("start") Integer start);
	// 총페이지
	@Query(value="SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM movie WHERE cno=:cno",nativeQuery = true)
	public int movieTotalPage(@Param("cno") Integer cno);
	// 상세보기
	public MovieEntity findByMno(int mno);
	// 찾기
	@Query(value="SELECT mno,cno,"+ 
			"title,time,grade,reserve,genre,regdate,"+ 
			"director,actor,showuser,poster,story,mkey "
			+ "FROM movie WHERE title LIKE CONCAT('%',:title,'%') "
			+ "limit :start,20",nativeQuery = true)
	public List<MovieEntity> movieFindData(@Param("title") String title,@Param("start") Integer start);
	// 찾기 총페이지
	@Query(value="SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM movie WHERE title LIKE CONCAT('%',:title,'%')",nativeQuery = true)
	public int movieFindTotalPage(@Param("title") String title);
}
