package com.sist.music.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.music.entity.MusicEntity;
@Repository
public interface MusicDAO extends JpaRepository<MusicEntity, Integer>{
	@Query(value="SELECT no,cno,title,singer,album,state,idcrement,"
			+ "poster,mkey "
			+ "FROM music WHERE cno=:cno ORDER BY no ASC limit :start,20",nativeQuery = true)
	public List<MusicEntity> musicTop200ListData(@Param("cno") Integer cno, @Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/20.0) FROM music WHERE cno=:cno",nativeQuery = true)
	public int musicTotalPage(@Param("cno") Integer cno);
	
	public MusicEntity findByNo(int no); //자동 처리
	// SELECT * FROM music WHERE no=no
	/*
	 * 		findBySingerAndTitle(String singer,String title)
	 * 		WHERE singer = singer AND title = title
	 * 		findByNameLike(String name) => WHERE name LIKE ...
	 *  	메소드명으로 SQL문장 제작
	 *  	CONCAT('%',:title,'%') = mysql, mariadb
	 *  	:title => 1? 2? ...
	 */
	@Query(value="SELECT no,cno,title,singer,album,state,idcrement,"
			+ "poster,mkey "
			+ "FROM music WHERE title LIKE CONCAT('%',:title,'%') "
			+ "limit :start,20",nativeQuery = true)
	public List<MusicEntity> musicFindData(@Param("title") String title,@Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/20.0) FROM music "
			+ "WHERE title LIKE CONCAT('%',:title,'%')",nativeQuery = true)
	public int musicFindTotalPage(@Param("title") String	title);
}
