package com.sist.mapper;
// Mapper => PreparedStatement, ResultSet
// MyBatis에서 1. SQL문장, 2.?에 값 설정, 3. 받는 데이터 형
//     					   --------------  ------------------
//						 	매개변수로 설정(parameter Type) 	리턴형으로 설정(resultType)
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.BoardVO;
public interface BoardMapper {
	// 목록 출력 => Read
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(project_freeboard pf_no_pk)*/no,subject,name,regdate,hit "
			+ "FROM project_freeboard)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	
	@Select("SELECT COUNT(*) FROM project_freeboard")
	public int boardRowCount();
	// 상세보기 => Read
	@Update("UPDATE project_freeboard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitincrement(int no);
	@Select("SELECT no,name,subject,content,"
			+ "TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit "
			+ "FROM project_freeboard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	// 글쓰기 => Create
	// 기본키 => MyBatis => @SelectKet, 서브쿼리, 시퀀스 => 자동 증가 번호
	@SelectKey(statement="SELECT NVL(MAX(no)+1,1) FROM project_freeboard", keyProperty = "no", before=true, resultType=int.class )
	@Insert("INSERT INTO project_freeboard (no,name,subject,content,pwd) "
			+ "VALUES (#{no},#{name},#{subject},#{content},#{pwd})")
	public void boardInsert(BoardVO vo);
	// VO => #{name} => vo.getName() , #{subject} => vo.getSubject()
	   // Map => #{start} => map.get("start")
	@Select("SELECT pwd FROM final_board "
			  +"WHERE no=#{no}")
	public String boardGetPassword(int no);
	   // 수정하기   => Update
	@Update("UPDATE final_board SET "
			  +"name=#{name},subject=#{subject},content=#{content} "
			  +"WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	   // 삭제        => Delete 
	@Delete("DELETE FROM final_board "
			  +"WHERE no=#{no}")
	public void boardDelete(int no);
	   // DB , XML , IO => ***메모리 (ArrayList) add,remove,set,get
}
