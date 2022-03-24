package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class ReplyBoardDAO {
	@Autowired
	private ReplyBoardMapper mapper;
	
	public List<ReplyBoardVO> replyBoardListData(Map map){
		return mapper.replyBoardListData(map);
	}
	public int replyBoardTotalPage() {
		return mapper.replyBoardTotalPage();
	}
	
	public int replyBoardCount() {
		return mapper.replyBoardCount();
	}
	
	public void replyBoardInsert(ReplyBoardVO vo) {
		mapper.replyBoardInsert(vo);
	}
	
	public ReplyBoardVO replyboardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.replyboardDetailData(no);
	}
	public ReplyBoardVO replyboardUpadteData(int no) {
		return mapper.replyboardDetailData(no);
	}
	
	
	public boolean replyBoardUpdate(ReplyBoardVO vo) {
		boolean bCheck=false;
		String db_pwd = mapper.replyBoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			mapper.replyBoardUpdate(vo);
			bCheck=true;
		}else {
			bCheck=false;
		}
		return bCheck;
	}
	
	// 답변
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void replyBoardReplyInsert(int pno,ReplyBoardVO vo) {
		ReplyBoardVO pvo = mapper.replyBoardParentInfoData(pno);
		mapper.replyGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pvo.getNo());
		mapper.replyBoardReplyInsert(vo);
		mapper.replyBoardDepthIncrement(pno);
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public boolean replyBoardDelete(int no,String pwd) {
		boolean bCheck=false;
		// 비밀번호 가지고 오기
		String db_pwd=mapper.replyBoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			// 정보
			ReplyBoardVO vo = mapper.replyBoardGetRootDepth(no);
			if(vo.getDepth()==0) {
				// 댓글이 없다 => 바로삭제
				mapper.replyBoardDelete(no);
			}else {
				// 댓글이 있다 => 수정 (관리자가 삭제한 게시물입니다)
				mapper.replySubjectContentUpdate(no);
			}
			
			mapper.replyBoardDepthIncrement(no);
			bCheck=true;
		}
		return bCheck;
	}
	
	public List<ReplyBoardVO> replyBoardFindData(Map map){
		return mapper.replyBoardFindData(map);
	}
}
