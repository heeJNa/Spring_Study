package com.sist.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;


// annotation => xml
import com.sist.vo.BoardVO;
public interface BoardMapper {
	// 목록 : 페이징 => 인라인 뷰 @Select => rownum (top-n, 중간에 있는데이터 x)
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(project_freeboard pf_no_pk)*/no,subject,name,regdate,hit "
			+ "FROM project_freeboard)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM project_freeboard")
	public int boardTotalPage();
	// 글쓰기 : 자동증가 번호 (시퀀스) @SelectKey, @Insert
	// 			======= 서브쿼리, Sequence, SelectKey
	@SelectKey(keyProperty = "no", resultType = int.class,before=true, 
			statement = "SELECT NVL(MAX(no)+1,1) as no FROM project_freeboard")
	@Insert("INSERT INTO project_freeboard VALUES("
			+ "#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0)")
	public void boardInsert(BoardVO vo);
	// 상세보기 : 조건에 맞는 데이터 인출 @Update, @select => 데이터 분석 (그래프)
	@Update("UPDATE project_freeboard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void boardHitIncrement(int no);
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday "
			+ "FROM project_freeboard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	// 데이터 분석(데이터마이닝 => 형태소분석) => API (꼬꼬마)
	// 수정 : UPDATE @Select @Update
	// 삭제 : DELETE @Select @Delete
	
}
