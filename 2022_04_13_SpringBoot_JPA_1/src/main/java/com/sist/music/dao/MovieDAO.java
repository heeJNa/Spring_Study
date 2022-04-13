package com.sist.music.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.music.entity.MovieEntity;

public interface MovieDAO extends JpaRepository<MovieEntity, Integer>{
	// 영화 목록
	@Query("SELECT mno,cno,"+ 
			"title,time,grade,reserve,genre,regdate,"+ 
			"director,actor,showUser,poster,story,mkey "
			+ "FROM movie WHERE cno=:cno ORDER BY mno ASC "
			+ "limit :start,20")
	public List<MovieEntity> movieListData(@Param("cno") Integer cno, @Param("start") Integer start);
	// 총페이지
	@Query("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM movie WHERE cno=:cno")
	public int movieTotalPage(@Param("cno") Integer cno);
	// 상세보기
	public MovieEntity findByMno(int mno);
	// 찾기
	@Query("SELECT mno,cno,"+ 
			"title,time,grade,reserve,genre,regdate,"+ 
			"director,actor,showUser,poster,story,mkey "
			+ "FROM movie WHERE title LIKE CONCAT('%',:title.'%') "
			+ "limit :start,20")
	public List<MovieEntity> movieFindData(@Param("title") String title,@Param("start") Integer start);
	// 찾기 총페이지
	@Query("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM movie WHERE title LIKE CONCAT('%',:title.'%')")
	public int movieFindTotalPage(@Param("title") String title);
}
