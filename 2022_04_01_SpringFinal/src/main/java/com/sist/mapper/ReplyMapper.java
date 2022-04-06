package com.sist.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.ReplyVO;
public interface ReplyMapper {
	// 목록 출력
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,group_tab,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
			+ "FROM (SELECT no,subject,name,regdate,hit,group_tab "
			+ "FROM final_reply ORDER BY group_id DESC,group_step ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ReplyVO> replyListData(Map map);
	// 총 페이지
	@Select("SELECT COUNT(*) FROM final_reply")
	public int replyRowCount();
	// 데이터 추가
	@Insert("INSERT INTO final_reply (no,name,subject,content,pwd,group_id) "
			+ "VALUES(frb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "(SELECT NVL(MAX(group_id)+1,1) FROM final_reply))")
	public void replyInsert(ReplyVO vo);
	// 상세보기
	// 1-1.
	@Update("UPDATE final_reply SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void replyHitIncrement(int no);
	// 1-2.
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,group_id "
			+ "FROM final_reply "
			+ "WHERE no=#{no}")
	public ReplyVO replyDetailData(int no);
	// 1-3.
	@Select("SELECT COUNT(*) FROM final_reply "
			+ "WHERE group_id=#{group_id} AND group_step=1")
	public int replyCount(int group_id);
	// 답변
	// 1-1. 상위(답변대상) => group_id, group_step, group_tab
	@Select("SELECT group_id,group_step,group_tab "
			+ "FROM final_reply "
			+ "WHERE no=#{no}")
	public ReplyVO replyParentInfoData(int no);
	// 1-2. 실제 답변 삽입
	@Insert("INSERT INTO final_reply(no,name,subject,content,pwd,"
			+ "group_id,group_step,group_tab) "
			+ "VALUES(frb_no_seq.nextval,#{name},#{subject},#{content},#{pwd} ,#{group_id},#{group_step},#{group_tab})")
	public void replyReplyInsert(ReplyVO vo);
	// 비밀번호 읽기
	@Select("SELECT pwd FROM final_reply "
			+ "WHERE no=#{no}")
	public String replyGetPassword(int no);
	// 수정
	@Update("UPDATE final_reply SET "
			+ "name=#{name},subject=#{subject},content=#{content} "
			+ "WHERE no=#{no}")
	public void replyUpdate(ReplyVO vo);
	// 삭제
	@Delete("DELETE FROM final_reply "
			+ "WHERE group_id=#{group_id}")
	public void replyDelete1(int group_id);
	@Delete("DELETE FROM final_reply "
			+ "WHERE no=#{no}")
	public void replyDelete2(int no);
}
