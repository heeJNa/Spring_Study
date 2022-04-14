package com.sist.music.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.music.entity.MusicEntity;

@Repository
public interface MusicDAO extends JpaRepository<MusicEntity, Integer>{
	// 목록 => 페이지 나누기
	@Query(value="SELECT no,cno,idcrement,title,singer,album,"
			+ "state,poster,mkey "
			+ "FROM music WHERE cno=:cno ORDER BY no ASC "
			+ "limit :start,20",nativeQuery=true)
	public List<MusicEntity> musicListData(@Param("cno") Integer cno,@Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/20.0) FROM music "
			+ "WHERE cno=:cno",nativeQuery=true)
	public int musicTop200TotalPage(@Param("cno") Integer cno);
	
}
